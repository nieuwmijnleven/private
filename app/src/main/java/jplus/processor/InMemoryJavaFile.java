/*
 * Copyright 2025 Cheol Jeon <nieuwmijnleven@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
