package jplus.util;

import jplus.base.TypeInfo;

import javax.lang.model.type.*;
import javax.lang.model.element.*;
import java.util.List;
import java.util.stream.Collectors;

public class TypeUtils {

    public static TypeInfo fromTypeMirror(TypeMirror typeMirror, Element originalElement) {
        switch (typeMirror.getKind()) {
            case DECLARED:
                DeclaredType declaredType = (DeclaredType) typeMirror;
                Element declaration = declaredType.asElement();
                // originElement가 클래스/인터페이스 자체인지 확인
                boolean isClassDecl = originalElement.getKind().isClass() || originalElement.getKind().isInterface();

                if (isClassDecl) {
                    // 클래스/인터페이스 선언 그 자체를 나타냄
                    String className = ((TypeElement) declaredType.asElement()).getQualifiedName().toString();



                    // ✔ 선언부의 타입 파라미터 추출 (T, K, V 등)
                    List<String> typeParams = ((TypeElement) declaration)
                            .getTypeParameters()
                            .stream()
                            .map(TypeParameterElement::getSimpleName)
                            .map(Object::toString)
                            .toList();

                    boolean isGeneric = !((TypeElement)declaration).getTypeParameters().isEmpty();

                    return new TypeInfo(className, false, isGeneric, TypeInfo.Type.Class, typeParams, List.of());
                } else {
                    String referenceTypeName = ((TypeElement) declaredType.asElement()).getQualifiedName().toString();

                    boolean isNullable = typeMirror.getAnnotationMirrors().stream()
                            .map(annotationMirror -> annotationMirror.getAnnotationType().toString())
                            .anyMatch(annName -> annName.endsWith(".Nullable") || annName.equals("org.jspecify.annotations.Nullable"));

                    List<TypeInfo> typeArgs = declaredType.getTypeArguments()
                            .stream()
                            .map(tm -> TypeUtils.fromTypeMirror(tm, originalElement))
                            .collect(Collectors.toList());

                    boolean isGeneric = !((TypeElement)declaration).getTypeParameters().isEmpty();

                    return new TypeInfo(referenceTypeName, isNullable, isGeneric, TypeInfo.Type.Reference, List.of(), typeArgs);
                }

            case TYPEVAR:
                TypeVariable typeVar = (TypeVariable) typeMirror;
                return new TypeInfo(typeVar.asElement().getSimpleName().toString(),
                        false, TypeInfo.Type.TypeParameter);

            case ARRAY:
                ArrayType arrayType = (ArrayType) typeMirror;
                TypeInfo component = fromTypeMirror(arrayType.getComponentType(), originalElement);
                return new TypeInfo(component.getName() + "[]", false, TypeInfo.Type.Array);

            case BOOLEAN: case INT: case LONG: case FLOAT: case DOUBLE: case CHAR: case BYTE: case SHORT:
                return new TypeInfo(typeMirror.toString(), false, TypeInfo.Type.Primitive);

            default:
                return new TypeInfo((typeMirror instanceof DeclaredType dt) ? dt.asElement().toString() : typeMirror.toString(), false, TypeInfo.Type.Unknown);
        }
    }


    // TypeMirror에서 타입명을 얻는 일반적인 방법
    public static String getTypeName(TypeMirror type) {
        switch (type.getKind()) {
            case DECLARED:
                DeclaredType declared = (DeclaredType) type;
                String rawType = ((TypeElement) declared.asElement()).getQualifiedName().toString();
                
                List<? extends TypeMirror> typeArgs = declared.getTypeArguments();
                if (!typeArgs.isEmpty()) {
                    String args = typeArgs.stream()
                                          .map(TypeUtils::getTypeName)
                                          .collect(Collectors.joining(", "));
                    return rawType + "<" + args + ">";
                } else {
                    return rawType;
                }

            case TYPEVAR:
                TypeVariable typeVar = (TypeVariable) type;
                return typeVar.asElement().getSimpleName().toString();

            case ARRAY:
                ArrayType arrayType = (ArrayType) type;
                return getTypeName(arrayType.getComponentType()) + "[]";

            default:
                return type.toString(); // primitive, wildcard 등
        }
    }

    // TypeMirror에서 제너릭 타입 파라미터만 추출
    public static List<String> getTypeParameters(TypeMirror type) {
        if (type.getKind() == TypeKind.DECLARED) {
            DeclaredType declared = (DeclaredType) type;
            return declared.getTypeArguments().stream()
                           .map(TypeUtils::getTypeName)
                           .collect(Collectors.toList());
        }
        return List.of();
    }
}
