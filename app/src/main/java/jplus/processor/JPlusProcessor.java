package jplus.processor;

import jplus.analyzer.NullabilityChecker;
import jplus.analyzer.UnresolvedReferenceScanner;
import jplus.base.JPlus20Lexer;
import jplus.base.JPlus20Parser;
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
import java.util.List;
import java.util.Set;

public class JPlusProcessor {
    private final String originalText;
    private final TextChangeRange originalTextRange;
    private JPlus20Parser parser;
    private Set<SourceMappingEntry> sourceMappingEntrySet;
    private List<Path> srcDirPathList;
    private JPlusParserRuleContext parseTree;
    private JavaProcessor javaProcessor;
    private SymbolTable globalSymbolTable;
    private SymbolTable symbolTable;
    private boolean nullabilityChecked = false;
    private boolean symbolsAnalyzed = false;

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

    public void analyzeSymbols() {
        if (parseTree == null) {
            throw new IllegalStateException("Call process() first.");
        }

//        SymbolAnalyzer symbolAnalyzer = new SymbolAnalyzer(globalSymbolTable);
//        symbolAnalyzer.visit(parseTree);
//        symbolTable = symbolAnalyzer.getTopLevelSymbolTable();
        javaProcessor.analyzeSymbols();
        symbolTable = javaProcessor.getSymbolTable();
        symbolsAnalyzed = true;

        UnresolvedReferenceScanner scanner = new UnresolvedReferenceScanner(symbolTable, "jplus.example");
        List<Path> unsolvedSrcPathList = scanner.findUnresolvedReference();
        unsolvedSrcPathList.forEach(path -> System.err.println("[UnresolvedReferenceScanner] unsolvedSrcPath = " + path));
    }

    public List<NullabilityChecker.NullabilityIssue> checkNullability() {
        if (parseTree == null || !symbolsAnalyzed) {
            throw new IllegalStateException("Call process() and analyzeSymbols() first.");
        }

        NullabilityChecker nullabilityChecker = new NullabilityChecker(globalSymbolTable, sourceMappingEntrySet, javaProcessor.getMethodInvocationManager());
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

    public JPlusParserRuleContext getParseTree() {
        return parseTree;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public SymbolTable getGlobalSymbolTable() { return globalSymbolTable; }
}
