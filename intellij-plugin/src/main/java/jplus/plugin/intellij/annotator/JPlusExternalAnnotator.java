package jplus.plugin.intellij.annotator;

import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import jplus.analyzer.nullability.NullabilityChecker;
import jplus.plugin.intellij.JPlusFile;
import jplus.processor.JPlusProcessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JPlusExternalAnnotator
        extends ExternalAnnotator<JPlusAnnotationInput, JPlusAnnotationResult> {

    @Override
    public @Nullable JPlusAnnotationInput collectInformation(
            @NotNull PsiFile file
    ) {
        if (!(file instanceof JPlusFile)) return null;

        Project ideaProject = file.getProject();

        Module module = ModuleUtilCore.findModuleForFile(file.getVirtualFile(), ideaProject);
        jplus.base.Project jplusProject = JPlusIntelliJProjectUtil.buildJPlusProject(ideaProject, module);

        String className = file.getVirtualFile().getNameWithoutExtension();

        String packageName = JPlusIntelliJProjectUtil.resolvePackageName(ideaProject, file);

        return new JPlusAnnotationInput(
                file.getText(),
                packageName,
                className,
                jplusProject
        );
    }

    @Override
    public @Nullable JPlusAnnotationResult doAnnotate(
            JPlusAnnotationInput input
    ) {
        try {
            JPlusProcessor processor =
                    new JPlusProcessor(
                            input.project(),
                            input.packageName(),
                            input.className()
                    );

            processor.process();
            processor.analyzeSymbols();

            List<NullabilityChecker.NullabilityIssue> issues =
                    processor.checkNullability();

            return new JPlusAnnotationResult(issues);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void apply(
            @NotNull PsiFile file,
            JPlusAnnotationResult result,
            @NotNull com.intellij.lang.annotation.AnnotationHolder holder
    ) {
        if (result == null) return;

        for (NullabilityChecker.NullabilityIssue issue : result.issues()) {
            holder.newAnnotation(
                            HighlightSeverity.WARNING,
                            issue.message()
                    )
                    .range(TextRange.create(issue.offset(), issue.offset()))
                    .create();
        }
    }
}
