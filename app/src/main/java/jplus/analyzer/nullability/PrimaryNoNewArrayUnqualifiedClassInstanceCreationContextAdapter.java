package jplus.analyzer.nullability;

import jplus.base.JPlus25Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public interface PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter {
    ParserRuleContext originalContext();
    JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression();
    TerminalNode NULLSAFE();
    JPlus25Parser.PNNAContext pNNA();
    Token getStart();

    static PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter from(JPlus25Parser.PrimaryNoNewArrayClassInstanceCreationContext ctx) {
        return new PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression() {
                return ctx.unqualifiedClassInstanceCreationExpression();
            }

            @Override
            public JPlus25Parser.PNNAContext pNNA() {
                return ctx.pNNA();
            }

            @Override
            public TerminalNode NULLSAFE() {
                return null;
            }

            @Override
            public Token getStart() {
                return ctx.start;
            }
        };
    }

    static PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter from(JPlus25Parser.PrimaryNoNewArrayExprQualifiedClassInstanceCreationContext ctx) {
        return new PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression() {
                return ctx.unqualifiedClassInstanceCreationExpression();
            }

            @Override
            public JPlus25Parser.PNNAContext pNNA() {
                return ctx.pNNA();
            }

            @Override
            public TerminalNode NULLSAFE() {
                return ctx.NULLSAFE();
            }

            @Override
            public Token getStart() {
                return ctx.start;
            }
        };
    }

    static PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter from(JPlus25Parser.PrimaryNoNewArrayArrayQualifiedClassInstanceCreationContext ctx) {
        return new PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression() {
                return ctx.unqualifiedClassInstanceCreationExpression();
            }

            @Override
            public JPlus25Parser.PNNAContext pNNA() {
                return ctx.pNNA();
            }

            @Override
            public TerminalNode NULLSAFE() {
                return null;
            }

            @Override
            public Token getStart() {
                return ctx.start;
            }
        };
    }

    static PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter from(JPlus25Parser.PNNAClassInstanceCreationContext ctx) {
        return new PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression() {
                return ctx.unqualifiedClassInstanceCreationExpression();
            }

            @Override
            public JPlus25Parser.PNNAContext pNNA() {
                return ctx.pNNA();
            }

            @Override
            public TerminalNode NULLSAFE() {
                return null;
            }

            @Override
            public Token getStart() {
                return ctx.start;
            }
        };
    }

    static PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter from(ExpressionNameInvocationContext ctx) {
        return new PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx.originalContext();
            }

            @Override
            public JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression() {
                return ctx.unqualifiedClassInstanceCreationExpression();
            }

            @Override
            public JPlus25Parser.PNNAContext pNNA() {
                return ctx.pNNA();
            }

            @Override
            public TerminalNode NULLSAFE() {
                return null;
            }

            @Override
            public Token getStart() {
                return ctx.getStart();
            }
        };
    }
}
