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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToStringFeatureProcessor implements ApplyFeatureProcessor {
    @Override
    public void process(ApplyFeatureProcessingContext context) {
        if (context.hasProcessed("tostring")) return;

        String methodSymbol = "^toString$_";
        if (context.getClassSymbolTable().contains(methodSymbol, TypeInfo.Type.Method)) {
            return;
        }

        String toString = "\n@Override\n";
        toString += "public String toString() {\n";
        toString += context.getIndentation() + "return \"" + context.getTargetClass() + "{\"";

        List<String> lines = new ArrayList<>();
        for (String fieldName : context.getFieldList()) {
            lines.add("\"" + fieldName + "=\"" + " + " + fieldName);
        }
        toString += lines.stream().collect(Collectors.joining("+ \", \" + ", " + ", " + "));
        toString += "\"}\";\n";
        toString += "}\n";
        context.appendMethodPartText(toString);
//        ApplyFeatureProcessor.super.process(context);
        context.addProcessedAction("tostring");
    }
}
