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
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import jplus.base.JPlus20Parser;
import jplus.plugin.intellij.psi.ApplyBlockPsiElement;
import jplus.plugin.intellij.psi.ApplyStatementPsiElement;
import jplus.plugin.intellij.psi.NormalClassDeclarationPsiElement;
import jplus.plugin.intellij.util.PsiUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class JPlusCompletionContributor extends CompletionContributor {

    private static final List<String> APPLY_KEYWORDS = Arrays.asList(
            "getter", "setter", "data", "equality", "constructor", "builder", "tostring", "equals", "hashcode"
    );

    public JPlusCompletionContributor() {
        extend(CompletionType.BASIC, JPlusPatterns.psiElement(), new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters,
                                          @NotNull ProcessingContext context,
                                          @NotNull CompletionResultSet result) {
                Project project = parameters.getPosition().getProject();
                PsiElement jplusElement = parameters.getPosition();

                collectApplyCandidates(jplusElement, result);

                PsiFile javaPsiFile = createJavaPsiFromJPlus(project, jplusElement.getContainingFile());
                if (javaPsiFile != null) {
                    //collectJavaPsiCandidates(javaPsiFile, result);
                    invokeJavaCompletion(javaPsiFile, parameters, result);
                }
            }
        });
    }

    private void collectApplyCandidates(PsiElement element, CompletionResultSet result) {
        PsiElement parent = element.getParent();
        if (parent == null) return;

        boolean isInApply = PsiTreeUtil.getParentOfType(element, ApplyStatementPsiElement.class, ApplyBlockPsiElement.class) != null;
        if (!isInApply) return;

        if (PsiTreeUtil.getParentOfType(element, ApplyBlockPsiElement.class) != null) {
            PsiFile file = element.getContainingFile();
            PsiUtils.getClassNameList(file).forEach(className -> {
                result.addElement(LookupElementBuilder.create(className));
            });
        }

        for (String keyword : APPLY_KEYWORDS) {
            result.addElement(LookupElementBuilder.create(keyword));
        }
    }

    private PsiFile createJavaPsiFromJPlus(Project project, PsiFile jplusFile) {
//        String javaText = JPlusToJavaConverter.convert(jplusFile.getText());
        String javaText = jplusFile.getText();
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
