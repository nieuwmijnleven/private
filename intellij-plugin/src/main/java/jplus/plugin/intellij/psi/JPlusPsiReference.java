package jplus.plugin.intellij.psi;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cef.remote.thrift.annotation.Nullable;
import jplus.plugin.intellij.util.JPlusUtil;
import org.jetbrains.annotations.NotNull;

public class JPlusPsiReference extends PsiReferenceBase<PsiElement> {

    public JPlusPsiReference(@NotNull PsiElement element, TextRange range) {
        super(element, range);
    }

    @Override
    public @Nullable PsiElement resolve() {
        Project project = myElement.getProject();
        PsiFile jplusPsiFile = myElement.getContainingFile();
        String symbol = getValue();

        var javaFile = JPlusUtil.createJavaPsiFromJPlus(project, jplusPsiFile);
        int newOffset = JPlusUtil.findNewOffset(jplusPsiFile.getText(), javaFile.getText(), myElement.getTextRange().getStartOffset());

        PsiElement deReferencedElement = JPlusUtil.findCorrespondingPsiElement(javaFile, newOffset);
        if (deReferencedElement == null || !(deReferencedElement instanceof PsiIdentifier)) return null;

        PsiElement resolved = PsiTreeUtil.getParentOfType(deReferencedElement, false, PsiJavaCodeReferenceElement.class);
        if (resolved != null) {
            var refElement = (PsiJavaCodeReferenceElement) resolved;
            String qualfiedName = refElement.getQualifiedName();
            String packageName = javaFile.getPackageName();
            if (!qualfiedName.startsWith(packageName)) {
                PsiClass psiClass = JavaPsiFacade.getInstance(project).findClass(qualfiedName, GlobalSearchScope.allScope(project));
                if (psiClass != null) return psiClass;
            }
            resolved = resolved.getReference().resolve();
        } else {
            resolved = PsiTreeUtil.getParentOfType(deReferencedElement, false, PsiClass.class, PsiMethod.class, PsiField.class, PsiVariable.class);
        }

        if (resolved == null) return null;

        if (resolved instanceof PsiNamedElement psiNamedElement) {
            return new PsiNamedElementWrapper(psiNamedElement, jplusPsiFile);
        } else {
            return new PsiElementWrapper(resolved, jplusPsiFile);
        }
    }
}
