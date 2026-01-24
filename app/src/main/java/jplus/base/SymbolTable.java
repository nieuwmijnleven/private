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
import jplus.analyzer.nullability.dataflow.NullState;
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

    public static final String STATIC_NS = "^static$";
    public static final String INSTANCE_NS = "^instance$";

    private SymbolTable superClassTable;

    private List<SymbolTable> superInterfaceTables;

    private final ExecutionContext context;

    private boolean deadContext;

    private SymbolTable parent;

    private Map<String, SymbolInfo> symbolMap = new HashMap<>();

    private Map<String, SymbolTable> enclosing = new HashMap<>();

    private List<ResolvedChain> resolvedChains = new ArrayList<>();

    public enum ExecutionContext {
        STATIC,
        INSTANCE
    }

    /*enum TableKind {
        CLASS,
        METHOD,
        BLOCK
    }*/

    public SymbolTable(SymbolTable parent, ExecutionContext context) {
        this.parent = parent;
        this.context = context;
    }

    public SymbolTable(SymbolTable parent) {
        this(parent, ExecutionContext.INSTANCE);
    }

    public SymbolTable copy() {
        SymbolTable replica = new SymbolTable(parent, this.context);

        // 1. symbolMap deep copy (상태)
        replica.symbolMap = new HashMap<>();
        for (var e : this.symbolMap.entrySet()) {
            replica.symbolMap.put(e.getKey(), e.getValue().toBuilder().build());
        }

        // 2. resolvedChains deep copy
        replica.resolvedChains = new ArrayList<>(this.resolvedChains);
//        for (ResolvedChain chain : this.resolvedChains) {
//            copy.resolvedChains.add(chain.deepCopy());
//        }

        // 3. enclosing은 새로 만들되, child만 복제
        replica.enclosing = new HashMap<>();
        for (var e : this.enclosing.entrySet()) {
            SymbolTable childCopy = e.getValue().copy();
            childCopy.parent = replica;          // ⭐ 중요
            replica.enclosing.put(e.getKey(), childCopy);
        }

        return replica;
    }


    private void declareInternal(String name, SymbolInfo symbolInfo) {
        symbolMap.put(name, symbolInfo);
    }

    public void declare(String name, SymbolInfo symbolInfo) {
        if (isClassContext()) {
            if (symbolInfo.isStatic()) {
                getEnclosingSymbolTable(STATIC_NS)
                        .declareInternal(name, symbolInfo);
            } else {
                getEnclosingSymbolTable(INSTANCE_NS)
                        .declareInternal(name, symbolInfo);
            }
        } else {
            declareInternal(name, symbolInfo);
        }
    }

    private boolean isClassContext() {
        return containsInCurrent("^ClassDef$", TypeInfo.Type.Class);
    }

    /*public SymbolInfo resolve(String name) {
        SymbolInfo symbolInfo = symbolMap.get(name);
        if (symbolInfo == null && parent != null) {
            symbolInfo = parent.resolve(name);
        }
        return symbolInfo;
    }*/

    public void setSuperClassTable(SymbolTable superClassTable) {
        this.superClassTable = superClassTable;
    }
    
    public boolean hasSuperClassTable() {
        return this.superClassTable != null;
    }

    public SymbolTable getSuperClassTable() {
        return superClassTable;
    }

    public void setSuperInterfaceTables(List<SymbolTable> superInterfaceTables) {
        this.superInterfaceTables = superInterfaceTables;
    }

    public SymbolInfo resolve(String name) {

        SymbolInfo symbolInfo = resolveInCurrent(name);
        if (symbolInfo != null) {
            return symbolInfo;
        }

        if (superClassTable != null) {
            symbolInfo = superClassTable.resolve(name);
            if (symbolInfo != null) return symbolInfo;
        }

        if (parent != null) {
            return parent.resolve(name);
        }

        return null;
    }

    public SymbolInfo resolveInCurrent(String name) {
        SymbolInfo local = symbolMap.get(name);
        if (local != null) return local;

        if (!isClassContext()) return null;

        System.err.println("[resolve] symbol = " + name);
        if (context == ExecutionContext.INSTANCE) {
            SymbolTable instanceNS = enclosing.get(INSTANCE_NS);
            System.err.println("[resolve][instance] isClassContext symbol = " + name);
            if (instanceNS != null) {
                SymbolInfo inst = instanceNS.resolveInCurrent(name);
                if (inst != null) return inst;
            }
        }

        SymbolTable staticNS = enclosing.get(STATIC_NS);
        if (staticNS != null) {
            System.err.println("[resolve][static] isClassContext symbol = " + name);
            SymbolInfo stat = staticNS.resolveInCurrent(name);
            if (stat != null) return stat;
        }

        return null;
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

    public Iterator<String> keyIterator() {
        return symbolMap.keySet().iterator();
    }

    public List<SymbolTable> getEnclosingSymbolTables() {
        return enclosing.values().stream().toList();
    }

    public void merge(SymbolTable table) {
        table.symbolMap.forEach(symbolMap::putIfAbsent);
        table.enclosing.forEach(enclosing::putIfAbsent);
    }

    public boolean isEmpty() {
        return symbolMap.isEmpty();
    }

    public boolean isDeadContext() {
        return deadContext;
    }

    public void setDeadContext(boolean deadContext) {
        this.deadContext = deadContext;
    }

    public void mergeDeadContext(boolean deadContext) {
        this.deadContext |= deadContext;
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
        SymbolInfo symbolInfo = symbolMap.get(symbol);
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

        var allSymbolMap = symbolMap;

        if (isClassContext()) {
            allSymbolMap = new HashMap<>();
            getEnclosingSymbolTable(INSTANCE_NS).symbolMap.forEach(allSymbolMap::putIfAbsent);
            getEnclosingSymbolTable(STATIC_NS).symbolMap.forEach(allSymbolMap::putIfAbsent);
        }

        return allSymbolMap.entrySet().stream()
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

    public List<SymbolInfo> findSymbolInfoByType(List<TypeInfo.Type> typeList) {

        var allSymbolMap = symbolMap;

        if (isClassContext()) {
            allSymbolMap = new HashMap<>();
            getEnclosingSymbolTable(INSTANCE_NS).symbolMap.forEach(allSymbolMap::putIfAbsent);
            getEnclosingSymbolTable(STATIC_NS).symbolMap.forEach(allSymbolMap::putIfAbsent);
        }

        return allSymbolMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(symbolInfo -> typeList.contains(symbolInfo.getTypeInfo().getType()))
                .sorted(
                        Comparator
                                .comparingInt((SymbolInfo s) -> s.getRange().startLine())
                                .thenComparingInt(s -> s.getRange().startIndex())
                )
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
        return enclosing.computeIfAbsent(name, s -> {
            if (STATIC_NS.equals(name)) {
                return new SymbolTable(this, ExecutionContext.STATIC);
            }
            if (INSTANCE_NS.equals(name)) {
                return new SymbolTable(this, ExecutionContext.INSTANCE);
            }
            return new SymbolTable(this, this.context);
        });
    }

//    public SymbolTable joinNullState(SymbolTable other) {
//        for (String symbol : symbolMap.keySet()) {
//            var syminfo1 = resolveInCurrent(symbol);
//            var symInfo2 = other.resolveInCurrent(symbol);
//            NullState joined = NullState.join(syminfo1.getNullState(), symInfo2.getNullState());
//            symbolMap.put(symbol, syminfo1.toBuilder().nullState(joined).build());
//        }
//
//        return this;
//    }

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

    private boolean compareResolvedQualifierChain(ResolvedChain chain, TextChangeRange range) {
        return Optional.ofNullable(chain.qualifierLast())
                .map(step -> step.range.equals(range))
                .orElse(false);
    }

    private Optional<ResolvedChain> findResolvedChildQualifierChain(ResolvedChain chain, TextChangeRange range) {
        if (compareResolvedQualifierChain(chain, range)) {
            return Optional.of(chain);
        }

        for (ResolvedChain.Step step : chain.getSteps()) {
            if (step.kind == ResolvedChain.Kind.CHAIN) {
                var result = findResolvedChildQualifierChain(step.childChain, range);
                if (result.isPresent()) return result;
            }
        }

        return Optional.empty();
    }

    public Optional<ResolvedChain> findResolvedQualifierChain(TextChangeRange range) {
        for (ResolvedChain resolvedChain : resolvedChains) {
            var result = findResolvedChildQualifierChain(resolvedChain, range);
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
