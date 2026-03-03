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

package jplus.base;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectTest {

    @Test
    void setJdkHome() {
        Project project = new Project(Path.of("src"));
        project.setJdkHome("/opt/jdk-21");

        assertEquals("/opt/jdk-21", project.getJdkHome());
    }

    @Test
    void getJdkHome() {
        Project project = new Project(Path.of("src"));

        assertNull(project.getJdkHome());

        project.setJdkHome("C:/jdk");
        assertEquals("C:/jdk", project.getJdkHome());
    }

    @Test
    void getSourceDirs() {
        Path src1 = Path.of("src1");
        Path src2 = Path.of("src2");

        Project project = new Project(List.of(src1, src2));

        List<Path> dirs = project.getSourceDirs();

        assertEquals(2, dirs.size());
        assertEquals(src1, dirs.get(0));
        assertEquals(src2, dirs.get(1));
    }

    @Test
    void getSrcDir() {
        Path src1 = Path.of("src1");
        Path src2 = Path.of("src2");

        Project project = new Project(List.of(src1, src2));

        assertEquals(src1, project.getSrcDir());
    }

    @Test
    void withSourceDir() {
        Path src1 = Path.of("src1");
        Path src2 = Path.of("src2");

        Project original = new Project(src1);
        Project modified = original.withSourceDir(src2);

        // 원본은 변경되지 않음
        assertEquals(1, original.getSourceDirs().size());

        // 새 객체에는 추가됨
        assertEquals(2, modified.getSourceDirs().size());
        assertEquals(src2, modified.getSourceDirs().get(1));
    }

    @Test
    void buildJavaSourcePath() {
        Path src1 = Path.of("src1");
        Path src2 = Path.of("src2");

        Project project = new Project(List.of(src1, src2));

        String expected = src1.toAbsolutePath() +
                File.pathSeparator +
                src2.toAbsolutePath();

        assertEquals(expected, project.buildJavaSourcePath());
    }

    @Test
    void getJavaClassPath() {
        Path src = Path.of("src");
        Path cp1 = Path.of("lib/a.jar");
        Path cp2 = Path.of("lib/b.jar");

        Project project = new Project(List.of(src), List.of(cp1, cp2));

        List<Path> cp = project.getJavaClassPath();

        assertEquals(2, cp.size());
        assertEquals(cp1, cp.get(0));
        assertEquals(cp2, cp.get(1));
    }

    @Test
    void withJavaClassPathEntry() {
        Path src = Path.of("src");
        Path cp1 = Path.of("lib/a.jar");

        Project original = new Project(src);
        Project modified = original.withJavaClassPathEntry(cp1);

        // 원본은 변경되지 않음
        assertTrue(original.getJavaClassPath().isEmpty());

        // 새 객체에만 추가됨
        assertEquals(1, modified.getJavaClassPath().size());
        assertEquals(cp1, modified.getJavaClassPath().get(0));
    }

    @Test
    void buildJavaClassPath() {
        Path src = Path.of("src");
        Path cp1 = Path.of("lib/a.jar");
        Path cp2 = Path.of("lib/b.jar");

        Project project = new Project(List.of(src), List.of(cp1, cp2));

        String expected = cp1.toAbsolutePath() +
                File.pathSeparator +
                cp2.toAbsolutePath();

        assertEquals(expected, project.buildJavaClassPath());
    }
}