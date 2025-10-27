package jplus.plugin.intellij.psi;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.application.impl.AnyThreadWriteThreadingSupport;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.PsiTypeElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.testFramework.LightVirtualFile;
import com.jetbrains.cef.remote.thrift.annotation.Nullable;
import jplus.plugin.intellij.JPlusPsiElementWrapper;
import jplus.plugin.intellij.PsiElementWrapper;
import jplus.plugin.intellij.PsiResolver;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class JPlusReference extends PsiReferenceBase<PsiElement> {

    public JPlusReference(@NotNull PsiElement element, TextRange range) {
        super(element, range);
    }

    @Override
    public @Nullable PsiElement resolve() {
        Project project = myElement.getProject();
        PsiFile jplusPsiFile = myElement.getContainingFile();
        String symbol = getValue();

        var javaFile = createJavaPsiFromJPlus(project, jplusPsiFile);
//        PsiClass[] classes = javaFile.getClasses();
//        for (PsiClass cls : classes) {
//            if (cls.getName().equals(symbol)) return cls;
//        }
//
//        PsiClass psiClass = JavaPsiFacade.getInstance(project)
//                .findClass("java.lang." + symbol, GlobalSearchScope.allScope(project));
//        return psiClass;

        PsiElement resolved = PsiResolver.resolveSymbol(javaFile, symbol);
        if (resolved == null) return null;

        if (resolved instanceof PsiTypeElement) {
            String qualfiedName = ((PsiTypeElement) resolved).getInnermostComponentReferenceElement().getQualifiedName();
            System.err.println("PsiTypeElement = " + qualfiedName);
            PsiClass psiClass = JavaPsiFacade.getInstance(project).findClass(qualfiedName, GlobalSearchScope.allScope(project));
            if (psiClass != null) return psiClass;
        }

//        TextRange range = myElement.getTextRange();
//        return new JPlusPsiElementWrapper(resolved, jplusPsiFile, range, symbol);
        return new PsiElementWrapper(resolved, jplusPsiFile);
    }

    private PsiJavaFile createJavaPsiFromJPlus(Project project, PsiFile jplusFile) {
        String javaText = jplusFile.getText();
        if (javaText.isEmpty()) return null;


////        LightVirtualFile vFile = new LightVirtualFile(
////                "Temp.java",
////                JavaFileType.INSTANCE,
////                javaText
////        );
//
//        VirtualFile tempVf = WriteAction.compute(() ->
//                LocalFileSystem.getInstance()
//                        .refreshAndFindFileByPath("Temp.java"));
//
//        try {
//            VfsUtil.saveText(tempVf, javaText);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        return (PsiJavaFile) PsiFileFactory.getInstance(project)
                .createFileFromText("Temp.java", JavaFileType.INSTANCE, javaText);
    }
}
