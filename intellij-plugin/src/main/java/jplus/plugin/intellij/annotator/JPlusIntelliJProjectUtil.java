package jplus.plugin.intellij.annotator;

import com.intellij.execution.CantRunException;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.LibraryOrderEntry;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.libraries.LibraryTablesRegistrar;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.jetbrains.cef.remote.thrift.annotation.Nullable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class JPlusIntelliJProjectUtil {

    public static jplus.base.Project buildJPlusProject(com.intellij.openapi.project.Project ideaProject, Module module) {
        List<Path> sourceDirs = new ArrayList<>();

        for (VirtualFile root :
                ProjectRootManager.getInstance(ideaProject).getContentSourceRoots()) {
            sourceDirs.add(Path.of(root.getPath()));
        }

        //JavaParameters params = new JavaParameters();
        //params.setJdk(JavaSdk.getInstance().createJdk("My JDK", getJdkHome(ideaProject), false));

        /*ModuleRootManager rootManager = ModuleRootManager.getInstance(module);
        for (OrderEntry entry : rootManager.getOrderEntries()) {
            if (entry instanceof LibraryOrderEntry libEntry) {
                Library library = libEntry.getLibrary();
                if (library != null) {
                    for (VirtualFile file : library.getFiles(OrderRootType.CLASSES)) {
                        params.getClassPath().add(file.getPath());
                        //System.out.println("Added library jar: " + file.getPath());
                    }
                }
            }
        }*/

        /*LibraryTable projectLibraryTable = LibraryTablesRegistrar.getInstance().getLibraryTable(ideaProject);
        for (Library library : projectLibraryTable.getLibraries()) {
            for (VirtualFile root : library.getFiles(OrderRootType.CLASSES)) {
                params.getClassPath().add(root.getPath());
            }
        }*/

        JavaParameters params = new JavaParameters();
        try {
            params.configureByModule(module, JavaParameters.JDK_AND_CLASSES_AND_PROVIDED);
            //List<String> classPathList = params.getClassPath().getPathList();
            //System.err.println("classPathList = " + classPathList);

            // 2️⃣ JPlusProject에 클래스패스 추가
            // jplusProject = jplusProject.withJavaClassPathEntry(classPathList);

        } catch (CantRunException e) {
            throw new RuntimeException(e);
        }

        List<Path> classPathList = params.getClassPath().getPathList().stream()
                .map(s -> s.replaceAll("!/$", ""))  // !/ 제거
                .map(Path::of)
                .toList();
        System.err.println("classPathList = " + classPathList);

        var jplusProject = new jplus.base.Project(sourceDirs, classPathList);

        jplusProject = jplusProject.withJavaClassPathEntry(resolveJSpecifyJarPath());
        jplusProject.setJdkHome(getJdkHome(ideaProject));

        return jplusProject;
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

    private static @Nullable String getJdkHome(Project ideaProject) {
        Sdk sdk = ProjectRootManager.getInstance(ideaProject).getProjectSdk();
        return sdk.getHomePath();
    }

    private static Path resolveJSpecifyJarPath() {
        return Path.of(PathManager.getPluginsPath())
                .resolve("intellij-plugin")
                .resolve("lib/jspecify-1.0.0.jar");
    }
}
