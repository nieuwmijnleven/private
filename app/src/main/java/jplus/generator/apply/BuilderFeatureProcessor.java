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
import jplus.util.CodeGenUtils;

import java.util.ArrayList;
import java.util.EnumSet;
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

        TypeInfo targetClassTypeInfo = context.getTargetClassTypeInfo();
        System.err.println("[BuilderFeatureProcessor] targetClassTypeInfo = " + targetClassTypeInfo);
        String targetClassTypeName = CodeGenUtils.getSimpleTypeName(targetClassTypeInfo);
        System.err.println("[BuilderFeatureProcessor] targetClassTypeName = " + targetClassTypeName);
        String typeArgumentString = CodeGenUtils.getGenericTypeArgumentString(targetClassTypeInfo);
        System.err.println("[BuilderFeatureProcessor] typeArgumentString = " + typeArgumentString);


        String builderTypeName = "Builder" + typeArgumentString;
        String builder = "\npublic static class " + builderTypeName + " {";
        List<String> fieldDeclarations = new ArrayList<>();
        List<String> methodDeclarations = new ArrayList<>();
        List<String> nonStaticFieldList = new ArrayList<>();
        for (String fieldName : context.getFieldList()) {
            SymbolInfo symbolInfo = context.getClassSymbolTable().resolve(fieldName);
            TypeInfo typeInfo = symbolInfo.getTypeInfo();

            EnumSet<Modifier> excluded = EnumSet.of(Modifier.STATIC);
            if (CodeGenUtils.hasAnyModifiers(symbolInfo, excluded)) {
                continue;
            }

            String simpleTypeName = CodeGenUtils.getSimpleTypeName(typeInfo);

            fieldDeclarations.add(indentation + "private " + simpleTypeName + " " + fieldName + ";");
            methodDeclarations.add(indentation + "public " + builderTypeName + " " + fieldName + "(" + simpleTypeName + " " + fieldName + ") {\n" +
                    doubleIndentation + "this." + fieldName + " = " + fieldName + ";\n" +
                    doubleIndentation + "return this;\n" +
                    indentation + "}"
            );

            nonStaticFieldList.add(fieldName);
        }

        builder += fieldDeclarations.stream().collect(Collectors.joining("\n", "\n", "\n"));
        builder += methodDeclarations.stream().collect(Collectors.joining("\n\n", "\n", "\n"));
        builder += "\n" + indentation + "public " + targetClassTypeName + " build() {\n";
        builder += doubleIndentation + "return new " + targetClassTypeName + "(" + nonStaticFieldList.stream().collect(Collectors.joining(", ")) + ");\n";
        builder += indentation + "}\n";
        builder += "}\n";

        builder += "\npublic static " + getTypeArgumentString(typeArgumentString) + builderTypeName + " builder() {\n";
        builder += indentation + "return new " + builderTypeName + "();\n";
        builder += "}\n";

        context.appendMethodPartText(builder);
//        ApplyFeatureProcessor.super.process(context);
        context.addProcessedAction("builder");
    }

    private String getTypeArgumentString(String typeArgumentString) {
        return typeArgumentString + (!typeArgumentString.isEmpty() ? " " : "");
    }
}
