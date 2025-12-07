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
    void testApplyHashCode() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyHashCode.jplus", "FiHLi04TBUW4bDxmWsfSNLklKGg=");
    }

    @Test
    void testApplyGenerics() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyGenerics.jplus", "FiHLi04TBUW4bDxmWsfSNLklKGg=");
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
//        System.err.println(processor.getParseTreeString());
        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            fail();
        }

        String generatedJavaCode = processor.generateJavaCode();
        System.err.println("[BoilerplateCodeGeneration] = " + generatedJavaCode);
        processor = new JPlusProcessor(generatedJavaCode);
        processor.process();

        String parseTreeString = processor.getParseTreeString();
        String hashString = getHashString(parseTreeString);

        assertEquals(expected, hashString);
    }

    private String getHashString(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("sha-1");
        byte[] hash = messageDigest.digest(s.getBytes(StandardCharsets.UTF_8));
        String hashString = Base64.getEncoder().encodeToString(hash);
        return hashString;
    }
}