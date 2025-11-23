package jplus.base;

import java.util.HashMap;
import java.util.Map;

public class JavaMethodInvocationManager {
    private final Map<SymbolTable, Map<String, MethodInvocationInfo>> invocationMapInSymbolTable = new HashMap<>();

    public void addInvocationInfo(SymbolTable symbolTable, String invocationCode, MethodInvocationInfo invocationInfo) {
        var invocationMap = invocationMapInSymbolTable.computeIfAbsent(symbolTable, (k) -> new HashMap<>());
        invocationMap.put(invocationCode, invocationInfo);
    }
}
