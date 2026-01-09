package jplus.analyzer.nullability;

import jplus.base.JPlus25Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public interface PrimaryNoNewArrayMethodInvocationContextAdapter {
    ParserRuleContext originalContext();
    JPlus25Parser.IdentifierContext identifier();
    JPlus25Parser.PNNAContext pNNA();
    Token getStart();

    static PrimaryNoNewArrayMethodInvocationContextAdapter from(JPlus25Parser.PrimaryNoNewArrayTypeMethodInvocationContext ctx) {
        return new PrimaryNoNewArrayMethodInvocationContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.IdentifierContext identifier() {
                return ctx.identifier();
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

    static PrimaryNoNewArrayMethodInvocationContextAdapter from(JPlus25Parser.PrimaryNoNewArrayArrayMethodInvocationContext ctx) {
        return new PrimaryNoNewArrayMethodInvocationContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.IdentifierContext identifier() {
                return ctx.identifier();
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

    static PrimaryNoNewArrayMethodInvocationContextAdapter from(JPlus25Parser.PrimaryNoNewArrayQualifiedSuperMethodInvocationContext ctx) {
        return new PrimaryNoNewArrayMethodInvocationContextAdapter() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.IdentifierContext identifier() {
                return ctx.identifier();
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
}
