package jplus.plugin.intellij;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import org.jetbrains.annotations.NotNull;

public class JPlusAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof PsiErrorElement) {
            String text = element.getText();
            System.err.println("text = " + text);
//            if ("?".equals(text)) { // nullability 표시
//                holder.newAnnotation(HighlightSeverity.INFORMATION, "Nullable type indicator")
//                        .textAttributes(DefaultLanguageHighlighterColors.KEYWORD)
//                        .create();
//            } else if ("apply".equals(text)) {
//                holder.newAnnotation(HighlightSeverity.INFORMATION, "Apply statement")
//                        .textAttributes(DefaultLanguageHighlighterColors.KEYWORD)
//                        .create();
//            }
        }
    }
}
