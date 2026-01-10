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
        checkGeneratedCode("./src/test/samples/generics/ApplySetter.jplus", "2hZ3L6MGeReTmmN7Dy7/qKaLRlA=");
    }

    @Test
    void testApplyGetter() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyGetter.jplus", "QdE5ntgm0TlTqjMoj0Xtci5qx2c=");
    }

    @Test
    void testApplyGenerics() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyGenerics.jplus", "2HI1YQuuDf3Wzdcg/tZTp8b92a0=");
    }



    @Test
    void testApplyHashCode() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyHashCode.jplus", "yTMHuRJfK9jeo9f4GxBU8KPNnbU=");
    }

    @Test
    void testApplyEquals() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyEquals.jplus", "FyGOM28B2f5SvPYRijnCaLAmMJU=");
    }

    @Test
    void testApplyEquality() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyEquality.jplus", "eE4oaFmhxZt+FVSPTABFeM8Obuw=");
    }

    @Test
    void testApplyToBuilder() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyToBuilder.jplus", "16iL8hms/J9iMueFqZw2aSy5FAk=");
    }

    @Test
    void testApplyData() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyData.jplus", "lSsHxL4mwofyYXHlYFRjiyWcnTg=");
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
                "Error: (line:30, column:64) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:42, column:34) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:46, column:35) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:58, column:35) street is a nullable variable. But it directly accesses name(). Consider using null-safe operator(?.).\n" +
                "Error: (line:70, column:15) street is a nullable variable. But it directly accesses name(). Consider using null-safe operator(?.).\n" +
                "Error: (line:74, column:15) street is a nullable variable. But it directly accesses name(). Consider using null-safe operator(?.).\n" +
                "Error: (line:86, column:8) street is a nullable variable. But it directly accesses name(). Consider using null-safe operator(?.).\n" +
                "Error: (line:90, column:8) street is a nullable variable. But it directly accesses name(). Consider using null-safe operator(?.).\n" +
                "Error: (line:102, column:76) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:106, column:91) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:118, column:90) street is a nullable variable. But it directly accesses name(). Consider using null-safe operator(?.).\n" +
                "Error: (line:122, column:35) street is a nullable variable. But it directly accesses name(). Consider using null-safe operator(?.).\n" +
                "Error: (line:138, column:35) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:142, column:36) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:148, column:58) The 2nd argument of the jplus.example.UserMethodParamAnnotationForCodeGenerator constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:149, column:58) The 1st argument of the jplus.example.UserMethodParamAnnotationForCodeGenerator constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:149, column:110) The 1st argument of the jplus.example.AddressAnnotation constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:152, column:8) The 1st argument of the jplus.example.UserMethodParamAnnotationForCodeGenerator.updateAddress() is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:154, column:8) The 2nd argument of the jplus.example.UserMethodParamAnnotationForCodeGenerator constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:154, column:8) The 1st argument of the jplus.example.UserMethodParamAnnotationForCodeGenerator.updateAddress() is a non-nullable variable, but a null value is assigned to it.\n", outContent.toString());
    }

    @Test
    void testApplyConstructorWithNo() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyConstructorWithNo.jplus", "5cHIkScgk9vQMQ4JEUp9heuWDhA=");
    }

    @Test
    void testApplyConstructorWithAll() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyConstructorWithAll.jplus", "sOGWkAyN9f2Vd3QY9BdI0Yeltq4=");
    }

    @Test
    void testApplyConstructorWithRequired() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyConstructorWithRequired.jplus", "FEoScBd1/3GCMoAhsSsIZZPSKK0=");
    }

    @Test
    void testApplyToString() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyToString.jplus", "F7w02FLzIfhdUDFj7Cll/SKZgWw=");
    }

    @Test
    void testApplyBuilder() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyBuilder.jplus", "wuPD49++rD1ngSxO8gKb5DxdQBU=");
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

        //String parseTreeString = processor.getParseTreeString();
        //String hashString = getHashString(parseTreeString);
        //System.err.println("hashString = " + hashString);

        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            fail();
        }

        String generatedJavaCode = processor.generateJavaCode();
        System.err.println("[BoilerplateCodeGeneration] = " + generatedJavaCode);

        /*processor = new JPlusProcessor(generatedJavaCode);
        processor.process();

        String parseTreeString = processor.getParseTreeString();
        String hashString = getHashString(parseTreeString);*/

        String hashString = getHashString(generatedJavaCode);

        assertEquals(expected, hashString);
    }

    private String getHashString(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("sha-1");
        byte[] hash = messageDigest.digest(s.getBytes(StandardCharsets.UTF_8));
        String hashString = Base64.getEncoder().encodeToString(hash);
        return hashString;
    }
}