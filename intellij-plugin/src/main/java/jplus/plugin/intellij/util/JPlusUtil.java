package jplus.plugin.intellij.util;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiJavaFile;

import com.intellij.openapi.module.Module;
import com.jetbrains.cef.remote.thrift.annotation.Nullable;
import jplus.plugin.intellij.annotator.JPlusIntelliJProjectUtil;
import jplus.processor.JPlusProcessor;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;

import java.util.LinkedList;

public class JPlusUtil {

    private JPlusUtil() {}

    public static PsiJavaFile createJavaPsiFromJPlus(Project project, PsiFile jplusFile) {

        Project ideaProject = jplusFile.getProject();

        Module module = ModuleUtilCore.findModuleForFile(jplusFile.getVirtualFile(), ideaProject);
        jplus.base.Project jplusProject = JPlusIntelliJProjectUtil.buildJPlusProject(ideaProject, module);

        JPlusProcessor processor = new JPlusProcessor(jplusProject, jplusFile.getText());
        String javaText = processor.compile();
        if (javaText == null || javaText.isEmpty()) return null;

//        LightVirtualFile vFile = new LightVirtualFile(
//                "Temp.java",
//                JavaFileType.INSTANCE,
//                javaText
//        );
//        return (PsiJavaFile) PsiManager.getInstance(project).findFile(vFile);

        return (PsiJavaFile) PsiFileFactory.getInstance(project)
                .createFileFromText("Temp.java", JavaFileType.INSTANCE, javaText);
    }

    public static int findNewOffset(String oldText, String newText, int oldOffset) {
        DiffMatchPatch dmp = new DiffMatchPatch();
        LinkedList<DiffMatchPatch.Diff> diffs = dmp.diffMain(oldText, newText);
//        dmp.diffCleanupSemantic(diffs);

        int newOffset = dmp.diffXIndex(diffs, oldOffset);
//        System.out.printf("old offset %d -> new offset %d\n", oldOffset, newOffset);
        return newOffset;
    }

    public static @Nullable PsiElement findCorrespondingPsiElement(PsiFile file, int offset) {
        return file.findElementAt(offset);
    }
}
