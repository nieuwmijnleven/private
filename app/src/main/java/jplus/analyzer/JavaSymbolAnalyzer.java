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
import com.sun.source.tree.PrimitiveTypeTree;
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
import jplus.util.Utils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;

public class JavaSymbolAnalyzer extends TreePathScanner<Void, Void> {

    private String source;
    private CompilationUnitTree ast;
    private Trees trees;
    private final SymbolTable globalSymbolTable;
    private final SymbolTable topLevelSymbolTable;
    private SymbolTable currentSymbolTable;
    private JavaMethodInvocationManager javaMethodInvocationManager;
    private String packageName;

    public JavaSymbolAnalyzer(String source, CompilationUnitTree ast, Trees trees, SymbolTable globalSymbolTable) {
        this.source = source;
        this.ast = ast;
        this.trees = trees;
        this.globalSymbolTable = globalSymbolTable;
        this.topLevelSymbolTable = new SymbolTable(globalSymbolTable);
        this.currentSymbolTable = topLevelSymbolTable;
        this.javaMethodInvocationManager = new JavaMethodInvocationManager(source);
    }

    public SymbolTable getGlobalSymbolTable() {
        return globalSymbolTable;
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

    @Override
    public Void visitClass(ClassTree node, Void unused) {
        SymbolInfo classSymbolInfo = createClassSymbolInfo(node);
        if (topLevelSymbolTable.isEmpty()) {
            currentSymbolTable.declare("^PackageName$", classSymbolInfo.copyBuilder().symbol(this.packageName).build());
            currentSymbolTable.declare("^TopLevelClass$", classSymbolInfo);
        }
        declareClassSymbol(node, classSymbolInfo);

        currentSymbolTable = currentSymbolTable.addEnclosingSymbolTable(node.getSimpleName().toString(), new SymbolTable(currentSymbolTable));
        for (Tree member : node.getMembers()) {
            if (member instanceof VariableTree var) visitField(var, getCurrentPath());
        }

        try {
            return super.visitClass(node, unused);
        } finally {
            currentSymbolTable = currentSymbolTable.getParent();
        }
    }

    private SymbolInfo createClassSymbolInfo(ClassTree node) {
        TreePath path = getCurrentPath();
        TypeMirror typeMirror = trees.getTypeMirror(path);

        TypeInfo typeInfo = new TypeInfo(typeMirror.toString(), false, TypeInfo.Type.Class);
        int start = (int) trees.getSourcePositions().getStartPosition(ast, node);
        int end = (int) trees.getSourcePositions().getEndPosition(ast, node);
        TextChangeRange range = Utils.computeTextChangeRange(source, start, end - 1);

        return SymbolInfo.builder()
                .symbol(node.getSimpleName().toString())
                .typeInfo(typeInfo)
                .symbolTable(currentSymbolTable)
                .originalText(source.substring(start, end))
                .range(range)
                .build();
    }

    private void declareClassSymbol(ClassTree node, SymbolInfo classSymbolInfo) {
        String typeName = classSymbolInfo.getTypeInfo().getName();
        String simpleName = classSymbolInfo.getSymbol();
        currentSymbolTable.declare(simpleName, classSymbolInfo);
        globalSymbolTable.declare(typeName, classSymbolInfo);
    }

    private void visitField(VariableTree node, TreePath classPath) {
        TypeMirror typeMirror = trees.getTypeMirror(new TreePath(classPath, node));
        TypeInfo typeInfo = buildTypeInfo(typeMirror);

        SymbolInfo fieldSymbolInfo = createSymbolInfo(node.getName().toString(), node.getModifiers().getFlags(), typeInfo, node, currentSymbolTable);
        //System.err.println("[JavaSymbolAnalyzer] fieldSymbolInfo = " + fieldSymbolInfo);
        currentSymbolTable.declare(fieldSymbolInfo.getSymbol(), fieldSymbolInfo);
    }

    private TypeInfo buildTypeInfo(TypeMirror typeMirror) {
        TypeInfo.Builder typeInfoBuilder = TypeInfo.builder();
        typeInfoBuilder.isNullable(false);

        switch (typeMirror.getKind()) {
            case BOOLEAN, BYTE, SHORT, INT, LONG, CHAR, FLOAT, DOUBLE -> {
                typeInfoBuilder.type(TypeInfo.Type.Primitive);
                typeInfoBuilder.name(typeMirror.toString());
            }
            case DECLARED, TYPEVAR -> {
                typeInfoBuilder.type(TypeInfo.Type.Reference);
                typeInfoBuilder.name((typeMirror instanceof DeclaredType dt) ? dt.asElement().toString() : typeMirror.toString());

                for (AnnotationMirror ann : typeMirror.getAnnotationMirrors()) {
                    String annName = ann.getAnnotationType().toString();
                    if (annName.endsWith(".Nullable") || annName.equals("org.jspecify.annotations.Nullable")) {
                        typeInfoBuilder.isNullable(true);
                        break;
                    }
                }
            }
            case ARRAY -> {
                typeInfoBuilder.type(TypeInfo.Type.Array);
                typeInfoBuilder.name(typeMirror.toString());
            }
            default -> {
                typeInfoBuilder.type(TypeInfo.Type.Unknown);
                typeInfoBuilder.name((typeMirror instanceof DeclaredType dt) ? dt.asElement().toString() : typeMirror.toString());
            }
        }

        return typeInfoBuilder.build();
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

    private TypeInfo.Type getType(TypeMirror fieldTypeMirror) {
        TypeKind kind = fieldTypeMirror.getKind();
        System.err.println("[getType] type = " + kind);
        if (kind.isPrimitive()) {
            System.err.println("[getType] Primitive type: " + kind);
            return TypeInfo.Type.Primitive;
        } else if (kind == TypeKind.DECLARED || kind == TypeKind.TYPEVAR) {
            System.err.println("[getType] Reference type: " + fieldTypeMirror);
            return TypeInfo.Type.Reference;
        } else if (kind == TypeKind.ARRAY) {
            return TypeInfo.Type.Array;
        } else {
            return TypeInfo.Type.Unknown;
        }
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
        MethodInvocationInfo info = buildMethodInvocationInfo(node, qualifiedName, qualifiedName);
        javaMethodInvocationManager.addInvocationInfo(currentSymbolTable, info);
        System.err.println("[NewClass] methodInvocationInfo = " + info);
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

        List<String> argStrs = args.stream().map(Object::toString).toList();
        List<String> argTypes = args.stream()
                .map(arg -> {
                    TypeMirror tm = trees.getTypeMirror(trees.getPath(ast, arg));
                    return tm != null ? tm.toString() : "unknown";
                })
                .toList();

        MethodInvocationInfo.Builder builder = MethodInvocationInfo.builder()
                .instanceName(instanceName)
                .methodName(methodName)
                .args(argStrs)
                .argTypes(argTypes)
                .source(computeRangeText(node))
                .startPos(getSourceStartPosition(node))
                .endPos(getSourceEndPosition(node));

        TreePath path = trees.getPath(ast, node);
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
        System.err.println("[visitMethod] invoked");
//        boolean isStatic = node.getModifiers().getFlags().contains(Modifier.STATIC);

        if (node.getReturnType() == null) {
            System.err.println("Constructor: " + node.getName());
            return visitConstructorDeclaration(node, unused);
        } else {
            System.err.println("method: " + node.getName());
            return visitMethodDeclaration(node, unused);

            /*if (isStatic) {
                System.err.println("static method: " + node.getName());
                return super.visitMethod(node, unused);
            } else {
                System.err.println("instance method: " + node.getName());
                return visitInstanceMethod(node, unused);
            }*/
        }
    }

    private Void visitConstructorDeclaration(MethodTree node, Void unused) {
        return processCallable(node, unused);
    }

    private Void visitMethodDeclaration(MethodTree node, Void unused) {
        return processCallable(node, unused);
    }

    private Void processCallable(MethodTree node, Void unused) {
        SymbolTable methodSymbolTable = new SymbolTable(currentSymbolTable);
        List<String> typeNameList = new ArrayList<>();
        List<String> simpleTypeNameList = new ArrayList<>();

        for (VariableTree param : node.getParameters()) {
            List<jplus.base.Modifier> paramModifierList = new ArrayList<>();
            for (Modifier m : param.getModifiers().getFlags()) {
                paramModifierList.add(jplus.base.Modifier.valueOf(m.toString().toUpperCase()));
            }
            System.err.println("[method] paramModifierList " + paramModifierList);

            // Nullable annotation 체크
            boolean hasNullableAnnotation = param.getModifiers().getAnnotations()
                    .stream()
                    .peek(annotationTree -> System.err.println("[method] annotationTree = " + annotationTree))
                    .anyMatch(ann -> ann.toString().endsWith("@Nullable") || ann.toString().endsWith("@org.jspecify.annotations.Nullable"));

            // 변수 이름
            String variableName = param.getName().toString();
            System.err.println("[method] variableName = " + variableName);

            // primitive 여부
            Tree typeTree = param.getType();
            String typeName;
            String simpleTypeName;
            boolean isPrimitive;

            if (typeTree instanceof PrimitiveTypeTree) {
                isPrimitive = true;
                typeName = typeTree.toString(); // int, boolean 등 그대로
                simpleTypeName = typeName;
            } else {
                isPrimitive = false;
                // Reference 타입: FQN 가져오기
                TreePath typePath = TreePath.getPath(ast, typeTree);
                TypeMirror typeMirror = trees.getTypeMirror(typePath);

                simpleTypeName = param.getType().toString();
                if (typeMirror instanceof DeclaredType dt) {
                    typeName = dt.asElement().toString();
                } else {
                    typeName = typeMirror.toString();
                }
            }

            // 타입 이름
            String typeNameWithNullability = typeName + (hasNullableAnnotation ? "?" : "");
            String simpleTypeNameWithNullability = simpleTypeName + (hasNullableAnnotation ? "?" : "");
            System.err.println("[method] typeName = " + typeName);
            System.err.println("[method] typeNameWithNullability = " + typeNameWithNullability);
            System.err.println("[method] simpleTypeNameWithNullability = " + simpleTypeNameWithNullability);
            typeNameList.add(typeNameWithNullability);
            simpleTypeNameList.add(simpleTypeNameWithNullability);

            TypeInfo.Type type = (isPrimitive)
                    ? TypeInfo.Type.Primitive
                    : TypeInfo.Type.Reference;

            // nullable 여부
            boolean nullable = hasNullableAnnotation;

            TypeInfo typeInfo = new TypeInfo(typeName, nullable, type);
            System.err.println("[method] typeInfo = " + typeInfo);

            // 원본 텍스트 범위 추출
            int startPos = (int)trees.getSourcePositions().getStartPosition(ast, node);
            int endPos = (int)trees.getSourcePositions().getEndPosition(ast, node);
            TextChangeRange range = Utils.computeTextChangeRange(source, startPos, endPos - 1);
            String rangeText = param.toString();

            // 심볼 정보 생성
            SymbolInfo symbolInfo =
                    new SymbolInfo(variableName, typeInfo, range, rangeText, paramModifierList);

            // 파라미터 심볼 등록
            methodSymbolTable.declare(variableName, symbolInfo);
        }

        // ---------- 생성자 Modifier 처리 ----------
        List<jplus.base.Modifier> modifierList = node.getModifiers().getFlags().stream().map(Object::toString).map(String::toUpperCase).map(jplus.base.Modifier::valueOf).toList();

        // ---------- constructor symbol name 구성 ----------

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
        String symbolNameWithSimpleTypeName = "^" + methodName + "$_" + String.join("_", simpleTypeNameList);
        System.err.println("[method] symbolName = " + symbolName);
        System.err.println("[method] symbolNameWithSimpleTypeName = " + symbolNameWithSimpleTypeName);

        TypeInfo typeInfo = new TypeInfo(symbolName, false, type);

        int startPos = (int)trees.getSourcePositions().getStartPosition(ast, node);
        int endPos = (int)trees.getSourcePositions().getEndPosition(ast, node);
        if (endPos < 0 && "constructor".equals(methodName)) {
            endPos = startPos + 1;
        }
        TextChangeRange range = Utils.computeTextChangeRange(source, startPos, endPos - 1);
        String rangeText = source.substring(startPos, endPos);

        SymbolInfo symbolInfo = new SymbolInfo(symbolName, typeInfo, range, rangeText, modifierList);
        symbolInfo.setSymbolTable(methodSymbolTable);

        // ---------- 현재 SymbolTable에 등록 ----------
        currentSymbolTable.declare(symbolName, symbolInfo);
        currentSymbolTable.declare(symbolNameWithSimpleTypeName, symbolInfo);
        System.err.println("[method] currentSymbolTable = "  + currentSymbolTable);
        currentSymbolTable = currentSymbolTable.addEnclosingSymbolTable(symbolName, methodSymbolTable);
        System.err.println("[method] methodSymbolTable = "  + methodSymbolTable);

        try {
            return super.visitMethod(node, unused);
        } finally {
            currentSymbolTable = currentSymbolTable.getParent();
        }
    }

    @Override
    public Void visitVariable(VariableTree node, Void p) {
        TreePath path = getCurrentPath();
        Element element = trees.getElement(path);

        if (element == null) {
            return super.visitVariable(node, p);
        }

        switch (element.getKind()) {
            case LOCAL_VARIABLE:
                handleLocalVariable(node, p);
                break;

            default:
                return super.visitVariable(node, p);
        }

        return super.visitVariable(node, p);
    }




    private Void handleLocalVariable(VariableTree node, Void unused) {
        List<jplus.base.Modifier> modifierList = new ArrayList<>();
        for (Modifier m : node.getModifiers().getFlags()) {
            modifierList.add(jplus.base.Modifier.valueOf(m.toString().toUpperCase()));
        }

        TypeInfo.Builder typeInfoBuilder = TypeInfo.builder();
        Tree typeTree = node.getType();
        boolean isNullable = false;

        TreePath typePath = TreePath.getPath(getCurrentPath().getCompilationUnit(), typeTree);
        TypeMirror typeMirror = trees.getTypeMirror(typePath);

        TypeInfo.Type type = getType(typeMirror);
        if (type == TypeInfo.Type.Primitive) {
            typeInfoBuilder.name(typeTree.toString());
            typeInfoBuilder.isNullable(false);
        } else if (type == TypeInfo.Type.Reference) {
            typeInfoBuilder.name(((DeclaredType)typeMirror).asElement().toString());
            // annotation으로 nullable 체크
            for (AnnotationMirror annotationMirror : typeMirror.getAnnotationMirrors()) {
                DeclaredType annType = annotationMirror.getAnnotationType();
                System.err.println("annType = " + annType.toString());
                if (annType.toString().endsWith(".Nullable") || annType.toString().equals("org.jspecify.annotations.Nullable")) {
                    isNullable = true;
                    break;
                }
            }
            typeInfoBuilder.isNullable(isNullable);
        } else {
            typeInfoBuilder.name(typeMirror.toString());
            typeInfoBuilder.isNullable(false);
        }
        typeInfoBuilder.type(type);

        // primitive 타입 처리
        /*if (typeTree instanceof PrimitiveTypeTree) {
            typeInfoBuilder.name(typeTree.toString());
            typeInfoBuilder.type(TypeInfo.Type.Primitive);
            typeInfoBuilder.isNullable(false);
        } else {
            // reference 타입 처리
            TreePath typePath = TreePath.getPath(getCurrentPath().getCompilationUnit(), typeTree);
            TypeMirror typeMirror = trees.getTypeMirror(typePath);

            if (typeMirror instanceof DeclaredType dt) {
                typeInfoBuilder.name(dt.asElement().toString());
                typeInfoBuilder.type(TypeInfo.Type.Reference);
            } else {
                typeInfoBuilder.name(typeMirror.toString());
                typeInfoBuilder.type(TypeInfo.Type.Reference);
            }

            // annotation으로 nullable 체크
            for (AnnotationMirror annotationMirror : typeMirror.getAnnotationMirrors()) {
                DeclaredType annType = annotationMirror.getAnnotationType();
                System.err.println("annType = " + annType.toString());
                if (annType.toString().endsWith(".Nullable") || annType.toString().equals("org.jspecify.annotations.Nullable")) {
                    isNullable = true;
                    break;
                }
            }
            typeInfoBuilder.isNullable(isNullable);
        }*/

        int startPos = (int) trees.getSourcePositions().getStartPosition(getCurrentPath().getCompilationUnit(), node);
        int endPos = (int) trees.getSourcePositions().getEndPosition(getCurrentPath().getCompilationUnit(), node);
        TextChangeRange range = Utils.computeTextChangeRange(source, startPos, endPos - 1);

        SymbolInfo symbolInfo = SymbolInfo.builder()
                .symbol(node.getName().toString())
                .typeInfo(typeInfoBuilder.build())
                .modifierList(modifierList)
                .symbolTable(currentSymbolTable)
                .originalText(source.substring(startPos, endPos))
                .range(range)
                .build();

        currentSymbolTable.declare(symbolInfo.getSymbol(), symbolInfo);

        System.err.println("[variable] currentSymbolTable = " + currentSymbolTable);

        return super.visitVariable(node, unused);
    }

    @Override
    public Void visitMethodInvocation(MethodInvocationTree node, Void unused) {
        String instanceName = null;
        String methodName = "";

        ExpressionTree methodSelect = node.getMethodSelect();
        if (methodSelect instanceof MemberSelectTree mst) {
            instanceName = mst.getExpression().toString();
            methodName = mst.getIdentifier().toString();
        } else if (methodSelect instanceof IdentifierTree it) {
            methodName = it.getName().toString();
        }

        MethodInvocationInfo info = buildMethodInvocationInfo(node, instanceName, methodName);
        javaMethodInvocationManager.addInvocationInfo(currentSymbolTable, info);
        System.err.println("methodInvocationInfo = " + info);

        return super.visitMethodInvocation(node, unused);
    }

    @Override
    public Void visitBlock(BlockTree node, Void unused) {
        System.err.println("[visitBlock] invoked");
        currentSymbolTable = currentSymbolTable.getEnclosingSymbolTable("^block$");
        try {
            return super.visitBlock(node, unused);
        } finally {
            currentSymbolTable = currentSymbolTable.getParent();
        }
    }

}