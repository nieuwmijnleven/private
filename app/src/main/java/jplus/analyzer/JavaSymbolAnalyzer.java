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

import com.sun.source.tree.BlockTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.NewClassTree;
import com.sun.source.tree.PackageTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;
import jplus.analyzer.util.CleanTypePrinter;
import jplus.base.JavaMethodInvocationManager;
import jplus.base.MethodInvocationInfo;
import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.base.TypeInfo;
import jplus.generator.TextChangeRange;
import jplus.util.MethodUtils;
import jplus.util.TypeUtils;
import jplus.util.Utils;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.ArrayList;
import java.util.List;

public class JavaSymbolAnalyzer extends TreePathScanner<Void, Void> {

    private final String source;
    private final CompilationUnitTree ast;
    private final Trees trees;
    private final SymbolTable globalSymbolTable;
    private final SymbolTable topLevelSymbolTable;
    private SymbolTable currentSymbolTable;

    private final Elements elements;
    private final Types types;

    private final JavaMethodInvocationManager javaMethodInvocationManager;
    private String packageName;

    public JavaSymbolAnalyzer(String source, CompilationUnitTree ast, Trees trees, SymbolTable globalSymbolTable, Elements elements, Types types) {
        this.source = source;
        this.ast = ast;
        this.trees = trees;
        this.elements = elements;
        this.types = types;
        this.globalSymbolTable = globalSymbolTable;
        this.topLevelSymbolTable = new SymbolTable(globalSymbolTable);
        this.currentSymbolTable = topLevelSymbolTable;
        this.javaMethodInvocationManager = new JavaMethodInvocationManager(source);
    }

    public SymbolTable getTopLevelSymbolTable() {
        return topLevelSymbolTable;
    }

    public JavaMethodInvocationManager getJavaMethodInvocationManager() {
        return javaMethodInvocationManager;
    }

    @Override
    public Void visitPackage(PackageTree node, Void unused) {
        this.packageName = node.getPackageName().toString();
        return super.visitPackage(node, unused);
    }

    private void enterSymbolTable(String symbol) {
        currentSymbolTable = currentSymbolTable.getEnclosingSymbolTable(symbol);
    }

    private void enterSymbolTable(String symbol, SymbolTable table) {
        currentSymbolTable = currentSymbolTable.addEnclosingSymbolTable(symbol, table);
    }

    private void exitSymbolTable() {
        currentSymbolTable = currentSymbolTable.getParent();
    }

    @Override
    public Void visitClass(ClassTree node, Void unused) {
        SymbolInfo classSymbolInfo = createClassSymbolInfo(node);
        if (topLevelSymbolTable.isEmpty()) {
            currentSymbolTable.declare("^PackageName$", classSymbolInfo.toBuilder().symbol(this.packageName).build());
            currentSymbolTable.declare("^TopLevelClass$", classSymbolInfo);
        }
        declareClassSymbol(classSymbolInfo);
        System.err.println("[JavaSymbolAnalyzer] classSymbolInfo = " + classSymbolInfo);

        System.err.println("[JavaSymbolAnalyzer] node.getSimpleName().toString() = " + node.getSimpleName().toString());
        enterSymbolTable(node.getSimpleName().toString());
        for (Tree member : node.getMembers()) {
            if (member instanceof VariableTree var) visitField(var, getCurrentPath());
        }

        try {
            return super.visitClass(node, unused);
        } finally {
            exitSymbolTable();
        }
    }

    private SymbolInfo createClassSymbolInfo(ClassTree node) {
        TreePath path = getCurrentPath();
        Element element = trees.getElement(path);
        TypeMirror typeMirror = trees.getTypeMirror(path);

        //TypeInfo typeInfo = new TypeInfo(typeMirror.toString(), false, TypeInfo.Type.Class);
        TypeInfo typeInfo = TypeUtils.fromTypeMirror(typeMirror, element);

        return SymbolInfo.builder()
                .symbol(node.getSimpleName().toString())
                .typeInfo(typeInfo)
                .symbolTable(currentSymbolTable)
                .range(computeRange(node))
                .originalText(computeRangeText(node))
                .build();
    }

