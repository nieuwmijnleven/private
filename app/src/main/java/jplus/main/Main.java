package jplus.main;

import jplus.base.Project;
import jplus.processor.JPlusProcessor;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.out.println("Usage: jplus <java file>");
//            return;
//        }

        //Path filePath = Path.of(args[0]);
        //String className = Utils.getFileNameWithoutExtension(filePath);

        List<Path> srcDirList = List.of(Path.of("/home/user/OnTheGoDatabase/onthego.database/app/src/main/java"));

        List<Path> classPathList = List.of("/home/user/OnTheGoDatabase/onthego.database/app/build/classes/java/main", "/home/user/JPlus/intellij-plugin/build/idea-sandbox/IC-2025.1.4.1/plugins/intellij-plugin/lib/jspecify-1.0.0.jar").stream().map(Path::of).toList();

        String className = "SQLParser";

        Project project = new Project(srcDirList, classPathList);
        JPlusProcessor processor = new JPlusProcessor(project, "onthego.database.core.sqlprocessor", className);
        processor.process();

//        JPlusProcessor processor = new JPlusProcessor(Path.of(args[0]));
//        processor.process();
        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
            });
            return;
        }

        String javaCode = processor.generateJavaCode();
        System.out.println(javaCode);
    }
}
