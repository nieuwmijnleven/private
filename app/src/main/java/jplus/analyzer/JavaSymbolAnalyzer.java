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

import com.sun.source.tree.*;
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
import java.util.EnumSet;
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

    private String packageName;

    private final JavaMethodInvocationManager javaMethodInvocationManager;
    private final JavaSymbolResolver resolver;

    final class ExpressionChainBuilder {

        private final Trees trees;
        private final Types types;
        private final JavaSymbolResolver resolver;
        private final CompilationUnitTree ast;
        private final String source;
        private final JavaMethodInvocationManager invocationManager;

        ExpressionChainBuilder(
                Trees trees,
                Types types,
                JavaSymbolResolver resolver,
                CompilationUnitTree ast,
                String source,
                JavaMethodInvocationManager invocationManager
        ) {
            this.trees = trees;
            this.types = types;
            this.resolver = resolver;
            this.ast = ast;
            this.source = source;
            this.invocationManager = invocationManager;
        }

        public ResolvedChain build(ExpressionTree expr) {
            ResolvedChain chain = new ResolvedChain();
            visit(expr, chain);
            return chain;
        }

        private void visit(ExpressionTree expr, ResolvedChain chain) {
            // ⚡ ParenthesizedTree 처리
            if (expr instanceof ParenthesizedTree p) {
                ResolvedChain child = new ResolvedChain();
                visit(p.getExpression(), child);
                chain.addStep(new ResolvedChain.Step(
                        ResolvedChain.Kind.CHAIN,
                        expr.toString(), // 괄호 포함 텍스트
                        child.last() != null ? child.last().typeInfo : null, // typeInfo는 마지막 step에서 결정
                        child.last() != null ? child.last().typeInfo.isNullable() : false,
                        false,
                        computeRange(expr),
                        null,
                        child
                ));

                return; // 괄호 안은 childChain에서 처리
            }

            // ⚡ + 연산(BinaryTree)
            if (expr instanceof BinaryTree bt && bt.getKind() == Tree.Kind.PLUS) {
                // 좌측 operand
                visit(bt.getLeftOperand(), chain);

                // 우측 operand
                visit(bt.getRightOperand(), chain);
                return;
            }

            while (true) {
                /*if (expr instanceof ParenthesizedTree p) {
                    expr = p.getExpression();
                } else*/
                if (expr instanceof TypeCastTree c) {
                    expr = c.getExpression();
                } else if (expr instanceof UnaryTree u) {
                    expr = u.getExpression();
                } else {
                    break;
                }
            }

            if (expr instanceof ArrayAccessTree aa) {
                // 1. 배열 변수를 먼저 방문
                visit(aa.getExpression(), chain);

                // 2. 마지막 Step이 배열이어야 elementType 사용
                ResolvedChain.Step lastStep = chain.last();
                if (lastStep != null && lastStep.typeInfo.getType() == TypeInfo.Type.Array) {
                    TypeInfo elementType = lastStep.typeInfo.getElementType();
                    String indexedSymbol = lastStep.symbol + "[" + aa.getIndex().toString() + "]";

                    chain.updateLastStep(step -> step.toBuilder()
                            .kind(ResolvedChain.Kind.ARRAY_ACCESS)
                            .symbol(indexedSymbol)
                            .typeInfo(elementType)
                            .nullable(elementType.isNullable())
                            .range(computeRange(aa))
                            .build());
                }
                return;
            }

            if (expr instanceof ConditionalExpressionTree ce) {
                visit(ce.getTrueExpression(), chain);
                visit(ce.getFalseExpression(), chain);
                return;
            }

            if (expr instanceof AssignmentTree at) {
                visit(at.getExpression(), chain);

                TreePath path = TreePath.getPath(ast, at);
                TypeMirror tm = trees.getTypeMirror(path);
                Element e = trees.getElement(path);
                TypeInfo ti = TypeUtils.fromTypeMirror(tm, e);

                chain.addStep(new ResolvedChain.Step(
                        ResolvedChain.Kind.EXPRESSION,
                        at.toString(),
                        ti,
                        ti.isNullable(),
                        false,
                        computeRange(at),
                        null,
                        null
                ));
                return;
            }

            if (expr instanceof LiteralTree lt) {
                Object value = lt.getValue();

                TreePath path = TreePath.getPath(ast, lt);
                Element element = trees.getElement(path);
                TypeMirror tm = trees.getTypeMirror(path);
                TypeInfo ti = TypeUtils.fromTypeMirror(tm, element);

                chain.addStep(new ResolvedChain.Step(
                        ResolvedChain.Kind.LITERAL,
                        String.valueOf(value),
                        ti,
                        false,
                        false,
                        computeRange(lt),
                        null,
                        null
                ));
                return;
            }

            if (expr instanceof IdentifierTree id) {
                handleIdentifier(id, chain);
            } else if (expr instanceof MemberSelectTree ms) {
                visit(ms.getExpression(), chain);
                handleMemberSelect(ms, chain);
            } else if (expr instanceof MethodInvocationTree mi) {
                handleMethodInvocation(mi, chain);
            } else if (expr instanceof NewClassTree nc) {
                handleNewClass(nc, chain);
            } else if (expr instanceof NewArrayTree na) {
                handleNewArray(na, chain);
            }
        }

        private void handleIdentifier(IdentifierTree id, ResolvedChain chain) {
            Element e = trees.getElement(TreePath.getPath(ast, id));
            if (e == null) return;

            TypeInfo ti = TypeUtils.fromTypeMirror(e.asType(), e);

            chain.addStep(new ResolvedChain.Step(
                    ResolvedChain.Kind.IDENTIFIER,
                    id.getName().toString(),
                    ti,
                    ti.isNullable(),
                    false,
                    computeRange(id),
                    null,
                    null
            ));
        }

        private void handleMemberSelect(MemberSelectTree ms, ResolvedChain chain) {
            Element e = trees.getElement(TreePath.getPath(ast, ms));
            if (e == null) return;

            TypeInfo ti = TypeUtils.fromTypeMirror(e.asType(), e);

            chain.addStep(new ResolvedChain.Step(
                    ResolvedChain.Kind.FIELD,
                    ms.getIdentifier().toString(),
                    ti,
                    ti.isNullable(),
                    false,
                    computeRange(ms),
                    null,
                    null
            ));
        }

        private void handleMethodInvocation(MethodInvocationTree mi, ResolvedChain chain) {
            ExpressionTree select = mi.getMethodSelect();

            if (select instanceof MemberSelectTree ms) {
                // obj.method()
                visit(ms.getExpression(), chain);
                addMethodStep(ms, mi, chain);
            } else if (select instanceof IdentifierTree id) {
                // method()  ← implicit this
                addImplicitThisMethodStep(id, mi, chain);
            } else {
                visit(select, chain);
            }
        }

        private void addImplicitThisMethodStep(
                IdentifierTree id,
                MethodInvocationTree mi,
                ResolvedChain chain
        ) {
            Element e = trees.getElement(TreePath.getPath(ast, mi));
            if (!(e instanceof ExecutableElement method)) return;

            TypeMirror ret = method.getReturnType();
            TypeInfo ti = TypeUtils.fromTypeMirror(ret, method);

            chain.addStep(new ResolvedChain.Step(
                    ResolvedChain.Kind.METHOD,
                    method.getSimpleName().toString(),
                    ti,
                    ti.isNullable(),
                    false,
                    computeRange(mi),
                    buildMethodInvocationInfo(
                            mi,
                            "this",   // receiver
                            method.getSimpleName().toString()
                    ),
                    null
            ));
        }

        private void handleNewClass(NewClassTree nc, ResolvedChain chain) {
            TreePath path = trees.getPath(ast, nc);
            Element element = trees.getElement(path);
            TypeMirror typeMirror = trees.getTypeMirror(path);
            TypeInfo ti = buildTypeInfo(typeMirror, element);

            String qualifiedName = getQualifiedName(nc);
            chain.addStep(new ResolvedChain.Step(
                    ResolvedChain.Kind.IDENTIFIER,
                    qualifiedName,
                    ti,
                    ti.isNullable(),
                    false,
                    computeRange(nc),
                    buildMethodInvocationInfo(nc, qualifiedName, qualifiedName),
                    null
            ));

            for (var arg: nc.getArguments()) {
                buildChain(arg);
            }
        }

        private void handleNewArray(NewArrayTree na, ResolvedChain chain) {
            // 배열 타입 정보 가져오기
            TreePath path = trees.getPath(ast, na);
            Element element = trees.getElement(path);
            TypeMirror typeMirror = trees.getTypeMirror(path);

            TypeInfo arrayType = buildTypeInfo(typeMirror, element);

            // 배열 literal 자체를 Step으로 추가
            chain.addStep(new ResolvedChain.Step(
                    ResolvedChain.Kind.IDENTIFIER,
                    na.toString(),    // "new T[]{ ... }"
                    arrayType,
                    arrayType.isNullable(),
                    false,
                    computeRange(na),
                    null,
                    null
            ));

            // 배열 요소 체인 생성 (NewClassTree 등)
            if (na.getInitializers() != null) {
                for (var initializer : na.getInitializers()) {
                    build(initializer); // visit()를 재귀적으로 호출해서 Step 생성
                }
            }
        }

        private void addMethodStep(
                MemberSelectTree ms,
                MethodInvocationTree mi,
                ResolvedChain chain
        ) {
            Element e = trees.getElement(TreePath.getPath(ast, mi));
            if (!(e instanceof ExecutableElement method)) return;

            TypeMirror ret = method.getReturnType();
            //if (ret.getKind() == TypeKind.VOID) return;

            TypeInfo ti = TypeUtils.fromTypeMirror(ret, method);

            chain.addStep(new ResolvedChain.Step(
                    ResolvedChain.Kind.METHOD,
                    method.getSimpleName().toString(),
                    ti,
                    ti.isNullable(),
                    false,
                    computeRange(mi),
                    buildMethodInvocationInfo(mi, resolveReceiverType(chain), method.getSimpleName().toString()),
                    null
            ));
        }

        private String resolveReceiverType(ResolvedChain chain) {
            return (chain.last() != null) ? chain.last().typeInfo.getName() : null;
        }
    }

    @Override
    public Void visitInstanceOf(InstanceOfTree node, Void unused) {
        buildChain(node.getExpression());
        return super.visitInstanceOf(node, unused);
    }

    @Override
    public Void visitExpressionStatement(ExpressionStatementTree node, Void unused) {
        buildChain(node.getExpression());
        return super.visitExpressionStatement(node, unused);
    }

    private void buildChain(ExpressionTree expr) {
        ExpressionChainBuilder chainBuilder = new ExpressionChainBuilder(
                trees, types, resolver, ast, source, javaMethodInvocationManager
        );
        ResolvedChain chain = chainBuilder.build(expr);
        currentSymbolTable.addResolvedChain(chain);
    }

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
        this.resolver = new JavaSymbolResolver(globalSymbolTable, elements, types);
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
        System.err.println("[JavaSymbolAnalyzer] currentSymbolTable = " + currentSymbolTable);
        System.err.println("[JavaSymbolAnalyzer] classSymbolInfo = " + classSymbolInfo);

        System.err.println("[JavaSymbolAnalyzer] node.getSimpleName().toString() = " + node.getSimpleName().toString());
        enterSymbolTable(node.getSimpleName().toString());

        currentSymbolTable.declare("^ClassDef$", classSymbolInfo);

        currentSymbolTable.declare(classSymbolInfo.getSymbol(), classSymbolInfo);
        currentSymbolTable.declare(classSymbolInfo.getTypeInfo().getName(), classSymbolInfo);

        currentSymbolTable
                .getEnclosingSymbolTable(SymbolTable.INSTANCE_NS)
                .declare("this", classSymbolInfo);

        Element element = trees.getElement(getCurrentPath());
        if (element instanceof TypeElement typeElement) {

            TypeMirror superClassMirror = typeElement.getSuperclass();
            if (superClassMirror.getKind() == TypeKind.DECLARED) {
                TypeElement superClassElement = (TypeElement) types.asElement(superClassMirror);
                TreePath superClassPath = trees.getPath(superClassElement);
                //ClassTree superClassTree = (ClassTree) superClassPath.getLeaf();
                //SymbolInfo superClassSymbol = resolver.resolveClass(superClassElement.getQualifiedName().toString());
                if (superClassPath != null) {
                    JavaSymbolAnalyzer superClassAnalyzer = new JavaSymbolAnalyzer(
                            source, ast, trees, globalSymbolTable, elements, types
                    );

                    superClassAnalyzer.scan(superClassPath.getLeaf(), null);
//                    var topLevelSymbolTable = superClassAnalyzer.getTopLevelSymbolTable();
//                    var classSymInfo = topLevelSymbolTable.resolveInCurrent("^TopLevelClass$");
//                    var classSymbolTable = topLevelSymbolTable.getEnclosingSymbolTable(classSymInfo.getSymbol());

                    currentSymbolTable.setSuperClassTable(superClassAnalyzer.getTopLevelSymbolTable().getEnclosingSymbolTables().get(0));
                }
            }

            for (TypeMirror interfaceMirror : typeElement.getInterfaces()) {

                if (interfaceMirror.getKind() == TypeKind.DECLARED) {
                    TypeElement interfaceElement =
                            (TypeElement) types.asElement(interfaceMirror);

                    TreePath interfacePath = trees.getPath(interfaceElement);
                    if (interfacePath != null) {
                        JavaSymbolAnalyzer interfaceAnalyzer = new JavaSymbolAnalyzer(
                                source, ast, trees, globalSymbolTable, elements, types
                        );

                        interfaceAnalyzer.scan(interfacePath.getLeaf(), null);

                        // 인터페이스는 여러 개일 수 있으므로 add 형태가 자연스러움
                        currentSymbolTable.addSuperInterfaceTable(
                                interfaceAnalyzer
                                        .getTopLevelSymbolTable()
                                        .getEnclosingSymbolTables()
                                        .get(0)
                        );
                    }
                }
            }
        }

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
                .modifierList(convertModifiers(node.getModifiers().getFlags()))
                .build();
    }

    private void declareClassSymbol(SymbolInfo classSymbolInfo) {
        String typeName = classSymbolInfo.getTypeInfo().getName();
        String simpleName = classSymbolInfo.getSymbol();
        currentSymbolTable.declare(simpleName, classSymbolInfo);
        //currentSymbolTable.declare("this", classSymbolInfo);
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
        System.err.println("[JavaSymbolAnalyzer] currentSymbolTable = " + currentSymbolTable);
        System.err.println("[JavaSymbolAnalyzer] currentSymbolTable.instanceTable = " + currentSymbolTable.getEnclosingSymbolTable(SymbolTable.INSTANCE_NS));

        ExpressionTree initializer = node.getInitializer();
        if (initializer != null) {
            buildChain(initializer);
        }
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
        System.err.println("[NewClass] symbolInfo = " + symbolInfo);

        MethodInvocationInfo info = buildMethodInvocationInfo(node, qualifiedName, qualifiedName);
        javaMethodInvocationManager.addInvocationInfo(currentSymbolTable, info);
        System.err.println("[NewClass] methodInvocationInfo = " + info);

        for (ExpressionTree arg : node.getArguments()) {
            buildChain(arg);
        }

        return super.visitNewClass(node, unused);
    }

    @Override
    public Void visitAssignment(AssignmentTree node, Void unused) {
        ExpressionTree lhs = node.getVariable();
        buildChain(lhs);

        ExpressionTree rhs = node.getExpression();
        buildChain(rhs);

        return super.visitAssignment(node, unused);
    }

    @Override
    public Void visitLambdaExpression(LambdaExpressionTree node, Void unused) {

        SymbolTable lambdaSymbolTable = new SymbolTable(currentSymbolTable);

        String lamdaSymbol = "^lambda$" + getSourceStartPosition(node);
        enterSymbolTable(lamdaSymbol, lambdaSymbolTable);
        try {
            for (VariableTree param : node.getParameters()) {
                TreePath paramPath = new TreePath(getCurrentPath(), param);
                Element e = trees.getElement(paramPath);
                TypeMirror tm = trees.getTypeMirror(paramPath);

                TypeInfo ti = TypeUtils.fromTypeMirror(tm, e);
                SymbolInfo si = SymbolInfo.builder()
                        .symbol(param.getName().toString())
                        .typeInfo(ti)
                        .symbolTable(lambdaSymbolTable)
                        .range(computeRange(param))
                        .originalText(param.toString())
                        .build();

                lambdaSymbolTable.declare(si.getSymbol(), si);
            }

            // 3. body 처리
            Tree body = node.getBody();
            if (body instanceof ExpressionTree et) {
                buildChain(et);
            } else if (body instanceof BlockTree bt) {
                scan(bt, unused);
            }

            return null;

        } finally {
            exitSymbolTable();
        }
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
                //CleanTypePrinter printer = new CleanTypePrinter();
                //List<String> paramTypes = ((ExecutableType) ee.asType()).getParameterTypes().stream().map(printer::print).toList();
                List<String> paramTypes = ((ExecutableType) ee.asType()).getParameterTypes().stream().map(t -> TypeUtils.fromTypeMirror(t, e)).map(TypeInfo::getFullname).toList();
                builder.paramTypes(paramTypes).returnType(((ExecutableType) ee.asType()).getReturnType().toString()).hasVarArgs(ee.isVarArgs());
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
            Element element = trees.getElement(TreePath.getPath(ast, param));
            //TypeMirror typeMirror = trees.getTypeMirror(TreePath.getPath(ast, param.getType()));
            TypeMirror typeMirror = element.asType();
            TypeInfo typeInfo = buildTypeInfo(typeMirror, element);
            System.err.println("[method] typeInfo = " + typeInfo);


            // 이름 리스트 생성 (FQN / simple)
            String typeNameWithNullability = typeInfo.getFullname() + (typeInfo.isNullable() ? "?" : "");
            System.err.println("[method] typeNameWithNullability = " + typeNameWithNullability);
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

        boolean isNullableReturn = node.getModifiers().getAnnotations().stream()
                .map(a -> a.getAnnotationType().toString())
                .anyMatch(name ->
                        name.endsWith(".Nullable")
                                || name.equals("org.jspecify.annotations.Nullable")
                );
        System.err.println("[processCallable] isNullableReturn = " + isNullableReturn);


        TypeInfo returnTypeInfo;
        if (type == TypeInfo.Type.Constructor) {
            // 생성자는 void와 같은 개념
            returnTypeInfo = TypeInfo.builder()
                    .name("void")
                    .isNullable(false)
                    .type(TypeInfo.Type.Void)
                    .build();
        } else {
            TypeMirror returnTypeMirror = trees.getTypeMirror(TreePath.getPath(ast, node.getReturnType()));
            Element returnTypeElement = trees.getElement(TreePath.getPath(ast, node.getReturnType()));
            returnTypeInfo = buildTypeInfo(returnTypeMirror, returnTypeElement);
        }

        TypeInfo typeInfo = new TypeInfo(symbolName, isNullableReturn, type, returnTypeInfo);
        SymbolInfo symbolInfo = new SymbolInfo(symbolName, typeInfo, computeRange(node), computeRangeText(node), convertModifiers(node.getModifiers().getFlags()), currentSymbolTable);

        // ---------- 현재 SymbolTable에 등록 ----------
        currentSymbolTable.declare(symbolName, symbolInfo);
        currentSymbolTable.declare(symbolNameWithSimpleTypeName, symbolInfo);
        System.err.println("[method] symbolInfo = "  + symbolInfo);
        System.err.println("[method] currentSymbolTable = "  + currentSymbolTable);

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

        final EnumSet localKindSet = EnumSet.of(ElementKind.LOCAL_VARIABLE, ElementKind.RESOURCE_VARIABLE);
        if (element == null || !localKindSet.contains(element.getKind())) {
            return super.visitVariable(node, p);
        }

        ExpressionTree initializer = node.getInitializer();
        if (initializer != null) {
            buildChain(initializer);
        }

        return handleLocalVariable(node, p);
    }

    @Override
    public Void visitReturn(ReturnTree node, Void unused) {
        buildChain(node.getExpression());
        return super.visitReturn(node, unused);
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

    @Override
    public Void visitTry(TryTree tryTree, Void unused) {
        /*for (Tree resource : tryTree.getResources()) {
            if (resource instanceof VariableTree varTree) {
                visitVariable(varTree, unused);
            }
        }*/

        return super.visitTry(tryTree, unused);
    }


    @Override
    public Void visitMethodInvocation(MethodInvocationTree node, Void unused) {

        System.err.println("[JavaSymbolAnalyzer] code = " + node.getMethodSelect().toString());

        // 🔥 여기서 체인 전체 해석
        //SymbolInfo ownerType = resolveExpressionChain(node.getMethodSelect());
        SymbolInfo ownerType = buildResolvedChain(
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

            for (ExpressionTree arg : node.getArguments()) {
                buildChain(arg);
            }

            javaMethodInvocationManager.addInvocationInfo(currentSymbolTable, info);
            System.err.println("[JavaSymbolAnalyzer] methodInvocationInfo = " + info);
        }

        return super.visitMethodInvocation(node, unused);
    }

    @Override
    public Void visitBinary(BinaryTree node, Void unused) {
        buildChain(node.getLeftOperand());
        buildChain(node.getRightOperand());

        buildChain((node));

        return super.visitBinary(node, unused);
    }

//    @Override
//    public Void visitIf(IfTree node, Void unused) {
//        ExpressionTree exprTree = node.getCondition();
//        return super.visitIf(node, unused);
//    }

    public SymbolInfo buildResolvedChain(ExpressionTree expr) {

        Element e = trees.getElement(TreePath.getPath(ast, expr));

        if (expr instanceof IdentifierTree) {
            // System
            if (e instanceof TypeElement type) {
                System.err.println("[resolveExpressionChain] identifer = " + type.getQualifiedName().toString());
                return resolver.resolveClass(type.getQualifiedName().toString());
            }

            if (e instanceof VariableElement var) {
                // 변수 / 필드 / 파라미터
                TypeMirror varType = var.asType();

                if (varType.getKind() == TypeKind.DECLARED) {
                    TypeElement varTypeElement = (TypeElement) types.asElement(varType);
                    SymbolInfo typeSymbol = resolver.resolveClass(varTypeElement.getQualifiedName().toString());
                    System.err.println("[resolveExpressionChain] identifier (variable) = " + var.getSimpleName() + ", type = " + varTypeElement.getQualifiedName());
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
            // 왼쪽 먼저 처리 (System / System.out)
            SymbolInfo ownerType = buildResolvedChain(mst.getExpression());

            // 현재 멤버의 Element
            if (e == null) return ownerType;

            // 필드 접근
            if (e instanceof VariableElement field) {
                TypeMirror fieldType = field.asType();
                TypeElement fieldTypeElement = (TypeElement) types.asElement(fieldType);

                System.err.println("[resolveExpressionChain] field = " + field);
                System.err.println("[resolveExpressionChain] fieldType = " + fieldTypeElement.getQualifiedName().toString());
                return resolver.resolveClass(fieldTypeElement.getQualifiedName().toString());
            }

            // 메서드 접근
            if (e instanceof ExecutableElement method) {

                TypeMirror returnType = method.getReturnType();
                System.err.println("[resolveExpressionChain] returnType = " + returnType.toString());

                // void 처리 (체인 종료)
                if (TypeKind.VOID == returnType.getKind()) {
                    System.err.println("[resolveExpressionChain] method returns void: " + method.getSimpleName());
                    return null;
                }

                // primitive 처리 (int, boolean ...)
                if (returnType.getKind().isPrimitive()) {
                    System.err.println("[resolveExpressionChain] method returns primitive: " + returnType);
                    return null;
                }

                // DeclaredType (일반 클래스, 인터페이스)
                if (returnType.getKind() == TypeKind.DECLARED) {
                    TypeElement returnTypeElement = (TypeElement) types.asElement(returnType);
                    return resolver.resolveClass(returnTypeElement.getQualifiedName().toString());
                }

                // TypeVariable (T, E, K, V 등)
                if (returnType.getKind() == TypeKind.TYPEVAR) {
                    TypeVariable tv = (TypeVariable) returnType;

                    // 상한(bound)으로 해석
                    TypeMirror upperBound = tv.getUpperBound();

                    // 보통 Object
                    if (upperBound.getKind() == TypeKind.DECLARED) {
                        TypeElement boundElement = (TypeElement) types.asElement(upperBound);
                        return resolver.resolveClass(boundElement.getQualifiedName().toString());
                    }

                    return null;
                }

                // ArrayType
                if (returnType.getKind() == TypeKind.ARRAY) {
                    ArrayType at = (ArrayType) returnType;
                    TypeMirror component = at.getComponentType();

                    if (component.getKind() == TypeKind.DECLARED) {
                        TypeElement componentElement = (TypeElement) types.asElement(component);
                        return resolver.resolveClass(componentElement.getQualifiedName().toString());
                    }
                }

                return null;
            }
        }

        return null;
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