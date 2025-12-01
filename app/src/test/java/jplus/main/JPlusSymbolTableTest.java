package jplus.main;

import jplus.analyzer.NullabilityChecker;
import jplus.base.Project;
import jplus.processor.JPlusProcessor;
import jplus.processor.JavaProcessor;
import jplus.processor.ProjectProcessor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JPlusSymbolTableTest {
    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(new PrintStream(originalOut));
    }

    @Test
    void testSymbolResolver() throws Exception {
        JPlusProcessor processor = new JPlusProcessor(Path.of("./src/test/files/SymbolResolver/User.jplus"));
//        processor.addSrcDirPath(Path.of("./src/test/files/SymbolResolver"));
        processor.process();
        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.getLine(), nullabilityIssue.getColumn(), nullabilityIssue.getMessage());
//                System.err.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.getLine(), nullabilityIssue.getColumn(), nullabilityIssue.getMessage());
            });
        }

        assertEquals("Error: (line:16, column:15) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:27, column:21) The 2nd argument of the User constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:28, column:21) The 1st argument of the User constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:28, column:36) The 1st argument of the Address constructor is a non-nullable variable, but a null value is assigned to it.\n", outContent.toString());
    }

    @Test
    void testNullableAnnotation() throws Exception {
        JPlusProcessor processor = new JPlusProcessor(Path.of("./src/test/files/NullableAnnotation/UserAnnotation.jplus"));
//        processor.addSrcDirPath(Path.of("./src/test/files/NullableAnnotation"));
        processor.process();
        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.getLine(), nullabilityIssue.getColumn(), nullabilityIssue.getMessage());
//                System.err.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.getLine(), nullabilityIssue.getColumn(), nullabilityIssue.getMessage());
            });
        }

        assertEquals("Error: (line:16, column:15) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:27, column:31) The 2nd argument of the UserAnnotation constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:28, column:31) The 1st argument of the UserAnnotation constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:28, column:56) The 1st argument of the AddressAnnotation constructor is a non-nullable variable, but a null value is assigned to it.\n", outContent.toString());
    }
    @Test
    void testNullableConstructorParamAnnotation() throws Exception {
        Project project = new Project(Path.of("./src/test/files/NullableAnnotation"));
        JPlusProcessor processor = new JPlusProcessor(project, "jplus.example", "UserConstructorParamAnnotation");
        processor.process();
        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.getLine(), nullabilityIssue.getColumn(), nullabilityIssue.getMessage());
                System.err.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.getLine(), nullabilityIssue.getColumn(), nullabilityIssue.getMessage());
            });
        }

        assertEquals("Error: (line:13, column:8) cannot assign addr(nullable) to this.address(non-nullable).\n" +
                "Error: (line:18, column:15) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:30, column:47) The 1st argument of the UserConstructorParamAnnotation constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:30, column:88) The 1st argument of the AddressAnnotation constructor is a non-nullable variable, but a null value is assigned to it.\n", outContent.toString());
    }

    @Test
    void testNullableMethodParamAnnotation() throws Exception {
        JPlusProcessor processor = new JPlusProcessor(Path.of("./src/test/files/NullableAnnotation/UserMethodParamAnnotation.jplus"));
//        processor.addSrcDirPath(Path.of("./src/test/files/NullableAnnotation"));
        processor.process();
        System.err.println(processor.getParseTreeString());
        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.getLine(), nullabilityIssue.getColumn(), nullabilityIssue.getMessage());
                System.err.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.getLine(), nullabilityIssue.getColumn(), nullabilityIssue.getMessage());
            });
        }

        assertEquals("Error: (line:22, column:15) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:33, column:42) The 2nd argument of the UserMethodParamAnnotation constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:34, column:42) The 1st argument of the UserMethodParamAnnotation constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:34, column:78) The 1st argument of the AddressAnnotation constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:37, column:8) The 1st argument of the UserMethodParamAnnotation.updateAddress() is a non-nullable variable, but a null value is assigned to it.\n", outContent.toString());
    }


    @Test
    void testConvertJavaWithNullableAnnotation() throws Exception {
//        JPlusProcessor processor = new JPlusProcessor(Path.of("./src/test/files/NullableAnnotation/User.jplus"));
////        processor.addSrcDirPath(Path.of("./src/test/files/NullableAnnotation"));
//        processor.process();
////        System.err.println(processor.getParseTreeString());
//        String javaCode = processor.generateJavaCodeWithoutBoilerplate();
//        System.err.println(javaCode);
//
//        JavaProcessor javaProcessor = new JavaProcessor(javaCode);
//        javaProcessor.process();
//        javaProcessor.analyzeSymbols();


    }
}
