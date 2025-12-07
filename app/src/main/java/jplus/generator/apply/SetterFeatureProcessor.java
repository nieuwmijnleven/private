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
import jplus.base.SymbolTable;
import jplus.base.TypeInfo;
import jplus.util.CodeUtils;
import jplus.util.Utils;

import java.util.List;
import java.util.stream.Collectors;

public class SetterFeatureProcessor implements ApplyFeatureProcessor {
    @Override
    public void process(ApplyFeatureProcessingContext context) {
        if (context.hasProcessed("setter")) return;

        SymbolTable classSymbolTable = context.getClassSymbolTable();
        for (String fieldName : context.getFieldList()) {
            SymbolInfo symbolInfo = classSymbolTable.resolve(fieldName);
            TypeInfo typeInfo = symbolInfo.getTypeInfo();
            String typeName = typeInfo.getName();
            String simpleTypeName = CodeUtils.getSimpleName(typeName);

            List<Modifier> excludedModifiers = List.of(Modifier.FINAL, Modifier.STATIC);
            if (symbolInfo.getModifierList().stream().anyMatch(modifier -> excludedModifiers.contains(modifier))) {
                continue;
            }

            String methodName = "set" + Utils.convertToPascalCase(fieldName);
            String methodSymbol = "^" + methodName + "$_" + typeName;
            if (classSymbolTable.contains(methodSymbol, TypeInfo.Type.Method)) {
                continue;
            }

            StringBuffer methodPartText = new StringBuffer();
            methodPartText.append("\n");
            methodPartText.append("public ").append("void").append(" ").append(methodName).append("(").append(simpleTypeName).append(" ").append(fieldName).append(")").append(" {\n");
            methodPartText.append(context.getIndentation()).append("this.").append(fieldName).append(" = ").append(fieldName).append(";\n").append("}\n");

            context.appendMethodPartText(methodPartText.toString());
        }
//        ApplyFeatureProcessor.super.process(context);
        context.addProcessedAction("setter");
    }
}
