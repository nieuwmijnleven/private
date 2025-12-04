package jplus.generator;

import jplus.base.JPlus20Parser;
import jplus.base.JPlus20Parser.ApplyDeclarationContext;
import jplus.base.JPlus20Parser.ExpressionNameContext;
import jplus.base.JPlus20Parser.FieldAccessContext;
import jplus.base.JPlus20Parser.MethodInvocationContext;
import jplus.base.JPlus20Parser.NullCoalescingExpressionContext;
import jplus.base.JPlus20Parser.PrimaryNoNewArrayContext;
import jplus.base.JPlus20Parser.UnannTypeContext;
import jplus.util.FragmentedText;
import jplus.util.Utils;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

public class JPlusParserRuleContext extends ParserRuleContext {

    private boolean processed = false;

    private String updatedContextString = "";

    public JPlusParserRuleContext(ParserRuleContext parent, int invokingStateNumber) {
        super(parent, invokingStateNumber);
    }

    public Optional<JPlusParserRuleContext> _getParent() {
        return Optional.ofNullable((JPlusParserRuleContext) this.parent);
    }

    @Override
    public String getText() {
        if (processed) return this.updatedContextString;
        processed = true;

        if (this instanceof ApplyDeclarationContext ApplyDeclarationCtx) {
            return replaceApplyStatementWithComment(ApplyDeclarationCtx);
        } else if (this instanceof UnannTypeContext unannTypeCtx && unannTypeCtx.unannReferenceType() != null) {
            return replaceNullType(unannTypeCtx);
        } else if (this instanceof NullCoalescingExpressionContext nullCoalescingCtx && nullCoalescingCtx.ELVIS() != null) {
            return replaceElvisOperator(nullCoalescingCtx);
        } else if (this instanceof PrimaryNoNewArrayContext primaryCtx && primaryCtx.NULLSAFE() != null) {
            return replaceNullsafeOperator(primaryCtx);
        } else if (this instanceof FieldAccessContext fieldAccessCtx && fieldAccessCtx.NULLSAFE() != null) {
            return replaceNullsafeOperator(fieldAccessCtx);
        } else if (this instanceof MethodInvocationContext methodInvocationCtx) {
            if (methodInvocationCtx.NULLSAFE() != null) {
                return replaceNullsafeOperator(methodInvocationCtx);
            }

            for (int i = 0; i < getChildCount(); i++) {
                getChild(i).getText();
            }

            TextChangeRange range =Utils.getTextChangeRange(getOriginalText(), this);
            String contextString = Utils.getTokenString(this);
            this.updatedContextString = getUpdatedContextString(range, contextString);
            System.err.println("updatedContextString = " + updatedContextString);
            updateFragmentedText(range, updatedContextString);
            return null;
        } else if (this instanceof ExpressionNameContext expressionNameContext && expressionNameContext.NULLSAFE() != null) {
            return replaceNullsafeOperator(expressionNameContext);
        }

        return processDefaultText();
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

            TextChangeRange range = Utils.getTextChangeRange(getOriginalText(), ctx);
            System.err.println("[replaceNullType] originalText " + getOriginalText());
            System.err.println("[replaceNullType] range " + range);
            System.err.println("[replaceNullType] replaced " + replaced);

//            _getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
            System.err.println("[replaceNullType] before debugString = " + getDebugString());
            updateFragmentedText(range, replaced);
            System.err.println("[replaceNullType] after debugString = " + getDebugString());
            this.updatedContextString = replaced;
            return null;
        }
        return null;
    }

    private String replaceElvisOperator(NullCoalescingExpressionContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ctx.getChild(i).getText();
        }

        TextChangeRange conditionalOrExpressionRange = Utils.getTextChangeRange(getOriginalText(), ctx.conditionalOrExpression());
        Optional<String> conditionalOrExpressionString = getFragmentedTextByRange(conditionalOrExpressionRange);
        Optional<String> expression = Optional.empty();
        if (ctx.nullCoalescingExpression() != null) {
            TextChangeRange expressionRange = Utils.getTextChangeRange(getOriginalText(), ctx.nullCoalescingExpression());
            expression = getFragmentedTextByRange(expressionRange);
        } else if (ctx.lambdaExpression() != null) {
            TextChangeRange expressionRange = Utils.getTextChangeRange(getOriginalText(), ctx.lambdaExpression());
            expression = getFragmentedTextByRange(expressionRange);
        }

        String replaced = "(" +
                "((" + conditionalOrExpressionString.orElse("null") + ")!=null)?" +
                "(" + conditionalOrExpressionString.orElse("null") + "):" +
                "(" + expression.orElse("null") + ")" +
                ")";

        TextChangeRange range = Utils.getTextChangeRange(getOriginalText(), ctx);
        System.err.println("range = " + range);
        System.err.println("replaced = " + replaced);
        System.err.println("before debugString = " + getDebugString());
        updateFragmentedText(range, replaced);
        System.err.println("after debugString = " + getDebugString());
        this.updatedContextString = replaced;
        return null;
    }

    private Optional<String> getFragmentedTextByRange(TextChangeRange range) {
        CodeGenContext codeGenContext = CodeGenContext.current();
        FragmentedText fragmentedText = codeGenContext.getFragmentedText();
        return fragmentedText.findFragmentedTextByRange(range);
    }

    private String replaceNullsafeOperator(ParserRuleContext ctx) {
        String contextString = Utils.getTokenString(ctx);
        System.err.println("[replaceNullsafeOperator] contextString = " + contextString);

        String tokenString = contextString.replace("?.", ".");
        String variableName = tokenString.split("\\.")[0];

        String replaced = "(" +
                "((" + variableName + ")!=null)?" +
                "(" + tokenString + "):" +
                "null" +
                ")";

//        String originalText = this.start.getInputStream().toString();
//        TextChangeRange range = Utils.getTextChangeRange(originalText, ctx);
        TextChangeRange range = Utils.getTextChangeRange(getOriginalText(), ctx);
        //_getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
        System.err.println("before debugString = " + getDebugString());
        updateFragmentedText(range, replaced);
        System.err.println("after debugString = " + getDebugString());
        this.updatedContextString = replaced;
        return null;
    }

    private String getUpdatedContextString(TextChangeRange range, String contextString) {
        CodeGenContext codeGenContext = CodeGenContext.current();
        FragmentedText fragmentedText = codeGenContext.getFragmentedText();
        return fragmentedText.add(range, contextString);
    }

    private String getOriginalText() {
        CodeGenContext codeGenContext = CodeGenContext.current();
        FragmentedText fragmentedText = codeGenContext.getFragmentedText();
        return fragmentedText.getOriginalText();
    }

    private void updateFragmentedText(TextChangeRange range, String replaced) {
        CodeGenContext codeGenContext = CodeGenContext.current();
        FragmentedText fragmentedText = codeGenContext.getFragmentedText();
        fragmentedText.update(range, replaced);
    }

    private Object getDebugString() {
        CodeGenContext codeGenContext = CodeGenContext.current();
        FragmentedText fragmentedText = codeGenContext.getFragmentedText();
        return fragmentedText.debugString();
    }

    private String processDefaultText() {
        // force children to compute text and update map if needed
        for (int i = 0; i < getChildCount(); i++) {
            getChild(i).getText();
        }

        CodeGenContext codeGenContext = CodeGenContext.current();
        FragmentedText fragmentedText = codeGenContext.getFragmentedText();
        if (this instanceof JPlus20Parser.Start_Context startCtx) {
            System.err.println("debugString = " + fragmentedText.debugString());
            return (this.updatedContextString = fragmentedText.toString());
        } else {
            System.err.println("ParserRuleContext = " + this.getClass().getSimpleName());
            TextChangeRange range =Utils.getTextChangeRange(getOriginalText(), this);
            String contextString = Utils.getTokenString(this);
            System.err.println("contextString = " + contextString);
            return (this.updatedContextString = getUpdatedContextString(range, contextString));
        }
    }

    public String getFlattenedText() {
        return super.getText();
    }
}