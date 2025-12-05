package jplus.base;

import jplus.generator.TextChangeRange;
import jplus.util.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JavaMethodInvocationManager {
    private final String source;
    private final Map<SymbolTable, Map<String, MethodInvocationInfo>> invocationMapInSymbolTable = new HashMap<>();

    public JavaMethodInvocationManager(String source) {
        this.source = source;
    }

    public void addInvocationInfo(SymbolTable symbolTable, MethodInvocationInfo invocationInfo) {
        var invocationMap = invocationMapInSymbolTable.computeIfAbsent(symbolTable, (k) -> new HashMap<>());
        invocationMap.put(invocationInfo.source, invocationInfo);
    }

    public MethodInvocationInfo getInvocationInfo(SymbolTable symbolTable, String invocationCode) {
        return Optional.ofNullable(invocationMapInSymbolTable.get(symbolTable)).orElseThrow().get(invocationCode);
    }

    public Optional<MethodInvocationInfo> findInvocationInfo(SymbolTable symbolTable, TextChangeRange range) {
        return Optional.ofNullable(invocationMapInSymbolTable.get(symbolTable)).stream()
                .flatMap(methodInvocationInfoMap -> methodInvocationInfoMap.entrySet().stream())
                .map(Map.Entry::getValue)
                .filter(invocationInfo -> {
                    TextChangeRange invocationCodeRange = Utils.computeTextChangeRange(source, invocationInfo.startPos, invocationInfo.endPos);
                    return invocationCodeRange.equals(range);
                })
                .findFirst();
    }
}
