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
                "DataFlowInitialState",
                "Error: (line:4, column:8) s is a non-nullable variable. But null value is assigned to it.\n" +
                        "Error: (line:12, column:16) u is not initialized.\n" +
                        "Error: (line:12, column:8) r is a non-nullable variable. But nullable value is assigned to it.\n" +
                        "Error: (line:14, column:8) s is a non-nullable variable. But null value is assigned to it.\n"
        );
    }

    private void checkNullability(String className, String expected) throws Exception {
        Project project = new Project(Path.of("./src/test/files/NullabilityDataflowAnalysis"));
        JPlusProcessor processor = new JPlusProcessor(project, "jplus.example", className);

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
