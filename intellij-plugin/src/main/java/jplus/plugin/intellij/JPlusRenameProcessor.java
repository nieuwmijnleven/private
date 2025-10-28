package jplus.plugin.intellij;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.PsiReferenceExpression;
import com.intellij.psi.impl.source.PsiFieldImpl;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.refactoring.listeners.RefactoringElementListener;
import com.intellij.refactoring.rename.RenameProcessor;
import com.intellij.refactoring.rename.RenamePsiElementProcessor;
import com.intellij.usageView.UsageInfo;
import jplus.plugin.intellij.context.JPlusContext;
import jplus.plugin.intellij.psi.IdentifierPsiElement;
import jplus.plugin.intellij.psi.PsiElementWrapper;
import jplus.plugin.intellij.psi.PsiNamedElementWrapper;
import jplus.plugin.intellij.util.JPlusUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class JPlusRenameProcessor extends RenamePsiElementProcessor {

    @Override
    public boolean canProcessElement(@NotNull PsiElement element) {
//        return element instanceof IdentifierPsiElement;
        System.err.println("canProcessElement = " + element.getClass().getSimpleName());
//        if (element instanceof PsiNamedElementWrapper || element instanceof PsiNamedElement) return true;
//        if (element instanceof PsiFieldImpl || element instanceof PsiMethod || element instanceof PsiClass) return true;
        return true;
    }

    @Override
    public @NotNull PsiElement substituteElementToRename(@NotNull PsiElement element, @Nullable Editor editor) {
        System.out.println("substituteElementToRename = " + element.getClass().getSimpleName());
        if (editor != null) {
            Project project = element.getProject();
            Document doc = editor.getDocument();
            PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(doc);

            JPlusContext.getInstance().setProject(project);
            JPlusContext.getInstance().setJPlusFile(element.getContainingFile());
//
//            PsiJavaFile javaPsiFile = JPlusUtil.createJavaPsiFromJPlus(project, psiFile);
//            if (javaPsiFile == null) return element;
//            System.err.println("javaPsiFile = " + javaPsiFile);
//
//            PsiElement javaElement = findCorrespondingJavaElement(javaPsiFile, editor.getCaretModel().getOffset());
//            if (javaElement == null) return element;
//            System.err.println("javaElement = " + javaElement);

            if (element instanceof PsiElementWrapper javaElement) {
                PsiElement deReferencedElement = javaElement.getDeReferencedPsiElement();
                System.out.println("deReferencedElement = " + deReferencedElement.getClass().getSimpleName());
//                PsiNamedElement namedParent = PsiTreeUtil.getParentOfType(deReferencedElement, PsiNamedElement.class, false);
                if (deReferencedElement instanceof PsiIdentifier) {
                    PsiElement parent = PsiTreeUtil.getParentOfType(deReferencedElement, PsiReferenceExpression.class, false);
                    if (parent != null) {
                        deReferencedElement = parent;
                    }
                }

                PsiElement resolved = deReferencedElement.getReference().resolve();
                System.out.println("resolve = " + resolved.getClass().getSimpleName());
                if (resolved != null && resolved instanceof PsiNamedElement namedParent) {
//                    return new PsiNamedElementWrapper(namedParent, psiFile, deReferencedElement);
                    return namedParent;
                } else {
                    // 부모가 없으면 그냥 wrapper 생성
//                    return new PsiElementWrapper(resolved, psiFile, deReferencedElement);
                    return resolved;
                }
            }
        }
        return element;
    }

    @Override
    public void renameElement(
            @NotNull PsiElement element,
            @NotNull String newName,
            @NotNull UsageInfo[] usages,
            @Nullable RefactoringElementListener listener
    ) {
        System.err.println("element = " + element.getClass().getSimpleName());

        Project project = JPlusContext.getInstance().getProject();
        System.err.println("project = " + project);

        // 🔹 1. JPlus 원본 파일 가져오기
        PsiFile jplusFile = JPlusContext.getInstance().getJPlusFile();
        System.err.println("jplusFile = " + jplusFile);
        if (jplusFile == null) return;

        // 🔹 2. JPlus → Java PSI 변환
        PsiJavaFile javaPsiFile = JPlusUtil.createJavaPsiFromJPlus(project, jplusFile);
        if (javaPsiFile == null) {
            System.err.println("Failed to create Java PSI from JPlus");
            return;
        }

        // 🔹 3. rename 대상 element 찾기 (메모리 Java PSI 내)
        PsiElement javaElement = findCorrespondingJavaElement(javaPsiFile, element.getTextOffset());

        PsiElement target = (PsiElement) javaElement;
        System.err.println("target = " + target.getClass().getSimpleName());

        if (javaElement instanceof PsiIdentifier) {
            PsiElement parent = PsiTreeUtil.getParentOfType(javaElement,false, PsiReferenceExpression.class, PsiMethod.class, PsiField.class, PsiClass.class);
            System.err.println("parent = " + parent);
            if (parent != null) {
                target = parent;
            }
        }

//        PsiElement resolved = target.getReference().resolve();
//        System.out.println("resolve = " + resolved.getClass().getSimpleName());
//        if (resolved != null && resolved instanceof PsiNamedElement namedParent) {
////                    return new PsiNamedElementWrapper(namedParent, psiFile, deReferencedElement);
//            target = namedParent;
//        }

        if (!(target instanceof PsiNamedElement)) {
            System.err.println("Not a PsiNamedElement: " + javaElement);
            return;
        }

        // 🔹 4. rename 수행 (메모리상 Java PSI 기준)
        performInMemoryRename(project, javaPsiFile, (PsiNamedElement) target, newName);

        // 🔹 5. 변경된 Java PSI를 문자열로 변환
        String newJavaCode = javaPsiFile.getText();
        System.out.println("newJavaCode = " + newJavaCode);

        // 🔹 6. Java → JPlus로 역변환 (단순하거나, 주석 기반으로 복원)
        String newJPlusCode = newJavaCode;

        Document jplusDoc = PsiDocumentManager.getInstance(project).getDocument(jplusFile);
        if (jplusDoc != null) {
            ApplicationManager.getApplication().runWriteAction(() -> {
                jplusDoc.setText(newJPlusCode);
                PsiDocumentManager.getInstance(project).commitDocument(jplusDoc);
            });
        }

        if (listener != null) listener.elementRenamed(element);


////        if (element instanceof PsiNamedElementWrapper) {
////            System.err.println("PsiNamedElementWrapper.setName()");
////            ((PsiNamedElement) element).setName(newName);
////        }
//
//        if (!(element instanceof PsiNamedElement)) return;
//
////        element.getReference().resolve();
//
//        System.out.println("renameElement = " + element.getClass().getSimpleName());
//
////        super.renameElement(element, newName, usages, listener);
//
////        Project project = element.getProject();
////        ApplicationManager.getApplication().invokeLater(() -> {
////            RenameProcessor javaRenameProcessor = new RenameProcessor(
////                    project,
////                    element,          // rename 대상: 실제 Java PSI
////                    newName,          // 새 이름
////                    false,            // search in comments
////                    false             // search in text occurrences
////            );
////            javaRenameProcessor.run();
////        });
    }

    private void performInMemoryRename(Project project,
                                       PsiJavaFile javaPsiFile,
                                       PsiNamedElement element,
                                       String newName) {

        ApplicationManager.getApplication().runWriteAction(() -> {
            // reference rename
            Collection<PsiReference> refs = ReferencesSearch.search(element, element.getUseScope()).findAll();
            for (PsiReference ref : refs) {
                try {
                    ref.handleElementRename(newName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // identifier rename
            element.setName(newName);

//            PsiDocumentManager.getInstance(project).commitAllDocuments();


//            PsiElement[] psiElements = PsiTreeUtil.collectElements(javaPsiFile, psiElement -> psiElement instanceof PsiReference);
//            System.err.println("psiElements.length = " + psiElements.length);
//            for (PsiElement refElement : psiElements) {
//                PsiReference ref = (PsiReference) refElement;
//                PsiElement resolved = ref.resolve();
//                if (resolved != null && resolved.isEquivalentTo(element)) {
//                    try {
//                        System.err.println("found reference: " + ref.getElement().getText());
//                        ref.handleElementRename(newName);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }

//            element.setName(newName);
//
//            System.err.println("isPhysical = " + element.isPhysical());
//            System.err.println("element class = " + element.getClass());
//            System.err.println("element text = " + element.getText());

//            PsiDocumentManager.getInstance(project).commitAllDocuments();
        });
    }


    private @Nullable PsiElement findCorrespondingJavaElement(PsiJavaFile javaPsiFile, int offset) {
        return javaPsiFile.findElementAt(offset);
    }
}
