package jplus.processor;

import jplus.analyzer.NullabilityChecker;
import jplus.analyzer.SymbolAnalyzer;
import jplus.base.JPlus20Lexer;
import jplus.base.JPlus20Parser;
import jplus.base.SymbolTable;
import jplus.generator.BoilerplateCodeGenerator;
import jplus.generator.JPlusParserRuleContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.List;

public class JPlusProcessor {

    private final String text;
    private JPlus20Parser parser;
    private JPlusParserRuleContext parseTree;
    private SymbolTable symbolTable;
    private boolean nullabilityChecked = false;
    private boolean symbolsAnalyzed = false;

    public JPlusProcessor(String text) {
        this.text = text;
    }

    /**
     * 1️⃣ Parsing 단계: parseTree 생성
     */
    public void process() throws Exception {
        CharStream input = CharStreams.fromString(text);
        JPlus20Lexer lexer = new JPlus20Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser = new JPlus20Parser(tokens);

        parseTree = parser.start_();
    }

    /**
     * 2️⃣ Nullability 체크
     * @return true if passed
     */
    public List<NullabilityChecker.NullabilityIssue> checkNullability() {
        if (parseTree == null) {
            throw new IllegalStateException("Call process() first.");
        }

        NullabilityChecker nullabilityChecker = new NullabilityChecker();
        nullabilityChecker.visit(parseTree);
        nullabilityChecked = true;
        return nullabilityChecker.getIssues();
    }

    /**
     * 3️⃣ Symbol 분석
     */
    public void analyzeSymbols() {
        if (parseTree == null) {
            throw new IllegalStateException("Call process() first.");
        }

        SymbolAnalyzer symbolAnalyzer = new SymbolAnalyzer();
        symbolAnalyzer.visit(parseTree);
        symbolTable = symbolAnalyzer.getTopLevelSymbolTable();
        symbolsAnalyzed = true;
    }

    /**
     * 4️⃣ Boilerplate 코드 생성
     */
    public String generateJavaCode() {
        if (!nullabilityChecked || !symbolsAnalyzed) {
            throw new IllegalStateException("Must perform nullability check and symbol analysis first.");
        }
        parseTree.getText();
        BoilerplateCodeGenerator generator = new BoilerplateCodeGenerator(symbolTable);
        generator.visit(parseTree);
        return generator.generate();
    }

    public String compile() {
        try {
            process();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        checkNullability();
        analyzeSymbols();
        return generateJavaCode();
    }

    // Getter
    public JPlusParserRuleContext getParseTree() {
        return parseTree;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }
}
