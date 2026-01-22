package jplus.plugin.intellij.annotator;

import com.intellij.execution.CantRunException;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.module.Module;
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
        //jplusProject = jplusProject.withJavaClassPathEntry(resolveJSpecifyJarPath());

//        if (module != null) {
//            JavaParameters params = new JavaParameters();
//            try {
//                params.configureByModule(module, JavaParameters.JDK_AND_CLASSES_AND_PROVIDED);
//                List<String> classPathList = params.getClassPath().getPathList();
//                System.err.println("classPathList = " + classPathList);
//
//                // 2️⃣ JPlusProject에 클래스패스 추가
//                // jplusProject = jplusProject.withJavaClassPathEntry(classPathList);
//
//            } catch (CantRunException e) {
//                e.printStackTrace();
//            }
//        }

        /*Module[] modules = ModuleManager.getInstance(ideaProject).getModules();

        if (modules.length > 0) {
            Module module = modules[0]; // 첫 번째 모듈

            JavaParameters params = new JavaParameters();
            try {
                params.configureByModule(
                        module,
                        JavaParameters.JDK_AND_CLASSES
                );
            } catch (CantRunException e) {
                throw new RuntimeException(e);
            }

            List<String> classPathList = params.getClassPath().getPathList();
            System.err.println("classPathList = " + classPathList);
        }*/


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
            throw new RuntimeException(e); // 분석 실패 → IDE 크래시 방지
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
}
