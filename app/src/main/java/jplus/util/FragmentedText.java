package jplus.util;

import jplus.generator.SourceMappingEntry;
import jplus.generator.TextChangeRange;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FragmentedText {
    private final String original;
    private final TextChangeRange originalTextChangeRange;
    private final List<TextFragmentNode> fragmentedNodeList;
    private final List<TextFragmentNode> unchangedRangeList;
    private final List<TextFragmentNode> deletedFragmentedNodeList;

    static class TextFragmentNode {
        TextChangeRange originalRange;
        String string;
        boolean rangeFixed;
        TextFragmentNode prior;

        int transformedStartLine;
        int transformedStartCol;
        int transformedEndLine;
        int transformedEndCol;

        public TextFragmentNode(TextChangeRange originalRange, String string, boolean rangeFixed, TextFragmentNode prior) {
            this.originalRange = originalRange;
            this.string = string;
            this.rangeFixed = rangeFixed;
            this.prior = prior;
        }

        public static TextFragmentNode copyFrom(final TextFragmentNode node) {
            TextChangeRange originalRange = TextChangeRange.copyFrom(node.originalRange);
            String string = node.string;
            boolean rangeFixed = node.rangeFixed;
            TextFragmentNode prior = node.prior;
            return new TextFragmentNode(originalRange, string, rangeFixed, prior);
        }

        @Override
        public String toString() {
            return "TextFragmentNode{" +
                    "originalRange=" + originalRange +
                    ", string='" + string + '\'' +
                    ", rangeFixed=" + rangeFixed +
                    '}';
        }
    }

    public FragmentedText(TextChangeRange range, String original) {
        this.original = original;
        this.originalTextChangeRange = range;
        this.fragmentedNodeList = new LinkedList<>();
        this.fragmentedNodeList.add(new TextFragmentNode(range, original, false, null));
        this.unchangedRangeList = new ArrayList<>();
        this.deletedFragmentedNodeList = new ArrayList<>();
    }

    public FragmentedText(String original) {
        this(Utils.computeTextChangeRange(original, 0, original.length()-1), original);
    }

    public String getOriginalText() {
        return this.original;
    }

    public Optional<String> findFragmentedTextByRange(TextChangeRange range) {
        System.err.println("[findFragmentedText] range = " + range);
        List<TextFragmentNode> allFragmentNodeList = new ArrayList<>();
        allFragmentNodeList.addAll(fragmentedNodeList);
        allFragmentNodeList.addAll(unchangedRangeList);

        for (TextFragmentNode textFragmentNode : allFragmentNodeList) {
            System.err.println("[findFragmentedText] originalRange = " + textFragmentNode.originalRange);
            if (range.equals(textFragmentNode.originalRange)) {
                return Optional.of(textFragmentNode.string);
            }

            TextFragmentNode prior = textFragmentNode.prior;
            while (prior != null) {
                System.err.println("[findFragmentedText] prior = " + prior.originalRange);
                if (range.equals(prior.originalRange)) {
                    return Optional.of(prior.string);
                }
                prior = prior.prior;
            }
        }
        return Optional.empty();
    }

    public String add(TextChangeRange textChangeRange, String string) {
        FragmentedText fragmentedText = new FragmentedText(textChangeRange, string);
        for (TextFragmentNode textFragmentNode : fragmentedNodeList) {
            if (textChangeRange.contains(textFragmentNode.originalRange) && !string.contains(textFragmentNode.string)) {
                fragmentedText.update(textFragmentNode.originalRange, textFragmentNode.string);
            }
        }

        String updated = fragmentedText.toString();
        this.unchangedRangeList.add(new TextFragmentNode(textChangeRange, updated, true, null));
        return updated;
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
                fragmentedNodeList.add(i, new TextFragmentNode(headRange, head, false, null));
                ++i;
            }

            if (overlapEnd < nodeEnd) {
                TextChangeRange tailRange = Utils.getRangeFromStartIndexAndEndIndex(this.original, this.originalTextChangeRange, overlapEnd + 1, nodeEnd);
                String tail = node.string.substring(relEnd);
                fragmentedNodeList.add(i + 1, new TextFragmentNode(tailRange, tail, false, null));
            }

            TextFragmentNode priorNode = TextFragmentNode.copyFrom(node);
            node.originalRange = Utils.getRangeFromStartIndexAndEndIndex(this.original, this.originalTextChangeRange, overlapStart, overlapEnd);
            node.string = node.string.substring(relStart, relEnd);
            node.rangeFixed = false;
            node.prior = priorNode;
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
                System.err.println("nodeRange = " + nodeRange);
                if (!replaced) {
                    TextFragmentNode priorNode = TextFragmentNode.copyFrom(node);
                    priorNode.prior = node.prior;

                    node.originalRange = textChangeRange;
                    node.string = replace;
                    node.rangeFixed = true;
                    node.prior = priorNode;
                    ++affectedRangeCount;
                    replaced = true;
                } else {
                    TextFragmentNode removed = fragmentedNodeList.remove(i);
                    deletedFragmentedNodeList.add(removed);
                    --i;
                }
                continue;
            }

//            if (node.rangeFixed) {
//                throw new IllegalArgumentException("Cannot partially overwrite a fixed range: " + nodeRange);
//            }
        }

        if (affectedRangeCount == 0) {
            throw new IllegalStateException("originalRange = " + this.originalTextChangeRange + ", updateRange = " + textChangeRange + ", " + replace + " " + "No nodes were affected by the given range." + toString());
        }
    }

    private SourceMappingEntry buildSourceMapForPrior(TextFragmentNode prior, SourceMappingEntry entry) {
        //skip
        int skipOffset = entry.getSource().indexOf(prior.string);
        if (skipOffset == -1) {
            throw new IllegalStateException("The recent change must contain the prior chages.");
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
        Set<SourceMappingEntry> mapping = new HashSet<>();

        int currentLine = originalTextChangeRange.startLine();
        int currentCol = originalTextChangeRange.startIndex();

        for (TextFragmentNode node : fragmentedNodeList) {
            node.transformedStartLine = currentLine;
            node.transformedStartCol = currentCol;

            for (char c : node.string.toCharArray()) {
                if (c == '\n') {
                    currentLine++;
                    currentCol = 0;
                } else {
                    currentCol++;
                }
            }

            node.transformedEndLine = currentLine;
            node.transformedEndCol = currentCol;

            SourceMappingEntry entry = new SourceMappingEntry(
                                            node.string,
                                            node.originalRange,
                                            new TextChangeRange(
                                                node.transformedStartLine,
                                                node.transformedStartCol,
                                                node.transformedEndLine,
                                                node.transformedEndCol
                                            ));

            mapping.add(entry);

            TextFragmentNode prior = node.prior;
            while(prior != null) {
                if (prior.rangeFixed) mapping.add(buildSourceMapForPrior(prior, entry));
                prior = prior.prior;
            }

            List<TextFragmentNode> remainderList = new ArrayList();
            remainderList.addAll(unchangedRangeList);
            remainderList.addAll(deletedFragmentedNodeList);

            for (TextFragmentNode remainNode : remainderList) {
                if (node.originalRange.contains(remainNode.originalRange) && remainNode.rangeFixed) {
                    System.err.println("node = " + node.string);
                    System.err.println("unchanged = " + remainNode.string);
                    mapping.add(buildSourceMapForPrior(remainNode, entry));
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
