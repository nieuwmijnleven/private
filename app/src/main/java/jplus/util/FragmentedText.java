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

package jplus.util;

import jplus.generator.SourceMappingEntry;
import jplus.generator.TextChangeRange;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class FragmentedText {
    private final String original;
    private final TextChangeRange originalRange;
    private final List<Fragment> fragments = new ArrayList<>();

    static final class Fragment {
        TextChangeRange range;
        String text;
        boolean fixed;
        List<Fragment> priors;

        Fragment(TextChangeRange range, String text, boolean fixed, List<Fragment> priors) {
            this.range = Objects.requireNonNull(range);
            this.text = Objects.requireNonNull(text);
            this.fixed = fixed;
            this.priors = new ArrayList<>(
                    Objects.requireNonNull(priors)
            );
        }

        Fragment(TextChangeRange range, String text, boolean fixed) {
            this(range, text, fixed, List.of());
        }

        static Fragment copyFrom(final Fragment node) {
            TextChangeRange originalRange = TextChangeRange.copyFrom(node.range);
            String string = node.text;
            boolean rangeFixed = node.fixed;
            List<Fragment> priors = node.priors;
            return new Fragment(originalRange, string, rangeFixed, priors);
        }

        @Override
        public String toString() {
            return "TextFragmentNode{" +
                    "originalRange=" + range +
                    ", string='" + text + '\'' +
                    ", rangeFixed=" + fixed +
                    ", priors=" + priors +
                    '}';
        }
    }

    public FragmentedText(TextChangeRange range, String original) {
        this.original = original;
        this.originalRange = range;
        this.fragments.add(new Fragment(range, original, false));
    }

    public FragmentedText(String original) {
        this(Utils.computeTextChangeRange(original, 0, original.length() - 1), original);
    }

    public String getOriginalText() {
        return this.original;
    }

    public Optional<String> findFragmentByRange(TextChangeRange range) {
        //System.err.println("[findFragmentedText] range = " + range);

        for (Fragment f : fragments) {
            //System.err.println("[findFragmentedText] originalRange = " + textFragmentNode.originalRange);
            Optional<String> found = find(f, range);
            if (found.isPresent()) return found;
        }
        return Optional.empty();
    }

    private Optional<String> find(Fragment f, TextChangeRange range) {
        if (range.equals(f.range)) {
            return Optional.of(f.text);
        }

        Deque<Fragment> deque = new LinkedList<>();
        deque.addAll(f.priors);
        while (!deque.isEmpty()) {
            Fragment node = deque.removeFirst();
//                System.err.println("[findFragmentedText] prior = " + node.originalRange);
            if (range.equals(node.range)) {
                return Optional.of(node.text);
            }
            deque.addAll(node.priors);
        }
        return Optional.empty();
    }

    public String projectOn(TextChangeRange textChangeRange, String string) {
        FragmentedText temp = new FragmentedText(textChangeRange, string);
        for (Fragment fragment : fragments) {
            if (textChangeRange.contains(fragment.range)) {
                temp.update(fragment.range, fragment.text);
            }
        }
        return temp.toString();
    }

    public void update(TextChangeRange textChangeRange, String replace) {
        splitOverlappingFragment(textChangeRange);
        replace(textChangeRange, replace);
    }

    //split overlap range
    private void splitOverlappingFragment(TextChangeRange target) {
        for (int i = 0; i < fragments.size(); ++i) {
            Fragment f = fragments.get(i);

            if (target.equals(f.range)) {
                break;
            }

            if (!target.overlaps(f.range) || f.fixed) {
                continue;
            }

            int nodeStart = toIndex(f.range.startLine(), f.range.startIndex());
            int nodeEnd = toIndex(f.range.endLine(), f.range.inclusiveEndIndex());

            int rangeStart = toIndex(target.startLine(), target.startIndex());
            int rangeEnd = toIndex(target.endLine(), target.inclusiveEndIndex());

            int overlapStart = Math.max(rangeStart, nodeStart);
            int overlapEnd = Math.min(rangeEnd, nodeEnd);

            int relStart = overlapStart - nodeStart;
            int relEnd = overlapEnd - nodeStart + 1;

            if (relStart > 0) {
                fragments.add(i++, new Fragment(rangeFrom(nodeStart, overlapStart - 1), f.text.substring(0, relStart), false));
            }

            if (relEnd < f.text.length()) {
                fragments.add(i + 1, new Fragment(rangeFrom(overlapEnd + 1, nodeEnd), f.text.substring(relEnd), false));
            }

            Fragment priorNode = Fragment.copyFrom(f);
            f.range = rangeFrom(overlapStart, overlapEnd);
            f.text = f.text.substring(relStart, relEnd);
            f.fixed = false;
            f.priors = new ArrayList<>(List.of(priorNode));
        }
    }

    private TextChangeRange rangeFrom(int start, int end) {
        return Utils.getRangeFromStartIndexAndEndIndex(this.original, this.originalRange, start, end);
    }

    private int toIndex(int line, int col) {
        return Utils.getIndexFromLineColumn(this.original, this.originalRange, line, col);
    }

    private void replace(TextChangeRange range, String replacement) {
        boolean replaced = false;

        for (int i = 0; i < fragments.size(); ++i) {
            Fragment f = fragments.get(i);

            if (!range.contains(f.range)) continue;

            if (!replaced) {
                Fragment priorNode = Fragment.copyFrom(f);
                f.range = range;
                f.text = replacement;
                f.fixed = true;
                f.priors = new ArrayList(List.of(priorNode));
                replaced = true;
            } else {
                Fragment prevNode = fragments.get(i - 1);
                prevNode.priors.add(fragments.remove(i--));
            }
        }

        if (!replaced) {
            throw new IllegalStateException("No nodes were affected by the given range:" + range + ", replacement = " + replacement + ", debugString" + debugString());
        }
    }

    private SourceMappingEntry buildSourceMapForPrior( SourceMappingEntry entry, Fragment prior, int skipOffset) {
        int currentLine = entry.getTransformedRange().startLine();
        int currentCol = entry.getTransformedRange().startIndex();

        char[] charArray = entry.getSource().toCharArray();
        for (int offset = 0; offset < skipOffset; ++offset) {
            char c = charArray[offset];
            if (c == '\n') {
                currentLine++;
                currentCol = 0;
            } else {
                currentCol++;
            }
        }

        //calculate text range
        int startLine = currentLine;
        int startCol = currentCol;
        int endLine = startLine;
        int endCol = startCol;

        for (char c : prior.text.toCharArray()) {
            if (c == '\n') {
                currentLine++;
                currentCol = 0;
            } else {
                currentCol++;
            }
        }

        endLine = currentLine;
        endCol = currentCol;

        TextChangeRange newRange = new TextChangeRange(startLine, startCol, endLine, endCol);
        return new SourceMappingEntry(prior.text, prior.range, newRange);
    }

    public Set<SourceMappingEntry> buildSourceMap() {
        System.err.println("[buildSourceMap] debugString() = " + debugString());
        Set<SourceMappingEntry> mapping = new HashSet<>();

        int currentLine = originalRange.startLine();
        int currentCol = originalRange.startIndex();

        for (Fragment f : fragments) {
            int transformedStartLine = currentLine;
            int transformedStartCol = currentCol;

            for (char c : f.text.toCharArray()) {
                if (c == '\n') {
                    currentLine++;
                    currentCol = 0;
                } else {
                    currentCol++;
                }
            }

            int transformedEndLine = currentLine;
            int transformedEndCol = currentCol;

            SourceMappingEntry entry = new SourceMappingEntry(
                    f.text,
                    f.range,
                    new TextChangeRange(
                            transformedStartLine,
                            transformedStartCol,
                            transformedEndLine,
                            transformedEndCol
                    ));
            mapping.add(entry);

            int offset = 0;
            for (Fragment p : f.priors) {
                traversePrior(p, offset, entry, mapping);
                offset += p.text.length();
            }
        }

        return mapping;
    }

    void traversePrior(
            Fragment fragment,
            int offsetInParent,
            SourceMappingEntry entry,
            Set<SourceMappingEntry> mapping
    ) {
        SourceMappingEntry priorEntry = buildSourceMapForPrior(entry, fragment, offsetInParent);
        if (priorEntry != null) mapping.add(priorEntry);

        int childOffset = 0;
        for (Fragment child : fragment.priors) {
            traversePrior(
                    child,
                    offsetInParent + childOffset,
                    entry,
                    mapping
            );
            childOffset += child.text.length();
        }
    }

    public String debugString() {
        StringBuilder sb = new StringBuilder();
        for (Fragment node : fragments) {
            sb.append("[")
                    .append(node.range)
                    .append(node.fixed ? " FIXED" : "")
                    .append("]:\n")
                    .append(node.text)
                    .append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if (fragments.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (Fragment node : fragments) {
            sb.append(node.text);
        }
        return sb.toString();
    }
}
