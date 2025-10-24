package jplus.plugin.intellij;

import com.intellij.lang.ASTNode;
import com.intellij.lang.java.JavaParserDefinition;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import org.jetbrains.annotations.NotNull;

public class JPlusParserDefinitionFromJavaParserDefinition extends JavaParserDefinition {
//    public static final IFileElementType FILE =
//            new IFileElementType(JPlusLanguage.INSTANCE);

//    @Override
//    public @NotNull IFileElementType getFileNodeType() {
//        return FILE;
//    }

//    @Override
//    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
//        return new JPlusFile(viewProvider);
//    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        if (!(viewProvider instanceof JPlusFileViewProvider)) {
            viewProvider = new JPlusFileViewProvider(
                    viewProvider.getManager(), viewProvider.getVirtualFile(), true);
        }
        return new PsiJavaFileImpl(viewProvider);
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return super.createElement(node);
    }
}
