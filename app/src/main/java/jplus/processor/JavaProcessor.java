package jplus.processor;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.Trees;
import jplus.analyzer.JavaSymbolAnalyzer;
import jplus.analyzer.JavacMethodInspector;
import jplus.analyzer.NullabilityChecker;
import jplus.analyzer.SymbolAnalyzer;
import jplus.base.JPlus20Lexer;
import jplus.base.JPlus20Parser;
import jplus.base.JavaMethodInvocationManager;
import jplus.base.MethodInvocationInfo;
import jplus.base.SymbolTable;
import jplus.generator.BoilerplateCodeGenerator;
import jplus.generator.JPlusParserRuleContext;
import jplus.generator.TextChangeRange;
import jplus.util.FragmentedText;
import jplus.util.Utils;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JavaProcessor {
    private List<InMemoryJavaFile> javaFiles;
    private String source;
    private SymbolTable globalSymbolTable;
    private List<JavaSymbolAnalyzer> symbolAnalyzerList;
    private boolean symbolsAnalyzed = false;

    private JavacTask task;
    private Iterable<? extends CompilationUnitTree> asts;
    private Trees trees;
    private Map<String, MethodInvocationInfo> methodInvocationInfoMap;

    public JavaProcessor(String source) {
        this(source, new SymbolTable(null));
    }

    public JavaProcessor(String source, SymbolTable globalSymbolTable) {
        this.source = source;
        this.globalSymbolTable = globalSymbolTable;
        this.javaFiles = new ArrayList<>();
        this.symbolAnalyzerList = new ArrayList<>();
        InMemoryJavaFile file = new InMemoryJavaFile("Source", this.source);
        javaFiles.add(file);
    }

    public JavaProcessor(List<InMemoryJavaFile> javaFiles, SymbolTable globalSymbolTable) {
        this.source = javaFiles.get(0).getContent();
        this.javaFiles = javaFiles;
        this.globalSymbolTable = globalSymbolTable;
        this.symbolAnalyzerList = new ArrayList<>();
    }

    public JavaProcessor(Path filePath) throws Exception {
        this(Files.readString(filePath, StandardCharsets.UTF_8));
    }

    public void process() throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        task = (JavacTask) compiler.getTask(
                null, fileManager, null,
                List.of("-XDcompilePolicy=simple"), null,
                javaFiles
        );

        asts = task.parse();
        task.analyze();
        trees = Trees.instance(task);
    }

    public void analyzeSymbols() {
        if (trees == null) {
            throw new IllegalStateException("Call process() first.");
        }

        int k = 0;
        for (CompilationUnitTree ast : asts) {
            String source = javaFiles.get(k).getContent();
            JavaSymbolAnalyzer javaSymbolAnalyzer = new JavaSymbolAnalyzer(source, ast, trees, globalSymbolTable);
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