    private void declareClassSymbol(SymbolInfo classSymbolInfo) {
        String typeName = classSymbolInfo.getTypeInfo().getName();
        String simpleName = classSymbolInfo.getSymbol();
        currentSymbolTable.declare(simpleName, classSymbolInfo);
        globalSymbolTable.declare(typeName, classSymbolInfo);
    }

    private void visitField(VariableTree node, TreePath classPath) {
        TreePath path = new TreePath(classPath, node);
        Element element = trees.getElement(path);
        TypeMirror typeMirror = trees.getTypeMirror(path);
        TypeInfo typeInfo = buildTypeInfo(typeMirror, element);

        SymbolInfo fieldSymbolInfo = createSymbolInfo(node.getName().toString(), node.getModifiers().getFlags(), typeInfo, node, currentSymbolTable);
        System.err.println("[JavaSymbolAnalyzer] fieldSymbolInfo = " + fieldSymbolInfo);
        currentSymbolTable.declare(fieldSymbolInfo.getSymbol(), fieldSymbolInfo);
    }

    private TypeInfo buildTypeInfo(TypeMirror typeMirror, Element originalElement) {
        return TypeUtils.fromTypeMirror(typeMirror, originalElement);
    }

    private List<jplus.base.Modifier> convertModifiers(Iterable<Modifier> modifiers) {
        List<jplus.base.Modifier> result = new ArrayList<>();
        for (Modifier m : modifiers) result.add(jplus.base.Modifier.valueOf(m.toString().toUpperCase()));
        return result;
    }

    private SymbolInfo createSymbolInfo(String name, Iterable<Modifier> modifiers, TypeInfo typeInfo, Tree node, SymbolTable table) {
        return new SymbolInfo(name, typeInfo, computeRange(node), computeRangeText(node), convertModifiers(modifiers), table);
    }

    private TextChangeRange computeRange(Tree node) {
        int start = getSourceStartPosition(node);
        int end = getSourceEndPosition(node);
        return Utils.computeTextChangeRange(source, start, end - 1);
    }

    private String computeRangeText(Tree node) {
        int start = getSourceStartPosition(node);
        int end = getSourceEndPosition(node);
        return (start < end) ? source.substring(start, end) : "";
    }

    private int getSourceStartPosition(Tree node) {
        return (int) trees.getSourcePositions().getStartPosition(ast, node);
    }

    private int getSourceEndPosition(Tree node) {
        int endPos = (int) trees.getSourcePositions().getEndPosition(ast, node);
        return endPos < getSourceStartPosition(node) ? getSourceStartPosition(node) + 1 : endPos;
    }

    private String getQualifiedName(Tree node) {
        TreePath path = trees.getPath(ast, node);
        if (path != null) {
            TypeMirror tm = trees.getTypeMirror(path);
            if (tm instanceof DeclaredType dt && dt.asElement() instanceof TypeElement te) {
                return te.getQualifiedName().toString();
            }
        }

        if (node instanceof MemberSelectTree mst) {
            return mst.toString();
        }

        if (node instanceof IdentifierTree idt) {
            return idt.getName().toString();
        }

        return node.toString();
    }


    @Override
    public Void visitNewClass(NewClassTree node, Void unused) {
        String qualifiedName = getQualifiedName(node);

        JavaSymbolResolver resolver = new JavaSymbolResolver(globalSymbolTable, elements, types);
        SymbolInfo symbolInfo = resolver.resolveClass(qualifiedName);
        System.err.println("[JavaSymbolResolver] symbolInfo = " + symbolInfo);

        MethodInvocationInfo info = buildMethodInvocationInfo(node, qualifiedName, qualifiedName);
        javaMethodInvocationManager.addInvocationInfo(currentSymbolTable, info);
        //System.err.println("[NewClass] methodInvocationInfo = " + info);
        return super.visitNewClass(node, unused);
    }

