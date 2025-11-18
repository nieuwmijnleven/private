package jplus.analyzer;

import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.base.TypeInfo;
import jplus.util.Utils;
import org.objectweb.asm.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
import java.util.zip.*;

public class BytecodeSymbolAnalyzer {

    // 인식할 Nullable 어노테이션 목록
    private static final Set<String> NULLABLE_ANNOTATIONS = Set.of(
            "Ljavax/annotation/Nullable;",
            "Ljakarta/annotation/Nullable;",
            "Lorg/jetbrains/annotations/Nullable;",
            "Lorg/eclipse/jdt/annotation/Nullable;"
    );

    private final SymbolTable globalSymbolTable;
    private final SymbolTable symbolTable;
    private SymbolTable currentSymbolTable;

    public BytecodeSymbolAnalyzer(SymbolTable globalSymbolTable) {
        this.globalSymbolTable = globalSymbolTable;
        this.symbolTable = new SymbolTable(globalSymbolTable);
        this.currentSymbolTable = this.symbolTable;
    }

    public SymbolTable analyzeSymbol(Path src, String fqn) throws IOException {
        byte[] payload = loadFromJMod(src, fqn);
        analyzeClass(payload);
        return symbolTable;
    }

//    public static void main(String[] args) throws Exception {
//
//        if (args.length == 0) {
//            System.out.println("Usage: java LibraryNullabilityScanner <path>");
//            return;
//        }
//
//        Path input = Paths.get(args[0]);
//        List<byte[]> classFiles = loadAllClassFiles(input);
//
//        for (byte[] bytes : classFiles) {
//            analyzeClass(bytes);
//        }
//    }

    // ---------------------------------------------------------
    // 1. path 가 directory / jar / jmod 인지 자동 판단하여 처리
    // ---------------------------------------------------------
    private static List<byte[]> loadAllClassFiles(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException(path.toString());
        }

        if (Files.isDirectory(path)) {
            return loadFromDirectory(path);
        }

        String lower = path.toString().toLowerCase();
        if (lower.endsWith(".jar")) {
            return loadFromJar(path);
        }
        if (lower.endsWith(".jmod")) {
            return loadFromJMod(path);
        }

