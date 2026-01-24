package jplus.analyzer.nullability;

public enum InitState {
    INIT,    // 이 생성자에서 확실히 초기화됨
    UNINIT;  // 하나라도 경로에서 안 됨

    public static InitState meet(InitState a, InitState b) {
        if (a == INIT && b == INIT) return INIT;
        return UNINIT;
    }
}