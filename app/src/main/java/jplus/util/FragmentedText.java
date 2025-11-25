package jplus.util;

import jplus.generator.SourceMappingEntry;
import jplus.generator.TextChangeRange;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FragmentedText {
    private final String original;
    private final TextChangeRange originalTextChangeRange;
    private final List<TextFragmentNode> fragmentedNodeList;

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
    }

    public FragmentedText(String original) {
        this(Utils.computeTextChangeRange(original, 0, original.length()-1), original);
    }

    public void update(TextChangeRange textChangeRange, String replace) {
        int affectedRangeCount = 0;
        for (int i = 0; i < fragmentedNodeList.size(); ++i) {
            TextFragmentNode node = fragmentedNodeList.get(i);
            TextChangeRange nodeRange = node.originalRange;

            if (!textChangeRange.overlaps(nodeRange)) {
                continue;
            }

            if (textChangeRange.equals(nodeRange)) {
                node.string = replace;
                ++affectedRangeCount;
                break;
            }

//            if (node.rangeFixed) {
//                throw new IllegalArgumentException("Cannot partially overwrite a fixed range: " + nodeRange);
//            }

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
                ++i;
            }

            TextFragmentNode priorNode = TextFragmentNode.copyFrom(node);
            node.originalRange = Utils.getRangeFromStartIndexAndEndIndex(this.original, this.originalTextChangeRange, overlapStart, overlapEnd);
            node.string = replace;
            node.rangeFixed = true;
            node.prior = priorNode;

            if (node.originalRange.contains(priorNode.originalRange) && !node.string.contains(priorNode.string)) {
                throw new IllegalStateException("A new change must include the prior changes.");
            }

            ++affectedRangeCount;
        }

        if (affectedRangeCount == 0) {
            throw new IllegalStateException("originalRange = " + this.originalTextChangeRange + ", updateRange = " + textChangeRange + ", " + replace + " " + "No nodes were affected by the given range." + toString());
        }
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

            mapping.add(new SourceMappingEntry(
                    node.originalRange,
                    new TextChangeRange(
                            node.transformedStartLine,
                            node.transformedStartCol,
                            node.transformedEndLine,
                            node.transformedEndCol
                    )
            ));
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

//        StringBuilder sb = new StringBuilder();
//        System.err.println("fragmentedNodeList.size() = " + fragmentedNodeList.size());
//        fragmentedNodeList.forEach(textFragmentNode -> {
//            sb.append(textFragmentNode.string);
//        });
//        return sb.toString();
    }
}
