package jplus.analyzer.nullability.dataflow;

public enum NullState {
    NON_NULL,   // 확실히 null 아님
    NULL,       // 확실히 null
    UNKNOWN;    // maybe-null (top)

    public static NullState join(NullState a, NullState b) {
        if (a == b) return a;
        return UNKNOWN;
    }
}
