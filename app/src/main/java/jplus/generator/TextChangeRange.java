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

package jplus.generator;

import java.util.Objects;

public record TextChangeRange(int startLine, int startIndex, int endLine, int inclusiveEndIndex) {

    public static final TextChangeRange EMPTY = new TextChangeRange(0, 0,Integer.MIN_VALUE, Integer.MIN_VALUE);

    public static TextChangeRange copyFrom(final TextChangeRange range) {
        return new TextChangeRange(range.startLine, range.startIndex, range.endLine, range.inclusiveEndIndex);
    }

    public boolean isEmpty() {
        return endLine < startLine;
    }

    public boolean contains(TextChangeRange other) {
        return this.startLine() <= other.startLine()
                && this.endLine() >= other.endLine()
                && (this.startLine() != other.startLine() || this.startIndex() <= other.startIndex())
                && (this.endLine() != other.endLine() || this.inclusiveEndIndex() >= other.inclusiveEndIndex());
    }

    public boolean overlaps(TextChangeRange other) {
        return !(this.endLine() < other.startLine() ||
                this.startLine() > other.endLine() ||
                (this.endLine() == other.startLine() && this.inclusiveEndIndex() < other.startIndex()) ||
                (this.startLine() == other.endLine() && this.startIndex() > other.inclusiveEndIndex()));
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextChangeRange)) return false;
        TextChangeRange that = (TextChangeRange) o;
        return this.startLine() == that.startLine()
                && this.startIndex() == that.startIndex()
                && this.endLine() == that.endLine()
                && this.inclusiveEndIndex() == that.inclusiveEndIndex();
    }

    @Override
    public int hashCode() {
        return Objects.hash(startLine(), startIndex(), endLine(), inclusiveEndIndex());
    }*/

    @Override
    public String toString() {
        return "TextChangeRange{" +
                "startLine=" + startLine +
                ", startIndex=" + startIndex +
                ", endLine=" + endLine +
                ", inclusiveEndIndex=" + inclusiveEndIndex +
                '}';
    }
}