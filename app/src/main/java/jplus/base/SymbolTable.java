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

import jplus.analyzer.ResolvedChain;
import jplus.generator.TextChangeRange;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SymbolTable implements Iterable<SymbolInfo> {
    private SymbolTable parent;

    private final Map<String, SymbolInfo> symbolMap = new HashMap<>();

    private Map<String, SymbolTable> enclosing = new HashMap<>();

    private List<ResolvedChain> resolvedChains = new ArrayList<>();

    public SymbolTable(SymbolTable parent) {
        this.parent = parent;
    }

    public void declare(String name, SymbolInfo symbolInfo) {
        symbolMap.put(name, symbolInfo);
    }

    public SymbolInfo resolve(String name) {
        SymbolInfo symbolInfo = symbolMap.get(name);
        if (symbolInfo == null && parent != null) {
            symbolInfo = parent.resolve(name);
        }
        return symbolInfo;
    }

    public SymbolInfo resolveInCurrent(String name) {
        return symbolMap.get(name);
    }

    private SymbolInfo resolveInCurrentInternal(String name, EnumSet<TypeInfo.Type> type) {
        SymbolInfo symbolInfo = symbolMap.get(name);
        if (symbolInfo == null) return null;
        return type.contains(symbolInfo.getTypeInfo().getType()) ? symbolInfo : null;
    }

    public Optional<SymbolInfo> resolveInCurrent(String name, EnumSet<TypeInfo.Type> type) {
        return Optional.ofNullable(resolveInCurrentInternal(name, type));
    }

    @Override
    public Iterator<SymbolInfo> iterator() {
        return symbolMap.values().iterator();
    }

    public Collection<SymbolTable> getEnclosingSymbolTables() {
        return enclosing.values();
    }

    public void merge(SymbolTable table) {
        table.symbolMap.forEach(symbolMap::putIfAbsent);
        table.enclosing.forEach(enclosing::putIfAbsent);
    }

    public boolean isEmpty() {
        return symbolMap.isEmpty();
    }

    public boolean contains(String symbol, TypeInfo.Type type) {
        SymbolInfo symbolInfo = resolve(symbol);
        if (symbolInfo != null && symbolInfo.getTypeInfo().getType() == type) {
            return true;
        }
        return false;
    }

    public boolean contains(String symbol, EnumSet<TypeInfo.Type> types) {
        SymbolInfo symbolInfo = resolve(symbol);
        if (symbolInfo == null) return false;

        return types.contains(symbolInfo.getTypeInfo().getType());
    }

    public boolean containsInCurrent(String symbol, TypeInfo.Type type) {
        SymbolInfo symbolInfo = resolveInCurrent(symbol);
        if (symbolInfo != null && symbolInfo.getTypeInfo().getType() == type) {
            return true;
        }
        return false;
    }

    public boolean containsInCurrent(String symbol, EnumSet<TypeInfo.Type> types) {
        SymbolInfo symbolInfo = resolveInCurrent(symbol);
        if (symbolInfo == null) return false;

        return types.contains(symbolInfo.getTypeInfo().getType());
    }

    public List<String> findSymbolsByType(List<TypeInfo.Type> typeList) {
        return symbolMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(symbolInfo -> typeList.contains(symbolInfo.getTypeInfo().getType()))
                .sorted(
                        Comparator
                                .comparingInt((SymbolInfo s) -> s.getRange().startLine())
                                .thenComparingInt(s -> s.getRange().startIndex())
                )
                .map(SymbolInfo::getSymbol)
                .toList();
    }

    public SymbolTable getParent() {
        return parent;
    }

    public SymbolTable addEnclosingSymbolTable(String name, SymbolTable symbolTable) {
        if (symbolTable == null) throw new IllegalArgumentException();
        enclosing.put(name, symbolTable);
        return symbolTable;
    }

    public SymbolTable getEnclosingSymbolTable(String name) {
        return enclosing.computeIfAbsent(name, s -> new SymbolTable((this)));
//        return enclosing.getOrDefault(name, new SymbolTable(this));
    }

    public void addResolvedChain(ResolvedChain chain) {
        resolvedChains.add(chain);
    }

    public List<ResolvedChain> getResolvedChains() {
        return Collections.unmodifiableList(resolvedChains);
    }

    private boolean compareResolvedChainRange(ResolvedChain chain, TextChangeRange range) {
        return Optional.ofNullable(chain.last())
                .map(step -> step.range.equals(range))
                .orElse(false);
    }

    private Optional<ResolvedChain> findResolvedChildChain(ResolvedChain chain, TextChangeRange range) {
        if (compareResolvedChainRange(chain, range)) {
            return Optional.of(chain);
        }

        for (ResolvedChain.Step step : chain.getSteps()) {
            if (step.kind == ResolvedChain.Kind.CHAIN) {
                var result = findResolvedChildChain(step.childChain, range);
                if (result.isPresent()) return result;
            }
        }

        return Optional.empty();
    }

    public Optional<ResolvedChain> findResolvedChain(TextChangeRange range) {
        for (ResolvedChain resolvedChain : resolvedChains) {
            var result = findResolvedChildChain(resolvedChain, range);
            if (result.isPresent()) return result;
        }

        return Optional.empty();
    }

    public SymbolTable findLowContextSymbolTable(String name) {
        if (!symbolMap.containsKey(name)) {
            SymbolTable found = null;
            for (SymbolTable symbolTable : enclosing.values()) {
                if ((found = symbolTable.findLowContextSymbolTable(name)) != null) {
                    return found;
                }
            }
            return new SymbolTable(null);
        }
        return enclosing.get(name);
    }

    @Override
    public String toString() {
        return "SymbolTable{" +
                //"parent=" + parent +
                ", symbolMap=" + symbolMap +
                ", enclosing=" + enclosing +
                '}';
    }
}
