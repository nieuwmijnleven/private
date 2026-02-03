package jplus.analyzer.nullability.context.adapter;

import jplus.base.JPlus25Parser;
import org.antlr.v4.runtime.ParserRuleContext;

public interface InvocationContext {
    ParserRuleContext originalContext();

    static InvocationContext from(JPlus25Parser.MethodInvocationContext ctx) {
        return new InvocationContext() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }
        };
    }

    static InvocationContext from(JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext ctx) {
        return new InvocationContext() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }
        };
    }
}
