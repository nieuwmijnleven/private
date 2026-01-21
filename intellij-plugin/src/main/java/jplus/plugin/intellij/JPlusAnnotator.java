package jplus.plugin.intellij;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import jplus.analyzer.nullability.NullabilityChecker;
import jplus.base.Project;
import jplus.processor.JPlusProcessor;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JPlusAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof JPlusFile)) return;

        System.err.println("run annotator = " + element.getClass().getSimpleName());
        PsiFile jplusFile = element.getContainingFile();
        com.intellij.openapi.project.Project ideaProject = jplusFile.getProject();

        Project project = buildJPlusProject(ideaProject);
        String packageName = resolvePackageName(ideaProject, jplusFile);
        String className = jplusFile.getVirtualFile().getNameWithoutExtension();

        System.err.println("project = " + project.getSourceDirs());
        System.err.println("packageName = " + packageName);
        System.err.println("className = " + className);

        //JPlusProcessor processor = new JPlusProcessor(jplusFile.getText());
        //System.err.println("jplusFile.getText() = " + jplusFile.getText());

        try {
            JPlusProcessor processor = new JPlusProcessor(project, packageName, className);

            processor.process();
            processor.analyzeSymbols();

            var issues = processor.checkNullability();
            if (!issues.isEmpty()) {
                System.err.println("nullability warning");
                for (NullabilityChecker.NullabilityIssue issue : issues) {
                    System.out.println(issue);
                    holder.newAnnotation(HighlightSeverity.ERROR, issue.message())
                            .range(new TextRange(issue.offset(), issue.offset()))
                            .create();
                }
            }
        } catch (Exception e) {
            //System.err.println(e.getMessage());
            //throw new RuntimeException(e);
        }
    }

    private static jplus.base.Project buildJPlusProject(com.intellij.openapi.project.Project ideaProject) {
        List<Path> sourceDirs = new ArrayList<>();

        for (VirtualFile root : ProjectRootManager.getInstance(ideaProject)
                .getContentSourceRoots()) {
            sourceDirs.add(Path.of(root.getPath()));
        }

        return new jplus.base.Project(sourceDirs);
    }

    private static String resolvePackageName(
            com.intellij.openapi.project.Project ideaProject,
            PsiFile file
    ) {
        VirtualFile vFile = file.getVirtualFile();
        if (vFile == null) return null;

        VirtualFile sourceRoot =
                ProjectRootManager.getInstance(ideaProject)
                        .getFileIndex()
                        .getSourceRootForFile(vFile);

        if (sourceRoot == null) return null;

        String relativePath =
                vFile.getParent().getPath()
                        .substring(sourceRoot.getPath().length());

        if (relativePath.startsWith("/")) {
            relativePath = relativePath.substring(1);
        }

        if (relativePath.isEmpty()) {
            return null;
        }

        return relativePath.replace('/', '.');
    }


}
