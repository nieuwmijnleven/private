package jplus.analyzer.nullability;

import jplus.base.JPlus25Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public interface MethodInvocationSignatureContextAdapter {
    ParserRuleContext originalContext();
    JPlus25Parser.IdentifierContext identifier();
    JPlus25Parser.MethodNameContext methodName();
    JPlus25Parser.ArgumentListContext argumentList();
    JPlus25Parser.PNNAContext pNNA();
    TerminalNode NULLSAFE();
    Token getStart();

    static MethodInvocationSignatureContextAdapter from(JPlus25Parser.PrimaryNoNewArrayTypeMethodInvocationContext ctx) {
        return new MethodInvocationSignatureContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.IdentifierContext identifier() {
                return ctx.identifier();
            }

            @Override
            public JPlus25Parser.MethodNameContext methodName() {
                return null;
            }

            @Override
            public JPlus25Parser.ArgumentListContext argumentList() {
                return ctx.argumentList();
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

    static MethodInvocationSignatureContextAdapter from(JPlus25Parser.PrimaryNoNewArrayArrayMethodInvocationContext ctx) {
        return new MethodInvocationSignatureContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.IdentifierContext identifier() {
                return ctx.identifier();
            }

            @Override
            public JPlus25Parser.MethodNameContext methodName() {
                return null;
            }

            @Override
            public JPlus25Parser.ArgumentListContext argumentList() {
                return ctx.argumentList();
            }

            @Override
            public TerminalNode NULLSAFE() {
                return null;
            }

            @Override
            public JPlus25Parser.PNNAContext pNNA() {
                return ctx.pNNA();
            }

            @Override
            public Token getStart() {
                return ctx.start;
            }
        };
    }

    static MethodInvocationSignatureContextAdapter from(JPlus25Parser.PrimaryNoNewArrayQualifiedSuperMethodInvocationContext ctx) {
        return new MethodInvocationSignatureContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.IdentifierContext identifier() {
                return ctx.identifier();
            }

            @Override
            public JPlus25Parser.MethodNameContext methodName() {
                return null;
            }

            @Override
            public JPlus25Parser.ArgumentListContext argumentList() {
                return ctx.argumentList();
            }

            @Override
            public TerminalNode NULLSAFE() {
                return null;
            }

            @Override
            public JPlus25Parser.PNNAContext pNNA() {
                return ctx.pNNA();
            }

            @Override
            public Token getStart() {
                return ctx.start;
            }
        };
    }

    static MethodInvocationSignatureContextAdapter from(JPlus25Parser.MethodInvocationContext ctx) {
        return new MethodInvocationSignatureContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.IdentifierContext identifier() {
                return ctx.identifier();
            }

            @Override
            public JPlus25Parser.MethodNameContext methodName() {
                return ctx.methodName();
            }

            @Override
            public JPlus25Parser.ArgumentListContext argumentList() {
                return ctx.argumentList();
            }

            @Override
            public JPlus25Parser.PNNAContext pNNA() {
                return null;
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

    static MethodInvocationSignatureContextAdapter from(JPlus25Parser.PrimaryNoNewArrayMethodInvocationContext ctx) {
        return new MethodInvocationSignatureContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.IdentifierContext identifier() {
                return null;
            }

            @Override
            public JPlus25Parser.MethodNameContext methodName() {
                return ctx.methodName();
            }

            @Override
            public JPlus25Parser.ArgumentListContext argumentList() {
                return ctx.argumentList();
            }

            @Override
            public JPlus25Parser.PNNAContext pNNA() {
                return null;
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

    static MethodInvocationSignatureContextAdapter from(JPlus25Parser.PrimaryNoNewArraySuperMethodInvocationContext ctx) {
        return new MethodInvocationSignatureContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.IdentifierContext identifier() {
                return ctx.identifier();
            }

            @Override
            public JPlus25Parser.MethodNameContext methodName() {
                return null;
            }

            @Override
            public JPlus25Parser.ArgumentListContext argumentList() {
                return ctx.argumentList();
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

    static MethodInvocationSignatureContextAdapter from(JPlus25Parser.PNNAMethodInvocationContext ctx) {
        return new MethodInvocationSignatureContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.IdentifierContext identifier() {
                return ctx.identifier();
            }

            @Override
            public JPlus25Parser.MethodNameContext methodName() {
                return null;
            }

            @Override
            public JPlus25Parser.ArgumentListContext argumentList() {
                return ctx.argumentList();
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

    //JPlus25Parser.PrimaryNoNewArrayExprMethodInvocationContext
    static MethodInvocationSignatureContextAdapter from(JPlus25Parser.PrimaryNoNewArrayExprMethodInvocationContext ctx) {
        return new MethodInvocationSignatureContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.IdentifierContext identifier() {
                return ctx.identifier();
            }

            @Override
            public JPlus25Parser.MethodNameContext methodName() {
                return null;
            }

            @Override
            public JPlus25Parser.ArgumentListContext argumentList() {
                return ctx.argumentList();
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

    //UnqualifiedClassInstanceCreationExpressionContext
    static MethodInvocationSignatureContextAdapter from(JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext ctx) {
        return new MethodInvocationSignatureContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.IdentifierContext identifier() {
                return null;
            }

            @Override
            public JPlus25Parser.MethodNameContext methodName() {
                return null;
            }

            @Override
            public JPlus25Parser.ArgumentListContext argumentList() {
                return ctx.argumentList();
            }

            @Override
            public JPlus25Parser.PNNAContext pNNA() {
                return null;
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

    //PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter
    static MethodInvocationSignatureContextAdapter from(PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter ctx) {
        return new MethodInvocationSignatureContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx.originalContext();
            }

            @Override
            public JPlus25Parser.IdentifierContext identifier() {
                return null;
            }

            @Override
            public JPlus25Parser.MethodNameContext methodName() {
                return null;
            }

            @Override
            public JPlus25Parser.ArgumentListContext argumentList() {
                return ctx.argumentList();
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
                return ctx.getStart();
            }
        };
    }

}
