/*
 * Copyright 2025 Cheol Jeon <nieuwmijnleven@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
