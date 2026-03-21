// Generated from JADExParser.g4 by ANTLR 4.12.0
package jplus.base;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JADExParser}.
 */
public interface JADExParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JADExParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(JADExParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(JADExParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#modularCompulationUnit}.
	 * @param ctx the parse tree
	 */
	void enterModularCompulationUnit(JADExParser.ModularCompulationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#modularCompulationUnit}.
	 * @param ctx the parse tree
	 */
	void exitModularCompulationUnit(JADExParser.ModularCompulationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPackageDeclaration(JADExParser.PackageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPackageDeclaration(JADExParser.PackageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterImportDeclaration(JADExParser.ImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitImportDeclaration(JADExParser.ImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#applyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterApplyDeclaration(JADExParser.ApplyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#applyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitApplyDeclaration(JADExParser.ApplyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#applyStatement}.
	 * @param ctx the parse tree
	 */
	void enterApplyStatement(JADExParser.ApplyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#applyStatement}.
	 * @param ctx the parse tree
	 */
	void exitApplyStatement(JADExParser.ApplyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#applyBlock}.
	 * @param ctx the parse tree
	 */
	void enterApplyBlock(JADExParser.ApplyBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#applyBlock}.
	 * @param ctx the parse tree
	 */
	void exitApplyBlock(JADExParser.ApplyBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#applyFeatureList}.
	 * @param ctx the parse tree
	 */
	void enterApplyFeatureList(JADExParser.ApplyFeatureListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#applyFeatureList}.
	 * @param ctx the parse tree
	 */
	void exitApplyFeatureList(JADExParser.ApplyFeatureListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#applyFeature}.
	 * @param ctx the parse tree
	 */
	void enterApplyFeature(JADExParser.ApplyFeatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#applyFeature}.
	 * @param ctx the parse tree
	 */
	void exitApplyFeature(JADExParser.ApplyFeatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#applyFeatureArgs}.
	 * @param ctx the parse tree
	 */
	void enterApplyFeatureArgs(JADExParser.ApplyFeatureArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#applyFeatureArgs}.
	 * @param ctx the parse tree
	 */
	void exitApplyFeatureArgs(JADExParser.ApplyFeatureArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#applyBlockEntry}.
	 * @param ctx the parse tree
	 */
	void enterApplyBlockEntry(JADExParser.ApplyBlockEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#applyBlockEntry}.
	 * @param ctx the parse tree
	 */
	void exitApplyBlockEntry(JADExParser.ApplyBlockEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(JADExParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(JADExParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(JADExParser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(JADExParser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#classOrInterfaceModifier}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceModifier(JADExParser.ClassOrInterfaceModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#classOrInterfaceModifier}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceModifier(JADExParser.ClassOrInterfaceModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void enterVariableModifier(JADExParser.VariableModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void exitVariableModifier(JADExParser.VariableModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(JADExParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(JADExParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameters(JADExParser.TypeParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameters(JADExParser.TypeParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameter(JADExParser.TypeParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameter(JADExParser.TypeParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#typeBound}.
	 * @param ctx the parse tree
	 */
	void enterTypeBound(JADExParser.TypeBoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#typeBound}.
	 * @param ctx the parse tree
	 */
	void exitTypeBound(JADExParser.TypeBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterEnumDeclaration(JADExParser.EnumDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitEnumDeclaration(JADExParser.EnumDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#enumConstants}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstants(JADExParser.EnumConstantsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#enumConstants}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstants(JADExParser.EnumConstantsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#enumConstant}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstant(JADExParser.EnumConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#enumConstant}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstant(JADExParser.EnumConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterEnumBodyDeclarations(JADExParser.EnumBodyDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitEnumBodyDeclarations(JADExParser.EnumBodyDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDeclaration(JADExParser.InterfaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDeclaration(JADExParser.InterfaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(JADExParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(JADExParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBody(JADExParser.InterfaceBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBody(JADExParser.InterfaceBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyDeclaration(JADExParser.ClassBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyDeclaration(JADExParser.ClassBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclaration(JADExParser.MemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclaration(JADExParser.MemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(JADExParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(JADExParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(JADExParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(JADExParser.MethodBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#typeTypeOrVoid}.
	 * @param ctx the parse tree
	 */
	void enterTypeTypeOrVoid(JADExParser.TypeTypeOrVoidContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#typeTypeOrVoid}.
	 * @param ctx the parse tree
	 */
	void exitTypeTypeOrVoid(JADExParser.TypeTypeOrVoidContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#genericMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericMethodDeclaration(JADExParser.GenericMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#genericMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericMethodDeclaration(JADExParser.GenericMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#genericConstructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericConstructorDeclaration(JADExParser.GenericConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#genericConstructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericConstructorDeclaration(JADExParser.GenericConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(JADExParser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(JADExParser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#compactConstructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterCompactConstructorDeclaration(JADExParser.CompactConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#compactConstructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitCompactConstructorDeclaration(JADExParser.CompactConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(JADExParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(JADExParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBodyDeclaration(JADExParser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBodyDeclaration(JADExParser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMemberDeclaration(JADExParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMemberDeclaration(JADExParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#constDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstDeclaration(JADExParser.ConstDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#constDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstDeclaration(JADExParser.ConstDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclarator(JADExParser.ConstantDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclarator(JADExParser.ConstantDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#interfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodDeclaration(JADExParser.InterfaceMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#interfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodDeclaration(JADExParser.InterfaceMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#interfaceMethodModifier}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodModifier(JADExParser.InterfaceMethodModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#interfaceMethodModifier}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodModifier(JADExParser.InterfaceMethodModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#genericInterfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericInterfaceMethodDeclaration(JADExParser.GenericInterfaceMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#genericInterfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericInterfaceMethodDeclaration(JADExParser.GenericInterfaceMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#interfaceCommonBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceCommonBodyDeclaration(JADExParser.InterfaceCommonBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#interfaceCommonBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceCommonBodyDeclaration(JADExParser.InterfaceCommonBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarators(JADExParser.VariableDeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarators(JADExParser.VariableDeclaratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(JADExParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(JADExParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorId(JADExParser.VariableDeclaratorIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorId(JADExParser.VariableDeclaratorIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializer(JADExParser.VariableInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializer(JADExParser.VariableInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(JADExParser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(JADExParser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#classType}.
	 * @param ctx the parse tree
	 */
	void enterClassType(JADExParser.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#classType}.
	 * @param ctx the parse tree
	 */
	void exitClassType(JADExParser.ClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#packageName}.
	 * @param ctx the parse tree
	 */
	void enterPackageName(JADExParser.PackageNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#packageName}.
	 * @param ctx the parse tree
	 */
	void exitPackageName(JADExParser.PackageNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgument(JADExParser.TypeArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgument(JADExParser.TypeArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#qualifiedNameList}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedNameList(JADExParser.QualifiedNameListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#qualifiedNameList}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedNameList(JADExParser.QualifiedNameListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(JADExParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(JADExParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#receiverParameter}.
	 * @param ctx the parse tree
	 */
	void enterReceiverParameter(JADExParser.ReceiverParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#receiverParameter}.
	 * @param ctx the parse tree
	 */
	void exitReceiverParameter(JADExParser.ReceiverParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterList(JADExParser.FormalParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterList(JADExParser.FormalParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(JADExParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(JADExParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#lambdaLVTIList}.
	 * @param ctx the parse tree
	 */
	void enterLambdaLVTIList(JADExParser.LambdaLVTIListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#lambdaLVTIList}.
	 * @param ctx the parse tree
	 */
	void exitLambdaLVTIList(JADExParser.LambdaLVTIListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#lambdaLVTIParameter}.
	 * @param ctx the parse tree
	 */
	void enterLambdaLVTIParameter(JADExParser.LambdaLVTIParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#lambdaLVTIParameter}.
	 * @param ctx the parse tree
	 */
	void exitLambdaLVTIParameter(JADExParser.LambdaLVTIParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(JADExParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(JADExParser.QualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(JADExParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(JADExParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(JADExParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(JADExParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#floatLiteral}.
	 * @param ctx the parse tree
	 */
	void enterFloatLiteral(JADExParser.FloatLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#floatLiteral}.
	 * @param ctx the parse tree
	 */
	void exitFloatLiteral(JADExParser.FloatLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#altAnnotationQualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterAltAnnotationQualifiedName(JADExParser.AltAnnotationQualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#altAnnotationQualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitAltAnnotationQualifiedName(JADExParser.AltAnnotationQualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(JADExParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(JADExParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#annotationFieldValues}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationFieldValues(JADExParser.AnnotationFieldValuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#annotationFieldValues}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationFieldValues(JADExParser.AnnotationFieldValuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#annotationFieldValue}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationFieldValue(JADExParser.AnnotationFieldValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#annotationFieldValue}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationFieldValue(JADExParser.AnnotationFieldValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#annotationValue}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationValue(JADExParser.AnnotationValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#annotationValue}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationValue(JADExParser.AnnotationValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void enterElementValue(JADExParser.ElementValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void exitElementValue(JADExParser.ElementValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterElementValueArrayInitializer(JADExParser.ElementValueArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitElementValueArrayInitializer(JADExParser.ElementValueArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeDeclaration(JADExParser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeDeclaration(JADExParser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#annotationTypeBody}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeBody(JADExParser.AnnotationTypeBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#annotationTypeBody}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeBody(JADExParser.AnnotationTypeBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#annotationTypeElementDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeElementDeclaration(JADExParser.AnnotationTypeElementDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#annotationTypeElementDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeElementDeclaration(JADExParser.AnnotationTypeElementDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#annotationTypeElementRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeElementRest(JADExParser.AnnotationTypeElementRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#annotationTypeElementRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeElementRest(JADExParser.AnnotationTypeElementRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#annotationMethodOrConstantRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationMethodOrConstantRest(JADExParser.AnnotationMethodOrConstantRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#annotationMethodOrConstantRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationMethodOrConstantRest(JADExParser.AnnotationMethodOrConstantRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#annotationMethodRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationMethodRest(JADExParser.AnnotationMethodRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#annotationMethodRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationMethodRest(JADExParser.AnnotationMethodRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#annotationConstantRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationConstantRest(JADExParser.AnnotationConstantRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#annotationConstantRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationConstantRest(JADExParser.AnnotationConstantRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(JADExParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(JADExParser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#moduleDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterModuleDeclaration(JADExParser.ModuleDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#moduleDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitModuleDeclaration(JADExParser.ModuleDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#moduleDirective}.
	 * @param ctx the parse tree
	 */
	void enterModuleDirective(JADExParser.ModuleDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#moduleDirective}.
	 * @param ctx the parse tree
	 */
	void exitModuleDirective(JADExParser.ModuleDirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#requiresModifier}.
	 * @param ctx the parse tree
	 */
	void enterRequiresModifier(JADExParser.RequiresModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#requiresModifier}.
	 * @param ctx the parse tree
	 */
	void exitRequiresModifier(JADExParser.RequiresModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#recordDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterRecordDeclaration(JADExParser.RecordDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#recordDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitRecordDeclaration(JADExParser.RecordDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#recordHeader}.
	 * @param ctx the parse tree
	 */
	void enterRecordHeader(JADExParser.RecordHeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#recordHeader}.
	 * @param ctx the parse tree
	 */
	void exitRecordHeader(JADExParser.RecordHeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#recordComponentList}.
	 * @param ctx the parse tree
	 */
	void enterRecordComponentList(JADExParser.RecordComponentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#recordComponentList}.
	 * @param ctx the parse tree
	 */
	void exitRecordComponentList(JADExParser.RecordComponentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#recordComponent}.
	 * @param ctx the parse tree
	 */
	void enterRecordComponent(JADExParser.RecordComponentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#recordComponent}.
	 * @param ctx the parse tree
	 */
	void exitRecordComponent(JADExParser.RecordComponentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#recordBody}.
	 * @param ctx the parse tree
	 */
	void enterRecordBody(JADExParser.RecordBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#recordBody}.
	 * @param ctx the parse tree
	 */
	void exitRecordBody(JADExParser.RecordBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(JADExParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(JADExParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(JADExParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(JADExParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclaration(JADExParser.LocalVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclaration(JADExParser.LocalVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(JADExParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(JADExParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#typeIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeIdentifier(JADExParser.TypeIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#typeIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeIdentifier(JADExParser.TypeIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#localTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalTypeDeclaration(JADExParser.LocalTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#localTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalTypeDeclaration(JADExParser.LocalTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(JADExParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(JADExParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void enterCatchClause(JADExParser.CatchClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void exitCatchClause(JADExParser.CatchClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#catchType}.
	 * @param ctx the parse tree
	 */
	void enterCatchType(JADExParser.CatchTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#catchType}.
	 * @param ctx the parse tree
	 */
	void exitCatchType(JADExParser.CatchTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#finallyBlock}.
	 * @param ctx the parse tree
	 */
	void enterFinallyBlock(JADExParser.FinallyBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#finallyBlock}.
	 * @param ctx the parse tree
	 */
	void exitFinallyBlock(JADExParser.FinallyBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#resourceSpecification}.
	 * @param ctx the parse tree
	 */
	void enterResourceSpecification(JADExParser.ResourceSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#resourceSpecification}.
	 * @param ctx the parse tree
	 */
	void exitResourceSpecification(JADExParser.ResourceSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#resources}.
	 * @param ctx the parse tree
	 */
	void enterResources(JADExParser.ResourcesContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#resources}.
	 * @param ctx the parse tree
	 */
	void exitResources(JADExParser.ResourcesContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#resource}.
	 * @param ctx the parse tree
	 */
	void enterResource(JADExParser.ResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#resource}.
	 * @param ctx the parse tree
	 */
	void exitResource(JADExParser.ResourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroup(JADExParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroup(JADExParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabel(JADExParser.SwitchLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabel(JADExParser.SwitchLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#forControl}.
	 * @param ctx the parse tree
	 */
	void enterForControl(JADExParser.ForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#forControl}.
	 * @param ctx the parse tree
	 */
	void exitForControl(JADExParser.ForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(JADExParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(JADExParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void enterEnhancedForControl(JADExParser.EnhancedForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void exitEnhancedForControl(JADExParser.EnhancedForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(JADExParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(JADExParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(JADExParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(JADExParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTernaryExpression(JADExParser.TernaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTernaryExpression(JADExParser.TernaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstanceOfOperatorExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInstanceOfOperatorExpression(JADExParser.InstanceOfOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstanceOfOperatorExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInstanceOfOperatorExpression(JADExParser.InstanceOfOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryOperatorExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperatorExpression(JADExParser.UnaryOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryOperatorExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperatorExpression(JADExParser.UnaryOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(JADExParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(JADExParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ObjectCreationExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterObjectCreationExpression(JADExParser.ObjectCreationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ObjectCreationExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitObjectCreationExpression(JADExParser.ObjectCreationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionLambda}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionLambda(JADExParser.ExpressionLambdaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionLambda}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionLambda(JADExParser.ExpressionLambdaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostIncrementDecrementOperatorExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPostIncrementDecrementOperatorExpression(JADExParser.PostIncrementDecrementOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostIncrementDecrementOperatorExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPostIncrementDecrementOperatorExpression(JADExParser.PostIncrementDecrementOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ElvisExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterElvisExpression(JADExParser.ElvisExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ElvisExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitElvisExpression(JADExParser.ElvisExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberReferenceExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMemberReferenceExpression(JADExParser.MemberReferenceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberReferenceExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMemberReferenceExpression(JADExParser.MemberReferenceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionSwitch}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionSwitch(JADExParser.ExpressionSwitchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionSwitch}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionSwitch(JADExParser.ExpressionSwitchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryOperatorExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOperatorExpression(JADExParser.BinaryOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryOperatorExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOperatorExpression(JADExParser.BinaryOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodCallExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallExpression(JADExParser.MethodCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodCallExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallExpression(JADExParser.MethodCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodReferenceExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMethodReferenceExpression(JADExParser.MethodReferenceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodReferenceExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMethodReferenceExpression(JADExParser.MethodReferenceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SquareBracketExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSquareBracketExpression(JADExParser.SquareBracketExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SquareBracketExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSquareBracketExpression(JADExParser.SquareBracketExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CastExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(JADExParser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CastExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(JADExParser.CastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(JADExParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(JADExParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#componentPatternList}.
	 * @param ctx the parse tree
	 */
	void enterComponentPatternList(JADExParser.ComponentPatternListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#componentPatternList}.
	 * @param ctx the parse tree
	 */
	void exitComponentPatternList(JADExParser.ComponentPatternListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#componentPattern}.
	 * @param ctx the parse tree
	 */
	void enterComponentPattern(JADExParser.ComponentPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#componentPattern}.
	 * @param ctx the parse tree
	 */
	void exitComponentPattern(JADExParser.ComponentPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpression(JADExParser.LambdaExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpression(JADExParser.LambdaExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#lambdaParameters}.
	 * @param ctx the parse tree
	 */
	void enterLambdaParameters(JADExParser.LambdaParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#lambdaParameters}.
	 * @param ctx the parse tree
	 */
	void exitLambdaParameters(JADExParser.LambdaParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#lambdaBody}.
	 * @param ctx the parse tree
	 */
	void enterLambdaBody(JADExParser.LambdaBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#lambdaBody}.
	 * @param ctx the parse tree
	 */
	void exitLambdaBody(JADExParser.LambdaBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(JADExParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(JADExParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#switchExpression}.
	 * @param ctx the parse tree
	 */
	void enterSwitchExpression(JADExParser.SwitchExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#switchExpression}.
	 * @param ctx the parse tree
	 */
	void exitSwitchExpression(JADExParser.SwitchExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#switchLabeledRule}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabeledRule(JADExParser.SwitchLabeledRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#switchLabeledRule}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabeledRule(JADExParser.SwitchLabeledRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#guard}.
	 * @param ctx the parse tree
	 */
	void enterGuard(JADExParser.GuardContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#guard}.
	 * @param ctx the parse tree
	 */
	void exitGuard(JADExParser.GuardContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#casePattern}.
	 * @param ctx the parse tree
	 */
	void enterCasePattern(JADExParser.CasePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#casePattern}.
	 * @param ctx the parse tree
	 */
	void exitCasePattern(JADExParser.CasePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#switchRuleOutcome}.
	 * @param ctx the parse tree
	 */
	void enterSwitchRuleOutcome(JADExParser.SwitchRuleOutcomeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#switchRuleOutcome}.
	 * @param ctx the parse tree
	 */
	void exitSwitchRuleOutcome(JADExParser.SwitchRuleOutcomeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceType(JADExParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceType(JADExParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterCreator(JADExParser.CreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitCreator(JADExParser.CreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#createdName}.
	 * @param ctx the parse tree
	 */
	void enterCreatedName(JADExParser.CreatedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#createdName}.
	 * @param ctx the parse tree
	 */
	void exitCreatedName(JADExParser.CreatedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#innerCreator}.
	 * @param ctx the parse tree
	 */
	void enterInnerCreator(JADExParser.InnerCreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#innerCreator}.
	 * @param ctx the parse tree
	 */
	void exitInnerCreator(JADExParser.InnerCreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreatorRest(JADExParser.ArrayCreatorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreatorRest(JADExParser.ArrayCreatorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void enterClassCreatorRest(JADExParser.ClassCreatorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void exitClassCreatorRest(JADExParser.ClassCreatorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#explicitGenericInvocation}.
	 * @param ctx the parse tree
	 */
	void enterExplicitGenericInvocation(JADExParser.ExplicitGenericInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#explicitGenericInvocation}.
	 * @param ctx the parse tree
	 */
	void exitExplicitGenericInvocation(JADExParser.ExplicitGenericInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgumentsOrDiamond(JADExParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgumentsOrDiamond(JADExParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#nonWildcardTypeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void enterNonWildcardTypeArgumentsOrDiamond(JADExParser.NonWildcardTypeArgumentsOrDiamondContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#nonWildcardTypeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void exitNonWildcardTypeArgumentsOrDiamond(JADExParser.NonWildcardTypeArgumentsOrDiamondContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#nonWildcardTypeArguments}.
	 * @param ctx the parse tree
	 */
	void enterNonWildcardTypeArguments(JADExParser.NonWildcardTypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#nonWildcardTypeArguments}.
	 * @param ctx the parse tree
	 */
	void exitNonWildcardTypeArguments(JADExParser.NonWildcardTypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#typeList}.
	 * @param ctx the parse tree
	 */
	void enterTypeList(JADExParser.TypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#typeList}.
	 * @param ctx the parse tree
	 */
	void exitTypeList(JADExParser.TypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#typeType}.
	 * @param ctx the parse tree
	 */
	void enterTypeType(JADExParser.TypeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#typeType}.
	 * @param ctx the parse tree
	 */
	void exitTypeType(JADExParser.TypeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(JADExParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(JADExParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void enterTypeArguments(JADExParser.TypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void exitTypeArguments(JADExParser.TypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#superSuffix}.
	 * @param ctx the parse tree
	 */
	void enterSuperSuffix(JADExParser.SuperSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#superSuffix}.
	 * @param ctx the parse tree
	 */
	void exitSuperSuffix(JADExParser.SuperSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#explicitGenericInvocationSuffix}.
	 * @param ctx the parse tree
	 */
	void enterExplicitGenericInvocationSuffix(JADExParser.ExplicitGenericInvocationSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#explicitGenericInvocationSuffix}.
	 * @param ctx the parse tree
	 */
	void exitExplicitGenericInvocationSuffix(JADExParser.ExplicitGenericInvocationSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link JADExParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(JADExParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JADExParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(JADExParser.ArgumentsContext ctx);
}