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
        checkGeneratedCode("./src/test/samples/generics/ApplySetter.jplus", "1z2wSaMFPEihup5zDR6wH5f+XEs=");
    }

    @Test
    void testApplyGetter() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyGetter.jplus", "Cf0XeGQLc90wGb15PhG1AWJlTIo=");
    }

    @Test
    void testApplyGenerics() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyGenerics.jplus", "eFF4FV90LtZIV8mL4NtPmVQ85Y8=");
    }



    @Test
    void testApplyHashCode() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyHashCode.jplus", "2swIgD1B8nYL4OVJPRs1QVw++Is=");
    }

    @Test
    void testApplyEquals() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyEquals.jplus", "vYHwVMopnX20qoAeZzJxoLuwt4A=");
    }

    @Test
    void testApplyEquality() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyEquality.jplus", "gGV3xA+ITehB4J6jswHKW9tw35I=");
    }

    @Test
    void testApplyToBuilder() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyToBuilder.jplus", "1Xn2ny5+W2Y+lxCk8b+vsjxSW3Y=");
    }

    @Test
    void testApplyData() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyData.jplus", "AHK8SQeb3M3liQ1M0WDF9NaaLf0=");
    }

    @Test
    void testNullableMethodParamAnnotation() throws Exception {
        Project project = new Project(Path.of("./src/test/files/NullableAnnotation"));
        JPlusProcessor processor = new JPlusProcessor(project, "jplus.example", "UserMethodParamAnnotationForCodeGenerator");
        processor.process();
//        System.err.println(processor.getParseTreeString());
        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
                System.err.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
            });
        }

        assertEquals("Error: (line:22, column:15) street is a nullable variable. But it directly accesses name. Consider using null-safe operator(?.).\n" +
                "Error: (line:33, column:42) The 2nd argument of the jplus.example.UserMethodParamAnnotation constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:34, column:42) The 1st argument of the jplus.example.UserMethodParamAnnotation constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:34, column:78) The 1st argument of the jplus.example.AddressAnnotation constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:37, column:8) The 1st argument of the user2.updateAddress() is a non-nullable variable, but a null value is assigned to it.\n", outContent.toString());
    }

    @Test
    void testApplyConstructorWithNo() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyConstructorWithNo.jplus", "tMxYt2Y2giLz5PH7P+BfZCE+mVc=");
    }

    @Test
    void testApplyConstructorWithAll() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyConstructorWithAll.jplus", "4HyrVpuRaCWYSGSFd6Ln8Ov3hdM=");
    }

    @Test
    void testApplyConstructorWithRequired() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyConstructorWithRequired.jplus", "rryTmrcl69V24QLr9b2/ChFeiQE=");
    }

    @Test
    void testApplyToString() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyToString.jplus", "33E7aLd5YiZz7yRnIeDe1Z3qlkU=");
    }

    @Test
    void testApplyBuilder() throws Exception {
        checkGeneratedCode("./src/test/samples/generics/ApplyBuilder.jplus", "UVR3TSB24yuHfuYnP+ZHhglChxg=");
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