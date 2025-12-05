package jplus.base;

import java.nio.file.Path;

public class Project {

    private final Path srcDir;

    public Project(Path srcDir) {
        this.srcDir = srcDir;
    }

    public Path getSrcDir() {
        return srcDir;
    }
}
