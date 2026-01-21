package jplus.plugin.intellij.annotator;

public record JPlusAnnotationInput(
        String fileText,
        String packageName,
        String className,
        jplus.base.Project project
) {}
