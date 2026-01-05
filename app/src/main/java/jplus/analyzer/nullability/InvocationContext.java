package jplus.analyzer.nullability;

import jplus.base.JPlus25Parser;
import jplus.generator.TextChangeRange;
import jplus.util.Utils;
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
