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

public class SourceMappingEntry {
    private String source;
    private TextChangeRange originalRange;
    private TextChangeRange transformedRange;

    public SourceMappingEntry(String source, TextChangeRange originalRange, TextChangeRange transformedRange) {
        this.source = source;
        this.originalRange = originalRange;
        this.transformedRange = transformedRange;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public TextChangeRange getOriginalRange() {
        return originalRange;
    }

    public void setOriginalRange(TextChangeRange originalRange) {
        this.originalRange = originalRange;
    }

    public TextChangeRange getTransformedRange() {
        return transformedRange;
    }

    public void setTransformedRange(TextChangeRange transformedRange) {
        this.transformedRange = transformedRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceMappingEntry sourceMappingEntry = (SourceMappingEntry) o;
        return java.util.Objects.equals(originalRange, sourceMappingEntry.originalRange)
            && java.util.Objects.equals(transformedRange, sourceMappingEntry.transformedRange);
    }
    
    @Override
    public int hashCode() {
        return java.util.Objects.hash(originalRange, transformedRange);
    }

    @Override
    public String toString() {
        return originalRange + " -> " + transformedRange;
    }
}