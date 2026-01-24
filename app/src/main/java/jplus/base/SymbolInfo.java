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

package jplus.base;

import jplus.analyzer.nullability.dataflow.NullState;
import jplus.generator.TextChangeRange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SymbolInfo {
    private final String symbol;
    private final TypeInfo typeInfo;
    private final NullState nullState;
    private final TextChangeRange range;
    private final String originalText;
    private final List<Modifier> modifierList;

    private ResolvingStatus resolvingStatus = ResolvingStatus.RESOLVED;
    private SymbolTable symbolTable;

    public enum ResolvingStatus {
        RESOLVING,
        RESOLVED,
        UNRESOLVED
    }

    public SymbolInfo(String symbol, TypeInfo typeInfo, NullState nullState, TextChangeRange range, String originalText, List<Modifier> modifierList, SymbolTable symbolTable) {
        this.symbol = symbol;
        this.typeInfo = typeInfo;
        this.nullState = nullState;
        this.range = range;
        this.originalText = originalText;
        this.modifierList = modifierList;
        this.symbolTable = symbolTable;
    }

    public SymbolInfo(String symbol, TypeInfo typeInfo, TextChangeRange range, String originalText, List<Modifier> modifierList, SymbolTable symbolTable) {
        //this(symbol, typeInfo, typeInfo.isNullable() ? NullState.UNKNOWN : NullState.NON_NULL, range, originalText, modifierList, symbolTable);
        this(symbol, typeInfo, NullState.UNKNOWN, range, originalText, modifierList, symbolTable);
    }

    public SymbolInfo(String symbol, TypeInfo typeInfo, TextChangeRange range, String originalText, List<Modifier> modifierList) {
        this(symbol, typeInfo, NullState.UNKNOWN, range, originalText, modifierList, null);
    }

    public SymbolInfo(SymbolInfo other) {
        this.symbol = other.symbol;
        this.typeInfo = other.typeInfo;
        this.nullState = other.nullState;
        this.range = other.range;
        this.originalText = other.originalText;
        this.modifierList = other.modifierList;
        this.symbolTable = other.symbolTable;
    }

    public static SymbolInfo from(SymbolInfo other) {
        return new SymbolInfo(other);
    }

    public Builder toBuilder() {
        return new Builder()
                .symbol(this.symbol)
                .typeInfo(this.typeInfo)
                .nullState(this.nullState)
                .originalText(this.originalText)
                .range(this.range)
                .modifierList(this.modifierList)
                .symbolTable(this.symbolTable);
    }

    public String getSymbol() {
        return symbol;
    }

    public TypeInfo getTypeInfo() {
        return typeInfo;
    }

    public NullState getNullState() {
        return nullState;
    }

    public TextChangeRange getRange() {
        return range;
    }

    public String getOriginalText() {
        return originalText;
    }

    public List<Modifier> getModifierList() {
        return Collections.unmodifiableList(modifierList);
    }

    public boolean isStatic() {
        return modifierList.contains(Modifier.STATIC);
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public ResolvingStatus getResolvingStatus() {
        return resolvingStatus;
    }

    public void setResolvingStatus(ResolvingStatus resolvingStatus) {
        this.resolvingStatus = resolvingStatus;
    }

    @Override
    public String toString() {
        return "SymbolInfo{" +
                "symbol=" + symbol +
                ", typeInfo=" + typeInfo +
                ", nullState=" + nullState +
                ", modifierList=" + modifierList +
//                ", range=" + range +
//                ", originalText='" + originalText + '\'' +
                '}';
    }

    public static class Builder {
        private String symbol;
        private TypeInfo typeInfo;
        private NullState nullState;
        private TextChangeRange range;
        private String originalText;
        private List modifierList;

        private SymbolTable symbolTable;

        public Builder symbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder typeInfo(TypeInfo typeInfo) {
            this.typeInfo = typeInfo;
            return this;
        }

        public Builder nullState(NullState nullState) {
            this.nullState = nullState;
            return this;
        }

        public Builder range(TextChangeRange range) {
            this.range = range;
            return this;
        }

        public Builder originalText(String originalText) {
            this.originalText = originalText;
            return this;
        }

        public Builder modifierList(List modifierList) {
            this.modifierList = modifierList;
            return this;
        }

        public Builder symbolTable(SymbolTable symbolTable) {
            this.symbolTable = symbolTable;
            return this;
        }
        public SymbolInfo build() {
            return new SymbolInfo(symbol, typeInfo, (nullState == null ? (typeInfo.isNullable() ? NullState.UNKNOWN : NullState.NON_NULL) : nullState), range, originalText, modifierList, symbolTable);
        }

    }

    public static Builder builder() {
        return new Builder();
    }
}
