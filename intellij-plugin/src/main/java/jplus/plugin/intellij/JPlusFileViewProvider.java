package jplus.plugin.intellij;

import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.SingleRootFileViewProvider;
import org.jetbrains.annotations.NotNull;

public class JPlusFileViewProvider extends SingleRootFileViewProvider {
    public JPlusFileViewProvider(@NotNull PsiManager manager,
                                 @NotNull VirtualFile file,
                                 boolean eventSystemEnabled,
                                 @NotNull Language baseLanguage) {
        super(manager, file, eventSystemEnabled, baseLanguage);
    }
}
