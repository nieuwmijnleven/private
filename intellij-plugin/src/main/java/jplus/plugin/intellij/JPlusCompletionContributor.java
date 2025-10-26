package jplus.plugin.intellij;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class JPlusCompletionContributor extends CompletionContributor {

    public JPlusCompletionContributor() {
        extend(CompletionType.BASIC, JPlusPatterns.psiElement(), new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters,
                                          @NotNull ProcessingContext context,
                                          @NotNull CompletionResultSet result) {
                Project project = parameters.getPosition().getProject();
                PsiElement jplusElement = parameters.getPosition();

                // 1️⃣ JPlus PSI 기반 후보 수집
                result.addElement(LookupElementBuilder.create("jplusLocalCandidate"));

                // 2️⃣ 필요 시 Java PSI 생성
                PsiFile javaPsiFile = createJavaPsiFromJPlus(project, jplusElement.getContainingFile());
                if (javaPsiFile != null) {
                    collectJavaPsiCandidates(javaPsiFile, result);
                }
            }
        });
    }

    // 임시 Java PSI 생성
    private PsiFile createJavaPsiFromJPlus(Project project, PsiFile jplusFile) {
//        String javaText = JPlusToJavaConverter.convert(jplusFile.getText()); // 변환 로직
        String javaText = jplusFile.getText(); // 변환 로직
        if (javaText.isEmpty()) return null;

        return PsiFileFactory.getInstance(project)
                .createFileFromText("Temp.java", JavaFileType.INSTANCE, javaText);
    }

    // Java PSI 기반 후보 수집
    private void collectJavaPsiCandidates(PsiFile javaPsiFile, CompletionResultSet result) {
        javaPsiFile.accept(new JavaRecursiveElementWalkingVisitor() {
            @Override
            public void visitMethod(PsiMethod method) {
                result.addElement(LookupElementBuilder.create(method.getName()));
            }

            @Override
            public void visitField(PsiField field) {
                result.addElement(LookupElementBuilder.create(field.getName()));
            }
        });
    }
}
