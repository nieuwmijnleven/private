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
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class JPlusParserRuleContext extends ParserRuleContext {

    private static final Map<ParserRuleContext, TextChangeRange> parserRuleContextTextChangeRangeMap = new HashMap<>();

    private static final Map<ParserRuleContext, String> parserRuleContextStringeMap = new HashMap<>();

    private static final Set<ParseTree> parserRuleContextProcessed = new HashSet<>();

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
            return null;
        }
        return null;
    }

    private String replaceElvisOperator(NullCoalescingExpressionContext ctx) {
//        String conditionalOrExpression = ctx.conditionalOrExpression().getText();
//        for (int i = 0; i < ctx.getChildCount(); i++) {
//            ctx.getChild(i).getText();
//        }

        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree child = ctx.getChild(i);
            if (!parserRuleContextProcessed.contains(child)) {
                child.getText();
                parserRuleContextProcessed.add(child);
            }
        }

        CodeGenContext codeGenContext = CodeGenContext.current();
        FragmentedText fragmentedText = codeGenContext.getFragmentedText();

        TextChangeRange conditionalOrExpressionRange = parserRuleContextTextChangeRangeMap.computeIfAbsent(ctx.conditionalOrExpression(), parserRuleContext -> Utils.getTextChangeRange(fragmentedText.getOriginalText(), ctx.conditionalOrExpression()));
//        String contextString = parserRuleContextStringeMap.computeIfAbsent(this, parserRuleContext -> Utils.getTokenString(this));

//        TextChangeRange conditionalOrExpressionRange = Utils.getTextChangeRange(fragmentedText.getOriginalText(), ctx.conditionalOrExpression());
        Optional<String> conditionalOrExpressionString = fragmentedText.findFragmentedTextByRange(conditionalOrExpressionRange);

        Optional<String> expression = Optional.empty();
        if (ctx.nullCoalescingExpression() != null) {
//            expression = ctx.nullCoalescingExpression().getText();
//            TextChangeRange expressionRange = Utils.getTextChangeRange(fragmentedText.getOriginalText(), ctx.nullCoalescingExpression());
//            for (int i = 0; i < ctx.nullCoalescingExpression().getChildCount(); i++) {
//                ctx.nullCoalescingExpression().getChild(i).getText();
//            }
            TextChangeRange expressionRange = parserRuleContextTextChangeRangeMap.computeIfAbsent(ctx.nullCoalescingExpression(), parserRuleContext -> Utils.getTextChangeRange(fragmentedText.getOriginalText(), ctx.nullCoalescingExpression()));
            expression = fragmentedText.findFragmentedTextByRange(expressionRange);
        } else if (ctx.lambdaExpression() != null) {
//            for (int i = 0; i < ctx.lambdaExpression().getChildCount(); i++) {
//                ctx.lambdaExpression().getChild(i).getText();
//            }
//            expression = ctx.lambdaExpression().getText();
//            TextChangeRange expressionRange = Utils.getTextChangeRange(fragmentedText.getOriginalText(), ctx.lambdaExpression());
            TextChangeRange expressionRange = parserRuleContextTextChangeRangeMap.computeIfAbsent(ctx.lambdaExpression(), parserRuleContext -> Utils.getTextChangeRange(fragmentedText.getOriginalText(), ctx.lambdaExpression()));
            expression = fragmentedText.findFragmentedTextByRange(expressionRange);
        }

        String replaced = "(" +
                "((" + conditionalOrExpressionString.orElse("null") + ")!=null)?" +
                "(" + conditionalOrExpressionString.orElse("null") + "):" +
                "(" + expression.orElse("null") + ")" +
                ")";

//        String originalText = ctx.start.getInputStream().toString();
//        TextChangeRange range = Utils.getTextChangeRange(fragmentedText.getOriginalText(), ctx);
        TextChangeRange range = parserRuleContextTextChangeRangeMap.computeIfAbsent(ctx, parserRuleContext -> Utils.getTextChangeRange(fragmentedText.getOriginalText(), ctx));
