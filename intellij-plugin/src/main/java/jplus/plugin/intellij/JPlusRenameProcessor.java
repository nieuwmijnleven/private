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
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.refactoring.listeners.RefactoringElementListener;
import com.intellij.refactoring.rename.RenamePsiElementProcessor;
import com.intellij.usageView.UsageInfo;
import jplus.plugin.intellij.context.JPlusContext;
import jplus.plugin.intellij.psi.PsiElementWrapper;
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
                PsiElement resolved = null;
                if (deReferencedElement instanceof PsiIdentifier) {
                    PsiElement parent = PsiTreeUtil.getParentOfType(deReferencedElement, false, PsiJavaCodeReferenceElement.class);
                    if (parent != null) {
                        resolved = parent.getReference().resolve();
                    } else {
                        resolved = PsiTreeUtil.getParentOfType(deReferencedElement, false, PsiClass.class, PsiMethod.class, PsiField.class, PsiVariable.class);
                    }
                }

                if (resolved != null) {
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
        System.err.println("renameElement = " + element.getClass().getSimpleName());
        if (!(element instanceof PsiNamedElement)) {
            System.err.println("Not a PsiNamedElement: " + element);
            return;
        }

        Project project = JPlusContext.getInstance().getProject();
        System.err.println("project = " + project);

        PsiJavaFile javaPsiFile = (PsiJavaFile) element.getContainingFile();
        performInMemoryRename(project, javaPsiFile, (PsiNamedElement) element, newName);

        String newJavaCode = javaPsiFile.getText();
        System.out.println("newJavaCode = " + newJavaCode);

        String newJPlusCode = newJavaCode;

        PsiFile jplusFile = JPlusContext.getInstance().getJPlusFile();
        System.err.println("jplusFile = " + jplusFile);
        if (jplusFile == null) return;

        Document jplusDoc = PsiDocumentManager.getInstance(project).getDocument(jplusFile);
        if (jplusDoc != null) {
            ApplicationManager.getApplication().runWriteAction(() -> {
                jplusDoc.setText(newJPlusCode);
                PsiDocumentManager.getInstance(project).commitDocument(jplusDoc);
            });
        }

        if (listener != null) listener.elementRenamed(element);
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

            PsiElement[] psiElements = PsiTreeUtil.collectElements(javaPsiFile, psiElement -> psiElement instanceof PsiReference);
            for (PsiElement refElement : psiElements) {
                PsiReference ref = (PsiReference) refElement;
                PsiElement resolved = ref.resolve();
                if (resolved != null && resolved.isEquivalentTo(element)) {
                    try {
                        ref.handleElementRename(newName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // identifier rename
            element.setName(newName);

            PsiDocumentManager.getInstance(project).commitAllDocuments();
        });
    }


    private @Nullable PsiElement findCorrespondingJavaElement(PsiJavaFile javaPsiFile, int offset) {
        return javaPsiFile.findElementAt(offset);
    }
}
