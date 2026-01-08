package jplus.analyzer.nullability;

import jplus.base.JPlus25Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public interface ExpressionNameInvocationContext {
    ParserRuleContext originalContext();
    JPlus25Parser.ExpressionNameContext expressionName();
    ParserRuleContext identifier();
    JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression();
    JPlus25Parser.PNNAContext pNNA();
    TerminalNode LPAREN();
    TerminalNode NULLSAFE();
    Token getStart();

    static ExpressionNameInvocationContext from(JPlus25Parser.PrimaryNoNewArrayExprQualifiedClassInstanceCreationContext ctx) {
        return new ExpressionNameInvocationContext() {
            public ParserRuleContext originalContext() { return ctx; }
            public JPlus25Parser.ExpressionNameContext expressionName() { return ctx.expressionName(); }
            public ParserRuleContext identifier() { return null; }
            public JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression() { return ctx.unqualifiedClassInstanceCreationExpression(); }
            public JPlus25Parser.PNNAContext pNNA() { return ctx.pNNA(); }
            public TerminalNode LPAREN() { return null; }
            public TerminalNode NULLSAFE() { return ctx.NULLSAFE(); }
            public Token getStart() { return ctx.start; }
        };
    }

    static ExpressionNameInvocationContext from(JPlus25Parser.MethodInvocationContext ctx) {
        return new ExpressionNameInvocationContext() {
            public ParserRuleContext originalContext() { return ctx; }
            public JPlus25Parser.ExpressionNameContext expressionName() { return ctx.expressionName(); }
            public ParserRuleContext identifier() { return ctx.identifier(); }
            public JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext unqualifiedClassInstanceCreationExpression() { return null; }
            public JPlus25Parser.PNNAContext pNNA() { return null; }
            public TerminalNode LPAREN() { return ctx.LPAREN(); }
            public TerminalNode NULLSAFE() { return ctx.NULLSAFE(); }
            public Token getStart() { return ctx.start; }
        };
    }
}
