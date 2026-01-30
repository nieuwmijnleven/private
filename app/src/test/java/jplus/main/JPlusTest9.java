package jplus.main;

import jplus.base.Project;
import jplus.processor.JPlusProcessor;
import jplus.util.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JPlusTest9 {
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
    void testDataFlowInitialState() throws Exception {
        checkNullability(
                "./src/test/files/NullabilityDataflowAnalysis",
                "jplus.example",
                "DataFlowInitialState",
                "Error: (line:12, column:16) u may be null. Consider checking for null or using null-safe operator(?.) before accessing.\n" +
                        "Error: (line:12, column:8) r is a non-nullable variable. But nullable value is assigned to it.\n" +
                        "Error: (line:15, column:12) s is a nullable variable. But it directly accesses length(). Consider using null-safe operator(?.).\n" +
                        "Error: (line:20, column:16) s is a nullable variable. But it directly accesses length(). Consider using null-safe operator(?.).\n" +
                        "Error: (line:24, column:16) s is a nullable variable. But it directly accesses length(). Consider using null-safe operator(?.).\n" +
                        "Error: (line:42, column:12) s is a nullable variable. But it directly accesses length(). Consider using null-safe operator(?.).\n" +
                        "Error: (line:47, column:16) s is a nullable variable. But it directly accesses length(). Consider using null-safe operator(?.).\n"
        );
    }

    @Test
    void testStaticFieldInitialization() throws Exception {
        checkNullability(
                "./src/test/files/NullabilityDataflowAnalysis",
                "jplus.example",
                "StaticFieldInitialization",
                ""
        );
    }

    @Test
    void testConstructors() throws Exception {
        checkNullability(
                "./src/test/files/NullableAnnotation",
                "jplus.example",
                "Constructors",
                ""
        );
    }

    @Test
    void testSwitch() throws Exception {
        checkNullability(
                "./src/test/files/NullableAnnotation",
                "jplus.example",
                "Switch",
                "Error: (line:8, column:46) The 1st argument of the java.io.PrintStream.println() is a non-nullable variable, but a null value is assigned to it.\n" +
                        "Error: (line:9, column:41) The 1st argument of the java.io.PrintStream.println() is a non-nullable variable, but a null value is assigned to it.\n"
        );
    }

    private void checkNullability(String srcPath, String packageName, String className, String expected) throws Exception {
        Project project = new Project(Path.of(srcPath));
        JPlusProcessor processor = new JPlusProcessor(project, packageName, className);

        processor.process();
        System.err.println("[ParseTreeString] = " + processor.getParseTreeString());

        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
                System.err.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
            });
        }

        assertEquals(expected, outContent.toString());
    }

    private String getHashString(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("sha-1");
        byte[] hash = messageDigest.digest(s.getBytes(StandardCharsets.UTF_8));
        String hashString = Base64.getEncoder().encodeToString(hash);
        return hashString;
    }
}
