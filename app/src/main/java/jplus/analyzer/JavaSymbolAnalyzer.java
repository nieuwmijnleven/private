package jplus.analyzer;

import com.sun.source.tree.BlockTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.PrimitiveTypeTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;
import jplus.base.JavaMethodInvocationManager;
import jplus.base.MethodInvocationInfo;
import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.base.TypeInfo;
import jplus.generator.TextChangeRange;
import jplus.util.Utils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.DeclaredType;
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

    public JavaSymbolAnalyzer(String source, CompilationUnitTree ast, Trees trees, SymbolTable globalSymbolTable) {
        this.source = source;
        this.ast = ast;
        this.trees = trees;
        this.globalSymbolTable = globalSymbolTable;
        this.topLevelSymbolTable = new SymbolTable(globalSymbolTable);
        this.currentSymbolTable = topLevelSymbolTable;
        this.javaMethodInvocationManager = new JavaMethodInvocationManager();
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
    public Void visitClass(ClassTree node, Void unused) {
        TreePath classPath = getCurrentPath();
        Element element = trees.getElement(classPath);
        TypeMirror typeMirror = trees.getTypeMirror(classPath);
        String typeName = typeMirror.toString();

        TypeInfo.Builder typeInfoBuilder = TypeInfo.builder();
        typeInfoBuilder.name(typeName)
                .isNullable(false)
                .type(TypeInfo.Type.Class);

        int startPos = (int)trees.getSourcePositions().getStartPosition(ast, node);
        int endPos = (int)trees.getSourcePositions().getEndPosition(ast, node);
        TextChangeRange range = Utils.computeTextChangeRange(source, startPos, endPos - 1);

        SymbolInfo classSymbolInfo = SymbolInfo.builder()
                .symbol(typeName)
                .typeInfo(typeInfoBuilder.build())
                .symbolTable(currentSymbolTable)
                .originalText(source.substring(startPos, endPos))
                .range(range)
                .build();

        if (topLevelSymbolTable.isEmpty()) {
            currentSymbolTable.declare("^TopLevelClass$", classSymbolInfo);
        }

        currentSymbolTable.declare(typeName, classSymbolInfo);
        globalSymbolTable.declare(typeName, classSymbolInfo);
        currentSymbolTable = currentSymbolTable.addEnclosingSymbolTable(typeName, new SymbolTable(currentSymbolTable));

        for (Tree member : node.getMembers()) {
            if (member instanceof VariableTree var) {
                visitField(var, classPath);
            }
        }

        System.err.println("globalSymbolTable = " + globalSymbolTable);
        System.err.println("topLevelSymbolTable = " + topLevelSymbolTable);

        try {
            return super.visitClass(node, unused);
        } finally {
            currentSymbolTable = currentSymbolTable.getParent();
            System.err.println("[class] = " + currentSymbolTable);
            System.err.println("[class] = " + (currentSymbolTable == topLevelSymbolTable));
        }
    }

    private void visitField(VariableTree node, TreePath classPath) {
        TreePath varPath = new TreePath(classPath, node);
        Element fieldElement = trees.getElement(varPath);
        TypeMirror fieldTypeMirror = trees.getTypeMirror(varPath);

        List<jplus.base.Modifier> fieldModifierList = new ArrayList<>();
        for (Modifier m : node.getModifiers().getFlags()) {
            fieldModifierList.add(jplus.base.Modifier.valueOf(m.toString().toUpperCase()));
        }
        System.err.println("[Field] fieldModifierList " + fieldModifierList);

        String fieldTypeName;
        if (fieldTypeMirror instanceof DeclaredType dt) {
            fieldTypeName = dt.asElement().toString();
        } else {
            fieldTypeName = fieldTypeMirror.toString(); // primitive 등
        }

        boolean hasNullableAnnotation = false;
        for (AnnotationMirror annotationMirror : fieldTypeMirror.getAnnotationMirrors()) {
            if (annotationMirror.getAnnotationType().toString().endsWith(".Nullable")) {
                hasNullableAnnotation = true;
            }
        }

        TypeInfo.Builder typeInfoBuilder = TypeInfo.builder();
        typeInfoBuilder.name(fieldTypeName)
                .isNullable(hasNullableAnnotation)
                .type(TypeInfo.Type.Reference);

        int startPos = (int)trees.getSourcePositions().getStartPosition(ast, node);
        int endPos = (int)trees.getSourcePositions().getEndPosition(ast, node);
        TextChangeRange range = Utils.computeTextChangeRange(source, startPos, endPos - 1);

        SymbolInfo fieldSymbolInfo = SymbolInfo.builder()
                .symbol(fieldTypeName)
                .typeInfo(typeInfoBuilder.build())
                .symbolTable(currentSymbolTable)
                .modifierList(fieldModifierList)
                .originalText(source.substring(startPos, endPos))
                .range(range)
                .build();

        currentSymbolTable.declare(node.getName().toString(), fieldSymbolInfo);

        System.err.println("Field: " + node.getName() + ", type = " + fieldTypeName + ", annotationTypeList = " + fieldTypeMirror.getAnnotationMirrors());
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
                    .anyMatch(ann -> ann.toString().equals("@Nullable"));

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
            System.err.println("[method] typeName = " + typeName);
            System.err.println("[method] typeNameWithNullability = " + typeNameWithNullability);
            typeNameList.add(typeNameWithNullability);
            simpleTypeNameList.add(simpleTypeName);

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
            methodName = "constructor";
            type = TypeInfo.Type.Constructor;
        }

        String symbolName = "^" + methodName + "$_" + String.join("_", typeNameList);
        String symbolNameWithSimpleTypeName = "^" + methodName + "$_" + String.join("_", simpleTypeNameList);
        System.err.println("symbolNameWithSimpleTypeName = " + symbolNameWithSimpleTypeName);

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
    public Void visitVariable(VariableTree node, Void unused) {
        List<jplus.base.Modifier> modifierList = new ArrayList<>();
        for (Modifier m : node.getModifiers().getFlags()) {
            modifierList.add(jplus.base.Modifier.valueOf(m.toString().toUpperCase()));
        }

        TypeInfo.Builder typeInfoBuilder = TypeInfo.builder();
        Tree typeTree = node.getType();
        boolean isNullable = false;

        // primitive 타입 처리
        if (typeTree instanceof PrimitiveTypeTree) {
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
                if (annotationMirror.getAnnotationType().toString().endsWith(".Nullable")) {
                    isNullable = true;
                    break;
                }
            }
            typeInfoBuilder.isNullable(isNullable);
        }

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
//        String methodInvocationCode = node.toString().replace("\\'", "'");
        //String methodInvocationCode = node.toString();
        //System.err.println("methodInvocationCode = " + methodInvocationCode);

        String instanceName = null;
        String methodName = "";

        ExpressionTree methodSelect = node.getMethodSelect();
        if (methodSelect instanceof MemberSelectTree) {
            MemberSelectTree mst = (MemberSelectTree) methodSelect;
            instanceName = mst.getExpression().toString();
            methodName = mst.getIdentifier().toString();
        } else if (methodSelect instanceof IdentifierTree) {
            methodName = ((IdentifierTree) methodSelect).getName().toString();
        }

        //System.err.println("instanceName = " + instanceName);
        //System.err.println("methodName = " + methodName);

        List<String> argStrings = new ArrayList<>();
        List<String> argTypes = new ArrayList<>();
        for (ExpressionTree arg : node.getArguments()) {
            argStrings.add(arg.toString());
            TypeMirror argType = trees.getTypeMirror(trees.getPath(ast, arg));
            argTypes.add(argType != null ? argType.toString() : "unknown");
        }

        //System.err.println("argTypes = " + argTypes);

        TypeMirror returnTypeMirror = trees.getTypeMirror(trees.getPath(ast, node));
        String returnType = returnTypeMirror != null ? returnTypeMirror.toString() : "unknown";

        //System.err.println("returnType = " + returnType);

        int startPos = (int)trees.getSourcePositions().getStartPosition(ast, node);
        int endPos = (int)trees.getSourcePositions().getEndPosition(ast, node);

        if ("super".equals(methodName) && endPos < 0) {
            endPos = startPos + 1;
        }

        TextChangeRange range = Utils.computeTextChangeRange(source, startPos, endPos - 1);
        String rangeText = source.substring(startPos, endPos);
        //System.err.println("[methodInvocation] rangeText = " + rangeText);

        var methodInvocationInfoBuilder = MethodInvocationInfo.builder();
        methodInvocationInfoBuilder.instanceName(instanceName)
                .methodName(methodName)
                .argTypes(argTypes)
                .args(argStrings)
                .returnType(returnType)
                .source(rangeText)
                .startPos(startPos)
                .endPos(endPos);

        javaMethodInvocationManager.addInvocationInfo(currentSymbolTable, rangeText, methodInvocationInfoBuilder.build());

        System.err.println("methodInvocationInfo = " + methodInvocationInfoBuilder.build());

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