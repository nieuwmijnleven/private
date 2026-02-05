package jplus.analyzer.nullability.dataflow;

public enum InitState {

    INIT,
    UNINIT,
    UNKNOWN;

    public static InitState meet(InitState a, InitState b) {

        if (a == INIT && b == INIT) return INIT;

        if (a == UNKNOWN) return b;
        if (b == UNKNOWN) return a;

        return UNINIT;
    }
}