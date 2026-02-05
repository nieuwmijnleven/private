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

import jplus.analyzer.nullability.dataflow.NullState;
import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.generator.TextChangeRange;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    private Utils() {}

    public static String getTokenString(ParserRuleContext ctx) {
        if (ctx == null)
            return "";

        int startIndex = ctx.start.getStartIndex();
        int stopIndex = (ctx.stop != null) ? ctx.stop.getStopIndex() : ctx.start.getStopIndex();

        return ctx.start.getTokenSource().getInputStream().getText(Interval.of(startIndex, stopIndex));
    }

    public static String indent(String string, int n) {
        if (string.isEmpty()) {
            return "";
        }

        if (n < 0) {
            throw new IllegalArgumentException("indent must be greater than 0.");
        }

        if (n == 0) {
            return string;
        }

        return " ".repeat(n);
    }

    public static String indentLines(String string, int n) {
        if (string.isEmpty()) {
            return "";
        }

        if (n < 0) {
            throw new IllegalArgumentException("indent must be greater than 0.");
        }

        if (n == 0) {
            return string;
        }

        Stream<String> stream = string.lines();
        final String spaces = " ".repeat(n);
        stream = stream.map(s -> spaces + s);
        return stream.collect(Collectors.joining("\n", "", ""));
    }

    public static boolean isJavaPrimtive(String javaPrimitive) {
        switch (javaPrimitive) {
            case "byte":
            case "char":
            case "double":
            case "float":
            case "int":
            case "long":
            case "short":
            case "boolean":
            case "void":
                return true;
            default:
                return false;
        }
    }

    public static String jvmPrimtiveToJavaPrimitive(String jvmPrimitive) {
        switch(jvmPrimitive) {
            case "B": return "byte";
            case "C": return "char";
            case "D": return "double";
            case "F": return "float";
            case "I": return "int";
            case "J": return "long";
            case "S": return "short";
            case "Z": return "boolean";
            case "V": return "void";
            default: throw new IllegalArgumentException(jvmPrimitive + "is not jvm primitive type.");
        }
    }

    public static boolean isJvmPrimtive(String jvmPrimitive) {
        switch(jvmPrimitive) {
            case "B":
            case "C":
            case "D":
            case "F":
            case "I":
            case "J":
            case "S":
            case "Z":
            case "V":
                return true;
            default:
                return false;
        }
    }

    public static String convertToPascalCase(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }

    public static String convertToCamelCase(String s) {
        return s.substring(0,1).toLowerCase() + s.substring(1);
    }

    public static int getIndexFromLineColumn(String text, TextChangeRange range, int targetLine, int targetColumn) {
        if (targetLine < range.startLine() || targetLine > range.endLine()) {
            return -1;
        }

        if (targetLine == range.startLine() && targetColumn < range.startIndex()) {
            return -1;
        }

        if (targetLine == range.endLine() && targetColumn > range.inclusiveEndIndex()) {
            return -1;
        }

        int currentLine = range.startLine();
        int currentColumn = range.startIndex();
        int index = 0;

        while (index < text.length()) {
            if (currentLine == targetLine && currentColumn == targetColumn) {
                return index;
            }

            char c = text.charAt(index);
            if (c == '\n') {
                currentLine++;
                currentColumn = 0;
            } else {
                currentColumn++;
            }

            index++;
        }

        return -1;
    }


    public static TextChangeRange computeTextChangeRange(String text, int startOffset, int endOffset) {
        if (startOffset < 0 || endOffset < startOffset || endOffset >= text.length()) {
            throw new IllegalArgumentException("Invalid start or end offset: " + "text.length() = " + text.length() + ", startOffset = " + startOffset + ", endOffset = " + endOffset);
        }

        int line = 1;
        int column = 0;
        int currentOffset = 0;

        int startLine = 1, startIndex = 0;
        int endLine = 1, endIndex = 0;

        while (currentOffset <= endOffset && currentOffset < text.length()) {
            char c = text.charAt(currentOffset);

            if (currentOffset == startOffset) {
                startLine = line;
                startIndex = column;
            }

            if (currentOffset == endOffset) {
                endLine = line;
                endIndex = column;
                break;
            }

            if (c == '\n') {
                line++;
                column = 0;
            } else {
                column++;
            }

            currentOffset++;
        }

        return new TextChangeRange(startLine, startIndex, endLine, endIndex);
    }

    public static TextChangeRange getRangeFromStartIndexAndEndIndex(
            String originalText,
            TextChangeRange originalTextChangeRange,
            int startIndex,
            int endIndex) {

        if (startIndex < 0 || endIndex < startIndex || endIndex >= originalText.length()) {
            throw new IllegalArgumentException("Invalid startIndex or endIndex");
        }

        int currentLine = originalTextChangeRange.startLine();
        int currentColumn = originalTextChangeRange.startIndex();
        int currentIndex = 0;

        int startLine = -1, startColumn = -1;
        int endLine = -1, endColumn = -1;

        while (currentIndex <= endIndex && currentIndex < originalText.length()) {
            if (currentIndex == startIndex) {
                startLine = currentLine;
                startColumn = currentColumn;
            }

            if (currentIndex == endIndex) {
                endLine = currentLine;
                endColumn = currentColumn;
                break;
            }

            char c = originalText.charAt(currentIndex);
            if (c == '\n') {
                currentLine++;
                currentColumn = 0;
            } else {
                currentColumn++;
            }

            currentIndex++;
        }

        if (endLine == -1 && currentIndex == endIndex) {
            endLine = currentLine;
            endColumn = currentColumn;
        }

        if (startLine == -1 || endLine == -1) {
            throw new IllegalStateException("Could not resolve line/column positions.");
        }

        return new TextChangeRange(startLine, startColumn, endLine, endColumn);
    }

    public static TextChangeRange getTextChangeRange(String original, ParserRuleContext ctx) {
        int startIndex = ctx.start.getStartIndex();
        int stopIndex = (ctx.stop != null) ? ctx.stop.getStopIndex() : ctx.start.getStopIndex();

        if (startIndex < 0 || stopIndex < startIndex || stopIndex >= original.length()) {
            throw new IllegalArgumentException("Invalid start or end offset: " + "text.length() = " + original.length() + ", startOffset = " + startIndex + ", endOffset = " + stopIndex + ", simpleName = " + ctx.getClass().getSimpleName());
        }

        return Utils.computeTextChangeRange(original, startIndex, stopIndex);
    }

    public static SymbolInfo getOrElse(
            Map<String, SymbolInfo> delta,
            String symbol,
            Supplier<SymbolInfo> elseFunction
    ) {

        SymbolInfo s = delta.get(symbol);
        if (s != null) {
            return s;
        }

        return elseFunction.get();
    }

    public static SymbolInfo joinNullState(SymbolTable joined, SymbolInfo stateA, SymbolInfo stateB) {

        var joinedNS = NullState.join(stateA.getNullState(), stateB.getNullState());

        return stateA.toBuilder().nullState(joinedNS).symbolTable(joined).build();
    }

    public static String getFileNameWithoutExtension(Path path) {
        String name = path.getFileName().toString();
        int dotIndex = name.lastIndexOf('.');
        return (dotIndex == -1) ? name : name.substring(0, dotIndex);
    }
}
