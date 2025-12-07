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
import jplus.util.Utils;

public class HashCodeFeatureProcessor implements ApplyFeatureProcessor {
    @Override
    public void process(ApplyFeatureProcessingContext context) {
        if (context.hasProcessed("hashcode")) return;

        String methodSymbol = "^hashCode$_";
        if (context.getClassSymbolTable().contains(methodSymbol, TypeInfo.Type.Method)) {
            return;
        }

        StringBuilder generatedText = new StringBuilder();
        generatedText.append("\n@Override\n");
        generatedText.append("public int hashCode() {\n");
        generatedText.append(context.getIndentation()).append("return ").append("java.util.Objects.hash(").append(String.join(", ", context.getFieldList())).append(");\n");
        generatedText.append("}\n");

        context.appendMethodPartText(generatedText.toString());
//        ApplyFeatureProcessor.super.process(context);
        context.addProcessedAction("hashcode");
    }
}
