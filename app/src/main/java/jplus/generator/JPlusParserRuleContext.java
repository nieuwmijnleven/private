/*
 * Copyright 2025 Cheol Jeon <nieuwmijnleven@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jplus.generator;

import jplus.base.JPlus20Parser;
import jplus.base.JPlus20Parser.ApplyDeclarationContext;
import jplus.base.JPlus20Parser.ExpressionNameContext;
import jplus.base.JPlus20Parser.FieldAccessContext;
import jplus.base.JPlus20Parser.MethodInvocationContext;
import jplus.base.JPlus20Parser.NullCoalescingExpressionContext;
import jplus.base.JPlus20Parser.PNNAContext;
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
        } else if (this instanceof JPlus20Parser.PrimaryContext primaryCtx) {
            if (primaryCtx.primaryNoNewArray() != null) {
                //return primaryCtx.primaryNoNewArray().getText();
                return primaryCtx.primaryNoNewArray().getText();
            }
            return processDefaultText();
        } /*else if (this instanceof PNNAContext pnnaCtx) {
            if (pnnaCtx.parent instanceof PrimaryNoNewArrayContext ctx && ctx.NULLSAFE() != null) {

            } else if (pnnaCtx.parent instanceof PNNAContext ctx && ctx.NULLSAFE() != null) {

            }

            return replaceNullsafeOperator(pnnaCtx);
        } */else if (this instanceof PrimaryNoNewArrayContext primaryCtx) {
            if (primaryCtx.NULLSAFE() != null) {
                return replaceNullsafeOperator(primaryCtx);
            }

            return processDefaultText();
        } else if (this instanceof ExpressionNameContext expressionNameCtx) {
            return processExpressionName(expressionNameCtx);
        } else if (this instanceof FieldAccessContext fieldAccessCtx) {
            if (fieldAccessCtx.primary() != null && fieldAccessCtx.NULLSAFE() != null) {
//                String primaryPart = fieldAccessCtx.primary().getText();
//                String identifierPart = Utils.getTokenString(fieldAccessCtx.identifier());
//                return replaceNullsafeOperator(fieldAccessCtx, primaryPart, identifierPart);
                return replaceNullsafeOperator(fieldAccessCtx);
            }
            return processDefaultText();
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
        }

        return processDefaultText();
    }

    private String processExpressionName(ExpressionNameContext expressionNameCtx) {
        String lhsPart = processAmbigousName(expressionNameCtx.ambiguousName());
        System.err.println("[processExpressionName] lhsPart = " + lhsPart);
        String rhsPart = Utils.getTokenString(expressionNameCtx.identifier());
        System.err.println("[processExpressionName] rhsPart = " + rhsPart);

        if (expressionNameCtx.NULLSAFE() != null) {
            return replaceNullsafeOperator(expressionNameCtx, lhsPart, rhsPart);
        }

        String replaced = rhsPart;
        if (!lhsPart.isEmpty()) replaced = lhsPart + "." + rhsPart;

        return updateContextString(expressionNameCtx, replaced);
    }

    private String updateContextString(ParserRuleContext ctx, String replaced) {
        TextChangeRange range = Utils.getTextChangeRange(getOriginalText(), ctx);
        //_getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
        System.err.println("[updateContextString] before debugString = " + getDebugString());
        updateFragmentedText(range, replaced);
        System.err.println("[updateContextString] after debugString = " + getDebugString());
        this.updatedContextString = replaced;
        return replaced;
    }

    private String processAmbigousName(JPlus20Parser.AmbiguousNameContext ambiguousNameContext) {
        if (ambiguousNameContext == null) {
            return "";
        }

        String lhsPart = Utils.getTokenString(ambiguousNameContext.identifier());
        System.err.println("[processAmbigousName] lhsPart = " + lhsPart);
        String rhsPart = processAmbigousName(ambiguousNameContext.ambiguousName());
        System.err.println("[processAmbigousName] rhsPart = " + rhsPart);
        if (ambiguousNameContext.NULLSAFE() != null) {
            return replaceNullsafeOperator(ambiguousNameContext, lhsPart, rhsPart);
        }

        if (rhsPart.isEmpty()) return lhsPart;
        return lhsPart + "." + rhsPart;
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
        System.err.println("[replaceNullsafeOperator] ParserRuleContext = " + this.getClass().getSimpleName());
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

    private String replaceNullsafeOperator(ParserRuleContext ctx, String lhs, String rhs) {
        System.err.println("[replaceNullsafeOperator] lhs = " + lhs);
        System.err.println("[replaceNullsafeOperator] rhs = " + rhs);

        String tokenString = lhs + "." + rhs;
        String variableName = lhs;

        String replaced = "(" +
                "((" + variableName + ")!=null)?" +
                "(" + tokenString + "):" +
                "null" +
                ")";

//        String originalText = this.start.getInputStream().toString();
//        TextChangeRange range = Utils.getTextChangeRange(originalText, ctx);
//        TextChangeRange range = Utils.getTextChangeRange(getOriginalText(), ctx);
//        //_getParent().ifPresent(parent -> parent.addTextChangeRange(range, replaced));
//        System.err.println("before debugString = " + getDebugString());
//        updateFragmentedText(range, replaced);
//        System.err.println("after debugString = " + getDebugString());
//        this.updatedContextString = replaced;
//        return replaced;

        return updateContextString(ctx, replaced);
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