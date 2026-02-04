package jplus.plugin.intellij;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import jplus.plugin.intellij.annotator.JPlusIntelliJProjectUtil;
import jplus.processor.JPlusProcessor;
import com.intellij.openapi.module.Module;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JPlusFileService {

    private final Project project;

    public JPlusFileService(Project project) {
        this.project = project;
    }

    public void compileAndWriteToJava(VirtualFile jplusFile, String jplusCode) {

        try {

            PsiFile file = PsiManager.getInstance(project).findFile(jplusFile);
            if (file == null) return;

            Project ideaProject = file.getProject();

            Module module = ModuleUtilCore.findModuleForFile(file.getVirtualFile(), ideaProject);
            jplus.base.Project jplusProject = JPlusIntelliJProjectUtil.buildJPlusProject(ideaProject, module);

            //String className = file.getVirtualFile().getNameWithoutExtension();

            //String packageName = JPlusIntelliJProjectUtil.resolvePackageName(ideaProject, file);


            //JPlusProcessor processor = new JPlusProcessor(jplusCode);
            JPlusProcessor processor = new JPlusProcessor(jplusProject, jplusCode);

            processor.process();

            processor.analyzeSymbols();

            //var issues = processor.checkNullability();
//            if (!issues.isEmpty()) {
//                System.err.println("Nullability check failed.");
//                return;
//            }

            String javaCode = processor.generateJavaCode();
            //System.out.println("javaCode = " + javaCode);

            Path jplusPath = Paths.get(jplusFile.getPath());
            String javaFileName = jplusPath.getFileName().toString().replaceFirst("\\.jplus$", ".java");
            Path javaFilePath = jplusPath.resolveSibling(javaFileName);

            ApplicationManager.getApplication().invokeLater(() -> {
                WriteCommandAction.runWriteCommandAction(project, () -> {
                    try {
                        VirtualFile javaVirtualFile = LocalFileSystem.getInstance()
                                .findFileByPath(javaFilePath.toString());
                        if (javaVirtualFile == null) {
                            javaVirtualFile = jplusFile.getParent().createChildData(this, javaFileName);
                        }

                        PsiFile psiFile = PsiManager.getInstance(project).findFile(javaVirtualFile);
                        if (psiFile != null) {
                            var doc = PsiDocumentManager.getInstance(project).getDocument(psiFile);
                            if (doc != null) {
                                doc.setText(javaCode);
                                PsiDocumentManager.getInstance(project).commitDocument(doc);
                                FileDocumentManager.getInstance().saveDocument(doc);
                            }
                        } else {
                            javaVirtualFile.setBinaryContent(javaCode.getBytes());
                        }

//                    FileDocumentManager.getInstance().saveAllDocuments();
                        System.out.println("JPlus compiled → " + javaFilePath);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
