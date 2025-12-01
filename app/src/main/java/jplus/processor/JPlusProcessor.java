package jplus.processor;

import jplus.analyzer.NullabilityChecker;
import jplus.analyzer.UnresolvedReferenceScanner;
import jplus.base.JPlus20Lexer;
import jplus.base.JPlus20Parser;
import jplus.base.JavaMethodInvocationManager;
import jplus.base.Project;
import jplus.base.SymbolTable;
import jplus.generator.BoilerplateCodeGenerator;
import jplus.generator.CodeGenContext;
import jplus.generator.JPlusParserRuleContext;
import jplus.generator.SourceMappingEntry;
import jplus.generator.TextChangeRange;
import jplus.util.FragmentedText;
import jplus.util.Utils;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class JPlusProcessor {
    private Project project;
    private final String originalText;
    private final TextChangeRange originalTextRange;
    private JPlus20Parser parser;
    private Set<SourceMappingEntry> sourceMappingEntrySet;
    private List<Path> srcDirPathList;
    private JPlusParserRuleContext parseTree;
    private JavaProcessor javaProcessor;
    private SymbolTable globalSymbolTable;
    private SymbolTable symbolTable;
    private String className;
    private String packageName;
    private boolean nullabilityChecked = false;
    private boolean symbolsAnalyzed = false;

    static class SourceFileInfo {
        String className;
        String packageName;
        String path;
    }

    public JPlusProcessor(Project project, String originalText) {
        this(project, originalText, new SymbolTable(null));
    }

    public JPlusProcessor(Project project, String originalText, SymbolTable globalSymbolTable) {
        this.project = project;
        this.originalText = originalText;
        this.originalTextRange = Utils.computeTextChangeRange(originalText, 0, originalText.length()-1);
        this.globalSymbolTable = globalSymbolTable;
    }

    public JPlusProcessor(Project project, String packageName, String className) throws Exception {
        this(project, Files.readString(project.getSrcDir().resolve(packageName.replace(".", "/")).resolve(className + ".jplus"), StandardCharsets.UTF_8), new SymbolTable(null));
        System.err.println("[JPlusProcessor] srcPath = " + project.getSrcDir().resolve(packageName.replace(".", "/")).resolve(className + ".jplus"));
    }

    public JPlusProcessor(Project project, Path filePath, SymbolTable globalSymbolTable) throws Exception {
        this(project, Files.readString(filePath, StandardCharsets.UTF_8), globalSymbolTable);
    }

    public JPlusProcessor(String originalText) {
        this(originalText, new SymbolTable(null));
    }

    public JPlusProcessor(String originalText, SymbolTable globalSymbolTable) {
        this.originalText = originalText;
        this.originalTextRange = Utils.computeTextChangeRange(originalText, 0, originalText.length()-1);
        this.globalSymbolTable = globalSymbolTable;
    }

    public JPlusProcessor(Path filePath) throws Exception {
        this(Files.readString(filePath, StandardCharsets.UTF_8), new SymbolTable(null));
    }

    public JPlusProcessor(Path filePath, SymbolTable globalSymbolTable) throws Exception {
        this(Files.readString(filePath, StandardCharsets.UTF_8), globalSymbolTable);
    }

    public void process() throws Exception {
        CharStream input = CharStreams.fromString(originalText);
        JPlus20Lexer lexer = new JPlus20Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser = new JPlus20Parser(tokens);
        parseTree = parser.start_();

        String javaCode = generateJavaCodeWithoutBoilerplate();
        System.err.println("[JavaCodeWithoutBoilerplate] = " + javaCode);
        CodeGenContext codeGenContext = CodeGenContext.current();
        sourceMappingEntrySet = codeGenContext.getFragmentedText().buildSourceMap();
//        System.err.println("sourceMappingEntrySet = " + sourceMappingEntrySet);

        javaProcessor = new JavaProcessor(javaCode, globalSymbolTable);
        javaProcessor.process();
    }

    public String generateJavaCodeWithoutBoilerplate() {
        if (parseTree == null) {
            throw new IllegalStateException("Call process() first.");
        }
        CodeGenContext codeGenContext = CodeGenContext.current();
        codeGenContext.setFragmentedText(new FragmentedText(originalText));
        return parseTree.getText();
    }

    public String getParseTreeString() {
        if (parseTree == null) {
            throw new IllegalStateException("Call process() first.");
        }
        return parseTree.toStringTree(parser);
    }

    public void analyzeSymbols() throws Exception {
        if (parseTree == null) {
            throw new IllegalStateException("Call process() first.");
        }

        javaProcessor.analyzeSymbols();
        symbolsAnalyzed = true;

        symbolTable = javaProcessor.getSymbolTable().get(0);
        className = symbolTable.resolve("^TopLevelClass$").getSymbol();
        packageName = symbolTable.resolve("^PackageName$").getSymbol();

        List<InMemoryJavaFile> inMemoryJavaFiles = new ArrayList<>();
        inMemoryJavaFiles.add(new InMemoryJavaFile(getFullyQualifiedName(), javaProcessor.getSource()));

        while(true) {
            var unresolvedReferenceInfoList = collectUnresolvedReferenceInfo(javaProcessor.getSymbolTable());
            if (unresolvedReferenceInfoList.isEmpty()) break;

            symbolsAnalyzed = false;
            for (var unresolvedReferenceInfo : unresolvedReferenceInfoList) {
                JPlusProcessor jPlusProcessor = new JPlusProcessor(project, unresolvedReferenceInfo.packageName, unresolvedReferenceInfo.className);
                jPlusProcessor.process();
                String javaCode = jPlusProcessor.generateJavaCodeWithoutBoilerplate();
                inMemoryJavaFiles.add(new InMemoryJavaFile(unresolvedReferenceInfo.getFullyQualifiedName(), javaCode));
            }

//            globalSymbolTable = new SymbolTable(null);
            javaProcessor = new JavaProcessor(inMemoryJavaFiles, globalSymbolTable);
            javaProcessor.process();
            javaProcessor.analyzeSymbols();

            symbolTable = javaProcessor.getSymbolTable().get(0);
        }

        symbolsAnalyzed = true;
    }

    private List<UnresolvedReferenceScanner.UnresolvedReferenceInfo> collectUnresolvedReferenceInfo(List<SymbolTable> symbolTableList) {
        List<UnresolvedReferenceScanner.UnresolvedReferenceInfo> allUnresolvedReferenceInfoList = new ArrayList<>();
        for (SymbolTable symbolTable : symbolTableList) {
            UnresolvedReferenceScanner scanner = new UnresolvedReferenceScanner(symbolTable);
            List<UnresolvedReferenceScanner.UnresolvedReferenceInfo> unresolvedReferenceInfoList = scanner.findUnresolvedReference();
            unresolvedReferenceInfoList.forEach(unsolvedType -> System.err.println("[UnresolvedReferenceScanner] unsolvedType = " + unsolvedType.className));
            allUnresolvedReferenceInfoList.addAll(unresolvedReferenceInfoList);
        }
        return allUnresolvedReferenceInfoList;
    }

    public List<NullabilityChecker.NullabilityIssue> checkNullability() {
        if (parseTree == null || !symbolsAnalyzed) {
            throw new IllegalStateException("Call process() and analyzeSymbols() first.");
        }

        NullabilityChecker nullabilityChecker = new NullabilityChecker(globalSymbolTable, sourceMappingEntrySet, javaProcessor.getMethodInvocationManager().get(0));
        nullabilityChecker.visit(parseTree);
        nullabilityChecked = true;
        return nullabilityChecker.getIssues();
    }

    public String generateJavaCode() {
        if (!nullabilityChecked || !symbolsAnalyzed) {
            throw new IllegalStateException("Must perform nullability check and symbol analysis first.");
        }

        String generated = parseTree.getText();
        int startIndex = parseTree.start.getStartIndex();
        int stopIndex = parseTree.stop.getStopIndex();

        String startWhiteSpace = originalText.substring(0, startIndex);
        String fullyGenerated = startWhiteSpace + generated;
        System.err.println("fullyGenerated = " + fullyGenerated);

//        TextChangeRange generatedRange = Utils.computeTextChangeRange(originalText, startIndex, stopIndex);
        FragmentedText fragmentedText = new FragmentedText(fullyGenerated);

        BoilerplateCodeGenerator generator = new BoilerplateCodeGenerator(symbolTable, fragmentedText);
        generator.visit(parseTree);
//
//        FragmentedText fragmentedTextForOriginalText = new FragmentedText(originalTextRange, originalText);
//        fragmentedTextForOriginalText.update(generatedRange, javaCode);
//        return fragmentedTextForOriginalText.toString();
        return generator.generate();
    }

    public String compile() {
        try {
            process();
            analyzeSymbols();
            checkNullability();
            return generateJavaCode();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return null;
    }

    private void assertAnalyzed() {
        if (!symbolsAnalyzed) {
            throw new IllegalStateException("analyzeSymbol() not called.");
        }
    }

    public String getClassName() {
        assertAnalyzed();
        return className;
    }

    public String getPackageName() {
        assertAnalyzed();
        return packageName;
    }

    public String getFullyQualifiedName() {
        assertAnalyzed();
        return Optional.ofNullable(packageName).map(packageName -> packageName + "." + className).orElse(className);
    }

    public JPlusParserRuleContext getParseTree() {
        return parseTree;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public SymbolTable getGlobalSymbolTable() { return globalSymbolTable; }
}
