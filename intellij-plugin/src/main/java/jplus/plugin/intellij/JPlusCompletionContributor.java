package jplus.plugin.intellij;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.completion.JavaCompletionContributor;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiMethod;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class JPlusCompletionContributor extends CompletionContributor {

    public JPlusCompletionContributor() {
        extend(CompletionType.SMART, JPlusPatterns.psiElement(), new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters,
                                          @NotNull ProcessingContext context,
                                          @NotNull CompletionResultSet result) {
                Project project = parameters.getPosition().getProject();
                PsiElement jplusElement = parameters.getPosition();

                PsiFile javaPsiFile = createJavaPsiFromJPlus(project, jplusElement.getContainingFile());
                if (javaPsiFile != null) {
                    //collectJavaPsiCandidates(javaPsiFile, result);
                    invokeJavaCompletion(javaPsiFile, parameters, result);
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
        javaPsiFile.accept(new JavaElementVisitor() {
            @Override
            public void visitMethod(PsiMethod method) {
                result.addElement(LookupElementBuilder.create(method.getName()));
            }

            @Override
            public void visitField(PsiField field) {
                result.addElement(LookupElementBuilder.create(field.getName()));
            }

            @Override
            public void visitClass(PsiClass aClass) {
                result.addElement(LookupElementBuilder.create(aClass.getName()));
                super.visitClass(aClass);
            }
        });

//        // Java SDK 클래스 추가 후보 (System.out 등)
//        JavaPsiFacade facade = JavaPsiFacade.getInstance(javaPsiFile.getProject());
//        PsiClass systemClass = facade.findClass("java.lang.System", javaPsiFile.getResolveScope());
//        if (systemClass != null) {
//            for (PsiMethod method : systemClass.getMethods()) {
//                result.addElement(LookupElementBuilder.create("System." + method.getName()));
//            }
//        }
    }

    private void invokeJavaCompletion(PsiFile javaPsiFile,
                                      CompletionParameters originalParameters,
                                      CompletionResultSet result) {
        PsiElement javaElementAtCursor = javaPsiFile.findElementAt(originalParameters.getOffset());
        CompletionParameters javaParams = originalParameters.withPosition(javaElementAtCursor, originalParameters.getOffset());

        JavaCompletionContributor javaContributor = new JavaCompletionContributor();
        javaContributor.fillCompletionVariants(javaParams, result);
//        javaContributor.fillCompletionVariants(javaParams, new CompletionResultSet(result) {
//            @Override
//            public void addElement(@NotNull LookupElement element) {
//                LookupElement decorated = LookupElementBuilder.create(element.getLookupString())
//                        .withInsertHandler((context, item) -> {
//                            // 메서드 선택 시 괄호 자동 입력
//                            if (element.getObject() instanceof PsiMethod) {
//                                context.getDocument().insertString(context.getTailOffset(), "()");
//                                context.getEditor().getCaretModel().moveToOffset(context.getTailOffset() - 1);
//                            } else {
//                                element.handleInsert(context);
//                            }
//                        });
//                super.addElement(decorated);
//            }
//        });
    }
}
