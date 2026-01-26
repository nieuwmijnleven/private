package jplus.analyzer.nullability.context;

import jplus.analyzer.nullability.InitState;

public class FieldContext implements Context {

    private final String name;

    private final InitState initState;

    public FieldContext(String name, InitState initState) {
        this.name = name;
        this.initState = initState;
    }

    public String getName() {
        return name;
    }

    public InitState getInitState() {
        return initState;
    }
}
