package jplus.plugin.intellij;

import com.intellij.core.CoreASTFactory;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.tree.IElementType;
import jplus.base.JPlus20Lexer;
import jplus.plugin.intellij.psi.IdentifierPsiElement;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;

public class JPlusASTFactory extends CoreASTFactory {
	@NotNull
    @Override
    public CompositeElement createComposite(IElementType type) {
	    return super.createComposite(type);
    }

	@NotNull
	@Override
	public LeafElement createLeaf(@NotNull IElementType type, CharSequence text) {
		if ( type instanceof TokenIElementType &&
			 ((TokenIElementType) type).getANTLRTokenType() == JPlus20Lexer.Identifier)
		{
            System.err.println("created IdentifierPsiElement");
			// found an ID node; here we do not distinguish between definitions and references
			// because we have no context information here. All we know is that
			// we have an identifier node that will be connected somewhere in a tree.
			//
			// You can only rename, find usages, etc... on leaves implementing PsiNamedElement
			//
			// TODO: try not to create one for IDs under def subtree roots like vardef, function
			return new IdentifierPsiElement(type, text);
		}

        System.err.println("type " + type.getDebugName());
        System.err.println("class " + type.getClass().getSimpleName());
        if ( type instanceof TokenIElementType tokenIElementType) {
            System.err.println("TokenIElementType " + tokenIElementType.getANTLRTokenType());
        }
		LeafElement leaf = super.createLeaf(type, text);
		return leaf;
    }
}
