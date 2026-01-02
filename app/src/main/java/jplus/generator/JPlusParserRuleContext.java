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

package jplus.generator;

import jplus.base.JPlus25Parser;
import jplus.base.JPlus25Parser.ApplyDeclarationContext;
import jplus.base.JPlus25Parser.ExpressionNameContext;
import jplus.base.JPlus25Parser.FieldAccessContext;
import jplus.base.JPlus25Parser.MethodInvocationContext;
import jplus.base.JPlus25Parser.NullCoalescingExpressionContext;
import jplus.base.JPlus25Parser.PNNAContext;
import jplus.base.JPlus25Parser.PrimaryNoNewArrayContext;
import jplus.base.JPlus25Parser.UnannTypeContext;
import jplus.editor.FragmentedText;
import jplus.util.ParserUtils;
import jplus.util.Utils;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class JPlusParserRuleContext extends ParserRuleContext {
    private final CodeGenDelegate basicDelegate = new BasicCodeGenDelegate(this);
    private final CodeGenDelegate semanticDelegate = new SemanticCodeGenDelegate(this);

    public JPlusParserRuleContext(ParserRuleContext parent, int invokingStateNumber) {
        super(parent, invokingStateNumber);
    }

    @Override
    public String getText() {
        return isSemanticMode() ? semanticDelegate.getText()
                                : basicDelegate.getText();
    }

    private boolean isSemanticMode() {
        return CodeGenContext.current().isSemanticMode();
    }
}