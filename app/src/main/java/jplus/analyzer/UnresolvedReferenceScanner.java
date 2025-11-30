package jplus.analyzer;

import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.util.SymbolUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class UnresolvedReferenceScanner {

    private final SymbolTable symbolTable;
    private final String packageName;

    public UnresolvedReferenceScanner(SymbolTable symbolTable, String packageName) {
        this.symbolTable = symbolTable;
        this.packageName = packageName;
    }

    public List<Path> findUnresolvedReference() {
        List<Path> result = new ArrayList<>();
        for (SymbolInfo unresolved : findUnresolvedSymbols()) {
            String typeName = unresolved.getTypeInfo().getName();
            String fqn = this.packageName + "." + typeName;
            String pathString = fqn.replaceAll("\\.", "/");
            result.add(Path.of(pathString));
        }
        return result;
    }

    private List<SymbolInfo> findUnresolvedSymbols() {
        List<SymbolInfo> result = new ArrayList<>();
        Deque<SymbolTable> deque = new LinkedList<>();
        deque.add(symbolTable);
        while (!deque.isEmpty()) {
            SymbolTable table = deque.removeFirst();
            for (SymbolInfo symbolInfo : table) {
                if (checkUnresolvedSymbol(symbolInfo)) {
                    result.add(symbolInfo);
                }
            }
            deque.addAll(table.getEnclosingSymbolTables());
        }
        return result;
    }

    private boolean checkUnresolvedSymbol(SymbolInfo symbolInfo) {
        return !SymbolUtils.isFQN(symbolInfo.getTypeInfo().getName());
    }

}
