package jplus.analyzer.nullability;

import jplus.analyzer.nullability.dataflow.NullState;
import jplus.base.JPlus25Parser;
import jplus.base.JPlus25ParserBaseVisitor;
import jplus.base.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

class ConditionVisitor
    extends JPlus25ParserBaseVisitor<ConditionResult> {

    private final NullabilityChecker nullabilityChecker;

    private final SymbolTable before;

    ConditionVisitor(NullabilityChecker nullabilityChecker) {
        this.nullabilityChecker = nullabilityChecker;
        this.before = nullabilityChecker.getCurrentSymbolTable();
    }

    ConditionVisitor(NullabilityChecker nullabilityChecker, SymbolTable before) {
        this.nullabilityChecker = nullabilityChecker;
        this.before = before;
    }

    @Override
    public ConditionResult visitEqualityExpression(JPlus25Parser.EqualityExpressionContext ctx) {
        // base case
        if (ctx.EQUAL() == null && ctx.NOTEQUAL() == null) {
            return visit(ctx.relationalExpression());
        }

        SymbolTable thenTable = before.copy();
        SymbolTable elseTable = before.copy();

        NullState rhsState = nullabilityChecker.evalRHS(ctx.relationalExpression(), before);
        if (rhsState != NullState.NULL) {
            return new ConditionResult(thenTable, elseTable);
        }

        var lhs = extractLValue(ctx.equalityExpression());
        System.err.println("[ConditionVisitor][visitEqualityExpression] lhs = " + lhs);
        if (lhs == null) {
            return new ConditionResult(thenTable, elseTable);
        }

        if (ctx.NOTEQUAL() != null) {
            System.err.println("[ConditionVisitor][visitEqualityExpression] NotEqual");
            nullabilityChecker.updateNullState(lhs, thenTable, NullState.NON_NULL);
            nullabilityChecker.updateNullState(lhs, elseTable, NullState.NULL);

            strengthenReceiver(lhs, thenTable);
        }

        if (ctx.EQUAL() != null) {
            System.err.println("[ConditionVisitor][visitEqualityExpression] equal");
            nullabilityChecker.updateNullState(lhs, thenTable, NullState.NULL);
            nullabilityChecker.updateNullState(lhs, elseTable, NullState.NON_NULL);

            strengthenReceiver(lhs, thenTable);
            strengthenReceiver(lhs, elseTable);
        }

        return new ConditionResult(thenTable, elseTable);
    }

    void strengthenReceiver(ParserRuleContext lhs, SymbolTable thenTable) {
        ParserRuleContext recv = null;

        if (lhs instanceof JPlus25Parser.PrimaryNoNewArrayExprMethodInvocationContext m) {
            recv = m.expressionName();
        }

        if (lhs instanceof JPlus25Parser.PrimaryNoNewArrayTypeMethodInvocationContext t) {
            recv = t.typeName();
        }

        // 필요시 array / super / qualified 확장
        //primaryNoNewArraySuperMethodInvocation
        //primaryNoNewArrayQualifiedSuperMethodInvocation

        if (recv != null) {
            nullabilityChecker.updateNullState(recv, thenTable, NullState.NON_NULL);
        }
    }

    /**
     * extractLValue
     *
     * ParseTree에서 "단일 reference 식"만 추출합니다.
     * - 단일식: SymbolTable에서 null/non-null 상태를 추적할 수 있는 참조 타입 식
     */
    ParserRuleContext extractLValue(ParseTree expr) {
        System.err.println("[extractLValue] expr = " + expr.getClass().getSimpleName());
        if (expr == null) return null;

        if (expr instanceof JPlus25Parser.EqualityExpressionContext eq) {
            return extractLValue(eq.relationalExpression());
        }

        if (expr instanceof JPlus25Parser.RelationalExpressionContext re) {
            return extractLValue(re.shiftExpression());
        }

        if (expr instanceof JPlus25Parser.ShiftExpressionContext se) {
            return extractLValue(se.additiveExpression());
        }

        if (expr instanceof JPlus25Parser.AdditiveExpressionContext ae) {
            return extractLValue(ae.multiplicativeExpression());
        }

        if (expr instanceof JPlus25Parser.MultiplicativeExpressionContext me) {
            return extractLValue(me.unaryExpression());
        }

        // -------------------------------
        // UnaryExpression → !x, -x 등은 단일식 아님
        // -------------------------------
        if (expr instanceof JPlus25Parser.UnaryExpressionContext ue) {
            if (ue.unaryExpression() != null) return null;
            return extractLValue(ue.unaryExpressionNotPlusMinus());
        }

        if (expr instanceof JPlus25Parser.UnaryExpressionNotPlusMinusContext uenpm) {
            return extractLValue(uenpm.postfixExpression());
        }

        if (expr instanceof JPlus25Parser.ExpressionNameContext exprNm) {
            return exprNm;
        }

        // -------------------------------
        // Primary
        // -------------------------------
        if (expr instanceof JPlus25Parser.PrimaryContext primary) {
            // primaryNoNewArray
            if (primary.primaryNoNewArray() != null)
                return extractLValue(primary.primaryNoNewArray());

            // arrayCreationExpression
            if (primary.arrayCreationExpression() != null)
                return primary.arrayCreationExpression();
        }

        // -------------------------------
        // PrimaryNoNewArray
        // -------------------------------
        if (expr instanceof JPlus25Parser.PrimaryNoNewArrayContext p) {
            // this / Type.this
            if (p instanceof JPlus25Parser.PrimaryNoNewArrayThisContext) return p;
            if (p instanceof JPlus25Parser.PrimaryNoNewArrayQualifiedThisContext) return p;

            // expressionName → 변수 / 필드
            if (p instanceof JPlus25Parser.PrimaryNoNewArrayLiteralContext literal) {
                if (literal.literal() != null && literal.literal().NullLiteral() != null)
                    return literal; // null literal만 단일식
                return null; // primitive literal은 제외
            }

            if (p instanceof JPlus25Parser.PrimaryNoNewArrayExprQualifiedClassInstanceCreationContext
                    || p instanceof JPlus25Parser.PrimaryNoNewArrayClassInstanceCreationContext
                    || p instanceof JPlus25Parser.PrimaryNoNewArrayArrayQualifiedClassInstanceCreationContext
                    || p instanceof JPlus25Parser.PrimaryNoNewArrayArrayCreationWithInitAccessContext
                    || p instanceof JPlus25Parser.PrimaryNoNewArrayArrayAccessContext
                    || p instanceof JPlus25Parser.PrimaryNoNewArrayArrayFieldAccessContext
                    || p instanceof JPlus25Parser.PrimaryNoNewArraySuperFieldAccessContext
                    || p instanceof JPlus25Parser.PrimaryNoNewArrayQualifiedSuperFieldAccessContext) {
                return p; // 안전하게 단일식 처리
            }

            // method / constructor reference → 반환 타입이 reference인지 확인
            if (p instanceof JPlus25Parser.PrimaryNoNewArrayMethodInvocationContext m) {
                if (returnsReferenceType(m)) return m;
                return null;
            }
            if (p instanceof JPlus25Parser.PrimaryNoNewArrayExprMethodInvocationContext m) {
                if (returnsReferenceType(m)) return m;
                return null;
            }
            if (p instanceof JPlus25Parser.PrimaryNoNewArrayTypeMethodInvocationContext m) {
                if (returnsReferenceType(m)) return m;
                return null;
            }
            if (p instanceof JPlus25Parser.PrimaryNoNewArrayArrayMethodInvocationContext m) {
                if (returnsReferenceType(m)) return m;
                return null;
            }
            if (p instanceof JPlus25Parser.PrimaryNoNewArraySuperMethodInvocationContext m) {
                if (returnsReferenceType(m)) return m;
                return null;
            }
            if (p instanceof JPlus25Parser.PrimaryNoNewArrayQualifiedSuperMethodInvocationContext m) {
                if (returnsReferenceType(m)) return m;
                return null;
            }

            // Method / Constructor Reference (::) → 항상 단일식
            if (p instanceof JPlus25Parser.PrimaryNoNewArrayExprMethodReferenceContext
                    || p instanceof JPlus25Parser.PrimaryNoNewArrayArrayMethodReferenceContext
                    || p instanceof JPlus25Parser.PrimaryNoNewArrayTypeMethodReferenceContext
                    || p instanceof JPlus25Parser.PrimaryNoNewArraySuperMethodReferenceContext
                    || p instanceof JPlus25Parser.PrimaryNoNewArrayQualifiedSuperMethodReferenceContext
                    || p instanceof JPlus25Parser.PrimaryNoNewArrayConstructorReferenceContext
                    || p instanceof JPlus25Parser.PrimaryNoNewArrayArrayConstructorReferenceContext) {
                return p;
            }

            // 괄호 식 → 재귀 검사
            if (p instanceof JPlus25Parser.PrimaryNoNewArrayParenExpressionContext paren) {
                return extractLValue(paren.expression());
            }
        }

        // -------------------------------
        // PostfixExpression → primary 재귀
        // -------------------------------
        if (expr instanceof JPlus25Parser.PostfixExpressionContext post) {
            if (post.expressionName() != null) return extractLValue(post.expressionName());
            if (post.primary() != null) return extractLValue(post.primary());
            return null; // ++/-- 등은 단일식 아님
        }

        // -------------------------------
        // ArrayCreationExpression → new Type[]
        // -------------------------------
        if (expr instanceof JPlus25Parser.ArrayCreationExpressionContext array) {
            return array;
        }

        // -------------------------------
        // Equality / Relational / Boolean 식 → 단일식 아님
        // -------------------------------
        // 여기서는 모두 null 반환
        return null;
    }

    /**
     * returnsReferenceType
     *
     * 주어진 MethodInvocationContext의 반환 타입이 reference인지 확인
     */
    boolean returnsReferenceType(ParserRuleContext methodCall) {
        //Type returnType = resolveMethodType(methodCall);
        //return returnType != null && !returnType.isPrimitive();
        return true;
    }

    @Override
    public ConditionResult visitRelationalExpression(JPlus25Parser.RelationalExpressionContext ctx) {
        System.err.println("[RelationalExpression]");

        if (ctx.INSTANCEOF() != null) {
            System.err.println("[InstanceOf]");
            SymbolTable t = before.copy();
            SymbolTable f = before.copy();

            var target = extractLValue(ctx.relationalExpression());
            if (target != null) {
                nullabilityChecker.updateNullState(target, t, NullState.NON_NULL);
            }

            return new ConditionResult(t, f);
        }

        // <, >, <=, >=
        if (ctx.LT() != null || ctx.GT() != null
                || ctx.LE() != null || ctx.GE() != null) {

            SymbolTable t = before.copy();
            SymbolTable f = before.copy();

            // 좌변에서 receiver 추출
            var lhs = extractLValue(ctx.relationalExpression());
            if (lhs != null) {
                // 비교가 평가되었다는 사실 자체가 NON_NULL 보장
                nullabilityChecker.updateNullState(lhs, t, NullState.NON_NULL);
                nullabilityChecker.updateNullState(lhs, f, NullState.NON_NULL);

                strengthenReceiver(lhs, t);
                strengthenReceiver(lhs, f);
            }

            return new ConditionResult(t, f);
        }

        return new ConditionResult(before.copy(), before.copy());
    }

    @Override
    public ConditionResult visitConditionalAndExpression(JPlus25Parser.ConditionalAndExpressionContext ctx
    ) {
        if (ctx.conditionalAndExpression() == null) {
            return visit(ctx.inclusiveOrExpression());
        }

        ConditionResult left =
                visit(ctx.conditionalAndExpression());

        ConditionResult right =
                new ConditionVisitor(nullabilityChecker, left.whenTrue)
                        .visit(ctx.inclusiveOrExpression());

        return new ConditionResult(
                right.whenTrue,
                nullabilityChecker.join(left.whenFalse, right.whenFalse)
        );
    }


    @Override
    public ConditionResult visitConditionalOrExpression(JPlus25Parser.ConditionalOrExpressionContext ctx
    ) {
        if (ctx.conditionalOrExpression() == null) {
            return visit(ctx.conditionalAndExpression());
        }

        ConditionResult left =
                visit(ctx.conditionalOrExpression());

        ConditionResult right =
                new ConditionVisitor(nullabilityChecker, left.whenFalse)
                        .visit(ctx.conditionalAndExpression());

        return new ConditionResult(
                nullabilityChecker.join(left.whenTrue, right.whenTrue),
                right.whenFalse
        );
    }
}
