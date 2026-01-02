/*
 * Copyright 2025 Cheol Jeon <nieuwmijnleven@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jplus.generator;

import jplus.editor.FragmentedText;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class CodeGenContext {
    private static final ThreadLocal<Deque<CodeGenContext>> stackLocal = ThreadLocal.withInitial(ArrayDeque::new);

    private FragmentedText fragmentedText;

    private final Set<SourceMappingEntry> sourceMappingEntrySet = new HashSet<>();

    private boolean semanticMode = false;

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

    public boolean isSemanticMode() {
        return semanticMode;
    }

    public void setSemanticMode(boolean semanticMode) {
        this.semanticMode = semanticMode;
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
