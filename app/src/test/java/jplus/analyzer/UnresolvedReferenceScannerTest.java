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

package jplus.analyzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import jplus.analyzer.UnresolvedReferenceScanner;
import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.base.TypeInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnresolvedReferenceScannerTest {

    @Test
    void findUnresolvedReference() {
        // unresolved 타입과 resolved 타입 심볼 생성
        SymbolInfo unresolved = new SymbolInfo("MyClass", new TypeInfo("MyClass", false, TypeInfo.Type.Unknown), null, null, List.of());
        SymbolInfo resolved = new SymbolInfo("ResolvedClass", new TypeInfo("ResolvedClass", false, TypeInfo.Type.Class), null, null, List.of());

        SymbolTable table = new SymbolTable(null);
        table.declare(
                "^PackageName$",
                SymbolInfo.builder()
                        .symbol("jadex.example")
                        .typeInfo(new TypeInfo("package", false, TypeInfo.Type.Void))
                        .build()
        );

        table.declare(unresolved.getSymbol(), unresolved);
        table.declare(resolved.getSymbol(), resolved);

        UnresolvedReferenceScanner scanner = new UnresolvedReferenceScanner(table);

        List<UnresolvedReferenceScanner.UnresolvedReferenceInfo> result = scanner.findUnresolvedReference();

        // 미해결 참조만 포함되는지 확인
        assertEquals(1, result.size());
        UnresolvedReferenceScanner.UnresolvedReferenceInfo info = result.getFirst();
        assertEquals("MyClass", info.className);
        assertEquals("jadex.example", info.packageName);
        assertEquals("jadex.example.MyClass", info.getFullyQualifiedName());
    }
}