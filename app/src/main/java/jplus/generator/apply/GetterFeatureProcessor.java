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
import jplus.util.CodeUtils;
import jplus.util.Utils;

import java.util.List;
import java.util.stream.Collectors;

public class GetterFeatureProcessor implements ApplyFeatureProcessor {
    @Override
    public void process(ApplyFeatureProcessingContext context) {
        if (context.hasProcessed("getter")) return;

        for (String fieldName : context.getFieldList()) {
            System.err.println("[GetterFeatureProcessor] fieldName = " + fieldName);
            String methodName = "get" + Utils.convertToPascalCase(fieldName);
            String methodSymbol = "^" + methodName + "$_";
            if (context.getClassSymbolTable().contains(methodSymbol, TypeInfo.Type.Method)) {
                continue;
            }

            SymbolInfo symbolInfo = context.getClassSymbolTable().resolve(fieldName);
            System.err.println("[GetterFeatureProcessor] typeInfo = " + symbolInfo.getTypeInfo());
            String simpleTypeName = CodeGenUtils.getSimpleTypeName(symbolInfo.getTypeInfo());

            List<Modifier> excludedModifiers = List.of(Modifier.STATIC);
            if (symbolInfo.getModifierList().stream().anyMatch(modifier -> excludedModifiers.contains(modifier))) {
                continue;
            }

            StringBuilder methodPartText = new StringBuilder();
            methodPartText.append("\n");
            methodPartText.append("public ").append(simpleTypeName).append(" ").append(methodName).append("() {\n");
            methodPartText.append(context.getIndentation()).append("return ").append(fieldName).append(";\n");
            methodPartText.append("}\n");

            context.appendMethodPartText(methodPartText.toString());
        }
//        ApplyFeatureProcessor.super.process(context);
        context.addProcessedAction("getter");
    }
}