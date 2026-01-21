package jplus.plugin.intellij.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.roots.ProjectRootManager;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JavacUtils {

    /**
     * IntelliJ 프로젝트에서 JavacTool(JavaCompiler)을 가져옵니다.
     * null이면 JDK가 없는 것입니다.
     */

    public static Sdk getProjectJdk(Project project) {
        // ProjectRootManager를 통해 Project SDK 확인
        Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
        if (projectSdk == null || !(projectSdk.getSdkType() instanceof JavaSdk)) {
            throw new IllegalStateException("Project SDK is not a Java SDK");
        }
        return projectSdk;
    }

    public static String getJdkHomePath(Project project) {
        Sdk sdk = getProjectJdk(project);
        String homePath = sdk.getHomePath();
        if (homePath == null) {
            throw new IllegalStateException("JDK home path is null");
        }
        return homePath;
    }

    public static JavaCompiler getCompiler(Project intelliJProject) {
        Sdk projectSdk = getProjectJdk(intelliJProject);
        if (projectSdk == null || !(projectSdk.getSdkType() instanceof JavaSdk)) {
            throw new IllegalStateException("Project SDK is not a Java SDK");
        }

        // JDK home 디렉토리
        String jdkHomePath = projectSdk.getHomePath();
        if (jdkHomePath == null) {
            throw new IllegalStateException("JDK home path is null");
        }

        // JavacTool 직접 로드
        try {
            ClassLoader jdkClassLoader = new java.net.URLClassLoader(
                    new java.net.URL[]{new java.io.File(jdkHomePath + "/lib/tools.jar").toURI().toURL()},
                    ToolProvider.class.getClassLoader()
            );
            Class<?> javacClass = Class.forName("com.sun.tools.javac.api.JavacTool", true, jdkClassLoader);
            return (JavaCompiler) javacClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JavacTool from JDK", e);
        }
    }
}