//        _getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
        System.err.println("range = " + range);
        System.err.println("replaced = " + replaced);
        System.err.println("before debugString = " + fragmentedText.debugString());
        updateFragmentedText(range, replaced);
        System.err.println("after debugString = " + fragmentedText.debugString());
        return null;
    }


    /*private String replaceElvisOperator(NullCoalescingExpressionContext ctx) {
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
    }*/

    private String replaceNullsafeOperator(ParserRuleContext ctx) {
        String contextString = parserRuleContextStringeMap.computeIfAbsent(ctx, parserRuleContext -> Utils.getTokenString(ctx));

        String tokenString = contextString.replace("?.", ".");
        String variableName = tokenString.split("\\.")[0];

        String replaced = "(" +
                "((" + variableName + ")!=null)?" +
                "(" + tokenString + "):" +
                "null" +
                ")";

//        String originalText = this.start.getInputStream().toString();
//        TextChangeRange range = Utils.getTextChangeRange(originalText, ctx);
        CodeGenContext codeGenContext = CodeGenContext.current();
        FragmentedText fragmentedText = codeGenContext.getFragmentedText();
        TextChangeRange range = parserRuleContextTextChangeRangeMap.computeIfAbsent(ctx, parserRuleContext -> Utils.getTextChangeRange(fragmentedText.getOriginalText(), ctx));
        //_getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
        updateFragmentedText(range, replaced);

        return null;
    }

    private String processDefaultText() {
        // force children to compute text and update map if needed
        for (int i = 0; i < getChildCount(); i++) {
            ParseTree child = getChild(i);
            if (!parserRuleContextProcessed.contains(child)) {
                child.getText();
                parserRuleContextProcessed.add(child);
            }
        }

//        if (textChangeRangeStringMap.isEmpty()) {
//            return contextString;
//        }

//        String originalText = this.start.getInputStream().toString();
//        FragmentedText fragmentedText = new FragmentedText(range, contextString);
//        CodeGenContext codeGenContext = CodeGenContext.current();
//        FragmentedText fragmentedText = codeGenContext.getFragmentedText();
//        TextChangeRange range = Utils.getTextChangeRange(fragmentedText.getOriginalText(), this);
//        String updatedContextString = fragmentedText.add(range, contextString);

//        textChangeRangeStringMap.forEach(fragmentedText::update);

//        CodeGenContext codeGenContext = CodeGenContext.current();
//        codeGenContext.addSourceMappingEntrySet(fragmentedText.buildSourceMap());

//        _getParent().ifPresent(parent -> parent.addTextChangeRange(range, fragmentedText.toString()));

        CodeGenContext codeGenContext = CodeGenContext.current();
        FragmentedText fragmentedText = codeGenContext.getFragmentedText();
        if (this instanceof JPlus20Parser.Start_Context startCtx) {
            System.err.println("debugString = " + fragmentedText.debugString());
            return fragmentedText.toString();
        } else {
            System.err.println("ParserRuleContext = " + this.getClass().getSimpleName());
            TextChangeRange range = parserRuleContextTextChangeRangeMap.computeIfAbsent(this, parserRuleContext -> Utils.getTextChangeRange(fragmentedText.getOriginalText(), this));
            String contextString = parserRuleContextStringeMap.computeIfAbsent(this, parserRuleContext -> Utils.getTokenString(this));
            String updatedContextString = fragmentedText.add(range, contextString);
            return null;
        }
            /*else if (this instanceof TopLevelClassOrInterfaceDeclarationContext topLevelClassContext) {
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
    }

//    public void addTextChangeRange(TextChangeRange range, String replaced) {
//        this.textChangeRangeStringMap.put(range, replaced);
//    }

    public String getFlattenedText() {
        return super.getText();
    }
}