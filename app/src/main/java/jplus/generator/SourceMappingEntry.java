package jplus.generator;

public class SourceMappingEntry {
    private TextChangeRange originalRange;
    private TextChangeRange transformedRange;

    public SourceMappingEntry(TextChangeRange originalRange, TextChangeRange transformedRange) {
        this.originalRange = originalRange;
        this.transformedRange = transformedRange;
    }

    public TextChangeRange getOriginalRange() {
        return originalRange;
    }

    public void setOriginalRange(TextChangeRange originalRange) {
        this.originalRange = originalRange;
    }

    public TextChangeRange getTransformedRange() {
        return transformedRange;
    }

    public void setTransformedRange(TextChangeRange transformedRange) {
        this.transformedRange = transformedRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceMappingEntry sourceMappingEntry = (SourceMappingEntry) o;
        return java.util.Objects.equals(originalRange, sourceMappingEntry.originalRange)
            && java.util.Objects.equals(transformedRange, sourceMappingEntry.transformedRange);
    }
    
    @Override
    public int hashCode() {
        return java.util.Objects.hash(originalRange, transformedRange);
    }

    @Override
    public String toString() {
        return originalRange + " -> " + transformedRange;
    }
}