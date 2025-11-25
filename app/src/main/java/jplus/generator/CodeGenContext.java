package jplus.generator;

import jplus.util.FragmentedText;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CodeGenContext {
    private static final ThreadLocal<CodeGenContext> threadLocal =
            ThreadLocal.withInitial(CodeGenContext::new);

    private FragmentedText fragmentedText;

    private final Set<SourceMappingEntry> sourceMappingEntrySet = new HashSet<>();

    private CodeGenContext() {}

    public static CodeGenContext current() {
        return threadLocal.get();
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
