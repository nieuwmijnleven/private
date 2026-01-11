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

package jplus.analyzer.nullability;

import jplus.analyzer.ResolvedChain;
import jplus.analyzer.StepCursor;
import jplus.base.JPlus25Parser;
import jplus.base.JPlus25ParserBaseVisitor;
import jplus.base.JavaMethodInvocationManager;
import jplus.base.MethodInvocationInfo;
import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.base.TypeInfo;
import jplus.generator.SourceMappingEntry;
import jplus.generator.TextChangeRange;
import jplus.util.MethodUtils;
import jplus.util.Utils;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class NullabilityChecker extends JPlus25ParserBaseVisitor<Void> {

    private final SymbolTable globalSymbolTable;
    private final Set<SourceMappingEntry> sourceMappingEntrySet;
    private final JavaMethodInvocationManager methodInvocationManager;
    private SymbolTable currentSymbolTable;
    private final Set<TextChangeRange> consumedExpressions = new HashSet<>();

    private String originalText;
    private String packageName;
    private boolean hasPassed = true;

    public record NullabilityIssue(int line, int column, int offset, String message) {
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
    }

    private final List<NullabilityIssue> issues = new ArrayList<>();

    public List<NullabilityIssue> getIssues() {
        return issues;
    }

    private void enterSymbolTable(String symbol) {
        log("[enterSymbolTable] currentSymbolTable = " + currentSymbolTable);
        currentSymbolTable = currentSymbolTable.getEnclosingSymbolTable(symbol);
        log("[enterSymbolTable] symbol = " + symbol);
        log("[enterSymbolTable] enclosing symboltable = " + currentSymbolTable);
    }

    private void exitSymbolTable() {
        currentSymbolTable = currentSymbolTable.getParent();
    }

    private void reportIssue(Token token, String msg) {
        issues.add(new NullabilityIssue(token.getLine(), token.getCharPositionInLine(), token.getStartIndex(), msg));
        hasPassed = false;
    }

    @Override
    public Void visitStart_(JPlus25Parser.Start_Context ctx) {
        this.originalText = ctx.start.getInputStream().toString();
        return super.visitStart_(ctx);
    }

    @Override
    public Void visitPackageDeclaration(JPlus25Parser.PackageDeclarationContext ctx) {
        this.packageName = ctx.identifier().stream().map(Utils::getTokenString).collect(Collectors.joining("."));
        System.err.println("[NullabilityChecker] packageName = " + packageName);
        return super.visitPackageDeclaration(ctx);
    }

    @Override
    public Void visitTopLevelClassOrInterfaceDeclaration(JPlus25Parser.TopLevelClassOrInterfaceDeclarationContext ctx) {
        String className = getClassName(ctx.classDeclaration().normalClassDeclaration());
        System.err.println("className = " + className);

        SymbolInfo classSymbolInfo = getClassSymbolInfo(className);
        currentSymbolTable = classSymbolInfo.getSymbolTable();
        System.err.println("currentSymbolTable = " + currentSymbolTable);
        return super.visitTopLevelClassOrInterfaceDeclaration(ctx);
    }

    private SymbolInfo getClassSymbolInfo(String className) {
        SymbolInfo classSymbolInfo = globalSymbolTable.resolveInCurrent(className);
        if (classSymbolInfo == null) {
            throw new IllegalStateException("Symbol not found: " + className);
        }
        return classSymbolInfo;
    }

    private String getClassName(JPlus25Parser.NormalClassDeclarationContext ctx) {
        String className = Utils.getTokenString(ctx.typeIdentifier());
        if (this.packageName != null) {
            className = this.packageName + "." + className;
        }
        return className;
    }

    @Override
    public Void visitClassDeclaration(JPlus25Parser.ClassDeclarationContext ctx) {
        if (ctx.normalClassDeclaration() != null) {
            String className = Utils.getTokenString(ctx.normalClassDeclaration().typeIdentifier());
            System.err.println("[NullabilityChecker][ClassDecl] className = " + className);
            enterSymbolTable(className);
        } else if (ctx.enumDeclaration() != null) {
            String enumName = Utils.getTokenString(ctx.enumDeclaration().typeIdentifier());
            System.err.println("[NullabilityChecker][ClassDecl] enumName = " + enumName);
            enterSymbolTable(enumName);
        } else if (ctx.recordDeclaration() != null) {
            String recordName = Utils.getTokenString(ctx.recordDeclaration().typeIdentifier());
            System.err.println("[NullabilityChecker][ClassDecl] recordName = " + recordName);
            enterSymbolTable(recordName);
        }

        try {
            return super.visitClassDeclaration(ctx);
        } finally {
            exitSymbolTable();
        }
    }

    @Override
    public Void visitConstructorDeclaration(JPlus25Parser.ConstructorDeclarationContext ctx) {
        return processInvocationDeclaration(InvocationDeclarationContext.from(ctx));
    }

    @Override
    public Void visitMethodDeclaration(JPlus25Parser.MethodDeclarationContext ctx) {
        return processInvocationDeclaration(InvocationDeclarationContext.from(ctx));
    }

    private List<String> extractParameterTypes(List<JPlus25Parser.FormalParameterContext> params) {
        return params.stream()
                .map(this::extractParameterType)
                .toList();
    }

    private String extractParameterType(JPlus25Parser.FormalParameterContext param) {
        boolean nullable = isNullable(param);
        String type = Utils.getTokenString(param.unannType());

        return nullable ? type + "?" : type;
    }

    private boolean isNullable(JPlus25Parser.FormalParameterContext param) {
        if (param.variableModifier() == null) {
            return false;
        }

        return param.variableModifier().stream()
                .anyMatch(vm ->
                        "@Nullable".equals(Utils.getTokenString(vm.annotation()))
                );
    }


    private List<String> getTypeNamesFromParameterList(JPlus25Parser.FormalParameterListContext ctx) {
        if (ctx == null) return List.of();
        return extractParameterTypes(ctx.formalParameter());
    }

    private Void processInvocationDeclaration(InvocationDeclarationContext ctx) {
        List<String> typeNameList = getTypeNamesFromParameterList(ctx.formalParameterList());
        String methodSymbol = "^" + ctx.methodName() + "$_" + String.join("_", typeNameList);

        SymbolInfo methodSymbolInfo = currentSymbolTable.resolveInCurrent(methodSymbol);
        if (methodSymbolInfo == null) {
            throw new IllegalStateException("cannot find the symbol(" + methodSymbol + ")");
        }

        enterSymbolTable(methodSymbolInfo.getSymbol());
        try {
            return super.visitChildren(ctx.originalContext());
        } finally {
            exitSymbolTable();
        }
    }

    @Override
    public Void visitLambdaExpression(JPlus25Parser.LambdaExpressionContext ctx) {
        log("[LambdaExpression] contextString = " + Utils.getTokenString(ctx));
        log("[LambdaExpression] startOffset = " + ctx.start.getStartIndex());

        String lambdaSymbol = "^lambda$" + ctx.start.getStartIndex();
        enterSymbolTable(lambdaSymbol);
        try {
            log("[LambdaExpression] lambdaSymboTable = " + currentSymbolTable);
            return super.visitLambdaExpression(ctx);
        } finally {
            exitSymbolTable();
        }
    }

    @Override
    public Void visitFieldDeclaration(JPlus25Parser.FieldDeclarationContext ctx) {
        for (var variableDeclaratorContext : ctx.variableDeclaratorList().variableDeclarator()) {
            String symbol = Utils.getTokenString(variableDeclaratorContext.variableDeclaratorId());
            SymbolInfo symbolInfo = currentSymbolTable.resolve(symbol);
            System.err.println("[NullabilityChecker][FieldDecl] symbol = " + symbol);
            System.err.println("[NullabilityChecker][FieldDecl] currentSymbolTable = " + currentSymbolTable);
            TypeInfo typeInfo = symbolInfo.getTypeInfo();

            if (variableDeclaratorContext.variableInitializer() != null) {

                String expression = Utils.getTokenString(variableDeclaratorContext.variableInitializer());
                if (!typeInfo.isNullable() && "null".equals(expression)) {
                    reportIssue(ctx.getStart(), symbol + " is a non-nullable variable. But null value is assigned to it.");
                }
            }
        }

        return super.visitFieldDeclaration(ctx);
    }

    @Override
    public Void visitBlock(JPlus25Parser.BlockContext ctx) {
        enterSymbolTable("^block$");
        try {
            return super.visitBlock(ctx);
        } finally {
            exitSymbolTable();
        }
    }

    @Override
    public Void visitConstructorBody(JPlus25Parser.ConstructorBodyContext ctx) {
        enterSymbolTable("^block$");
        try {
            return super.visitConstructorBody(ctx);
        } finally {
            exitSymbolTable();
        }
    }

    private SymbolTable resolveClassSymbolTable(SymbolInfo symbolInfo) {
        SymbolTable symbolTable = symbolInfo.getSymbolTable();
        System.err.println("[resolveClassSymbolTable] symbolTable = " + symbolTable);
        if (symbolInfo.getTypeInfo().getType() != TypeInfo.Type.Class) {
            SymbolInfo classSymbolInfo = globalSymbolTable.resolveInCurrent(symbolInfo.getTypeInfo().getName());
            if (classSymbolInfo != null) {
                symbolTable = classSymbolInfo.getSymbolTable();
                symbolInfo = classSymbolInfo;
            }
        }

        String classSymbol;
        if(symbolTable.containsInCurrent("^TopLevelClass$", EnumSet.of(TypeInfo.Type.Class)) ) {
            classSymbol = symbolTable.resolveInCurrent("^TopLevelClass$").getSymbol();
        } else {
            classSymbol = symbolInfo.getSymbol();
        }

        SymbolTable classSymbolTable = symbolTable.getEnclosingSymbolTable(classSymbol);
        log("[resolveClassSymbolTable] enclosingSymbolTable = " + classSymbolTable);
        return classSymbolTable;
    }

    private TextChangeRange getCodeRange(ParserRuleContext ctx) {
        String code = Utils.getTokenString(ctx);
        log("[getCodeRange] code: " + code);

        TextChangeRange codeRange = Utils.getTextChangeRange(originalText, ctx);
        log("[getCodeRange] codeRange: " + codeRange);

        return codeRange;
    }

    private Optional<TextChangeRange> findTransformedRange(TextChangeRange instanceRange) {
        return sourceMappingEntrySet.stream()
                .filter(entry -> Objects.equals(instanceRange, entry.getOriginalRange()))
                //.peek(entry -> log("[findTransformedRange] originalRange = " + entry.getOriginalRange() + ", transformedRange = " + entry.getTransformedRange()))
                .map(SourceMappingEntry::getTransformedRange)
                .findFirst();
    }

    private Optional<SymbolInfo> resolveClassSymbol(MethodInvocationInfo info) {
        return Optional.ofNullable(info.instanceName)
                .map(className -> currentSymbolTable.resolve(className));
    }

    private Optional<SymbolInfo> resolveMethod(SymbolTable classSymbolTable, MethodInvocationInfo info) {
        System.err.println("[resolveMethod] classSymbolTable = " + classSymbolTable);

        String methodName = resolveMethodName(info);
        List<String> candidates = MethodUtils.getCandidates(methodName, info.paramTypes);
        candidates.forEach(c -> log("[InstanceCreationExpression] candidate = " + c));

        for (String candidate : candidates) {
            Optional<SymbolInfo> methodSymbolInfo = classSymbolTable.resolveInCurrent(candidate, EnumSet.of(TypeInfo.Type.Constructor, TypeInfo.Type.Method));
            if (methodSymbolInfo.isPresent()) return methodSymbolInfo;
        }

        return Optional.empty();
    }

    private String resolveMethodName(MethodInvocationInfo info) {
        return Objects.equals(info.instanceName, info.methodName) ? "constructor" : info.methodName;
    }

    private void validateMethodArguments(
            ParserRuleContext ctx,
            MethodInvocationInfo info,
            SymbolInfo methodSymbolInfo
    ) {
        String methodName = resolveMethodName(info);
        String rawParamTypes = methodSymbolInfo.getSymbol().substring(("^" + methodName + "$_").length());
        String[] paramTypes = rawParamTypes.isEmpty() ? new String[0] : rawParamTypes.split("_");

        for (int i = 0; i < paramTypes.length; i++) {
            String paramType = paramTypes[i];
            String argType = info.argTypes.get(i);
            log("paramType = " + paramType + ", argType = " + argType);

            if (isInvalidNullAssignment(paramType, argType)) {
                reportInvalidNull(ctx, info, i + 1);
            }


        }
    }

    private boolean isInvalidNullAssignment(String paramType, String argType) {
        if (paramType.endsWith("?")) return false;
        return "<nulltype>".equals(argType);
        //return !paramType.endsWith("?") && "<nulltype>".equals(argType);
    }

    private void reportInvalidNull(
            ParserRuleContext ctx,
            MethodInvocationInfo info,
            int argIndex
    ) {
        String suffix = getOrdinalSuffix(argIndex);
        String msg = String.format("The %s argument of the %s is a non-nullable variable, but a null value is assigned to it.", argIndex + suffix, info.getInvocationInfoMessage());

        reportIssue(ctx.start, msg);
    }



    private void log(String msg) {
        System.err.println(msg);
    }

    @Override
    public Void visitUnqualifiedClassInstanceCreationExpression(JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext ctx) {

        TextChangeRange codeRange = getCodeRange(ctx);
        if (consumedExpressions.contains(codeRange)) {
            return null;
        }

        Optional<TextChangeRange> transformedRange = findTransformedRange(codeRange);
        if (transformedRange.isEmpty()) {
            throw new IllegalStateException("cannot find a mapping java code range.");
            //return super.visitUnqualifiedClassInstanceCreationExpression(ctx);
        }

        /*Optional<ResolvedChain> chain = currentSymbolTable.findResolvedChain(transformedRange.get());
        if (chain.isPresent()) {
            // 이미 PrimaryNoNewArray에서 처리됨
            return null;
        }*/

        Optional<MethodInvocationInfo> invocationInfo = methodInvocationManager.findInvocationInfo(currentSymbolTable, transformedRange.get());
        if (invocationInfo.isEmpty()) {
            throw new IllegalStateException("cannot find a method invocation info." + transformedRange.get());
        }

        invocationInfo.ifPresent(info -> {
            log("[InstanceCreationExpression] InvocationInfo = " + info);

            Optional<SymbolInfo> classSymbolInfo = resolveClassSymbol(info);
            if (classSymbolInfo.isEmpty()) {
                throw new IllegalStateException("cannot find a symbolinfo related to the symbol(" + info.instanceName + ")");
            }
            //System.err.println("[InstanceCreationExpression] symbolInfo = " + classSymbolInfo.get());
            System.err.println("[UnqualifiedClass] classSymbolInfo = " + classSymbolInfo.get());

            SymbolTable classSymbolTable = resolveClassSymbolTable(classSymbolInfo.get());
            System.err.println("[UnqualifiedClass] classSymbolTable = " + classSymbolTable);
            Optional<SymbolInfo> constructorSymbol = resolveMethod(classSymbolTable, info);
            if (constructorSymbol.isEmpty()) {
                throw new IllegalStateException("cannot find a mapping constructor.");
            }

            validateMethodArguments(ctx, info, constructorSymbol.get());
        });

        return super.visitUnqualifiedClassInstanceCreationExpression(ctx);
    }


    private static String getOrdinalSuffix(int number) {
        if (number % 100 >= 11 && number % 100 <= 13) {
            return "th";
        }

        return switch (number % 10) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }

    @Override
    public Void visitLocalVariableDeclaration(JPlus25Parser.LocalVariableDeclarationContext ctx) {
        var variableDeclarator = ctx.variableDeclaratorList().variableDeclarator();
        for (JPlus25Parser.VariableDeclaratorContext variableDeclaratorContext : variableDeclarator) {

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
                        String msg = symbol + " is a non-nullable variable. But null value is assigned to it.";
                        reportIssue(ctx.start, msg);
                    }
                } else {

                    if (!typeInfo.isNullable() && typeInfo.getType().equals(TypeInfo.Type.Reference)) {
                        String msg = symbol + " is a non-nullable variable. But null value is assigned to it.";
                        reportIssue(ctx.start, msg);
                    }
                }
            }
        }

        return super.visitLocalVariableDeclaration(ctx);
    }

    @Override
    public Void visitAssignment(JPlus25Parser.AssignmentContext ctx) {
        String fullVariableName = Utils.getTokenString(ctx.leftHandSide());
        if (ctx.leftHandSide().expressionName() != null) {
            fullVariableName = Utils.getTokenString(ctx.leftHandSide().expressionName());
        } else if (ctx.leftHandSide().fieldAccess() != null) {
            fullVariableName = Utils.getTokenString(ctx.leftHandSide().fieldAccess());
        } else if (ctx.leftHandSide().arrayAccess() != null) {
            fullVariableName = Utils.getTokenString(ctx.leftHandSide().arrayAccess());
        }

        System.err.println("fullVariableName = " + fullVariableName);
        System.err.println("currentSymbolTable = " + currentSymbolTable);

        String expression = Utils.getTokenString(ctx.expression());

        SymbolInfo symbolInfo;
        String variableName;
        int thisIndex = fullVariableName.indexOf("this");
        if (thisIndex != -1) {
            variableName = fullVariableName.substring(thisIndex + "this".length() + 1);
            symbolInfo = currentSymbolTable.getParent().getParent().resolve(variableName);
            System.err.println("symbolInfo = " + symbolInfo);
        } else {
            symbolInfo = currentSymbolTable.resolve(fullVariableName);
        }

        if (symbolInfo != null) {
            TypeInfo typeInfo = symbolInfo.getTypeInfo();
            if (!typeInfo.isNullable() && typeInfo.getType().equals(TypeInfo.Type.Reference)) {
                if ("null".equals(expression)) {
                    String msg = fullVariableName + " is a non-nullable variable. But null value is assigned to it.";
                    reportIssue(ctx.start, msg);
                } else {
                    SymbolInfo rhsSymbolInfo = currentSymbolTable.resolve(expression);
//                    System.err.println("rhsSymbolInfo = " + rhsSymbolInfo);
                    if (rhsSymbolInfo != null) {
                        TypeInfo rhsTypeInfo = rhsSymbolInfo.getTypeInfo();
                        if (typeInfo.getType().equals(TypeInfo.Type.Reference) && rhsTypeInfo.getType().equals(TypeInfo.Type.Reference) && !typeInfo.isNullable() && rhsTypeInfo.isNullable()) {
                            String msg = "cannot assign " + expression + "(nullable) to " + fullVariableName + "(non-nullable).";
                            reportIssue(ctx.start, msg);
                        }
                    }
                }
            }
        }

        return super.visitAssignment(ctx);
    }

    @Override
    public Void visitEqualityExpression(JPlus25Parser.EqualityExpressionContext ctx) {
        if (ctx.NOTEQUAL() != null) {
            String variableName = Utils.getTokenString(ctx.equalityExpression());
            String value = Utils.getTokenString(ctx.relationalExpression());
            if ("null".equals(value)) {
                SymbolInfo symbolInfo = currentSymbolTable.resolve(variableName);
                if (symbolInfo != null) {
                    TypeInfo typeInfo = symbolInfo.getTypeInfo();
                    if (typeInfo.isNullable()) {
                        TypeInfo newTypeInfo = typeInfo.toBuilder()
                                                    .isNullable(false)
                                                    .build();

                        SymbolInfo newSymbolInfo = symbolInfo.toBuilder()
                                                            .typeInfo(newTypeInfo)
                                                            .build();

                        currentSymbolTable.declare(variableName, newSymbolInfo);
                    }
                }
            }
        }

        return super.visitEqualityExpression(ctx);
    }

    @Override
    public Void visitMethodInvocation(JPlus25Parser.MethodInvocationContext ctx) {
        TextChangeRange invocationCodeRange = getCodeRange(ctx);
        System.err.println("[MethodInvocation] invocationCodeRange: " + invocationCodeRange);

        return processMethodInvocation(ctx);
    }

    private Void processMethodInvocation(JPlus25Parser.MethodInvocationContext ctx) {
        System.err.println("[MethodInvocation] resolvedChains: " + currentSymbolTable.getResolvedChains());
        System.err.println("[MethodInvocation] original range = " + Utils.getTextChangeRange(originalText, ctx));

        findTransformedRange(Utils.getTextChangeRange(originalText, ctx)).ifPresent(range -> {
            System.err.println("[MethodInvocation] mapped range = " + range);
            System.err.println("[MethodInvocation] findResolvedChain = " + currentSymbolTable.findResolvedChain(range));

            currentSymbolTable.findResolvedChain(range)
                    .ifPresentOrElse(chain -> handleMethodInvocation(ctx, chain),
                            () -> currentSymbolTable.findResolvedQualifierChain(range)
                                        .ifPresent(chain -> handleMethodInvocation(ctx, chain))
                    );
        });

        //return super.visitMethodInvocation(ctx);
        return null;
    }

    private void handleMethodInvocation(JPlus25Parser.MethodInvocationContext ctx, ResolvedChain chain) {
        StepCursor cursor = chain.stepCursor();

        //methodName '(' argumentList? ')'
        if (ctx.methodName() != null) {
            processImplicitReceiverMethodInvocationSignature(MethodInvocationSignatureContextAdapter.from(ctx), cursor);
            return;
        }

        //expressionName ('.'|'?.') typeArguments? identifier '(' argumentList? ')'
        if (ctx.expressionName() != null) {
            handleExpressionName(ctx.expressionName(), cursor);
            processExplicitReceiverMethodInvocationSignature(MethodInvocationSignatureContextAdapter.from(ctx), cursor);
            return;
        }

        //primary ('.'|'?.') typeArguments? identifier '(' argumentList? ')'
        if (ctx.primary() != null) {
            super.visitPrimary(ctx.primary());
            //handlePrimaryNoNewArray();

            StepCursor qualifierLastCursor = new StepCursor(List.of(chain.qualifierLast(), chain.last()));
            qualifierLastCursor.consume();

            processExplicitReceiverMethodInvocationSignature(MethodInvocationSignatureContextAdapter.from(ctx), qualifierLastCursor);
            return;
        }

        //typeName '.' typeArguments? identifier '(' argumentList? ')'
        if (ctx.typeName() != null) {
            processTypeName(ctx.typeName(), cursor);

            //typeName '.' 'super' '.' typeArguments? identifier '(' argumentList? ')'
            if (ctx.SUPER() != null) {
                cursor.consume();
            }

            processExplicitReceiverMethodInvocationSignature(MethodInvocationSignatureContextAdapter.from(ctx), cursor);
        }

        //'super' '.' typeArguments? identifier '(' argumentList? ')'
        if (ctx.SUPER() != null) {
            cursor.consume();
            processExplicitReceiverMethodInvocationSignature(MethodInvocationSignatureContextAdapter.from(ctx), cursor);
        }
    }

    @Override
    public Void visitPostfixExpression(JPlus25Parser.PostfixExpressionContext ctx) {
        if (ctx.expressionName() !=  null) {
            System.err.println("[PostfixExpression] chaining = " + currentSymbolTable.getResolvedChains());
            System.err.println("[PostfixExpression] findResolvedChain = " + currentSymbolTable.findResolvedChain(Utils.getTextChangeRange(originalText, ctx)));

            currentSymbolTable.findResolvedChain(Utils.getTextChangeRange(originalText, ctx)).ifPresent(chain -> processExpressionNameContext(getExpressionNameList(ctx.expressionName()), chain.stepCursor()));
            return null;
        }

        return super.visitPostfixExpression(ctx);
    }

    private Optional<ResolvedChain> findPrimaryNoNewArrayChain(JPlus25Parser.PrimaryNoNewArrayContext ctx) {
        System.err.println("[PrimaryNoNewArray] line(" + ctx.start.getLine() + ") contextString = " + Utils.getTokenString(ctx));
        System.err.println("[PrimaryNoNewArray] line(" + ctx.start.getLine() + ") chaining = " + currentSymbolTable.getResolvedChains());

        TextChangeRange originalRange = Utils.getTextChangeRange(originalText, ctx);
        System.err.println("[PrimaryNoNewArray] original range = " + originalRange);

        return findTransformedRange(originalRange)
                .map(mappedRange -> {
                    System.err.println("[PrimaryNoNewArray] mapped range = " + mappedRange);
                    Optional<ResolvedChain> chain = currentSymbolTable.findResolvedChain(mappedRange);
                    System.err.println("[PrimaryNoNewArray] findResolvedChain = " + chain);
                    return chain.orElseGet(() -> currentSymbolTable.findResolvedQualifierChain(mappedRange).orElse(null));
                });
    }

    private void withPrimaryNoNewArrayChain(JPlus25Parser.PrimaryNoNewArrayContext ctx, Consumer<ResolvedChain> chainConsumer) {
        findPrimaryNoNewArrayChain(ctx).ifPresent(chainConsumer);
    }

    @Override
    public Void visitPrimaryNoNewArrayThis(JPlus25Parser.PrimaryNoNewArrayThisContext ctx) {
        //'this' pNNA? #primaryNoNewArrayThis
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> PrimaryNoNewArrayThis(PrimaryNoNewArrayContextAdapter.from(ctx), resolvedChain.stepCursor()));
        return null;
    }

    @Override
    public Void visitPrimaryNoNewArrayQualifiedThis(JPlus25Parser.PrimaryNoNewArrayQualifiedThisContext ctx) {
        //typeName '.' 'this' pNNA?
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handlePrimaryNoNewArrayQualifiedThis(ctx, resolvedChain.stepCursor()));
        return null;
    }

    private void handlePrimaryNoNewArrayQualifiedThis(JPlus25Parser.PrimaryNoNewArrayQualifiedThisContext ctx, StepCursor cursor) {
        processTypeName(ctx.typeName(), cursor);
        PrimaryNoNewArrayThis(PrimaryNoNewArrayContextAdapter.from(ctx), cursor);
    }

    @Override
    public Void visitPrimaryNoNewArrayClassInstanceCreation(JPlus25Parser.PrimaryNoNewArrayClassInstanceCreationContext ctx) {
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handleUnqualifiedClassInstanceCreationExpression(PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter.from(ctx), resolvedChain.stepCursor()));
        //return super.visitPrimaryNoNewArrayClassInstanceCreation(ctx);
        return null;
    }

    @Override
    public Void visitPrimaryNoNewArrayExprQualifiedClassInstanceCreation(JPlus25Parser.PrimaryNoNewArrayExprQualifiedClassInstanceCreationContext ctx) {
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handleExprQualifiedClassInstanceCreation(ctx, resolvedChain.stepCursor()));
        //return super.visitPrimaryNoNewArrayClassInstanceCreation(ctx);
        return null;
    }

    /*@Override
    public Void visitPrimaryNoNewArrayArrayQualifiedClassInstanceCreation(JPlus25Parser.PrimaryNoNewArrayArrayQualifiedClassInstanceCreationContext ctx) {
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handlePrimaryNoNewArrayArrayQualifiedClassInstanceCreation(ctx, resolvedChain.stepCursor()));
        //return super.visitPrimaryNoNewArrayArrayQualifiedClassInstanceCreation(ctx);
        return null;
    }

    private void handlePrimaryNoNewArrayArrayQualifiedClassInstanceCreation(JPlus25Parser.PrimaryNoNewArrayArrayQualifiedClassInstanceCreationContext ctx, StepCursor cursor) {
        //ResolvedChain.Step step = cursor.consume();
        //if (!step.symbol.equals(Utils.getTokenString(ctx.arrayCreationExpression()))) throw new IllegalStateException();

        cursor.consume(); //skip: arrayCreationExpression

        handleUnqualifiedClassInstanceCreationExpression(PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter.from(ctx), cursor);

    }*/

    /*@Override
    public Void visitPrimaryNoNewArrayArrayFieldAccess(JPlus25Parser.PrimaryNoNewArrayArrayFieldAccessContext ctx) {
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handlePrimaryNoNewArrayArrayFieldAccess(ctx, resolvedChain.stepCursor()));
        //return super.visitPrimaryNoNewArrayArrayFieldAccess(ctx);
        return null;
    }

    private void handlePrimaryNoNewArrayArrayFieldAccess(JPlus25Parser.PrimaryNoNewArrayArrayFieldAccessContext ctx, StepCursor cursor) {
        cursor.consume(); //skip: arrayCreationExpression

        ResolvedChain.Step step = cursor.consume();
        if (!step.symbol.equals(Utils.getTokenString(ctx.identifier()))) throw new IllegalStateException();

        handlePNNA(PNNAContextAdapter.from(ctx.pNNA()), cursor);
    }*/

    @Override
    public Void visitPrimaryNoNewArrayExprMethodInvocation(JPlus25Parser.PrimaryNoNewArrayExprMethodInvocationContext ctx) {
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handlePrimaryNoNewArrayExprMethodInvocation(ctx, resolvedChain.stepCursor()));
        //return super.visitPrimaryNoNewArrayExprMethodInvocation(ctx);
        return null;

    }

    private void handlePrimaryNoNewArrayExprMethodInvocation(JPlus25Parser.PrimaryNoNewArrayExprMethodInvocationContext ctx, StepCursor cursor) {
        handleExpressionName(ctx.expressionName(), cursor);
        handlePrimaryNoNewArrayMethodInvocation(ctx, cursor);
    }

    @Override
    public Void visitPrimaryNoNewArraySuperFieldAccess(JPlus25Parser.PrimaryNoNewArraySuperFieldAccessContext ctx) {
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handlePrimaryNoNewArraySuperFieldAccess(ctx, resolvedChain.stepCursor()));
        //return super.visitPrimaryNoNewArrayExprMethodInvocation(ctx);
        return null;
    }

    private void handlePrimaryNoNewArraySuperFieldAccess(JPlus25Parser.PrimaryNoNewArraySuperFieldAccessContext ctx, StepCursor cursor) {
        ResolvedChain.Step step = cursor.consume();
        if (!step.symbol.equals(ctx.SUPER().toString())) throw new IllegalStateException();

        step = cursor.consume();
        if (!step.symbol.equals(Utils.getTokenString(ctx.identifier()))) throw new IllegalStateException();

        handlePNNA(PNNAContextAdapter.from(ctx.pNNA()), cursor);
    }

    @Override
    public Void visitPrimaryNoNewArrayQualifiedSuperFieldAccess(JPlus25Parser.PrimaryNoNewArrayQualifiedSuperFieldAccessContext ctx) {
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handlePrimaryNoNewArrayQualifiedSuperFieldAccess(ctx, resolvedChain.stepCursor()));
        return null;
    }

    private void handlePrimaryNoNewArrayQualifiedSuperFieldAccess(JPlus25Parser.PrimaryNoNewArrayQualifiedSuperFieldAccessContext ctx, StepCursor cursor) {
        processTypeName(ctx.typeName(), cursor);

        ResolvedChain.Step step = cursor.consume();
        if (!step.symbol.equals(ctx.SUPER().toString())) throw new IllegalStateException();

        step = cursor.consume();
        if (!step.symbol.equals(Utils.getTokenString(ctx.identifier()))) throw new IllegalStateException();

        handlePNNA(PNNAContextAdapter.from(ctx.pNNA()), cursor);
    }

    @Override
    public Void visitPrimaryNoNewArrayArrayAccess(JPlus25Parser.PrimaryNoNewArrayArrayAccessContext ctx) {
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handlePrimaryNoNewArrayArrayAccess(ctx, resolvedChain.stepCursor()));
        return null;
    }

    private void handlePrimaryNoNewArrayArrayAccess(JPlus25Parser.PrimaryNoNewArrayArrayAccessContext ctx, StepCursor cursor) {
        //need dataflow
        //expressionName '[' expression ']' pNNA?
        //must handle the front part of `expressionName '[' expression ']'`
        ResolvedChain.Step step = cursor.consume();
        log("[handlePrimaryNoNewArrayArrayAccess] step.symbol = " + step.symbol);

        handlePNNA(PNNAContextAdapter.from(ctx.pNNA()), cursor);
    }

    @Override
    public Void visitPrimaryNoNewArrayArrayCreationWithInitAccess(JPlus25Parser.PrimaryNoNewArrayArrayCreationWithInitAccessContext ctx) {
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handlePrimaryNoNewArrayArrayCreationWithInitAccess(ctx, resolvedChain.stepCursor()));
        return null;
    }

    private void handlePrimaryNoNewArrayArrayCreationWithInitAccess(JPlus25Parser.PrimaryNoNewArrayArrayCreationWithInitAccessContext ctx, StepCursor cursor) {
        //expressionName '[' expression ']' pNNA?
        //must handle the front part of `arrayCreationExpressionWithInitializer '[' expression ']'`

        handlePNNA(PNNAContextAdapter.from(ctx.pNNA()), cursor);
    }

    @Override
    public Void visitPrimaryNoNewArrayMethodInvocation(JPlus25Parser.PrimaryNoNewArrayMethodInvocationContext ctx) {
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> processImplicitReceiverMethodInvocationSignature(MethodInvocationSignatureContextAdapter.from(ctx), resolvedChain.stepCursor()));
        return null;
    }

    private void processImplicitReceiverMethodInvocationSignature(MethodInvocationSignatureContextAdapter ctx, StepCursor cursor) {
        var currentStep = cursor.consume();
        System.err.println("[handlePrimaryNoNewArrayMethodInvocation] currentStep = " + currentStep);

        String methodName = Utils.getTokenString(ctx.methodName());
        System.err.println("[handlePrimaryNoNewArrayMethodInvocation] methodName = " + methodName);

        if (!Objects.equals(methodName, currentStep.symbol)) throw new IllegalStateException();

        System.err.println("[handlePrimaryNoNewArrayMethodInvocation] methodInvocationInfo = " + currentStep.invocationInfo);
        
        TypeInfo receiverTypeInfo = currentSymbolTable.resolve("^TopLevelClass$").getTypeInfo();
        System.err.println("[handlePrimaryNoNewArrayMethodInvocation] receiverTypeInfo = " + receiverTypeInfo);

        validateMethodArgumentNullability(ctx.originalContext(), receiverTypeInfo.getName(), currentStep);

        handlePNNA(PNNAContextAdapter.from(ctx.pNNA()), cursor);
    }

    private void handlePrimaryNoNewArrayMethodInvocation(JPlus25Parser.PrimaryNoNewArrayExprMethodInvocationContext ctx, StepCursor cursor) {
        var prevStep = cursor.peekPrev().orElseThrow(() -> new IllegalStateException("A ExpressionName rule needed."));
        var currentStep = cursor.consume();

        System.err.println("[handlePrimaryNoNewArrayMethodInvocation] prevStep = " + prevStep);
        System.err.println("[handlePrimaryNoNewArrayMethodInvocation] currentStep = " + currentStep);

        String receiverName = prevStep.symbol;
        String methodName = Utils.getTokenString(ctx.identifier());
        System.err.println("[handlePrimaryNoNewArrayMethodInvocation] methodName = " + methodName);

        if (!Objects.equals(methodName, currentStep.symbol)) throw new IllegalStateException();

        System.err.println("[handlePrimaryNoNewArrayMethodInvocation] methodInvocationInfo = " + currentStep.invocationInfo);

        TypeInfo receiverTypeInfo = prevStep.typeInfo;
        System.err.println("[handlePrimaryNoNewArrayMethodInvocation] receiverTypeInfo = " + receiverTypeInfo);

        //check instance nullability
        boolean useNullsafeOperator = (ctx.NULLSAFE() != null);
        if (receiverTypeInfo.isNullable() && !useNullsafeOperator) {
            reportIssue(
                    ctx.getStart(),
                    String.format("%s is a nullable variable. But it directly accesses %s(). Consider using null-safe operator(?.).", receiverName, methodName)
            );
        }

        validateMethodArgumentNullability(ctx, receiverTypeInfo.getName(), currentStep);

        handlePNNA(PNNAContextAdapter.from(ctx.pNNA()), cursor);
    }

    @Override
    public Void visitPrimaryNoNewArrayTypeMethodInvocation(JPlus25Parser.PrimaryNoNewArrayTypeMethodInvocationContext ctx) {
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handlePrimaryNoNewArrayTypeMethodInvocation(ctx, resolvedChain.stepCursor()));
        return null;
    }

    private void handlePrimaryNoNewArrayTypeMethodInvocation(JPlus25Parser.PrimaryNoNewArrayTypeMethodInvocationContext ctx, StepCursor cursor) {
        processTypeName(ctx.typeName(), cursor);
        processExplicitReceiverMethodInvocationSignature(MethodInvocationSignatureContextAdapter.from(ctx), cursor);
    }

    private void processExplicitReceiverMethodInvocationSignature(MethodInvocationSignatureContextAdapter ctx, StepCursor cursor) {
        var prevStep = cursor.peekPrev().orElseThrow(() -> new IllegalStateException("A TypeName rule needed."));
        var currentStep = cursor.consume();

        System.err.println("[handlePrimaryNoNewArrayTypeMethodInvocation] prevStep = " + prevStep);
        System.err.println("[handlePrimaryNoNewArrayTypeMethodInvocation] currentStep = " + currentStep);

        String methodName = Utils.getTokenString(ctx.identifier());
        System.err.println("[handlePrimaryNoNewArrayTypeMethodInvocation] methodName = " + methodName);

        if (!Objects.equals(methodName, currentStep.symbol)) throw new IllegalStateException();

        System.err.println("[handlePrimaryNoNewArrayTypeMethodInvocation] methodInvocationInfo = " + currentStep.invocationInfo);

        TypeInfo receiverTypeInfo = prevStep.typeInfo;
        System.err.println("[handlePrimaryNoNewArrayTypeMethodInvocation] receiverTypeInfo = " + receiverTypeInfo);

        //check instance nullability
        boolean useNullsafeOperator = (ctx.NULLSAFE() != null);
        if (receiverTypeInfo.isNullable() && !useNullsafeOperator) {
            reportIssue(
                    ctx.getStart(),
                    String.format("%s is a nullable variable. But it directly accesses %s(). Consider using null-safe operator(?.).", prevStep.symbol, methodName)
            );
        }

        validateMethodArgumentNullability(ctx.originalContext(), receiverTypeInfo.getName(), currentStep);

        //handlePNNA(PNNAContextAdapter.from(ctx.pNNA()), cursor);
    }

    @Override
    public Void visitPrimaryNoNewArrayArrayMethodInvocation(JPlus25Parser.PrimaryNoNewArrayArrayMethodInvocationContext ctx) {
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handlePrimaryNoNewArrayArrayMethodInvocation(ctx, resolvedChain.stepCursor()));
        return null;
    }

    private void handlePrimaryNoNewArrayArrayMethodInvocation(JPlus25Parser.PrimaryNoNewArrayArrayMethodInvocationContext ctx, StepCursor cursor) {
        //skip: arrayCreationExpression
        cursor.consume();

        processExplicitReceiverMethodInvocationSignature(MethodInvocationSignatureContextAdapter.from(ctx), cursor);
    }

    private void handleExprQualifiedClassInstanceCreation(JPlus25Parser.PrimaryNoNewArrayExprQualifiedClassInstanceCreationContext ctx, StepCursor cursor) {
        handleExpressionName(ctx.expressionName(), cursor);
        handleUnqualifiedClassInstanceCreationExpression(PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter.from(ctx), cursor);
    }

    @Override
    public Void visitPrimaryNoNewArrayQualifiedSuperMethodInvocation(JPlus25Parser.PrimaryNoNewArrayQualifiedSuperMethodInvocationContext ctx) {
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handlePrimaryNoNewArrayQualifiedSuperMethodInvocation(ctx, resolvedChain.stepCursor()));
        return null;
    }

    private void handlePrimaryNoNewArrayQualifiedSuperMethodInvocation(JPlus25Parser.PrimaryNoNewArrayQualifiedSuperMethodInvocationContext ctx, StepCursor cursor) {
        processTypeName(ctx.typeName(), cursor);
        //skip: super
        cursor.consume();
        processExplicitReceiverMethodInvocationSignature(MethodInvocationSignatureContextAdapter.from(ctx), cursor);
    }

    @Override
    public Void visitPrimaryNoNewArrayParenExpression(JPlus25Parser.PrimaryNoNewArrayParenExpressionContext ctx) {
        //'(' expression ')' pNNA?
        withPrimaryNoNewArrayChain(ctx, resolvedChain -> handlePrimaryNoNewArrayParenExpression(ctx, resolvedChain.stepCursor()));
        return null;
    }

    private void handlePrimaryNoNewArrayParenExpression(JPlus25Parser.PrimaryNoNewArrayParenExpressionContext ctx, StepCursor cursor) {
        handleExpressionWithParenthesis(ctx, cursor);
    }

    private void handleExpressionWithParenthesis(JPlus25Parser.PrimaryNoNewArrayParenExpressionContext ctx, StepCursor cursor) {
        log("[handleExpressionWithParenthesis] expression = " + Utils.getTokenString(ctx.expression()));
        log("[handleExpressionWithParenthesis] step = " + cursor.peek());

        super.visitExpression(ctx.expression());

        if (ctx.pNNA() != null) {

            String stepSymbol = Utils.getTokenString(ctx.pNNA()).replaceAll("^\\.", "");
            log("[handleExpressionWithParenthesis] stepSymbol = " + stepSymbol);

            while(cursor.hasNext()) {
                if (Objects.equals(stepSymbol, cursor.peek().orElseThrow(IllegalStateException::new).symbol)) break;
                log("[handleExpressionWithParenthesis] cursor.peek() = " + cursor.peek().orElseThrow(IllegalStateException::new).symbol);
                cursor.consume();
            }

            handlePNNA(PNNAContextAdapter.from(ctx.pNNA()), cursor);
        }
    }

    private void processTypeName(JPlus25Parser.TypeNameContext ctx, StepCursor cursor) {
        processPackageName(ctx.packageName(), cursor);
        processTypeIdentifier(ctx.typeIdentifier(), cursor);
    }

    private void processTypeIdentifier(JPlus25Parser.TypeIdentifierContext ctx, StepCursor cursor) {
        if (ctx == null) return;

        var step = cursor.consume();
        if (!Objects.equals(step.symbol, Utils.getTokenString(ctx))) throw new IllegalStateException();
    }

    private void processPackageName(JPlus25Parser.PackageNameContext ctx, StepCursor cursor) {
        if (ctx == null) return;

        var step = cursor.consume();
        if (!Objects.equals(step.symbol, Utils.getTokenString(ctx.identifier()))) throw new IllegalStateException();

        processPackageName(ctx.packageName(), cursor);
    }

    private void handleUnqualifiedClassInstanceCreationExpression(PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter ctx, StepCursor cursor) {
        consumedExpressions.add(Utils.getTextChangeRange(originalText, ctx.unqualifiedClassInstanceCreationExpression()));
        log("[consumedExpressions] " + consumedExpressions);

        cursor.peekPrev().ifPresent(prevStep -> {
            if (prevStep.typeInfo.isNullable() && ctx.NULLSAFE() == null) {
                reportIssue(
                        ctx.getStart(),
                        String.format("%s is a nullable variable. But it directly accesses %s. Consider using null-safe operator(?.).", prevStep.typeInfo.getName(), Utils.getTokenString(ctx.unqualifiedClassInstanceCreationExpression()))
                );
            }
        });

        var curStep = cursor.consume();
        log("[unqualifiedClassInstanceCreationExpression] currentStep = " + curStep);

        String typeName = curStep.typeInfo.getName();
        //String typeName = currentStep.invocationInfo.typeInfo.getName();
        log("[unqualifiedClassInstanceCreationExpression] typeName = " + typeName);

        validateMethodArgumentNullability(ctx.originalContext(), typeName, curStep);

        handlePNNA(PNNAContextAdapter.from(ctx.pNNA()), cursor);

        super.visitUnqualifiedClassInstanceCreationExpression(ctx.unqualifiedClassInstanceCreationExpression());
    }

    private void PrimaryNoNewArrayThis(PrimaryNoNewArrayContextAdapter ctx, StepCursor cursor) {
        System.err.println("[handleThisPrimary]");
        processThis(ctx, cursor);
    }

    private void processThis(PrimaryNoNewArrayContextAdapter ctx, StepCursor cursor) {
        System.err.println("[processThis]");
        ResolvedChain.Step step = cursor.consume();
        if (!step.symbol.equals(ctx.THIS().toString())) throw new IllegalStateException();

        handlePNNA(PNNAContextAdapter.from(ctx.pNNA()), cursor);
    }

    private void handlePNNA(PNNAContextAdapter ctx, StepCursor cursor) {
        if (ctx == null) return;

        log("[handlePNNA] original context = " + ctx.originalContext().getClass().getSimpleName());
        var originalCtx = ctx.originalContext();
        if (originalCtx instanceof JPlus25Parser.PNNAClassInstanceCreationContext instanceCreationContext) {
            processPNNAClassInstanceCreation(instanceCreationContext, cursor);
        } else if (originalCtx instanceof JPlus25Parser.PNNAFieldAccessContext fieldAccessContext) {
            processPNNAFieldAccessContext(fieldAccessContext, cursor);
        } else if (originalCtx instanceof JPlus25Parser.PNNAArrayAccessContext arrayAccessContext) {
            processPNNAArrayAccessContext(arrayAccessContext);
        } else if (originalCtx instanceof JPlus25Parser.PNNAMethodInvocationContext methodInvocationContext) {
            processPNNAMethodInvocationContext(methodInvocationContext, cursor);
        } else if (originalCtx instanceof JPlus25Parser.PNNAMethodReferenceContext methodReferenceContext) {
            processPNNAMethodReferenceContext(methodReferenceContext);
        }

        handlePNNA(PNNAContextAdapter.from(ctx.pNNA()), cursor);
    }

    private void processPNNAMethodReferenceContext(JPlus25Parser.PNNAMethodReferenceContext methodReferenceContext) {
    }

    private void processPNNAMethodInvocationContext(JPlus25Parser.PNNAMethodInvocationContext ctx, StepCursor cursor) {
        processExplicitReceiverMethodInvocationSignature(MethodInvocationSignatureContextAdapter.from(ctx), cursor);
        if (ctx.argumentList() != null) super.visitChildren(ctx.argumentList());
    }

    private void processPNNAArrayAccessContext(JPlus25Parser.PNNAArrayAccessContext arrayAccessContext) {
    }

    private void processPNNAFieldAccessContext(JPlus25Parser.PNNAFieldAccessContext ctx, StepCursor cursor) {
        ResolvedChain.Step prevStep = cursor.peekPrev().orElse(null);
        System.err.println("[processPNNAFieldAccessContext] prevStep = " + prevStep);

        if (isUnsafeNullableAccess(PNNAContextAdapter.from(ctx), prevStep)) {
            reportIssue(
                    ctx.start,
                    prevStep.symbol + " is a nullable variable. But it directly accesses "
                            + Utils.getTokenString(ctx.identifier()) + ". Consider using null-safe operator(?.)."
            );
        }

        cursor.consume();
    }

    private void processPNNAClassInstanceCreation(JPlus25Parser.PNNAClassInstanceCreationContext instanceCreationContext, StepCursor cursor) {
        handleUnqualifiedClassInstanceCreationExpression(PrimaryNoNewArrayUnqualifiedClassInstanceCreationContextAdapter.from(instanceCreationContext), cursor);
    }

    private boolean isUnsafeNullableAccess(PNNAContextAdapter ctx, ResolvedChain.Step prevStep) {
        return prevStep != null
                && prevStep.typeInfo.isNullable()
                && ctx.NULLSAFE() == null;
    }

    private void handleExpressionName(JPlus25Parser.ExpressionNameContext ctx, StepCursor cursor) {
        var expressionNameList = getExpressionNameList(ctx);
        processExpressionNameContext(expressionNameList, cursor);
    }

    private void validateMethodArgumentNullability(ParserRuleContext ctx, String typeName, ResolvedChain.Step invocationStep) {
        globalSymbolTable
            .resolveInCurrent(typeName, EnumSet.of(TypeInfo.Type.Class))
            .ifPresent(receiverClassSymbolInfo -> {
                System.err.println("[handleExpressionNameInternal] receiverClassSymbolInfo = " + receiverClassSymbolInfo);

                //check method parameter nullability
                SymbolTable classSymbolTable = resolveReceiverClassContextSymbolTable(receiverClassSymbolInfo);
                if (!classSymbolTable.isEmpty()) {
                    SymbolInfo methodSymbolInfo = resolveMethod(classSymbolTable, invocationStep.invocationInfo).orElseThrow(() -> new IllegalStateException("cannot find a mapping method."));

                    validateMethodArguments(ctx, invocationStep.invocationInfo, methodSymbolInfo);
                }
            });
    }

    private SymbolTable resolveReceiverClassContextSymbolTable(SymbolInfo receiverClassSymbolInfo) {
        SymbolTable symbolTable = receiverClassSymbolInfo.getSymbolTable();
        return symbolTable.getEnclosingSymbolTable(receiverClassSymbolInfo.getSymbol());
    }

    private void processExpressionNameContext(List<JPlus25Parser.ExpressionNameContext> expressionNameList, StepCursor cursor) {
        ResolvedChain.Step prevStep = null;
        for (JPlus25Parser.ExpressionNameContext ctx : expressionNameList) {
            var step = cursor.consume();

            String identifier = Utils.getTokenString(ctx.identifier());
            System.err.println("[processExpressionNameContext] identifier = " + identifier);
            if (!identifier.equals(step.symbol)) throw new IllegalStateException();

            if (prevStep != null && prevStep.typeInfo.isNullable() && ctx.NULLSAFE() == null) {
                reportIssue(
                        ctx.start,
                        prevStep.symbol + " is a nullable variable. But it directly accesses "
                                + identifier + ". Consider using null-safe operator(?.)."
                );
            }

            prevStep = step;
        }
    }

    private List<JPlus25Parser.ExpressionNameContext> getExpressionNameList(JPlus25Parser.ExpressionNameContext ctx) {
        Deque<JPlus25Parser.ExpressionNameContext> expressionNameContextDeque = new ArrayDeque<>();
        JPlus25Parser.ExpressionNameContext current = ctx;
        expressionNameContextDeque.addFirst(current);
        while (current.expressionName() != null) {
            current = current.expressionName();
            expressionNameContextDeque.addFirst(current);
        }
        return expressionNameContextDeque.stream().toList();
    }

    public boolean hasPassed() {
        return hasPassed;
    }
}
