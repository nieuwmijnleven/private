package jplus.analyzer;

import jplus.base.MethodInvocationInfo;
import jplus.base.TypeInfo;
import jplus.generator.TextChangeRange;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ResolvedChain {

    public enum Kind {
        IDENTIFIER,
        FIELD,
        METHOD,
        CHAIN
    }

    public static final class Step {
        //public final Element element;      // field / method / variable
        public final Kind kind;
        public final String symbol;
        public final TypeInfo typeInfo;     // 이 step의 결과 타입
        public final boolean nullable;      // 선언상 nullable
        public final TextChangeRange range; // 소스 위치
        public final MethodInvocationInfo invocationInfo;
        public final ResolvedChain childChain;

        public Step(Kind kind,
                    String symbol,
                    TypeInfo typeInfo,
                    boolean nullable,
                    TextChangeRange range,
                    MethodInvocationInfo invocationInfo,
                    ResolvedChain childChain) {
            this.kind = kind;
            this.symbol = symbol;
            this.typeInfo = typeInfo;
            this.nullable = nullable;
            this.range = range;
            this.invocationInfo = invocationInfo;
            this.childChain = childChain;
        }

        @Override
        public String toString() {
            return "Step{" +
                    "kind=" + kind +
                    ", symbol=" + symbol +
                    ", typeInfo=" + typeInfo +
                    ", nullable=" + nullable +
                    ", range=" + range +
                    ", invocationInfo=" + invocationInfo +
                    ", childChain=" + childChain +
                    '}';
        }
    }

    private final List<Step> steps = new ArrayList<>();

    public void addStep(Step step) {
        steps.add(step);
    }

    public List<Step> getSteps() {
        return steps;
    }

    public Step last() {
        return steps.isEmpty() ? null : steps.get(steps.size() - 1);
    }

    public StepCursor stepCursor() {
        return new StepCursor(steps);
    }

    @Override
    public String toString() {
        return "ResolvedChain{" +
                "steps=" + steps +
                '}';
    }
}