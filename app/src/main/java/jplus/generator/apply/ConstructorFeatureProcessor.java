/*
 * Copyright 2025 Cheol Jeon <nieuwmijnleven@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jplus.generator.apply;

import jplus.base.Modifier;
import jplus.base.SymbolInfo;
import jplus.base.SymbolTable;
import jplus.base.TypeInfo;
import jplus.generator.TextChangeRange;
import jplus.util.CodeGenUtils;
import jplus.util.CodeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConstructorFeatureProcessor implements ApplyFeatureProcessor {
    @Override
    public void process(ApplyFeatureProcessingContext context) {
        List<String> argumentList = context.getFeature().getArgumentList();
        if (argumentList.isEmpty()) {
            throw new IllegalArgumentException("constructor must be used with required, all and no arguments");
        }

        for (String argument : argumentList) {
            if ("no".equalsIgnoreCase(argument)) {
                processNoArgsConsturctor(context);
            } else if ("all".equalsIgnoreCase(argument)) {
                processAllArgsConstructor(context);
            } else if ("required".equalsIgnoreCase(argument)) {
                processRequiredArgsConstructor(context);
            } else {
                throw new IllegalArgumentException(argument + "is not valid");
            }
        }
    }

    private void processNoArgsConsturctor(ApplyFeatureProcessingContext context) {
        if (context.hasProcessed("constructor(no)")) return;

        String className = context.getTargetClass();
        SymbolTable classSymbolTable = context.getClassSymbolTable();
        System.err.println("[processNoArgsConsturctor] classSymbolTable = " + classSymbolTable);
        String constructorName = "^constructor$_";
        if (classSymbolTable.resolveInCurrent(constructorName) != null) {
            System.err.println("default constructor already exists.");
            return;
        }

        TypeInfo typeInfo = new TypeInfo(constructorName, false, TypeInfo.Type.Constructor);
        SymbolInfo constructorSymInfo =
                SymbolInfo.builder()
                        .symbol(constructorName)
                        .typeInfo(typeInfo)
                        .modifierList(List.of())
                        .build();

        classSymbolTable.declare(constructorName, constructorSymInfo);

        List<String> fieldList = context.getFieldList();
        if (fieldList.isEmpty()) {
            System.err.println("fieldList is empty");
            return;
        }

        boolean hasFinalField = fieldList.stream().map(classSymbolTable::resolve).anyMatch(symbolInfo -> symbolInfo.getModifierList().contains(Modifier.FINAL));
        if (hasFinalField) {
            System.err.println("final field exists");
            return;
        }

        String constructor = "\n\n" + "public " + className + "() {}";
        context.appendConstructorPartText(constructor);
        context.addProcessedAction("constructor(no)");
    }

    public void processAllArgsConstructor(ApplyFeatureProcessingContext context) {
        if (context.hasProcessed("constructor(all)")) return;

        List<String> fieldList = context.getFieldList();
        if (fieldList.isEmpty()) return;

        SymbolTable classSymbolTable = context.getClassSymbolTable();
        List<SymbolInfo> nonStaticFieldList = fieldList.stream().map(classSymbolTable::resolve).filter(symbolInfo -> !symbolInfo.getModifierList().contains(Modifier.STATIC)).toList();
        if (nonStaticFieldList.isEmpty()) return;

        String nonoStaticTypeNameJoining = nonStaticFieldList.stream().map(SymbolInfo::getTypeInfo).map(TypeInfo::getName).collect(Collectors.joining("_"));
        String constructorName = "^constructor$_" + nonoStaticTypeNameJoining;
        if (classSymbolTable.resolveInCurrent(constructorName) != null) {
            return;
        }

        TypeInfo typeInfo = new TypeInfo(constructorName, false, TypeInfo.Type.Constructor);
        SymbolInfo constructorSymInfo =
                SymbolInfo.builder()
                        .symbol(constructorName)
                        .typeInfo(typeInfo)
                        .modifierList(List.of())
                        .build();

        classSymbolTable.declare(constructorName, constructorSymInfo);

        String indentation = context.getIndentation();

        String className = context.getTargetClass();
        String constructor = "\n\n" + "public " + className + "(";

        List<String> parameters = new ArrayList<>();
        for (SymbolInfo symbolInfo : nonStaticFieldList) {
            String simpleTypeName = CodeGenUtils.getSimpleTypeName(symbolInfo.getTypeInfo());
            String fieldName = symbolInfo.getSymbol();
            parameters.add(simpleTypeName + " " + fieldName);
        }
        constructor += String.join(", ", parameters);
        constructor += ") {\n";

        List<String> assignments = new ArrayList<>();
        for (SymbolInfo symbolInfo : nonStaticFieldList) {
            String fieldName = symbolInfo.getSymbol();
            assignments.add(indentation + "this." + fieldName + " = " + fieldName + ";");
        }
        constructor += String.join("\n", assignments);
        constructor += "\n}";

        context.appendConstructorPartText(constructor);
        context.addProcessedAction("constructor(all)");
    }

    public void processRequiredArgsConstructor(ApplyFeatureProcessingContext context) {
        if (context.hasProcessed("constructor(required)")) return;

        List<String> fieldList = context.getFieldList();
        if (fieldList.isEmpty()) return;

        SymbolTable classSymbolTable = context.getClassSymbolTable();
        List<SymbolInfo> nonStaticFieldList = fieldList.stream().map(classSymbolTable::resolve).filter(symbolInfo -> !symbolInfo.getModifierList().contains(Modifier.STATIC)).toList();
        if (nonStaticFieldList.isEmpty()) return;

        List<SymbolInfo> requiredFieldList = new ArrayList<>();
        for (SymbolInfo symbolInfo : nonStaticFieldList) {
            List<Modifier> modifierList = symbolInfo.getModifierList();
            if (modifierList != null && modifierList.contains(Modifier.FINAL)) {
                requiredFieldList.add(symbolInfo);
            }
        }

        if (requiredFieldList.isEmpty()) return;

        String requiredTypeNameJoining = requiredFieldList.stream().map(SymbolInfo::getTypeInfo).map(TypeInfo::getName).collect(Collectors.joining("_"));
        String constructorName = "^constructor$_" + requiredTypeNameJoining;
        System.out.println("classSymbolTable = " + classSymbolTable);
        System.out.println("constructorName = " + constructorName);
        if (classSymbolTable.resolveInCurrent(constructorName) != null) {
            return;
        }

        TypeInfo typeInfo = new TypeInfo(constructorName, false, TypeInfo.Type.Constructor);
        SymbolInfo constructorSymInfo =
                SymbolInfo.builder()
                        .symbol(constructorName)
                        .typeInfo(typeInfo)
                        .modifierList(List.of())
                        .build();

        classSymbolTable.declare(constructorName, constructorSymInfo);

        String indentation = context.getIndentation();

        String className = context.getTargetClass();
        String constructor = "\n\n" + "public " + className + "(";

        List<String> parameters = new ArrayList<>();
        for (SymbolInfo symInfo : requiredFieldList) {
            String simpleTypeName = CodeGenUtils.getSimpleTypeName(symInfo.getTypeInfo());
            String fieldName = symInfo.getSymbol();
            parameters.add(simpleTypeName + " " + fieldName);
        }
        constructor += String.join(", ", parameters);
        constructor += ") {\n";

        List<String> assignments = new ArrayList<>();
        for (SymbolInfo symInfo : requiredFieldList) {
            String fieldName = symInfo.getSymbol();
            assignments.add(indentation + "this." + fieldName + " = " + fieldName + ";");
        }
        constructor += String.join("\n", assignments);
        constructor += "\n}";

        context.appendConstructorPartText(constructor);
        context.addProcessedAction("constructor(required)");
    }
}
