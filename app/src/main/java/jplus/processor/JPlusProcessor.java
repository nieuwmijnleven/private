package jplus.processor;

import jplus.analyzer.NullabilityChecker;
import jplus.analyzer.UnresolvedReferenceScanner;
import jplus.base.JPlus20Lexer;
import jplus.base.JPlus20Parser;
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
import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * Performs:
 * 1. Parse JPlus source → ANTLR AST
 * 2. Generate intermediate Java
 * 3. Java symbol analysis (recursive unresolved resolution)
 * 4. Nullability check
 * 5. Boilerplate Java code generation
 */
public class JPlusProcessor {

    private Project project;
    private final String originalText;
    private final TextChangeRange originalTextRange;
    private SymbolTable globalSymbolTable;

    private JPlus20Parser parser;
    private JPlusParserRuleContext parseTree;
    private Set<SourceMappingEntry> sourceMappingEntrySet;

    private JavaProcessor javaProcessor;
    private SymbolTable symbolTable;

    private String className;
    private String packageName;

    private boolean processed = false;
    private boolean nullabilityChecked = false;
    private boolean symbolsAnalyzed = false;

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
        this(null, originalText, new SymbolTable(null));
    }

    public JPlusProcessor(String originalText, SymbolTable globalSymbolTable) {
        this(null, originalText, globalSymbolTable);
    }

    public JPlusProcessor(Path filePath) throws Exception {
        this(Files.readString(filePath, StandardCharsets.UTF_8), new SymbolTable(null));
    }

    public JPlusProcessor(Path filePath, SymbolTable globalSymbolTable) throws Exception {
        this(Files.readString(filePath, StandardCharsets.UTF_8), globalSymbolTable);
    }

    // --------------------------------------------------------------
    // Main processing pipeline
    // --------------------------------------------------------------

    public void process() throws Exception {
        if (processed) return;

        CharStream input = CharStreams.fromString(originalText);
        JPlus20Lexer lexer = new JPlus20Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser = new JPlus20Parser(tokens);

        parseTree = parser.start_();
        processed = true;

        runInitialJavaProcessing();
    }

    // --------------------------------------------------------------
    // Java intermediate code generation (no boilerplate)
    // --------------------------------------------------------------

    private String generateJavaCodeWithoutBoilerplate() {
        assertProcessed();
        CodeGenContext ctx = CodeGenContext.current();
        ctx.setFragmentedText(new FragmentedText(originalText));

        String text = parseTree.getText();
        sourceMappingEntrySet = ctx.getFragmentedText().buildSourceMap();
        return text;
    }

    private void runInitialJavaProcessing() throws Exception {
        String javaCode = generateJavaCodeWithoutBoilerplate();
        javaProcessor = new JavaProcessor(javaCode, globalSymbolTable);
        javaProcessor.process();
    }

    /*public String generateJavaCodeWithoutBoilerplate() {
        if (parseTree == null) {
            throw new IllegalStateException("Call process() first.");
        }
        CodeGenContext codeGenContext = CodeGenContext.current();
        codeGenContext.setFragmentedText(new FragmentedText(originalText));
        return parseTree.getText();
    }*/

    public String getParseTreeString() {
        if (parseTree == null) {
            throw new IllegalStateException("Call process() first.");
        }
        return parseTree.toStringTree(parser);
    }

    // --------------------------------------------------------------
    // Symbol Analysis (recursive unresolved resolution)
    // --------------------------------------------------------------

    public void analyzeSymbols() throws Exception {
        assertProcessed();
        if (symbolsAnalyzed) return;

        javaProcessor.analyzeSymbols();
        symbolsAnalyzed = true;

        updateSymbolInfoFrom(javaProcessor);
        resolveAllUnresolvedReferences();
    }

    private void updateSymbolInfoFrom(JavaProcessor processor) {
        symbolTable = processor.getSymbolTable().get(0);
        className = symbolTable.resolve("^TopLevelClass$").getSymbol();
        packageName = symbolTable.resolve("^PackageName$").getSymbol();
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

    private void resolveAllUnresolvedReferences() throws Exception {
        List<InMemoryJavaFile> inMemoryJavaFiles = new ArrayList<>();
        inMemoryJavaFiles.add(new InMemoryJavaFile(getFullyQualifiedName(), javaProcessor.getSource()));

        symbolsAnalyzed = false;
        while(true) {
            var unresolvedList = collectUnresolvedReferenceInfo(javaProcessor.getSymbolTable());
            if (unresolvedList.isEmpty()) break;

            for (var unresolved : unresolvedList) {
                JPlusProcessor dependency = new JPlusProcessor(project, unresolved.packageName, unresolved.className);
                dependency.process();
                String javaCode = dependency.generateJavaCodeWithoutBoilerplate();
                System.err.println("[resolveAllUnresolvedReferences] javaCode = " + javaCode);
                inMemoryJavaFiles.add(new InMemoryJavaFile(unresolved.getFullyQualifiedName(), javaCode));
            }

            javaProcessor = new JavaProcessor(inMemoryJavaFiles, globalSymbolTable);
            javaProcessor.process();
            javaProcessor.analyzeSymbols();
            symbolTable = javaProcessor.getSymbolTable().get(0);
        }
        symbolsAnalyzed = true;
    }

    public List<NullabilityChecker.NullabilityIssue> checkNullability() {
        assertAnalyzed();

        NullabilityChecker nullabilityChecker = new NullabilityChecker(globalSymbolTable, sourceMappingEntrySet, javaProcessor.getMethodInvocationManager().get(0));
        nullabilityChecker.visit(parseTree);
        nullabilityChecked = true;
        return nullabilityChecker.getIssues();
    }

    public String generateJavaCode() {
        if (!symbolsAnalyzed || !nullabilityChecked) {
            throw new IllegalStateException("Must perform nullability check and symbol analysis first.");
        }

        String generated = parseTree.getText();
        int startIndex = parseTree.start.getStartIndex();
        String startWhiteSpace = originalText.substring(0, startIndex);
        String fullyGenerated = startWhiteSpace + generated;
        System.err.println("fullyGenerated = " + fullyGenerated);

        FragmentedText fragmentedText = new FragmentedText(fullyGenerated);
        BoilerplateCodeGenerator generator = new BoilerplateCodeGenerator(symbolTable, fragmentedText);
        generator.visit(parseTree);
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
            return null;
        }
    }

    // --------------------------------------------------------------
    // Assertions
    // --------------------------------------------------------------

    private void assertProcessed() {
        if (!processed) throw new IllegalStateException("Call process() first.");
    }

    private void assertAnalyzed() {
        if (!symbolsAnalyzed) throw new IllegalStateException("analyzeSymbol() not called.");
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
        return packageName == null ? className : packageName + "." + className;
    }

    public JPlusParserRuleContext getParseTree() {
        return parseTree;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public SymbolTable getGlobalSymbolTable() {
        return globalSymbolTable;
    }
}
