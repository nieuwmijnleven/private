package jplus.util;

import jplus.base.TypeInfo;

import java.util.stream.Collectors;

public class CodeGenUtils {

    private CodeGenUtils() {}

    public static String getSimpleTypeName(TypeInfo typeInfo) {
        String base = CodeUtils.getSimpleName(typeInfo.getName());
        if (!typeInfo.isGeneric()) {
            return base;
        }

        String typeArgumentString = getGenericTypeArgumentString(typeInfo);
        return base + typeArgumentString;
    }

    public static String getGenericTypeArgumentString(TypeInfo typeInfo) {
        if (!typeInfo.isGeneric()) return "";

        if (typeInfo.getType() == TypeInfo.Type.Class) {
            return typeInfo.getTypeParameters().stream().collect(Collectors.joining(",", "<", ">"));
        }
        return typeInfo.getTypeArguments().stream().map(TypeInfo::getName).collect(Collectors.joining(",", "<", ">"));
    }

    public static String getSimpleTypeNameWithGenericWildcard(TypeInfo typeInfo) {
        String base = CodeUtils.getSimpleName(typeInfo.getName());
        if (!typeInfo.isGeneric()) {
            return base;
        }
        return base + "<?>";
    }
}
