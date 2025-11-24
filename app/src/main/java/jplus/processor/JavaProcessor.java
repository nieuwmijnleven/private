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
import java.util.List;
import java.util.Map;

public class JavaProcessor {

    private final String source;
    private SymbolTable globalSymbolTable;
    private SymbolTable symbolTable;
    private JavaSymbolAnalyzer symbolAnalyzer;
    private boolean symbolsAnalyzed = false;

    private JavacTask task;
    private Iterable<? extends CompilationUnitTree> asts;
    private Trees trees;
    private Map<String, MethodInvocationInfo> methodInvocationInfoMap;

    public JavaProcessor(String source) {
        this.source = source;
        this.globalSymbolTable = new SymbolTable(null);
    }

    public JavaProcessor(String source, SymbolTable globalSymbolTable) {
        this.source = source;
        this.globalSymbolTable = globalSymbolTable;
    }

    public JavaProcessor(Path filePath) throws Exception {
        this(Files.readString(filePath, StandardCharsets.UTF_8));
    }

    public void process() throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        JavaFileObject file = new SimpleJavaFileObject(URI.create("string:///Source.java"),
                JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return source;
            }
        };

        task = (JavacTask) compiler.getTask(
                null, fileManager, null,
                List.of("-XDcompilePolicy=simple"), null,
                List.of(file)
        );

        asts = task.parse();
        task.analyze();
        trees = Trees.instance(task);
    }

    public void analyzeSymbols() {
        if (trees == null) {
            throw new IllegalStateException("Call process() first.");
        }

        symbolAnalyzer = null;
        for (CompilationUnitTree ast : asts) {
            symbolAnalyzer = new JavaSymbolAnalyzer(source, ast, trees, globalSymbolTable);
            symbolAnalyzer.scan(ast, null);
        }
        symbolsAnalyzed = false;
    }

    public SymbolTable getSymbolTable() {
        return symbolAnalyzer.getTopLevelSymbolTable();
    }

    public SymbolTable getGlobalSymbolTable() { return globalSymbolTable; }

    public JavaMethodInvocationManager getMethodInvocationManager() {
        return symbolAnalyzer.getJavaMethodInvocationManager();
    }
}
