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
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class FragmentedText {
    private final String original;
    private final TextChangeRange originalTextChangeRange;
    private final List<TextFragmentNode> fragmentedNodeList;
    //private final List<TextFragmentNode> unchangedRangeList;

    static class TextFragmentNode {
        TextChangeRange originalRange;
        String string;
        boolean rangeFixed;
        List<TextFragmentNode> priors;

        int parentSliceStart;
        int parentSliceEnd;

        public TextFragmentNode(TextChangeRange originalRange, String string, boolean rangeFixed, int parentSliceStart, int parentSliceEnd, List<TextFragmentNode> priors) {
            this.originalRange = originalRange;
            this.string = string;
            this.rangeFixed = rangeFixed;
            this.parentSliceStart = parentSliceStart;
            this.parentSliceEnd = parentSliceEnd;
            this.priors = priors != null ? new ArrayList(priors) : new ArrayList<>();
        }

        public static TextFragmentNode copyFrom(final TextFragmentNode node) {
            TextChangeRange originalRange = TextChangeRange.copyFrom(node.originalRange);
            String string = node.string;
            boolean rangeFixed = node.rangeFixed;
            List<TextFragmentNode> priors = node.priors;
            int parentSliceStart = node.parentSliceStart;
            int parentSliceEnd = node.parentSliceEnd;
            return new TextFragmentNode(originalRange, string, rangeFixed, parentSliceStart, parentSliceEnd, priors);
        }

        @Override
        public String toString() {
            return "TextFragmentNode{" +
                    "originalRange=" + originalRange +
                    ", string='" + string + '\'' +
                    ", rangeFixed=" + rangeFixed +
                    ", priors=" + priors +
                    '}';
        }
    }

    public FragmentedText(TextChangeRange range, String original) {
        this.original = original;
        this.originalTextChangeRange = range;
        this.fragmentedNodeList = new LinkedList<>();
        this.fragmentedNodeList.add(new TextFragmentNode(range, original, false, -1, -1, null));
        //this.unchangedRangeList = new ArrayList<>();
//        this.deletedFragmentedNodeList = new ArrayList<>();
    }

    public FragmentedText(String original) {
        this(Utils.computeTextChangeRange(original, 0, original.length() - 1), original);
    }

    public String getOriginalText() {
        return this.original;
    }

    public Optional<String> findFragmentedTextByRange(TextChangeRange range) {
        //System.err.println("[findFragmentedText] range = " + range);
        List<TextFragmentNode> allFragmentNodeList = new ArrayList<>();
        allFragmentNodeList.addAll(fragmentedNodeList);
        //allFragmentNodeList.addAll(unchangedRangeList);

        for (TextFragmentNode textFragmentNode : allFragmentNodeList) {
//            System.err.println("[findFragmentedText] originalRange = " + textFragmentNode.originalRange);
            if (range.equals(textFragmentNode.originalRange)) {
                return Optional.of(textFragmentNode.string);
            }

//            List<TextFragmentNode> priors = textFragmentNode.priors;
            Deque<TextFragmentNode> deque = new LinkedList<>();
            deque.addAll(textFragmentNode.priors);
            while (!deque.isEmpty()) {
                TextFragmentNode node = deque.removeFirst();
//                System.err.println("[findFragmentedText] prior = " + node.originalRange);
                if (range.equals(node.originalRange)) {
                    return Optional.of(node.string);
                }
                deque.addAll(node.priors);
            }
        }
        return Optional.empty();
    }

    public String projectOn(TextChangeRange textChangeRange, String string) {
        FragmentedText temp = new FragmentedText(textChangeRange, string);
        for (TextFragmentNode textFragmentNode : fragmentedNodeList) {
            if (textChangeRange.contains(textFragmentNode.originalRange)) {
                //System.err.println("textFragmentNode.string = " + textFragmentNode.string);
                //System.err.println("string = " + string);
                temp.update(textFragmentNode.originalRange, textFragmentNode.string);
            }
        }
        return temp.toString();
    }

    public void update(TextChangeRange textChangeRange, String replace) {
        split(textChangeRange);
        replace(textChangeRange, replace);
    }

    //split overlap range
    private void split(TextChangeRange textChangeRange) {
        for (int i = 0; i < fragmentedNodeList.size(); ++i) {
            TextFragmentNode node = fragmentedNodeList.get(i);
            TextChangeRange nodeRange = node.originalRange;

            if (!textChangeRange.overlaps(nodeRange)) {
                continue;
            }

            if (textChangeRange.equals(nodeRange)) {
                break;
            }

            if (node.rangeFixed) {
//                throw new IllegalArgumentException("Cannot partially overwrite a fixed range: " + nodeRange);
                continue;
            }

            int nodeStart = Utils.getIndexFromLineColumn(this.original, this.originalTextChangeRange, nodeRange.startLine(), nodeRange.startIndex());
            int nodeEnd = Utils.getIndexFromLineColumn(this.original, this.originalTextChangeRange, nodeRange.endLine(), nodeRange.inclusiveEndIndex());

            int rangeStart = Utils.getIndexFromLineColumn(this.original, this.originalTextChangeRange, textChangeRange.startLine(), textChangeRange.startIndex());
            int rangeEnd = Utils.getIndexFromLineColumn(this.original, this.originalTextChangeRange, textChangeRange.endLine(), textChangeRange.inclusiveEndIndex());

            int overlapStart = Math.max(rangeStart, nodeStart);
            int overlapEnd = Math.min(rangeEnd, nodeEnd);

            int relStart = overlapStart - nodeStart;
            int relEnd = overlapEnd - nodeStart + 1;

            if (overlapStart > nodeStart) {
                TextChangeRange headRange = Utils.getRangeFromStartIndexAndEndIndex(this.original, this.originalTextChangeRange, nodeStart, overlapStart - 1);
                String head = node.string.substring(0, relStart);
                fragmentedNodeList.add(i, new TextFragmentNode(headRange, head, false, 0, relStart - 1, null));
                ++i;
            }

            if (overlapEnd < nodeEnd) {
                TextChangeRange tailRange = Utils.getRangeFromStartIndexAndEndIndex(this.original, this.originalTextChangeRange, overlapEnd + 1, nodeEnd);
                String tail = node.string.substring(relEnd);
                fragmentedNodeList.add(i + 1, new TextFragmentNode(tailRange, tail, false, relEnd, node.string.length() - 1, null));
            }

            TextFragmentNode priorNode = TextFragmentNode.copyFrom(node);
            node.originalRange = Utils.getRangeFromStartIndexAndEndIndex(this.original, this.originalTextChangeRange, overlapStart, overlapEnd);
            node.string = node.string.substring(relStart, relEnd);
            node.rangeFixed = false;
            node.parentSliceStart = relStart;
            node.parentSliceEnd = relEnd - 1;
            node.priors = new ArrayList<>(List.of(priorNode));
        }
    }

    private void replace(TextChangeRange textChangeRange, String replace) {
        int affectedRangeCount = 0;
        boolean replaced = false;
        for (int i = 0; i < fragmentedNodeList.size(); ++i) {
            TextFragmentNode node = fragmentedNodeList.get(i);
            TextChangeRange nodeRange = node.originalRange;

            if (!textChangeRange.overlaps(nodeRange)) {
                continue;
            }

            if (textChangeRange.contains(nodeRange)) {
//                System.err.println("nodeRange = " + nodeRange);
                if (!replaced) {
                    TextFragmentNode priorNode = TextFragmentNode.copyFrom(node);
                    node.originalRange = textChangeRange;
                    node.string = replace;
                    node.rangeFixed = true;
                    node.priors = new ArrayList(List.of(priorNode));
                    ++affectedRangeCount;
                    replaced = true;

                    priorNode.parentSliceStart = 0;
                    priorNode.parentSliceEnd = priorNode.string.length() - 1;
                } else {
                    TextFragmentNode prevNode = fragmentedNodeList.get(i - 1);
                    TextFragmentNode removed = fragmentedNodeList.remove(i);

                    removed.parentSliceStart = 0;
                    removed.parentSliceEnd = removed.string.length() - 1;

                    prevNode.priors.add(removed);
                    --i;
                }
                continue;
            }

            //if (node.string.contains(replace)) ++affectedRangeCount;
        }

        if (affectedRangeCount == 0) {
            throw new IllegalStateException("No nodes were affected by the given range." + "\ntextChangeRange = " + textChangeRange + ", replace = " + replace + ", debugString" + debugString());
        }
    }

    private SourceMappingEntry buildSourceMapForPrior(TextFragmentNode prior, SourceMappingEntry entry, int skipOffset) {
        //skip
        if (skipOffset == -1) {
            System.err.println("[buildSourceMapForPrior] skipOffset = " + skipOffset);
            System.err.println("entry.getSource() = " + entry.getSource());
            System.err.println("prior.string = " + prior.string);
//            throw new IllegalStateException("The recent change must contain the prior chages.");
            return null;
        }

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

        for (char c : prior.string.toCharArray()) {
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
        return new SourceMappingEntry(prior.string, prior.originalRange, newRange);
    }

    public Set<SourceMappingEntry> buildSourceMap() {
        System.err.println("[buildSourceMap] debugString() = " + debugString());
        Set<SourceMappingEntry> mapping = new HashSet<>();

        int currentLine = originalTextChangeRange.startLine();
        int currentCol = originalTextChangeRange.startIndex();

        for (var node : fragmentedNodeList) {
            int transformedStartLine = currentLine;
            int transformedStartCol = currentCol;

            for (char c : node.string.toCharArray()) {
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
                    node.string,
                    node.originalRange,
                    new TextChangeRange(
                            transformedStartLine,
                            transformedStartCol,
                            transformedEndLine,
                            transformedEndCol
                    ));
            mapping.add(entry);

            Deque<Map.Entry<TextFragmentNode, Integer>> stack = new LinkedList<>();
            int offset = 0;
            for (TextFragmentNode prior : node.priors) {
                stack.push(Map.entry(prior, offset));
                offset += prior.string.length(); // 누적 offset
            }

            while (!stack.isEmpty()) {
                var pair = stack.pop();
                TextFragmentNode priorNode = pair.getKey();
                int accumulatedOffset = pair.getValue();

                SourceMappingEntry priorEntry = buildSourceMapForPrior(priorNode, entry, accumulatedOffset);
                if (priorEntry != null) mapping.add(priorEntry);

                // priorNode의 priors도 누적 offset으로 DFS
                int childOffset = 0;
                for (TextFragmentNode child : priorNode.priors) {
                    stack.push(Map.entry(child, accumulatedOffset + childOffset));
                    childOffset += child.string.length();
                }
            }
        }

        return mapping;
    }

    public String debugString() {
        StringBuilder sb = new StringBuilder();
        for (TextFragmentNode node : fragmentedNodeList) {
            sb.append("[")
                    .append(node.originalRange)
                    .append(node.rangeFixed ? " FIXED" : "")
                    .append("]:\n")
                    .append(node.string)
                    .append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if (fragmentedNodeList.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (TextFragmentNode node : fragmentedNodeList) {
            sb.append(node.string);
        }
        return sb.toString();
    }
}
