package jplus.plugin.intellij;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.FakePsiElement;
import com.intellij.psi.impl.source.resolve.reference.impl.PsiDelegateReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * test.jplus 안에서 커서 이동을 유도하기 위한 가짜 PsiElement
 */
public class JPlusPsiElementWrapper extends FakePsiElement implements NavigatablePsiElement {

    private final PsiElement psiElement;
    private final PsiFile file;
    private final TextRange range;
    private final String name;

    public JPlusPsiElementWrapper(@NotNull PsiElement psiElement, @NotNull PsiFile file, @NotNull TextRange range, @NotNull String name) {
        this.psiElement = psiElement;
        this.file = file;
        this.range = range;
        this.name = name;
    }

    public PsiElement getPsiElement() {
        return this.psiElement;
    }

    @Override
    public @Nullable PsiElement getParent() {
        return file;
    }

    @Override
    public PsiFile getContainingFile() {
        return file;
    }

    @Override
    public TextRange getTextRange() {
        return range;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void navigate(boolean requestFocus) {
        Project project = file.getProject();
        VirtualFile vFile = file.getVirtualFile();

        if (vFile != null) {
            int offset = range.getStartOffset();
            new OpenFileDescriptor(project, vFile, offset).navigate(requestFocus);
        }
    }

    @Override
    public boolean canNavigate() {
        return file.getVirtualFile() != null;
    }

    @Override
    public boolean canNavigateToSource() {
        return canNavigate();
    }

    @Override
    public String toString() {
        return "JPlusPsiElementWrapper(" + name + ")";
    }
}
