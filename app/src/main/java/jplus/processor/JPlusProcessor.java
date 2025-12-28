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

import jplus.analyzer.NullabilityChecker;
import jplus.analyzer.UnresolvedReferenceScanner;
import jplus.base.JPlus25Lexer;
import jplus.base.JPlus25Parser;
import jplus.base.Project;
import jplus.base.SymbolTable;
import jplus.generator.BoilerplateCodeGenerator;
import jplus.generator.CodeGenContext;
import jplus.generator.JPlusParserRuleContext;
import jplus.generator.SourceMappingEntry;
import jplus.editor.FragmentedText;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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

    private final Project project;
    private final String originalText;

    private JPlus25Parser parser;
    private JPlusParserRuleContext parseTree;
    private Set<SourceMappingEntry> sourceMappingEntrySet;

    private JavaProcessor javaProcessor;
    private final SymbolTable globalSymbolTable;
    private SymbolTable symbolTable;

    private String className;
    private String packageName;

    private boolean processed = false;
    private boolean nullabilityChecked = false;
    private boolean symbolsAnalyzed = false;

    public JPlusProcessor(Project project, String originalText, SymbolTable globalSymbolTable) {
        this.project = project;
        this.originalText = originalText;
        this.globalSymbolTable = globalSymbolTable;
    }

    public JPlusProcessor(Project project, String packageName, String className) throws Exception {
        this(
                project,
                Files.readString(
                        packageName == null || packageName.isBlank()
                                ? project.getSrcDir().resolve(className + ".jplus")
                                : project.getSrcDir()
                                .resolve(packageName.replace(".", "/"))
                                .resolve(className + ".jplus"),
                        StandardCharsets.UTF_8
                ),
                new SymbolTable(null)
        );
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

        CodeGenContext.push();
        try {
            CharStream input = CharStreams.fromString(originalText);
            JPlus25Lexer lexer = new JPlus25Lexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new JPlus25Parser(tokens);

            parseTree = parser.start_();
            processed = true;

            runInitialJavaProcessing();
        } finally {
            CodeGenContext.pop();
        }
    }

    // --------------------------------------------------------------
    // Java intermediate code generation (no boilerplate)
    // --------------------------------------------------------------

    private String generateJavaCodeWithoutBoilerplate() {
        assertProcessed();
        CodeGenContext ctx = CodeGenContext.current();
        if (ctx != null) ctx.setFragmentedText(new FragmentedText(originalText));
        return parseTree.getText();
    }

    private void runInitialJavaProcessing() throws Exception {
        String javaCode = generateJavaCodeWithoutBoilerplate();
        System.err.println("[JPlusProcessor][runInitialJavaProcessing] javaCode = " + javaCode);
        CodeGenContext ctx = CodeGenContext.current();
        sourceMappingEntrySet = ctx.getFragmentedText().buildSourceMap();

        javaProcessor = new JavaProcessor(javaCode, globalSymbolTable);
        javaProcessor.process();
    }

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
            //unresolvedReferenceInfoList.forEach(unsolvedType -> System.err.println("[UnresolvedReferenceScanner] unsolvedType = " + unsolvedType.className));
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
                //System.err.println("[resolveAllUnresolvedReferences] javaCode = " + javaCode);
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
        //System.err.println("fullyGenerated = " + fullyGenerated);

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

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public SymbolTable getGlobalSymbolTable() {
        return globalSymbolTable;
    }
}
