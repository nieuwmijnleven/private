/*
 * JADEx - Java Advanced Development Extension
 *
 * Copyright (C) 2026 Cheol Jeon <nieuwmijnleven@outlook.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 only,
 * as published by the Free Software Foundation.
 *
 * Alternatively, this software may be used under a commercial license
 * from Cheol Jeon.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License version 2 for more details:
 * <https://www.gnu.org/licenses/old-licenses/gpl-2.0.html>.
 *
 * For commercial licensing, please contact <nieuwmijnleven@outlook.com>.
 *
 * Contributors to this project must sign a Contributor License Agreement (CLA)
 * granting Cheol Jeon the right to relicense their contributions under
 * a commercial license. See the CLA file in the project root for details.
 */

package jplus.processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LombokCleaner {

    public static String removeLombokGeneratedPerfectly(String text) {
        if (text == null || text.isEmpty()) return text;

        // 1. 롬복 생성 패턴 (메서드 시작 부분까지만 잡음)
        String lombokStartRegex = "(?s)(/\\*\\*.*?\\*/\\s*)?" +
                                  "(@[a-zA-Z0-9.]+(\\(\".*?\"\\))?\\s*)*" +
                                  "@lombok\\.Generated\\s+" +
                                  "(@[a-zA-Z0-9.]+(\\(\".*?\"\\))?\\s*)*" +
                                  "public [^{]+?\\{";

        Pattern pattern = Pattern.compile(lombokStartRegex);
        StringBuilder sb = new StringBuilder(text);
        Matcher matcher = pattern.matcher(sb);

        int offset = 0;
        while (matcher.find(offset)) {
            int start = matcher.start();
            int openBraceIndex = matcher.end() - 1; // '{' 의 위치

            // 2. '{' 부터 짝이 맞는 '}' 찾기 (중첩 괄호 계산)
            int end = findMatchingClosingBrace(sb, openBraceIndex);

            if (end != -1) {
                // 3. 주석부터 마지막 '}' 까지 통째로 삭제
                sb.delete(start, end + 1);
                offset = start; // 삭제한 지점부터 다시 검색
                matcher = pattern.matcher(sb); // 문자열이 변했으므로 매처 갱신
            } else {
                offset = matcher.end();
            }
        }

        String cleaned = sb.toString();
        // 잔여 어노테이션 및 공백 정리
        cleaned = cleaned.replaceAll("@java\\.lang\\.SuppressWarnings\\(\"all\"\\)\\s*", "");
        cleaned = cleaned.replaceAll("@lombok\\.Generated\\s*", "");
        cleaned = cleaned.replaceAll("(\\r?\\n){3,}", "\n\n");

        return cleaned.trim();
    }

    private static int findMatchingClosingBrace(StringBuilder sb, int openIndex) {
        int closeIndex = -1;
        int counter = 1;
        for (int i = openIndex + 1; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '{') {
                counter++;
            } else if (c == '}') {
                counter--;
                if (counter == 0) {
                    closeIndex = i;
                    break;
                }
            }
        }
        return closeIndex;
    }
}