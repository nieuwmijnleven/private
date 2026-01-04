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

import java.util.EnumSet;

public class ToBuilderFeatureProcessor implements ApplyFeatureProcessor {
    @Override
    public void process(ApplyFeatureProcessingContext context) {
        if (context.hasProcessed("tobuilder")) return;

        String methodName = "^toBuilder$_";
        if (context.getClassSymbolTable().contains(methodName, TypeInfo.Type.Method)) {
            return;
        }

        if (!context.getClassSymbolTable().contains("Builder", TypeInfo.Type.Class) || !context.hasProcessed("builder")) {
            new BuilderFeatureProcessor().process(context);
        }

        String indentation = context.getIndentation();
        String doubleIndentation = indentation.repeat(2);

        TypeInfo targetClassTypeInfo = context.getTargetClassTypeInfo();
        System.err.println("[ToBuilderFeatureProcessor] targetClassTypeInfo = " + targetClassTypeInfo);
        String typeArgumentString = CodeGenUtils.getGenericTypeArgumentString(targetClassTypeInfo);
        System.err.println("[ToBuilderFeatureProcessor] typeArgumentString = " + typeArgumentString);


        String builderTypeName = "Builder" + typeArgumentString;
        String builder = "\npublic " + builderTypeName + " toBuilder() {\n";
        builder += indentation + "return new Builder" + typeArgumentString + "()";

        for (String fieldName : context.getFieldList()) {
            SymbolInfo symbolInfo = context.getClassSymbolTable().resolve(fieldName);

            EnumSet<Modifier> excluded = EnumSet.of(Modifier.STATIC);
            if (CodeGenUtils.hasAnyModifiers(symbolInfo, excluded)) {
                continue;
            }

            builder += "\n" + doubleIndentation + "." + fieldName + "(this." + fieldName + ")";
        }

        builder += ";\n}\n";

        context.appendMethodPartText(builder);
//        ApplyFeatureProcessor.super.process(context);
        context.addProcessedAction("tobuilder");
    }
}
