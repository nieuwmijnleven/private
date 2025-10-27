package jplus.plugin.intellij;

import com.intellij.codeInsight.completion.commands.impl.JavaGoToDeclarationCommandCompletion;
import com.intellij.codeInsight.completion.commands.impl.JavaGoToImplementationCommandCompletion;
import com.intellij.codeInsight.navigation.JavaGotoSuperHandler;
import com.intellij.codeInsight.navigation.JavaGotoTargetPresentationProvider;
import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.testFramework.LightVirtualFile;
import com.jetbrains.cef.remote.thrift.annotation.Nullable;

public class JPlusGotoDefinitionHandler implements GotoDeclarationHandler {
    @Override
    public  @Nullable PsiElement[] getGotoDeclarationTargets(
            @Nullable PsiElement sourceElement, int offset, Editor editor) {

        if (sourceElement == null) return PsiElement.EMPTY_ARRAY;

//        Project project = sourceElement.getProject();
//        PsiFile jplusFile = sourceElement.getContainingFile();
//
//        // 1️⃣ JPlus → Java PSI 생성
//        PsiJavaFile javaPsiFile = createJavaPsiFromJPlus(project, jplusFile);
//        if (javaPsiFile == null) return PsiElement.EMPTY_ARRAY;
//        System.err.println("javaPsiFile = " + javaPsiFile);
//
//        // 2️⃣ JPlus 파일 내의 offset에 해당하는 Java PSI 위치를 매칭
//        PsiElement javaElement = findCorrespondingJavaElement(javaPsiFile, offset);
//        if (javaElement == null) return PsiElement.EMPTY_ARRAY;
//        System.err.println("javaElement = " + javaElement);
//
//        // 3️⃣ Java PSI의 기본 Go to Definition 로직 (reference.resolve())
//        PsiReference ref = javaElement.getReference();
//        System.err.println("PsiReference = " + ref);
//        if (ref != null) {
//            PsiElement resolved = ref.resolve();
//            if (resolved == null) {
//                // 혹시 인덱스 기반 탐색 시도
//                String symbol = ref.getCanonicalText();
//                PsiClass psiClass = JavaPsiFacade.getInstance(project)
//                        .findClass(symbol, GlobalSearchScope.allScope(project));
//                System.err.println("resolved = " + resolved);
//                if (psiClass != null) return new PsiElement[]{psiClass};
//            }
//        }

        System.err.println("sourceElement => " + sourceElement.getClass().getSimpleName());
        PsiReference ref = sourceElement.getReference();
        System.err.println("PsiReference = " + ref);
        if (ref != null) {
            PsiElement resolved = ref.resolve();
            if (resolved != null) {
                System.err.println("resolved = " + resolved);
                return new PsiElement[]{resolved};
            }
        }

        return PsiElement.EMPTY_ARRAY;
    }

    /**
     * JPlus -> Java PSI 변환
     */
    private PsiJavaFile createJavaPsiFromJPlus(Project project, PsiFile jplusFile) {
        String javaText = jplusFile.getText();
        if (javaText.isEmpty()) return null;

        LightVirtualFile vFile = new LightVirtualFile(
                "Temp.java",
                JavaFileType.INSTANCE,
                javaText
        );
        return (PsiJavaFile) PsiManager.getInstance(project).findFile(vFile);

//        return (PsiJavaFile) PsiFileFactory.getInstance(project)
//                .createFileFromText("Temp.java", JavaFileType.INSTANCE, javaText);
    }

    /**
     * 단순히 동일한 offset 위치의 PsiElement를 찾는 헬퍼
     */
    private @Nullable PsiElement findCorrespondingJavaElement(PsiJavaFile javaPsiFile, int offset) {
        // 변환 결과와 원본의 구조가 1:1 매칭된다고 가정할 경우, 단순히 동일 offset 사용
        return javaPsiFile.findElementAt(offset);
    }

    @Override
    public @Nullable String getActionText(@Nullable com.intellij.openapi.actionSystem.DataContext context) {
        return "Go to Definition (JPlus → Java)";
    }
}