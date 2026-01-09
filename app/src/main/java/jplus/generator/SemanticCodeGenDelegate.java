package jplus.generator;

import jplus.base.JPlus25Parser.ApplyDeclarationContext;
import jplus.base.JPlus25Parser.ExpressionNameContext;
import jplus.base.JPlus25Parser.FieldAccessContext;
import jplus.base.JPlus25Parser.MethodInvocationContext;
import jplus.base.JPlus25Parser.NullCoalescingExpressionContext;
import jplus.base.JPlus25Parser.PrimaryNoNewArrayContext;
import jplus.base.JPlus25Parser.UnannTypeContext;
import jplus.util.Utils;

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

    private String processPrimaryNoNewArray(PrimaryNoNewArrayContext ctx) {
        ensureChildTextInitialized();

        String replaced = Utils.getTokenString(ctx).replace("?.", ".");
        System.err.println("[processPrimaryNoNewArray] replaced = " + replaced);
        return updateContextString(ctx, replaced);
    }
}
