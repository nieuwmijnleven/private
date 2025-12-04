package jplus.generator;

import jplus.util.FragmentedText;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class CodeGenContext {
    private static final ThreadLocal<Deque<CodeGenContext>> stackLocal =
            ThreadLocal.withInitial(ArrayDeque::new);

    private FragmentedText fragmentedText;

    private final Set<SourceMappingEntry> sourceMappingEntrySet = new HashSet<>();

    private CodeGenContext() {}

    public static CodeGenContext current() {
        Deque<CodeGenContext> stack = stackLocal.get();
        return stack.peek(); // 현재 top
    }

    public static void push() {
        stackLocal.get().push(new CodeGenContext());
    }

    public static void pop() {
        stackLocal.get().pop();
    }

    public FragmentedText getFragmentedText() {
        return fragmentedText;
    }

    public void setFragmentedText(FragmentedText fragmentedText) {
        this.fragmentedText = fragmentedText;
    }

    public void addSourceMappingEntry(SourceMappingEntry entry) {
        sourceMappingEntrySet.add(entry);
    }

    public void addSourceMappingEntrySet(Set<SourceMappingEntry> entrySet) {
        sourceMappingEntrySet.addAll(entrySet);
    }

    public Set<SourceMappingEntry> getSourceMapping() {
        return sourceMappingEntrySet;
    }

    public void reset() {
        sourceMappingEntrySet.clear();
    }
}
