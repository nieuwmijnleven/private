package jplus.analyzer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class StepCursor {

    private final List<ResolvedChain.Step> steps;
    private int index = 0;

    StepCursor(List<ResolvedChain.Step> steps) {
        this.steps = Objects.requireNonNull(steps);
    }

    public boolean hasNext() {
        return index < steps.size();
    }

    public Optional<ResolvedChain.Step> peek() {
        return hasNext()
                ? Optional.ofNullable(steps.get(index))
                : Optional.empty();
    }

    public ResolvedChain.Step consume() {
        if (!hasNext()) {
            throw new IllegalStateException(
                    "No more steps at index " + index
            );
        }
        return steps.get(index++);
    }

    public int position() {
        return index;
    }
}
