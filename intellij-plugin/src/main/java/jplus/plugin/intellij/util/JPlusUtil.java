package jplus.plugin.intellij.util;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiJavaFile;

public class JPlusUtil {

    private JPlusUtil() {}

    public static PsiJavaFile createJavaPsiFromJPlus(Project project, PsiFile jplusFile) {
        String javaText = jplusFile.getText();
        if (javaText.isEmpty()) return null;

//        LightVirtualFile vFile = new LightVirtualFile(
//                "Temp.java",
//                JavaFileType.INSTANCE,
//                javaText
//        );
//        return (PsiJavaFile) PsiManager.getInstance(project).findFile(vFile);

        return (PsiJavaFile) PsiFileFactory.getInstance(project)
                .createFileFromText("Temp.java", JavaFileType.INSTANCE, javaText);
    }
}
