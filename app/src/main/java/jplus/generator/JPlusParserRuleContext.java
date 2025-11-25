package jplus.generator;

import jplus.base.JPlus20Parser;
import jplus.base.JPlus20Parser.ApplyDeclarationContext;
import jplus.base.JPlus20Parser.TopLevelClassOrInterfaceDeclarationContext;
import jplus.base.JPlus20Parser.ExpressionNameContext;
import jplus.base.JPlus20Parser.FieldAccessContext;
import jplus.base.JPlus20Parser.MethodInvocationContext;
import jplus.base.JPlus20Parser.NullCoalescingExpressionContext;
import jplus.base.JPlus20Parser.PrimaryNoNewArrayContext;
import jplus.base.JPlus20Parser.UnannTypeContext;
import jplus.util.FragmentedText;
import jplus.util.Utils;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JPlusParserRuleContext extends ParserRuleContext {

    private final Map<TextChangeRange, String> textChangeRangeStringMap = new HashMap<>();

    public JPlusParserRuleContext(ParserRuleContext parent, int invokingStateNumber) {
        super(parent, invokingStateNumber);
    }

    public Optional<JPlusParserRuleContext> _getParent() {
        return Optional.ofNullable((JPlusParserRuleContext) this.parent);
    }

    @Override
    public String getText() {
        if (this instanceof ApplyDeclarationContext ApplyDeclarationCtx) {
            String originalText = ApplyDeclarationCtx.start.getInputStream().toString();
            TextChangeRange range = Utils.getTextChangeRange(originalText, ApplyDeclarationCtx);
            String replaced = Utils.getTokenString(ApplyDeclarationCtx).replaceFirst("^", "//").replaceAll("\n", "\n//");
//            _getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
            updateFragmentedText(range, replaced);
            return null;
        } else if (this instanceof UnannTypeContext unannTypeCtx && unannTypeCtx.unannReferenceType() != null) {
            return replaceNullType(unannTypeCtx);
        } else if (this instanceof NullCoalescingExpressionContext nullCoalescingCtx && nullCoalescingCtx.ELVIS() != null) {
            return replaceElvisOperator(nullCoalescingCtx);
        } else if (this instanceof PrimaryNoNewArrayContext primaryCtx && primaryCtx.NULLSAFE() != null) {
            return replaceNullsafeOperator(primaryCtx);
        } else if (this instanceof FieldAccessContext fieldAccessCtx && fieldAccessCtx.NULLSAFE() != null) {
            return replaceNullsafeOperator(fieldAccessCtx);
        } else if (this instanceof MethodInvocationContext methodInvocationCtx && methodInvocationCtx.NULLSAFE() != null) {
            return replaceNullsafeOperator(methodInvocationCtx);
        } else if (this instanceof ExpressionNameContext expressionNameContext && expressionNameContext.NULLSAFE() != null) {
            return replaceNullsafeOperator(expressionNameContext);
        }

        /*else if (this instanceof FieldDeclarationContext fieldDeclarationContext) {
            String fieldDeclarationText = Utils.getTokenString(fieldDeclarationContext);

            boolean hasNullableAnnotation = false;
            if (fieldDeclarationContext.fieldModifier() != null) {
                for (var fieldModifierContext : fieldDeclarationContext.fieldModifier()) {
                    if (fieldModifierContext.annotation() != null) {
                        String annotation = Utils.getTokenString(fieldModifierContext.annotation());
                        if ("@Nullable".equals(annotation)) {
                            hasNullableAnnotation = true;
                        }
                    }
                }
            }

            if (!hasNullableAnnotation && fieldDeclarationContext.unannType().QUESTION() != null) {
                String typeName = Utils.getTokenString(fieldDeclarationContext.unannType().unannReferenceType());
                String originalText = this.start.getInputStream().toString();
                TextChangeRange range = Utils.getTextChangeRange(originalText, fieldDeclarationContext);
                String replaced = "@Nullable " + fieldDeclarationText;
                _getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
                return replaced;
            }

            return fieldDeclarationText;
        }*/

        return processDefaultText();
    }

    private void updateFragmentedText(TextChangeRange range, String replaced) {
        CodeGenContext codeGenContext = CodeGenContext.current();
        FragmentedText fragmentedText = codeGenContext.getFragmentedText();
        fragmentedText.update(range, replaced);
    }

    /*private String replaceNullType(UnannTypeContext ctx) {
        String original = Utils.getTokenString(ctx);
        if (ctx.QUESTION() != null) {
            String replaced = original.substring(0, original.length()-1);
            String originalText = ctx.start.getInputStream().toString();
            TextChangeRange range = Utils.getTextChangeRange(originalText, ctx);
            _getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
            return replaced;
        }
        return original;
    }*/

    private String replaceNullType(UnannTypeContext ctx) {
        String unannTypeText = Utils.getTokenString(ctx);
        if (ctx.QUESTION() != null) {
            String replaced = "@org.jspecify.annotations.Nullable " + unannTypeText.substring(0, unannTypeText.length()-1);
            String originalText = ctx.start.getInputStream().toString();
            TextChangeRange range = Utils.getTextChangeRange(originalText, ctx);
//            _getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
            updateFragmentedText(range, replaced);
            return replaced;
        }
        return unannTypeText;
    }

    private String replaceElvisOperator(NullCoalescingExpressionContext ctx) {
        String conditionalOrExpression = ctx.conditionalOrExpression().getText();
        String expression = "null";

        if (ctx.nullCoalescingExpression() != null) {
            expression = ctx.nullCoalescingExpression().getText();
        } else if (ctx.lambdaExpression() != null) {
            expression = ctx.lambdaExpression().getText();
        }

        String replaced = "(" +
                "((" + conditionalOrExpression + ")!=null)?" +
                "(" + conditionalOrExpression + "):" +
                "(" + expression + ")" +
                ")";

        String originalText = ctx.start.getInputStream().toString();
        TextChangeRange range = Utils.getTextChangeRange(originalText, ctx);
//        _getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
        updateFragmentedText(range, replaced);
        return replaced;
    }

    private String replaceNullsafeOperator(ParserRuleContext ctx) {
        String tokenString = Utils.getTokenString(ctx).replace("?.", ".");
        String variableName = tokenString.split("\\.")[0];

        String replaced = "(" +
                "((" + variableName + ")!=null)?" +
                "(" + tokenString + "):" +
                "null" +
                ")";

        String originalText = this.start.getInputStream().toString();
        TextChangeRange range = Utils.getTextChangeRange(originalText, ctx);
        //_getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
        updateFragmentedText(range, replaced);

        return replaced;
    }

    private String processDefaultText() {
        String contextString = Utils.getTokenString(this);

        // force children to compute text and update map if needed
        for (int i = 0; i < getChildCount(); i++) {
            getChild(i).getText();
        }

//        if (textChangeRangeStringMap.isEmpty()) {
//            return contextString;
//        }

//        String originalText = this.start.getInputStream().toString();
//        FragmentedText fragmentedText = new FragmentedText(range, contextString);
        CodeGenContext codeGenContext = CodeGenContext.current();
        FragmentedText fragmentedText = codeGenContext.getFragmentedText();
        TextChangeRange range = Utils.getTextChangeRange(fragmentedText.getOriginalText(), this);
        fragmentedText.add(range, contextString);

//        textChangeRangeStringMap.forEach(fragmentedText::update);

//        CodeGenContext codeGenContext = CodeGenContext.current();
//        codeGenContext.addSourceMappingEntrySet(fragmentedText.buildSourceMap());

//        _getParent().ifPresent(parent -> parent.addTextChangeRange(range, fragmentedText.toString()));

        if (this instanceof JPlus20Parser.Start_Context startCtx) {
//            CodeGenContext codeGenContext = CodeGenContext.current();
//            FragmentedText fragmentedText = codeGenContext.getFragmentedText();
            return fragmentedText.toString();
        } /*else if (this instanceof TopLevelClassOrInterfaceDeclarationContext topLevelClassContext) {
            int startIndex = topLevelClassContext.start.getStartIndex();
            int stopIndex = topLevelClassContext.stop.getStopIndex();
            CodeGenContext codeGenContext = CodeGenContext.current();
            FragmentedText fragmentedText = codeGenContext.getFragmentedText();
            TextChangeRange range = Utils.computeTextChangeRange(fragmentedText.getOriginalText(), startIndex, stopIndex);
            String topLevelClassText = fragmentedText.findFragmentedText(range).orElseThrow();
            System.err.println("[topLevelClassContext] topLevelClassText = " + topLevelClassText);
            String replaced = "import org.jspecify.annotations.Nullable;\n\n" + topLevelClassText;
            String originalText = topLevelClassContext.start.getInputStream().toString();
            range = Utils.getTextChangeRange(originalText, topLevelClassContext);
//            _getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
//            return replaced;
            updateFragmentedText(range, replaced);
        }*/

        return null;
    }

//    public void addTextChangeRange(TextChangeRange range, String replaced) {
//        this.textChangeRangeStringMap.put(range, replaced);
//    }

    public String getFlattenedText() {
        return super.getText();
    }
}