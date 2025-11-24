package jplus.util;

import jplus.generator.TextChangeRange;
import org.antlr.v4.runtime.ParserRuleContext;

public class CodeUtils {
    private CodeUtils() {}

    public static String replaceNullsafeOperator(ParserRuleContext ctx) {
        String tokenString = Utils.getTokenString(ctx).replace("?.", ".");
        String variableName = tokenString.split("\\.")[0];

        String replaced = "(" +
                "((" + variableName + ")!=null)?" +
                "(" + tokenString + "):" +
                "null" +
                ")";

        return replaced;
    }
}
