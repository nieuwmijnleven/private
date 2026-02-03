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

package jplus.util;

import com.sun.source.tree.MethodTree;
import com.sun.source.util.Trees;
import com.sun.source.util.TreePath;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.ElementKind;
import java.util.ArrayList;
import java.util.List;

public class MethodUtils {

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

    public static List<String> getCandidates(String methodName, List<String> paramTypes) {
        List<String> candidates = new ArrayList<>();
        if (paramTypes.isEmpty()) {
            return List.of("^" + methodName + "$~");
        }

        generateCandidates(paramTypes, 0, "^"+ methodName + "$", candidates);
        return candidates;
    }

    private static void generateCandidates(List<String> paramTypes, int index, String generated, List<String> candidates) {
        if (paramTypes.size() == index) {
            candidates.add(generated);
            return;
        }

        String paramType = paramTypes.get(index);
        generateCandidates(paramTypes, index + 1, generated + "~" + paramType, candidates);
        generateCandidates(paramTypes, index + 1, generated + "~" + paramType + "?", candidates);
    }
}
