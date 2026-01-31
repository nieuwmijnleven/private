package jplus.analyzer.nullability.adapter;

import jplus.base.JPlus25Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public interface SwitchExprStmtContext {
    ParserRuleContext originalContext();
    JPlus25Parser.ExpressionContext expression();
    JPlus25Parser.SwitchBlockContext switchBlock();
    Token getStart();

    static SwitchExprStmtContext from(JPlus25Parser.SwitchExpressionContext ctx) {
        return new SwitchExprStmtContext() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.ExpressionContext expression() {
                return ctx.expression();
            }

            @Override
            public JPlus25Parser.SwitchBlockContext switchBlock() {
                return ctx.switchBlock();
            }

            @Override
            public Token getStart() {
                return ctx.start;
            }
        };
    }

    static SwitchExprStmtContext from(JPlus25Parser.SwitchStatementContext ctx) {
        return new SwitchExprStmtContext() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.ExpressionContext expression() {
                return ctx.expression();
            }

            @Override
            public JPlus25Parser.SwitchBlockContext switchBlock() {
                return ctx.switchBlock();
            }

            @Override
            public Token getStart() {
                return ctx.start;
            }
        };
    }

}
