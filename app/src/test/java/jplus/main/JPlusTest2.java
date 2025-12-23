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

class JPlusTest2 {
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
    void testApplySetter() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplySetter.jplus", "wPKhrny4qhcRQUsYO0+zAm7KMpg=");
    }

    @Test
    void testApplyGetter() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyGetter.jplus", "ngP1x8iwnp9zE5UL5hEHhgnPxeM=");
    }

    @Test
    void testApplyGenerics() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyGenerics.jplus", "YjZlxsnWSUhBuxCCPk52Wup/HPw=");
    }



    @Test
    void testApplyHashCode() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyHashCode.jplus", "GoGv4wptqzWrWxozkV3gN6N35aw=");
    }

    @Test
    void testApplyEquals() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyEquals.jplus", "G0Kw+8iWktD6w29oRj5YJ2RfuQE=");
    }

    @Test
    void testApplyEquality() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyEquality.jplus", "6udO+Lhyqehr1ct4AH582I9CFE8=");
    }

    @Test
    void testApplyToBuilder() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyToBuilder.jplus", "gelnDevoQZEs9mKZ4cFJllVH8t4=");
    }

    @Test
    void testApplyData() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyData.jplus", "amnGh2AW2D7x6vlOKyj6voimwOc=");
    }

    @Test
    void testNullableMethodParamAnnotation() throws Exception {
        Project project = new Project(Path.of("./src/test/files/NullableAnnotation"));
        JPlusProcessor processor = new JPlusProcessor(project, "jplus.example", "UserMethodParamAnnotationForCodeGenerator");
        processor.process();
        System.err.println("[ParseTreeString] " + processor.getParseTreeString());
        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
                System.err.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
            });
        }

        assertEquals("Error: (line:22, column:15) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:26, column:15) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:70, column:15) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:86, column:8) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:109, column:58) The 2nd argument of the jplus.example.UserMethodParamAnnotationForCodeGenerator constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:110, column:58) The 1st argument of the jplus.example.UserMethodParamAnnotationForCodeGenerator constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:110, column:110) The 1st argument of the jplus.example.AddressAnnotation constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:113, column:8) The 1st argument of the UserMethodParamAnnotationForCodeGenerator.updateAddress() is a non-nullable variable, but a null value is assigned to it.\n", outContent.toString());
    }

    @Test
    void testApplyConstructorWithNo() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyConstructorWithNo.jplus", "mCOocA3c9h2396LcUTepeJUmxC0=");
    }

    @Test
    void testApplyConstructorWithAll() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyConstructorWithAll.jplus", "u8IYl7H++eeomF+k1AoELdRLiSQ=");
    }

    @Test
    void testApplyConstructorWithRequired() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyConstructorWithRequired.jplus", "pFh2+Tkvb1yJiJzKXKu3ivk6XvU=");
    }

    @Test
    void testApplyToString() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyToString.jplus", "VgDOxwpRb9n2gn7qLSP9w0pF5E0=");
    }

    @Test
    void testApplyBuilder() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyBuilder.jplus", "cB7/mwtLK3cWvziXjvPLhUr8LVA=");
    }

    private void checkGeneratedCode(String fileName, String expected) throws Exception {
        Path filePath = Path.of(fileName);
        Path parentDirectory = filePath.getParent();
        String className = Utils.getFileNameWithoutExtension(filePath);
        System.err.println("[checkGeneratedCode] parentDirectory = " + parentDirectory.toString());
        System.err.println("[checkGeneratedCode] className = " + className);

        Project project = new Project(parentDirectory);
        JPlusProcessor processor = new JPlusProcessor(project, "", className);
        processor.process();

        String parseTreeString = processor.getParseTreeString();
        String hashString = getHashString(parseTreeString);
        System.err.println("hashString = " + hashString);

        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            fail();
        }

        String generatedJavaCode = processor.generateJavaCode();
        System.err.println("[BoilerplateCodeGeneration] = " + generatedJavaCode);

        processor = new JPlusProcessor(generatedJavaCode);
        processor.process();

        parseTreeString = processor.getParseTreeString();
        hashString = getHashString(parseTreeString);

        assertEquals(expected, hashString);
    }

    private String getHashString(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("sha-1");
        byte[] hash = messageDigest.digest(s.getBytes(StandardCharsets.UTF_8));
        String hashString = Base64.getEncoder().encodeToString(hash);
        return hashString;
    }
}