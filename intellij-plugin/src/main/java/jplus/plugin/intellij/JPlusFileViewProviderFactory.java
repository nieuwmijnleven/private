package jplus.plugin.intellij;

import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.FileViewProviderFactory;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;

public class JPlusFileViewProviderFactory implements FileViewProviderFactory {
    @Override
    public @NotNull FileViewProvider createFileViewProvider(@NotNull VirtualFile file,
                                                            @NotNull Language language,
                                                            @NotNull PsiManager manager,
                                                            boolean eventSystemEnabled) {

        return new JPlusFileViewProvider(manager, file, eventSystemEnabled, JavaLanguage.INSTANCE);
    }
}
