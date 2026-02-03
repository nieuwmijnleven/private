package jplus.analyzer.nullability.dataflow.context;

import jplus.analyzer.nullability.dataflow.InitState;

import java.util.HashMap;
import java.util.Map;

public class ConstructorContext implements Context {

    private Map<String, InitState> initStateMap = new HashMap<>();

    public void put(String field, InitState initState) {
        initStateMap.put(field, initState);
    }

    public InitState get(String field) {
        return initStateMap.getOrDefault(field, InitState.UNINIT);
    }

    @Override
    public String toString() {
        return "ConstructorContext{" +
                "initStateMap=" + initStateMap +
                '}';
    }
}
