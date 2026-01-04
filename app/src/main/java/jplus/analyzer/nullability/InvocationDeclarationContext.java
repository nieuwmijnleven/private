package jplus.analyzer.nullability;

import jplus.base.JPlus25Parser;
import jplus.util.Utils;
import org.antlr.v4.runtime.ParserRuleContext;

public interface InvocationDeclarationContext {
    ParserRuleContext originalContext();
    JPlus25Parser.FormalParameterListContext formalParameterList();
    String methodName();

    static InvocationDeclarationContext from(JPlus25Parser.MethodDeclarationContext ctx) {
        return new InvocationDeclarationContext() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.FormalParameterListContext formalParameterList() {
                return ctx.methodHeader().methodDeclarator().formalParameterList();
            }

            @Override
            public String methodName() {
                return Utils.getTokenString(ctx.methodHeader().methodDeclarator().identifier());
            }
        };
    }

    static InvocationDeclarationContext from(JPlus25Parser.ConstructorDeclarationContext ctx) {
        return new InvocationDeclarationContext() {
            @Override
            public ParserRuleContext originalContext() {
                return ctx;
            }

            @Override
            public JPlus25Parser.FormalParameterListContext formalParameterList() {
                return ctx.constructorDeclarator().formalParameterList();
            }

            @Override
            public String methodName() {
                return "constructor";
            }
        };
    }
}
