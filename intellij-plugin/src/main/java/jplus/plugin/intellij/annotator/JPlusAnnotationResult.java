package jplus.plugin.intellij.annotator;

import jplus.analyzer.nullability.NullabilityChecker;

import java.util.List;

public record JPlusAnnotationResult(
        List<NullabilityChecker.NullabilityIssue> issues
) {}
