package jplus.analyzer.nullability;

import jplus.base.JPlus25Parser;
import jplus.base.Modifier;
import jplus.util.Utils;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.EnumSet;

public interface InvocationDeclarationContext {
    ParserRuleContext originalContext();
    JPlus25Parser.FormalParameterListContext formalParameterList();
    EnumSet<Modifier> modifiers();
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
            public EnumSet<Modifier> modifiers() {
                EnumSet<Modifier> modifierEnumSet = EnumSet.noneOf(Modifier.class);
                for (JPlus25Parser.MethodModifierContext methodModifierContext : ctx.methodModifier()) {
                    modifierEnumSet.add(Modifier.valueOf(methodModifierContext.toString().toUpperCase()));
                }
                return modifierEnumSet;
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
            public EnumSet<Modifier> modifiers() {
                EnumSet<Modifier> modifierEnumSet = EnumSet.noneOf(Modifier.class);
                for (JPlus25Parser.ConstructorModifierContext constructorModifierContext: ctx.constructorModifier()) {
                    modifierEnumSet.add(Modifier.valueOf(constructorModifierContext.toString().toUpperCase()));
                }
                return modifierEnumSet;
            }

            @Override
            public String methodName() {
                return "constructor";
            }
        };
    }
}
