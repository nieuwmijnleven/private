package jplus.analyzer.nullability.context;

import jplus.analyzer.nullability.dataflow.NullState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwitchContext implements Context {

    private final Map<String, NullState> nullStateMap = new HashMap<>();

    public SwitchContext() {

    }

    public void addNullState(String label, NullState nullState) {
        nullStateMap.put(label, nullState);
    }

    public NullState join() {
        return nullStateMap.values().stream().reduce((nullState1, nullState2) -> NullState.join(nullState1, nullState2)).orElse(NullState.UNKNOWN);
    }

    @Override
    public String toString() {
        return "SwitchContext{" +
                "nullStateMap=" + nullStateMap +
                '}';
    }
}
