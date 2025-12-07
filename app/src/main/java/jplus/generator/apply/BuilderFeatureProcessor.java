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

import jplus.base.Modifier;
import jplus.base.SymbolInfo;
import jplus.base.TypeInfo;
import jplus.util.CodeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuilderFeatureProcessor implements ApplyFeatureProcessor {
    @Override
    public void process(ApplyFeatureProcessingContext context) {
        if (context.hasProcessed("builder")) return;

        if (context.getClassSymbolTable().contains("Builder", TypeInfo.Type.Class)) {
            return;
        }

        String methodName = "^builder$_";
        if (context.getClassSymbolTable().contains(methodName, TypeInfo.Type.Method)) {
            return;
        }

        ConstructorFeatureProcessor processor = new ConstructorFeatureProcessor();
        processor.processAllArgsConstructor(context);

        String indentation = context.getIndentation();
        String doubleIndentation = indentation.repeat(2);

        String builder = "\npublic static class Builder {";
        List<String> fieldDeclarations = new ArrayList<>();
        List<String> methodDeclarations = new ArrayList<>();
        List<String> nonStaticFieldList = new ArrayList<>();
        for (String fieldName : context.getFieldList()) {
            SymbolInfo symbolInfo = context.getClassSymbolTable().resolve(fieldName);
            TypeInfo typeInfo = symbolInfo.getTypeInfo();

            List<Modifier> excludedModifiers = List.of(Modifier.STATIC);
            if (symbolInfo.getModifierList().stream().anyMatch(modifier -> excludedModifiers.contains(modifier))) {
                continue;
            }

            String typeName = typeInfo.getName();
            String simpleTypeName = CodeUtils.getSimpleName(typeName);

            fieldDeclarations.add(indentation + "private " + simpleTypeName + " " + fieldName + ";");
            methodDeclarations.add(indentation + "public Builder " + fieldName + "(" + simpleTypeName + " " + fieldName + ") {\n" +
                    doubleIndentation + "this." + fieldName + " = " + fieldName + ";\n" +
                    doubleIndentation + "return this;\n" +
                    indentation + "}"
            );

            nonStaticFieldList.add(fieldName);
        }

        builder += fieldDeclarations.stream().collect(Collectors.joining("\n", "\n", "\n"));
        builder += methodDeclarations.stream().collect(Collectors.joining("\n\n", "\n", "\n"));
        builder += "\n" + indentation + "public " + context.getTargetClass() + " build() {\n";
        builder += doubleIndentation + "return new " + context.getTargetClass() + "(" + nonStaticFieldList.stream().collect(Collectors.joining(", ")) + ");\n";
        builder += indentation + "}\n";
        builder += "}\n";

        builder += "\npublic static Builder builder() {\n";
        builder += indentation + "return new Builder();\n";
        builder += "}\n";

        context.appendMethodPartText(builder);
//        ApplyFeatureProcessor.super.process(context);
        context.addProcessedAction("builder");
    }
}
