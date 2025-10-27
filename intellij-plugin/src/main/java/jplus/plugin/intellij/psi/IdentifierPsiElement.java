package jplus.plugin.intellij.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;

import static jplus.base.JPlus20Parser.RULE_normalClassDeclaration;

public class IdentifierPsiElement extends ANTLRPsiLeafNode implements PsiNamedElement {

    public IdentifierPsiElement(IElementType type, CharSequence text) {
        super(type, text);
    }

    @Override
    public PsiElement setName(@NlsSafe @NotNull String s) throws IncorrectOperationException {
        return null;
    }

    @Override
    public PsiReference getReference() {
        return new JPlusReference(this, new TextRange(0, getTextLength()));
//        PsiElement parent = getParent();
//        IElementType iElementType = parent.getNode().getElementType();
//        System.out.println("referece = " + iElementType.getClass().getSimpleName());
//        // do not return a reference for the ID nodes in a definition
//        if (iElementType instanceof RuleIElementType ruleIElementType) {
//            switch ( ruleIElementType.getRuleIndex() ) {
//                case RULE_normalClassDeclaration:
////                case RULE_expr :
////                case RULE_primary :
//                    return new NormalClassDeclarationPsiReference(this);
////                case RULE_call_expr :
////                    return new FunctionRef(this);
//            }
//        }
//        return null;
    }
}
