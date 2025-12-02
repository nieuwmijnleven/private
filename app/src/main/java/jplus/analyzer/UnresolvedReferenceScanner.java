package jplus.analyzer;

import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.base.TypeInfo;
import jplus.util.SymbolUtils;

import java.nio.file.Path;
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
    }

    public UnresolvedReferenceScanner(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.packageName = symbolTable.resolve("^PackageName$").getSymbol();
        System.err.println("[UnresolvedReferenceScanner] packageName = " + this.packageName);
    }

    public List<UnresolvedReferenceInfo> findUnresolvedReference() {
        Set<UnresolvedReferenceInfo> unresolvedReferenceInfoList = new HashSet<>();
        for (SymbolInfo unresolved : findUnresolvedSymbols()) {
            if ("<error>".equals(unresolved.getSymbol())) continue;

            UnresolvedReferenceInfo info = new UnresolvedReferenceInfo();
            info.className = unresolved.getTypeInfo().getName();
            info.packageName = this.packageName;
            unresolvedReferenceInfoList.add(info);
        }
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
                    unresolved.add(symbolInfo);
                }
            }
            deque.addAll(table.getEnclosingSymbolTables());
        }
        return unresolved;
    }

    private boolean checkUnresolvedSymbol(SymbolInfo symbolInfo) {
        TypeInfo typeInfo = symbolInfo.getTypeInfo();
        return typeInfo.getType() != TypeInfo.Type.Method && !SymbolUtils.isFQN(typeInfo.getName());
    }

}
