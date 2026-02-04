package jplus.analyzer.nullability.dataflow;

public enum NullState {

    NON_NULL,
    NULL,
    UNKNOWN;

    public static NullState join(NullState a, NullState b) {
        if (a == b) return a;
        return UNKNOWN;
    }
}
