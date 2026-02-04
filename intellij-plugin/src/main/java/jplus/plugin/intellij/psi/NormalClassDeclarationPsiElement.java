package jplus.plugin.intellij.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import jplus.base.JPlus25Parser;
import jplus.plugin.intellij.JPlusTokenTypes;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;

public class NormalClassDeclarationPsiElement extends ANTLRPsiNode {
    public NormalClassDeclarationPsiElement(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        IElementType typeIndentifier = JPlusTokenTypes.getRuleElementType(JPlus25Parser.RULE_typeIdentifier);
        ASTNode child = getNode().findChildByType(typeIndentifier);
        return child != null ? child.getText() : super.getName();
    }
}
