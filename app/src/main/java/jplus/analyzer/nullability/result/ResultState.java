package jplus.analyzer.nullability.result;

import jplus.analyzer.nullability.dataflow.NullState;

public class ResultState {

    private NullState nullState;

    public ResultState(NullState nullState) {
        this.nullState = nullState;
    }

    public NullState getNullState() {
        return nullState;
    }
}
