package jplus.analyzer;

import com.sun.source.tree.ExpressionTree;
import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.base.TypeInfo;
import jplus.util.TypeUtils;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// JavaSymbolResolver
public class JavaSymbolResolver {

    private final SymbolTable globalSymbolTable;
    private final Elements elements;
    private final Types types;
    private final Map<String, SymbolInfo> cache = new HashMap<>();

    public JavaSymbolResolver(SymbolTable globalSymbolTable, Elements elements, Types types) {
        this.globalSymbolTable = globalSymbolTable;
        this.elements = elements;
        this.types = types;
    }

    /**
     * 클래스 이름으로 TypeInfo 반환
     * 내부 캐시 사용, 없으면 Elements에서 조회
     */
    public SymbolInfo resolveClass(String qualifiedName) {
        if (cache.containsKey(qualifiedName)) {
            return cache.get(qualifiedName);
        }

        if (globalSymbolTable.contains(qualifiedName, TypeInfo.Type.Class)) {
            return globalSymbolTable.resolveInCurrent(qualifiedName);
        }

        TypeElement clazz = elements.getTypeElement(qualifiedName);
        if (clazz == null) {
            return null; // classpath에 존재하지 않음
        }


        // Class Declaration
        // 1. 타입 파라미터 추출 (T, K, V 등)
        List<String> typeParams = clazz.getTypeParameters().stream()
                .map(TypeParameterElement::getSimpleName)
                .map(Object::toString)
                .toList();

        // 2. 타입 인자 (Type Arguments) - 선언 시에는 비어있음
        List<TypeInfo> typeArgs = List.of(); // 필요 시 외부 입력값이나 generic으로 채움

        // TypeInfo 생성
        TypeInfo classTypeInfo = TypeInfo.builder()
                .name(qualifiedName)
                .isNullable(false)
                .isGeneric(!typeParams.isEmpty())
                .type(TypeInfo.Type.Class)
                .typeParameters(typeParams)
                .typeArguments(typeArgs)
                .build();

        // typeInfo 사용 가능
        System.err.println(classTypeInfo);

        SymbolTable topLevelSymbolTable = new SymbolTable(globalSymbolTable);
        SymbolInfo classSymbolInfo = SymbolInfo.builder()
                .symbol(qualifiedName)
                .typeInfo(classTypeInfo)
                .symbolTable(topLevelSymbolTable)
                .build();
        globalSymbolTable.declare(qualifiedName, classSymbolInfo);

        topLevelSymbolTable.declare("^PackageName$", classSymbolInfo.toBuilder().symbol(getPackageName(clazz)).build());
        topLevelSymbolTable.declare("^TopLevelClass$", classSymbolInfo);
        topLevelSymbolTable.declare(qualifiedName, classSymbolInfo);

        cache.put(qualifiedName, classSymbolInfo);

        SymbolTable classSymbolTable = topLevelSymbolTable.getEnclosingSymbolTable(qualifiedName);

        // Field Declaration
        for (Element element : clazz.getEnclosedElements()) {
            if (element.getKind() == ElementKind.FIELD) {
                VariableElement field = (VariableElement) element;

                TypeInfo fieldTypeInfo =
                        TypeUtils.fromTypeMirror(field.asType(), field);

                SymbolInfo fieldSymbolInfo = SymbolInfo.builder()
                        .symbol(field.getSimpleName().toString())
                        .typeInfo(fieldTypeInfo)
                        .symbolTable(classSymbolTable)
                        .build();

                classSymbolTable.declare(fieldSymbolInfo.getSymbol(), fieldSymbolInfo);
            }
        }

        // Method Declaration
        SymbolTable methodSymbolTable = new SymbolTable(classSymbolTable);

        for (Element element : clazz.getEnclosedElements()) {
            if (element.getKind() == ElementKind.CONSTRUCTOR || element.getKind() == ElementKind.METHOD) {

                ExecutableElement methodElement = (ExecutableElement) element;
                List<String> typeNameList = new ArrayList<>();

                for (VariableElement parameter : methodElement.getParameters()) {
                    TypeMirror typeMirror = parameter.asType();
                    TypeInfo typeInfo = TypeUtils.fromTypeMirror(typeMirror, parameter);

                    String typeNameWithNullability = typeInfo.getName() + (typeInfo.isNullable() ? "?" : "");
                    typeNameList.add(typeNameWithNullability);

                    // 심볼 정보 생성
                    SymbolInfo symbolInfo = SymbolInfo.builder()
                            .symbol(parameter.getSimpleName().toString())
                            .typeInfo(typeInfo)
                            .symbolTable(methodSymbolTable)
                            .build();

                    // 파라미터 심볼 등록
                    methodSymbolTable.declare(symbolInfo.getSymbol(), symbolInfo);
                }
                // ---------- method/constructor symbol name 구성 ----------
                //String methodName = methodElement.toString();
                String methodName = methodElement.getSimpleName().toString();
                TypeInfo.Type type = TypeInfo.Type.Method;
                if (methodName.equals("<init>")) {
                    methodName = "constructor";
                    type = TypeInfo.Type.Constructor;
                }

                String symbolName = "^" + methodName + "$_" + String.join("_", typeNameList);
                System.err.println("[method] symbolName = " + symbolName);

                TypeInfo typeInfo = new TypeInfo(symbolName, false, type);
                SymbolInfo symbolInfo = SymbolInfo.builder()
                        .symbol(symbolName)
                        .typeInfo(typeInfo)
                        .symbolTable(classSymbolTable)
                        .build();

                classSymbolTable.declare(symbolName, symbolInfo);
                classSymbolTable.addEnclosingSymbolTable(symbolName, methodSymbolTable);
            }
        }

        return classSymbolInfo;
    }

    public SymbolInfo resolveFieldAccess(SymbolInfo ownerClassSymbol,
                                         VariableElement fieldElement) {

        // 1. 필드 타입
        TypeMirror fieldType = fieldElement.asType();
        TypeInfo fieldTypeInfo = TypeUtils.fromTypeMirror(fieldType, fieldElement);

        // 2. 필드 심볼
        SymbolInfo fieldSymbol = SymbolInfo.builder()
                .symbol(fieldElement.getSimpleName().toString())
                .typeInfo(fieldTypeInfo)
                .symbolTable(ownerClassSymbol.getSymbolTable())
                .build();

        ownerClassSymbol.getSymbolTable()
                .declare(fieldSymbol.getSymbol(), fieldSymbol);

        // 3. 필드 타입의 클래스 심볼 생성
        String fieldTypeFQN = fieldTypeInfo.getName();
        SymbolInfo fieldTypeClassSymbol = resolveClass(fieldTypeFQN);

        return fieldTypeClassSymbol;
    }


    /*public SymbolInfo resolveExpressionChain(ExpressionTree expr) {
        Element element = Trees.getElement(getCurrentPath());

        if (element instanceof TypeElement type) {
            return resolveClass(type.getQualifiedName().toString());
        }

        if (element instanceof VariableElement field) {
            TypeElement owner = (TypeElement) field.getEnclosingElement();
            SymbolInfo ownerSymbol =
                    resolveClass(owner.getQualifiedName().toString());

            return resolveFieldAccess(ownerSymbol, field);
        }

        if (element instanceof ExecutableElement method) {
            TypeElement owner = (TypeElement) method.getEnclosingElement();
            return resolveClass(owner.getQualifiedName().toString());
        }

        return null;
    }*/


    private String getPackageName(TypeElement clazz) {
        PackageElement pkg = elements.getPackageOf(clazz);
        return pkg.isUnnamed() ? "" : pkg.getQualifiedName().toString();
    }
}
