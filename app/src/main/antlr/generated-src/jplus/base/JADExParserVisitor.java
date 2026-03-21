// Generated from JADExParser.g4 by ANTLR 4.12.0
package jplus.base;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JADExParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JADExParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JADExParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(JADExParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#modularCompulationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModularCompulationUnit(JADExParser.ModularCompulationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#packageDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageDeclaration(JADExParser.PackageDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#importDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDeclaration(JADExParser.ImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#applyDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplyDeclaration(JADExParser.ApplyDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#applyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplyStatement(JADExParser.ApplyStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#applyBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplyBlock(JADExParser.ApplyBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#applyFeatureList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplyFeatureList(JADExParser.ApplyFeatureListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#applyFeature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplyFeature(JADExParser.ApplyFeatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#applyFeatureArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplyFeatureArgs(JADExParser.ApplyFeatureArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#applyBlockEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplyBlockEntry(JADExParser.ApplyBlockEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#typeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclaration(JADExParser.TypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifier(JADExParser.ModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#classOrInterfaceModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassOrInterfaceModifier(JADExParser.ClassOrInterfaceModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#variableModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableModifier(JADExParser.VariableModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(JADExParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#typeParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameters(JADExParser.TypeParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#typeParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameter(JADExParser.TypeParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#typeBound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeBound(JADExParser.TypeBoundContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#enumDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumDeclaration(JADExParser.EnumDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#enumConstants}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumConstants(JADExParser.EnumConstantsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#enumConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumConstant(JADExParser.EnumConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumBodyDeclarations(JADExParser.EnumBodyDeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceDeclaration(JADExParser.InterfaceDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(JADExParser.ClassBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#interfaceBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceBody(JADExParser.InterfaceBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBodyDeclaration(JADExParser.ClassBodyDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#memberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberDeclaration(JADExParser.MemberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(JADExParser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#methodBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodBody(JADExParser.MethodBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#typeTypeOrVoid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeTypeOrVoid(JADExParser.TypeTypeOrVoidContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#genericMethodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericMethodDeclaration(JADExParser.GenericMethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#genericConstructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericConstructorDeclaration(JADExParser.GenericConstructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDeclaration(JADExParser.ConstructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#compactConstructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompactConstructorDeclaration(JADExParser.CompactConstructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDeclaration(JADExParser.FieldDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceBodyDeclaration(JADExParser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceMemberDeclaration(JADExParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#constDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDeclaration(JADExParser.ConstDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#constantDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclarator(JADExParser.ConstantDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#interfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceMethodDeclaration(JADExParser.InterfaceMethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#interfaceMethodModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceMethodModifier(JADExParser.InterfaceMethodModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#genericInterfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericInterfaceMethodDeclaration(JADExParser.GenericInterfaceMethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#interfaceCommonBodyDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceCommonBodyDeclaration(JADExParser.InterfaceCommonBodyDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#variableDeclarators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarators(JADExParser.VariableDeclaratorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#variableDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarator(JADExParser.VariableDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaratorId(JADExParser.VariableDeclaratorIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#variableInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableInitializer(JADExParser.VariableInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#arrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializer(JADExParser.ArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#classType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassType(JADExParser.ClassTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#packageName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageName(JADExParser.PackageNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#typeArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArgument(JADExParser.TypeArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#qualifiedNameList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedNameList(JADExParser.QualifiedNameListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#formalParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameters(JADExParser.FormalParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#receiverParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReceiverParameter(JADExParser.ReceiverParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#formalParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterList(JADExParser.FormalParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#formalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameter(JADExParser.FormalParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#lambdaLVTIList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaLVTIList(JADExParser.LambdaLVTIListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#lambdaLVTIParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaLVTIParameter(JADExParser.LambdaLVTIParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#qualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedName(JADExParser.QualifiedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(JADExParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(JADExParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#floatLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatLiteral(JADExParser.FloatLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#altAnnotationQualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAltAnnotationQualifiedName(JADExParser.AltAnnotationQualifiedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(JADExParser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#annotationFieldValues}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationFieldValues(JADExParser.AnnotationFieldValuesContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#annotationFieldValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationFieldValue(JADExParser.AnnotationFieldValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#annotationValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationValue(JADExParser.AnnotationValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#elementValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValue(JADExParser.ElementValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValueArrayInitializer(JADExParser.ElementValueArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationTypeDeclaration(JADExParser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#annotationTypeBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationTypeBody(JADExParser.AnnotationTypeBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#annotationTypeElementDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationTypeElementDeclaration(JADExParser.AnnotationTypeElementDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#annotationTypeElementRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationTypeElementRest(JADExParser.AnnotationTypeElementRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#annotationMethodOrConstantRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationMethodOrConstantRest(JADExParser.AnnotationMethodOrConstantRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#annotationMethodRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationMethodRest(JADExParser.AnnotationMethodRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#annotationConstantRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationConstantRest(JADExParser.AnnotationConstantRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#defaultValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultValue(JADExParser.DefaultValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#moduleDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleDeclaration(JADExParser.ModuleDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#moduleDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleDirective(JADExParser.ModuleDirectiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#requiresModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequiresModifier(JADExParser.RequiresModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#recordDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordDeclaration(JADExParser.RecordDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#recordHeader}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordHeader(JADExParser.RecordHeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#recordComponentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordComponentList(JADExParser.RecordComponentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#recordComponent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordComponent(JADExParser.RecordComponentContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#recordBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordBody(JADExParser.RecordBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(JADExParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(JADExParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVariableDeclaration(JADExParser.LocalVariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(JADExParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#typeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeIdentifier(JADExParser.TypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#localTypeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalTypeDeclaration(JADExParser.LocalTypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(JADExParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#catchClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchClause(JADExParser.CatchClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#catchType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchType(JADExParser.CatchTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#finallyBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFinallyBlock(JADExParser.FinallyBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#resourceSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResourceSpecification(JADExParser.ResourceSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#resources}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResources(JADExParser.ResourcesContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#resource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResource(JADExParser.ResourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchBlockStatementGroup(JADExParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#switchLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchLabel(JADExParser.SwitchLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#forControl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForControl(JADExParser.ForControlContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(JADExParser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#enhancedForControl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnhancedForControl(JADExParser.EnhancedForControlContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(JADExParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(JADExParser.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryExpression(JADExParser.TernaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstanceOfOperatorExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstanceOfOperatorExpression(JADExParser.InstanceOfOperatorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryOperatorExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperatorExpression(JADExParser.UnaryOperatorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(JADExParser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ObjectCreationExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectCreationExpression(JADExParser.ObjectCreationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionLambda}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionLambda(JADExParser.ExpressionLambdaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostIncrementDecrementOperatorExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIncrementDecrementOperatorExpression(JADExParser.PostIncrementDecrementOperatorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ElvisExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElvisExpression(JADExParser.ElvisExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MemberReferenceExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberReferenceExpression(JADExParser.MemberReferenceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionSwitch}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionSwitch(JADExParser.ExpressionSwitchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryOperatorExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperatorExpression(JADExParser.BinaryOperatorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MethodCallExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCallExpression(JADExParser.MethodCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MethodReferenceExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodReferenceExpression(JADExParser.MethodReferenceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SquareBracketExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSquareBracketExpression(JADExParser.SquareBracketExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CastExpression}
	 * labeled alternative in {@link JADExParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpression(JADExParser.CastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(JADExParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#componentPatternList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComponentPatternList(JADExParser.ComponentPatternListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#componentPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComponentPattern(JADExParser.ComponentPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#lambdaExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExpression(JADExParser.LambdaExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#lambdaParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaParameters(JADExParser.LambdaParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#lambdaBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaBody(JADExParser.LambdaBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(JADExParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#switchExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchExpression(JADExParser.SwitchExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#switchLabeledRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchLabeledRule(JADExParser.SwitchLabeledRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#guard}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGuard(JADExParser.GuardContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#casePattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCasePattern(JADExParser.CasePatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#switchRuleOutcome}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchRuleOutcome(JADExParser.SwitchRuleOutcomeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassOrInterfaceType(JADExParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreator(JADExParser.CreatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#createdName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreatedName(JADExParser.CreatedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#innerCreator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInnerCreator(JADExParser.InnerCreatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreatorRest(JADExParser.ArrayCreatorRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#classCreatorRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassCreatorRest(JADExParser.ClassCreatorRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#explicitGenericInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplicitGenericInvocation(JADExParser.ExplicitGenericInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArgumentsOrDiamond(JADExParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#nonWildcardTypeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonWildcardTypeArgumentsOrDiamond(JADExParser.NonWildcardTypeArgumentsOrDiamondContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#nonWildcardTypeArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonWildcardTypeArguments(JADExParser.NonWildcardTypeArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#typeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeList(JADExParser.TypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#typeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeType(JADExParser.TypeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(JADExParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#typeArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArguments(JADExParser.TypeArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#superSuffix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperSuffix(JADExParser.SuperSuffixContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#explicitGenericInvocationSuffix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplicitGenericInvocationSuffix(JADExParser.ExplicitGenericInvocationSuffixContext ctx);
	/**
	 * Visit a parse tree produced by {@link JADExParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(JADExParser.ArgumentsContext ctx);
}