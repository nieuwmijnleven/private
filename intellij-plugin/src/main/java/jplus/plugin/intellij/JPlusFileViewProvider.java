package jplus.plugin.intellij;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.SingleRootFileViewProvider;

public class JPlusFileViewProvider extends SingleRootFileViewProvider {
    public JPlusFileViewProvider(PsiManager manager, VirtualFile file, boolean eventSystemEnabled) {
        super(manager, file, eventSystemEnabled, JavaLanguage.INSTANCE); // 💡 base를 Java로 지정
    }
}