    private MethodInvocationInfo buildMethodInvocationInfo(Tree node, String instanceName, String methodName) {
        List<? extends ExpressionTree> args;
        if (node instanceof MethodInvocationTree mit) {
            args = mit.getArguments();
        } else if (node instanceof NewClassTree nct) {
            args = nct.getArguments();
        } else {
            args = List.of();
        }

        List<String> argStrings = args.stream().map(Object::toString).toList();
        List<String> argTypes = args.stream()
                .map(arg -> {
                    TypeMirror tm = trees.getTypeMirror(trees.getPath(ast, arg));
                    return tm != null ? tm.toString() : "unknown";
                })
                .toList();

        TreePath path = trees.getPath(ast, node);
        Element element = trees.getElement(path);
        TypeMirror typeMirror = trees.getTypeMirror(path);

        MethodInvocationInfo.Builder builder = MethodInvocationInfo.builder()
                .instanceName(instanceName)
                .typeInfo(buildTypeInfo(typeMirror, element))
                .methodName(methodName)
                .args(argStrings)
                .argTypes(argTypes)
                .source(computeRangeText(node))
                .startPos(getSourceStartPosition(node))
                .endPos(getSourceEndPosition(node));

//        TreePath path = trees.getPath(ast, node);
        if (path != null) {
            Element e = trees.getElement(path);
            if (e instanceof ExecutableElement ee) {
                CleanTypePrinter printer = new CleanTypePrinter();
                List<String> paramTypes = ((ExecutableType) ee.asType()).getParameterTypes().stream().map(printer::print).toList();
                builder.paramTypes(paramTypes).returnType(((ExecutableType) ee.asType()).getReturnType().toString());
            }
        }

        return builder.build();
    }

    @Override
    public Void visitMethod(MethodTree node, Void unused) {
        if (node.getReturnType() == null) {
            //System.err.println("Constructor: " + node.getName());
            return visitConstructorDeclaration(node, unused);
        } else {
            //System.err.println("method: " + node.getName());
            return visitMethodDeclaration(node, unused);
        }
    }

    private Void visitConstructorDeclaration(MethodTree node, Void unused) {
        return processCallable(node, unused);
    }

    private Void visitMethodDeclaration(MethodTree node, Void unused) {
        return processCallable(node, unused);
    }

    private Void processCallable(MethodTree node, Void unused) {
        // 메서드/생성자 전용 심볼 테이블 생성
        SymbolTable methodSymbolTable = new SymbolTable(currentSymbolTable);
        List<String> typeNameList = new ArrayList<>();
        List<String> simpleTypeNameList = new ArrayList<>();

        for (VariableTree param : node.getParameters()) {
            // TypeMirror 기반 TypeInfo 생성
            TypeMirror typeMirror = trees.getTypeMirror(TreePath.getPath(ast, param.getType()));
            Element element = trees.getElement(TreePath.getPath(ast, param));
            TypeInfo typeInfo = buildTypeInfo(typeMirror, element);

            // 이름 리스트 생성 (FQN / simple)
            String typeNameWithNullability = typeInfo.getName() + (typeInfo.isNullable() ? "?" : "");
            //System.err.println("[method] typeNameWithNullability = " + typeNameWithNullability);
            String simpleTypeNameWithNullability = param.getType().toString() + (typeInfo.isNullable() ? "?" : "");
            //System.err.println("[method] simpleTypeNameWithNullability = " + simpleTypeNameWithNullability);
            typeNameList.add(typeNameWithNullability);
            simpleTypeNameList.add(simpleTypeNameWithNullability);

            // 심볼 정보 생성
            SymbolInfo symbolInfo = SymbolInfo.builder()
                    .symbol(param.getName().toString())
                    .typeInfo(typeInfo)
                    .range(computeRange(node))
                    .originalText(param.toString())
                    .modifierList(convertModifiers(param.getModifiers().getFlags()))
                    .symbolTable(methodSymbolTable)
                    .build();

            // 파라미터 심볼 등록
            methodSymbolTable.declare(symbolInfo.getSymbol(), symbolInfo);
        }

        // ---------- method/constructor symbol name 구성 ----------
        String methodName = node.getName().toString();
        TypeInfo.Type type = TypeInfo.Type.Method;
        if (methodName.equals("<init>")) {
            if (!MethodUtils.isExplicitConstructor(node, trees, getCurrentPath())) {
                return super.visitMethod(node, unused);
            }
            methodName = "constructor";
            type = TypeInfo.Type.Constructor;
        }

        String symbolName = "^" + methodName + "$_" + String.join("_", typeNameList);
        //System.err.println("[method] symbolName = " + symbolName);
        String symbolNameWithSimpleTypeName = "^" + methodName + "$_" + String.join("_", simpleTypeNameList);
        //System.err.println("[method] symbolNameWithSimpleTypeName = " + symbolNameWithSimpleTypeName);

        TypeInfo typeInfo = new TypeInfo(symbolName, false, type);
        SymbolInfo symbolInfo = new SymbolInfo(symbolName, typeInfo, computeRange(node), computeRangeText(node), convertModifiers(node.getModifiers().getFlags()), currentSymbolTable);

        // ---------- 현재 SymbolTable에 등록 ----------
        currentSymbolTable.declare(symbolName, symbolInfo);
        currentSymbolTable.declare(symbolNameWithSimpleTypeName, symbolInfo);
        //System.err.println("[method] currentSymbolTable = "  + currentSymbolTable);

        enterSymbolTable(symbolName, methodSymbolTable);
        try {
            return super.visitMethod(node, unused);
        } finally {
            exitSymbolTable();
        }
    }

