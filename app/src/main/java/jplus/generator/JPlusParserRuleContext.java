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
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayDeque;
import java.util.Deque;
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
        } else if (this instanceof PrimaryNoNewArrayContext primaryNoNewArrayCtx) {
            for (int i = 0; i < getChildCount(); i++) {
                getChild(i).getText();
            }
            return processPrimaryNoNewArray(primaryNoNewArrayCtx);
        } else if (this instanceof ExpressionNameContext expressionNameCtx) {
            String replaced = Utils.getTokenString(expressionNameCtx);
            if (replaced.indexOf("?.") != -1) {
                replaced = processExpressionName(expressionNameCtx);
            }
            System.err.println("[ExpressionNameContext] replaced = " + replaced);
            return updateContextString(expressionNameCtx, replaced);
        } else if (this instanceof FieldAccessContext fieldAccessCtx) {
            if (fieldAccessCtx.primary() != null) {
                String primaryPart = fieldAccessCtx.primary().getText();
                String replaced = primaryPart;
                if (!usesNullSafety(fieldAccessCtx.primary())) {
                    String identifierPart = Utils.getTokenString(fieldAccessCtx.identifier());
                    if (fieldAccessCtx.NULLSAFE() != null) {
                        replaced = replaceNullsafeOperator(fieldAccessCtx, primaryPart, identifierPart);
                    } else {
                        replaced = primaryPart + "." + identifierPart;
                    }
                }
                return updateContextString(fieldAccessCtx, replaced);
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

    private String processPrimaryNoNewArray(PrimaryNoNewArrayContext ctx) {
        String replaced = Utils.getTokenString(ctx);
        if (replaced.indexOf("?.") != -1) {
            replaced = replaceBaseWithOptional(ctx, (FieldAccessContext) Optional.ofNullable(ctx.getParent()).map(context -> ctx.getParent()).filter(context -> context.getClass().getSimpleName().equals("FieldAccessContext")).orElse(null));
        }
        System.err.println("[processPrimaryNoNewArray] replaced = " + replaced);
        return updateContextString(ctx, replaced);
    }

    private String getBase(PrimaryNoNewArrayContext ctx) {
        for (int i = 0; i < getChildCount(); i++) {
            getChild(i).getText();
        }
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
        for (int i = 0; i < getChildCount(); i++) {
            getChild(i).getText();
        }
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

    private String processExpressionName(ExpressionNameContext expressionNameCtx) {
        Deque<ExpressionNameContext> expressionNameContextDeque = new ArrayDeque<>();
        ExpressionNameContext current = expressionNameCtx;
        expressionNameContextDeque.addFirst(current);
        while (current.expressionName() != null) {
            current = current.expressionName();
            expressionNameContextDeque.addFirst(current);
        }

        String identifier = Utils.getTokenString(expressionNameContextDeque.removeFirst().identifier());
        String replaced = "java.util.Optional.ofNullable(" + identifier;
        int i = 0;
        while (!expressionNameContextDeque.isEmpty()) {
            String curTVar = "t" + i;
            var expressionNameContext = expressionNameContextDeque.removeFirst();
            String member = Utils.getTokenString(expressionNameContext.identifier());
            if (expressionNameContext.NULLSAFE() != null) {
                replaced += ").map(" + curTVar + " -> " + curTVar + "." + member;
                ++i;
            } else {
                replaced += "." + member;
            }
        }

        replaced += ").orElse(null)";
        return replaced;
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

    private boolean usesNullSafety(ParserRuleContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree child = ctx.getChild(i);

            if (child instanceof TerminalNode tn) {
                if (tn.getSymbol().getType() == JPlus20Parser.NULLSAFE) {
                    return true;
                }
            } else if (child instanceof ParserRuleContext prc) {
                if (usesNullSafety(prc)) {
                    return true;
                }
            }
        }
        return false;
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



        String replaced;
        if (usesNullSafety(ctx)) {
            replaced = conditionalOrExpressionString.orElse("null");
            replaced = replaced.replace("orElse(null)", "orElseGet(() -> " + expression.orElse("null") + ")");
        } else {
            replaced = "java.util.Optional.ofNullable(";
            replaced += conditionalOrExpressionString.orElse("null") + ")";
            replaced += ".orElseGet(() -> " + expression.orElse("null") + ")";
        }

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

    private String replaceNullsafeOperator(String lhsPart, String rhsPart) {
        System.err.println("[replaceNullsafeOperator] lhsPart = " + lhsPart);
        System.err.println("[replaceNullsafeOperator] rhsPart = " + rhsPart);

        String tokenString = lhsPart + "." + rhsPart;
        String variableName = lhsPart;

        return "(" +
                "((" + variableName + ")!=null)?" +
                "(" + tokenString + "):" +
                "null" +
                ")";
    }

    private String replaceBaseWithNullsafeMethodInvocation(PrimaryNoNewArrayContext ctx) {
        String base = getBase(ctx);
        System.err.println("[replaceNullsafeOperator] base = " + base);

        if (ctx.NULLSAFE() != null) {
            //String tokenString = basePart.replace("?.", ".");
            String[] tokens = base.split("\\?\\.");
            String instance = tokens[0];
            String member = tokens[1];

            return "__nullsafe(" + instance + ", t0 -> " +
                    "__nullsafe(t1." + member +  replacePNNAWithNullsafeMethodInvocation(ctx.pNNA(), 1) + "))";
        }

        //return "__nullsafe(" + base +", t1 -> " + //replacePNNAWithNullsafeMethodInvocation(ctx.pNNA(), 1) + ")";
        return "__nullsafe(" + base + replacePNNAWithNullsafeMethodInvocation(ctx.pNNA(), 0) + ")";
    }

    private String replacePNNAWithNullsafeMethodInvocation(PNNAContext ctx, int index) {
        String curTVar = "t" + index;
        if (ctx == null) {
            return ", " + curTVar + " -> " + curTVar;
        }

        String member = getBase(ctx);
        System.err.println("[replaceNullsafeOperator] member = " + member);

//        String curTVar = "t" + (index + 1);
        if (ctx.NULLSAFE() != null) {
            return ", " + curTVar + " -> __nullsafe(" + curTVar + "." + member +
                        replacePNNAWithNullsafeMethodInvocation(ctx.pNNA(), index + 1) + ")";
        } else {
            return "." + member + replacePNNAWithNullsafeMethodInvocation(ctx.pNNA(), index);
        }
    }

    private String replaceBaseWithOptional(PrimaryNoNewArrayContext ctx, FieldAccessContext faCtx) {
        String base = getBase(ctx);
        System.err.println("[replaceBaseWithOptional] base = " + base);

        if (ctx.NULLSAFE() != null) {
            //String tokenString = basePart.replace("?.", ".");
            String[] tokens = base.split("\\?\\.");
            String instance = tokens[0];
            String member = tokens[1];

            return "java.util.Optional.ofNullable(" + instance + ").map(t0 -> t0." + member +  replacePNNAWithOptional(ctx.pNNA(), 1, faCtx);
        }

        //return "__nullsafe(" + base +", t1 -> " + //replacePNNAWithNullsafeMethodInvocation(ctx.pNNA(), 1) + ")";
//        if (ctx.pNNA() != null && ctx.pNNA().NULLSAFE() != null) {
//            return "java.util.Optional.ofNullable(" + base +  replacePNNAWithOptioanl(ctx.pNNA(), 0);
//        }
        return "java.util.Optional.ofNullable(" + base + replacePNNAWithOptional(ctx.pNNA(), 0, faCtx);
    }

    private String replacePNNAWithOptional(PNNAContext ctx, int index, FieldAccessContext faCtx) {
        if (ctx == null) {
            String replaced = "";

            if (faCtx != null) {
                String member = Utils.getTokenString(faCtx.identifier());
                if (faCtx.NULLSAFE() != null) {
                    String curTVar = "t" + index;
                    replaced = ").map(" + curTVar + " -> " + curTVar + "." + member;
                } else {
                    replaced = "." + member;
                }
            }

            return replaced + ").orElse(null)";
        }

        String member = getBase(ctx);
        System.err.println("[replaceNullsafeOperator] member = " + member);

        String curTVar = "t" + index;
        if (ctx.NULLSAFE() != null) {
            return ").map(" + curTVar + " -> " + curTVar + "." + member + replacePNNAWithOptional(ctx.pNNA(), index + 1, faCtx);
        } else {
            return "." + member + replacePNNAWithOptional(ctx.pNNA(), index, faCtx);
        }
    }


    private String replaceNullsafeOperator(String basePart) {
        System.err.println("[replaceNullsafeOperator] basePart = " + basePart);

        String tokenString = basePart.replace("?.", ".");
        String variableName = tokenString.split("\\.")[0];

        return "(" +
                "((" + variableName + ")!=null)?" +
                "(" + tokenString + "):" +
                "null" +
                ")";
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

    /*private String replaceNullsafeOperator(ParserRuleContext ctx, String lhs, String rhs) {
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
    }*/

    private String replaceNullsafeOperator(ParserRuleContext ctx, String lhs, String rhs) {
        System.err.println("[replaceNullsafeOperator] lhs = " + lhs);
        System.err.println("[replaceNullsafeOperator] rhs = " + rhs);

        String replaced = "java.util.Optional.ofNullable(" + lhs + ").map(t0 -> t0." + rhs + ").orElse(null)";
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