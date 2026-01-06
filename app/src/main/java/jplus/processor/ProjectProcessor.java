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

import jplus.analyzer.nullability.NullabilityChecker;
import jplus.base.SymbolTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectProcessor {
    private final Path sourceDir;
    private final SymbolTable globalSymbolTable;
    private final Map<Path, JPlusProcessor> jPlusProcessorMap = new HashMap<>();

    public ProjectProcessor(Path sourceDir) {
        if (!sourceDir.toFile().isDirectory()) {
            throw new IllegalArgumentException("The argument must be the path of a source directory.");
        }
        this.sourceDir = sourceDir;
        this.globalSymbolTable = new SymbolTable(null);
    }

    public SymbolTable buildSymbolTable() throws IOException {
        Files.walk(sourceDir)
            .filter(path -> path.toString().endsWith(".jplus"))
            .forEach(path -> {
                JPlusProcessor jPlusProcessor = null;
                try {
                    jPlusProcessor = new JPlusProcessor(path, globalSymbolTable);
                    jPlusProcessor.process();
                    jPlusProcessor.analyzeSymbols();
                    jPlusProcessorMap.put(path, jPlusProcessor);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        return globalSymbolTable;
    }

    public List<NullabilityChecker.NullabilityIssue> checkNullability(Path sourcePath) {
        JPlusProcessor processor = jPlusProcessorMap.get(sourcePath);
        if (processor == null) {
            throw new IllegalArgumentException("There is no analyzed source file : " + sourcePath.toAbsolutePath());
        }
        return processor.checkNullability();
    }

}
