package jplus.processor;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

public class InMemoryJavaFile extends SimpleJavaFileObject {
    private final String content;

    public InMemoryJavaFile(String className, String content) {
        super(toURI(className), Kind.SOURCE);
        this.content = content;
    }

    private static URI toURI(String className) {
        // className = "a.b.C"
        String path = className.replace('.', '/') + ".java";
        return URI.create("string:///" + path);
    }

    public String getContent() {
        return content;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return content;
    }
}
