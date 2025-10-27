package jplus.plugin.intellij.psi;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.PsiTypeElement;
import com.intellij.psi.search.GlobalSearchScope;
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
        PsiElement resolved = PsiResolver.resolveSymbol(javaFile, symbol);
        if (resolved == null) return null;

        if (resolved instanceof PsiTypeElement psiTypeElement) {
            String qualfiedName = psiTypeElement.getInnermostComponentReferenceElement().getQualifiedName();
            PsiClass psiClass = JavaPsiFacade.getInstance(project).findClass(qualfiedName, GlobalSearchScope.allScope(project));
            if (psiClass != null) return psiClass;
        }

        return new PsiElementWrapper(resolved, jplusPsiFile);
    }
}
