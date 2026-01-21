package jplus.plugin.intellij.annotator;

import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import jplus.analyzer.nullability.NullabilityChecker;
import jplus.plugin.intellij.JPlusFile;
import jplus.processor.JPlusProcessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JPlusExternalAnnotator
        extends ExternalAnnotator<JPlusAnnotationInput, JPlusAnnotationResult> {

    @Override
    public @Nullable JPlusAnnotationInput collectInformation(
            @NotNull PsiFile file
    ) {
        if (!(file instanceof JPlusFile)) return null;

        Project ideaProject = file.getProject();

        jplus.base.Project jplusProject = JPlusIntelliJProjectUtil.buildJPlusProject(ideaProject);
        jplusProject = jplusProject.withJavaClassPathEntry(resolveJSpecifyJarPath());
        jplusProject.setJdkHome(getJdkHome(ideaProject));

        String className = file.getVirtualFile().getNameWithoutExtension();

        String packageName = JPlusIntelliJProjectUtil.resolvePackageName(ideaProject, file);

        return new JPlusAnnotationInput(
                file.getText(),
                packageName,
                className,
                jplusProject
        );
    }

    private @Nullable String getJdkHome(Project ideaProject) {
        Sdk sdk = ProjectRootManager.getInstance(ideaProject).getProjectSdk();
        return sdk.getHomePath();
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
            return null; // 분석 실패 → IDE 크래시 방지
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
                            com.intellij.lang.annotation.HighlightSeverity.ERROR,
                            issue.message()
                    )
                    .range(TextRange.create(issue.offset(), issue.offset()))
                    .create();
        }
    }

    private static Path resolveJSpecifyJarPath() {
        return Path.of(PathManager.getPluginsPath())
                .resolve("intellij-plugin")
                .resolve("lib/jspecify-1.0.0.jar");
    }
}