    @Override
    public Void visitVariable(VariableTree node, Void p) {
        TreePath path = getCurrentPath();
        Element element = trees.getElement(path);

        if (element != null && element.getKind() == ElementKind.LOCAL_VARIABLE) {
            return handleLocalVariable(node, p);
        }

        return super.visitVariable(node, p);
    }

    private Void handleLocalVariable(VariableTree node, Void unused) {
        //TypeMirror typeMirror = trees.getTypeMirror(getCurrentPath());
        TreePath typePath = TreePath.getPath(ast, node.getType());
        Element element = trees.getElement(TreePath.getPath(ast, node));
        TypeMirror typeMirror = trees.getTypeMirror(typePath);

        SymbolInfo symbolInfo = SymbolInfo.builder()
                .symbol(node.getName().toString())
                .typeInfo(buildTypeInfo(typeMirror, element))
                .modifierList(convertModifiers(node.getModifiers().getFlags()))
                .symbolTable(currentSymbolTable)
                .originalText(computeRangeText(node))
                .range(computeRange(node))
                .build();

        currentSymbolTable.declare(symbolInfo.getSymbol(), symbolInfo);
        //System.err.println("[handleLocalVariable] currentSymbolTable = " + currentSymbolTable);

        return super.visitVariable(node, unused);
    }

    private String getFQN(ExpressionTree expr) {
        Element element = trees.getElement(TreePath.getPath(ast, expr)); // TreePath 필요
        if (element != null) {
            return element.asType().toString(); // 타입의 FQN
        }
        return expr.toString(); // fallback
    }

    private String getFieldFQN(ExpressionTree expr) {
        Element element = trees.getElement(TreePath.getPath(ast, expr));
        if (element != null) {
            if (element.getKind().isField()) {
                String classFQN = ((TypeElement) element.getEnclosingElement()).getQualifiedName().toString();
                return classFQN + "." + element.getSimpleName().toString(); // java.lang.System.out
            } else if (element.getKind() == ElementKind.METHOD) {
                String classFQN = ((TypeElement) element.getEnclosingElement()).getQualifiedName().toString();
                return classFQN + "." + element.getSimpleName().toString();
            } else if (element.getKind().isClass() || element.getKind().isInterface()) {
                return ((TypeElement) element).getQualifiedName().toString();
            }
        }
        return expr.toString(); // fallback
    }

    /*@Override
    public Void visitMethodInvocation(MethodInvocationTree node, Void unused) {
        String instanceName = null;
        String fqnInstanceName = null;
        String instanceTypeName = null;
        String methodName = "";

        ExpressionTree methodSelect = node.getMethodSelect();
        if (methodSelect instanceof MemberSelectTree mst) {
            instanceName = mst.getExpression().toString();
            fqnInstanceName = getFieldFQN(mst.getExpression());
            instanceTypeName = getFQN(mst.getExpression());
            methodName = mst.getIdentifier().toString();
        } else if (methodSelect instanceof IdentifierTree it) {
            methodName = it.getName().toString();
        }

        if (instanceName != null) {
            JavaSymbolResolver resolver = new JavaSymbolResolver(globalSymbolTable, elements, types);
            SymbolInfo symbolInfo = resolver.resolveClass(instanceTypeName);
            if (symbolInfo != null) {
                System.err.println("[JavaSymbolResolver] instanceName = " + instanceName);
                System.err.println("[JavaSymbolResolver] fqnInstanceName = " + fqnInstanceName);
                System.err.println("[JavaSymbolResolver] instanceTypeName = " + instanceTypeName);
                System.err.println("[JavaSymbolResolver] symbolInfo = " + symbolInfo);
                currentSymbolTable.declare(instanceName, symbolInfo.toBuilder().symbol(instanceName).build());
            }
        }

        MethodInvocationInfo info = buildMethodInvocationInfo(node, instanceName, methodName);
        javaMethodInvocationManager.addInvocationInfo(currentSymbolTable, info);
        System.err.println("[JavaSymbolAnalyzer] methodInvocationInfo = " + info);

        return super.visitMethodInvocation(node, unused);
    }*/