        throw new IllegalArgumentException("Unsupported file type: " + path);
    }

    // ---------------------------------------------------------
    // Directory 안의 .class 파일 읽기
    // ---------------------------------------------------------
    private static List<byte[]> loadFromDirectory(Path dir) throws IOException {
        List<byte[]> list = new ArrayList<>();

        Files.walk(dir)
                .filter(p -> p.toString().endsWith(".class"))
                .forEach(p -> {
                    try {
                        list.add(Files.readAllBytes(p));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return list;
    }

    // ---------------------------------------------------------
    // JAR 내부 .class 파일 읽기
    // ---------------------------------------------------------
    private static List<byte[]> loadFromJar(Path jarPath) throws IOException {
        List<byte[]> list = new ArrayList<>();

        try (JarFile jar = new JarFile(jarPath.toFile())) {
            Enumeration<JarEntry> entries = jar.entries();

            while (entries.hasMoreElements()) {
                JarEntry e = entries.nextElement();
                if (e.getName().endsWith(".class")) {
                    list.add(jar.getInputStream(e).readAllBytes());
                }
            }
        }
        return list;
    }

    // ---------------------------------------------------------
    // JMOD 내부 .class 파일 읽기
    //
    // JMOD 구조 예:
    //   classes/java/lang/String.class
    // ---------------------------------------------------------
    private static List<byte[]> loadFromJMod(Path jmodPath) throws IOException {
        System.out.println("---- loadFromJMod ----");
        List<byte[]> list = new ArrayList<>();

        try (ZipFile zip = new ZipFile(jmodPath.toFile())) {
            Enumeration<? extends ZipEntry> entries = zip.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                // .jmod 내부는 classes/~~ 경로에 class 파일 존재
                if (entry.getName().startsWith("classes/") &&
                        entry.getName().endsWith(".class")) {
                    System.out.println("entry = " + entry.getName());
                    list.add(zip.getInputStream(entry).readAllBytes());
                }
            }
        }

        return list;
    }

    private byte[] loadFromJMod(Path jmodPath, String fqn) throws IOException {
        System.out.println("---- loadFromJMod ----");
        System.out.println("fqn = " + fqn);

        List<byte[]> list = new ArrayList<>();
        try (ZipFile zip = new ZipFile(jmodPath.toFile())) {
            String entryName = "classes/" + fqn.replace(".", "/") + ".class";
            System.out.println("entryName = " + entryName);
            
            ZipEntry entry = zip.getEntry(entryName);
            if (entry != null) {
                return zip.getInputStream(entry).readAllBytes();
            }
        }

        return null;
    }

    // ---------------------------------------------------------
    // 2. ASM 분석
    // ---------------------------------------------------------
    private void analyzeClass(byte[] classBytes) {
        Objects.requireNonNull(classBytes);

        ClassReader reader = new ClassReader(classBytes);
        reader.accept(new ClassVisitor(Opcodes.ASM9) {

            String className;

            @Override
            public void visit(int ver, int access, String name, String sig, String superName, String[] intf) {
                className = name.replace('/', '.');
//                System.err.println("className = " + className);
                TypeInfo typeInfo = new TypeInfo(className, false, TypeInfo.Type.Class);
                SymbolInfo symbolInfo = SymbolInfo.builder()
                        .symbol(className)
                        .typeInfo(typeInfo)
                        .symbolTable(symbolTable)
                        .build();
                globalSymbolTable.declare(className, symbolInfo);
                symbolTable.declare("^TopLevelClass$", symbolInfo);
                globalSymbolTable.addEnclosingSymbolTable(className, symbolTable);
                currentSymbolTable = symbolTable.addEnclosingSymbolTable(className, new SymbolTable(symbolTable));

                super.visit(ver, access, name, sig, superName, intf);
            }

            @Override
            public FieldVisitor visitField(int access, String name, String desc,
                                           String signature, Object value) {

                final SymbolTable classSymbolTable = currentSymbolTable;
                String typeName = desc;
                if (desc.length() > 1) {
                    typeName = desc.substring(1, desc.length() - 1).replace("/", ".");
                }
                TypeInfo.Builder typeInfoBuilder = TypeInfo.builder();
                typeInfoBuilder.name(typeName);
                if (desc.startsWith("L") || desc.startsWith("[")) {
                    typeInfoBuilder.type(TypeInfo.Type.Reference);
                } else {
                    typeInfoBuilder.type(TypeInfo.Type.Primitive);
                }
                typeInfoBuilder.isNullable(false);

                return new FieldVisitor(Opcodes.ASM9) {

                    @Override
                    public AnnotationVisitor visitAnnotation(String ad, boolean v) {

                        if (NULLABLE_ANNOTATIONS.contains(ad)) {
//                            System.err.println("[Nullable Field] " + className + "." + name + " : " + desc);
                            typeInfoBuilder.isNullable(true);
                        }

                        return super.visitAnnotation(ad, v);
                    }

                    @Override
                    public void visitEnd() {
                        SymbolInfo symbolInfo = SymbolInfo.builder().symbol(name).typeInfo(typeInfoBuilder.build()).symbolTable(classSymbolTable).build();
                        classSymbolTable.declare(name, symbolInfo);
                        super.visitEnd();
                    }
                };
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String desc,
                                             String sig, String[] ex) {

                final SymbolTable classSymbolTable = currentSymbolTable;
                System.err.println("[visitMethod] name = " + name);
                System.err.println("[visitMethod] desc = " + desc);




                /*String typeName = desc;
                if (desc.length() > 1) {
                    typeName = desc.substring(1, desc.length() - 1).replace("/", ".");
                }
                TypeInfo.Builder typeInfoBuilder = TypeInfo.builder();
                typeInfoBuilder.name(typeName);
                if (desc.startsWith("L") || desc.startsWith("[")) {
                    typeInfoBuilder.type(TypeInfo.Type.Reference);
                } else {
                    typeInfoBuilder.type(TypeInfo.Type.Primitive);
                }
                typeInfoBuilder.isNullable(false);*/


                return new MethodVisitor(Opcodes.ASM9) {

                    boolean returnNullable = false;
                    Map<Integer, Boolean> paramNullable = new HashMap<>();

                    @Override
                    public AnnotationVisitor visitAnnotation(String ad, boolean v) {
                        if (NULLABLE_ANNOTATIONS.contains(ad)) {
                            returnNullable = true;
                        }
                        return super.visitAnnotation(ad, v);
                    }

                    @Override
                    public AnnotationVisitor visitParameterAnnotation(int p, String ad, boolean v) {
                        if (NULLABLE_ANNOTATIONS.contains(ad)) {
                            paramNullable.put(p, true);
                        }
                        return super.visitParameterAnnotation(p, ad, v);
                    }

                    @Override
                    public void visitEnd() {
                        if (returnNullable) {
                            System.err.println("[Nullable Return] " + className + "." + name + " " + desc);
                        }
                        paramNullable.forEach((i, a) -> {
                            System.err.println("[Nullable Param] " + className + "." + name
                                    + " param#" + i + " " + desc);
                        });

                        int paramStartIndex = 1;
                        int paramEndIndex = desc.indexOf(")");

                        String paramTypesPart = desc.substring(paramStartIndex, paramEndIndex);
                        String[] paramTypes = paramTypesPart.split(";");
                        for (int i = 0; i < paramTypes.length; i++) {
                            if (paramTypes[i].startsWith("L")) {
                                paramTypes[i] = paramTypes[i].substring(1).replace("/", ".");
                            } else if (paramTypes[i].startsWith("[L")) {
                                paramTypes[i] = paramTypes[i].substring(2).replace("/", ".") + "[]";
                            } else if (Utils.isJvmPrimtive(paramTypes[i])) {
                                paramTypes[i] = Utils.jvmPrimtiveToJavaPrimitive(paramTypes[i]);
                            } else if (paramTypes[i].startsWith("[") && Utils.isJvmPrimtive(paramTypes[i].substring(1))) {
                                paramTypes[i] = Utils.jvmPrimtiveToJavaPrimitive(paramTypes[i].substring(1)) + "[]";
                            }

                            paramTypes[i] += paramNullable.getOrDefault(i, false) ? "?" : "";
                        }

                        Arrays.asList(paramTypes).forEach(paramType -> {
                            System.err.println("paramType = " + paramType);
                        });


                        String methodSymbol = "^" + name + "$_" + String.join("_", paramTypes);
                        System.err.println("[visitMethod] = " + methodSymbol);

                        TypeInfo typeInfo = new TypeInfo(methodSymbol, false, TypeInfo.Type.Method);
                        SymbolInfo.Builder symbolInfoBuilder = SymbolInfo.builder();
                        SymbolInfo methodSymbolInfo = symbolInfoBuilder.symbol(methodSymbol)
                                                                        .typeInfo(typeInfo)
                                                                        .symbolTable(classSymbolTable)
                                                                        .build();

                        classSymbolTable.declare(methodSymbol, methodSymbolInfo);
                    }
                };
            }

        }, 0);
    }
}
