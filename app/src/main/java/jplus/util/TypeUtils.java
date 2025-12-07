package jplus.util;

import jplus.base.TypeInfo;

import javax.lang.model.type.*;
import javax.lang.model.element.*;
import java.util.List;
import java.util.stream.Collectors;

public class TypeUtils {

    public static TypeInfo fromTypeMirror(TypeMirror typeMirror) {
        switch (typeMirror.getKind()) {
            case DECLARED:
                DeclaredType declaredType = (DeclaredType) typeMirror;
                String className = ((TypeElement) declaredType.asElement())
                        .getQualifiedName().toString();

                List<TypeInfo> typeArgs = declaredType.getTypeArguments()
                        .stream()
                        .map(TypeUtils::fromTypeMirror)
                        .collect(Collectors.toList());

                return new TypeInfo(className, false, TypeInfo.Type.Class, typeArgs, List.of());

            case TYPEVAR:
                TypeVariable typeVar = (TypeVariable) typeMirror;
                return new TypeInfo(typeVar.asElement().getSimpleName().toString(),
                        false, TypeInfo.Type.TypeParameter);

            case ARRAY:
                ArrayType arrayType = (ArrayType) typeMirror;
                TypeInfo component = fromTypeMirror(arrayType.getComponentType());
                return new TypeInfo(component.getName() + "[]", false, TypeInfo.Type.Array);

            case BOOLEAN: case INT: case LONG: case FLOAT: case DOUBLE: case CHAR: case BYTE: case SHORT:
                return new TypeInfo(typeMirror.toString(), false, TypeInfo.Type.Primitive);

            default:
                return new TypeInfo(typeMirror.toString(), false, TypeInfo.Type.Reference);
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
