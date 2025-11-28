package jplus.analyzer;

import jplus.base.JPlus20Parser;
import jplus.base.JPlus20ParserBaseVisitor;
import jplus.base.JavaMethodInvocationManager;
import jplus.base.MethodInvocationInfo;
import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.base.TypeInfo;
import jplus.generator.SourceMappingEntry;
import jplus.generator.TextChangeRange;
import jplus.util.ConstructorUtils;
import jplus.util.Utils;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class NullabilityChecker extends JPlus20ParserBaseVisitor<Void> {

    private final SymbolTable globalSymbolTable;
    private final Set<SourceMappingEntry> sourceMappingEntrySet;
    private JavaMethodInvocationManager methodInvocationManager;
    private SymbolTable currentSymbolTable;
    private String originalText;
    private String packageName;
    private List<Path> srcDirPathList;
    private boolean hasPassed = true;

    public static class NullabilityIssue {
        private final int line;
        private final int column;
        private final int offset;
        private final String message;

        public NullabilityIssue(int line, int column, int offset, String message) {
            this.line = line;
            this.column = column;
            this.offset = offset;
            this.message = message;
        }

        public int getLine() { return line; }
        public int getColumn() { return column; }
        public int getOffset() { return offset; }
        public String getMessage() { return message; }

        @Override
        public String toString() {
            return String.format("Error: (line:%d, column:%d) %s", line, column, message);
        }
    }

    public NullabilityChecker(SymbolTable globalSymbolTable, Set<SourceMappingEntry> sourceMappingEntrySet, JavaMethodInvocationManager methodInvocationManager) {
        this.globalSymbolTable = globalSymbolTable;
        this.sourceMappingEntrySet = sourceMappingEntrySet;
        this.methodInvocationManager = methodInvocationManager;
        this.currentSymbolTable = globalSymbolTable;
        this.srcDirPathList = new ArrayList<>();
    }

    private final List<NullabilityIssue> issues = new ArrayList<>();

    public List<NullabilityIssue> getIssues() {
        return issues;
    }

    public void addSrcDirPath(Path srcDirPath) {
        this.srcDirPathList.add(srcDirPath);
    }

    public void setSrcDirPathList(List<Path> srcDirPathList) {
        this.srcDirPathList = srcDirPathList;
    }

    @Override
    public Void visitStart_(JPlus20Parser.Start_Context ctx) {
        this.originalText = ctx.start.getInputStream().toString();
        return super.visitStart_(ctx);
    }

    @Override
    public Void visitPackageDeclaration(JPlus20Parser.PackageDeclarationContext ctx) {
        this.packageName = ctx.identifier().stream().map(Utils::getTokenString).collect(Collectors.joining("."));
        System.err.println("packageName = " + packageName);
        return super.visitPackageDeclaration(ctx);
    }

    @Override
    public Void visitTopLevelClassOrInterfaceDeclaration(JPlus20Parser.TopLevelClassOrInterfaceDeclarationContext ctx) {
        String className = Utils.getTokenString(ctx.classDeclaration().normalClassDeclaration().typeIdentifier());
        if (this.packageName != null) {
            className = this.packageName + "." + className;
        }
        System.err.println("className = " + className);

        SymbolInfo classSymbolInfo = globalSymbolTable.resolveInCurrent(className);
        if (classSymbolInfo == null) {
            throw new IllegalStateException("Symbol not found: " + className);
        }
        currentSymbolTable = classSymbolInfo.getSymbolTable();

        return super.visitTopLevelClassOrInterfaceDeclaration(ctx);
    }

    @Override
    public Void visitClassDeclaration(JPlus20Parser.ClassDeclarationContext ctx) {
        if (ctx.normalClassDeclaration() != null) {
            String className = Utils.getTokenString(ctx.normalClassDeclaration().typeIdentifier());
//            if (this.packageName != null) {
//                className = this.packageName + "." + className;
//            }
            System.err.println("[NullabilityChecker][ClassDecl] className = " + className);
            System.err.println("[NullabilityChecker][ClassDecl] currentSymbolTable = " + currentSymbolTable);
            currentSymbolTable = currentSymbolTable.getEnclosingSymbolTable(className);
        } else if (ctx.enumDeclaration() != null) {

        } else if (ctx.recordDeclaration() != null) {

        }

        super.visitClassDeclaration(ctx);
        currentSymbolTable = currentSymbolTable.getParent();
        return null;
    }

    @Override
    public Void visitConstructorDeclaration(JPlus20Parser.ConstructorDeclarationContext ctx) {
        List<String> typeNameList = new ArrayList<>();
        var formalParameterList = ctx.constructorDeclarator().formalParameterList().formalParameter();
        for (var formalParameterContext : formalParameterList) {
            boolean hasNullableAnnotation = false;
            if (formalParameterContext.variableModifier() != null) {
                for (var variableModifierContext : formalParameterContext.variableModifier()) {
                    if (variableModifierContext.annotation() != null) {
                        String annotation = Utils.getTokenString(variableModifierContext.annotation());
//                        System.err.println("annotation = " + annotation);
                        if ("@Nullable".equals(annotation)) {
                            hasNullableAnnotation = true;
                        }
                    }
                }
            }

            String typeName = Utils.getTokenString(formalParameterContext.unannType());
            typeNameList.add(typeName + (hasNullableAnnotation ? "?" : ""));
        }

        String symbolName = "^constructor$_" + typeNameList.stream().collect(Collectors.joining("_"));
        currentSymbolTable = currentSymbolTable.getEnclosingSymbolTable(symbolName);
        super.visitConstructorDeclaration(ctx);
        currentSymbolTable = currentSymbolTable.getParent();
        return null;
    }

    @Override
    public Void visitMethodDeclaration(JPlus20Parser.MethodDeclarationContext ctx) {
        List<String> typeNameList = new ArrayList<>();
        var methodDeclarator = ctx.methodHeader().methodDeclarator();
        var formalParameterList = methodDeclarator.formalParameterList() != null ? methodDeclarator.formalParameterList().formalParameter() : new ArrayList<JPlus20Parser.FormalParameterContext>();
        for (var formalParameterContext : formalParameterList) {
            boolean hasNullableAnnotation = false;
            for (var variableModifierContext : formalParameterContext.variableModifier()) {
                String annotation = Utils.getTokenString(variableModifierContext.annotation());
                //System.err.println("annotation = " + annotation);
                if ("@Nullable".equals(annotation)) {
                    hasNullableAnnotation = true;
                }
            }

            String typeName = Utils.getTokenString(formalParameterContext.unannType());
            typeNameList.add(typeName + (hasNullableAnnotation ? "?" : ""));
        }

        String methodName = Utils.getTokenString(ctx.methodHeader().methodDeclarator().identifier());
        String symbolName = "^" + methodName + "$_" + typeNameList.stream().collect(Collectors.joining("_"));
        System.err.println("[MethodDecl] methodName = " + symbolName);
        System.err.println("[MethodDecl] currentSymbolTable = " + currentSymbolTable);

        Optional<SymbolInfo> methodSymbolInfo = Optional.ofNullable(currentSymbolTable.resolveInCurrent(symbolName));
        String fqnSymbolName = methodSymbolInfo.orElseThrow(() -> new IllegalStateException()).getSymbol();
        System.err.println("[MethodDecl] fqnSymbolName = " + fqnSymbolName);

        currentSymbolTable = currentSymbolTable.getEnclosingSymbolTable(fqnSymbolName);
        super.visitMethodDeclaration(ctx);
        currentSymbolTable = currentSymbolTable.getParent();
        return null;
    }

    @Override
    public Void visitFieldDeclaration(JPlus20Parser.FieldDeclarationContext ctx) {
        for (var variableDeclaratorContext : ctx.variableDeclaratorList().variableDeclarator()) {
            String symbol = Utils.getTokenString(variableDeclaratorContext.variableDeclaratorId());
            SymbolInfo symbolInfo = currentSymbolTable.resolve(symbol);
            System.err.println("[NullabilityChecker][FieldDecl] currentSymbolTable = " + currentSymbolTable);
            TypeInfo typeInfo = symbolInfo.getTypeInfo();

            if (variableDeclaratorContext.variableInitializer() != null) {
                String expression = Utils.getTokenString(variableDeclaratorContext.variableInitializer());

                if (!typeInfo.isNullable() && "null".equals(expression)) {
                    reportNullableAccessIssue(ctx.getStart(), symbol + " is a non-nullable variable. But null value is assigned to it.");
                }
            }
        }
        return super.visitFieldDeclaration(ctx);
    }

    @Override
    public Void visitBlock(JPlus20Parser.BlockContext ctx) {
        currentSymbolTable = currentSymbolTable.getEnclosingSymbolTable("^block$");
        super.visitBlock(ctx);
        currentSymbolTable = currentSymbolTable.getParent();
        return null;
    }

    @Override
    public Void visitConstructorBody(JPlus20Parser.ConstructorBodyContext ctx) {
        currentSymbolTable = currentSymbolTable.getEnclosingSymbolTable("^block$");
        super.visitConstructorBody(ctx);
        currentSymbolTable = currentSymbolTable.getParent();
        return null;
    }

    @Override
    public Void visitExpressionName(JPlus20Parser.ExpressionNameContext ctx) {
        var ambiguousNameCtx = ctx.ambiguousName();
        SymbolInfo symbolInfo = null;
        SymbolTable symbolTable = currentSymbolTable;
        while (ambiguousNameCtx != null) {
            String symbol = Utils.getTokenString(ambiguousNameCtx.identifier());
            symbolInfo = symbolTable.resolve(symbol);
            TypeInfo typeInfo = symbolInfo.getTypeInfo();

            SymbolResolver resolver = new SymbolResolver(globalSymbolTable);
            try {
                symbolTable = resolver.resolveSymbolFromSource(srcDirPathList.get(0), typeInfo.getName(), typeInfo.getType());
                SymbolInfo symInfo = symbolTable.resolve("^TopLevelClass$");
                String className = symInfo.getSymbol();
                symbolTable = symbolTable.getEnclosingSymbolTable(className);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }

            if (symbolInfo.getTypeInfo().isNullable && ambiguousNameCtx.DOT() != null) {
                int line = ambiguousNameCtx.start.getLine();
                int column = ambiguousNameCtx.start.getCharPositionInLine();
                int offset = ambiguousNameCtx.start.getStartIndex();
                String identifier = Utils.getTokenString(ambiguousNameCtx.ambiguousName().identifier());
                String msg = symbol + " is a nullable variable. But it directly accesses " + identifier + ". Consider using null-safe operator(?.).";
                issues.add(new NullabilityIssue(line, column, offset, msg));
                hasPassed = false;
            }

            ambiguousNameCtx = ambiguousNameCtx.ambiguousName();
        }

        if (symbolInfo != null && symbolInfo.getTypeInfo().isNullable && ctx.DOT() != null) {
            int line = ctx.start.getLine();
            int column = ctx.start.getCharPositionInLine();
            int offset = ctx.start.getStartIndex();
            String identifier = Utils.getTokenString(ctx.identifier());
            String msg = symbolInfo.getSymbol() + " is a nullable variable. But it directly accesses " + identifier + ". Consider using null-safe operator(?.).";
            issues.add(new NullabilityIssue(line, column, offset, msg));
            hasPassed = false;
        }
        return super.visitExpressionName(ctx);
    }

    /*@Override
    public Void visitUnqualifiedClassInstanceCreationExpression(JPlus20Parser.UnqualifiedClassInstanceCreationExpressionContext ctx) {
        String instanceCreationCode = Utils.getTokenString(ctx);
        System.err.println("[InstanceCreationExpression] intanceCreationCode: " + instanceCreationCode);
        TextChangeRange instanceCreationCodeRange = Utils.computeTextChangeRange(this.originalText, ctx.start.getStartIndex(), ctx.stop.getStopIndex());
//        TextChangeRange invocationCodeRange = Utils.getTextChangeRange(this.originalText, ctx);
        System.err.println("[InstanceCreationExpression] invocationCodeRange: " + instanceCreationCodeRange);

        Optional<TextChangeRange> javaInvocationCodeRange = sourceMappingEntrySet.stream()
                .peek(sourceMappingEntry -> System.err.println("[InstanceCreationExpression] originalRange = " + sourceMappingEntry.getOriginalRange()))
                .filter(sourceMappingEntry -> instanceCreationCodeRange.equals(sourceMappingEntry.getOriginalRange()))
                .map(SourceMappingEntry::getTransformedRange)
                .findFirst();

        javaInvocationCodeRange.ifPresent(range -> {
            System.err.println("[InstanceCreationExpression] javaInvocationCodeRange: " + range);
            Optional<MethodInvocationInfo> invocationInfo = methodInvocationManager.findInvocationInfo(currentSymbolTable, range);

            invocationInfo.ifPresent(info -> {
                System.err.println("[InstanceCreationExpression] = " + info);

                Optional<SymbolInfo> classSymbolInfo = Optional.ofNullable(info.instanceName).map(className -> currentSymbolTable.resolve(className));

                classSymbolInfo.ifPresent(symbolInfo -> {
                    System.err.println("[InstanceCreationExpression] symbolInfo = " + symbolInfo);
                    System.err.println("[InstanceCreationExpression] symbolTable = " + symbolInfo.getSymbolTable());
                    SymbolTable symbolTable = symbolInfo.getSymbolTable();
                    SymbolTable classSymbolTable = symbolTable.getEnclosingSymbolTable(symbolInfo.getSymbol());
                    System.err.println("[InstanceCreationExpression] enclosingSymbolTable = " + classSymbolTable);

                    //select candidates
                    List<String> candidates = ConstructorUtils.getCandidates(info.paramTypes);
                    candidates.forEach(candidate -> System.err.println("[InstanceCreationExpression] candidate = " + candidate));

                    SymbolInfo constructorSymbolInfo = null;
                    for (String candidate : candidates) {
                        constructorSymbolInfo = classSymbolTable.resolveInCurrent(candidate);
                        if (constructorSymbolInfo != null) {
                            System.err.println("found consturctor: " + constructorSymbolInfo);
                            break;
                        }
                    }

                    if (constructorSymbolInfo != null) {
                        String[] paramTypes = constructorSymbolInfo.getSymbol().substring("^constructor$_".length()).split("_");
                        for (int i = 0; i < paramTypes.length; i++) {
                            String paramType = paramTypes[i];
                            String argType = info.argTypes.get(i);
                            System.err.println("paramType = " + paramType + ", argType = " + argType);
                            if (!paramType.endsWith("?") && "<nulltype>".equals(argType)) {
                                int argIndex = i + 1;
                                String suffix = getOrdinalSuffix(argIndex);
                                String msg = "The " + argIndex + suffix + " argument of the " + info.instanceName + " constructor is a non-nullable variable, but a null value is assigned to it.";
                                reportNullableAccessIssue(ctx.start, msg);
                            }
                        }
                    }
                });
            });
        });

        return super.visitUnqualifiedClassInstanceCreationExpression(ctx);
    }*/

    private TextChangeRange getCodeRange(ParserRuleContext ctx) {
        String code = Utils.getTokenString(ctx);
        log("[InstanceCreationExpression] code: " + code);

        TextChangeRange codeRange = Utils.getTextChangeRange(originalText, ctx);
        log("[InstanceCreationExpression] codeRange: " + codeRange);

        return codeRange;
    }

    private Optional<TextChangeRange> findTransformedRange(TextChangeRange instanceRange) {
        return sourceMappingEntrySet.stream()
                .peek(entry -> log("[InstanceCreationExpression] originalRange = " + entry.getOriginalRange()))
                .filter(entry -> instanceRange.equals(entry.getOriginalRange()))
                .map(SourceMappingEntry::getTransformedRange)
                .findFirst();
    }

    private Optional<SymbolInfo> resolveClassSymbol(MethodInvocationInfo info) {
        return Optional.ofNullable(info.instanceName)
                .map(className -> {
                    SymbolInfo symbolInfo = currentSymbolTable.resolve(className);
                    log("[InstanceCreationExpression] classSymbolInfo = " + symbolInfo);
                    return symbolInfo;
                });
    }

    private SymbolTable resolveClassSymbolTable(SymbolInfo symbolInfo) {
        SymbolTable symbolTable = symbolInfo.getSymbolTable();
        SymbolTable classSymbolTable = symbolTable.getEnclosingSymbolTable(symbolInfo.getSymbol());
        log("[InstanceCreationExpression] enclosingSymbolTable = " + classSymbolTable);
        return classSymbolTable;
    }

    private Optional<SymbolInfo> resolveConstructor(SymbolTable classSymbolTable, MethodInvocationInfo info) {
        List<String> candidates = ConstructorUtils.getCandidates(info.paramTypes);
        //candidates.forEach(c -> log("[InstanceCreationExpression] candidate = " + c));

        for (String candidate : candidates) {
            SymbolInfo constructor = classSymbolTable.resolveInCurrent(candidate);
            if (constructor != null) {
                log("[InstanceCreationExpression] found constructor: " + constructor);
                return Optional.of(constructor);
            }
        }
        return Optional.empty();
    }

    private void validateConstructorArguments(
            JPlus20Parser.UnqualifiedClassInstanceCreationExpressionContext ctx,
            MethodInvocationInfo info,
            SymbolInfo constructorSymbolInfo
    ) {
        String raw = constructorSymbolInfo.getSymbol().substring("^constructor$_".length());
        String[] paramTypes = raw.split("_");

        for (int i = 0; i < paramTypes.length; i++) {
            String paramType = paramTypes[i];
            String argType = info.argTypes.get(i);
            log("paramType = " + paramType + ", argType = " + argType);

            if (isInvalidNullAssignment(paramType, argType)) {
                reportInvalidNull(ctx, info.instanceName, i + 1);
            }
        }
    }

    private boolean isInvalidNullAssignment(String paramType, String argType) {
        return !paramType.endsWith("?") && "<nulltype>".equals(argType);
    }

    private void reportInvalidNull(
            JPlus20Parser.UnqualifiedClassInstanceCreationExpressionContext ctx,
            String className,
            int argIndex
    ) {
        String suffix = getOrdinalSuffix(argIndex);
        String msg = "The " + argIndex + suffix +
                " argument of the " + className +
                " constructor is a non-nullable variable, but a null value is assigned to it.";
        reportNullableAccessIssue(ctx.start, msg);
    }

    private void log(String msg) {
        System.err.println(msg);
    }

    @Override
    public Void visitUnqualifiedClassInstanceCreationExpression(JPlus20Parser.UnqualifiedClassInstanceCreationExpressionContext ctx) {
        TextChangeRange codeRange = getCodeRange(ctx);
        Optional<TextChangeRange> transformedRange = findTransformedRange(codeRange);
        if (transformedRange.isEmpty()) {
            return super.visitUnqualifiedClassInstanceCreationExpression(ctx);
        }

        Optional<MethodInvocationInfo> invocationInfo = methodInvocationManager.findInvocationInfo(currentSymbolTable, transformedRange.get());
        if (invocationInfo.isEmpty()) {
            return super.visitUnqualifiedClassInstanceCreationExpression(ctx);
        }

        MethodInvocationInfo info = invocationInfo.get();
        log("[InstanceCreationExpression] InvocationInfo = " + info);

        Optional<SymbolInfo> classSymbolInfo = resolveClassSymbol(info);
        if (classSymbolInfo.isEmpty()) {
            return super.visitUnqualifiedClassInstanceCreationExpression(ctx);
        }

        SymbolTable classSymbolTable = resolveClassSymbolTable(classSymbolInfo.get());
        Optional<SymbolInfo> constructorSymbol = resolveConstructor(classSymbolTable, info);
        if (constructorSymbol.isPresent()) {
            validateConstructorArguments(ctx, info, constructorSymbol.get());
        }

        return super.visitUnqualifiedClassInstanceCreationExpression(ctx);
    }


    private static String getOrdinalSuffix(int number) {
        if (number % 100 >= 11 && number % 100 <= 13) {
            return "th";
        }

        switch (number % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }

    @Override
    public Void visitLocalVariableDeclaration(JPlus20Parser.LocalVariableDeclarationContext ctx) {
        var variableDeclarator = ctx.variableDeclaratorList().variableDeclarator();
        for (JPlus20Parser.VariableDeclaratorContext variableDeclaratorContext : variableDeclarator) {
            String symbol = Utils.getTokenString(variableDeclaratorContext.variableDeclaratorId());
            System.err.println("[NullabilityChecker][LocalVariable] symbol = " + symbol);
            SymbolInfo symbolInfo = currentSymbolTable.resolve(symbol);
            System.err.println("[NullabilityChecker][LocalVariable] symbolInfo = " + symbolInfo);
            System.err.println("[NullabilityChecker][LocalVariable] currentSymbolTable = " + currentSymbolTable);
            if (symbolInfo != null) {
                TypeInfo typeInfo = symbolInfo.getTypeInfo();
                if (variableDeclaratorContext.variableInitializer() != null) {
                    String expression = Utils.getTokenString(variableDeclaratorContext.variableInitializer());
                    if (!typeInfo.isNullable() && typeInfo.getType().equals(TypeInfo.Type.Reference) && "null".equals(expression)) {
                        int line = ctx.getStart().getLine();
                        int column = ctx.getStart().getCharPositionInLine();
                        int offset = ctx.getStart().getStartIndex();
                        String msg = symbol + " is a non-nullable variable. But null value is assigned to it.";
                        issues.add(new NullabilityIssue(line, column, offset, msg));
                        hasPassed = false;
                    }
                } else {
                    if (!typeInfo.isNullable() && typeInfo.getType().equals(TypeInfo.Type.Reference)) {
                        int line = ctx.getStart().getLine();
                        int column = ctx.getStart().getCharPositionInLine();
                        int offset = ctx.getStart().getStartIndex();
                        String msg = symbol + " is a non-nullable variable. But null value is assigned to it.";
                        issues.add(new NullabilityIssue(line, column, offset, msg));
                        hasPassed = false;
                    }
                }
            }
        }

        return super.visitLocalVariableDeclaration(ctx);
    }

    @Override
    public Void visitAssignment(JPlus20Parser.AssignmentContext ctx) {
        String fullVariableName = null;
        if (ctx.leftHandSide().expressionName() != null) {
            fullVariableName = Utils.getTokenString(ctx.leftHandSide().expressionName());
        } else if (ctx.leftHandSide().fieldAccess() != null) {
            fullVariableName = Utils.getTokenString(ctx.leftHandSide().fieldAccess());
        } else if (ctx.leftHandSide().arrayAccess() != null) {
            fullVariableName = Utils.getTokenString(ctx.leftHandSide().arrayAccess());
        }

//        System.err.println("fullVariableName = " + fullVariableName);

        String expression = Utils.getTokenString(ctx.expression());

        SymbolInfo symbolInfo = null;
        String variableName;
        int thisIndex = fullVariableName.indexOf("this");
        if (thisIndex != -1) {
            variableName = fullVariableName.substring(thisIndex + "this".length() + 1);
            symbolInfo = currentSymbolTable.getParent().getParent().resolve(variableName);
//            System.err.println("symbolInfo = " + symbolInfo);
        } else {
            symbolInfo = currentSymbolTable.resolve(fullVariableName);
        }

        if (symbolInfo != null) {
            TypeInfo typeInfo = symbolInfo.getTypeInfo();
            if (!typeInfo.isNullable() && typeInfo.getType().equals(TypeInfo.Type.Reference)) {
                if ("null".equals(expression)) {
                    int line = ctx.getStart().getLine();
                    int column = ctx.getStart().getCharPositionInLine();
                    int offset = ctx.getStart().getStartIndex();
                    String msg = fullVariableName + " is a non-nullable variable. But null value is assigned to it.";
                    issues.add(new NullabilityIssue(line, column, offset, msg));
                    hasPassed = false;
                } else {
                    SymbolInfo rhsSymbolInfo = currentSymbolTable.resolve(expression);
//                    System.err.println("rhsSymbolInfo = " + rhsSymbolInfo);
                    if (rhsSymbolInfo != null) {
                        TypeInfo rhsTypeInfo = rhsSymbolInfo.getTypeInfo();
                        if (typeInfo.getType().equals(TypeInfo.Type.Reference) && rhsTypeInfo.getType().equals(TypeInfo.Type.Reference) && !typeInfo.isNullable && rhsTypeInfo.isNullable) {
                            int line = ctx.getStart().getLine();
                            int column = ctx.getStart().getCharPositionInLine();
                            int offset = ctx.getStart().getStartIndex();
                            String msg = "cannot assign " + expression + "(nullable) to " + fullVariableName + "(non-nullable).";
                            issues.add(new NullabilityIssue(line, column, offset, msg));
                            hasPassed = false;
                        }
                    }
                }
            }
        }

        return super.visitAssignment(ctx);
    }

    @Override
    public Void visitEqualityExpression(JPlus20Parser.EqualityExpressionContext ctx) {
        if (ctx.NOTEQUAL() != null) {
            String variableName = Utils.getTokenString(ctx.equalityExpression());
            String value = Utils.getTokenString(ctx.relationalExpression());
            if ("null".equals(value)) {
                SymbolInfo symbolInfo = currentSymbolTable.resolve(variableName);
                if (symbolInfo != null) {
                    TypeInfo typeInfo = symbolInfo.getTypeInfo();
                    if (typeInfo.isNullable) {
                        TypeInfo newTypeInfo = TypeInfo.copyOf(typeInfo);
                        newTypeInfo.setNullable(false);

                        SymbolInfo newSymbolInfo = SymbolInfo.copyOf(symbolInfo);
                        newSymbolInfo.setTypeInfo(newTypeInfo);

                        currentSymbolTable.declare(variableName, newSymbolInfo);
                    }
                }
            }
        }

        return super.visitEqualityExpression(ctx);
    }

    @Override
    public Void visitMethodInvocation(JPlus20Parser.MethodInvocationContext ctx) {
        String invocationCode = Utils.getTokenString(ctx);
        System.err.println("[MethodInvocation] invocationCode: " + invocationCode);
        TextChangeRange invocationCodeRange = Utils.computeTextChangeRange(this.originalText, ctx.start.getStartIndex(), ctx.stop.getStopIndex());
//        TextChangeRange invocationCodeRange = Utils.getTextChangeRange(this.originalText, ctx);
        System.err.println("[MethodInvocation] invocationCodeRange: " + invocationCodeRange);

        Optional<TextChangeRange> javaInvocationCodeRange = sourceMappingEntrySet.stream()
                .peek(sourceMappingEntry -> System.err.println("originalRange = " + sourceMappingEntry.getOriginalRange()))
                .filter(sourceMappingEntry -> invocationCodeRange.equals(sourceMappingEntry.getOriginalRange()))
                .map(SourceMappingEntry::getTransformedRange)
                .findFirst();

        javaInvocationCodeRange.ifPresent(range -> {
            System.err.println("[MethodInvocation] javaInvocationCodeRange: " + range);
            Optional<MethodInvocationInfo> invocationInfo = methodInvocationManager.findInvocationInfo(currentSymbolTable, range);
            invocationInfo.ifPresent(info -> System.err.println("[MethodInvocation] = " + info));

            Optional<SymbolInfo> instanceSymbolInfo = invocationInfo
                    .flatMap(info -> Optional.ofNullable(info.instanceName))
                    .map(name -> currentSymbolTable.resolve(name));

            instanceSymbolInfo.ifPresent(symbolInfo -> {
                TypeInfo instanceTypeInfo = symbolInfo.getTypeInfo();
                boolean hasNullsafeOperator = (ctx.NULLSAFE() != null);

                invocationInfo.ifPresent(info -> {
                    if (instanceTypeInfo.isNullable && !hasNullsafeOperator) {
                        String msg = ("%s is a nullable variable. But it directly accesses %s(). "
                                + "Consider using null-safe operator(?.).")
                                .formatted(info.instanceName, info.methodName);
                        reportNullableAccessIssue(ctx.start, msg);
                    }
                });
            });
        });





        /*if (ctx.typeName() != null) {
            String instanceName = Utils.getTokenString(ctx.typeName());
            String methodName = Utils.getTokenString(ctx.identifier());
            boolean nullsafe = ctx.NULLSAFE() != null;

            SymbolInfo symbolInfo = currentSymbolTable.resolve(instanceName);
            if (symbolInfo != null && symbolInfo.getTypeInfo().isNullable() && !nullsafe) {
                int line = ctx.start.getLine();
                int column = ctx.start.getCharPositionInLine();
                int offset = ctx.start.getStartIndex();
                String msg = instanceName + " is a nullable variable. But it directly accesses " + methodName + "(). Consider using null-safe operator(?.).";
                issues.add(new NullabilityIssue(line, column, offset, msg));
                hasPassed = false;
            }

            //System.err.println("instanceName = " + instanceName);
            SymbolInfo symInfo = currentSymbolTable.resolve(instanceName);
            //System.err.println("symInfo = " + symInfo);

//            if (symInfo == null) return super.visitMethodInvocation(ctx);

            String typeName = null;
            SymbolTable classSymbolTable = null;
            if (symInfo == null) {
                //System.err.println("instanceName = " + instanceName);
                String[] tokens = instanceName.split("\\.");
//                typeName = tokens[0];
                for (int i = 0; i < tokens.length; i++) {
                    String token = tokens[i];
                    //System.err.println("token = " + token);
                    BytecodeSymbolAnalyzer symbolAnalyzer = new BytecodeSymbolAnalyzer(globalSymbolTable);
                    try {
                        if (i == 0) token = "java.lang." + token;
                        if (typeName == null) typeName = token;
                        else {
                            symInfo = classSymbolTable.resolveInCurrent(token);
                            typeName = symInfo.getTypeInfo().getName();
                            //System.err.println("typeName = " + typeName);
                        }
                        classSymbolTable = symbolAnalyzer.analyzeSymbol(Path.of("/home/user/miniconda3/envs/java_dev/jmods/java.base.jmod"), typeName);
                        String className = classSymbolTable.resolveInCurrent("^TopLevelClass$").getSymbol();
                        classSymbolTable = classSymbolTable.getEnclosingSymbolTable(className);
                        //System.err.println("className = " + className);
                        //System.err.println("classSymbolTable = " + classSymbolTable);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }


            } else {
                typeName = symInfo.getTypeInfo().getName();
                //System.err.println("typeName = " + typeName);
                classSymbolTable = globalSymbolTable.resolveInCurrent(typeName).getSymbolTable();
                //System.err.println("classSymbolTable = " + classSymbolTable);
            }


            List<String> argumentList = new ArrayList<>();
            for (JPlus20Parser.ExpressionContext expressionContext : ctx.argumentList().expression()) {
                String argument = Utils.getTokenString(expressionContext);
                if ("null".equals(argument)) {
                    argument = "<nulltype>";
                }
                argumentList.add(argument);
            }

            int arity = argumentList.size();
            List<String> methodList = classSymbolTable.findSymbolsByType(List.of(TypeInfo.Type.Method));
            List<String> methodListWithSameArity = methodList.stream().filter(s -> s.split("_").length == arity + 1).toList();

            //System.err.println("MethodList = " + methodList);
            //System.err.println("methodListWithSameArity = " + methodListWithSameArity);

            String matchedMethod = null;
            for (String method : methodListWithSameArity) {
                if (!method.contains(methodName)) continue;
                //System.err.println("method = " + method);
                int index = method.indexOf("$_");
                String[] paramTypes = method.substring(index + 2).split("_");

                boolean matched = true;
                for (int i = 0; i < paramTypes.length; i++) {
                    //System.err.println("argumentList.get(i) = " + argumentList.get(i));
                    //System.err.println("argTypes.get(i) = " + methodInvocationInfo.argTypes.get(i));
                    if(!paramTypes[i].equals(methodInvocationInfo.argTypes.get(i)) && !(!Utils.isJavaPrimtive(paramTypes[i]) && "<nulltype>".equals(methodInvocationInfo.argTypes.get(i)))) {
                        matched = false;
                        break;
                    }
                }

                if (matched) {
                    matchedMethod = method;
                    break;
                }
            }

            //System.err.println("matchedMethod = " + matchedMethod);
            if (matchedMethod != null) {
                int index = matchedMethod.indexOf("$_");
                String[] paramTypes = matchedMethod.substring(index + 2).split("_");
                for (int i = 0; i < paramTypes.length; i++) {
                    String paramType = paramTypes[i];
                    String argument = argumentList.get(i);
                    //System.err.println("paramType = " + paramType);
                    //System.err.println("argument = " + argument);
                    if (!paramType.endsWith("?") && "<nulltype>".equals(argumentList.get(i))) {
                        int line = ctx.getStart().getLine();
                        int column = ctx.getStart().getCharPositionInLine();
                        int offset = ctx.getStart().getStartIndex();

                        int argIndex = i + 1;
                        String suffix = getOrdinalSuffix(argIndex);
                        String msg = "The " + argIndex + suffix + " argument of the " + typeName + "." + methodName + "() is a non-nullable variable, but a null value is assigned to it.";
                        issues.add(new NullabilityIssue(line, column, offset, msg));
                        hasPassed = false;
                    }
                }
            }
        }*/

        return super.visitMethodInvocation(ctx);
    }

    private void reportNullableAccessIssue(Token ctx, String msg) {
        int line = ctx.getLine();
        int column = ctx.getCharPositionInLine();
        int offset = ctx.getStartIndex();
        issues.add(new NullabilityIssue(line, column, offset, msg));
        hasPassed = false;
    }

    @Override
    public Void visitPrimaryNoNewArray(JPlus20Parser.PrimaryNoNewArrayContext ctx) {
        if (ctx.typeName() != null) {
            if (ctx.THIS() == null) {
                String instanceName = Utils.getTokenString(ctx.typeName());
                String methodName = Utils.getTokenString(ctx.identifier());
                boolean nullsafe = ctx.NULLSAFE() != null;

                SymbolInfo symbolInfo = currentSymbolTable.resolve(instanceName);
                if (symbolInfo != null && symbolInfo.getTypeInfo().isNullable() && !nullsafe) {
                    int line = ctx.getStart().getLine();
                    int column = ctx.getStart().getCharPositionInLine();
                    int offset = ctx.getStart().getStartIndex();
                    String msg = instanceName + " is a nullable variable. But it directly accesses " + methodName + "(). Consider using null-safe operator(?.).";
                    issues.add(new NullabilityIssue(line, column, offset, msg));
                    hasPassed = false;
                }
            }
        }
        return super.visitPrimaryNoNewArray(ctx);
    }

    public boolean hasPassed() {
        return hasPassed;
    }
}
