package jplus.analyzer.nullability.context;

import jplus.analyzer.nullability.InitState;
import jplus.analyzer.nullability.dataflow.NullState;
import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.base.TypeInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContext implements Context {

    private SymbolTable instanceSymbolTable;

    private SymbolTable staticSymbolTable;

    private Map<SymbolInfo, InitState> fieldCtorNullState = new HashMap<>();

    public ClassContext(SymbolTable classSymbolTable) {
        this.instanceSymbolTable = classSymbolTable.getEnclosingSymbolTable(SymbolTable.INSTANCE_NS);
        this.staticSymbolTable = classSymbolTable.getEnclosingSymbolTable(SymbolTable.STATIC_NS);
        initFieldCtorNullState();
    }

    private void initFieldCtorNullState() {
        SymbolTable mergedSymbolTable = getMergedInstanceAndStaticSymbolTable();
        mergedSymbolTable.findSymbolInfoByType(List.of(TypeInfo.Type.Reference)).stream()
                .peek(fieldInfo -> System.err.println("[ClassContext] fieldInfo = " + fieldInfo))
                .filter(fieldInfo -> !fieldInfo.getTypeInfo().isNullable() && fieldInfo.getNullState() != NullState.NON_NULL)
                .forEach(symbolInfo -> fieldCtorNullState.put(symbolInfo, InitState.INIT));
    }

    private SymbolTable getMergedInstanceAndStaticSymbolTable() {
        SymbolTable mergedSymbolTable = instanceSymbolTable.copy();
        mergedSymbolTable.merge(staticSymbolTable);
        return mergedSymbolTable;
    }

    public void merge(ConstructorContext ctor) {
        for (var e : fieldCtorNullState.entrySet()) {
            e.setValue(
                    InitState.meet(e.getValue(), ctor.get(e.getKey().getSymbol()))
            );
        }
    }

    public boolean hasUninitializedNonNullField() {
//        SymbolTable mergedSymbolTable = getMergedInstanceAndStaticSymbolTable();
//        return mergedSymbolTable.findSymbolInfoByType(List.of(TypeInfo.Type.Reference)).stream()
//                .filter(fieldInfo -> !fieldInfo.getTypeInfo().isNullable() && fieldInfo.getNullState() != NullState.NON_NULL)
//                .anyMatch(fieldInfo -> {
//                    return fieldCtorNullState.getOrDefault(fieldInfo.getSymbol(), InitState.UNINIT) == InitState.UNINIT;
//                });
        return fieldCtorNullState.entrySet().stream()
                .anyMatch(entry -> entry.getValue() == InitState.UNINIT);
    }

    public List<SymbolInfo> getUninitializedFieldList() {
        return fieldCtorNullState.entrySet().stream()
                .filter(entry -> entry.getValue() == InitState.UNINIT)
                .map(Map.Entry::getKey)
                .toList();
    }

    public void updateNonNullFieldNullState() {
        SymbolTable mergedSymbolTable = getMergedInstanceAndStaticSymbolTable();
        mergedSymbolTable.findSymbolInfoByType(List.of(TypeInfo.Type.Reference)).stream()
                .filter(fieldInfo -> !fieldInfo.getTypeInfo().isNullable() && fieldInfo.getNullState() != NullState.NON_NULL)
                .forEach(fieldInfo -> {
                    var initState = fieldCtorNullState.get(fieldInfo.getSymbol());
                    var updated = fieldInfo.toBuilder().nullState(NullState.NON_NULL).build();
                    if (initState == InitState.INIT) {
                        if (fieldInfo.isStatic()) {
                            staticSymbolTable.declare(fieldInfo.getSymbol(), updated);
                        } else {
                            staticSymbolTable.declare(fieldInfo.getSymbol(), updated);
                        }
                    }
                });
    }

    @Override
    public String toString() {
        return "ClassContext{" +
                "fieldCtorNullState=" + fieldCtorNullState +
                '}';
    }
}
