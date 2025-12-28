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

package jplus.analyzer;

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
import jplus.util.SymbolUtils;
import jplus.util.Utils;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class NullabilityChecker extends JPlus25ParserBaseVisitor<Void> {

    private final SymbolTable globalSymbolTable;
    private final Set<SourceMappingEntry> sourceMappingEntrySet;
    private final JavaMethodInvocationManager methodInvocationManager;
    private SymbolTable currentSymbolTable;
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
        currentSymbolTable = currentSymbolTable.getEnclosingSymbolTable(symbol);
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
        //System.err.println("[NullabilityChecker] packageName = " + packageName);
        return super.visitPackageDeclaration(ctx);
    }

    @Override
    public Void visitTopLevelClassOrInterfaceDeclaration(JPlus25Parser.TopLevelClassOrInterfaceDeclarationContext ctx) {
        String className = getClassName(ctx.classDeclaration().normalClassDeclaration());
        System.err.println("className = " + className);

        SymbolInfo classSymbolInfo = getClassSymbolInfo(className);
        currentSymbolTable = classSymbolInfo.getSymbolTable();
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
            //System.err.println("[NullabilityChecker][ClassDecl] className = " + className);
            enterSymbolTable(className);
        }/* else if (ctx.enumDeclaration() != null) {

        } else if (ctx.recordDeclaration() != null) {

        }*/

        try {
            return super.visitClassDeclaration(ctx);
        } finally {
            exitSymbolTable();
        }
    }

    @Override
    public Void visitConstructorDeclaration(JPlus25Parser.ConstructorDeclarationContext ctx) {
        List<String> typeNameList = getTypeNamesFromParameterList(ctx.constructorDeclarator().formalParameterList());
        String constructorSymbol = "^constructor$_" + String.join("_", typeNameList);
        //System.err.println("SymbolName = " + symbolName);
        SymbolInfo constructorSymbolInfo = currentSymbolTable.resolveInCurrent(constructorSymbol);
        if (constructorSymbolInfo == null) throw new IllegalStateException("cannot find the symbol(" + constructorSymbol + ")");
        //System.err.println("constructorSymbolInfo = " + constructorSymbolInfo);
        enterSymbolTable(constructorSymbolInfo.getSymbol());
        //System.err.println("enclosingSymbolTable = " + currentSymbolTable);
        try {
            return super.visitConstructorDeclaration(ctx);
        } finally {
            exitSymbolTable();
        }
    }

    private List<String> extractParameterTypes(List<JPlus25Parser.FormalParameterContext> params) {
        return params.stream().map(param -> {
            boolean nullable = param.variableModifier() != null && param.variableModifier().stream()
                    .anyMatch(vm -> "@Nullable".equals(Utils.getTokenString(vm.annotation())));
            return Utils.getTokenString(param.unannType()) + (nullable ? "?" : "");
        }).toList();
    }

    private List<String> getTypeNamesFromParameterList(JPlus25Parser.FormalParameterListContext ctx) {
        if (ctx == null) return List.of();
        return extractParameterTypes(ctx.formalParameter());
    }

    @Override
    public Void visitMethodDeclaration(JPlus25Parser.MethodDeclarationContext ctx) {
        List<String> typeNameList = getTypeNamesFromParameterList(ctx.methodHeader().methodDeclarator().formalParameterList());
        String methodName = Utils.getTokenString(ctx.methodHeader().methodDeclarator().identifier());
        //System.err.println("[MethodDecl] methodName = " + methodSymbol);
        String methodSymbol = "^" + methodName + "$_" + String.join("_", typeNameList);
        //System.err.println("[MethodDecl] currentSymbolTable = " + currentSymbolTable);

        String fqnSymbolName = Optional.ofNullable(currentSymbolTable.resolveInCurrent(methodSymbol))
                .orElseThrow(() -> new IllegalStateException("There is no method symbol(" + methodSymbol + ") in the current symbol table"))
                .getSymbol();
        //System.err.println("[MethodDecl] fqnSymbolName = " + fqnSymbolName);

        enterSymbolTable(fqnSymbolName);
        try {
            return super.visitMethodDeclaration(ctx);
        } finally {
            exitSymbolTable();
        }
    }

    @Override
    public Void visitFieldDeclaration(JPlus25Parser.FieldDeclarationContext ctx) {
        for (var variableDeclaratorContext : ctx.variableDeclaratorList().variableDeclarator()) {
            String symbol = Utils.getTokenString(variableDeclaratorContext.variableDeclaratorId());
            SymbolInfo symbolInfo = currentSymbolTable.resolve(symbol);
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

    private boolean hasDot(ParserRuleContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof TerminalNode tn &&
                    tn.getSymbol().getType() == JPlus25Parser.DOT) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return 현재 expressionName에서 계산된 symbolTable (부모 재귀에서 사용)
     */
    private SymbolTable processExpressionName(JPlus25Parser.ExpressionNameContext ctx) {
        SymbolTable leftTable = currentSymbolTable; // 좌측 subtree에서 반환된 symbolTable

        if (ctx.expressionName() != null) {
            // 좌측 subtree 재귀 호출
            leftTable = processExpressionName(ctx.expressionName());
        }

        // 현재 identifier 처리
        String symbol = Utils.getTokenString(ctx.identifier());
        System.err.println("[ExpressionName] symbol = " + symbol);
        SymbolInfo symbolInfo = leftTable.resolve(symbol);
        System.err.println("[ExpressionName] symbolInfo = " + symbolInfo);

        if (symbolInfo != null && hasDot(ctx.getParent())) {
//            if (symbolInfo != null && symbolInfo.getTypeInfo().isNullable() && hasDot(ctx.getParent())) {
            if (symbolInfo.getTypeInfo().isNullable()) {
                System.err.println("[ExpressionName] nullability warning(" + ctx.start.getLine() + ") = " + symbolInfo);
                getIdentifierFromParent(ctx).ifPresent(id ->
                        reportIssue(
                                ctx.start,
                                symbol + " is a nullable variable. But it directly accesses "
                                        + id + ". Consider using null-safe operator(?.)."
                        )
                );
            }

            TypeInfo typeInfo = symbolInfo.getTypeInfo();
            String typeName = typeInfo.getName();
            if (!SymbolUtils.isFQN(typeName)) {
                typeName = Optional.ofNullable(this.packageName).map(pkg -> pkg + "." + typeInfo.getName()).orElse(typeName);
            }
            System.err.println("[ExpressionName] typeName = " + typeName);

            //System.err.println("[ExpressionName] globalSymbolTable = " + globalSymbolTable);
            SymbolInfo classSymbolInfo = globalSymbolTable.resolveInCurrent(typeName);
            SymbolTable topLevelTable = classSymbolInfo.getSymbolTable();
            SymbolInfo topLevelClassInfo = topLevelTable.resolve("^TopLevelClass$");

            // symbolTable을 부모 재귀 호출에 전달
            return topLevelTable.getEnclosingSymbolTable(topLevelClassInfo.getSymbol());
        }

        return leftTable;
    }

    private Optional<String> getIdentifierFromParent(JPlus25Parser.ExpressionNameContext ctx) {
        var parent = ctx.getParent();

        if (parent instanceof JPlus25Parser.ExpressionNameContext expressionNameCtx) {
            return Optional.ofNullable(
                    Utils.getTokenString(expressionNameCtx.identifier())
            );
        }

        if (parent instanceof JPlus25Parser.PrimaryNoNewArrayContext pnnaCtx) {
            return Optional.ofNullable(
                    Utils.getTokenString(pnnaCtx.identifier())
            );
        }

        if (parent instanceof JPlus25Parser.MethodInvocationContext miCtx) {
            return Optional.ofNullable(
                    Utils.getTokenString(miCtx.identifier())
            );
        }

        return Optional.empty();
    }

    @Override
    public Void visitExpressionName(JPlus25Parser.ExpressionNameContext ctx) {
        System.err.println("[ExpressionName] code = " + Utils.getTokenString(ctx));
        processExpressionName(ctx);
//        return super.visitExpressionName(ctx);
        return null;
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
//        return symbolTable;
        }

        String classSymbol;
        if(symbolTable.containsInCurrent("^TopLevelClass$", TypeInfo.Type.Class) ) {
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
                //.peek(entry -> log("[findTransformedRange] originalRange = " + entry.getOriginalRange()))
                .filter(entry -> instanceRange.equals(entry.getOriginalRange()))
                .peek(entry -> log("[findTransformedRange] originalRange = " + entry.getOriginalRange() + ", transformedRange = " + entry.getTransformedRange()))
                .map(SourceMappingEntry::getTransformedRange)
                .findFirst();
    }

    private Optional<SymbolInfo> resolveClassSymbol(MethodInvocationInfo info) {
        return Optional.ofNullable(info.instanceName)
                .map(className -> currentSymbolTable.resolve(className));
    }

    private Optional<SymbolInfo> resolveMethod(SymbolTable classSymbolTable, MethodInvocationInfo info) {
        String methodName = info.instanceName.equals(info.methodName) ? "constructor" : info.methodName;
        List<String> candidates = MethodUtils.getCandidates(methodName, info.paramTypes);
        candidates.forEach(c -> log("[InstanceCreationExpression] candidate = " + c));

        System.err.println("[resolveMethod] classSymbolTable = " + classSymbolTable);

        for (String candidate : candidates) {
            SymbolInfo methodSymbolInfo = classSymbolTable.resolveInCurrent(candidate);
            if (methodSymbolInfo != null) {
                log("[resolveMethod] found method: " + methodSymbolInfo);
                return Optional.of(methodSymbolInfo);
            }
        }
        return Optional.empty();
    }

    private void validateMethodArguments(
            ParserRuleContext ctx,
            MethodInvocationInfo info,
            SymbolInfo methodSymbolInfo
    ) {
        String methodName = info.instanceName.equals(info.methodName) ? "constructor" : info.methodName;
        String raw = methodSymbolInfo.getSymbol().substring(("^" + methodName + "$_").length());
        String[] paramTypes = raw.isEmpty() ? new String[0] : raw.split("_");

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
        return !paramType.endsWith("?") && "<nulltype>".equals(argType);
    }

    private void reportInvalidNull(
            ParserRuleContext ctx,
            MethodInvocationInfo info,
            int argIndex
    ) {
        String suffix = getOrdinalSuffix(argIndex);
        String msg;
        if (info.instanceName.equals(info.methodName)) {
            msg = "The " + argIndex + suffix +
                " argument of the " + info.instanceName +
                " constructor is a non-nullable variable, but a null value is assigned to it.";
        } else {
            msg = "The " + argIndex + suffix +
                " argument of the " + info.instanceName + "." + info.methodName + "()" +
                " is a non-nullable variable, but a null value is assigned to it.";
        }
        reportIssue(ctx.start, msg);
    }

    private void log(String msg) {
        System.err.println(msg);
    }

    @Override
    public Void visitUnqualifiedClassInstanceCreationExpression(JPlus25Parser.UnqualifiedClassInstanceCreationExpressionContext ctx) {
        TextChangeRange codeRange = getCodeRange(ctx);

        Optional<TextChangeRange> transformedRange = findTransformedRange(codeRange);
        if (transformedRange.isEmpty()) {
            throw new IllegalStateException("cannot find a mapping java code range.");
        }

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

        Optional<TextChangeRange> javaInvocationCodeRange = findTransformedRange(invocationCodeRange);
        if (javaInvocationCodeRange.isEmpty()) {
            throw new IllegalStateException("cannot find a mapping java code range.");
        }

        System.err.println("[MethodInvocation] javaInvocationCodeRange: " + javaInvocationCodeRange.get());

        Optional<MethodInvocationInfo> invocationInfo = methodInvocationManager.findInvocationInfo(currentSymbolTable, javaInvocationCodeRange.get());
        if (invocationInfo.isEmpty()) {
            throw new IllegalStateException("cannot find a method invocation info.");
        }

        invocationInfo.ifPresent(info -> {
            System.err.println("[MethodInvocation] info = " + info);

            Optional<SymbolInfo> instanceSymbolInfo = resolveClassSymbol(info);
            if (instanceSymbolInfo.isEmpty()) {
                return;
            }

            //check instance nullability
            System.err.println("[MethodInvocation] symbolInfo = " + instanceSymbolInfo.get());
            TypeInfo instanceTypeInfo = instanceSymbolInfo.get().getTypeInfo();
            boolean hasNullsafeOperator = (ctx.NULLSAFE() != null);

            if (instanceTypeInfo.isNullable() && !hasNullsafeOperator) {
                String msg = ("%s is a nullable variable. But it directly accesses %s(). "
                        + "Consider using null-safe operator(?.).")
                        .formatted(info.instanceName, info.methodName);
                reportIssue(ctx.start, msg);
            }

            //check method parameter nullability
            SymbolTable classSymbolTable = resolveClassSymbolTable(instanceSymbolInfo.get());
            if (!classSymbolTable.isEmpty()) { //must repair
                Optional<SymbolInfo> methodSymbol = resolveMethod(classSymbolTable, info);
                if (methodSymbol.isEmpty()) {
                    throw new IllegalStateException("cannot find a mapping method.");
                }

                validateMethodArguments(ctx, info, methodSymbol.get());
            }
        });

        return super.visitMethodInvocation(ctx);
    }

//    @Override
//    public Void visitPrimaryNoNewArray(JPlus25Parser.PrimaryNoNewArrayContext ctx) {
//        return super.visitPrimaryNoNewArray(ctx);
//    }

    public boolean hasPassed() {
        return hasPassed;
    }
}
