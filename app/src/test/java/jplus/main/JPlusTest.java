/*
 * JADEx - Java Advanced Development Extension
 *
 * Copyright (C) 2026 Cheol Jeon <nieuwmijnleven@outlook.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 only,
 * as published by the Free Software Foundation.
 *
 * Alternatively, this software may be used under a commercial license
 * from Cheol Jeon.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License version 2 for more details:
 * <https://www.gnu.org/licenses/old-licenses/gpl-2.0.html>.
 *
 * For commercial licensing, please contact <nieuwmijnleven@outlook.com>.
 *
 * Contributors to this project must sign a Contributor License Agreement (CLA)
 * granting Cheol Jeon the right to relicense their contributions under
 * a commercial license. See the CLA file in the project root for details.
 */

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

class JPlusTest {

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
    void testNullableType1() throws Exception {
        //JPlusProcessor processor = new JPlusProcessor(null, Path.of("./src/test/samples/NullableType1.jadex"));

        Project project = new Project(Path.of("./src/test/samples"));
        //JPlusProcessor processor = new JPlusProcessor(null, Path.of("./src/test/samples/NullabilityCheckerLocalVariable.jadex"));
        JPlusProcessor processor = new JPlusProcessor(project, "", "NullableType1");

        processor.process();
        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
            });
        }

        assertEquals("Error: (line:6, column:8) s2 is a non-nullable variable. But null value is assigned to it.\n", outContent.toString());
    }

    @Test
    void testNullableType2() throws Exception {
        //JPlusProcessor processor = new JPlusProcessor(null, Path.of("./src/test/samples/NullableType2.jadex"));

        Project project = new Project(Path.of("./src/test/samples"));
        //JPlusProcessor processor = new JPlusProcessor(null, Path.of("./src/test/samples/NullabilityCheckerLocalVariable.jadex"));
        JPlusProcessor processor = new JPlusProcessor(project, "", "NullableType2");

        processor.process();
        processor.analyzeSymbols();

        System.err.println("[parseTreeString] " + processor.getParseTreeString());

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
            });
        }

        assertEquals("Error: (line:8, column:8) s1 is a nullable variable. But it directly accesses length(). Consider using null-safe operator(?.).\n", outContent.toString());
    }

    @Test
    void SafeNavigationPrimitiveRequiresElvis() throws Exception {
        //JPlusProcessor processor = new JPlusProcessor(null, Path.of("./src/test/samples/NullableType2.jadex"));

        Project project = new Project(Path.of("./src/test/samples"));
        //JPlusProcessor processor = new JPlusProcessor(null, Path.of("./src/test/samples/NullabilityCheckerLocalVariable.jadex"));
        JPlusProcessor processor = new JPlusProcessor(project, "", "SafeNavigationPrimitiveRequiresElvis");

        processor.process();
        processor.analyzeSymbols();

        //System.err.println("[parseTreeString] " + processor.getParseTreeString());

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
            });
        }

        assertEquals("Error: (line:5, column:8) Nullable result of safe access(?.) assigned to primitive type 'int' may cause NullPointerException due to auto-unboxing. Use '?:' to provide a default value.\n", outContent.toString());
    }

    @Test
    void testNullabilityChecker() throws Exception {
        //JPlusProcessor processor = new JPlusProcessor(null, Path.of("./src/test/samples/NullabilityChecker.jadex"));

        Project project = new Project(Path.of("./src/test/samples"));
        //JPlusProcessor processor = new JPlusProcessor(null, Path.of("./src/test/samples/NullabilityCheckerLocalVariable.jadex"));
        JPlusProcessor processor = new JPlusProcessor(project, "", "NullabilityChecker");

        processor.process();
        System.err.println("[ParseTreeString] " + processor.getParseTreeString());
        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
            });
        }

        String expected = "Error: (line:5, column:4) lastname is a non-nullable variable. But null value is assigned to it.\n" +
                "Error: (line:9, column:8) tokens is a non-nullable variable. But nullable value is assigned to it. Change the type to String[]? or add a null check.\n" +
                "Error: (line:9, column:26) fullname is a nullable variable. But it directly accesses split(). Consider using null-safe operator(?.).\n" +
                "Error: (line:13, column:8) lastname is a non-nullable variable. But null value is assigned to it.\n" +
                "Error: (line:14, column:8) this.lastname is a non-nullable variable. But null value is assigned to it.\n" +
                "Error: (line:15, column:8) User.this.lastname is a non-nullable variable. But null value is assigned to it.\n" +
                "Error: (line:19, column:15) firstname is a nullable variable. But it directly accesses length(). Consider using null-safe operator(?.).\n";

        assertEquals(expected, outContent.toString());
    }

    @Test
    void testNullabilityCheckerWithDataflow() throws Exception {
//        JPlusProcessor processor = new JPlusProcessor(null, Path.of("./src/test/samples/NullabilityCheckerWithDataflow.jadex"));

        Project project = new Project(Path.of("./src/test/samples"));
        //JPlusProcessor processor = new JPlusProcessor(null, Path.of("./src/test/samples/NullabilityCheckerLocalVariable.jadex"));
        JPlusProcessor processor = new JPlusProcessor(project, "", "NullabilityCheckerWithDataflow");

        processor.process();
//        System.err.println(processor.getParseTreeString());
        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
            });
        }

        String expected = "Error: (line:5, column:4) lastname is a non-nullable variable. But null value is assigned to it.\n" +
                "Error: (line:12, column:12) lastname is a non-nullable variable. But null value is assigned to it.\n" +
                "Error: (line:17, column:15) firstname is a nullable variable. But it directly accesses length(). Consider using null-safe operator(?.).\n";

        assertEquals(expected, outContent.toString());
    }

    @Test
    void testNullabilityCheckerWithAssignNullableToNonNullable() throws Exception {
//        JPlusProcessor processor = new JPlusProcessor(null, Path.of("./src/test/samples/NullabilityCheckerWithAssignNullableToNonNullable.jadex"));

        Project project = new Project(Path.of("./src/test/samples"));
        //JPlusProcessor processor = new JPlusProcessor(null, Path.of("./src/test/samples/NullabilityCheckerLocalVariable.jadex"));
        JPlusProcessor processor = new JPlusProcessor(project, "", "NullabilityCheckerWithAssignNullableToNonNullable");

        processor.process();
//        System.err.println(processor.getParseTreeString());
        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
            });
        }

        String expected = "Error: (line:4, column:4) Non-null field 'name' is not initialized in one or more constructors of class 'User'\n" +
                "Error: (line:8, column:8) this.name is a non-nullable variable. But nullable value is assigned to it. Change the type to String? or add a null check.\n";
        assertEquals(expected, outContent.toString());
    }

    @Test
    void testNullabilityCheckerWithNonInitializeLocalVariable() throws Exception {
        Project project = new Project(Path.of("./src/test/samples"));
        //JPlusProcessor processor = new JPlusProcessor(null, Path.of("./src/test/samples/NullabilityCheckerLocalVariable.jadex"));
        JPlusProcessor processor = new JPlusProcessor(project, "", "NullabilityCheckerLocalVariable");

        processor.process();
//        System.err.println(processor.getParseTreeString());
        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            issues.forEach(nullabilityIssue -> {
                System.out.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.line(), nullabilityIssue.column(), nullabilityIssue.message());
//                System.err.printf("Error: (line:%d, column:%d) %s\n", nullabilityIssue.getLine(), nullabilityIssue.getColumn(), nullabilityIssue.getMessage());
            });
        }

        String expected = "Error: (line:18, column:8) The method(getCity) is declared to return a non-null value, but this return statement may return null.\n" +
                "Error: (line:18, column:15) address is a nullable variable. But it directly accesses city. Consider using null-safe operator(?.).\n" +
                "Error: (line:37, column:8) Variable 'nickName' might not have been initialized.\n" +
                "Error: (line:42, column:30) The 1st argument of the jplus.example.User constructor is a non-nullable variable, but a null value is assigned to it.\n" +
                "Error: (line:42, column:48) The 1st argument of the jplus.example.User.Address constructor is a non-nullable variable, but a null value is assigned to it.\n";
        assertEquals(expected, outContent.toString());
    }

    @Test
    void testNullsafeOperator() throws Exception {
        checkGeneratedCode("./src/test/samples/NullsafeOperator.jadex", "TijVyfgTW1Tdt22x80dz33gicb4=");
    }

    @Test
    void testNullsafeOperator2() throws Exception {
        checkGeneratedCode("./src/test/samples/NullsafeOperator2.jadex", "4Hv0pW9vIEHS3j6paNJ1VWDyzME=");
    }

    @Test
    void testElvisOperator() throws Exception {
        checkGeneratedCode("./src/test/samples/ElvisOperator.jadex", "AL0aBpZroiatomausu8e2frX7qw=");
    }

    @Test
    void testCascadingElvisOperator() throws Exception {
        checkGeneratedCode("./src/test/samples/CascadingElvisOperator.jadex", "9XsleZwkeCcBefEzroFgN8boNFg=");
    }

    @Test
    void testNullsafeWithElvisOperator() throws Exception {
        checkGeneratedCode("./src/test/samples/NullsafeWithElvisOperator.jadex", "aIGbfZc2w17DZm4D78925bSSl6c=");
    }

    @Test
    void testApplyGetter() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyGetter.jadex", "A5Gvl2lI+ydhH/ti0ZX2sxD+2uM=");
    }

    @Test
    void testApplySetter() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplySetter.jadex", "kIVUTpvDCm93X+eEZVuX8R6jkQU=");
    }

    @Test
    void testApplyEquals() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyEquals.jadex", "qOs8oK/I41ApIQFtURbTRDa+/9Y=");
    }

    @Test
    void testApplyEquality() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyEquality.jadex", "mK6ihnWZiJSw//iT1fSlnF2w2I4=");
    }

    @Test
    void testApplyData() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyData.jadex", "BIir59bo+/1XfgN6I8spb7q5gp0=");
    }

    @Test
    void testApplyToString() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyToString.jadex", "/QVzFS6fZls9182SmmZ16FNAtoU=");
    }

    @Test
    void testApplyHashCode() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyHashCode.jadex", "rbDR59oWQcHWpaEj4Dmde/ydr10=");
    }

    @Test
    void testApplyConstructorWithRequired() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyConstructorWithRequired.jadex", "/u7XMZXJ0+n790tyLYk89uwEP6E=");
    }

    @Test
    void testApplyConstructorWithAll() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyConstructorWithAll.jadex", "azvxsqhw78QejONm+KWMFo1VpUM=");
    }

    @Test
    void testApplyConstructorWithNo() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyConstructorWithNo.jadex", "kXck5+iZHAmz2b1B8AoTesPdNeM=");
    }

    @Test
    void testApplyConstructorWithNoInSingleClass() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyConstructorWithNoInSingleClass.jadex", "8CFaXm1HYqgyT3d3OJ0bZn3fO1Y=");
    }

    @Test
    void testApplyConstructorWithAllAndBuilder() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyConstructorWithAllAndBuilder.jadex", "+WXeToTgvwLQkY2Bm9ieZUP4+kE=");
    }

    @Test
    void testApplyConstructorWithRequiredAndBuilder() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyConstructorWithRequiredAndBuilder.jadex", "yY90RriomIajVnn6Ujrj+u0R3nQ=");
    }

    @Test
    void testApplyConstructorWithNoAndBuilder() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyConstructorWithNoAndBuilder.jadex", "+WXeToTgvwLQkY2Bm9ieZUP4+kE=");
    }

    @Test
    void testApplyDuplicatedHashCode() throws Exception {
        checkGeneratedCode("./src/test/samples/ApplyDuplicatedHashCode.jadex", "rbDR59oWQcHWpaEj4Dmde/ydr10=");
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
        //System.err.println("[parseTreeString] " + processor.getParseTreeString());

        processor.analyzeSymbols();

//        JPlusProcessor processor = new JPlusProcessor(Path.of(fileName));
//        processor.process();
////        System.err.println(processor.getParseTreeString());
//        processor.analyzeSymbols();

        var issues = processor.checkNullability();
        if (!issues.isEmpty()) {
            //fail();
        }

        String generatedJavaCode = processor.generateJavaCode();
        System.err.println("[BoilerplateCodeGeneration] = " + generatedJavaCode);

        processor = new JPlusProcessor(project, generatedJavaCode);
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

//    @Test
//    void testCodeGeneration() throws Exception {
//        JPlusProcessor processor = new JPlusProcessor(Path.of("./src/test/samples/TestExample.java"));
//        processor.process();
//        processor.analyzeSymbols();
//
//        var issues = processor.checkNullability();
//        if (!issues.isEmpty()) {
//            fail();
//        }
//
//        String parseTreeString = processor.getParseTreeString();
//        String generatedJavaCode = processor.generateJavaCode();
//
//        processor = new JPlusProcessor(generatedJavaCode);
//        processor.process();
//        String parseTreeStringOfGeneratedJavaCode = processor.getParseTreeString();
//
//        assertEquals(parseTreeString, parseTreeStringOfGeneratedJavaCode);
//    }
}