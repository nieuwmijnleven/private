package jplus.analyzer;

import jplus.base.Project;
import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.base.TypeInfo;
import jplus.processor.JPlusProcessor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SymbolResolver {
    private final Project project;
    private final SymbolTable globalSymbolTable;
    private List<Path> srcDirPathList;

    public SymbolResolver(Project project, SymbolTable globalSymbolTable) {
        this.project = project;
        this.globalSymbolTable = globalSymbolTable;
        this.srcDirPathList = new ArrayList<>();
    }

    public void addSrcDirPath(Path srcDirPath) {
        this.srcDirPathList.add(srcDirPath);
    }

    public void setSrcDirPathList(List<Path> srcDirPathList) {
        this.srcDirPathList = srcDirPathList;
    }

    public void resolveSymbolTable(SymbolTable symbolTable) {

    }

//    public SymbolTable resolveSymbol(String symbol, TypeInfo.Type type) throws Exception {
//
//
//        SymbolTable symbolTable = resolveSymbolFromSource(symbol)
//    }


    public SymbolTable resolveSymbolFromSource(Path src, String symbol, TypeInfo.Type type) throws Exception {
        if (globalSymbolTable.contains(symbol, type)) {
            SymbolInfo symInfo = globalSymbolTable.resolve(symbol);
            return symInfo.getSymbolTable();
        }

        Path filePath = src.resolve(symbol + ".jplus");
        if (!filePath.toFile().exists()) {
            filePath = src.resolve(symbol + ".java");
            if (!filePath.toFile().exists()) {
                return null;
            }
        }

        JPlusProcessor processor = new JPlusProcessor(filePath);
        processor.process();
        processor.analyzeSymbols();

        SymbolTable globalTable = processor.getGlobalSymbolTable();
        globalSymbolTable.merge(globalTable);

        return processor.getSymbolTable();
    }

    public SymbolTable resolveSymbolFromJavaLibs(Path src, String symbol, TypeInfo.Type type) throws Exception {
        if (globalSymbolTable.contains(symbol, type)) {
            SymbolInfo symInfo = globalSymbolTable.resolve(symbol);
            return symInfo.getSymbolTable();
        }

        Path filePath = src.resolve(symbol + ".jplus");
        if (!filePath.toFile().exists()) {
            filePath = src.resolve(symbol + ".java");
            if (!filePath.toFile().exists()) {
                return null;
            }
        }

        JPlusProcessor processor = new JPlusProcessor(filePath);
        processor.process();
        processor.analyzeSymbols();

        SymbolTable globalTable = processor.getGlobalSymbolTable();
        globalSymbolTable.merge(globalTable);

        return processor.getSymbolTable();
    }
}
