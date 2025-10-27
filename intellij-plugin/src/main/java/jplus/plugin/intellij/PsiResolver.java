package jplus.plugin.intellij;

import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class PsiResolver {

    /**
     * 주어진 메모리 Java PSI에서 심볼 이름 기반으로 PsiElement 검색
     *
     * @param javaFile 메모리에서 생성된 PsiJavaFile
     * @param symbol   찾고자 하는 심볼 이름
     * @return 해당 이름의 PsiElement (클래스, 메서드, 필드, 로컬 변수, 파라미터, 타입 등), 없으면 null
     */
    @Nullable
    public static PsiElement resolveSymbol(PsiJavaFile javaFile, String symbol) {
        // 1. 클래스 검색
        Collection<PsiClass> classes = PsiTreeUtil.findChildrenOfType(javaFile, PsiClass.class);
        for (PsiClass cls : classes) {
            if (symbol.equals(cls.getName())) return cls;

            PsiElement member = resolveInsideClass(cls, symbol);
            if (member != null) return member;
        }

        // 2. 최상위 레벨 필드, 메서드, 타입 검색
        Collection<PsiField> fields = PsiTreeUtil.findChildrenOfType(javaFile, PsiField.class);
        for (PsiField field : fields) {
            if (symbol.equals(field.getName())) return field;
        }

        Collection<PsiMethod> methods = PsiTreeUtil.findChildrenOfType(javaFile, PsiMethod.class);
        for (PsiMethod method : methods) {
            if (symbol.equals(method.getName())) return method;
        }

        Collection<PsiTypeElement> typeElements = PsiTreeUtil.findChildrenOfType(javaFile, PsiTypeElement.class);
        for (PsiTypeElement typeElement : typeElements) {
            if (symbol.equals(typeElement.getText())) return typeElement;
        }

        return null;
    }

    /**
     * 클래스 내부에서 심볼 검색
     */
    @Nullable
    private static PsiElement resolveInsideClass(PsiClass psiClass, String symbol) {
        // 내부 클래스
        for (PsiClass innerClass : psiClass.getInnerClasses()) {
            if (symbol.equals(innerClass.getName())) return innerClass;

            PsiElement member = resolveInsideClass(innerClass, symbol);
            if (member != null) return member;
        }

        // 필드
        for (PsiField field : psiClass.getFields()) {
            if (symbol.equals(field.getName())) return field;

            // 타입 검색
            PsiTypeElement typeElement = field.getTypeElement();
            if (typeElement != null && symbol.equals(typeElement.getText())) return typeElement;
        }

        // 메서드
        for (PsiMethod method : psiClass.getMethods()) {
            if (symbol.equals(method.getName())) return method;

            // 반환 타입
            PsiTypeElement returnType = method.getReturnTypeElement();
            if (returnType != null && symbol.equals(returnType.getText())) return returnType;

            // 매개변수 검색
            for (PsiParameter param : method.getParameterList().getParameters()) {
                if (symbol.equals(param.getName())) return param;

                PsiTypeElement paramType = param.getTypeElement();
                if (paramType != null && symbol.equals(paramType.getText())) return paramType;
            }

            // 메서드 내부 로컬 변수 검색
            PsiCodeBlock body = method.getBody();
            if (body != null) {
                PsiElement localVar = findLocalVariable(body, symbol);
                if (localVar != null) return localVar;
            }
        }

        return null;
    }

    /**
     * 메서드 body 내에서 로컬 변수 검색
     */
    @Nullable
    private static PsiElement findLocalVariable(PsiElement element, String symbol) {
        Collection<PsiLocalVariable> locals = PsiTreeUtil.findChildrenOfType(element, PsiLocalVariable.class);
        for (PsiLocalVariable local : locals) {
            if (symbol.equals(local.getName())) return local;

            PsiTypeElement typeElement = local.getTypeElement();
            if (typeElement != null && symbol.equals(typeElement.getText())) return typeElement;
        }
        return null;
    }
}
