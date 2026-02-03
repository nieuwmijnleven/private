package jplus.analyzer.nullability.context.adapter;

import jplus.base.JPlus25Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public interface PNNAContextAdapter {

    ParserRuleContext originalContext();
    JPlus25Parser.ExpressionContext expression();
    ParserRuleContext identifier();
    JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression();
    JPlus25Parser.ArgumentListContext argumentList();
    JPlus25Parser.PNNAContext pNNA();
    TerminalNode LPAREN();
    TerminalNode NULLSAFE();
    Token getStart();

    static PNNAContextAdapter from(JPlus25Parser.PNNAContext ctx) {
        if (ctx instanceof JPlus25Parser.PNNAClassInstanceCreationContext instanceCreationContext) {
            return from(instanceCreationContext);
        } else if (ctx instanceof JPlus25Parser.PNNAFieldAccessContext fieldAccessContext) {
            return from(fieldAccessContext);
        } else if (ctx instanceof JPlus25Parser.PNNAArrayAccessContext arrayAccessContext) {
            return from(arrayAccessContext);
        } else if (ctx instanceof JPlus25Parser.PNNAMethodInvocationContext methodInvocationContext) {
            return from(methodInvocationContext);
        } else if (ctx instanceof JPlus25Parser.PNNAMethodReferenceContext methodReferenceContext) {
            return from(methodReferenceContext);
        }

        return null;
    }

    static PNNAContextAdapter from(JPlus25Parser.PNNAClassInstanceCreationContext ctx) {
        return new PNNAContextAdapter() {
            public ParserRuleContext originalContext() { return ctx; }
            public JPlus25Parser.ExpressionContext expression() { return null; }
            public ParserRuleContext identifier() { return null; }
            public JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression() { return ctx.unqualifiedClassInstanceCreationExpression(); }
            public JPlus25Parser.PNNAContext pNNA() { return ctx.pNNA(); }

            @Override
            public JPlus25Parser.ArgumentListContext argumentList() {
                return null;
            }

            public TerminalNode LPAREN() { return null; }
            public TerminalNode NULLSAFE() { return ctx.NULLSAFE(); }
            public Token getStart() { return ctx.start; }
        };
    }

    static PNNAContextAdapter from(JPlus25Parser.PNNAFieldAccessContext ctx) {
        return new PNNAContextAdapter() {
            public ParserRuleContext originalContext() { return ctx; }
            public JPlus25Parser.ExpressionContext expression() { return null; }
            public ParserRuleContext identifier() { return ctx.identifier(); }
            public JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression() { return null; }
            public JPlus25Parser.ArgumentListContext argumentList() {
                return null;
            }
            public JPlus25Parser.PNNAContext pNNA() { return ctx.pNNA(); }
            public TerminalNode LPAREN() { return null; }
            public TerminalNode NULLSAFE() { return ctx.NULLSAFE(); }
            public Token getStart() { return ctx.start; }
        };
    }

    static PNNAContextAdapter from(JPlus25Parser.PNNAArrayAccessContext ctx) {
        return new PNNAContextAdapter() {
            public ParserRuleContext originalContext() { return ctx; }
            public JPlus25Parser.ExpressionContext expression() { return ctx.expression(); }
            public ParserRuleContext identifier() { return null; }
            public JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression() { return null; }
            @Override
            public JPlus25Parser.ArgumentListContext argumentList() {
                return null;
            }
            public JPlus25Parser.PNNAContext pNNA() { return ctx.pNNA(); }
            public TerminalNode LPAREN() { return null; }
            public TerminalNode NULLSAFE() { return null; }
            public Token getStart() { return ctx.start; }
        };
    }

    static PNNAContextAdapter from(JPlus25Parser.PNNAMethodInvocationContext ctx) {
        return new PNNAContextAdapter() {
            public ParserRuleContext originalContext() { return ctx; }
            public JPlus25Parser.ExpressionContext expression() { return null; }
            public ParserRuleContext identifier() { return ctx.identifier(); }
            public JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression() { return null; }
            @Override
            public JPlus25Parser.ArgumentListContext argumentList() {
                return ctx.argumentList();
            }
            public JPlus25Parser.PNNAContext pNNA() { return ctx.pNNA(); }
            public TerminalNode LPAREN() { return null; }
            public TerminalNode NULLSAFE() { return ctx.NULLSAFE(); }
            public Token getStart() { return ctx.start; }
        };
    }

    static PNNAContextAdapter from(JPlus25Parser.PNNAMethodReferenceContext ctx) {
        return new PNNAContextAdapter() {
            public ParserRuleContext originalContext() { return ctx; }
            public JPlus25Parser.ExpressionContext expression() { return null; }
            public ParserRuleContext identifier() { return ctx.identifier(); }
            public JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression() { return null; }
            public JPlus25Parser.ArgumentListContext argumentList() {
                return null;
            }
            public JPlus25Parser.PNNAContext pNNA() { return ctx.pNNA(); }
            public TerminalNode LPAREN() { return null; }
            public TerminalNode NULLSAFE() { return null; }
            public Token getStart() { return ctx.start; }
        };
    }
}
