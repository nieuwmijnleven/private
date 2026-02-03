package jplus.analyzer.nullability.dataflow;

public enum InitState {
    INIT,
    UNINIT;

    public static InitState meet(InitState a, InitState b) {
        if (a == INIT && b == INIT) return INIT;
        return UNINIT;
    }
}