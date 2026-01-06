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

package jplus.analyzer;

import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.base.TypeInfo;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class UnresolvedReferenceScanner {

    private final SymbolTable symbolTable;
    private final String packageName;

    public static class UnresolvedReferenceInfo {
        public String className;

        public String packageName;

        public String getFullyQualifiedName() {
            return packageName + "." + className;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof UnresolvedReferenceInfo info)) return false;
            return Objects.equals(className, info.className) && Objects.equals(packageName, info.packageName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(className, packageName);
        }

        @Override
        public String toString() {
            return "UnresolvedReferenceInfo{" +
                    "className='" + className + '\'' +
                    ", packageName='" + packageName + '\'' +
                    '}';
        }
    }

    public UnresolvedReferenceScanner(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.packageName = symbolTable.resolve("^PackageName$").getSymbol();
        System.err.println("[UnresolvedReferenceScanner] packageName = " + this.packageName);
    }

    public List<UnresolvedReferenceInfo> findUnresolvedReference() {
        Set<UnresolvedReferenceInfo> unresolvedReferenceInfoList = new HashSet<>();
        for (SymbolInfo unresolved : findUnresolvedSymbols()) {
            UnresolvedReferenceInfo info = new UnresolvedReferenceInfo();
            info.className = unresolved.getTypeInfo().getName();
            info.packageName = this.packageName;
            unresolvedReferenceInfoList.add(info);
        }
        System.err.println("[UnresolvedReferenceScanner] unresolvedReferenceInfoList = " + unresolvedReferenceInfoList);
        return unresolvedReferenceInfoList.stream().toList();
    }

    private List<SymbolInfo> findUnresolvedSymbols() {
        List<SymbolInfo> unresolved = new ArrayList<>();
        Deque<SymbolTable> deque = new LinkedList<>();
        deque.add(symbolTable);
        while (!deque.isEmpty()) {
            SymbolTable table = deque.removeFirst();
            for (SymbolInfo symbolInfo : table) {
                if (checkUnresolvedSymbol(symbolInfo)) {
                    System.err.println("[UnresolvedReferenceScanner] symbolInfo = " + symbolInfo);
                    unresolved.add(symbolInfo);
                }
            }
            deque.addAll(table.getEnclosingSymbolTables());
        }
        return unresolved;
    }

    private boolean checkUnresolvedSymbol(@NonNull SymbolInfo symbolInfo) {
        if ("<error>".equals(symbolInfo.getSymbol())) return false;
        if ("<any>".equals(symbolInfo.getTypeInfo().getName())) return false;
        return symbolInfo.getTypeInfo().getType() == TypeInfo.Type.Unknown;
    }
}
