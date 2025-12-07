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

import jplus.generator.TextChangeRange;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SymbolTable implements Iterable<SymbolInfo> {
    private SymbolTable parent;

    private final Map<String, SymbolInfo> symbolMap = new HashMap<>();

    private Map<String, SymbolTable> enclosing = new HashMap<>();

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

    public List<String> findSymbolsByType(List<TypeInfo.Type> typeList) {
//        return symbolMap.entrySet().stream().map(Map.Entry::getValue).filter(symbolInfo -> typeList.contains(symbolInfo.getTypeInfo().type)).sorted(
//            Comparator.<SymbolInfo>comparingInt(value -> value.getRange().startLine()).thenComparingInt(value -> value.getRange().startIndex())).map(symbolInfo -> symbolInfo.getSymbol()).toList();
        return symbolMap.entrySet().stream().map(Map.Entry::getValue).filter(symbolInfo -> typeList.contains(symbolInfo.getTypeInfo().type)).map(symbolInfo -> symbolInfo.getSymbol()).toList();
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
