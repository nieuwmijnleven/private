package jplus.plugin.intellij.annotator;

import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class JPlusIntelliJProjectUtil {

    public static jplus.base.Project buildJPlusProject(com.intellij.openapi.project.Project ideaProject) {
        List<Path> sourceDirs = new ArrayList<>();

        for (VirtualFile root :
                ProjectRootManager.getInstance(ideaProject).getContentSourceRoots()) {
            sourceDirs.add(Path.of(root.getPath()));
        }

        return new jplus.base.Project(sourceDirs);
    }

    public static String resolvePackageName(com.intellij.openapi.project.Project ideaProject, PsiFile file) {
        VirtualFile vFile = file.getVirtualFile();
        if (vFile == null) return null;

        VirtualFile sourceRoot =
                ProjectRootManager.getInstance(ideaProject)
                        .getFileIndex()
                        .getSourceRootForFile(vFile);

        if (sourceRoot == null) return null;

        String relative =
                vFile.getParent().getPath()
                        .substring(sourceRoot.getPath().length());

        if (relative.startsWith("/")) relative = relative.substring(1);
        if (relative.isEmpty()) return null;

        return relative.replace('/', '.');
    }
}
