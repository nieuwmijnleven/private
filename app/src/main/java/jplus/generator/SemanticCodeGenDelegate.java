package jplus.generator;

import jplus.base.JPlus25Parser;
import jplus.base.JPlus25Parser.ApplyDeclarationContext;
import jplus.base.JPlus25Parser.ExpressionNameContext;
import jplus.base.JPlus25Parser.FieldAccessContext;
import jplus.base.JPlus25Parser.MethodInvocationContext;
import jplus.base.JPlus25Parser.NullCoalescingExpressionContext;
import jplus.base.JPlus25Parser.PNNAContext;
import jplus.base.JPlus25Parser.PrimaryNoNewArrayContext;
import jplus.base.JPlus25Parser.UnannTypeContext;
import jplus.editor.FragmentedText;
import jplus.util.ParserUtils;
import jplus.util.Utils;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class SemanticCodeGenDelegate implements CodeGenDelegate {

    private boolean processed = false;

    private String updatedContextString = "";

    private final JPlusParserRuleContext ctx;

    public SemanticCodeGenDelegate(JPlusParserRuleContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public String getText() {
        if (processed) return updatedContextString;
        processed = true;

        if (ctx instanceof ApplyDeclarationContext applyDeclarationCtx) {
            System.err.println("[ApplyDeclarationCtx] code = " + Utils.getTokenString(applyDeclarationCtx));
            return replaceApplyStatementWithComment(applyDeclarationCtx);
        } else if (ctx instanceof UnannTypeContext unannTypeCtx && unannTypeCtx.unannReferenceType() != null) {
            return replaceNullType(unannTypeCtx);
        } else if (ctx instanceof NullCoalescingExpressionContext nullCoalescingCtx && nullCoalescingCtx.ELVIS() != null) {
            return replaceElvisOperator(nullCoalescingCtx);
        } else if (ctx instanceof PrimaryNoNewArrayContext primaryNoNewArrayCtx) {
            return processPrimaryNoNewArray(primaryNoNewArrayCtx);
        } else if (ctx instanceof ExpressionNameContext expressionNameCtx) {
            return processExpressionName(expressionNameCtx);
        } else if (ctx instanceof FieldAccessContext fieldAccessCtx) {
            return processFieldAccess(fieldAccessCtx);
        } else if (ctx instanceof MethodInvocationContext methodInvocationCtx) {
            return processMethodInvocation(methodInvocationCtx);
        }

        return processDefaultText();
    }

    private String processMethodInvocation(MethodInvocationContext methodInvocationCtx) {
        ensureChildTextInitialized();

        String replaced = Utils.getTokenString(methodInvocationCtx).replace("?.", ".");
        System.err.println("[processMethodInvocation] replaced = " + replaced);
        return updateContextString(methodInvocationCtx, replaced);
    }

    private String processFieldAccess(FieldAccessContext fieldAccessCtx) {
        ensureChildTextInitialized();

        String replaced = Utils.getTokenString(fieldAccessCtx).replace("?.", ".");
        System.err.println("[processFieldAccess] replaced = " + replaced);
        return updateContextString(fieldAccessCtx, replaced);
    }

    private String processExpressionName(ExpressionNameContext expressionNameCtx) {
        ensureChildTextInitialized();

        String replaced = Utils.getTokenString(expressionNameCtx).replace("?.", ".");
        System.err.println("[ExpressionNameContext] replaced = " + replaced);
        return updateContextString(expressionNameCtx, replaced);
    }

    private void ensureChildTextInitialized() {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ctx.getChild(i).getText();
        }
    }

    private String processPrimaryNoNewArray(PrimaryNoNewArrayContext ctx) {
        ensureChildTextInitialized();

        String replaced = Utils.getTokenString(ctx).replace("?.", ".");
        System.err.println("[processPrimaryNoNewArray] replaced = " + replaced);
        return updateContextString(ctx, replaced);
    }

    private String getBase(PrimaryNoNewArrayContext ctx) {
        ensureChildTextInitialized();
        String contextString = Utils.getTokenString(ctx);
//        String contextString = ctx.getText();
        int pnnaPartIndex = contextString.length();
        if (ctx.pNNA() != null) {
            String pnnaPart = Utils.getTokenString(ctx.pNNA());
            pnnaPartIndex = contextString.indexOf(pnnaPart);
        }

        return contextString.substring(0, pnnaPartIndex);
    }

    private String getBase(PNNAContext ctx) {
        ensureChildTextInitialized();
        String contextString = Utils.getTokenString(ctx);
//        String contextString = ctx.getText();
        int pnnaPartIndex = contextString.length();
        if (ctx.pNNA() != null) {
            String pnnaPart = Utils.getTokenString(ctx.pNNA());
            pnnaPartIndex = contextString.indexOf(pnnaPart);
        }

        String base = contextString.substring(0, pnnaPartIndex);
        return base.replaceAll("^(\\.|\\?\\.)", "");
    }

    private String processNullsafety(ExpressionNameContext ctx, ParserRuleContext ruleCtx) {
        Deque<ExpressionNameContext> expressionNameContextDeque = new ArrayDeque<>();
        ExpressionNameContext current = ctx;
        expressionNameContextDeque.addFirst(current);
        while (current.expressionName() != null) {
            current = current.expressionName();
            expressionNameContextDeque.addFirst(current);
        }

        String identifier = Utils.getTokenString(expressionNameContextDeque.removeFirst().identifier());
        System.err.println("[processNullSafety] identifier = " + identifier);
        String replaced = "java.util.Optional.ofNullable(" + identifier;
        int i = 0;
        while (!expressionNameContextDeque.isEmpty()) {
            String curTVar = "t" + i;
            var expressionNameContext = expressionNameContextDeque.removeFirst();
            String member = Utils.getTokenString(expressionNameContext.identifier());
            System.err.println("[processNullSafety] member = " + member);
            if (expressionNameContext.NULLSAFE() != null) {
                replaced += ").map(" + curTVar + " -> " + curTVar + "." + member;
                ++i;
            } else {
                replaced += "." + member;
            }
        }

        if (ruleCtx instanceof MethodInvocationContext miCtx) {
            String method = getMethodPart(miCtx);
            System.err.println("[processNullsafety] method = " + method);

            String curTVar = "t" + i;
            replaced += ").ifPresent(" + curTVar + " -> " + curTVar + "." + method + ")";
            return replaced;
        } else if (ruleCtx instanceof PrimaryNoNewArrayContext pnnaCtx) {
            String contextString = Utils.getTokenString(pnnaCtx);
            String remainderPart = contextString.substring(Utils.getTokenString(ctx).length()).replaceAll("^(\\.|\\?\\.)", "");
            System.err.println("[processNullsafety] remainderPart = " + remainderPart);
            String curTVar = "t" + i;
            if (pnnaCtx.NULLSAFE() != null) {
                replaced += ").map(" + curTVar + " -> " + curTVar + "." + remainderPart;
                ++i;
            } else {
                replaced += "." + remainderPart;
            }
        }

        replaced += ").orElse(null)";
        return replaced;
    }

    private String updateContextString(ParserRuleContext ctx, String replaced) {
        TextChangeRange range = Utils.getTextChangeRange(getOriginalText(), ctx);
        //_getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
        //System.err.println("[updateContextString] before debugString = " + getDebugString());
        updateFragmentedText(range, replaced);
        //System.err.println("[updateContextString] after debugString = " + getDebugString());
        this.updatedContextString = replaced;
        return replaced;
    }

    private String forceUpdateContextString(ParserRuleContext ctx) {
        System.err.println("[forceUpdateContextString] ParserRuleContext = " + ctx.getClass().getSimpleName());
        TextChangeRange range = Utils.getTextChangeRange(getOriginalText(), ctx);
        String contextString = Utils.getTokenString(ctx);
        String replaced = projectUpdatesOn(range, contextString);
        System.err.println("[forceUpdateContextString] contextString = " + contextString);
        System.err.println("[forceUpdateContextString] replaced = " + replaced);
        return updateContextString(ctx, replaced);
    }

    private String replaceApplyStatementWithComment(ApplyDeclarationContext ApplyDeclarationCtx) {
        String originalText = getOriginalText();
        TextChangeRange range = Utils.getTextChangeRange(originalText, ApplyDeclarationCtx);
        String replaced = Utils.getTokenString(ApplyDeclarationCtx).replaceFirst("^", "//").replaceAll("\n", "\n//");
        updateFragmentedText(range, replaced);
        this.updatedContextString = replaced;
        return null;
    }

    private String replaceNullType(UnannTypeContext ctx) {
        String unannTypeText = Utils.getTokenString(ctx);
        if (ctx.QUESTION() != null) {
            String replaced = "@org.jspecify.annotations.Nullable " + unannTypeText.substring(0, unannTypeText.length()-1);
            return updateContextString(ctx, replaced);
        }
        return null;
    }

    private String replaceElvisOperator(NullCoalescingExpressionContext ctx) {
        System.err.println("[replaceElvisOperator] contextString = " + Utils.getTokenString(ctx));
        ensureChildTextInitialized();

        Optional<String> conditionalOrExpressionString = getRangeText(ctx.conditionalOrExpression());
        Optional<String> rhsExpressionString = getRhsExpressionRangeText(ctx);

        String replaced;
        if (ParserUtils.usesNullSafety(ctx)) {
            replaced = conditionalOrExpressionString.orElse("null");
            replaced = replaced.replace("orElse(null)", "orElseGet(() -> " + rhsExpressionString.orElse("null") + ")");
        } else {
            replaced = "java.util.Optional.ofNullable(";
            replaced += conditionalOrExpressionString.orElse("null") + ")";
            replaced += ".orElseGet(() -> " + rhsExpressionString.orElse("null") + ")";
        }

        System.err.println("[replaceElvisOperator] replaced = " + replaced);
        return updateContextString(ctx, replaced);
    }

    private Optional<String> getRangeText(ParserRuleContext ctx) {
        TextChangeRange range = Utils.getTextChangeRange(getOriginalText(), ctx);
        return getRangeFragmentedText(range);
    }

    private Optional<String> getRhsExpressionRangeText(NullCoalescingExpressionContext ctx) {
        if (ctx.nullCoalescingExpression() != null) {
            return getRangeText(ctx.nullCoalescingExpression());
        } else if (ctx.lambdaExpression() != null) {
            return getRangeText(ctx.lambdaExpression());
        }
        return Optional.empty();
    }

    private Optional<String> getRangeFragmentedText(TextChangeRange range) {
        FragmentedText fragmentedText = getCurrentFragmentedText();
        return fragmentedText.findFragmentByRange(range);
    }

    private FragmentedText getCurrentFragmentedText() {
        CodeGenContext codeGenContext = CodeGenContext.current();
        FragmentedText fragmentedText = codeGenContext.getFragmentedText();
        return fragmentedText;
    }

    private String replaceBaseWithOptional(PrimaryNoNewArrayContext ctx, ParserRuleContext ruleCtx) {
        if (ctx.expressionName() != null && ParserUtils.usesNullSafety(ctx.expressionName())) {
            return ctx.expressionName().getText();
        }

        String base = getBase(ctx);
        System.err.println("[replaceBaseWithOptional] base = " + base);
        System.err.println("[replaceBaseWithOptional] ruleCtx = " + ruleCtx.getClass().getSimpleName());

        if (ctx.NULLSAFE() != null) {
            String[] tokens = base.split("\\?\\.");
            String instance = tokens[0];
            String member = tokens[1];

            System.err.println("[replaceBaseWithOptional] instance = " + instance);
            System.err.println("[replaceBaseWithOptional] member = " + member);

            return "java.util.Optional.ofNullable(" + instance + ").map(t0 -> t0." + member +  replacePNNAWithOptional(ctx.pNNA(), 1, ruleCtx);
        }

        return "java.util.Optional.ofNullable(" + base + replacePNNAWithOptional(ctx.pNNA(), 0, ruleCtx);
    }

    private String replacePNNAWithOptional(PNNAContext ctx, int index, ParserRuleContext ruleCtx) {
        if (ctx == null) {
            String replaced = "";

            if (ruleCtx instanceof FieldAccessContext faCtx) {
                String member = Utils.getTokenString(faCtx.identifier());
                if (faCtx.NULLSAFE() != null) {
                    String curTVar = "t" + index;
                    replaced = ").map(" + curTVar + " -> " + curTVar + "." + member;
                } else {
                    replaced = "." + member;
                }
            } else if (ruleCtx instanceof MethodInvocationContext miCtx) {
                String method = getMethodPart(miCtx);
                System.err.println("[replacePNNAWithOptional] method = " + method);

                String curTVar = "t" + index;
                replaced += ").ifPresent(" + curTVar + " -> " + curTVar + "." + method + ")";
                return replaced;
            }

            return replaced + ").orElse(null)";
        }

        String member = getBase(ctx);
        System.err.println("[replaceNullsafeOperator] member = " + member);

        String curTVar = "t" + index;
        if (ctx.NULLSAFE() != null) {
            return ").map(" + curTVar + " -> " + curTVar + "." + member + replacePNNAWithOptional(ctx.pNNA(), index + 1, ruleCtx);
        } else {
            return "." + member + replacePNNAWithOptional(ctx.pNNA(), index, ruleCtx);
        }
    }

    private String getMethodPart(MethodInvocationContext miCtx) {
        String typeArgument = Utils.getTokenString(miCtx.typeArguments());
        String identifier = Utils.getTokenString(miCtx.identifier());
        String argumentList = Optional.ofNullable(miCtx.argumentList()).map(ParserRuleContext::getText).orElse("");

        String methodPart = typeArgument + identifier + "(" + argumentList + ")";
        System.err.println("[getMethodPart] methodPart = " + methodPart);
        return methodPart;
    }

    private String replaceNullsafeOperator(ParserRuleContext ctx, String lhs, String rhs) {
        String replaced = "java.util.Optional.ofNullable(" + lhs + ").map(t0 -> t0." + rhs + ").orElse(null)";
        return updateContextString(ctx, replaced);
    }

    private String replaceNullsafeOperatorWithOptionalIfPresent(ParserRuleContext ctx, String lhs, String rhs) {
        String replaced = "java.util.Optional.ofNullable(" + lhs + ").ifPresent(t0 -> t0." + rhs + ")";
        return updateContextString(ctx, replaced);
    }

    private String projectUpdatesOn(TextChangeRange range, String contextString) {
        FragmentedText fragmentedText = getCurrentFragmentedText();
        return fragmentedText.projectOn(range, contextString);
    }

    private String getOriginalText() {
        FragmentedText fragmentedText = getCurrentFragmentedText();
        return fragmentedText.getOriginalText();
    }

    private void updateFragmentedText(TextChangeRange range, String replaced) {
        FragmentedText fragmentedText = getCurrentFragmentedText();
        fragmentedText.update(range, replaced);
    }

    private Object getDebugString() {
        FragmentedText fragmentedText = getCurrentFragmentedText();
        return fragmentedText.debugString();
    }

    private String processDefaultText() {
        ensureChildTextInitialized();

        if (ctx instanceof JPlus25Parser.Start_Context) {
            FragmentedText fragmentedText = getCurrentFragmentedText();
            //System.err.println("debugString = " + fragmentedText.debugString());
            this.updatedContextString = fragmentedText.toString();
            return this.updatedContextString;
        } else {
            return forceUpdateContextString(ctx);
        }
    }
}
