package jplus.util;

import jplus.base.JPlus25Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ParserUtils {

    private ParserUtils() {}

    public static boolean usesNullSafety(ParserRuleContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree child = ctx.getChild(i);

            if (child instanceof TerminalNode tn) {
                if (tn.getSymbol().getType() == JPlus25Parser.NULLSAFE) {
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
}
