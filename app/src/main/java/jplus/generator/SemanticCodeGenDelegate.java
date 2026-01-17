package jplus.generator;

import jplus.base.JPlus25Parser;
import jplus.base.JPlus25Parser.ApplyDeclarationContext;
import jplus.base.JPlus25Parser.ExpressionNameContext;
import jplus.base.JPlus25Parser.FieldAccessContext;
import jplus.base.JPlus25Parser.MethodInvocationContext;
import jplus.base.JPlus25Parser.NullCoalescingExpressionContext;
import jplus.base.JPlus25Parser.PrimaryNoNewArrayContext;
import jplus.base.JPlus25Parser.UnannTypeContext;
import jplus.editor.FragmentedText;
import jplus.util.ParserUtils;
import jplus.util.Utils;

import java.util.Optional;

public class SemanticCodeGenDelegate extends BasicCodeGenDelegate {
    public SemanticCodeGenDelegate(JPlusParserRuleContext ctx) {
        super(ctx);
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

        //String replaced = Utils.getTokenString(methodInvocationCtx).replace("?.", ".");
        String replaced = forceUpdateContextString(methodInvocationCtx).replace("?.", ".");
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

    @Override
    protected String replaceElvisOperator(NullCoalescingExpressionContext ctx) {
        System.err.println("[replaceElvisOperator] contextString = " + Utils.getTokenString(ctx));
        ensureChildTextInitialized();

        String conditionalOrExpressionString = Utils.getTokenString(ctx.conditionalOrExpression());
        String rhsExpressionString = ctx.nullCoalescingExpression() != null ? Utils.getTokenString(ctx.nullCoalescingExpression()) : Utils.getTokenString(ctx.lambdaExpression());

        String replaced = "__elvis(" + conditionalOrExpressionString + ", " + rhsExpressionString + ")";

        System.err.println("[replaceElvisOperator] replaced = " + replaced);
        return updateContextString(ctx, replaced);
    }

    private String processPrimaryNoNewArray(PrimaryNoNewArrayContext ctx) {
        ensureChildTextInitialized();

        String replaced = Utils.getTokenString(ctx).replace("?.", ".");
        System.err.println("[processPrimaryNoNewArray] replaced = " + replaced);
        return updateContextString(ctx, replaced);
    }

    @Override
    protected String processDefaultText() {
        ensureChildTextInitialized();

        if (ctx instanceof JPlus25Parser.Start_Context) {
            FragmentedText fragmentedText = getCurrentFragmentedText();
//            System.err.println("debugString = " + fragmentedText.debugString());


            var contextString = fragmentedText.toString();
            var range = Utils.computeTextChangeRange(contextString,0, contextString.length() - 1);

            FragmentedText appendedfragmentedText = new FragmentedText(range, contextString);
            appendedfragmentedText.appendTextFromEndParenthesis("static <T> T __elvis(T... args) { return null; }}");
            this.updatedContextString = appendedfragmentedText.toString();
            return this.updatedContextString;
        } else {
            return forceUpdateContextString(ctx);
        }
    }
}
