package jplus.plugin.intellij;

import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class JPlusLanguage extends Language {
    public static final JPlusLanguage INSTANCE = new JPlusLanguage();

//    private JPlusLanguage() {
//        super("JPLUS", new String[]{"text/x-java-source", "text/java", "application/x-java", "text/x-java"});
//    }

    private JPlusLanguage() {
        super(JavaLanguage.INSTANCE, "JPlus", new String[]{"text/jplus", "text/x-java-source", "text/java", "application/x-java", "text/x-java"});
    }

    @Override
    public @NotNull String getDisplayName() {
        return "JPlus";
    }
}

