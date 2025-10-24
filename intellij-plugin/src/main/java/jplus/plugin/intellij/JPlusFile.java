package jplus.plugin.intellij;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.impl.source.PsiJavaFileBaseImpl;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import org.jetbrains.annotations.NotNull;

public class JPlusFile extends PsiJavaFileImpl {

    protected JPlusFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider);
    }

//    @Override
//    public @NotNull FileType getFileType() {
//        return JPlusFileType.INSTANCE;
//    }
//
//    @Override
//    public String toString() {
//        return "JPlus file";
//    }
}
