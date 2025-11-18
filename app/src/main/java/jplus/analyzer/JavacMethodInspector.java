package jplus.analyzer;

import javax.tools.*;
import java.net.URI;
import java.util.*;
import com.sun.source.tree.*;
import com.sun.source.util.*;
import javax.lang.model.type.TypeMirror;

public class JavacMethodInspector {

    private String sourceCode;
    private JavacTask task;
    private Iterable<? extends CompilationUnitTree> asts;
    private Trees trees;
    private Map<String, MethodInvocationInfo> methodInvocationInfoMap;

    public JavacMethodInspector(String sourceCode) {
        this.sourceCode = sourceCode;
        this.methodInvocationInfoMap = new HashMap<>();
    }

    /** AST 생성 및 타입 분석 수행 */
    public void analyze() throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        JavaFileObject file = new SimpleJavaFileObject(URI.create("string:///Source.java"),
                JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return sourceCode;
            }
        };

        task = (JavacTask) compiler.getTask(
                null, fileManager, null,
                List.of("-XDcompilePolicy=simple"), null,
                List.of(file)
        );

        asts = task.parse();
        task.analyze();
        trees = Trees.instance(task);
    }

    /** MethodInvocation 정보를 담는 간단한 DTO */
    public static class MethodInvocationInfo {
        public final String instanceName;
        public final String methodName;
        public final List<String> args;
        public final List<String> argTypes;
        public final String returnType;
        public final long startPos;
        public final long endPos;

        public MethodInvocationInfo(String instanceName, String methodName, List<String> args, List<String> argTypes,
                                    String returnType, long startPos, long endPos) {
            this.instanceName = instanceName;
            this.methodName = methodName;
            this.args = args;
            this.argTypes = argTypes;
            this.returnType = returnType;
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public String toString() {
            return "MethodInvocationInfo{" +
                    "instanceName='" + instanceName + '\'' +
                    ", methodName='" + methodName + '\'' +
                    ", args=" + args +
                    ", argTypes=" + argTypes +
                    ", returnType='" + returnType + '\'' +
                    ", startPos=" + startPos +
                    ", endPos=" + endPos +
                    '}';
        }
    }

    public MethodInvocationInfo findMethodInvocation(String code) {
        return methodInvocationInfoMap.get(code);
    }

    /** MethodInvocationTree 노드 정보 수집 */
    public void collectMethodInvocationInfo() {
//        List<MethodInvocationInfo> results = new ArrayList<>();

        for (CompilationUnitTree ast : asts) {
            ast.accept(new TreeScanner<Void, Void>() {
                @Override
                public Void visitMethodInvocation(MethodInvocationTree node, Void p) {
                    String methodInvocationCode = node.toString().replace("\\'", "'");
                    System.err.println("methodInvocationCode: " + methodInvocationCode);

                    ExpressionTree methodSelect = node.getMethodSelect();

                    String methodName = "";
                    String instanceName = null;

                    if (methodSelect instanceof MemberSelectTree) {
                        MemberSelectTree mst = (MemberSelectTree) methodSelect;
                        methodName = mst.getIdentifier().toString();
                        instanceName = mst.getExpression().toString();
                    } else if (methodSelect instanceof IdentifierTree) {
                        methodName = ((IdentifierTree) methodSelect).getName().toString();
                    }

                    List<String> argStrings = new ArrayList<>();
                    List<String> argTypes = new ArrayList<>();
                    for (ExpressionTree arg : node.getArguments()) {
                        argStrings.add(arg.toString());
                        TypeMirror argType = trees.getTypeMirror(trees.getPath(ast, arg));
                        argTypes.add(argType != null ? argType.toString() : "unknown");
                    }

                    TypeMirror returnTypeMirror = trees.getTypeMirror(trees.getPath(ast, node));
                    String returnType = returnTypeMirror != null ? returnTypeMirror.toString() : "unknown";

                    long startPos = trees.getSourcePositions().getStartPosition(ast, node);
                    long endPos = trees.getSourcePositions().getEndPosition(ast, node);

                    methodInvocationInfoMap.put(methodInvocationCode, new MethodInvocationInfo(instanceName, methodName, argStrings, argTypes, returnType, startPos, endPos));

                    return super.visitMethodInvocation(node, p);
                }
            }, null);
        }
    }

    // -----------------------------
    // 테스트용 main
    // -----------------------------
    /*public static void main(String[] args) throws Exception {
        String code = """
                class Test {
                    void run() {
                        User user1 = new User();
                        System.out.println(user1.getDisplayName() + "'s city: " + user1.getStreet());
                    }
                }
                class User {
                    String getDisplayName() { return "Jeroen"; }
                    String getStreet() { return "NY"; }
                }
                """;

        JavacMethodInspector inspector = new JavacMethodInspector(code);
        inspector.analyze();
        List<MethodInvocationInfo> calls = inspector.findMethodInvocations();

        for (MethodInvocationInfo call : calls) {
            System.out.println(call);
        }
    }*/
}
