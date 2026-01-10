package jplus.analyzer;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class StepCursor {

    private static final StepCursor EMPTY = new StepCursor(Collections.emptyList());

    private final Deque<CursorState> stack = new ArrayDeque<>();
    private ResolvedChain.Step lastConsumed = null;

    private static final class CursorState {
        final List<ResolvedChain.Step> steps;
        int index = 0;

        CursorState(List<ResolvedChain.Step> steps) {
            this.steps = steps;
        }

        boolean hasNext() {
            return index < steps.size();
        }

        ResolvedChain.Step peek() {
            return steps.get(index);
        }

        ResolvedChain.Step next() {
            return steps.get(index++);
        }
    }

    public StepCursor(List<ResolvedChain.Step> steps) {
        Objects.requireNonNull(steps);
        stack.push(new CursorState(steps));
    }

    public static StepCursor empty() {
        return EMPTY;
    }

    public boolean hasNext() {
        skipChainSteps();
        return !stack.isEmpty();
    }

    public Optional<ResolvedChain.Step> peek() {
        skipChainSteps();
        if (stack.isEmpty()) return Optional.empty();
        return Optional.of(stack.peek().peek());
    }

    public Optional<ResolvedChain.Step> peekPrev() {
        return Optional.ofNullable(lastConsumed);
    }

    public ResolvedChain.Step consume() {
        skipChainSteps();
        if (stack.isEmpty()) throw new IllegalStateException("No more steps");

        CursorState current = stack.peek();
        ResolvedChain.Step step = current.next();

        // CHAIN step은 반환하지 않고 childChain 탐색만
        if (step.kind == ResolvedChain.Kind.CHAIN) {
            if (step.childChain != null && !step.childChain.getSteps().isEmpty()) {
                stack.push(new CursorState(step.childChain.getSteps()));
            }
            return consume(); // 재귀 호출로 다음 실제 step 반환
        }

        lastConsumed = step;

        // childChain이 있어도 CHAIN 타입이 아니면 그대로 탐색
        if (step.childChain != null && !step.childChain.getSteps().isEmpty()) {
            stack.push(new CursorState(step.childChain.getSteps()));
        }

        return step;
    }

    public int position() {
        int pos = 0;
        for (CursorState s : stack) pos += s.index;
        return pos;
    }

    // CHAIN 타입 step을 peek/consume에서 건너뛰기
    private void skipChainSteps() {
        while (!stack.isEmpty()) {
            CursorState current = stack.peek();
            while (current.hasNext() && current.peek().kind == ResolvedChain.Kind.CHAIN) {
                ResolvedChain.Step chainStep = current.next();
                if (chainStep.childChain != null && !chainStep.childChain.getSteps().isEmpty()) {
                    stack.push(new CursorState(chainStep.childChain.getSteps()));
                }
            }
            if (!current.hasNext()) stack.pop();
            else break;
        }
    }
}
