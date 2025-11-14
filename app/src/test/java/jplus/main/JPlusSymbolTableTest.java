package jplus.main;

import jplus.processor.JPlusProcessor;
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
    void testNullableType() throws Exception {
        JPlusProcessor processor = new JPlusProcessor(Path.of("./src/test/files/User.jplus"));
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
}
