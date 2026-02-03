package jplus.base;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Project {

    /** Java source roots (.java, .jplus) */
    private final List<Path> sourceDirs;

    /** Java classpath entries (.class directories or .jar files) */
    private final List<Path> javaClassPath;

    private String jdkHome;

    public Project(Path srcDir) {
        Objects.requireNonNull(srcDir, "srcDir");
        this.sourceDirs = List.of(srcDir);
        this.javaClassPath = List.of();
    }

    public Project(List<Path> sourceDirs) {
        Objects.requireNonNull(sourceDirs, "sourceDirs");
        if (sourceDirs.isEmpty()) {
            throw new IllegalArgumentException("sourceDirs must not be empty");
        }

        this.sourceDirs = List.copyOf(sourceDirs);
        this.javaClassPath = List.of();
    }

    public Project(List<Path> sourceDirs, List<Path> javaClassPath) {
        Objects.requireNonNull(sourceDirs, "sourceDirs");
        Objects.requireNonNull(javaClassPath, "javaClassPath");

        if (sourceDirs.isEmpty()) {
            throw new IllegalArgumentException("sourceDirs must not be empty");
        }

        this.sourceDirs = List.copyOf(sourceDirs);
        this.javaClassPath = List.copyOf(javaClassPath);
    }

    public void setJdkHome(String jdkHome) {
        this.jdkHome = jdkHome;
    }

    public String getJdkHome() {
        return jdkHome;
    }

    public List<Path> getSourceDirs() {
        return sourceDirs;
    }

    public Path getSrcDir() {
        return sourceDirs.get(0);
    }

    public Project withSourceDir(Path srcDir) {
        Objects.requireNonNull(srcDir, "srcDir");

        List<Path> newDirs = new ArrayList<>(this.sourceDirs);
        newDirs.add(srcDir);
        return new Project(newDirs, this.javaClassPath);
    }

    public String buildJavaSourcePath() {
        return sourceDirs.stream()
                .map(Path::toAbsolutePath)
                .map(Path::toString)
                .collect(Collectors.joining(File.pathSeparator));
    }

    public List<Path> getJavaClassPath() {
        return javaClassPath;
    }

    public Project withJavaClassPathEntry(Path entry) {
        Objects.requireNonNull(entry, "entry");

        List<Path> newCp = new ArrayList<>(this.javaClassPath);
        newCp.add(entry);
        return new Project(this.sourceDirs, newCp);
    }

    public String buildJavaClassPath() {
        return javaClassPath.stream()
                .map(Path::toAbsolutePath)
                .map(Path::toString)
                .collect(Collectors.joining(File.pathSeparator));
    }
}