    @Override
    public Void visitMethodInvocation(MethodInvocationTree node, Void unused) {

        System.err.println("[JavaSymbolAnalyzer] code = " + node.getMethodSelect().toString());

        // 🔥 여기서 체인 전체 해석
        //SymbolInfo ownerType = resolveExpressionChain(node.getMethodSelect());
        SymbolInfo ownerType = resolveExpressionChain(
                node.getMethodSelect() instanceof MemberSelectTree mst
                        ? mst.getExpression()
                        : node.getMethodSelect()
        );
        System.err.println("[JavaSymbolAnalyzer] ownerType = " + ownerType);

        Element e = trees.getElement(getCurrentPath());
        if (e instanceof ExecutableElement method) {

            String methodName = method.getSimpleName().toString();
            System.err.println("[JavaSymbolAnalyzer] methodName = " + methodName);

            MethodInvocationInfo info =
                    buildMethodInvocationInfo(
                            node,
                            ownerType != null ? ownerType.getSymbol() : null,
                            methodName
                    );

            javaMethodInvocationManager.addInvocationInfo(currentSymbolTable, info);
            System.err.println("[JavaSymbolAnalyzer] methodInvocationInfo = " + info);
        }

        return super.visitMethodInvocation(node, unused);
    }

    public SymbolInfo resolveExpressionChain(ExpressionTree expr) {

        if (expr instanceof IdentifierTree id) {
            // System
            Element e = trees.getElement(TreePath.getPath(ast, expr));
            if (e instanceof TypeElement type) {
                System.err.println("[resolveExpressionChain] identifer = " + type.getQualifiedName().toString());
                return resolveClass(
                        type.getQualifiedName().toString());
            }

            if (e instanceof VariableElement var) {
                // 변수 / 필드 / 파라미터
                TypeMirror varType = var.asType();

                if (varType.getKind() == TypeKind.DECLARED) {
                    TypeElement varTypeElement = (TypeElement) types.asElement(varType);
                    SymbolInfo typeSymbol = resolveClass(varTypeElement.getQualifiedName().toString());
                    System.err.println("[resolveExpressionChain] identifier (variable) = " + var.getSimpleName()
                            + ", type = " + varTypeElement.getQualifiedName());
                    System.err.println("[resolveExpressionChain] typeSymbol = " + typeSymbol);

                    // ownerType이 null이면 현재 SymbolTable 사용
                    SymbolTable parentTable = currentSymbolTable;
                    typeSymbol = typeSymbol.toBuilder()
                            .symbolTable(parentTable)
                            .build();
                    return typeSymbol;
                }
            }

            return null;
        }

        if (expr instanceof MemberSelectTree mst) {
            // 1️⃣ 왼쪽 먼저 처리 (System / System.out)
            SymbolInfo ownerType =
                    resolveExpressionChain(mst.getExpression());

            // 2️⃣ 현재 멤버의 Element
            Element e = trees.getElement(TreePath.getPath(ast, expr));
            if (e == null) return ownerType;

            // 3️⃣ 필드 접근
            if (e instanceof VariableElement field) {
                TypeMirror fieldType = field.asType();
                TypeElement fieldTypeElement =
                        (TypeElement) types.asElement(fieldType);

                System.err.println("[resolveExpressionChain] field = " + field.toString());
                System.err.println("[resolveExpressionChain] fieldType = " + fieldTypeElement.getQualifiedName().toString());
                return resolveClass(
                        fieldTypeElement.getQualifiedName().toString());
            }

            // 4️⃣ 메서드 접근
            if (e instanceof ExecutableElement method) {

                TypeMirror returnType = method.getReturnType();
                System.err.println("[resolveExpressionChain] returnType = " + returnType.toString());

                // 1️⃣ void 처리 (체인 종료)
                if (TypeKind.VOID == returnType.getKind()) {
                    System.err.println(
                            "[resolveExpressionChain] method returns void: "
                                    + method.getSimpleName()
                    );
                    return null;
                }

                // 2️⃣ primitive 처리 (int, boolean ...)
                if (returnType.getKind().isPrimitive()) {
                    System.err.println(
                            "[resolveExpressionChain] method returns primitive: "
                                    + returnType
                    );
                    return null;
                }

                // 3️⃣ DeclaredType (일반 클래스, 인터페이스)
                if (returnType.getKind() == TypeKind.DECLARED) {
                    TypeElement returnTypeElement =
                            (TypeElement) types.asElement(returnType);

                    return resolveClass(
                            returnTypeElement.getQualifiedName().toString()
                    );
                }

                // 4️⃣ TypeVariable (T, E, K, V 등)
                if (returnType.getKind() == TypeKind.TYPEVAR) {
                    TypeVariable tv = (TypeVariable) returnType;

                    // 상한(bound)으로 해석
                    TypeMirror upperBound = tv.getUpperBound();

                    // 보통 Object
                    if (upperBound.getKind() == TypeKind.DECLARED) {
                        TypeElement boundElement =
                                (TypeElement) types.asElement(upperBound);

                        return resolveClass(
                                boundElement.getQualifiedName().toString()
                        );
                    }

                    return null;
                }

                // 5️⃣ ArrayType
                if (returnType.getKind() == TypeKind.ARRAY) {
                    ArrayType at = (ArrayType) returnType;
                    TypeMirror component = at.getComponentType();

                    if (component.getKind() == TypeKind.DECLARED) {
                        TypeElement componentElement =
                                (TypeElement) types.asElement(component);

                        return resolveClass(
                                componentElement.getQualifiedName().toString()
                        );
                    }
                }

                return null;
            }
        }

        return null;
    }


