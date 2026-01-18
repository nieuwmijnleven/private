package jplus.analyzer.nullability;

import jplus.base.SymbolTable;

class ConditionResult {
    SymbolTable whenTrue;
    SymbolTable whenFalse;

    public ConditionResult(SymbolTable whenTrue, SymbolTable whenFalse) {
        this.whenTrue = whenTrue;
        this.whenFalse = whenFalse;
    }

    @Override
    public String toString() {
        return "ConditionResult{" +
                "whenTrue=" + whenTrue +
                ", whenFalse=" + whenFalse +
                '}';
    }
}
