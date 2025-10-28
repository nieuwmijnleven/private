package jplus.plugin.intellij.psi;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNamedElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PsiNamedElementWrapper extends PsiElementWrapper implements PsiNamedElement {

    public PsiNamedElementWrapper(PsiNamedElement psiElement, PsiFile psiFile) {
        this(psiElement, psiFile, null);
    }

    public PsiNamedElementWrapper(PsiNamedElement psiElement, PsiFile psiFile, PsiElement deReferencedPsiElement) {
        super(psiElement, psiFile, deReferencedPsiElement);
    }

    @Override
    public @Nullable @NlsSafe String getName() {
        return ((PsiNamedElement)this.psiElement).getName();
    }

    @Override
    public PsiElement setName(@NlsSafe @NotNull String s) throws IncorrectOperationException {
        return ((PsiNamedElement)this.psiElement).setName(s);
    }

    /*@Override
    public PsiElement setName(@NlsSafe @NotNull String s) throws IncorrectOperationException {
//        return ((PsiNamedElement)this.psiElement).setName(s);
        ((PsiNamedElement)psiElement).setName(s);

        Project project = psiFile.getProject();
        Document document = PsiDocumentManager.getInstance(project).getDocument(psiFile);
        if (document == null) return this;

        // 1. Java PSI에서 정의 위치(resolve)
        PsiNamedElement targetElement = (PsiNamedElement) psiElement;
//        PsiReference ref = psiElement.getReference();
//        if (ref != null) {
//            PsiElement resolved = ref.resolve();
//            if (resolved instanceof PsiNamedElement) {
//                targetElement = (PsiNamedElement) resolved;
//            }
//        }

        // 2. 정의 위치의 오프셋 계산 (Java PSI 기준)
        int startOffset = targetElement.getTextRange().getStartOffset();
        int endOffset = targetElement.getTextRange().getEndOffset();

        // 3. Document 변경
        document.replaceString(startOffset, endOffset, s);

        // 4. PSI 동기화
        PsiDocumentManager.getInstance(project).commitDocument(document);

        return this;
    }*/
}
