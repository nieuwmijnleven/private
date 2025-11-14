package jplus.analyzer;

import jplus.base.SymbolTable;
import jplus.processor.JPlusProcessor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.nio.file.Path;

public class SymbolResolver {
    private final SymbolTable globalSymbolTable;

    public SymbolResolver(SymbolTable globalSymbolTable) {
        this.globalSymbolTable = globalSymbolTable;
    }

    public void resolveSymbolTable(SymbolTable symbolTable) {

    }

    public SymbolTable resolveSymbol(Path src, String symbol) throws Exception {
        Path filePath = src.resolve(symbol + ".jplus");
        JPlusProcessor processor = new JPlusProcessor(filePath);
        processor.process();
        processor.analyzeSymbols();

        SymbolTable globalTable = processor.getGlobalSymbolTable();
        globalSymbolTable.merge(globalTable);

        return processor.getSymbolTable();
    }

}
