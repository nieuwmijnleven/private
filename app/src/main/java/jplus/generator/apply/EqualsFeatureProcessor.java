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

package jplus.generator.apply;

import jplus.base.TypeInfo;
import jplus.util.CodeGenUtils;
import jplus.util.CodeUtils;
import jplus.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class EqualsFeatureProcessor implements ApplyFeatureProcessor {
    @Override
    public void process(ApplyFeatureProcessingContext context) {
        if (context.hasProcessed("equals")) return;

        String methodSymbol = "^equals$_Object";
        if (context.getClassSymbolTable().contains(methodSymbol, TypeInfo.Type.Method)) {
            return;
        }

        List<String> primitiveTypeFieldList = context.getPrimitiveFields();
        List<String> referenceTypeFieldList = context.getReferenceFields();
        if (primitiveTypeFieldList.isEmpty() && referenceTypeFieldList.isEmpty()) {
            context.appendMethodPartText("true;\n");
        } else {
            String className = CodeGenUtils.getSimpleTypeNameWithGenericWildcard(context.getTargetClassTypeInfo());
            String instanceName = Utils.convertToCamelCase(context.getTargetClass());

            List<String> equalityChecks = new ArrayList<>();
            for (String primitiveTypeField : primitiveTypeFieldList) {
                equalityChecks.add(primitiveTypeField + " == " + instanceName + "." + primitiveTypeField);
            }

            for (String referenceTypeField : referenceTypeFieldList) {
                equalityChecks.add("java.util.Objects.equals(" + referenceTypeField + ", " + instanceName + "." + referenceTypeField + ")");
            }

            StringBuilder generatedText = new StringBuilder();
            String indentation = context.getIndentation();
            String doubleIndentation = indentation.repeat(2);

            generatedText.append("\n@Override\n");
            generatedText.append("public boolean equals(").append("Object o").append(") {\n");
            generatedText.append(indentation).append("if (this == o) return true;\n");
            generatedText.append(indentation).append("if (o == null || getClass() != o.getClass()) return false;\n");
            generatedText.append(indentation).append(className).append(" ").append(instanceName).append(" = ").append("(").append(className).append(") o;\n");

            generatedText.append(indentation).append("return ");
            generatedText.append(String.join("\n" + doubleIndentation + "&& ", equalityChecks));
            generatedText.append(";\n");
            generatedText.append("}\n");

            context.appendMethodPartText(generatedText.toString());
        }

//        ApplyFeatureProcessor.super.process(context);
        context.addProcessedAction("equals");
    }
}
