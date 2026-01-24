package jplus.analyzer.nullability.context;

import jplus.base.SymbolInfo;

public class MethodContext implements Context {

    private final String methodName;

    private final SymbolInfo methodSymbolInfo;

    public MethodContext(String methodName, SymbolInfo methodSymbolInfo) {
        this.methodName = methodName;
        this.methodSymbolInfo = methodSymbolInfo;
    }

    public SymbolInfo getMethodSymbolInfo() {
        return methodSymbolInfo;
    }

    public String getMethodName() {
        return methodName;
    }

    @Override
    public String toString() {
        return "MethodContext{" +
                "methodName=" + methodName +
                "methodSymbolInfo=" + methodSymbolInfo +
                '}';
    }
}