    private SymbolInfo resolveField(VariableElement field) {

        // 1. 필드 소유 타입
        TypeElement owner =
                (TypeElement) field.getEnclosingElement();

        // 2. 소유 타입 심볼
        SymbolInfo ownerSymbol =
                resolveClass(owner.getQualifiedName().toString());

        // 3. 필드 타입
        TypeMirror fieldType = field.asType();
        TypeInfo fieldTypeInfo =
                TypeUtils.fromTypeMirror(fieldType, field);

        // 4. 필드 심볼 등록
        SymbolInfo fieldSymbol = SymbolInfo.builder()
                .symbol(field.getSimpleName().toString())
                .typeInfo(fieldTypeInfo)
                .symbolTable(ownerSymbol.getSymbolTable())
                .build();

        ownerSymbol.getSymbolTable()
                .declare(fieldSymbol.getSymbol(), fieldSymbol);

        // 5. 필드 타입으로 이동
        return resolveTypeMirror(fieldType);
    }

    private SymbolInfo resolveMethod(ExecutableElement method) {

        // 1. 메서드 소유 타입
        TypeElement owner =
                (TypeElement) method.getEnclosingElement();

        // 2. 소유 타입 심볼
        SymbolInfo ownerSymbol =
                resolveClass(owner.getQualifiedName().toString());

        // 3. 반환 타입으로 이동
        return resolveTypeMirror(method.getReturnType());
    }


    private SymbolInfo resolveTypeMirror(TypeMirror tm) {
        if (tm.getKind().isPrimitive()) {
            return null; // primitive 종료
        }

        TypeElement type =
                (TypeElement) types.asElement(tm);

        return resolveClass(type.getQualifiedName().toString());
    }

    private SymbolInfo resolveClass(String qualifiedName) {
        JavaSymbolResolver resolver = new JavaSymbolResolver(globalSymbolTable, elements, types);
        return resolver.resolveClass(qualifiedName);
    }

    @Override
    public Void visitBlock(BlockTree node, Void unused) {
        //System.err.println("[visitBlock] invoked");
        enterSymbolTable("^block$");
        try {
            return super.visitBlock(node, unused);
        } finally {
            exitSymbolTable();
        }
    }

}