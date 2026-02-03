/*
 * Copyright 2025 Cheol Jeon <nieuwmijnleven@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jplus.processor;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.Trees;
import jplus.analyzer.JavaSymbolAnalyzer;
import jplus.base.JavaMethodInvocationManager;
import jplus.base.MethodInvocationInfo;
import jplus.base.Project;
import jplus.base.SymbolTable;

import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaProcessor {

    private static WeakReference<JavaCompiler> cachedCompilerRef;

    private final Project project;

    private List<InMemoryJavaFile> javaFiles;
    private String source;
    private SymbolTable globalSymbolTable;
    private List<JavaSymbolAnalyzer> symbolAnalyzerList;
    private boolean symbolsAnalyzed = false;

    private JavacTask task;
    private Iterable<? extends CompilationUnitTree> asts;
    private Trees trees;
    private Elements elements;
    private Types types;

    private Map<String, MethodInvocationInfo> methodInvocationInfoMap;


    public JavaProcessor(Project project, String source) {
        this(project, source, new SymbolTable(null));
    }

    public JavaProcessor(Project project, String source, SymbolTable globalSymbolTable) {
        this.project = project;
        this.source = source;
        this.globalSymbolTable = globalSymbolTable;
        this.javaFiles = new ArrayList<>();
        this.symbolAnalyzerList = new ArrayList<>();
        InMemoryJavaFile file = new InMemoryJavaFile("Source", this.source);
        javaFiles.add(file);
    }

    public JavaProcessor(Project project, List<InMemoryJavaFile> javaFiles, SymbolTable globalSymbolTable) {
        this.project = project;
        this.source = javaFiles.get(0).getContent();
        this.javaFiles = javaFiles;
        this.globalSymbolTable = globalSymbolTable;
        this.symbolAnalyzerList = new ArrayList<>();
    }

    public JavaProcessor(Project project, Path filePath) throws Exception {
        this(project, Files.readString(filePath, StandardCharsets.UTF_8));
    }

    public void process() throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            ////System.err.println("No system Java compiler available (JDK required)");
            //throw new IllegalStateException("No system Java compiler available (JDK required)");

            if (project.getJdkHome() != null) {
                compiler = loadJavaCompiler();
            }
        }

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        List<String> options = new ArrayList<>();
        options.add("-XDcompilePolicy=simple");
        options.add("-proc:none");
        options.add("-g:none");
        options.add("-implicit:none");
        options.add("-Xlint:none");

        if (project != null) {
            // source path
            List<File> sourcePathDirs = project.getSourceDirs().stream()
                    .map(Path::toFile)
                    .toList();
            fileManager.setLocation(javax.tools.StandardLocation.SOURCE_PATH, sourcePathDirs);

            // classpath
            List<File> classPathDirs = project.getJavaClassPath().stream()
                    .map(Path::toFile)
                    .toList();

            if (!classPathDirs.isEmpty()) {
                List<File> mergedClassPathDirs = new ArrayList<>();

                fileManager.getLocation(javax.tools.StandardLocation.CLASS_PATH).forEach(mergedClassPathDirs::add);
                mergedClassPathDirs.addAll(classPathDirs);
                ////System.err.println("classPathDirs = " + mergedClassPathDirs);

                fileManager.setLocation(javax.tools.StandardLocation.CLASS_PATH, mergedClassPathDirs);
            }
        }

        /*if (project != null && !project.getSourceDirs().isEmpty()) {
            List<File> sourcePathDirs = project.getSourceDirs().stream()
                    .map(Path::toFile)
                    .toList();

            fileManager.setLocation(
                    javax.tools.StandardLocation.SOURCE_PATH,
                    sourcePathDirs
            );

            String sourcePath = project.getSourceDirs().stream()
                    .map(Path::toAbsolutePath)
                    .map(Path::toString)
                    .collect(Collectors.joining(File.pathSeparator));

            options.add("-sourcepath");
            options.add(sourcePath);
        }*/

        /*if (project != null) {
            String classPath = project.buildJavaClassPath();
            if (!classPath.isEmpty()) {
                options.add("-classpath");
                options.add(classPath);
            }
        }*/

        //System.err.println("task before");
        task = (JavacTask) compiler.getTask(
                null,
                fileManager,
                null,
                options,
                null,
                javaFiles
        );

        //System.err.println("task after");

        //System.err.println("task.parse()");
        asts = task.parse();
        
        //System.err.println("task.analyze()");
        task.analyze();
        
        //System.err.println("Trees.instance(task)");
        trees = Trees.instance(task);
        
        //System.err.println("task.getElements()");
        elements = task.getElements();
        
        //System.err.println("task.getTypes()");
        types = task.getTypes();
    }

    private JavaCompiler loadJavaCompiler() {
        if (cachedCompilerRef != null) {
            JavaCompiler cached = cachedCompilerRef.get();
            if (cached != null) {
                return cached;
            }
        }

        try {
            ClassLoader jdkClassLoader = new java.net.URLClassLoader(
                    new java.net.URL[]{new File(project.getJdkHome() + "/lib/tools.jar").toURI().toURL()},
                    ToolProvider.class.getClassLoader()
            );
            Class<?> javacClass = Class.forName("com.sun.tools.javac.api.JavacTool", true, jdkClassLoader);
            JavaCompiler compiler = (JavaCompiler) javacClass.getDeclaredConstructor().newInstance();

            // WeakReference로 저장
            cachedCompilerRef = new WeakReference<>(compiler);

            return compiler;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JavacTool from JDK", e);
        }
    }

//    private JavaCompiler loadJavaCompiler() {
//        try {
//            ClassLoader jdkClassLoader = new java.net.URLClassLoader(
//                    new java.net.URL[]{new File(project.getJdkHome() + "/lib/tools.jar").toURI().toURL()},
//                    ToolProvider.class.getClassLoader()
//            );
//            Class<?> javacClass = Class.forName("com.sun.tools.javac.api.JavacTool", true, jdkClassLoader);
//            return (JavaCompiler) javacClass.getDeclaredConstructor().newInstance();
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to load JavacTool from JDK", e);
//        }
//    }

    public void analyzeSymbols() {
        if (trees == null) {
            throw new IllegalStateException("Call process() first.");
        }

        int k = 0;
        for (CompilationUnitTree ast : asts) {
            String source = javaFiles.get(k).getContent();
            JavaSymbolAnalyzer javaSymbolAnalyzer = new JavaSymbolAnalyzer(source, ast, trees, globalSymbolTable, elements, types);
            javaSymbolAnalyzer.scan(ast, null);
            symbolAnalyzerList.add(javaSymbolAnalyzer);
            ++k;
        }

//        String source = javaFiles.get(0).getContent();
//        CompilationUnitTree ast = asts.iterator().next();
//        symbolAnalyzer = new JavaSymbolAnalyzer(source, ast, trees, globalSymbolTable);
//        symbolAnalyzer.scan(ast, null);
//        symbolsAnalyzed = false;
    }

    public String getSource() {
        return source;
    }

    public List<SymbolTable> getSymbolTable() {
        return symbolAnalyzerList.stream().map(JavaSymbolAnalyzer::getTopLevelSymbolTable).toList();
    }

    public SymbolTable getGlobalSymbolTable() { return globalSymbolTable; }

    public List<JavaMethodInvocationManager> getMethodInvocationManager() {
        return symbolAnalyzerList.stream().map(JavaSymbolAnalyzer::getJavaMethodInvocationManager).toList();
    }
}
