package jplus.util;

public class SymbolUtils {

    private SymbolUtils() {}

    /**
     * 심볼이 FQN인지 아닌지 판별
     * 
     * @param symbol 심볼 이름
     * @return FQN이면 true, 아니면 false
     */
    public static boolean isFQN(String symbol) {
        if (symbol == null || symbol.isEmpty()) {
            return false;
        }
        // 최소한 2단 이상으로 . 구분되어 있어야 FQN으로 판단
        String[] parts = symbol.split("\\.");
        return parts.length > 1;
    }
}
