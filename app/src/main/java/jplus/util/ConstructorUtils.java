package jplus.util;

import com.sun.source.tree.MethodTree;
import com.sun.source.util.Trees;
import com.sun.source.util.TreePath;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.VariableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;

public class ConstructorUtils {

    /**
     * MethodTree가 실제 코드에 존재하는 생성자인지 확인
     * 
     * @param methodTree 확인할 MethodTree
     * @param trees      Trees 인스턴스
     * @param compilationUnit 컴파일 단위 루트
     * @return true면 명시적 생성자, false면 자동 default constructor 후보 또는 생성자 아님
     */
    public static boolean isExplicitConstructor(MethodTree methodTree, Trees trees, TreePath path) {
        // 생성자 이름 검사
        if (!methodTree.getName().contentEquals("<init>")) {
            return false;
        }

        Element element = trees.getElement(path);
        if (!(element instanceof ExecutableElement ee)) {
            return false;
        }

        // 생성자인지 검사
        if (ee.getKind() != ElementKind.CONSTRUCTOR) {
            return false;
        }

        // ⭐ 핵심: 소스에 명시적으로 작성된 생성자인가?
        return !ee.isDefault();
    }

    public static List<String> getCandidates(List<String> paramTypes) {
        List<String> candidates = new ArrayList<>();
        if (!paramTypes.isEmpty()) {
            generateCandidates(paramTypes, 0, "^constructor$", candidates);
        }
        return candidates;
    }

    private static void generateCandidates(List<String> paramTypes, int index, String generated, List<String> candidates) {
        if (paramTypes.size() == index) {
            candidates.add(generated);
            return;
        }

        String paramType = paramTypes.get(index);
        generateCandidates(paramTypes, index + 1, generated + "_" + paramType, candidates);
        generateCandidates(paramTypes, index + 1, generated + "_" + paramType + "?", candidates);
    }
}
