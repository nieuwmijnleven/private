// Generated from JADExParser.g4 by ANTLR 4.12.0
package jplus.base;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class JADExParser extends JADExParserBase {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ABSTRACT=1, APPLY=2, ASSERT=3, BOOLEAN=4, BREAK=5, BYTE=6, CASE=7, CATCH=8, 
		CHAR=9, CLASS=10, CONST=11, CONTINUE=12, DEFAULT=13, DO=14, DOUBLE=15, 
		ELSE=16, ENUM=17, EXPORTS=18, EXTENDS=19, FINAL=20, FINALLY=21, FLOAT=22, 
		FOR=23, GOTO=24, IF=25, IMPLEMENTS=26, IMPORT=27, INSTANCEOF=28, INT=29, 
		INTERFACE=30, LONG=31, MUTABLE=32, MODULE=33, NATIVE=34, NEW=35, NON_SEALED=36, 
		OPEN=37, OPENS=38, PACKAGE=39, PERMITS=40, PRIVATE=41, PROTECTED=42, PROVIDES=43, 
		PUBLIC=44, RECORD=45, REQUIRES=46, RETURN=47, SEALED=48, SHORT=49, STATIC=50, 
		STRICTFP=51, SUPER=52, SWITCH=53, SYNCHRONIZED=54, THIS=55, THROW=56, 
		THROWS=57, TO=58, TRANSIENT=59, TRANSITIVE=60, TRY=61, UNDER_SCORE=62, 
		USES=63, VAR=64, VOID=65, VOLATILE=66, WHEN=67, WHILE=68, WITH=69, YIELD=70, 
		DECIMAL_LITERAL=71, HEX_LITERAL=72, OCT_LITERAL=73, BINARY_LITERAL=74, 
		FLOAT_LITERAL=75, HEX_FLOAT_LITERAL=76, BOOL_LITERAL=77, CHAR_LITERAL=78, 
		STRING_LITERAL=79, TEXT_BLOCK=80, NULL_LITERAL=81, ELVIS=82, NULLSAFE=83, 
		LPAREN=84, RPAREN=85, LBRACE=86, RBRACE=87, LBRACK=88, RBRACK=89, SEMI=90, 
		COMMA=91, DOT=92, ASSIGN=93, GT=94, LT=95, BANG=96, TILDE=97, QUESTION=98, 
		COLON=99, EQUAL=100, LE=101, GE=102, NOTEQUAL=103, AND=104, OR=105, INC=106, 
		DEC=107, ADD=108, SUB=109, MUL=110, DIV=111, BITAND=112, BITOR=113, CARET=114, 
		MOD=115, ADD_ASSIGN=116, SUB_ASSIGN=117, MUL_ASSIGN=118, DIV_ASSIGN=119, 
		AND_ASSIGN=120, OR_ASSIGN=121, XOR_ASSIGN=122, MOD_ASSIGN=123, LSHIFT_ASSIGN=124, 
		RSHIFT_ASSIGN=125, URSHIFT_ASSIGN=126, ARROW=127, COLONCOLON=128, AT=129, 
		ELLIPSIS=130, WS=131, COMMENT=132, LINE_COMMENT=133, IDENTIFIER=134;
	public static final int
		RULE_compilationUnit = 0, RULE_modularCompulationUnit = 1, RULE_packageDeclaration = 2, 
		RULE_importDeclaration = 3, RULE_applyDeclaration = 4, RULE_applyStatement = 5, 
		RULE_applyBlock = 6, RULE_applyFeatureList = 7, RULE_applyFeature = 8, 
		RULE_applyFeatureArgs = 9, RULE_applyBlockEntry = 10, RULE_typeDeclaration = 11, 
		RULE_modifier = 12, RULE_classOrInterfaceModifier = 13, RULE_variableModifier = 14, 
		RULE_classDeclaration = 15, RULE_typeParameters = 16, RULE_typeParameter = 17, 
		RULE_typeBound = 18, RULE_enumDeclaration = 19, RULE_enumConstants = 20, 
		RULE_enumConstant = 21, RULE_enumBodyDeclarations = 22, RULE_interfaceDeclaration = 23, 
		RULE_classBody = 24, RULE_interfaceBody = 25, RULE_classBodyDeclaration = 26, 
		RULE_memberDeclaration = 27, RULE_methodDeclaration = 28, RULE_methodBody = 29, 
		RULE_typeTypeOrVoid = 30, RULE_genericMethodDeclaration = 31, RULE_genericConstructorDeclaration = 32, 
		RULE_constructorDeclaration = 33, RULE_compactConstructorDeclaration = 34, 
		RULE_fieldDeclaration = 35, RULE_interfaceBodyDeclaration = 36, RULE_interfaceMemberDeclaration = 37, 
		RULE_constDeclaration = 38, RULE_constantDeclarator = 39, RULE_interfaceMethodDeclaration = 40, 
		RULE_interfaceMethodModifier = 41, RULE_genericInterfaceMethodDeclaration = 42, 
		RULE_interfaceCommonBodyDeclaration = 43, RULE_variableDeclarators = 44, 
		RULE_variableDeclarator = 45, RULE_variableDeclaratorId = 46, RULE_variableInitializer = 47, 
		RULE_arrayInitializer = 48, RULE_classType = 49, RULE_packageName = 50, 
		RULE_typeArgument = 51, RULE_qualifiedNameList = 52, RULE_formalParameters = 53, 
		RULE_receiverParameter = 54, RULE_formalParameterList = 55, RULE_formalParameter = 56, 
		RULE_lambdaLVTIList = 57, RULE_lambdaLVTIParameter = 58, RULE_qualifiedName = 59, 
		RULE_literal = 60, RULE_integerLiteral = 61, RULE_floatLiteral = 62, RULE_altAnnotationQualifiedName = 63, 
		RULE_annotation = 64, RULE_annotationFieldValues = 65, RULE_annotationFieldValue = 66, 
		RULE_annotationValue = 67, RULE_elementValue = 68, RULE_elementValueArrayInitializer = 69, 
		RULE_annotationTypeDeclaration = 70, RULE_annotationTypeBody = 71, RULE_annotationTypeElementDeclaration = 72, 
		RULE_annotationTypeElementRest = 73, RULE_annotationMethodOrConstantRest = 74, 
		RULE_annotationMethodRest = 75, RULE_annotationConstantRest = 76, RULE_defaultValue = 77, 
		RULE_moduleDeclaration = 78, RULE_moduleDirective = 79, RULE_requiresModifier = 80, 
		RULE_recordDeclaration = 81, RULE_recordHeader = 82, RULE_recordComponentList = 83, 
		RULE_recordComponent = 84, RULE_recordBody = 85, RULE_block = 86, RULE_blockStatement = 87, 
		RULE_localVariableDeclaration = 88, RULE_identifier = 89, RULE_typeIdentifier = 90, 
		RULE_localTypeDeclaration = 91, RULE_statement = 92, RULE_catchClause = 93, 
		RULE_catchType = 94, RULE_finallyBlock = 95, RULE_resourceSpecification = 96, 
		RULE_resources = 97, RULE_resource = 98, RULE_switchBlockStatementGroup = 99, 
		RULE_switchLabel = 100, RULE_forControl = 101, RULE_forInit = 102, RULE_enhancedForControl = 103, 
		RULE_expressionList = 104, RULE_methodCall = 105, RULE_expression = 106, 
		RULE_pattern = 107, RULE_componentPatternList = 108, RULE_componentPattern = 109, 
		RULE_lambdaExpression = 110, RULE_lambdaParameters = 111, RULE_lambdaBody = 112, 
		RULE_primary = 113, RULE_switchExpression = 114, RULE_switchLabeledRule = 115, 
		RULE_guard = 116, RULE_casePattern = 117, RULE_switchRuleOutcome = 118, 
		RULE_classOrInterfaceType = 119, RULE_creator = 120, RULE_createdName = 121, 
		RULE_innerCreator = 122, RULE_arrayCreatorRest = 123, RULE_classCreatorRest = 124, 
		RULE_explicitGenericInvocation = 125, RULE_typeArgumentsOrDiamond = 126, 
		RULE_nonWildcardTypeArgumentsOrDiamond = 127, RULE_nonWildcardTypeArguments = 128, 
		RULE_typeList = 129, RULE_typeType = 130, RULE_primitiveType = 131, RULE_typeArguments = 132, 
		RULE_superSuffix = 133, RULE_explicitGenericInvocationSuffix = 134, RULE_arguments = 135;
	private static String[] makeRuleNames() {
		return new String[] {
			"compilationUnit", "modularCompulationUnit", "packageDeclaration", "importDeclaration", 
			"applyDeclaration", "applyStatement", "applyBlock", "applyFeatureList", 
			"applyFeature", "applyFeatureArgs", "applyBlockEntry", "typeDeclaration", 
			"modifier", "classOrInterfaceModifier", "variableModifier", "classDeclaration", 
			"typeParameters", "typeParameter", "typeBound", "enumDeclaration", "enumConstants", 
			"enumConstant", "enumBodyDeclarations", "interfaceDeclaration", "classBody", 
			"interfaceBody", "classBodyDeclaration", "memberDeclaration", "methodDeclaration", 
			"methodBody", "typeTypeOrVoid", "genericMethodDeclaration", "genericConstructorDeclaration", 
			"constructorDeclaration", "compactConstructorDeclaration", "fieldDeclaration", 
			"interfaceBodyDeclaration", "interfaceMemberDeclaration", "constDeclaration", 
			"constantDeclarator", "interfaceMethodDeclaration", "interfaceMethodModifier", 
			"genericInterfaceMethodDeclaration", "interfaceCommonBodyDeclaration", 
			"variableDeclarators", "variableDeclarator", "variableDeclaratorId", 
			"variableInitializer", "arrayInitializer", "classType", "packageName", 
			"typeArgument", "qualifiedNameList", "formalParameters", "receiverParameter", 
			"formalParameterList", "formalParameter", "lambdaLVTIList", "lambdaLVTIParameter", 
			"qualifiedName", "literal", "integerLiteral", "floatLiteral", "altAnnotationQualifiedName", 
			"annotation", "annotationFieldValues", "annotationFieldValue", "annotationValue", 
			"elementValue", "elementValueArrayInitializer", "annotationTypeDeclaration", 
			"annotationTypeBody", "annotationTypeElementDeclaration", "annotationTypeElementRest", 
			"annotationMethodOrConstantRest", "annotationMethodRest", "annotationConstantRest", 
			"defaultValue", "moduleDeclaration", "moduleDirective", "requiresModifier", 
			"recordDeclaration", "recordHeader", "recordComponentList", "recordComponent", 
			"recordBody", "block", "blockStatement", "localVariableDeclaration", 
			"identifier", "typeIdentifier", "localTypeDeclaration", "statement", 
			"catchClause", "catchType", "finallyBlock", "resourceSpecification", 
			"resources", "resource", "switchBlockStatementGroup", "switchLabel", 
			"forControl", "forInit", "enhancedForControl", "expressionList", "methodCall", 
			"expression", "pattern", "componentPatternList", "componentPattern", 
			"lambdaExpression", "lambdaParameters", "lambdaBody", "primary", "switchExpression", 
			"switchLabeledRule", "guard", "casePattern", "switchRuleOutcome", "classOrInterfaceType", 
			"creator", "createdName", "innerCreator", "arrayCreatorRest", "classCreatorRest", 
			"explicitGenericInvocation", "typeArgumentsOrDiamond", "nonWildcardTypeArgumentsOrDiamond", 
			"nonWildcardTypeArguments", "typeList", "typeType", "primitiveType", 
			"typeArguments", "superSuffix", "explicitGenericInvocationSuffix", "arguments"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'abstract'", "'apply'", "'assert'", "'boolean'", "'break'", "'byte'", 
			"'case'", "'catch'", "'char'", "'class'", "'const'", "'continue'", "'default'", 
			"'do'", "'double'", "'else'", "'enum'", "'exports'", "'extends'", "'final'", 
			"'finally'", "'float'", "'for'", "'goto'", "'if'", "'implements'", "'import'", 
			"'instanceof'", "'int'", "'interface'", "'long'", "'mutable'", "'module'", 
			"'native'", "'new'", "'non-sealed'", "'open'", "'opens'", "'package'", 
			"'permits'", "'private'", "'protected'", "'provides'", "'public'", "'record'", 
			"'requires'", "'return'", "'sealed'", "'short'", "'static'", "'strictfp'", 
			"'super'", "'switch'", "'synchronized'", "'this'", "'throw'", "'throws'", 
			"'to'", "'transient'", "'transitive'", "'try'", "'_'", "'uses'", "'var'", 
			"'void'", "'volatile'", "'when'", "'while'", "'with'", "'yield'", null, 
			null, null, null, null, null, null, null, null, null, "'null'", "'?:'", 
			"'?.'", "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", "','", "'.'", 
			"'='", "'>'", "'<'", "'!'", "'~'", "'?'", "':'", "'=='", "'<='", "'>='", 
			"'!='", "'&&'", "'||'", "'++'", "'--'", "'+'", "'-'", "'*'", "'/'", "'&'", 
			"'|'", "'^'", "'%'", "'+='", "'-='", "'*='", "'/='", "'&='", "'|='", 
			"'^='", "'%='", "'<<='", "'>>='", "'>>>='", "'->'", "'::'", "'@'", "'...'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ABSTRACT", "APPLY", "ASSERT", "BOOLEAN", "BREAK", "BYTE", "CASE", 
			"CATCH", "CHAR", "CLASS", "CONST", "CONTINUE", "DEFAULT", "DO", "DOUBLE", 
			"ELSE", "ENUM", "EXPORTS", "EXTENDS", "FINAL", "FINALLY", "FLOAT", "FOR", 
			"GOTO", "IF", "IMPLEMENTS", "IMPORT", "INSTANCEOF", "INT", "INTERFACE", 
			"LONG", "MUTABLE", "MODULE", "NATIVE", "NEW", "NON_SEALED", "OPEN", "OPENS", 
			"PACKAGE", "PERMITS", "PRIVATE", "PROTECTED", "PROVIDES", "PUBLIC", "RECORD", 
			"REQUIRES", "RETURN", "SEALED", "SHORT", "STATIC", "STRICTFP", "SUPER", 
			"SWITCH", "SYNCHRONIZED", "THIS", "THROW", "THROWS", "TO", "TRANSIENT", 
			"TRANSITIVE", "TRY", "UNDER_SCORE", "USES", "VAR", "VOID", "VOLATILE", 
			"WHEN", "WHILE", "WITH", "YIELD", "DECIMAL_LITERAL", "HEX_LITERAL", "OCT_LITERAL", 
			"BINARY_LITERAL", "FLOAT_LITERAL", "HEX_FLOAT_LITERAL", "BOOL_LITERAL", 
			"CHAR_LITERAL", "STRING_LITERAL", "TEXT_BLOCK", "NULL_LITERAL", "ELVIS", 
			"NULLSAFE", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", 
			"SEMI", "COMMA", "DOT", "ASSIGN", "GT", "LT", "BANG", "TILDE", "QUESTION", 
			"COLON", "EQUAL", "LE", "GE", "NOTEQUAL", "AND", "OR", "INC", "DEC", 
			"ADD", "SUB", "MUL", "DIV", "BITAND", "BITOR", "CARET", "MOD", "ADD_ASSIGN", 
			"SUB_ASSIGN", "MUL_ASSIGN", "DIV_ASSIGN", "AND_ASSIGN", "OR_ASSIGN", 
			"XOR_ASSIGN", "MOD_ASSIGN", "LSHIFT_ASSIGN", "RSHIFT_ASSIGN", "URSHIFT_ASSIGN", 
			"ARROW", "COLONCOLON", "AT", "ELLIPSIS", "WS", "COMMENT", "LINE_COMMENT", 
			"IDENTIFIER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "JADExParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JADExParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompilationUnitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(JADExParser.EOF, 0); }
		public PackageDeclarationContext packageDeclaration() {
			return getRuleContext(PackageDeclarationContext.class,0);
		}
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(JADExParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(JADExParser.SEMI, i);
		}
		public List<ApplyDeclarationContext> applyDeclaration() {
			return getRuleContexts(ApplyDeclarationContext.class);
		}
		public ApplyDeclarationContext applyDeclaration(int i) {
			return getRuleContext(ApplyDeclarationContext.class,i);
		}
		public List<TypeDeclarationContext> typeDeclaration() {
			return getRuleContexts(TypeDeclarationContext.class);
		}
		public TypeDeclarationContext typeDeclaration(int i) {
			return getRuleContext(TypeDeclarationContext.class,i);
		}
		public ModularCompulationUnitContext modularCompulationUnit() {
			return getRuleContext(ModularCompulationUnitContext.class,0);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitCompilationUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitCompilationUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		int _la;
		try {
			int _alt;
			setState(299);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(273);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(272);
					packageDeclaration();
					}
					break;
				}
				setState(279);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(277);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case IMPORT:
							{
							setState(275);
							importDeclaration();
							}
							break;
						case SEMI:
							{
							setState(276);
							match(SEMI);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(281);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				}
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==APPLY) {
					{
					{
					setState(282);
					applyDeclaration();
					}
					}
					setState(287);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3718622414504962L) != 0) || _la==SEMI || _la==AT) {
					{
					setState(290);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case ABSTRACT:
					case CLASS:
					case ENUM:
					case FINAL:
					case INTERFACE:
					case MUTABLE:
					case NON_SEALED:
					case PRIVATE:
					case PROTECTED:
					case PUBLIC:
					case RECORD:
					case SEALED:
					case STATIC:
					case STRICTFP:
					case AT:
						{
						setState(288);
						typeDeclaration();
						}
						break;
					case SEMI:
						{
						setState(289);
						match(SEMI);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(294);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(295);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(296);
				modularCompulationUnit();
				setState(297);
				match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModularCompulationUnitContext extends ParserRuleContext {
		public ModuleDeclarationContext moduleDeclaration() {
			return getRuleContext(ModuleDeclarationContext.class,0);
		}
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public ModularCompulationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modularCompulationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterModularCompulationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitModularCompulationUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitModularCompulationUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModularCompulationUnitContext modularCompulationUnit() throws RecognitionException {
		ModularCompulationUnitContext _localctx = new ModularCompulationUnitContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_modularCompulationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(301);
				importDeclaration();
				}
				}
				setState(306);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(307);
			moduleDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PackageDeclarationContext extends ParserRuleContext {
		public TerminalNode PACKAGE() { return getToken(JADExParser.PACKAGE, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public PackageDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterPackageDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitPackageDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitPackageDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageDeclarationContext packageDeclaration() throws RecognitionException {
		PackageDeclarationContext _localctx = new PackageDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_packageDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(309);
				annotation();
				}
				}
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(315);
			match(PACKAGE);
			setState(316);
			qualifiedName();
			setState(317);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportDeclarationContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(JADExParser.IMPORT, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public TerminalNode STATIC() { return getToken(JADExParser.STATIC, 0); }
		public TerminalNode DOT() { return getToken(JADExParser.DOT, 0); }
		public TerminalNode MUL() { return getToken(JADExParser.MUL, 0); }
		public TerminalNode MODULE() { return getToken(JADExParser.MODULE, 0); }
		public ImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterImportDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitImportDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitImportDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclarationContext importDeclaration() throws RecognitionException {
		ImportDeclarationContext _localctx = new ImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_importDeclaration);
		int _la;
		try {
			setState(335);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				match(IMPORT);
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(320);
					match(STATIC);
					}
				}

				setState(323);
				qualifiedName();
				setState(326);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DOT) {
					{
					setState(324);
					match(DOT);
					setState(325);
					match(MUL);
					}
				}

				setState(328);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(330);
				match(IMPORT);
				setState(331);
				match(MODULE);
				setState(332);
				qualifiedName();
				setState(333);
				match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ApplyDeclarationContext extends ParserRuleContext {
		public ApplyStatementContext applyStatement() {
			return getRuleContext(ApplyStatementContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public ApplyBlockContext applyBlock() {
			return getRuleContext(ApplyBlockContext.class,0);
		}
		public ApplyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_applyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterApplyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitApplyDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitApplyDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplyDeclarationContext applyDeclaration() throws RecognitionException {
		ApplyDeclarationContext _localctx = new ApplyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_applyDeclaration);
		try {
			setState(341);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(337);
				applyStatement();
				setState(338);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(340);
				applyBlock();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ApplyStatementContext extends ParserRuleContext {
		public TerminalNode APPLY() { return getToken(JADExParser.APPLY, 0); }
		public ApplyFeatureListContext applyFeatureList() {
			return getRuleContext(ApplyFeatureListContext.class,0);
		}
		public ApplyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_applyStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterApplyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitApplyStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitApplyStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplyStatementContext applyStatement() throws RecognitionException {
		ApplyStatementContext _localctx = new ApplyStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_applyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			match(APPLY);
			setState(344);
			applyFeatureList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ApplyBlockContext extends ParserRuleContext {
		public TerminalNode APPLY() { return getToken(JADExParser.APPLY, 0); }
		public TerminalNode LBRACE() { return getToken(JADExParser.LBRACE, 0); }
		public List<ApplyBlockEntryContext> applyBlockEntry() {
			return getRuleContexts(ApplyBlockEntryContext.class);
		}
		public ApplyBlockEntryContext applyBlockEntry(int i) {
			return getRuleContext(ApplyBlockEntryContext.class,i);
		}
		public TerminalNode RBRACE() { return getToken(JADExParser.RBRACE, 0); }
		public ApplyBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_applyBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterApplyBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitApplyBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitApplyBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplyBlockContext applyBlock() throws RecognitionException {
		ApplyBlockContext _localctx = new ApplyBlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_applyBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			match(APPLY);
			setState(347);
			match(LBRACE);
			setState(348);
			applyBlockEntry();
			setState(352);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 18)) & ~0x3f) == 0 && ((1L << (_la - 18)) & 7429401584631809L) != 0) || _la==IDENTIFIER) {
				{
				{
				setState(349);
				applyBlockEntry();
				}
				}
				setState(354);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(355);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ApplyFeatureListContext extends ParserRuleContext {
		public List<ApplyFeatureContext> applyFeature() {
			return getRuleContexts(ApplyFeatureContext.class);
		}
		public ApplyFeatureContext applyFeature(int i) {
			return getRuleContext(ApplyFeatureContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public ApplyFeatureListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_applyFeatureList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterApplyFeatureList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitApplyFeatureList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitApplyFeatureList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplyFeatureListContext applyFeatureList() throws RecognitionException {
		ApplyFeatureListContext _localctx = new ApplyFeatureListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_applyFeatureList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			applyFeature();
			setState(362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(358);
				match(COMMA);
				setState(359);
				applyFeature();
				}
				}
				setState(364);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ApplyFeatureContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public ApplyFeatureArgsContext applyFeatureArgs() {
			return getRuleContext(ApplyFeatureArgsContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public ApplyFeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_applyFeature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterApplyFeature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitApplyFeature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitApplyFeature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplyFeatureContext applyFeature() throws RecognitionException {
		ApplyFeatureContext _localctx = new ApplyFeatureContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_applyFeature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			identifier();
			setState(370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(366);
				match(LPAREN);
				setState(367);
				applyFeatureArgs();
				setState(368);
				match(RPAREN);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ApplyFeatureArgsContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public ApplyFeatureArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_applyFeatureArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterApplyFeatureArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitApplyFeatureArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitApplyFeatureArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplyFeatureArgsContext applyFeatureArgs() throws RecognitionException {
		ApplyFeatureArgsContext _localctx = new ApplyFeatureArgsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_applyFeatureArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			identifier();
			setState(377);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(373);
				match(COMMA);
				setState(374);
				identifier();
				}
				}
				setState(379);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ApplyBlockEntryContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(JADExParser.COLON, 0); }
		public ApplyFeatureListContext applyFeatureList() {
			return getRuleContext(ApplyFeatureListContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public ApplyBlockEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_applyBlockEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterApplyBlockEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitApplyBlockEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitApplyBlockEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplyBlockEntryContext applyBlockEntry() throws RecognitionException {
		ApplyBlockEntryContext _localctx = new ApplyBlockEntryContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_applyBlockEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			qualifiedName();
			setState(381);
			match(COLON);
			setState(382);
			applyFeatureList();
			setState(383);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeDeclarationContext extends ParserRuleContext {
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public RecordDeclarationContext recordDeclaration() {
			return getRuleContext(RecordDeclarationContext.class,0);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public TypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDeclarationContext typeDeclaration() throws RecognitionException {
		TypeDeclarationContext _localctx = new TypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_typeDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(385);
					classOrInterfaceModifier();
					}
					} 
				}
				setState(390);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			setState(396);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CLASS:
				{
				setState(391);
				classDeclaration();
				}
				break;
			case ENUM:
				{
				setState(392);
				enumDeclaration();
				}
				break;
			case INTERFACE:
				{
				setState(393);
				interfaceDeclaration();
				}
				break;
			case AT:
				{
				setState(394);
				annotationTypeDeclaration();
				}
				break;
			case RECORD:
				{
				setState(395);
				recordDeclaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModifierContext extends ParserRuleContext {
		public ClassOrInterfaceModifierContext classOrInterfaceModifier() {
			return getRuleContext(ClassOrInterfaceModifierContext.class,0);
		}
		public TerminalNode NATIVE() { return getToken(JADExParser.NATIVE, 0); }
		public TerminalNode SYNCHRONIZED() { return getToken(JADExParser.SYNCHRONIZED, 0); }
		public TerminalNode TRANSIENT() { return getToken(JADExParser.TRANSIENT, 0); }
		public TerminalNode VOLATILE() { return getToken(JADExParser.VOLATILE, 0); }
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_modifier);
		try {
			setState(403);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case FINAL:
			case MUTABLE:
			case NON_SEALED:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SEALED:
			case STATIC:
			case STRICTFP:
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(398);
				classOrInterfaceModifier();
				}
				break;
			case NATIVE:
				enterOuterAlt(_localctx, 2);
				{
				setState(399);
				match(NATIVE);
				}
				break;
			case SYNCHRONIZED:
				enterOuterAlt(_localctx, 3);
				{
				setState(400);
				match(SYNCHRONIZED);
				}
				break;
			case TRANSIENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(401);
				match(TRANSIENT);
				}
				break;
			case VOLATILE:
				enterOuterAlt(_localctx, 5);
				{
				setState(402);
				match(VOLATILE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassOrInterfaceModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public TerminalNode PUBLIC() { return getToken(JADExParser.PUBLIC, 0); }
		public TerminalNode PROTECTED() { return getToken(JADExParser.PROTECTED, 0); }
		public TerminalNode PRIVATE() { return getToken(JADExParser.PRIVATE, 0); }
		public TerminalNode STATIC() { return getToken(JADExParser.STATIC, 0); }
		public TerminalNode ABSTRACT() { return getToken(JADExParser.ABSTRACT, 0); }
		public TerminalNode FINAL() { return getToken(JADExParser.FINAL, 0); }
		public TerminalNode MUTABLE() { return getToken(JADExParser.MUTABLE, 0); }
		public TerminalNode STRICTFP() { return getToken(JADExParser.STRICTFP, 0); }
		public TerminalNode SEALED() { return getToken(JADExParser.SEALED, 0); }
		public TerminalNode NON_SEALED() { return getToken(JADExParser.NON_SEALED, 0); }
		public ClassOrInterfaceModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classOrInterfaceModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterClassOrInterfaceModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitClassOrInterfaceModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitClassOrInterfaceModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassOrInterfaceModifierContext classOrInterfaceModifier() throws RecognitionException {
		ClassOrInterfaceModifierContext _localctx = new ClassOrInterfaceModifierContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_classOrInterfaceModifier);
		try {
			setState(416);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(405);
				annotation();
				}
				break;
			case PUBLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(406);
				match(PUBLIC);
				}
				break;
			case PROTECTED:
				enterOuterAlt(_localctx, 3);
				{
				setState(407);
				match(PROTECTED);
				}
				break;
			case PRIVATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(408);
				match(PRIVATE);
				}
				break;
			case STATIC:
				enterOuterAlt(_localctx, 5);
				{
				setState(409);
				match(STATIC);
				}
				break;
			case ABSTRACT:
				enterOuterAlt(_localctx, 6);
				{
				setState(410);
				match(ABSTRACT);
				}
				break;
			case FINAL:
				enterOuterAlt(_localctx, 7);
				{
				setState(411);
				match(FINAL);
				}
				break;
			case MUTABLE:
				enterOuterAlt(_localctx, 8);
				{
				setState(412);
				match(MUTABLE);
				}
				break;
			case STRICTFP:
				enterOuterAlt(_localctx, 9);
				{
				setState(413);
				match(STRICTFP);
				}
				break;
			case SEALED:
				enterOuterAlt(_localctx, 10);
				{
				setState(414);
				match(SEALED);
				}
				break;
			case NON_SEALED:
				enterOuterAlt(_localctx, 11);
				{
				setState(415);
				match(NON_SEALED);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableModifierContext extends ParserRuleContext {
		public TerminalNode FINAL() { return getToken(JADExParser.FINAL, 0); }
		public TerminalNode MUTABLE() { return getToken(JADExParser.MUTABLE, 0); }
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public VariableModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterVariableModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitVariableModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitVariableModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableModifierContext variableModifier() throws RecognitionException {
		VariableModifierContext _localctx = new VariableModifierContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_variableModifier);
		try {
			setState(421);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(418);
				match(FINAL);
				}
				break;
			case MUTABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(419);
				match(MUTABLE);
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 3);
				{
				setState(420);
				annotation();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(JADExParser.CLASS, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(JADExParser.EXTENDS, 0); }
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode IMPLEMENTS() { return getToken(JADExParser.IMPLEMENTS, 0); }
		public List<TypeListContext> typeList() {
			return getRuleContexts(TypeListContext.class);
		}
		public TypeListContext typeList(int i) {
			return getRuleContext(TypeListContext.class,i);
		}
		public TerminalNode PERMITS() { return getToken(JADExParser.PERMITS, 0); }
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			match(CLASS);
			setState(424);
			identifier();
			setState(426);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(425);
				typeParameters();
				}
			}

			setState(430);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(428);
				match(EXTENDS);
				setState(429);
				typeType();
				}
			}

			setState(434);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(432);
				match(IMPLEMENTS);
				setState(433);
				typeList();
				}
			}

			setState(438);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PERMITS) {
				{
				setState(436);
				match(PERMITS);
				setState(437);
				typeList();
				}
			}

			setState(440);
			classBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeParametersContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(JADExParser.LT, 0); }
		public List<TypeParameterContext> typeParameter() {
			return getRuleContexts(TypeParameterContext.class);
		}
		public TypeParameterContext typeParameter(int i) {
			return getRuleContext(TypeParameterContext.class,i);
		}
		public TerminalNode GT() { return getToken(JADExParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public TypeParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterTypeParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitTypeParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitTypeParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParametersContext typeParameters() throws RecognitionException {
		TypeParametersContext _localctx = new TypeParametersContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_typeParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			match(LT);
			setState(443);
			typeParameter();
			setState(448);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(444);
				match(COMMA);
				setState(445);
				typeParameter();
				}
				}
				setState(450);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(451);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeParameterContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode EXTENDS() { return getToken(JADExParser.EXTENDS, 0); }
		public TypeBoundContext typeBound() {
			return getRuleContext(TypeBoundContext.class,0);
		}
		public TypeParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterTypeParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitTypeParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitTypeParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParameterContext typeParameter() throws RecognitionException {
		TypeParameterContext _localctx = new TypeParameterContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typeParameter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(456);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(453);
				annotation();
				}
				}
				setState(458);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(459);
			identifier();
			setState(468);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(460);
				match(EXTENDS);
				setState(464);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(461);
						annotation();
						}
						} 
					}
					setState(466);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				}
				setState(467);
				typeBound();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeBoundContext extends ParserRuleContext {
		public List<TypeTypeContext> typeType() {
			return getRuleContexts(TypeTypeContext.class);
		}
		public TypeTypeContext typeType(int i) {
			return getRuleContext(TypeTypeContext.class,i);
		}
		public List<TerminalNode> BITAND() { return getTokens(JADExParser.BITAND); }
		public TerminalNode BITAND(int i) {
			return getToken(JADExParser.BITAND, i);
		}
		public TypeBoundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeBound; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterTypeBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitTypeBound(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitTypeBound(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeBoundContext typeBound() throws RecognitionException {
		TypeBoundContext _localctx = new TypeBoundContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_typeBound);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(470);
			typeType();
			setState(475);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITAND) {
				{
				{
				setState(471);
				match(BITAND);
				setState(472);
				typeType();
				}
				}
				setState(477);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnumDeclarationContext extends ParserRuleContext {
		public TerminalNode ENUM() { return getToken(JADExParser.ENUM, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(JADExParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(JADExParser.RBRACE, 0); }
		public TerminalNode IMPLEMENTS() { return getToken(JADExParser.IMPLEMENTS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public EnumConstantsContext enumConstants() {
			return getRuleContext(EnumConstantsContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(JADExParser.COMMA, 0); }
		public EnumBodyDeclarationsContext enumBodyDeclarations() {
			return getRuleContext(EnumBodyDeclarationsContext.class,0);
		}
		public EnumDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterEnumDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitEnumDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitEnumDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumDeclarationContext enumDeclaration() throws RecognitionException {
		EnumDeclarationContext _localctx = new EnumDeclarationContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_enumDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(478);
			match(ENUM);
			setState(479);
			identifier();
			setState(482);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(480);
				match(IMPLEMENTS);
				setState(481);
				typeList();
				}
			}

			setState(484);
			match(LBRACE);
			setState(486);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 18)) & ~0x3f) == 0 && ((1L << (_la - 18)) & 7429401584631809L) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(485);
				enumConstants();
				}
			}

			setState(489);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(488);
				match(COMMA);
				}
			}

			setState(492);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(491);
				enumBodyDeclarations();
				}
			}

			setState(494);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnumConstantsContext extends ParserRuleContext {
		public List<EnumConstantContext> enumConstant() {
			return getRuleContexts(EnumConstantContext.class);
		}
		public EnumConstantContext enumConstant(int i) {
			return getRuleContext(EnumConstantContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public EnumConstantsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstants; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterEnumConstants(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitEnumConstants(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitEnumConstants(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumConstantsContext enumConstants() throws RecognitionException {
		EnumConstantsContext _localctx = new EnumConstantsContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_enumConstants);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(496);
			enumConstant();
			setState(501);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(497);
					match(COMMA);
					setState(498);
					enumConstant();
					}
					} 
				}
				setState(503);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnumConstantContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public EnumConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterEnumConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitEnumConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitEnumConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumConstantContext enumConstant() throws RecognitionException {
		EnumConstantContext _localctx = new EnumConstantContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_enumConstant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(507);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(504);
				annotation();
				}
				}
				setState(509);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(510);
			identifier();
			setState(512);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(511);
				arguments();
				}
			}

			setState(515);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(514);
				classBody();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnumBodyDeclarationsContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public EnumBodyDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumBodyDeclarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterEnumBodyDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitEnumBodyDeclarations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitEnumBodyDeclarations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumBodyDeclarationsContext enumBodyDeclarations() throws RecognitionException {
		EnumBodyDeclarationsContext _localctx = new EnumBodyDeclarationsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_enumBodyDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
			match(SEMI);
			setState(521);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7183382727791049134L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 2218786927L) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				{
				setState(518);
				classBodyDeclaration();
				}
				}
				setState(523);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InterfaceDeclarationContext extends ParserRuleContext {
		public TerminalNode INTERFACE() { return getToken(JADExParser.INTERFACE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public InterfaceBodyContext interfaceBody() {
			return getRuleContext(InterfaceBodyContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(JADExParser.EXTENDS, 0); }
		public List<TypeListContext> typeList() {
			return getRuleContexts(TypeListContext.class);
		}
		public TypeListContext typeList(int i) {
			return getRuleContext(TypeListContext.class,i);
		}
		public TerminalNode PERMITS() { return getToken(JADExParser.PERMITS, 0); }
		public InterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterInterfaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitInterfaceDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitInterfaceDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceDeclarationContext interfaceDeclaration() throws RecognitionException {
		InterfaceDeclarationContext _localctx = new InterfaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_interfaceDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(524);
			match(INTERFACE);
			setState(525);
			identifier();
			setState(527);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(526);
				typeParameters();
				}
			}

			setState(531);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(529);
				match(EXTENDS);
				setState(530);
				typeList();
				}
			}

			setState(535);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PERMITS) {
				{
				setState(533);
				match(PERMITS);
				setState(534);
				typeList();
				}
			}

			setState(537);
			interfaceBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(JADExParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(JADExParser.RBRACE, 0); }
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterClassBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitClassBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitClassBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_classBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(539);
			match(LBRACE);
			setState(543);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7183382727791049134L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 2218786927L) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				{
				setState(540);
				classBodyDeclaration();
				}
				}
				setState(545);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(546);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InterfaceBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(JADExParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(JADExParser.RBRACE, 0); }
		public List<InterfaceBodyDeclarationContext> interfaceBodyDeclaration() {
			return getRuleContexts(InterfaceBodyDeclarationContext.class);
		}
		public InterfaceBodyDeclarationContext interfaceBodyDeclaration(int i) {
			return getRuleContext(InterfaceBodyDeclarationContext.class,i);
		}
		public InterfaceBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterInterfaceBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitInterfaceBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitInterfaceBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceBodyContext interfaceBody() throws RecognitionException {
		InterfaceBodyContext _localctx = new InterfaceBodyContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_interfaceBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(548);
			match(LBRACE);
			setState(552);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7183382727791040942L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 2214592623L) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				{
				setState(549);
				interfaceBodyDeclaration();
				}
				}
				setState(554);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(555);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassBodyDeclarationContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode STATIC() { return getToken(JADExParser.STATIC, 0); }
		public MemberDeclarationContext memberDeclaration() {
			return getRuleContext(MemberDeclarationContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public ClassBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBodyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterClassBodyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitClassBodyDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitClassBodyDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBodyDeclarationContext classBodyDeclaration() throws RecognitionException {
		ClassBodyDeclarationContext _localctx = new ClassBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_classBodyDeclaration);
		int _la;
		try {
			int _alt;
			setState(569);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(557);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(559);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(558);
					match(STATIC);
					}
				}

				setState(561);
				block();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(565);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(562);
						modifier();
						}
						} 
					}
					setState(567);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				}
				setState(568);
				memberDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MemberDeclarationContext extends ParserRuleContext {
		public RecordDeclarationContext recordDeclaration() {
			return getRuleContext(RecordDeclarationContext.class,0);
		}
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public GenericMethodDeclarationContext genericMethodDeclaration() {
			return getRuleContext(GenericMethodDeclarationContext.class,0);
		}
		public FieldDeclarationContext fieldDeclaration() {
			return getRuleContext(FieldDeclarationContext.class,0);
		}
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public GenericConstructorDeclarationContext genericConstructorDeclaration() {
			return getRuleContext(GenericConstructorDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public MemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitMemberDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitMemberDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberDeclarationContext memberDeclaration() throws RecognitionException {
		MemberDeclarationContext _localctx = new MemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_memberDeclaration);
		try {
			setState(581);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(571);
				recordDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(572);
				methodDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(573);
				genericMethodDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(574);
				fieldDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(575);
				constructorDeclaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(576);
				genericConstructorDeclaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(577);
				interfaceDeclaration();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(578);
				annotationTypeDeclaration();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(579);
				classDeclaration();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(580);
				enumDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodDeclarationContext extends ParserRuleContext {
		public TypeTypeOrVoidContext typeTypeOrVoid() {
			return getRuleContext(TypeTypeOrVoidContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(JADExParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(JADExParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(JADExParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(JADExParser.RBRACK, i);
		}
		public TerminalNode THROWS() { return getToken(JADExParser.THROWS, 0); }
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitMethodDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_methodDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(583);
			typeTypeOrVoid();
			setState(584);
			identifier();
			setState(585);
			formalParameters();
			setState(590);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(586);
				match(LBRACK);
				setState(587);
				match(RBRACK);
				}
				}
				setState(592);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(595);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(593);
				match(THROWS);
				setState(594);
				qualifiedNameList();
				}
			}

			setState(597);
			methodBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodBodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public MethodBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterMethodBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitMethodBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitMethodBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodBodyContext methodBody() throws RecognitionException {
		MethodBodyContext _localctx = new MethodBodyContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_methodBody);
		try {
			setState(601);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(599);
				block();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(600);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeTypeOrVoidContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(JADExParser.VOID, 0); }
		public TypeTypeOrVoidContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeTypeOrVoid; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterTypeTypeOrVoid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitTypeTypeOrVoid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitTypeTypeOrVoid(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeTypeOrVoidContext typeTypeOrVoid() throws RecognitionException {
		TypeTypeOrVoidContext _localctx = new TypeTypeOrVoidContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_typeTypeOrVoid);
		try {
			setState(605);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case EXPORTS:
			case FLOAT:
			case INT:
			case LONG:
			case MODULE:
			case OPEN:
			case OPENS:
			case PERMITS:
			case PROVIDES:
			case RECORD:
			case REQUIRES:
			case SEALED:
			case SHORT:
			case TO:
			case TRANSITIVE:
			case USES:
			case VAR:
			case WHEN:
			case WITH:
			case YIELD:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(603);
				typeType();
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(604);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GenericMethodDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public GenericMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterGenericMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitGenericMethodDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitGenericMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GenericMethodDeclarationContext genericMethodDeclaration() throws RecognitionException {
		GenericMethodDeclarationContext _localctx = new GenericMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_genericMethodDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(607);
			typeParameters();
			setState(608);
			methodDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GenericConstructorDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public GenericConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericConstructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterGenericConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitGenericConstructorDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitGenericConstructorDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GenericConstructorDeclarationContext genericConstructorDeclaration() throws RecognitionException {
		GenericConstructorDeclarationContext _localctx = new GenericConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_genericConstructorDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(610);
			typeParameters();
			setState(611);
			constructorDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstructorDeclarationContext extends ParserRuleContext {
		public BlockContext constructorBody;
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode THROWS() { return getToken(JADExParser.THROWS, 0); }
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public ConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitConstructorDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitConstructorDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorDeclarationContext constructorDeclaration() throws RecognitionException {
		ConstructorDeclarationContext _localctx = new ConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_constructorDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(613);
			identifier();
			setState(614);
			formalParameters();
			setState(617);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(615);
				match(THROWS);
				setState(616);
				qualifiedNameList();
				}
			}

			setState(619);
			((ConstructorDeclarationContext)_localctx).constructorBody = block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompactConstructorDeclarationContext extends ParserRuleContext {
		public BlockContext constructorBody;
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public CompactConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compactConstructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterCompactConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitCompactConstructorDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitCompactConstructorDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompactConstructorDeclarationContext compactConstructorDeclaration() throws RecognitionException {
		CompactConstructorDeclarationContext _localctx = new CompactConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_compactConstructorDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(624);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(621);
					modifier();
					}
					} 
				}
				setState(626);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			}
			setState(627);
			identifier();
			setState(628);
			((CompactConstructorDeclarationContext)_localctx).constructorBody = block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldDeclarationContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public FieldDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterFieldDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitFieldDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitFieldDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldDeclarationContext fieldDeclaration() throws RecognitionException {
		FieldDeclarationContext _localctx = new FieldDeclarationContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_fieldDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(630);
			typeType();
			setState(631);
			variableDeclarators();
			setState(632);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InterfaceBodyDeclarationContext extends ParserRuleContext {
		public InterfaceMemberDeclarationContext interfaceMemberDeclaration() {
			return getRuleContext(InterfaceMemberDeclarationContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public InterfaceBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceBodyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterInterfaceBodyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitInterfaceBodyDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitInterfaceBodyDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceBodyDeclarationContext interfaceBodyDeclaration() throws RecognitionException {
		InterfaceBodyDeclarationContext _localctx = new InterfaceBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_interfaceBodyDeclaration);
		try {
			int _alt;
			setState(642);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case CLASS:
			case DEFAULT:
			case DOUBLE:
			case ENUM:
			case EXPORTS:
			case FINAL:
			case FLOAT:
			case INT:
			case INTERFACE:
			case LONG:
			case MUTABLE:
			case MODULE:
			case NATIVE:
			case NON_SEALED:
			case OPEN:
			case OPENS:
			case PERMITS:
			case PRIVATE:
			case PROTECTED:
			case PROVIDES:
			case PUBLIC:
			case RECORD:
			case REQUIRES:
			case SEALED:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TO:
			case TRANSIENT:
			case TRANSITIVE:
			case USES:
			case VAR:
			case VOID:
			case VOLATILE:
			case WHEN:
			case WITH:
			case YIELD:
			case LT:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(637);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(634);
						modifier();
						}
						} 
					}
					setState(639);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				}
				setState(640);
				interfaceMemberDeclaration();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(641);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InterfaceMemberDeclarationContext extends ParserRuleContext {
		public RecordDeclarationContext recordDeclaration() {
			return getRuleContext(RecordDeclarationContext.class,0);
		}
		public ConstDeclarationContext constDeclaration() {
			return getRuleContext(ConstDeclarationContext.class,0);
		}
		public InterfaceMethodDeclarationContext interfaceMethodDeclaration() {
			return getRuleContext(InterfaceMethodDeclarationContext.class,0);
		}
		public GenericInterfaceMethodDeclarationContext genericInterfaceMethodDeclaration() {
			return getRuleContext(GenericInterfaceMethodDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public InterfaceMemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMemberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterInterfaceMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitInterfaceMemberDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitInterfaceMemberDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceMemberDeclarationContext interfaceMemberDeclaration() throws RecognitionException {
		InterfaceMemberDeclarationContext _localctx = new InterfaceMemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_interfaceMemberDeclaration);
		try {
			setState(652);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(644);
				recordDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(645);
				constDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(646);
				interfaceMethodDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(647);
				genericInterfaceMethodDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(648);
				interfaceDeclaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(649);
				annotationTypeDeclaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(650);
				classDeclaration();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(651);
				enumDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstDeclarationContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public List<ConstantDeclaratorContext> constantDeclarator() {
			return getRuleContexts(ConstantDeclaratorContext.class);
		}
		public ConstantDeclaratorContext constantDeclarator(int i) {
			return getRuleContext(ConstantDeclaratorContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public ConstDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterConstDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitConstDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitConstDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstDeclarationContext constDeclaration() throws RecognitionException {
		ConstDeclarationContext _localctx = new ConstDeclarationContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_constDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(654);
			typeType();
			setState(655);
			constantDeclarator();
			setState(660);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(656);
				match(COMMA);
				setState(657);
				constantDeclarator();
				}
				}
				setState(662);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(663);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstantDeclaratorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(JADExParser.ASSIGN, 0); }
		public VariableInitializerContext variableInitializer() {
			return getRuleContext(VariableInitializerContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(JADExParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(JADExParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(JADExParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(JADExParser.RBRACK, i);
		}
		public ConstantDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterConstantDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitConstantDeclarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitConstantDeclarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantDeclaratorContext constantDeclarator() throws RecognitionException {
		ConstantDeclaratorContext _localctx = new ConstantDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_constantDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(665);
			identifier();
			setState(670);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(666);
				match(LBRACK);
				setState(667);
				match(RBRACK);
				}
				}
				setState(672);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(673);
			match(ASSIGN);
			setState(674);
			variableInitializer();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InterfaceMethodDeclarationContext extends ParserRuleContext {
		public InterfaceCommonBodyDeclarationContext interfaceCommonBodyDeclaration() {
			return getRuleContext(InterfaceCommonBodyDeclarationContext.class,0);
		}
		public List<InterfaceMethodModifierContext> interfaceMethodModifier() {
			return getRuleContexts(InterfaceMethodModifierContext.class);
		}
		public InterfaceMethodModifierContext interfaceMethodModifier(int i) {
			return getRuleContext(InterfaceMethodModifierContext.class,i);
		}
		public InterfaceMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterInterfaceMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitInterfaceMethodDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitInterfaceMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceMethodDeclarationContext interfaceMethodDeclaration() throws RecognitionException {
		InterfaceMethodDeclarationContext _localctx = new InterfaceMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_interfaceMethodDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(679);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(676);
					interfaceMethodModifier();
					}
					} 
				}
				setState(681);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			}
			setState(682);
			interfaceCommonBodyDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InterfaceMethodModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public TerminalNode PUBLIC() { return getToken(JADExParser.PUBLIC, 0); }
		public TerminalNode ABSTRACT() { return getToken(JADExParser.ABSTRACT, 0); }
		public TerminalNode DEFAULT() { return getToken(JADExParser.DEFAULT, 0); }
		public TerminalNode STATIC() { return getToken(JADExParser.STATIC, 0); }
		public TerminalNode STRICTFP() { return getToken(JADExParser.STRICTFP, 0); }
		public InterfaceMethodModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMethodModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterInterfaceMethodModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitInterfaceMethodModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitInterfaceMethodModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceMethodModifierContext interfaceMethodModifier() throws RecognitionException {
		InterfaceMethodModifierContext _localctx = new InterfaceMethodModifierContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_interfaceMethodModifier);
		try {
			setState(690);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(684);
				annotation();
				}
				break;
			case PUBLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(685);
				match(PUBLIC);
				}
				break;
			case ABSTRACT:
				enterOuterAlt(_localctx, 3);
				{
				setState(686);
				match(ABSTRACT);
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 4);
				{
				setState(687);
				match(DEFAULT);
				}
				break;
			case STATIC:
				enterOuterAlt(_localctx, 5);
				{
				setState(688);
				match(STATIC);
				}
				break;
			case STRICTFP:
				enterOuterAlt(_localctx, 6);
				{
				setState(689);
				match(STRICTFP);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GenericInterfaceMethodDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public InterfaceCommonBodyDeclarationContext interfaceCommonBodyDeclaration() {
			return getRuleContext(InterfaceCommonBodyDeclarationContext.class,0);
		}
		public List<InterfaceMethodModifierContext> interfaceMethodModifier() {
			return getRuleContexts(InterfaceMethodModifierContext.class);
		}
		public InterfaceMethodModifierContext interfaceMethodModifier(int i) {
			return getRuleContext(InterfaceMethodModifierContext.class,i);
		}
		public GenericInterfaceMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericInterfaceMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterGenericInterfaceMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitGenericInterfaceMethodDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitGenericInterfaceMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GenericInterfaceMethodDeclarationContext genericInterfaceMethodDeclaration() throws RecognitionException {
		GenericInterfaceMethodDeclarationContext _localctx = new GenericInterfaceMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_genericInterfaceMethodDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(695);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3395291906580482L) != 0) || _la==AT) {
				{
				{
				setState(692);
				interfaceMethodModifier();
				}
				}
				setState(697);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(698);
			typeParameters();
			setState(699);
			interfaceCommonBodyDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InterfaceCommonBodyDeclarationContext extends ParserRuleContext {
		public TypeTypeOrVoidContext typeTypeOrVoid() {
			return getRuleContext(TypeTypeOrVoidContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<TerminalNode> LBRACK() { return getTokens(JADExParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(JADExParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(JADExParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(JADExParser.RBRACK, i);
		}
		public TerminalNode THROWS() { return getToken(JADExParser.THROWS, 0); }
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public InterfaceCommonBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceCommonBodyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterInterfaceCommonBodyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitInterfaceCommonBodyDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitInterfaceCommonBodyDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceCommonBodyDeclarationContext interfaceCommonBodyDeclaration() throws RecognitionException {
		InterfaceCommonBodyDeclarationContext _localctx = new InterfaceCommonBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_interfaceCommonBodyDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(704);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(701);
					annotation();
					}
					} 
				}
				setState(706);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			}
			setState(707);
			typeTypeOrVoid();
			setState(708);
			identifier();
			setState(709);
			formalParameters();
			setState(714);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(710);
				match(LBRACK);
				setState(711);
				match(RBRACK);
				}
				}
				setState(716);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(719);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(717);
				match(THROWS);
				setState(718);
				qualifiedNameList();
				}
			}

			setState(721);
			methodBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclaratorsContext extends ParserRuleContext {
		public List<VariableDeclaratorContext> variableDeclarator() {
			return getRuleContexts(VariableDeclaratorContext.class);
		}
		public VariableDeclaratorContext variableDeclarator(int i) {
			return getRuleContext(VariableDeclaratorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public VariableDeclaratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterVariableDeclarators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitVariableDeclarators(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitVariableDeclarators(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclaratorsContext variableDeclarators() throws RecognitionException {
		VariableDeclaratorsContext _localctx = new VariableDeclaratorsContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_variableDeclarators);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(723);
			variableDeclarator();
			setState(728);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(724);
					match(COMMA);
					setState(725);
					variableDeclarator();
					}
					} 
				}
				setState(730);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclaratorContext extends ParserRuleContext {
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(JADExParser.ASSIGN, 0); }
		public VariableInitializerContext variableInitializer() {
			return getRuleContext(VariableInitializerContext.class,0);
		}
		public VariableDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterVariableDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitVariableDeclarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitVariableDeclarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclaratorContext variableDeclarator() throws RecognitionException {
		VariableDeclaratorContext _localctx = new VariableDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_variableDeclarator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(731);
			variableDeclaratorId();
			setState(734);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				{
				setState(732);
				match(ASSIGN);
				setState(733);
				variableInitializer();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclaratorIdContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(JADExParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(JADExParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(JADExParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(JADExParser.RBRACK, i);
		}
		public VariableDeclaratorIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaratorId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterVariableDeclaratorId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitVariableDeclaratorId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitVariableDeclaratorId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclaratorIdContext variableDeclaratorId() throws RecognitionException {
		VariableDeclaratorIdContext _localctx = new VariableDeclaratorIdContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_variableDeclaratorId);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(736);
			identifier();
			setState(741);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,68,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(737);
					match(LBRACK);
					setState(738);
					match(RBRACK);
					}
					} 
				}
				setState(743);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,68,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableInitializerContext extends ParserRuleContext {
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterVariableInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitVariableInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitVariableInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableInitializerContext variableInitializer() throws RecognitionException {
		VariableInitializerContext _localctx = new VariableInitializerContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_variableInitializer);
		try {
			setState(746);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(744);
				arrayInitializer();
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case EXPORTS:
			case FLOAT:
			case INT:
			case LONG:
			case MODULE:
			case NEW:
			case OPEN:
			case OPENS:
			case PERMITS:
			case PROVIDES:
			case RECORD:
			case REQUIRES:
			case SEALED:
			case SHORT:
			case SUPER:
			case SWITCH:
			case THIS:
			case TO:
			case TRANSITIVE:
			case USES:
			case VAR:
			case VOID:
			case WHEN:
			case WITH:
			case YIELD:
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
			case BOOL_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case TEXT_BLOCK:
			case NULL_LITERAL:
			case LPAREN:
			case LT:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(745);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayInitializerContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(JADExParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(JADExParser.RBRACE, 0); }
		public List<VariableInitializerContext> variableInitializer() {
			return getRuleContexts(VariableInitializerContext.class);
		}
		public VariableInitializerContext variableInitializer(int i) {
			return getRuleContext(VariableInitializerContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public ArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitArrayInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitArrayInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayInitializerContext arrayInitializer() throws RecognitionException {
		ArrayInitializerContext _localctx = new ArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_arrayInitializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(748);
			match(LBRACE);
			setState(760);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7731720228588715440L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 65985735557099L) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(749);
				variableInitializer();
				setState(754);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(750);
						match(COMMA);
						setState(751);
						variableInitializer();
						}
						} 
					}
					setState(756);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
				}
				setState(758);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(757);
					match(COMMA);
					}
				}

				}
			}

			setState(762);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassTypeContext extends ParserRuleContext {
		public List<TypeIdentifierContext> typeIdentifier() {
			return getRuleContexts(TypeIdentifierContext.class);
		}
		public TypeIdentifierContext typeIdentifier(int i) {
			return getRuleContext(TypeIdentifierContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(JADExParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(JADExParser.DOT, i);
		}
		public List<PackageNameContext> packageName() {
			return getRuleContexts(PackageNameContext.class);
		}
		public PackageNameContext packageName(int i) {
			return getRuleContext(PackageNameContext.class,i);
		}
		public List<TypeArgumentsContext> typeArguments() {
			return getRuleContexts(TypeArgumentsContext.class);
		}
		public TypeArgumentsContext typeArguments(int i) {
			return getRuleContext(TypeArgumentsContext.class,i);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public ClassTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterClassType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitClassType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitClassType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassTypeContext classType() throws RecognitionException {
		ClassTypeContext _localctx = new ClassTypeContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_classType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(778); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(772);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
					case 1:
						{
						setState(764);
						packageName();
						setState(765);
						match(DOT);
						setState(769);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==AT) {
							{
							{
							setState(766);
							annotation();
							}
							}
							setState(771);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
						break;
					}
					setState(774);
					typeIdentifier();
					setState(776);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
					case 1:
						{
						setState(775);
						typeArguments();
						}
						break;
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(780); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(795);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(782);
					match(DOT);
					setState(786);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==AT) {
						{
						{
						setState(783);
						annotation();
						}
						}
						setState(788);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(789);
					typeIdentifier();
					setState(791);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
					case 1:
						{
						setState(790);
						typeArguments();
						}
						break;
					}
					}
					} 
				}
				setState(797);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PackageNameContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(JADExParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(JADExParser.DOT, i);
		}
		public PackageNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterPackageName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitPackageName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitPackageName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageNameContext packageName() throws RecognitionException {
		PackageNameContext _localctx = new PackageNameContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_packageName);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(798);
			identifier();
			setState(803);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,80,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(799);
					match(DOT);
					setState(800);
					identifier();
					}
					} 
				}
				setState(805);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,80,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeArgumentContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode QUESTION() { return getToken(JADExParser.QUESTION, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode EXTENDS() { return getToken(JADExParser.EXTENDS, 0); }
		public TerminalNode SUPER() { return getToken(JADExParser.SUPER, 0); }
		public TypeArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterTypeArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitTypeArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitTypeArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeArgumentContext typeArgument() throws RecognitionException {
		TypeArgumentContext _localctx = new TypeArgumentContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_typeArgument);
		int _la;
		try {
			setState(818);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(806);
				typeType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(810);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AT) {
					{
					{
					setState(807);
					annotation();
					}
					}
					setState(812);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(813);
				match(QUESTION);
				setState(816);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXTENDS || _la==SUPER) {
					{
					setState(814);
					_la = _input.LA(1);
					if ( !(_la==EXTENDS || _la==SUPER) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(815);
					typeType();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QualifiedNameListContext extends ParserRuleContext {
		public List<QualifiedNameContext> qualifiedName() {
			return getRuleContexts(QualifiedNameContext.class);
		}
		public QualifiedNameContext qualifiedName(int i) {
			return getRuleContext(QualifiedNameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public QualifiedNameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedNameList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterQualifiedNameList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitQualifiedNameList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitQualifiedNameList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNameListContext qualifiedNameList() throws RecognitionException {
		QualifiedNameListContext _localctx = new QualifiedNameListContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_qualifiedNameList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(820);
			qualifiedName();
			setState(825);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(821);
				match(COMMA);
				setState(822);
				qualifiedName();
				}
				}
				setState(827);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormalParametersContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public ReceiverParameterContext receiverParameter() {
			return getRuleContext(ReceiverParameterContext.class,0);
		}
		public FormalParameterContext formalParameter() {
			return getRuleContext(FormalParameterContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public List<FormalParameterListContext> formalParameterList() {
			return getRuleContexts(FormalParameterListContext.class);
		}
		public FormalParameterListContext formalParameterList(int i) {
			return getRuleContext(FormalParameterListContext.class,i);
		}
		public FormalParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterFormalParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitFormalParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitFormalParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParametersContext formalParameters() throws RecognitionException {
		FormalParametersContext _localctx = new FormalParametersContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_formalParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(828);
			match(LPAREN);
			setState(840);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7781259854553513392L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 105L) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(831);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
				case 1:
					{
					setState(829);
					receiverParameter();
					}
					break;
				case 2:
					{
					setState(830);
					formalParameter();
					}
					break;
				}
				setState(837);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(833);
					match(COMMA);
					setState(834);
					formalParameterList();
					}
					}
					setState(839);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(842);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReceiverParameterContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode THIS() { return getToken(JADExParser.THIS, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(JADExParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(JADExParser.DOT, i);
		}
		public ReceiverParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_receiverParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterReceiverParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitReceiverParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitReceiverParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReceiverParameterContext receiverParameter() throws RecognitionException {
		ReceiverParameterContext _localctx = new ReceiverParameterContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_receiverParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(844);
			typeType();
			setState(850);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 18)) & ~0x3f) == 0 && ((1L << (_la - 18)) & 7429401584631809L) != 0) || _la==IDENTIFIER) {
				{
				{
				setState(845);
				identifier();
				setState(846);
				match(DOT);
				}
				}
				setState(852);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(853);
			match(THIS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormalParameterListContext extends ParserRuleContext {
		public List<FormalParameterContext> formalParameter() {
			return getRuleContexts(FormalParameterContext.class);
		}
		public FormalParameterContext formalParameter(int i) {
			return getRuleContext(FormalParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public FormalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterFormalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitFormalParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitFormalParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterListContext formalParameterList() throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_formalParameterList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(855);
			formalParameter();
			setState(860);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(856);
					match(COMMA);
					setState(857);
					formalParameter();
					}
					} 
				}
				setState(862);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormalParameterContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public TerminalNode ELLIPSIS() { return getToken(JADExParser.ELLIPSIS, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public FormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitFormalParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitFormalParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterContext formalParameter() throws RecognitionException {
		FormalParameterContext _localctx = new FormalParameterContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_formalParameter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(866);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(863);
					variableModifier();
					}
					} 
				}
				setState(868);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
			}
			setState(869);
			typeType();
			setState(877);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AT || _la==ELLIPSIS) {
				{
				setState(873);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AT) {
					{
					{
					setState(870);
					annotation();
					}
					}
					setState(875);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(876);
				match(ELLIPSIS);
				}
			}

			setState(879);
			variableDeclaratorId();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LambdaLVTIListContext extends ParserRuleContext {
		public List<LambdaLVTIParameterContext> lambdaLVTIParameter() {
			return getRuleContexts(LambdaLVTIParameterContext.class);
		}
		public LambdaLVTIParameterContext lambdaLVTIParameter(int i) {
			return getRuleContext(LambdaLVTIParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public LambdaLVTIListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaLVTIList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterLambdaLVTIList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitLambdaLVTIList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitLambdaLVTIList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaLVTIListContext lambdaLVTIList() throws RecognitionException {
		LambdaLVTIListContext _localctx = new LambdaLVTIListContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_lambdaLVTIList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(881);
			lambdaLVTIParameter();
			setState(886);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(882);
				match(COMMA);
				setState(883);
				lambdaLVTIParameter();
				}
				}
				setState(888);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LambdaLVTIParameterContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(JADExParser.VAR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public LambdaLVTIParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaLVTIParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterLambdaLVTIParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitLambdaLVTIParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitLambdaLVTIParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaLVTIParameterContext lambdaLVTIParameter() throws RecognitionException {
		LambdaLVTIParameterContext _localctx = new LambdaLVTIParameterContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_lambdaLVTIParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(892);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==MUTABLE || _la==AT) {
				{
				{
				setState(889);
				variableModifier();
				}
				}
				setState(894);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(895);
			match(VAR);
			setState(896);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QualifiedNameContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(JADExParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(JADExParser.DOT, i);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitQualifiedName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitQualifiedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_qualifiedName);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(898);
			identifier();
			setState(903);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(899);
					match(DOT);
					setState(900);
					identifier();
					}
					} 
				}
				setState(905);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public FloatLiteralContext floatLiteral() {
			return getRuleContext(FloatLiteralContext.class,0);
		}
		public TerminalNode CHAR_LITERAL() { return getToken(JADExParser.CHAR_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(JADExParser.STRING_LITERAL, 0); }
		public TerminalNode BOOL_LITERAL() { return getToken(JADExParser.BOOL_LITERAL, 0); }
		public TerminalNode NULL_LITERAL() { return getToken(JADExParser.NULL_LITERAL, 0); }
		public TerminalNode TEXT_BLOCK() { return getToken(JADExParser.TEXT_BLOCK, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_literal);
		try {
			setState(913);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(906);
				integerLiteral();
				}
				break;
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(907);
				floatLiteral();
				}
				break;
			case CHAR_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(908);
				match(CHAR_LITERAL);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(909);
				match(STRING_LITERAL);
				}
				break;
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(910);
				match(BOOL_LITERAL);
				}
				break;
			case NULL_LITERAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(911);
				match(NULL_LITERAL);
				}
				break;
			case TEXT_BLOCK:
				enterOuterAlt(_localctx, 7);
				{
				setState(912);
				match(TEXT_BLOCK);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntegerLiteralContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(JADExParser.DECIMAL_LITERAL, 0); }
		public TerminalNode HEX_LITERAL() { return getToken(JADExParser.HEX_LITERAL, 0); }
		public TerminalNode OCT_LITERAL() { return getToken(JADExParser.OCT_LITERAL, 0); }
		public TerminalNode BINARY_LITERAL() { return getToken(JADExParser.BINARY_LITERAL, 0); }
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitIntegerLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_integerLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(915);
			_la = _input.LA(1);
			if ( !(((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & 15L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FloatLiteralContext extends ParserRuleContext {
		public TerminalNode FLOAT_LITERAL() { return getToken(JADExParser.FLOAT_LITERAL, 0); }
		public TerminalNode HEX_FLOAT_LITERAL() { return getToken(JADExParser.HEX_FLOAT_LITERAL, 0); }
		public FloatLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterFloatLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitFloatLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitFloatLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatLiteralContext floatLiteral() throws RecognitionException {
		FloatLiteralContext _localctx = new FloatLiteralContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_floatLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(917);
			_la = _input.LA(1);
			if ( !(_la==FLOAT_LITERAL || _la==HEX_FLOAT_LITERAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AltAnnotationQualifiedNameContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(JADExParser.AT, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(JADExParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(JADExParser.DOT, i);
		}
		public AltAnnotationQualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_altAnnotationQualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterAltAnnotationQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitAltAnnotationQualifiedName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitAltAnnotationQualifiedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AltAnnotationQualifiedNameContext altAnnotationQualifiedName() throws RecognitionException {
		AltAnnotationQualifiedNameContext _localctx = new AltAnnotationQualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_altAnnotationQualifiedName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(924);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 18)) & ~0x3f) == 0 && ((1L << (_la - 18)) & 7429401584631809L) != 0) || _la==IDENTIFIER) {
				{
				{
				setState(919);
				identifier();
				setState(920);
				match(DOT);
				}
				}
				setState(926);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(927);
			match(AT);
			setState(928);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(JADExParser.AT, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public AnnotationFieldValuesContext annotationFieldValues() {
			return getRuleContext(AnnotationFieldValuesContext.class,0);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(930);
			match(AT);
			setState(931);
			qualifiedName();
			}
			setState(934);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(933);
				annotationFieldValues();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationFieldValuesContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public List<AnnotationFieldValueContext> annotationFieldValue() {
			return getRuleContexts(AnnotationFieldValueContext.class);
		}
		public AnnotationFieldValueContext annotationFieldValue(int i) {
			return getRuleContext(AnnotationFieldValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public AnnotationFieldValuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationFieldValues; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterAnnotationFieldValues(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitAnnotationFieldValues(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitAnnotationFieldValues(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationFieldValuesContext annotationFieldValues() throws RecognitionException {
		AnnotationFieldValuesContext _localctx = new AnnotationFieldValuesContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_annotationFieldValues);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(936);
			match(LPAREN);
			setState(945);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				{
				setState(937);
				annotationFieldValue();
				setState(942);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(938);
					match(COMMA);
					setState(939);
					annotationFieldValue();
					}
					}
					setState(944);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(947);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationFieldValueContext extends ParserRuleContext {
		public AnnotationValueContext annotationValue() {
			return getRuleContext(AnnotationValueContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(JADExParser.ASSIGN, 0); }
		public AnnotationFieldValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationFieldValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterAnnotationFieldValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitAnnotationFieldValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitAnnotationFieldValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationFieldValueContext annotationFieldValue() throws RecognitionException {
		AnnotationFieldValueContext _localctx = new AnnotationFieldValueContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_annotationFieldValue);
		try {
			setState(955);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(949);
				if (!( this.IsNotIdentifierAssign() )) throw new FailedPredicateException(this, " this.IsNotIdentifierAssign() ");
				setState(950);
				annotationValue();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(951);
				identifier();
				setState(952);
				match(ASSIGN);
				setState(953);
				annotationValue();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(JADExParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(JADExParser.RBRACE, 0); }
		public List<AnnotationValueContext> annotationValue() {
			return getRuleContexts(AnnotationValueContext.class);
		}
		public AnnotationValueContext annotationValue(int i) {
			return getRuleContext(AnnotationValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public AnnotationValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterAnnotationValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitAnnotationValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitAnnotationValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationValueContext annotationValue() throws RecognitionException {
		AnnotationValueContext _localctx = new AnnotationValueContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_annotationValue);
		int _la;
		try {
			int _alt;
			setState(974);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(957);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(958);
				annotation();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(959);
				match(LBRACE);
				setState(968);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7731720228588715440L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 65985735557099L) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(960);
					annotationValue();
					setState(965);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(961);
							match(COMMA);
							setState(962);
							annotationValue();
							}
							} 
						}
						setState(967);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
					}
					}
				}

				setState(971);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(970);
					match(COMMA);
					}
				}

				setState(973);
				match(RBRACE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public ElementValueArrayInitializerContext elementValueArrayInitializer() {
			return getRuleContext(ElementValueArrayInitializerContext.class,0);
		}
		public ElementValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterElementValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitElementValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitElementValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementValueContext elementValue() throws RecognitionException {
		ElementValueContext _localctx = new ElementValueContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_elementValue);
		try {
			setState(979);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(976);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(977);
				annotation();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(978);
				elementValueArrayInitializer();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementValueArrayInitializerContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(JADExParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(JADExParser.RBRACE, 0); }
		public List<ElementValueContext> elementValue() {
			return getRuleContexts(ElementValueContext.class);
		}
		public ElementValueContext elementValue(int i) {
			return getRuleContext(ElementValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public ElementValueArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValueArrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterElementValueArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitElementValueArrayInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitElementValueArrayInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementValueArrayInitializerContext elementValueArrayInitializer() throws RecognitionException {
		ElementValueArrayInitializerContext _localctx = new ElementValueArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_elementValueArrayInitializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(981);
			match(LBRACE);
			setState(990);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7731720228588715440L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 65985735557099L) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(982);
				elementValue();
				setState(987);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,107,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(983);
						match(COMMA);
						setState(984);
						elementValue();
						}
						} 
					}
					setState(989);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,107,_ctx);
				}
				}
			}

			setState(993);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(992);
				match(COMMA);
				}
			}

			setState(995);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationTypeDeclarationContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(JADExParser.AT, 0); }
		public TerminalNode INTERFACE() { return getToken(JADExParser.INTERFACE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AnnotationTypeBodyContext annotationTypeBody() {
			return getRuleContext(AnnotationTypeBodyContext.class,0);
		}
		public AnnotationTypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterAnnotationTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitAnnotationTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitAnnotationTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationTypeDeclarationContext annotationTypeDeclaration() throws RecognitionException {
		AnnotationTypeDeclarationContext _localctx = new AnnotationTypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_annotationTypeDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(997);
			match(AT);
			setState(998);
			match(INTERFACE);
			setState(999);
			identifier();
			setState(1000);
			annotationTypeBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationTypeBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(JADExParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(JADExParser.RBRACE, 0); }
		public List<AnnotationTypeElementDeclarationContext> annotationTypeElementDeclaration() {
			return getRuleContexts(AnnotationTypeElementDeclarationContext.class);
		}
		public AnnotationTypeElementDeclarationContext annotationTypeElementDeclaration(int i) {
			return getRuleContext(AnnotationTypeElementDeclarationContext.class,i);
		}
		public AnnotationTypeBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterAnnotationTypeBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitAnnotationTypeBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitAnnotationTypeBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationTypeBodyContext annotationTypeBody() throws RecognitionException {
		AnnotationTypeBodyContext _localctx = new AnnotationTypeBodyContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_annotationTypeBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1002);
			match(LBRACE);
			setState(1006);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7183382727791049134L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 67108973L) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				{
				setState(1003);
				annotationTypeElementDeclaration();
				}
				}
				setState(1008);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1009);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationTypeElementDeclarationContext extends ParserRuleContext {
		public AnnotationTypeElementRestContext annotationTypeElementRest() {
			return getRuleContext(AnnotationTypeElementRestContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public AnnotationTypeElementDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeElementDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterAnnotationTypeElementDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitAnnotationTypeElementDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitAnnotationTypeElementDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationTypeElementDeclarationContext annotationTypeElementDeclaration() throws RecognitionException {
		AnnotationTypeElementDeclarationContext _localctx = new AnnotationTypeElementDeclarationContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_annotationTypeElementDeclaration);
		try {
			int _alt;
			setState(1019);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case CLASS:
			case DOUBLE:
			case ENUM:
			case EXPORTS:
			case FINAL:
			case FLOAT:
			case INT:
			case INTERFACE:
			case LONG:
			case MUTABLE:
			case MODULE:
			case NATIVE:
			case NON_SEALED:
			case OPEN:
			case OPENS:
			case PERMITS:
			case PRIVATE:
			case PROTECTED:
			case PROVIDES:
			case PUBLIC:
			case RECORD:
			case REQUIRES:
			case SEALED:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TO:
			case TRANSIENT:
			case TRANSITIVE:
			case USES:
			case VAR:
			case VOLATILE:
			case WHEN:
			case WITH:
			case YIELD:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1014);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,111,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1011);
						modifier();
						}
						} 
					}
					setState(1016);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,111,_ctx);
				}
				setState(1017);
				annotationTypeElementRest();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(1018);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationTypeElementRestContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public AnnotationMethodOrConstantRestContext annotationMethodOrConstantRest() {
			return getRuleContext(AnnotationMethodOrConstantRestContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public RecordDeclarationContext recordDeclaration() {
			return getRuleContext(RecordDeclarationContext.class,0);
		}
		public AnnotationTypeElementRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeElementRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterAnnotationTypeElementRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitAnnotationTypeElementRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitAnnotationTypeElementRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationTypeElementRestContext annotationTypeElementRest() throws RecognitionException {
		AnnotationTypeElementRestContext _localctx = new AnnotationTypeElementRestContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_annotationTypeElementRest);
		try {
			setState(1045);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,118,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1021);
				typeType();
				setState(1022);
				annotationMethodOrConstantRest();
				setState(1023);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1025);
				classDeclaration();
				setState(1027);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
				case 1:
					{
					setState(1026);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1029);
				interfaceDeclaration();
				setState(1031);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
				case 1:
					{
					setState(1030);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1033);
				enumDeclaration();
				setState(1035);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
				case 1:
					{
					setState(1034);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1037);
				annotationTypeDeclaration();
				setState(1039);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
				case 1:
					{
					setState(1038);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1041);
				recordDeclaration();
				setState(1043);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
				case 1:
					{
					setState(1042);
					match(SEMI);
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationMethodOrConstantRestContext extends ParserRuleContext {
		public AnnotationMethodRestContext annotationMethodRest() {
			return getRuleContext(AnnotationMethodRestContext.class,0);
		}
		public AnnotationConstantRestContext annotationConstantRest() {
			return getRuleContext(AnnotationConstantRestContext.class,0);
		}
		public AnnotationMethodOrConstantRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationMethodOrConstantRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterAnnotationMethodOrConstantRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitAnnotationMethodOrConstantRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitAnnotationMethodOrConstantRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationMethodOrConstantRestContext annotationMethodOrConstantRest() throws RecognitionException {
		AnnotationMethodOrConstantRestContext _localctx = new AnnotationMethodOrConstantRestContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_annotationMethodOrConstantRest);
		try {
			setState(1049);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,119,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1047);
				annotationMethodRest();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1048);
				annotationConstantRest();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationMethodRestContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public AnnotationMethodRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationMethodRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterAnnotationMethodRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitAnnotationMethodRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitAnnotationMethodRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationMethodRestContext annotationMethodRest() throws RecognitionException {
		AnnotationMethodRestContext _localctx = new AnnotationMethodRestContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_annotationMethodRest);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1051);
			identifier();
			setState(1052);
			match(LPAREN);
			setState(1053);
			match(RPAREN);
			setState(1055);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(1054);
				defaultValue();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationConstantRestContext extends ParserRuleContext {
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public AnnotationConstantRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationConstantRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterAnnotationConstantRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitAnnotationConstantRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitAnnotationConstantRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationConstantRestContext annotationConstantRest() throws RecognitionException {
		AnnotationConstantRestContext _localctx = new AnnotationConstantRestContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_annotationConstantRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1057);
			variableDeclarators();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefaultValueContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(JADExParser.DEFAULT, 0); }
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitDefaultValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitDefaultValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1059);
			match(DEFAULT);
			setState(1060);
			elementValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModuleDeclarationContext extends ParserRuleContext {
		public TerminalNode MODULE() { return getToken(JADExParser.MODULE, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(JADExParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(JADExParser.RBRACE, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode OPEN() { return getToken(JADExParser.OPEN, 0); }
		public List<ModuleDirectiveContext> moduleDirective() {
			return getRuleContexts(ModuleDirectiveContext.class);
		}
		public ModuleDirectiveContext moduleDirective(int i) {
			return getRuleContext(ModuleDirectiveContext.class,i);
		}
		public ModuleDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterModuleDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitModuleDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitModuleDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleDeclarationContext moduleDeclaration() throws RecognitionException {
		ModuleDeclarationContext _localctx = new ModuleDeclarationContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_moduleDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1065);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(1062);
				annotation();
				}
				}
				setState(1067);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1069);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN) {
				{
				setState(1068);
				match(OPEN);
				}
			}

			setState(1071);
			match(MODULE);
			setState(1072);
			qualifiedName();
			setState(1073);
			match(LBRACE);
			setState(1077);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -9223292597139406848L) != 0)) {
				{
				{
				setState(1074);
				moduleDirective();
				}
				}
				setState(1079);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1080);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModuleDirectiveContext extends ParserRuleContext {
		public TerminalNode REQUIRES() { return getToken(JADExParser.REQUIRES, 0); }
		public List<QualifiedNameContext> qualifiedName() {
			return getRuleContexts(QualifiedNameContext.class);
		}
		public QualifiedNameContext qualifiedName(int i) {
			return getRuleContext(QualifiedNameContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public List<RequiresModifierContext> requiresModifier() {
			return getRuleContexts(RequiresModifierContext.class);
		}
		public RequiresModifierContext requiresModifier(int i) {
			return getRuleContext(RequiresModifierContext.class,i);
		}
		public TerminalNode EXPORTS() { return getToken(JADExParser.EXPORTS, 0); }
		public TerminalNode TO() { return getToken(JADExParser.TO, 0); }
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public TerminalNode OPENS() { return getToken(JADExParser.OPENS, 0); }
		public TerminalNode USES() { return getToken(JADExParser.USES, 0); }
		public TerminalNode PROVIDES() { return getToken(JADExParser.PROVIDES, 0); }
		public TerminalNode WITH() { return getToken(JADExParser.WITH, 0); }
		public ModuleDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterModuleDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitModuleDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitModuleDirective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleDirectiveContext moduleDirective() throws RecognitionException {
		ModuleDirectiveContext _localctx = new ModuleDirectiveContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_moduleDirective);
		int _la;
		try {
			int _alt;
			setState(1139);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case REQUIRES:
				enterOuterAlt(_localctx, 1);
				{
				setState(1082);
				match(REQUIRES);
				setState(1086);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,124,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1083);
						requiresModifier();
						}
						} 
					}
					setState(1088);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,124,_ctx);
				}
				setState(1089);
				qualifiedName();
				setState(1090);
				match(SEMI);
				}
				break;
			case EXPORTS:
				enterOuterAlt(_localctx, 2);
				{
				setState(1092);
				match(EXPORTS);
				setState(1093);
				qualifiedName();
				setState(1103);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TO) {
					{
					setState(1094);
					match(TO);
					setState(1095);
					qualifiedName();
					setState(1100);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(1096);
						match(COMMA);
						setState(1097);
						qualifiedName();
						}
						}
						setState(1102);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(1105);
				match(SEMI);
				}
				break;
			case OPENS:
				enterOuterAlt(_localctx, 3);
				{
				setState(1107);
				match(OPENS);
				setState(1108);
				qualifiedName();
				setState(1118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TO) {
					{
					setState(1109);
					match(TO);
					setState(1110);
					qualifiedName();
					setState(1115);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(1111);
						match(COMMA);
						setState(1112);
						qualifiedName();
						}
						}
						setState(1117);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(1120);
				match(SEMI);
				}
				break;
			case USES:
				enterOuterAlt(_localctx, 4);
				{
				setState(1122);
				match(USES);
				setState(1123);
				qualifiedName();
				setState(1124);
				match(SEMI);
				}
				break;
			case PROVIDES:
				enterOuterAlt(_localctx, 5);
				{
				setState(1126);
				match(PROVIDES);
				setState(1127);
				qualifiedName();
				setState(1128);
				match(WITH);
				setState(1129);
				qualifiedName();
				setState(1134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1130);
					match(COMMA);
					setState(1131);
					qualifiedName();
					}
					}
					setState(1136);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1137);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RequiresModifierContext extends ParserRuleContext {
		public TerminalNode TRANSITIVE() { return getToken(JADExParser.TRANSITIVE, 0); }
		public TerminalNode STATIC() { return getToken(JADExParser.STATIC, 0); }
		public RequiresModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requiresModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterRequiresModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitRequiresModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitRequiresModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequiresModifierContext requiresModifier() throws RecognitionException {
		RequiresModifierContext _localctx = new RequiresModifierContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_requiresModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1141);
			_la = _input.LA(1);
			if ( !(_la==STATIC || _la==TRANSITIVE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RecordDeclarationContext extends ParserRuleContext {
		public TerminalNode RECORD() { return getToken(JADExParser.RECORD, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public RecordHeaderContext recordHeader() {
			return getRuleContext(RecordHeaderContext.class,0);
		}
		public RecordBodyContext recordBody() {
			return getRuleContext(RecordBodyContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TerminalNode IMPLEMENTS() { return getToken(JADExParser.IMPLEMENTS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public RecordDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterRecordDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitRecordDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitRecordDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecordDeclarationContext recordDeclaration() throws RecognitionException {
		RecordDeclarationContext _localctx = new RecordDeclarationContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_recordDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1143);
			match(RECORD);
			setState(1144);
			identifier();
			setState(1146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1145);
				typeParameters();
				}
			}

			setState(1148);
			recordHeader();
			setState(1151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(1149);
				match(IMPLEMENTS);
				setState(1150);
				typeList();
				}
			}

			setState(1153);
			recordBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RecordHeaderContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public RecordComponentListContext recordComponentList() {
			return getRuleContext(RecordComponentListContext.class,0);
		}
		public RecordHeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordHeader; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterRecordHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitRecordHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitRecordHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecordHeaderContext recordHeader() throws RecognitionException {
		RecordHeaderContext _localctx = new RecordHeaderContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_recordHeader);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1155);
			match(LPAREN);
			setState(1157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7781259858849529264L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 105L) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(1156);
				recordComponentList();
				}
			}

			setState(1159);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RecordComponentListContext extends ParserRuleContext {
		public List<RecordComponentContext> recordComponent() {
			return getRuleContexts(RecordComponentContext.class);
		}
		public RecordComponentContext recordComponent(int i) {
			return getRuleContext(RecordComponentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public RecordComponentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordComponentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterRecordComponentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitRecordComponentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitRecordComponentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecordComponentListContext recordComponentList() throws RecognitionException {
		RecordComponentListContext _localctx = new RecordComponentListContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_recordComponentList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1161);
			recordComponent();
			setState(1166);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,134,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1162);
					match(COMMA);
					setState(1163);
					recordComponent();
					}
					} 
				}
				setState(1168);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,134,_ctx);
			}
			setState(1169);
			if (!( this.DoLastRecordComponent() )) throw new FailedPredicateException(this, " this.DoLastRecordComponent() ");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RecordComponentContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode ELLIPSIS() { return getToken(JADExParser.ELLIPSIS, 0); }
		public RecordComponentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordComponent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterRecordComponent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitRecordComponent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitRecordComponent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecordComponentContext recordComponent() throws RecognitionException {
		RecordComponentContext _localctx = new RecordComponentContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_recordComponent);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1174);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,135,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1171);
					annotation();
					}
					} 
				}
				setState(1176);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,135,_ctx);
			}
			setState(1177);
			typeType();
			setState(1185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AT || _la==ELLIPSIS) {
				{
				setState(1181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AT) {
					{
					{
					setState(1178);
					annotation();
					}
					}
					setState(1183);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1184);
				match(ELLIPSIS);
				}
			}

			setState(1187);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RecordBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(JADExParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(JADExParser.RBRACE, 0); }
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public List<CompactConstructorDeclarationContext> compactConstructorDeclaration() {
			return getRuleContexts(CompactConstructorDeclarationContext.class);
		}
		public CompactConstructorDeclarationContext compactConstructorDeclaration(int i) {
			return getRuleContext(CompactConstructorDeclarationContext.class,i);
		}
		public RecordBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterRecordBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitRecordBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitRecordBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecordBodyContext recordBody() throws RecognitionException {
		RecordBodyContext _localctx = new RecordBodyContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_recordBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1189);
			match(LBRACE);
			setState(1194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7183382727791049134L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 2218786927L) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(1192);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,138,_ctx) ) {
				case 1:
					{
					setState(1190);
					classBodyDeclaration();
					}
					break;
				case 2:
					{
					setState(1191);
					compactConstructorDeclaration();
					}
					break;
				}
				}
				setState(1196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1197);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(JADExParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(JADExParser.RBRACE, 0); }
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1199);
			match(LBRACE);
			setState(1203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -5332262526231587206L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 65985802665979L) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				{
				setState(1200);
				blockStatement();
				}
				}
				setState(1205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1206);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockStatementContext extends ParserRuleContext {
		public LocalVariableDeclarationContext localVariableDeclaration() {
			return getRuleContext(LocalVariableDeclarationContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public LocalTypeDeclarationContext localTypeDeclaration() {
			return getRuleContext(LocalTypeDeclarationContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitBlockStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitBlockStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_blockStatement);
		try {
			setState(1213);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,141,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1208);
				localVariableDeclaration();
				setState(1209);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1211);
				localTypeDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1212);
				statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LocalVariableDeclarationContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(JADExParser.VAR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(JADExParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public LocalVariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVariableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterLocalVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitLocalVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitLocalVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocalVariableDeclarationContext localVariableDeclaration() throws RecognitionException {
		LocalVariableDeclarationContext _localctx = new LocalVariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_localVariableDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1218);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,142,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1215);
					variableModifier();
					}
					} 
				}
				setState(1220);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,142,_ctx);
			}
			setState(1229);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,143,_ctx) ) {
			case 1:
				{
				setState(1221);
				match(VAR);
				setState(1222);
				identifier();
				setState(1223);
				match(ASSIGN);
				setState(1224);
				expression(0);
				}
				break;
			case 2:
				{
				setState(1226);
				typeType();
				setState(1227);
				variableDeclarators();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JADExParser.IDENTIFIER, 0); }
		public TerminalNode MODULE() { return getToken(JADExParser.MODULE, 0); }
		public TerminalNode OPEN() { return getToken(JADExParser.OPEN, 0); }
		public TerminalNode REQUIRES() { return getToken(JADExParser.REQUIRES, 0); }
		public TerminalNode EXPORTS() { return getToken(JADExParser.EXPORTS, 0); }
		public TerminalNode OPENS() { return getToken(JADExParser.OPENS, 0); }
		public TerminalNode TO() { return getToken(JADExParser.TO, 0); }
		public TerminalNode USES() { return getToken(JADExParser.USES, 0); }
		public TerminalNode PROVIDES() { return getToken(JADExParser.PROVIDES, 0); }
		public TerminalNode WHEN() { return getToken(JADExParser.WHEN, 0); }
		public TerminalNode WITH() { return getToken(JADExParser.WITH, 0); }
		public TerminalNode TRANSITIVE() { return getToken(JADExParser.TRANSITIVE, 0); }
		public TerminalNode YIELD() { return getToken(JADExParser.YIELD, 0); }
		public TerminalNode SEALED() { return getToken(JADExParser.SEALED, 0); }
		public TerminalNode PERMITS() { return getToken(JADExParser.PERMITS, 0); }
		public TerminalNode RECORD() { return getToken(JADExParser.RECORD, 0); }
		public TerminalNode VAR() { return getToken(JADExParser.VAR, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1231);
			_la = _input.LA(1);
			if ( !(((((_la - 18)) & ~0x3f) == 0 && ((1L << (_la - 18)) & 7429401584631809L) != 0) || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeIdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JADExParser.IDENTIFIER, 0); }
		public TerminalNode MODULE() { return getToken(JADExParser.MODULE, 0); }
		public TerminalNode OPEN() { return getToken(JADExParser.OPEN, 0); }
		public TerminalNode REQUIRES() { return getToken(JADExParser.REQUIRES, 0); }
		public TerminalNode EXPORTS() { return getToken(JADExParser.EXPORTS, 0); }
		public TerminalNode OPENS() { return getToken(JADExParser.OPENS, 0); }
		public TerminalNode TO() { return getToken(JADExParser.TO, 0); }
		public TerminalNode USES() { return getToken(JADExParser.USES, 0); }
		public TerminalNode PROVIDES() { return getToken(JADExParser.PROVIDES, 0); }
		public TerminalNode WITH() { return getToken(JADExParser.WITH, 0); }
		public TerminalNode TRANSITIVE() { return getToken(JADExParser.TRANSITIVE, 0); }
		public TerminalNode SEALED() { return getToken(JADExParser.SEALED, 0); }
		public TypeIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterTypeIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitTypeIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitTypeIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeIdentifierContext typeIdentifier() throws RecognitionException {
		TypeIdentifierContext _localctx = new TypeIdentifierContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_typeIdentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1233);
			_la = _input.LA(1);
			if ( !(((((_la - 18)) & ~0x3f) == 0 && ((1L << (_la - 18)) & 2292483121250305L) != 0) || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LocalTypeDeclarationContext extends ParserRuleContext {
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public RecordDeclarationContext recordDeclaration() {
			return getRuleContext(RecordDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public LocalTypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localTypeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterLocalTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitLocalTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitLocalTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocalTypeDeclarationContext localTypeDeclaration() throws RecognitionException {
		LocalTypeDeclarationContext _localctx = new LocalTypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_localTypeDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3683436968542210L) != 0) || _la==AT) {
				{
				{
				setState(1235);
				classOrInterfaceModifier();
				}
				}
				setState(1240);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1245);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CLASS:
				{
				setState(1241);
				classDeclaration();
				}
				break;
			case INTERFACE:
				{
				setState(1242);
				interfaceDeclaration();
				}
				break;
			case RECORD:
				{
				setState(1243);
				recordDeclaration();
				}
				break;
			case ENUM:
				{
				setState(1244);
				enumDeclaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public BlockContext blockLabel;
		public ExpressionContext statementExpression;
		public IdentifierContext identifierLabel;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode ASSERT() { return getToken(JADExParser.ASSERT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public TerminalNode COLON() { return getToken(JADExParser.COLON, 0); }
		public TerminalNode IF() { return getToken(JADExParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(JADExParser.ELSE, 0); }
		public TerminalNode FOR() { return getToken(JADExParser.FOR, 0); }
		public ForControlContext forControl() {
			return getRuleContext(ForControlContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(JADExParser.WHILE, 0); }
		public TerminalNode DO() { return getToken(JADExParser.DO, 0); }
		public TerminalNode TRY() { return getToken(JADExParser.TRY, 0); }
		public FinallyBlockContext finallyBlock() {
			return getRuleContext(FinallyBlockContext.class,0);
		}
		public List<CatchClauseContext> catchClause() {
			return getRuleContexts(CatchClauseContext.class);
		}
		public CatchClauseContext catchClause(int i) {
			return getRuleContext(CatchClauseContext.class,i);
		}
		public ResourceSpecificationContext resourceSpecification() {
			return getRuleContext(ResourceSpecificationContext.class,0);
		}
		public TerminalNode SWITCH() { return getToken(JADExParser.SWITCH, 0); }
		public TerminalNode LBRACE() { return getToken(JADExParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(JADExParser.RBRACE, 0); }
		public List<SwitchBlockStatementGroupContext> switchBlockStatementGroup() {
			return getRuleContexts(SwitchBlockStatementGroupContext.class);
		}
		public SwitchBlockStatementGroupContext switchBlockStatementGroup(int i) {
			return getRuleContext(SwitchBlockStatementGroupContext.class,i);
		}
		public List<SwitchLabelContext> switchLabel() {
			return getRuleContexts(SwitchLabelContext.class);
		}
		public SwitchLabelContext switchLabel(int i) {
			return getRuleContext(SwitchLabelContext.class,i);
		}
		public TerminalNode SYNCHRONIZED() { return getToken(JADExParser.SYNCHRONIZED, 0); }
		public TerminalNode RETURN() { return getToken(JADExParser.RETURN, 0); }
		public TerminalNode THROW() { return getToken(JADExParser.THROW, 0); }
		public TerminalNode BREAK() { return getToken(JADExParser.BREAK, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode CONTINUE() { return getToken(JADExParser.CONTINUE, 0); }
		public TerminalNode YIELD() { return getToken(JADExParser.YIELD, 0); }
		public SwitchExpressionContext switchExpression() {
			return getRuleContext(SwitchExpressionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_statement);
		int _la;
		try {
			int _alt;
			setState(1370);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,159,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1247);
				((StatementContext)_localctx).blockLabel = block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1248);
				match(ASSERT);
				setState(1249);
				expression(0);
				setState(1252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(1250);
					match(COLON);
					setState(1251);
					expression(0);
					}
				}

				setState(1254);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1256);
				match(IF);
				setState(1257);
				match(LPAREN);
				setState(1258);
				expression(0);
				setState(1259);
				match(RPAREN);
				setState(1260);
				statement();
				setState(1263);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,147,_ctx) ) {
				case 1:
					{
					setState(1261);
					match(ELSE);
					setState(1262);
					statement();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1265);
				match(FOR);
				setState(1266);
				match(LPAREN);
				setState(1267);
				forControl();
				setState(1268);
				match(RPAREN);
				setState(1269);
				statement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1271);
				match(WHILE);
				setState(1272);
				match(LPAREN);
				setState(1273);
				expression(0);
				setState(1274);
				match(RPAREN);
				setState(1275);
				statement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1277);
				match(DO);
				setState(1278);
				statement();
				setState(1279);
				match(WHILE);
				setState(1280);
				match(LPAREN);
				setState(1281);
				expression(0);
				setState(1282);
				match(RPAREN);
				setState(1283);
				match(SEMI);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1285);
				match(TRY);
				setState(1286);
				block();
				setState(1296);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CATCH:
					{
					setState(1288); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(1287);
						catchClause();
						}
						}
						setState(1290); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==CATCH );
					setState(1293);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==FINALLY) {
						{
						setState(1292);
						finallyBlock();
						}
					}

					}
					break;
				case FINALLY:
					{
					setState(1295);
					finallyBlock();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1298);
				match(TRY);
				setState(1299);
				resourceSpecification();
				setState(1300);
				block();
				setState(1304);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CATCH) {
					{
					{
					setState(1301);
					catchClause();
					}
					}
					setState(1306);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1308);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FINALLY) {
					{
					setState(1307);
					finallyBlock();
					}
				}

				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1310);
				match(SWITCH);
				setState(1311);
				match(LPAREN);
				setState(1312);
				expression(0);
				setState(1313);
				match(RPAREN);
				setState(1314);
				match(LBRACE);
				setState(1318);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,153,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1315);
						switchBlockStatementGroup();
						}
						} 
					}
					setState(1320);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,153,_ctx);
				}
				setState(1324);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CASE || _la==DEFAULT) {
					{
					{
					setState(1321);
					switchLabel();
					}
					}
					setState(1326);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1327);
				match(RBRACE);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1329);
				match(SYNCHRONIZED);
				setState(1330);
				match(LPAREN);
				setState(1331);
				expression(0);
				setState(1332);
				match(RPAREN);
				setState(1333);
				block();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1335);
				match(RETURN);
				setState(1337);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7731720228588715440L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 65985731362795L) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(1336);
					expression(0);
					}
				}

				setState(1339);
				match(SEMI);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(1340);
				match(THROW);
				setState(1341);
				expression(0);
				setState(1342);
				match(SEMI);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(1344);
				match(BREAK);
				setState(1346);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 18)) & ~0x3f) == 0 && ((1L << (_la - 18)) & 7429401584631809L) != 0) || _la==IDENTIFIER) {
					{
					setState(1345);
					identifier();
					}
				}

				setState(1348);
				match(SEMI);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(1349);
				match(CONTINUE);
				setState(1351);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 18)) & ~0x3f) == 0 && ((1L << (_la - 18)) & 7429401584631809L) != 0) || _la==IDENTIFIER) {
					{
					setState(1350);
					identifier();
					}
				}

				setState(1353);
				match(SEMI);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(1354);
				match(YIELD);
				setState(1355);
				expression(0);
				setState(1356);
				match(SEMI);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(1358);
				match(SEMI);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(1359);
				((StatementContext)_localctx).statementExpression = expression(0);
				setState(1360);
				match(SEMI);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(1362);
				switchExpression();
				setState(1364);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,158,_ctx) ) {
				case 1:
					{
					setState(1363);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(1366);
				((StatementContext)_localctx).identifierLabel = identifier();
				setState(1367);
				match(COLON);
				setState(1368);
				statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CatchClauseContext extends ParserRuleContext {
		public TerminalNode CATCH() { return getToken(JADExParser.CATCH, 0); }
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public CatchTypeContext catchType() {
			return getRuleContext(CatchTypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public CatchClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterCatchClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitCatchClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitCatchClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatchClauseContext catchClause() throws RecognitionException {
		CatchClauseContext _localctx = new CatchClauseContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_catchClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1372);
			match(CATCH);
			setState(1373);
			match(LPAREN);
			setState(1377);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==MUTABLE || _la==AT) {
				{
				{
				setState(1374);
				variableModifier();
				}
				}
				setState(1379);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1380);
			catchType();
			setState(1381);
			identifier();
			setState(1382);
			match(RPAREN);
			setState(1383);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CatchTypeContext extends ParserRuleContext {
		public List<QualifiedNameContext> qualifiedName() {
			return getRuleContexts(QualifiedNameContext.class);
		}
		public QualifiedNameContext qualifiedName(int i) {
			return getRuleContext(QualifiedNameContext.class,i);
		}
		public List<TerminalNode> BITOR() { return getTokens(JADExParser.BITOR); }
		public TerminalNode BITOR(int i) {
			return getToken(JADExParser.BITOR, i);
		}
		public CatchTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterCatchType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitCatchType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitCatchType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatchTypeContext catchType() throws RecognitionException {
		CatchTypeContext _localctx = new CatchTypeContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_catchType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1385);
			qualifiedName();
			setState(1390);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITOR) {
				{
				{
				setState(1386);
				match(BITOR);
				setState(1387);
				qualifiedName();
				}
				}
				setState(1392);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FinallyBlockContext extends ParserRuleContext {
		public TerminalNode FINALLY() { return getToken(JADExParser.FINALLY, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FinallyBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finallyBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterFinallyBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitFinallyBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitFinallyBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FinallyBlockContext finallyBlock() throws RecognitionException {
		FinallyBlockContext _localctx = new FinallyBlockContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_finallyBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1393);
			match(FINALLY);
			setState(1394);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ResourceSpecificationContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public ResourcesContext resources() {
			return getRuleContext(ResourcesContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(JADExParser.SEMI, 0); }
		public ResourceSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resourceSpecification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterResourceSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitResourceSpecification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitResourceSpecification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResourceSpecificationContext resourceSpecification() throws RecognitionException {
		ResourceSpecificationContext _localctx = new ResourceSpecificationContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_resourceSpecification);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1396);
			match(LPAREN);
			setState(1397);
			resources();
			setState(1399);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(1398);
				match(SEMI);
				}
			}

			setState(1401);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ResourcesContext extends ParserRuleContext {
		public List<ResourceContext> resource() {
			return getRuleContexts(ResourceContext.class);
		}
		public ResourceContext resource(int i) {
			return getRuleContext(ResourceContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(JADExParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(JADExParser.SEMI, i);
		}
		public ResourcesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resources; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterResources(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitResources(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitResources(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResourcesContext resources() throws RecognitionException {
		ResourcesContext _localctx = new ResourcesContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_resources);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1403);
			resource();
			setState(1408);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,163,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1404);
					match(SEMI);
					setState(1405);
					resource();
					}
					} 
				}
				setState(1410);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,163,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ResourceContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(JADExParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public TerminalNode VAR() { return getToken(JADExParser.VAR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public TerminalNode QUESTION() { return getToken(JADExParser.QUESTION, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public ResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitResource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitResource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResourceContext resource() throws RecognitionException {
		ResourceContext _localctx = new ResourceContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_resource);
		int _la;
		try {
			setState(1431);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,167,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1414);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FINAL || _la==MUTABLE || _la==AT) {
					{
					{
					setState(1411);
					variableModifier();
					}
					}
					setState(1416);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1425);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,166,_ctx) ) {
				case 1:
					{
					setState(1417);
					classOrInterfaceType();
					setState(1419);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==QUESTION) {
						{
						setState(1418);
						match(QUESTION);
						}
					}

					setState(1421);
					variableDeclaratorId();
					}
					break;
				case 2:
					{
					setState(1423);
					match(VAR);
					setState(1424);
					identifier();
					}
					break;
				}
				setState(1427);
				match(ASSIGN);
				setState(1428);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1430);
				qualifiedName();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchBlockStatementGroupContext extends ParserRuleContext {
		public List<SwitchLabelContext> switchLabel() {
			return getRuleContexts(SwitchLabelContext.class);
		}
		public SwitchLabelContext switchLabel(int i) {
			return getRuleContext(SwitchLabelContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(JADExParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(JADExParser.COLON, i);
		}
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public SwitchBlockStatementGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBlockStatementGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterSwitchBlockStatementGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitSwitchBlockStatementGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitSwitchBlockStatementGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchBlockStatementGroupContext switchBlockStatementGroup() throws RecognitionException {
		SwitchBlockStatementGroupContext _localctx = new SwitchBlockStatementGroupContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_switchBlockStatementGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1436); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1433);
				switchLabel();
				setState(1434);
				match(COLON);
				}
				}
				setState(1438); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CASE || _la==DEFAULT );
			setState(1441); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1440);
				blockStatement();
				}
				}
				setState(1443); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & -5332262526231587206L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 65985802665979L) != 0) || _la==AT || _la==IDENTIFIER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchLabelContext extends ParserRuleContext {
		public ExpressionContext constantExpression;
		public Token enumConstantName;
		public IdentifierContext varName;
		public TerminalNode CASE() { return getToken(JADExParser.CASE, 0); }
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JADExParser.IDENTIFIER, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(JADExParser.DEFAULT, 0); }
		public SwitchLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterSwitchLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitSwitchLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitSwitchLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchLabelContext switchLabel() throws RecognitionException {
		SwitchLabelContext _localctx = new SwitchLabelContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_switchLabel);
		try {
			setState(1454);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1445);
				match(CASE);
				setState(1451);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,170,_ctx) ) {
				case 1:
					{
					setState(1446);
					((SwitchLabelContext)_localctx).constantExpression = expression(0);
					}
					break;
				case 2:
					{
					setState(1447);
					((SwitchLabelContext)_localctx).enumConstantName = match(IDENTIFIER);
					}
					break;
				case 3:
					{
					setState(1448);
					typeType();
					setState(1449);
					((SwitchLabelContext)_localctx).varName = identifier();
					}
					break;
				}
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1453);
				match(DEFAULT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForControlContext extends ParserRuleContext {
		public ExpressionListContext forUpdate;
		public EnhancedForControlContext enhancedForControl() {
			return getRuleContext(EnhancedForControlContext.class,0);
		}
		public List<TerminalNode> SEMI() { return getTokens(JADExParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(JADExParser.SEMI, i);
		}
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForControlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forControl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterForControl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitForControl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitForControl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForControlContext forControl() throws RecognitionException {
		ForControlContext _localctx = new ForControlContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_forControl);
		int _la;
		try {
			setState(1468);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,175,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1456);
				enhancedForControl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1458);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7731720224292699568L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 65985731362795L) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(1457);
					forInit();
					}
				}

				setState(1460);
				match(SEMI);
				setState(1462);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7731720228588715440L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 65985731362795L) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(1461);
					expression(0);
					}
				}

				setState(1464);
				match(SEMI);
				setState(1466);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7731720228588715440L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 65985731362795L) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(1465);
					((ForControlContext)_localctx).forUpdate = expressionList();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForInitContext extends ParserRuleContext {
		public LocalVariableDeclarationContext localVariableDeclaration() {
			return getRuleContext(LocalVariableDeclarationContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitForInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitForInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_forInit);
		try {
			setState(1472);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,176,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1470);
				localVariableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1471);
				expressionList();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnhancedForControlContext extends ParserRuleContext {
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public TerminalNode COLON() { return getToken(JADExParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode VAR() { return getToken(JADExParser.VAR, 0); }
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public EnhancedForControlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enhancedForControl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterEnhancedForControl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitEnhancedForControl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitEnhancedForControl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnhancedForControlContext enhancedForControl() throws RecognitionException {
		EnhancedForControlContext _localctx = new EnhancedForControlContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_enhancedForControl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1477);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,177,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1474);
					variableModifier();
					}
					} 
				}
				setState(1479);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,177,_ctx);
			}
			setState(1482);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,178,_ctx) ) {
			case 1:
				{
				setState(1480);
				typeType();
				}
				break;
			case 2:
				{
				setState(1481);
				match(VAR);
				}
				break;
			}
			setState(1484);
			variableDeclaratorId();
			setState(1485);
			match(COLON);
			setState(1486);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1488);
			expression(0);
			setState(1493);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1489);
				match(COMMA);
				setState(1490);
				expression(0);
				}
				}
				setState(1495);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodCallContext extends ParserRuleContext {
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode THIS() { return getToken(JADExParser.THIS, 0); }
		public TerminalNode SUPER() { return getToken(JADExParser.SUPER, 0); }
		public MethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitMethodCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitMethodCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodCallContext methodCall() throws RecognitionException {
		MethodCallContext _localctx = new MethodCallContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_methodCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1499);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXPORTS:
			case MODULE:
			case OPEN:
			case OPENS:
			case PERMITS:
			case PROVIDES:
			case RECORD:
			case REQUIRES:
			case SEALED:
			case TO:
			case TRANSITIVE:
			case USES:
			case VAR:
			case WHEN:
			case WITH:
			case YIELD:
			case IDENTIFIER:
				{
				setState(1496);
				identifier();
				}
				break;
			case THIS:
				{
				setState(1497);
				match(THIS);
				}
				break;
			case SUPER:
				{
				setState(1498);
				match(SUPER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1501);
			arguments();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TernaryExpressionContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode COLON() { return getToken(JADExParser.COLON, 0); }
		public TerminalNode QUESTION() { return getToken(JADExParser.QUESTION, 0); }
		public TernaryExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterTernaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitTernaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitTernaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstanceOfOperatorExpressionContext extends ExpressionContext {
		public Token bop;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INSTANCEOF() { return getToken(JADExParser.INSTANCEOF, 0); }
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public InstanceOfOperatorExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterInstanceOfOperatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitInstanceOfOperatorExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitInstanceOfOperatorExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryOperatorExpressionContext extends ExpressionContext {
		public Token prefix;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ADD() { return getToken(JADExParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(JADExParser.SUB, 0); }
		public TerminalNode INC() { return getToken(JADExParser.INC, 0); }
		public TerminalNode DEC() { return getToken(JADExParser.DEC, 0); }
		public TerminalNode TILDE() { return getToken(JADExParser.TILDE, 0); }
		public TerminalNode BANG() { return getToken(JADExParser.BANG, 0); }
		public UnaryOperatorExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterUnaryOperatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitUnaryOperatorExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitUnaryOperatorExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryExpressionContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public PrimaryExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitPrimaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitPrimaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ObjectCreationExpressionContext extends ExpressionContext {
		public TerminalNode NEW() { return getToken(JADExParser.NEW, 0); }
		public CreatorContext creator() {
			return getRuleContext(CreatorContext.class,0);
		}
		public ObjectCreationExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterObjectCreationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitObjectCreationExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitObjectCreationExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionLambdaContext extends ExpressionContext {
		public LambdaExpressionContext lambdaExpression() {
			return getRuleContext(LambdaExpressionContext.class,0);
		}
		public ExpressionLambdaContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterExpressionLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitExpressionLambda(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitExpressionLambda(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostIncrementDecrementOperatorExpressionContext extends ExpressionContext {
		public Token postfix;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INC() { return getToken(JADExParser.INC, 0); }
		public TerminalNode DEC() { return getToken(JADExParser.DEC, 0); }
		public PostIncrementDecrementOperatorExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterPostIncrementDecrementOperatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitPostIncrementDecrementOperatorExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitPostIncrementDecrementOperatorExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ElvisExpressionContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ELVIS() { return getToken(JADExParser.ELVIS, 0); }
		public ElvisExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterElvisExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitElvisExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitElvisExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MemberReferenceExpressionContext extends ExpressionContext {
		public Token bop;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(JADExParser.DOT, 0); }
		public TerminalNode NULLSAFE() { return getToken(JADExParser.NULLSAFE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public TerminalNode THIS() { return getToken(JADExParser.THIS, 0); }
		public TerminalNode NEW() { return getToken(JADExParser.NEW, 0); }
		public InnerCreatorContext innerCreator() {
			return getRuleContext(InnerCreatorContext.class,0);
		}
		public TerminalNode SUPER() { return getToken(JADExParser.SUPER, 0); }
		public SuperSuffixContext superSuffix() {
			return getRuleContext(SuperSuffixContext.class,0);
		}
		public ExplicitGenericInvocationContext explicitGenericInvocation() {
			return getRuleContext(ExplicitGenericInvocationContext.class,0);
		}
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public MemberReferenceExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterMemberReferenceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitMemberReferenceExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitMemberReferenceExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionSwitchContext extends ExpressionContext {
		public SwitchExpressionContext switchExpression() {
			return getRuleContext(SwitchExpressionContext.class,0);
		}
		public ExpressionSwitchContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterExpressionSwitch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitExpressionSwitch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitExpressionSwitch(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinaryOperatorExpressionContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(JADExParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(JADExParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(JADExParser.MOD, 0); }
		public TerminalNode ADD() { return getToken(JADExParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(JADExParser.SUB, 0); }
		public List<TerminalNode> LT() { return getTokens(JADExParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(JADExParser.LT, i);
		}
		public List<TerminalNode> GT() { return getTokens(JADExParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(JADExParser.GT, i);
		}
		public TerminalNode LE() { return getToken(JADExParser.LE, 0); }
		public TerminalNode GE() { return getToken(JADExParser.GE, 0); }
		public TerminalNode EQUAL() { return getToken(JADExParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(JADExParser.NOTEQUAL, 0); }
		public TerminalNode BITAND() { return getToken(JADExParser.BITAND, 0); }
		public TerminalNode CARET() { return getToken(JADExParser.CARET, 0); }
		public TerminalNode BITOR() { return getToken(JADExParser.BITOR, 0); }
		public TerminalNode AND() { return getToken(JADExParser.AND, 0); }
		public TerminalNode OR() { return getToken(JADExParser.OR, 0); }
		public TerminalNode ASSIGN() { return getToken(JADExParser.ASSIGN, 0); }
		public TerminalNode ADD_ASSIGN() { return getToken(JADExParser.ADD_ASSIGN, 0); }
		public TerminalNode SUB_ASSIGN() { return getToken(JADExParser.SUB_ASSIGN, 0); }
		public TerminalNode MUL_ASSIGN() { return getToken(JADExParser.MUL_ASSIGN, 0); }
		public TerminalNode DIV_ASSIGN() { return getToken(JADExParser.DIV_ASSIGN, 0); }
		public TerminalNode AND_ASSIGN() { return getToken(JADExParser.AND_ASSIGN, 0); }
		public TerminalNode OR_ASSIGN() { return getToken(JADExParser.OR_ASSIGN, 0); }
		public TerminalNode XOR_ASSIGN() { return getToken(JADExParser.XOR_ASSIGN, 0); }
		public TerminalNode RSHIFT_ASSIGN() { return getToken(JADExParser.RSHIFT_ASSIGN, 0); }
		public TerminalNode URSHIFT_ASSIGN() { return getToken(JADExParser.URSHIFT_ASSIGN, 0); }
		public TerminalNode LSHIFT_ASSIGN() { return getToken(JADExParser.LSHIFT_ASSIGN, 0); }
		public TerminalNode MOD_ASSIGN() { return getToken(JADExParser.MOD_ASSIGN, 0); }
		public BinaryOperatorExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterBinaryOperatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitBinaryOperatorExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitBinaryOperatorExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MethodCallExpressionContext extends ExpressionContext {
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public MethodCallExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterMethodCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitMethodCallExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitMethodCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MethodReferenceExpressionContext extends ExpressionContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode COLONCOLON() { return getToken(JADExParser.COLONCOLON, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode NEW() { return getToken(JADExParser.NEW, 0); }
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MethodReferenceExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterMethodReferenceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitMethodReferenceExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitMethodReferenceExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SquareBracketExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LBRACK() { return getToken(JADExParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(JADExParser.RBRACK, 0); }
		public SquareBracketExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterSquareBracketExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitSquareBracketExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitSquareBracketExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CastExpressionContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public List<TypeTypeContext> typeType() {
			return getRuleContexts(TypeTypeContext.class);
		}
		public TypeTypeContext typeType(int i) {
			return getRuleContext(TypeTypeContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<TerminalNode> BITAND() { return getTokens(JADExParser.BITAND); }
		public TerminalNode BITAND(int i) {
			return getToken(JADExParser.BITAND, i);
		}
		public CastExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterCastExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitCastExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitCastExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 212;
		enterRecursionRule(_localctx, 212, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1546);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,186,_ctx) ) {
			case 1:
				{
				_localctx = new PrimaryExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(1504);
				primary();
				}
				break;
			case 2:
				{
				_localctx = new MethodCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1505);
				methodCall();
				}
				break;
			case 3:
				{
				_localctx = new MethodReferenceExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1506);
				typeType();
				setState(1507);
				match(COLONCOLON);
				setState(1513);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case EXPORTS:
				case MODULE:
				case OPEN:
				case OPENS:
				case PERMITS:
				case PROVIDES:
				case RECORD:
				case REQUIRES:
				case SEALED:
				case TO:
				case TRANSITIVE:
				case USES:
				case VAR:
				case WHEN:
				case WITH:
				case YIELD:
				case LT:
				case IDENTIFIER:
					{
					setState(1509);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(1508);
						typeArguments();
						}
					}

					setState(1511);
					identifier();
					}
					break;
				case NEW:
					{
					setState(1512);
					match(NEW);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 4:
				{
				_localctx = new MethodReferenceExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1515);
				classType();
				setState(1516);
				match(COLONCOLON);
				setState(1518);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1517);
					typeArguments();
					}
				}

				setState(1520);
				match(NEW);
				}
				break;
			case 5:
				{
				_localctx = new ExpressionSwitchContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1522);
				switchExpression();
				}
				break;
			case 6:
				{
				_localctx = new UnaryOperatorExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1523);
				((UnaryOperatorExpressionContext)_localctx).prefix = _input.LT(1);
				_la = _input.LA(1);
				if ( !(((((_la - 96)) & ~0x3f) == 0 && ((1L << (_la - 96)) & 15363L) != 0)) ) {
					((UnaryOperatorExpressionContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1524);
				expression(18);
				}
				break;
			case 7:
				{
				_localctx = new CastExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1525);
				match(LPAREN);
				setState(1529);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,184,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1526);
						annotation();
						}
						} 
					}
					setState(1531);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,184,_ctx);
				}
				setState(1532);
				typeType();
				setState(1537);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==BITAND) {
					{
					{
					setState(1533);
					match(BITAND);
					setState(1534);
					typeType();
					}
					}
					setState(1539);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1540);
				match(RPAREN);
				setState(1541);
				expression(17);
				}
				break;
			case 8:
				{
				_localctx = new ObjectCreationExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1543);
				match(NEW);
				setState(1544);
				creator();
				}
				break;
			case 9:
				{
				_localctx = new ExpressionLambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1545);
				lambdaExpression();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1634);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,193,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1632);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,192,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1548);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(1549);
						((BinaryOperatorExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 110)) & ~0x3f) == 0 && ((1L << (_la - 110)) & 35L) != 0)) ) {
							((BinaryOperatorExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1550);
						expression(16);
						}
						break;
					case 2:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1551);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(1552);
						((BinaryOperatorExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((BinaryOperatorExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1553);
						expression(15);
						}
						break;
					case 3:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1554);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(1562);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,187,_ctx) ) {
						case 1:
							{
							setState(1555);
							match(LT);
							setState(1556);
							match(LT);
							}
							break;
						case 2:
							{
							setState(1557);
							match(GT);
							setState(1558);
							match(GT);
							setState(1559);
							match(GT);
							}
							break;
						case 3:
							{
							setState(1560);
							match(GT);
							setState(1561);
							match(GT);
							}
							break;
						}
						setState(1564);
						expression(14);
						}
						break;
					case 4:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1565);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(1566);
						((BinaryOperatorExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & 387L) != 0)) ) {
							((BinaryOperatorExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1567);
						expression(13);
						}
						break;
					case 5:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1568);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(1569);
						((BinaryOperatorExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
							((BinaryOperatorExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1570);
						expression(11);
						}
						break;
					case 6:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1571);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(1572);
						((BinaryOperatorExpressionContext)_localctx).bop = match(BITAND);
						setState(1573);
						expression(10);
						}
						break;
					case 7:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1574);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(1575);
						((BinaryOperatorExpressionContext)_localctx).bop = match(CARET);
						setState(1576);
						expression(9);
						}
						break;
					case 8:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1577);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(1578);
						((BinaryOperatorExpressionContext)_localctx).bop = match(BITOR);
						setState(1579);
						expression(8);
						}
						break;
					case 9:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1580);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(1581);
						((BinaryOperatorExpressionContext)_localctx).bop = match(AND);
						setState(1582);
						expression(7);
						}
						break;
					case 10:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1583);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(1584);
						((BinaryOperatorExpressionContext)_localctx).bop = match(OR);
						setState(1585);
						expression(6);
						}
						break;
					case 11:
						{
						_localctx = new ElvisExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1586);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(1587);
						((ElvisExpressionContext)_localctx).bop = match(ELVIS);
						setState(1588);
						expression(4);
						}
						break;
					case 12:
						{
						_localctx = new TernaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1589);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(1590);
						((TernaryExpressionContext)_localctx).bop = match(QUESTION);
						setState(1591);
						expression(0);
						setState(1592);
						match(COLON);
						setState(1593);
						expression(3);
						}
						break;
					case 13:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1595);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(1596);
						((BinaryOperatorExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 93)) & ~0x3f) == 0 && ((1L << (_la - 93)) & 17171480577L) != 0)) ) {
							((BinaryOperatorExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1597);
						expression(2);
						}
						break;
					case 14:
						{
						_localctx = new SquareBracketExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1598);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(1599);
						match(LBRACK);
						setState(1600);
						expression(0);
						setState(1601);
						match(RBRACK);
						}
						break;
					case 15:
						{
						_localctx = new MemberReferenceExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1603);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(1604);
						((MemberReferenceExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==NULLSAFE || _la==DOT) ) {
							((MemberReferenceExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1616);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,189,_ctx) ) {
						case 1:
							{
							setState(1605);
							identifier();
							}
							break;
						case 2:
							{
							setState(1606);
							methodCall();
							}
							break;
						case 3:
							{
							setState(1607);
							match(THIS);
							}
							break;
						case 4:
							{
							setState(1608);
							match(NEW);
							setState(1610);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==LT) {
								{
								setState(1609);
								nonWildcardTypeArguments();
								}
							}

							setState(1612);
							innerCreator();
							}
							break;
						case 5:
							{
							setState(1613);
							match(SUPER);
							setState(1614);
							superSuffix();
							}
							break;
						case 6:
							{
							setState(1615);
							explicitGenericInvocation();
							}
							break;
						}
						}
						break;
					case 16:
						{
						_localctx = new MethodReferenceExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1618);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(1619);
						match(COLONCOLON);
						setState(1621);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==LT) {
							{
							setState(1620);
							typeArguments();
							}
						}

						setState(1623);
						identifier();
						}
						break;
					case 17:
						{
						_localctx = new PostIncrementDecrementOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1624);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(1625);
						((PostIncrementDecrementOperatorExpressionContext)_localctx).postfix = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==INC || _la==DEC) ) {
							((PostIncrementDecrementOperatorExpressionContext)_localctx).postfix = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 18:
						{
						_localctx = new InstanceOfOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1626);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(1627);
						((InstanceOfOperatorExpressionContext)_localctx).bop = match(INSTANCEOF);
						setState(1630);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,191,_ctx) ) {
						case 1:
							{
							setState(1628);
							typeType();
							}
							break;
						case 2:
							{
							setState(1629);
							pattern();
							}
							break;
						}
						}
						break;
					}
					} 
				}
				setState(1636);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,193,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public ComponentPatternListContext componentPatternList() {
			return getRuleContext(ComponentPatternListContext.class,0);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_pattern);
		int _la;
		try {
			int _alt;
			setState(1659);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,197,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1640);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,194,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1637);
						variableModifier();
						}
						} 
					}
					setState(1642);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,194,_ctx);
				}
				setState(1643);
				typeType();
				setState(1647);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AT) {
					{
					{
					setState(1644);
					annotation();
					}
					}
					setState(1649);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1650);
				variableDeclarators();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1652);
				typeType();
				setState(1653);
				match(LPAREN);
				setState(1655);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7781259854553513392L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 105L) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(1654);
					componentPatternList();
					}
				}

				setState(1657);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComponentPatternListContext extends ParserRuleContext {
		public List<ComponentPatternContext> componentPattern() {
			return getRuleContexts(ComponentPatternContext.class);
		}
		public ComponentPatternContext componentPattern(int i) {
			return getRuleContext(ComponentPatternContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public ComponentPatternListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_componentPatternList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterComponentPatternList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitComponentPatternList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitComponentPatternList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComponentPatternListContext componentPatternList() throws RecognitionException {
		ComponentPatternListContext _localctx = new ComponentPatternListContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_componentPatternList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1661);
			componentPattern();
			setState(1666);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1662);
				match(COMMA);
				setState(1663);
				componentPattern();
				}
				}
				setState(1668);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComponentPatternContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public ComponentPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_componentPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterComponentPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitComponentPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitComponentPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComponentPatternContext componentPattern() throws RecognitionException {
		ComponentPatternContext _localctx = new ComponentPatternContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_componentPattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1669);
			pattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LambdaExpressionContext extends ParserRuleContext {
		public LambdaParametersContext lambdaParameters() {
			return getRuleContext(LambdaParametersContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(JADExParser.ARROW, 0); }
		public LambdaBodyContext lambdaBody() {
			return getRuleContext(LambdaBodyContext.class,0);
		}
		public LambdaExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterLambdaExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitLambdaExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitLambdaExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaExpressionContext lambdaExpression() throws RecognitionException {
		LambdaExpressionContext _localctx = new LambdaExpressionContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_lambdaExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1671);
			lambdaParameters();
			setState(1672);
			match(ARROW);
			setState(1673);
			lambdaBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LambdaParametersContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public LambdaLVTIListContext lambdaLVTIList() {
			return getRuleContext(LambdaLVTIListContext.class,0);
		}
		public LambdaParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterLambdaParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitLambdaParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitLambdaParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaParametersContext lambdaParameters() throws RecognitionException {
		LambdaParametersContext _localctx = new LambdaParametersContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_lambdaParameters);
		int _la;
		try {
			setState(1697);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,202,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1675);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1676);
				match(LPAREN);
				setState(1678);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7781259854553513392L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 105L) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(1677);
					formalParameterList();
					}
				}

				setState(1680);
				match(RPAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1681);
				match(LPAREN);
				setState(1682);
				identifier();
				setState(1687);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1683);
					match(COMMA);
					setState(1684);
					identifier();
					}
					}
					setState(1689);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1690);
				match(RPAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1692);
				match(LPAREN);
				setState(1694);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & 17592186048513L) != 0) || _la==AT) {
					{
					setState(1693);
					lambdaLVTIList();
					}
				}

				setState(1696);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LambdaBodyContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public LambdaBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterLambdaBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitLambdaBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitLambdaBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaBodyContext lambdaBody() throws RecognitionException {
		LambdaBodyContext _localctx = new LambdaBodyContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_lambdaBody);
		try {
			setState(1701);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case EXPORTS:
			case FLOAT:
			case INT:
			case LONG:
			case MODULE:
			case NEW:
			case OPEN:
			case OPENS:
			case PERMITS:
			case PROVIDES:
			case RECORD:
			case REQUIRES:
			case SEALED:
			case SHORT:
			case SUPER:
			case SWITCH:
			case THIS:
			case TO:
			case TRANSITIVE:
			case USES:
			case VAR:
			case VOID:
			case WHEN:
			case WITH:
			case YIELD:
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
			case BOOL_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case TEXT_BLOCK:
			case NULL_LITERAL:
			case LPAREN:
			case LT:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1699);
				expression(0);
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1700);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public TerminalNode THIS() { return getToken(JADExParser.THIS, 0); }
		public TerminalNode SUPER() { return getToken(JADExParser.SUPER, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeTypeOrVoidContext typeTypeOrVoid() {
			return getRuleContext(TypeTypeOrVoidContext.class,0);
		}
		public TerminalNode DOT() { return getToken(JADExParser.DOT, 0); }
		public TerminalNode CLASS() { return getToken(JADExParser.CLASS, 0); }
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() {
			return getRuleContext(ExplicitGenericInvocationSuffixContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_primary);
		try {
			setState(1721);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,205,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1703);
				match(LPAREN);
				setState(1704);
				expression(0);
				setState(1705);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1707);
				match(THIS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1708);
				match(SUPER);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1709);
				literal();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1710);
				identifier();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1711);
				typeTypeOrVoid();
				setState(1712);
				match(DOT);
				setState(1713);
				match(CLASS);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1715);
				nonWildcardTypeArguments();
				setState(1719);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case EXPORTS:
				case MODULE:
				case OPEN:
				case OPENS:
				case PERMITS:
				case PROVIDES:
				case RECORD:
				case REQUIRES:
				case SEALED:
				case SUPER:
				case TO:
				case TRANSITIVE:
				case USES:
				case VAR:
				case WHEN:
				case WITH:
				case YIELD:
				case IDENTIFIER:
					{
					setState(1716);
					explicitGenericInvocationSuffix();
					}
					break;
				case THIS:
					{
					setState(1717);
					match(THIS);
					setState(1718);
					arguments();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchExpressionContext extends ParserRuleContext {
		public TerminalNode SWITCH() { return getToken(JADExParser.SWITCH, 0); }
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(JADExParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(JADExParser.RBRACE, 0); }
		public List<SwitchLabeledRuleContext> switchLabeledRule() {
			return getRuleContexts(SwitchLabeledRuleContext.class);
		}
		public SwitchLabeledRuleContext switchLabeledRule(int i) {
			return getRuleContext(SwitchLabeledRuleContext.class,i);
		}
		public SwitchExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterSwitchExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitSwitchExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitSwitchExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchExpressionContext switchExpression() throws RecognitionException {
		SwitchExpressionContext _localctx = new SwitchExpressionContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_switchExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1723);
			match(SWITCH);
			setState(1724);
			match(LPAREN);
			setState(1725);
			expression(0);
			setState(1726);
			match(RPAREN);
			setState(1727);
			match(LBRACE);
			setState(1731);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE || _la==DEFAULT) {
				{
				{
				setState(1728);
				switchLabeledRule();
				}
				}
				setState(1733);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1734);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchLabeledRuleContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(JADExParser.CASE, 0); }
		public SwitchRuleOutcomeContext switchRuleOutcome() {
			return getRuleContext(SwitchRuleOutcomeContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(JADExParser.ARROW, 0); }
		public TerminalNode COLON() { return getToken(JADExParser.COLON, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode NULL_LITERAL() { return getToken(JADExParser.NULL_LITERAL, 0); }
		public List<CasePatternContext> casePattern() {
			return getRuleContexts(CasePatternContext.class);
		}
		public CasePatternContext casePattern(int i) {
			return getRuleContext(CasePatternContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public TerminalNode DEFAULT() { return getToken(JADExParser.DEFAULT, 0); }
		public GuardContext guard() {
			return getRuleContext(GuardContext.class,0);
		}
		public SwitchLabeledRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchLabeledRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterSwitchLabeledRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitSwitchLabeledRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitSwitchLabeledRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchLabeledRuleContext switchLabeledRule() throws RecognitionException {
		SwitchLabeledRuleContext _localctx = new SwitchLabeledRuleContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_switchLabeledRule);
		int _la;
		try {
			setState(1761);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1736);
				match(CASE);
				setState(1754);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,210,_ctx) ) {
				case 1:
					{
					setState(1737);
					expressionList();
					}
					break;
				case 2:
					{
					setState(1738);
					match(NULL_LITERAL);
					setState(1741);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(1739);
						match(COMMA);
						setState(1740);
						match(DEFAULT);
						}
					}

					}
					break;
				case 3:
					{
					setState(1743);
					casePattern();
					setState(1748);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(1744);
						match(COMMA);
						setState(1745);
						casePattern();
						}
						}
						setState(1750);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(1752);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WHEN) {
						{
						setState(1751);
						guard();
						}
					}

					}
					break;
				}
				setState(1756);
				_la = _input.LA(1);
				if ( !(_la==COLON || _la==ARROW) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1757);
				switchRuleOutcome();
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1758);
				match(DEFAULT);
				setState(1759);
				_la = _input.LA(1);
				if ( !(_la==COLON || _la==ARROW) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1760);
				switchRuleOutcome();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GuardContext extends ParserRuleContext {
		public TerminalNode WHEN() { return getToken(JADExParser.WHEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public GuardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_guard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterGuard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitGuard(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitGuard(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GuardContext guard() throws RecognitionException {
		GuardContext _localctx = new GuardContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_guard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1763);
			match(WHEN);
			setState(1764);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CasePatternContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public CasePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_casePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterCasePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitCasePattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitCasePattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CasePatternContext casePattern() throws RecognitionException {
		CasePatternContext _localctx = new CasePatternContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_casePattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1766);
			pattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchRuleOutcomeContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public SwitchRuleOutcomeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchRuleOutcome; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterSwitchRuleOutcome(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitSwitchRuleOutcome(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitSwitchRuleOutcome(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchRuleOutcomeContext switchRuleOutcome() throws RecognitionException {
		SwitchRuleOutcomeContext _localctx = new SwitchRuleOutcomeContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_switchRuleOutcome);
		int _la;
		try {
			setState(1775);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,213,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1768);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1772);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -5332262526231587206L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 65985802665979L) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					{
					setState(1769);
					blockStatement();
					}
					}
					setState(1774);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassOrInterfaceTypeContext extends ParserRuleContext {
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public ClassOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterClassOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitClassOrInterfaceType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitClassOrInterfaceType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassOrInterfaceTypeContext classOrInterfaceType() throws RecognitionException {
		ClassOrInterfaceTypeContext _localctx = new ClassOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_classOrInterfaceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1777);
			classType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreatorContext extends ParserRuleContext {
		public CreatedNameContext createdName() {
			return getRuleContext(CreatedNameContext.class,0);
		}
		public ClassCreatorRestContext classCreatorRest() {
			return getRuleContext(ClassCreatorRestContext.class,0);
		}
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ArrayCreatorRestContext arrayCreatorRest() {
			return getRuleContext(ArrayCreatorRestContext.class,0);
		}
		public CreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitCreator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitCreator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreatorContext creator() throws RecognitionException {
		CreatorContext _localctx = new CreatorContext(_ctx, getState());
		enterRule(_localctx, 240, RULE_creator);
		int _la;
		try {
			setState(1788);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,215,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1780);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1779);
					nonWildcardTypeArguments();
					}
				}

				setState(1782);
				createdName();
				setState(1783);
				classCreatorRest();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1785);
				createdName();
				setState(1786);
				arrayCreatorRest();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreatedNameContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TypeArgumentsOrDiamondContext> typeArgumentsOrDiamond() {
			return getRuleContexts(TypeArgumentsOrDiamondContext.class);
		}
		public TypeArgumentsOrDiamondContext typeArgumentsOrDiamond(int i) {
			return getRuleContext(TypeArgumentsOrDiamondContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(JADExParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(JADExParser.DOT, i);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public CreatedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createdName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterCreatedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitCreatedName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitCreatedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreatedNameContext createdName() throws RecognitionException {
		CreatedNameContext _localctx = new CreatedNameContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_createdName);
		int _la;
		try {
			setState(1805);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXPORTS:
			case MODULE:
			case OPEN:
			case OPENS:
			case PERMITS:
			case PROVIDES:
			case RECORD:
			case REQUIRES:
			case SEALED:
			case TO:
			case TRANSITIVE:
			case USES:
			case VAR:
			case WHEN:
			case WITH:
			case YIELD:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1790);
				identifier();
				setState(1792);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1791);
					typeArgumentsOrDiamond();
					}
				}

				setState(1801);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(1794);
					match(DOT);
					setState(1795);
					identifier();
					setState(1797);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(1796);
						typeArgumentsOrDiamond();
						}
					}

					}
					}
					setState(1803);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1804);
				primitiveType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InnerCreatorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ClassCreatorRestContext classCreatorRest() {
			return getRuleContext(ClassCreatorRestContext.class,0);
		}
		public NonWildcardTypeArgumentsOrDiamondContext nonWildcardTypeArgumentsOrDiamond() {
			return getRuleContext(NonWildcardTypeArgumentsOrDiamondContext.class,0);
		}
		public InnerCreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_innerCreator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterInnerCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitInnerCreator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitInnerCreator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InnerCreatorContext innerCreator() throws RecognitionException {
		InnerCreatorContext _localctx = new InnerCreatorContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_innerCreator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1807);
			identifier();
			setState(1809);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1808);
				nonWildcardTypeArgumentsOrDiamond();
				}
			}

			setState(1811);
			classCreatorRest();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayCreatorRestContext extends ParserRuleContext {
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(JADExParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(JADExParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(JADExParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(JADExParser.RBRACK, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayCreatorRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayCreatorRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterArrayCreatorRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitArrayCreatorRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitArrayCreatorRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayCreatorRestContext arrayCreatorRest() throws RecognitionException {
		ArrayCreatorRestContext _localctx = new ArrayCreatorRestContext(_ctx, getState());
		enterRule(_localctx, 246, RULE_arrayCreatorRest);
		int _la;
		try {
			int _alt;
			setState(1835);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,224,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1815); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1813);
					match(LBRACK);
					setState(1814);
					match(RBRACK);
					}
					}
					setState(1817); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LBRACK );
				setState(1819);
				arrayInitializer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1824); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1820);
						match(LBRACK);
						setState(1821);
						expression(0);
						setState(1822);
						match(RBRACK);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1826); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,222,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(1832);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,223,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1828);
						match(LBRACK);
						setState(1829);
						match(RBRACK);
						}
						} 
					}
					setState(1834);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,223,_ctx);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassCreatorRestContext extends ParserRuleContext {
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public ClassCreatorRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classCreatorRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterClassCreatorRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitClassCreatorRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitClassCreatorRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassCreatorRestContext classCreatorRest() throws RecognitionException {
		ClassCreatorRestContext _localctx = new ClassCreatorRestContext(_ctx, getState());
		enterRule(_localctx, 248, RULE_classCreatorRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1837);
			arguments();
			setState(1839);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,225,_ctx) ) {
			case 1:
				{
				setState(1838);
				classBody();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExplicitGenericInvocationContext extends ParserRuleContext {
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() {
			return getRuleContext(ExplicitGenericInvocationSuffixContext.class,0);
		}
		public ExplicitGenericInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitGenericInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterExplicitGenericInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitExplicitGenericInvocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitExplicitGenericInvocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExplicitGenericInvocationContext explicitGenericInvocation() throws RecognitionException {
		ExplicitGenericInvocationContext _localctx = new ExplicitGenericInvocationContext(_ctx, getState());
		enterRule(_localctx, 250, RULE_explicitGenericInvocation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1841);
			nonWildcardTypeArguments();
			setState(1842);
			explicitGenericInvocationSuffix();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeArgumentsOrDiamondContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(JADExParser.LT, 0); }
		public TerminalNode GT() { return getToken(JADExParser.GT, 0); }
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public TypeArgumentsOrDiamondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgumentsOrDiamond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterTypeArgumentsOrDiamond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitTypeArgumentsOrDiamond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitTypeArgumentsOrDiamond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeArgumentsOrDiamondContext typeArgumentsOrDiamond() throws RecognitionException {
		TypeArgumentsOrDiamondContext _localctx = new TypeArgumentsOrDiamondContext(_ctx, getState());
		enterRule(_localctx, 252, RULE_typeArgumentsOrDiamond);
		try {
			setState(1847);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,226,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1844);
				match(LT);
				setState(1845);
				match(GT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1846);
				typeArguments();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NonWildcardTypeArgumentsOrDiamondContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(JADExParser.LT, 0); }
		public TerminalNode GT() { return getToken(JADExParser.GT, 0); }
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public NonWildcardTypeArgumentsOrDiamondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonWildcardTypeArgumentsOrDiamond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterNonWildcardTypeArgumentsOrDiamond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitNonWildcardTypeArgumentsOrDiamond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitNonWildcardTypeArgumentsOrDiamond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonWildcardTypeArgumentsOrDiamondContext nonWildcardTypeArgumentsOrDiamond() throws RecognitionException {
		NonWildcardTypeArgumentsOrDiamondContext _localctx = new NonWildcardTypeArgumentsOrDiamondContext(_ctx, getState());
		enterRule(_localctx, 254, RULE_nonWildcardTypeArgumentsOrDiamond);
		try {
			setState(1852);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,227,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1849);
				match(LT);
				setState(1850);
				match(GT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1851);
				nonWildcardTypeArguments();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NonWildcardTypeArgumentsContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(JADExParser.LT, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public TerminalNode GT() { return getToken(JADExParser.GT, 0); }
		public NonWildcardTypeArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonWildcardTypeArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterNonWildcardTypeArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitNonWildcardTypeArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitNonWildcardTypeArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonWildcardTypeArgumentsContext nonWildcardTypeArguments() throws RecognitionException {
		NonWildcardTypeArgumentsContext _localctx = new NonWildcardTypeArgumentsContext(_ctx, getState());
		enterRule(_localctx, 256, RULE_nonWildcardTypeArguments);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1854);
			match(LT);
			setState(1855);
			typeList();
			setState(1856);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeListContext extends ParserRuleContext {
		public List<TypeTypeContext> typeType() {
			return getRuleContexts(TypeTypeContext.class);
		}
		public TypeTypeContext typeType(int i) {
			return getRuleContext(TypeTypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public TypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitTypeList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitTypeList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeListContext typeList() throws RecognitionException {
		TypeListContext _localctx = new TypeListContext(_ctx, getState());
		enterRule(_localctx, 258, RULE_typeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1858);
			typeType();
			setState(1863);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1859);
				match(COMMA);
				setState(1860);
				typeType();
				}
				}
				setState(1865);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeTypeContext extends ParserRuleContext {
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<TerminalNode> LBRACK() { return getTokens(JADExParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(JADExParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(JADExParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(JADExParser.RBRACK, i);
		}
		public TerminalNode QUESTION() { return getToken(JADExParser.QUESTION, 0); }
		public TypeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterTypeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitTypeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitTypeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeTypeContext typeType() throws RecognitionException {
		TypeTypeContext _localctx = new TypeTypeContext(_ctx, getState());
		enterRule(_localctx, 260, RULE_typeType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1869);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(1866);
				annotation();
				}
				}
				setState(1871);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1874);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXPORTS:
			case MODULE:
			case OPEN:
			case OPENS:
			case PERMITS:
			case PROVIDES:
			case RECORD:
			case REQUIRES:
			case SEALED:
			case TO:
			case TRANSITIVE:
			case USES:
			case VAR:
			case WHEN:
			case WITH:
			case YIELD:
			case IDENTIFIER:
				{
				setState(1872);
				classOrInterfaceType();
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				{
				setState(1873);
				primitiveType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1886);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,232,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1879);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==AT) {
						{
						{
						setState(1876);
						annotation();
						}
						}
						setState(1881);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(1882);
					match(LBRACK);
					setState(1883);
					match(RBRACK);
					}
					} 
				}
				setState(1888);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,232,_ctx);
			}
			setState(1890);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,233,_ctx) ) {
			case 1:
				{
				setState(1889);
				match(QUESTION);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimitiveTypeContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(JADExParser.BOOLEAN, 0); }
		public TerminalNode CHAR() { return getToken(JADExParser.CHAR, 0); }
		public TerminalNode BYTE() { return getToken(JADExParser.BYTE, 0); }
		public TerminalNode SHORT() { return getToken(JADExParser.SHORT, 0); }
		public TerminalNode INT() { return getToken(JADExParser.INT, 0); }
		public TerminalNode LONG() { return getToken(JADExParser.LONG, 0); }
		public TerminalNode FLOAT() { return getToken(JADExParser.FLOAT, 0); }
		public TerminalNode DOUBLE() { return getToken(JADExParser.DOUBLE, 0); }
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitPrimitiveType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitPrimitiveType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 262, RULE_primitiveType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1892);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 562952642003536L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeArgumentsContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(JADExParser.LT, 0); }
		public List<TypeArgumentContext> typeArgument() {
			return getRuleContexts(TypeArgumentContext.class);
		}
		public TypeArgumentContext typeArgument(int i) {
			return getRuleContext(TypeArgumentContext.class,i);
		}
		public TerminalNode GT() { return getToken(JADExParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(JADExParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JADExParser.COMMA, i);
		}
		public TypeArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterTypeArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitTypeArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitTypeArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeArgumentsContext typeArguments() throws RecognitionException {
		TypeArgumentsContext _localctx = new TypeArgumentsContext(_ctx, getState());
		enterRule(_localctx, 264, RULE_typeArguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1894);
			match(LT);
			setState(1895);
			typeArgument();
			setState(1900);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1896);
				match(COMMA);
				setState(1897);
				typeArgument();
				}
				}
				setState(1902);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1903);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SuperSuffixContext extends ParserRuleContext {
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode DOT() { return getToken(JADExParser.DOT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public SuperSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterSuperSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitSuperSuffix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitSuperSuffix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuperSuffixContext superSuffix() throws RecognitionException {
		SuperSuffixContext _localctx = new SuperSuffixContext(_ctx, getState());
		enterRule(_localctx, 266, RULE_superSuffix);
		int _la;
		try {
			setState(1914);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(1905);
				arguments();
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1906);
				match(DOT);
				setState(1908);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1907);
					typeArguments();
					}
				}

				setState(1910);
				identifier();
				setState(1912);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,236,_ctx) ) {
				case 1:
					{
					setState(1911);
					arguments();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExplicitGenericInvocationSuffixContext extends ParserRuleContext {
		public TerminalNode SUPER() { return getToken(JADExParser.SUPER, 0); }
		public SuperSuffixContext superSuffix() {
			return getRuleContext(SuperSuffixContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitGenericInvocationSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterExplicitGenericInvocationSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitExplicitGenericInvocationSuffix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitExplicitGenericInvocationSuffix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() throws RecognitionException {
		ExplicitGenericInvocationSuffixContext _localctx = new ExplicitGenericInvocationSuffixContext(_ctx, getState());
		enterRule(_localctx, 268, RULE_explicitGenericInvocationSuffix);
		try {
			setState(1921);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUPER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1916);
				match(SUPER);
				setState(1917);
				superSuffix();
				}
				break;
			case EXPORTS:
			case MODULE:
			case OPEN:
			case OPENS:
			case PERMITS:
			case PROVIDES:
			case RECORD:
			case REQUIRES:
			case SEALED:
			case TO:
			case TRANSITIVE:
			case USES:
			case VAR:
			case WHEN:
			case WITH:
			case YIELD:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1918);
				identifier();
				setState(1919);
				arguments();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentsContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(JADExParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(JADExParser.RPAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JADExParserListener ) ((JADExParserListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JADExParserVisitor ) return ((JADExParserVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 270, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1923);
			match(LPAREN);
			setState(1925);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -7731720228588715440L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 65985731362795L) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(1924);
				expressionList();
				}
			}

			setState(1927);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 66:
			return annotationFieldValue_sempred((AnnotationFieldValueContext)_localctx, predIndex);
		case 83:
			return recordComponentList_sempred((RecordComponentListContext)_localctx, predIndex);
		case 106:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean annotationFieldValue_sempred(AnnotationFieldValueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return  this.IsNotIdentifierAssign() ;
		}
		return true;
	}
	private boolean recordComponentList_sempred(RecordComponentListContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return  this.DoLastRecordComponent() ;
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 15);
		case 3:
			return precpred(_ctx, 14);
		case 4:
			return precpred(_ctx, 13);
		case 5:
			return precpred(_ctx, 12);
		case 6:
			return precpred(_ctx, 10);
		case 7:
			return precpred(_ctx, 9);
		case 8:
			return precpred(_ctx, 8);
		case 9:
			return precpred(_ctx, 7);
		case 10:
			return precpred(_ctx, 6);
		case 11:
			return precpred(_ctx, 5);
		case 12:
			return precpred(_ctx, 4);
		case 13:
			return precpred(_ctx, 3);
		case 14:
			return precpred(_ctx, 2);
		case 15:
			return precpred(_ctx, 26);
		case 16:
			return precpred(_ctx, 25);
		case 17:
			return precpred(_ctx, 23);
		case 18:
			return precpred(_ctx, 19);
		case 19:
			return precpred(_ctx, 11);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0086\u078a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007"+
		"\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007"+
		"\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007"+
		"\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007"+
		"\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007"+
		",\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u0007"+
		"1\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u0007"+
		"6\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007"+
		";\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007"+
		"@\u0002A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007"+
		"E\u0002F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007"+
		"J\u0002K\u0007K\u0002L\u0007L\u0002M\u0007M\u0002N\u0007N\u0002O\u0007"+
		"O\u0002P\u0007P\u0002Q\u0007Q\u0002R\u0007R\u0002S\u0007S\u0002T\u0007"+
		"T\u0002U\u0007U\u0002V\u0007V\u0002W\u0007W\u0002X\u0007X\u0002Y\u0007"+
		"Y\u0002Z\u0007Z\u0002[\u0007[\u0002\\\u0007\\\u0002]\u0007]\u0002^\u0007"+
		"^\u0002_\u0007_\u0002`\u0007`\u0002a\u0007a\u0002b\u0007b\u0002c\u0007"+
		"c\u0002d\u0007d\u0002e\u0007e\u0002f\u0007f\u0002g\u0007g\u0002h\u0007"+
		"h\u0002i\u0007i\u0002j\u0007j\u0002k\u0007k\u0002l\u0007l\u0002m\u0007"+
		"m\u0002n\u0007n\u0002o\u0007o\u0002p\u0007p\u0002q\u0007q\u0002r\u0007"+
		"r\u0002s\u0007s\u0002t\u0007t\u0002u\u0007u\u0002v\u0007v\u0002w\u0007"+
		"w\u0002x\u0007x\u0002y\u0007y\u0002z\u0007z\u0002{\u0007{\u0002|\u0007"+
		"|\u0002}\u0007}\u0002~\u0007~\u0002\u007f\u0007\u007f\u0002\u0080\u0007"+
		"\u0080\u0002\u0081\u0007\u0081\u0002\u0082\u0007\u0082\u0002\u0083\u0007"+
		"\u0083\u0002\u0084\u0007\u0084\u0002\u0085\u0007\u0085\u0002\u0086\u0007"+
		"\u0086\u0002\u0087\u0007\u0087\u0001\u0000\u0003\u0000\u0112\b\u0000\u0001"+
		"\u0000\u0001\u0000\u0005\u0000\u0116\b\u0000\n\u0000\f\u0000\u0119\t\u0000"+
		"\u0001\u0000\u0005\u0000\u011c\b\u0000\n\u0000\f\u0000\u011f\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0005\u0000\u0123\b\u0000\n\u0000\f\u0000\u0126\t\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u012c\b\u0000"+
		"\u0001\u0001\u0005\u0001\u012f\b\u0001\n\u0001\f\u0001\u0132\t\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0005\u0002\u0137\b\u0002\n\u0002\f\u0002"+
		"\u013a\t\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0003\u0003\u0142\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003\u0147\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0150\b\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u0156\b\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0005\u0006\u015f\b\u0006\n\u0006\f\u0006\u0162\t\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u0169\b\u0007\n"+
		"\u0007\f\u0007\u016c\t\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u0173\b\b\u0001\t\u0001\t\u0001\t\u0005\t\u0178\b\t\n\t\f\t\u017b\t"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0005\u000b\u0183"+
		"\b\u000b\n\u000b\f\u000b\u0186\t\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0003\u000b\u018d\b\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0003\f\u0194\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u01a1\b\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u01a6\b\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0003\u000f\u01ab\b\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u01af\b\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u01b3\b\u000f"+
		"\u0001\u000f\u0001\u000f\u0003\u000f\u01b7\b\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u01bf\b\u0010"+
		"\n\u0010\f\u0010\u01c2\t\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0005"+
		"\u0011\u01c7\b\u0011\n\u0011\f\u0011\u01ca\t\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0005\u0011\u01cf\b\u0011\n\u0011\f\u0011\u01d2\t\u0011\u0001"+
		"\u0011\u0003\u0011\u01d5\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0005"+
		"\u0012\u01da\b\u0012\n\u0012\f\u0012\u01dd\t\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0003\u0013\u01e3\b\u0013\u0001\u0013\u0001\u0013"+
		"\u0003\u0013\u01e7\b\u0013\u0001\u0013\u0003\u0013\u01ea\b\u0013\u0001"+
		"\u0013\u0003\u0013\u01ed\b\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0005\u0014\u01f4\b\u0014\n\u0014\f\u0014\u01f7\t\u0014"+
		"\u0001\u0015\u0005\u0015\u01fa\b\u0015\n\u0015\f\u0015\u01fd\t\u0015\u0001"+
		"\u0015\u0001\u0015\u0003\u0015\u0201\b\u0015\u0001\u0015\u0003\u0015\u0204"+
		"\b\u0015\u0001\u0016\u0001\u0016\u0005\u0016\u0208\b\u0016\n\u0016\f\u0016"+
		"\u020b\t\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0210\b"+
		"\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0214\b\u0017\u0001\u0017\u0001"+
		"\u0017\u0003\u0017\u0218\b\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001"+
		"\u0018\u0005\u0018\u021e\b\u0018\n\u0018\f\u0018\u0221\t\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0019\u0001\u0019\u0005\u0019\u0227\b\u0019\n\u0019"+
		"\f\u0019\u022a\t\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0003\u001a\u0230\b\u001a\u0001\u001a\u0001\u001a\u0005\u001a\u0234\b"+
		"\u001a\n\u001a\f\u001a\u0237\t\u001a\u0001\u001a\u0003\u001a\u023a\b\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u0246\b\u001b"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0005\u001c"+
		"\u024d\b\u001c\n\u001c\f\u001c\u0250\t\u001c\u0001\u001c\u0001\u001c\u0003"+
		"\u001c\u0254\b\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0003"+
		"\u001d\u025a\b\u001d\u0001\u001e\u0001\u001e\u0003\u001e\u025e\b\u001e"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001!\u0001"+
		"!\u0001!\u0001!\u0003!\u026a\b!\u0001!\u0001!\u0001\"\u0005\"\u026f\b"+
		"\"\n\"\f\"\u0272\t\"\u0001\"\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001"+
		"#\u0001$\u0005$\u027c\b$\n$\f$\u027f\t$\u0001$\u0001$\u0003$\u0283\b$"+
		"\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0003%\u028d"+
		"\b%\u0001&\u0001&\u0001&\u0001&\u0005&\u0293\b&\n&\f&\u0296\t&\u0001&"+
		"\u0001&\u0001\'\u0001\'\u0001\'\u0005\'\u029d\b\'\n\'\f\'\u02a0\t\'\u0001"+
		"\'\u0001\'\u0001\'\u0001(\u0005(\u02a6\b(\n(\f(\u02a9\t(\u0001(\u0001"+
		"(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0003)\u02b3\b)\u0001*\u0005"+
		"*\u02b6\b*\n*\f*\u02b9\t*\u0001*\u0001*\u0001*\u0001+\u0005+\u02bf\b+"+
		"\n+\f+\u02c2\t+\u0001+\u0001+\u0001+\u0001+\u0001+\u0005+\u02c9\b+\n+"+
		"\f+\u02cc\t+\u0001+\u0001+\u0003+\u02d0\b+\u0001+\u0001+\u0001,\u0001"+
		",\u0001,\u0005,\u02d7\b,\n,\f,\u02da\t,\u0001-\u0001-\u0001-\u0003-\u02df"+
		"\b-\u0001.\u0001.\u0001.\u0005.\u02e4\b.\n.\f.\u02e7\t.\u0001/\u0001/"+
		"\u0003/\u02eb\b/\u00010\u00010\u00010\u00010\u00050\u02f1\b0\n0\f0\u02f4"+
		"\t0\u00010\u00030\u02f7\b0\u00030\u02f9\b0\u00010\u00010\u00011\u0001"+
		"1\u00011\u00051\u0300\b1\n1\f1\u0303\t1\u00031\u0305\b1\u00011\u00011"+
		"\u00031\u0309\b1\u00041\u030b\b1\u000b1\f1\u030c\u00011\u00011\u00051"+
		"\u0311\b1\n1\f1\u0314\t1\u00011\u00011\u00031\u0318\b1\u00051\u031a\b"+
		"1\n1\f1\u031d\t1\u00012\u00012\u00012\u00052\u0322\b2\n2\f2\u0325\t2\u0001"+
		"3\u00013\u00053\u0329\b3\n3\f3\u032c\t3\u00013\u00013\u00013\u00033\u0331"+
		"\b3\u00033\u0333\b3\u00014\u00014\u00014\u00054\u0338\b4\n4\f4\u033b\t"+
		"4\u00015\u00015\u00015\u00035\u0340\b5\u00015\u00015\u00055\u0344\b5\n"+
		"5\f5\u0347\t5\u00035\u0349\b5\u00015\u00015\u00016\u00016\u00016\u0001"+
		"6\u00056\u0351\b6\n6\f6\u0354\t6\u00016\u00016\u00017\u00017\u00017\u0005"+
		"7\u035b\b7\n7\f7\u035e\t7\u00018\u00058\u0361\b8\n8\f8\u0364\t8\u0001"+
		"8\u00018\u00058\u0368\b8\n8\f8\u036b\t8\u00018\u00038\u036e\b8\u00018"+
		"\u00018\u00019\u00019\u00019\u00059\u0375\b9\n9\f9\u0378\t9\u0001:\u0005"+
		":\u037b\b:\n:\f:\u037e\t:\u0001:\u0001:\u0001:\u0001;\u0001;\u0001;\u0005"+
		";\u0386\b;\n;\f;\u0389\t;\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001"+
		"<\u0003<\u0392\b<\u0001=\u0001=\u0001>\u0001>\u0001?\u0001?\u0001?\u0005"+
		"?\u039b\b?\n?\f?\u039e\t?\u0001?\u0001?\u0001?\u0001@\u0001@\u0001@\u0001"+
		"@\u0003@\u03a7\b@\u0001A\u0001A\u0001A\u0001A\u0005A\u03ad\bA\nA\fA\u03b0"+
		"\tA\u0003A\u03b2\bA\u0001A\u0001A\u0001B\u0001B\u0001B\u0001B\u0001B\u0001"+
		"B\u0003B\u03bc\bB\u0001C\u0001C\u0001C\u0001C\u0001C\u0001C\u0005C\u03c4"+
		"\bC\nC\fC\u03c7\tC\u0003C\u03c9\bC\u0001C\u0003C\u03cc\bC\u0001C\u0003"+
		"C\u03cf\bC\u0001D\u0001D\u0001D\u0003D\u03d4\bD\u0001E\u0001E\u0001E\u0001"+
		"E\u0005E\u03da\bE\nE\fE\u03dd\tE\u0003E\u03df\bE\u0001E\u0003E\u03e2\b"+
		"E\u0001E\u0001E\u0001F\u0001F\u0001F\u0001F\u0001F\u0001G\u0001G\u0005"+
		"G\u03ed\bG\nG\fG\u03f0\tG\u0001G\u0001G\u0001H\u0005H\u03f5\bH\nH\fH\u03f8"+
		"\tH\u0001H\u0001H\u0003H\u03fc\bH\u0001I\u0001I\u0001I\u0001I\u0001I\u0001"+
		"I\u0003I\u0404\bI\u0001I\u0001I\u0003I\u0408\bI\u0001I\u0001I\u0003I\u040c"+
		"\bI\u0001I\u0001I\u0003I\u0410\bI\u0001I\u0001I\u0003I\u0414\bI\u0003"+
		"I\u0416\bI\u0001J\u0001J\u0003J\u041a\bJ\u0001K\u0001K\u0001K\u0001K\u0003"+
		"K\u0420\bK\u0001L\u0001L\u0001M\u0001M\u0001M\u0001N\u0005N\u0428\bN\n"+
		"N\fN\u042b\tN\u0001N\u0003N\u042e\bN\u0001N\u0001N\u0001N\u0001N\u0005"+
		"N\u0434\bN\nN\fN\u0437\tN\u0001N\u0001N\u0001O\u0001O\u0005O\u043d\bO"+
		"\nO\fO\u0440\tO\u0001O\u0001O\u0001O\u0001O\u0001O\u0001O\u0001O\u0001"+
		"O\u0001O\u0005O\u044b\bO\nO\fO\u044e\tO\u0003O\u0450\bO\u0001O\u0001O"+
		"\u0001O\u0001O\u0001O\u0001O\u0001O\u0001O\u0005O\u045a\bO\nO\fO\u045d"+
		"\tO\u0003O\u045f\bO\u0001O\u0001O\u0001O\u0001O\u0001O\u0001O\u0001O\u0001"+
		"O\u0001O\u0001O\u0001O\u0001O\u0005O\u046d\bO\nO\fO\u0470\tO\u0001O\u0001"+
		"O\u0003O\u0474\bO\u0001P\u0001P\u0001Q\u0001Q\u0001Q\u0003Q\u047b\bQ\u0001"+
		"Q\u0001Q\u0001Q\u0003Q\u0480\bQ\u0001Q\u0001Q\u0001R\u0001R\u0003R\u0486"+
		"\bR\u0001R\u0001R\u0001S\u0001S\u0001S\u0005S\u048d\bS\nS\fS\u0490\tS"+
		"\u0001S\u0001S\u0001T\u0005T\u0495\bT\nT\fT\u0498\tT\u0001T\u0001T\u0005"+
		"T\u049c\bT\nT\fT\u049f\tT\u0001T\u0003T\u04a2\bT\u0001T\u0001T\u0001U"+
		"\u0001U\u0001U\u0005U\u04a9\bU\nU\fU\u04ac\tU\u0001U\u0001U\u0001V\u0001"+
		"V\u0005V\u04b2\bV\nV\fV\u04b5\tV\u0001V\u0001V\u0001W\u0001W\u0001W\u0001"+
		"W\u0001W\u0003W\u04be\bW\u0001X\u0005X\u04c1\bX\nX\fX\u04c4\tX\u0001X"+
		"\u0001X\u0001X\u0001X\u0001X\u0001X\u0001X\u0001X\u0003X\u04ce\bX\u0001"+
		"Y\u0001Y\u0001Z\u0001Z\u0001[\u0005[\u04d5\b[\n[\f[\u04d8\t[\u0001[\u0001"+
		"[\u0001[\u0001[\u0003[\u04de\b[\u0001\\\u0001\\\u0001\\\u0001\\\u0001"+
		"\\\u0003\\\u04e5\b\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001"+
		"\\\u0001\\\u0001\\\u0003\\\u04f0\b\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001"+
		"\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001"+
		"\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001"+
		"\\\u0004\\\u0509\b\\\u000b\\\f\\\u050a\u0001\\\u0003\\\u050e\b\\\u0001"+
		"\\\u0003\\\u0511\b\\\u0001\\\u0001\\\u0001\\\u0001\\\u0005\\\u0517\b\\"+
		"\n\\\f\\\u051a\t\\\u0001\\\u0003\\\u051d\b\\\u0001\\\u0001\\\u0001\\\u0001"+
		"\\\u0001\\\u0001\\\u0005\\\u0525\b\\\n\\\f\\\u0528\t\\\u0001\\\u0005\\"+
		"\u052b\b\\\n\\\f\\\u052e\t\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001"+
		"\\\u0001\\\u0001\\\u0001\\\u0001\\\u0003\\\u053a\b\\\u0001\\\u0001\\\u0001"+
		"\\\u0001\\\u0001\\\u0001\\\u0001\\\u0003\\\u0543\b\\\u0001\\\u0001\\\u0001"+
		"\\\u0003\\\u0548\b\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001\\\u0001"+
		"\\\u0001\\\u0001\\\u0001\\\u0001\\\u0003\\\u0555\b\\\u0001\\\u0001\\\u0001"+
		"\\\u0001\\\u0003\\\u055b\b\\\u0001]\u0001]\u0001]\u0005]\u0560\b]\n]\f"+
		"]\u0563\t]\u0001]\u0001]\u0001]\u0001]\u0001]\u0001^\u0001^\u0001^\u0005"+
		"^\u056d\b^\n^\f^\u0570\t^\u0001_\u0001_\u0001_\u0001`\u0001`\u0001`\u0003"+
		"`\u0578\b`\u0001`\u0001`\u0001a\u0001a\u0001a\u0005a\u057f\ba\na\fa\u0582"+
		"\ta\u0001b\u0005b\u0585\bb\nb\fb\u0588\tb\u0001b\u0001b\u0003b\u058c\b"+
		"b\u0001b\u0001b\u0001b\u0001b\u0003b\u0592\bb\u0001b\u0001b\u0001b\u0001"+
		"b\u0003b\u0598\bb\u0001c\u0001c\u0001c\u0004c\u059d\bc\u000bc\fc\u059e"+
		"\u0001c\u0004c\u05a2\bc\u000bc\fc\u05a3\u0001d\u0001d\u0001d\u0001d\u0001"+
		"d\u0001d\u0003d\u05ac\bd\u0001d\u0003d\u05af\bd\u0001e\u0001e\u0003e\u05b3"+
		"\be\u0001e\u0001e\u0003e\u05b7\be\u0001e\u0001e\u0003e\u05bb\be\u0003"+
		"e\u05bd\be\u0001f\u0001f\u0003f\u05c1\bf\u0001g\u0005g\u05c4\bg\ng\fg"+
		"\u05c7\tg\u0001g\u0001g\u0003g\u05cb\bg\u0001g\u0001g\u0001g\u0001g\u0001"+
		"h\u0001h\u0001h\u0005h\u05d4\bh\nh\fh\u05d7\th\u0001i\u0001i\u0001i\u0003"+
		"i\u05dc\bi\u0001i\u0001i\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0003"+
		"j\u05e6\bj\u0001j\u0001j\u0003j\u05ea\bj\u0001j\u0001j\u0001j\u0003j\u05ef"+
		"\bj\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0005j\u05f8\bj\n"+
		"j\fj\u05fb\tj\u0001j\u0001j\u0001j\u0005j\u0600\bj\nj\fj\u0603\tj\u0001"+
		"j\u0001j\u0001j\u0001j\u0001j\u0001j\u0003j\u060b\bj\u0001j\u0001j\u0001"+
		"j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001"+
		"j\u0001j\u0003j\u061b\bj\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001"+
		"j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001"+
		"j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001"+
		"j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001"+
		"j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0003"+
		"j\u064b\bj\u0001j\u0001j\u0001j\u0001j\u0003j\u0651\bj\u0001j\u0001j\u0001"+
		"j\u0003j\u0656\bj\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0001j\u0003"+
		"j\u065f\bj\u0005j\u0661\bj\nj\fj\u0664\tj\u0001k\u0005k\u0667\bk\nk\f"+
		"k\u066a\tk\u0001k\u0001k\u0005k\u066e\bk\nk\fk\u0671\tk\u0001k\u0001k"+
		"\u0001k\u0001k\u0001k\u0003k\u0678\bk\u0001k\u0001k\u0003k\u067c\bk\u0001"+
		"l\u0001l\u0001l\u0005l\u0681\bl\nl\fl\u0684\tl\u0001m\u0001m\u0001n\u0001"+
		"n\u0001n\u0001n\u0001o\u0001o\u0001o\u0003o\u068f\bo\u0001o\u0001o\u0001"+
		"o\u0001o\u0001o\u0005o\u0696\bo\no\fo\u0699\to\u0001o\u0001o\u0001o\u0001"+
		"o\u0003o\u069f\bo\u0001o\u0003o\u06a2\bo\u0001p\u0001p\u0003p\u06a6\b"+
		"p\u0001q\u0001q\u0001q\u0001q\u0001q\u0001q\u0001q\u0001q\u0001q\u0001"+
		"q\u0001q\u0001q\u0001q\u0001q\u0001q\u0001q\u0003q\u06b8\bq\u0003q\u06ba"+
		"\bq\u0001r\u0001r\u0001r\u0001r\u0001r\u0001r\u0005r\u06c2\br\nr\fr\u06c5"+
		"\tr\u0001r\u0001r\u0001s\u0001s\u0001s\u0001s\u0001s\u0003s\u06ce\bs\u0001"+
		"s\u0001s\u0001s\u0005s\u06d3\bs\ns\fs\u06d6\ts\u0001s\u0003s\u06d9\bs"+
		"\u0003s\u06db\bs\u0001s\u0001s\u0001s\u0001s\u0001s\u0003s\u06e2\bs\u0001"+
		"t\u0001t\u0001t\u0001u\u0001u\u0001v\u0001v\u0005v\u06eb\bv\nv\fv\u06ee"+
		"\tv\u0003v\u06f0\bv\u0001w\u0001w\u0001x\u0003x\u06f5\bx\u0001x\u0001"+
		"x\u0001x\u0001x\u0001x\u0001x\u0003x\u06fd\bx\u0001y\u0001y\u0003y\u0701"+
		"\by\u0001y\u0001y\u0001y\u0003y\u0706\by\u0005y\u0708\by\ny\fy\u070b\t"+
		"y\u0001y\u0003y\u070e\by\u0001z\u0001z\u0003z\u0712\bz\u0001z\u0001z\u0001"+
		"{\u0001{\u0004{\u0718\b{\u000b{\f{\u0719\u0001{\u0001{\u0001{\u0001{\u0001"+
		"{\u0004{\u0721\b{\u000b{\f{\u0722\u0001{\u0001{\u0005{\u0727\b{\n{\f{"+
		"\u072a\t{\u0003{\u072c\b{\u0001|\u0001|\u0003|\u0730\b|\u0001}\u0001}"+
		"\u0001}\u0001~\u0001~\u0001~\u0003~\u0738\b~\u0001\u007f\u0001\u007f\u0001"+
		"\u007f\u0003\u007f\u073d\b\u007f\u0001\u0080\u0001\u0080\u0001\u0080\u0001"+
		"\u0080\u0001\u0081\u0001\u0081\u0001\u0081\u0005\u0081\u0746\b\u0081\n"+
		"\u0081\f\u0081\u0749\t\u0081\u0001\u0082\u0005\u0082\u074c\b\u0082\n\u0082"+
		"\f\u0082\u074f\t\u0082\u0001\u0082\u0001\u0082\u0003\u0082\u0753\b\u0082"+
		"\u0001\u0082\u0005\u0082\u0756\b\u0082\n\u0082\f\u0082\u0759\t\u0082\u0001"+
		"\u0082\u0001\u0082\u0005\u0082\u075d\b\u0082\n\u0082\f\u0082\u0760\t\u0082"+
		"\u0001\u0082\u0003\u0082\u0763\b\u0082\u0001\u0083\u0001\u0083\u0001\u0084"+
		"\u0001\u0084\u0001\u0084\u0001\u0084\u0005\u0084\u076b\b\u0084\n\u0084"+
		"\f\u0084\u076e\t\u0084\u0001\u0084\u0001\u0084\u0001\u0085\u0001\u0085"+
		"\u0001\u0085\u0003\u0085\u0775\b\u0085\u0001\u0085\u0001\u0085\u0003\u0085"+
		"\u0779\b\u0085\u0003\u0085\u077b\b\u0085\u0001\u0086\u0001\u0086\u0001"+
		"\u0086\u0001\u0086\u0001\u0086\u0003\u0086\u0782\b\u0086\u0001\u0087\u0001"+
		"\u0087\u0003\u0087\u0786\b\u0087\u0001\u0087\u0001\u0087\u0001\u0087\u0000"+
		"\u0001\u00d4\u0088\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfh"+
		"jlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092"+
		"\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa"+
		"\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be\u00c0\u00c2"+
		"\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6\u00d8\u00da"+
		"\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8\u00ea\u00ec\u00ee\u00f0\u00f2"+
		"\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe\u0100\u0102\u0104\u0106\u0108\u010a"+
		"\u010c\u010e\u0000\u0010\u0002\u0000\u0013\u001344\u0001\u0000GJ\u0001"+
		"\u0000KL\u0002\u000022<<\r\u0000\u0012\u0012!!%&((++-.00::<<?@CCEF\u0086"+
		"\u0086\u000b\u0000\u0012\u0012!!%&++..00::<<??EE\u0086\u0086\u0002\u0000"+
		"`ajm\u0002\u0000noss\u0001\u0000lm\u0002\u0000^_ef\u0002\u0000ddgg\u0002"+
		"\u0000]]t~\u0002\u0000SS\\\\\u0001\u0000jk\u0002\u0000cc\u007f\u007f\b"+
		"\u0000\u0004\u0004\u0006\u0006\t\t\u000f\u000f\u0016\u0016\u001d\u001d"+
		"\u001f\u001f11\u085c\u0000\u012b\u0001\u0000\u0000\u0000\u0002\u0130\u0001"+
		"\u0000\u0000\u0000\u0004\u0138\u0001\u0000\u0000\u0000\u0006\u014f\u0001"+
		"\u0000\u0000\u0000\b\u0155\u0001\u0000\u0000\u0000\n\u0157\u0001\u0000"+
		"\u0000\u0000\f\u015a\u0001\u0000\u0000\u0000\u000e\u0165\u0001\u0000\u0000"+
		"\u0000\u0010\u016d\u0001\u0000\u0000\u0000\u0012\u0174\u0001\u0000\u0000"+
		"\u0000\u0014\u017c\u0001\u0000\u0000\u0000\u0016\u0184\u0001\u0000\u0000"+
		"\u0000\u0018\u0193\u0001\u0000\u0000\u0000\u001a\u01a0\u0001\u0000\u0000"+
		"\u0000\u001c\u01a5\u0001\u0000\u0000\u0000\u001e\u01a7\u0001\u0000\u0000"+
		"\u0000 \u01ba\u0001\u0000\u0000\u0000\"\u01c8\u0001\u0000\u0000\u0000"+
		"$\u01d6\u0001\u0000\u0000\u0000&\u01de\u0001\u0000\u0000\u0000(\u01f0"+
		"\u0001\u0000\u0000\u0000*\u01fb\u0001\u0000\u0000\u0000,\u0205\u0001\u0000"+
		"\u0000\u0000.\u020c\u0001\u0000\u0000\u00000\u021b\u0001\u0000\u0000\u0000"+
		"2\u0224\u0001\u0000\u0000\u00004\u0239\u0001\u0000\u0000\u00006\u0245"+
		"\u0001\u0000\u0000\u00008\u0247\u0001\u0000\u0000\u0000:\u0259\u0001\u0000"+
		"\u0000\u0000<\u025d\u0001\u0000\u0000\u0000>\u025f\u0001\u0000\u0000\u0000"+
		"@\u0262\u0001\u0000\u0000\u0000B\u0265\u0001\u0000\u0000\u0000D\u0270"+
		"\u0001\u0000\u0000\u0000F\u0276\u0001\u0000\u0000\u0000H\u0282\u0001\u0000"+
		"\u0000\u0000J\u028c\u0001\u0000\u0000\u0000L\u028e\u0001\u0000\u0000\u0000"+
		"N\u0299\u0001\u0000\u0000\u0000P\u02a7\u0001\u0000\u0000\u0000R\u02b2"+
		"\u0001\u0000\u0000\u0000T\u02b7\u0001\u0000\u0000\u0000V\u02c0\u0001\u0000"+
		"\u0000\u0000X\u02d3\u0001\u0000\u0000\u0000Z\u02db\u0001\u0000\u0000\u0000"+
		"\\\u02e0\u0001\u0000\u0000\u0000^\u02ea\u0001\u0000\u0000\u0000`\u02ec"+
		"\u0001\u0000\u0000\u0000b\u030a\u0001\u0000\u0000\u0000d\u031e\u0001\u0000"+
		"\u0000\u0000f\u0332\u0001\u0000\u0000\u0000h\u0334\u0001\u0000\u0000\u0000"+
		"j\u033c\u0001\u0000\u0000\u0000l\u034c\u0001\u0000\u0000\u0000n\u0357"+
		"\u0001\u0000\u0000\u0000p\u0362\u0001\u0000\u0000\u0000r\u0371\u0001\u0000"+
		"\u0000\u0000t\u037c\u0001\u0000\u0000\u0000v\u0382\u0001\u0000\u0000\u0000"+
		"x\u0391\u0001\u0000\u0000\u0000z\u0393\u0001\u0000\u0000\u0000|\u0395"+
		"\u0001\u0000\u0000\u0000~\u039c\u0001\u0000\u0000\u0000\u0080\u03a2\u0001"+
		"\u0000\u0000\u0000\u0082\u03a8\u0001\u0000\u0000\u0000\u0084\u03bb\u0001"+
		"\u0000\u0000\u0000\u0086\u03ce\u0001\u0000\u0000\u0000\u0088\u03d3\u0001"+
		"\u0000\u0000\u0000\u008a\u03d5\u0001\u0000\u0000\u0000\u008c\u03e5\u0001"+
		"\u0000\u0000\u0000\u008e\u03ea\u0001\u0000\u0000\u0000\u0090\u03fb\u0001"+
		"\u0000\u0000\u0000\u0092\u0415\u0001\u0000\u0000\u0000\u0094\u0419\u0001"+
		"\u0000\u0000\u0000\u0096\u041b\u0001\u0000\u0000\u0000\u0098\u0421\u0001"+
		"\u0000\u0000\u0000\u009a\u0423\u0001\u0000\u0000\u0000\u009c\u0429\u0001"+
		"\u0000\u0000\u0000\u009e\u0473\u0001\u0000\u0000\u0000\u00a0\u0475\u0001"+
		"\u0000\u0000\u0000\u00a2\u0477\u0001\u0000\u0000\u0000\u00a4\u0483\u0001"+
		"\u0000\u0000\u0000\u00a6\u0489\u0001\u0000\u0000\u0000\u00a8\u0496\u0001"+
		"\u0000\u0000\u0000\u00aa\u04a5\u0001\u0000\u0000\u0000\u00ac\u04af\u0001"+
		"\u0000\u0000\u0000\u00ae\u04bd\u0001\u0000\u0000\u0000\u00b0\u04c2\u0001"+
		"\u0000\u0000\u0000\u00b2\u04cf\u0001\u0000\u0000\u0000\u00b4\u04d1\u0001"+
		"\u0000\u0000\u0000\u00b6\u04d6\u0001\u0000\u0000\u0000\u00b8\u055a\u0001"+
		"\u0000\u0000\u0000\u00ba\u055c\u0001\u0000\u0000\u0000\u00bc\u0569\u0001"+
		"\u0000\u0000\u0000\u00be\u0571\u0001\u0000\u0000\u0000\u00c0\u0574\u0001"+
		"\u0000\u0000\u0000\u00c2\u057b\u0001\u0000\u0000\u0000\u00c4\u0597\u0001"+
		"\u0000\u0000\u0000\u00c6\u059c\u0001\u0000\u0000\u0000\u00c8\u05ae\u0001"+
		"\u0000\u0000\u0000\u00ca\u05bc\u0001\u0000\u0000\u0000\u00cc\u05c0\u0001"+
		"\u0000\u0000\u0000\u00ce\u05c5\u0001\u0000\u0000\u0000\u00d0\u05d0\u0001"+
		"\u0000\u0000\u0000\u00d2\u05db\u0001\u0000\u0000\u0000\u00d4\u060a\u0001"+
		"\u0000\u0000\u0000\u00d6\u067b\u0001\u0000\u0000\u0000\u00d8\u067d\u0001"+
		"\u0000\u0000\u0000\u00da\u0685\u0001\u0000\u0000\u0000\u00dc\u0687\u0001"+
		"\u0000\u0000\u0000\u00de\u06a1\u0001\u0000\u0000\u0000\u00e0\u06a5\u0001"+
		"\u0000\u0000\u0000\u00e2\u06b9\u0001\u0000\u0000\u0000\u00e4\u06bb\u0001"+
		"\u0000\u0000\u0000\u00e6\u06e1\u0001\u0000\u0000\u0000\u00e8\u06e3\u0001"+
		"\u0000\u0000\u0000\u00ea\u06e6\u0001\u0000\u0000\u0000\u00ec\u06ef\u0001"+
		"\u0000\u0000\u0000\u00ee\u06f1\u0001\u0000\u0000\u0000\u00f0\u06fc\u0001"+
		"\u0000\u0000\u0000\u00f2\u070d\u0001\u0000\u0000\u0000\u00f4\u070f\u0001"+
		"\u0000\u0000\u0000\u00f6\u072b\u0001\u0000\u0000\u0000\u00f8\u072d\u0001"+
		"\u0000\u0000\u0000\u00fa\u0731\u0001\u0000\u0000\u0000\u00fc\u0737\u0001"+
		"\u0000\u0000\u0000\u00fe\u073c\u0001\u0000\u0000\u0000\u0100\u073e\u0001"+
		"\u0000\u0000\u0000\u0102\u0742\u0001\u0000\u0000\u0000\u0104\u074d\u0001"+
		"\u0000\u0000\u0000\u0106\u0764\u0001\u0000\u0000\u0000\u0108\u0766\u0001"+
		"\u0000\u0000\u0000\u010a\u077a\u0001\u0000\u0000\u0000\u010c\u0781\u0001"+
		"\u0000\u0000\u0000\u010e\u0783\u0001\u0000\u0000\u0000\u0110\u0112\u0003"+
		"\u0004\u0002\u0000\u0111\u0110\u0001\u0000\u0000\u0000\u0111\u0112\u0001"+
		"\u0000\u0000\u0000\u0112\u0117\u0001\u0000\u0000\u0000\u0113\u0116\u0003"+
		"\u0006\u0003\u0000\u0114\u0116\u0005Z\u0000\u0000\u0115\u0113\u0001\u0000"+
		"\u0000\u0000\u0115\u0114\u0001\u0000\u0000\u0000\u0116\u0119\u0001\u0000"+
		"\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0117\u0118\u0001\u0000"+
		"\u0000\u0000\u0118\u011d\u0001\u0000\u0000\u0000\u0119\u0117\u0001\u0000"+
		"\u0000\u0000\u011a\u011c\u0003\b\u0004\u0000\u011b\u011a\u0001\u0000\u0000"+
		"\u0000\u011c\u011f\u0001\u0000\u0000\u0000\u011d\u011b\u0001\u0000\u0000"+
		"\u0000\u011d\u011e\u0001\u0000\u0000\u0000\u011e\u0124\u0001\u0000\u0000"+
		"\u0000\u011f\u011d\u0001\u0000\u0000\u0000\u0120\u0123\u0003\u0016\u000b"+
		"\u0000\u0121\u0123\u0005Z\u0000\u0000\u0122\u0120\u0001\u0000\u0000\u0000"+
		"\u0122\u0121\u0001\u0000\u0000\u0000\u0123\u0126\u0001\u0000\u0000\u0000"+
		"\u0124\u0122\u0001\u0000\u0000\u0000\u0124\u0125\u0001\u0000\u0000\u0000"+
		"\u0125\u0127\u0001\u0000\u0000\u0000\u0126\u0124\u0001\u0000\u0000\u0000"+
		"\u0127\u012c\u0005\u0000\u0000\u0001\u0128\u0129\u0003\u0002\u0001\u0000"+
		"\u0129\u012a\u0005\u0000\u0000\u0001\u012a\u012c\u0001\u0000\u0000\u0000"+
		"\u012b\u0111\u0001\u0000\u0000\u0000\u012b\u0128\u0001\u0000\u0000\u0000"+
		"\u012c\u0001\u0001\u0000\u0000\u0000\u012d\u012f\u0003\u0006\u0003\u0000"+
		"\u012e\u012d\u0001\u0000\u0000\u0000\u012f\u0132\u0001\u0000\u0000\u0000"+
		"\u0130\u012e\u0001\u0000\u0000\u0000\u0130\u0131\u0001\u0000\u0000\u0000"+
		"\u0131\u0133\u0001\u0000\u0000\u0000\u0132\u0130\u0001\u0000\u0000\u0000"+
		"\u0133\u0134\u0003\u009cN\u0000\u0134\u0003\u0001\u0000\u0000\u0000\u0135"+
		"\u0137\u0003\u0080@\u0000\u0136\u0135\u0001\u0000\u0000\u0000\u0137\u013a"+
		"\u0001\u0000\u0000\u0000\u0138\u0136\u0001\u0000\u0000\u0000\u0138\u0139"+
		"\u0001\u0000\u0000\u0000\u0139\u013b\u0001\u0000\u0000\u0000\u013a\u0138"+
		"\u0001\u0000\u0000\u0000\u013b\u013c\u0005\'\u0000\u0000\u013c\u013d\u0003"+
		"v;\u0000\u013d\u013e\u0005Z\u0000\u0000\u013e\u0005\u0001\u0000\u0000"+
		"\u0000\u013f\u0141\u0005\u001b\u0000\u0000\u0140\u0142\u00052\u0000\u0000"+
		"\u0141\u0140\u0001\u0000\u0000\u0000\u0141\u0142\u0001\u0000\u0000\u0000"+
		"\u0142\u0143\u0001\u0000\u0000\u0000\u0143\u0146\u0003v;\u0000\u0144\u0145"+
		"\u0005\\\u0000\u0000\u0145\u0147\u0005n\u0000\u0000\u0146\u0144\u0001"+
		"\u0000\u0000\u0000\u0146\u0147\u0001\u0000\u0000\u0000\u0147\u0148\u0001"+
		"\u0000\u0000\u0000\u0148\u0149\u0005Z\u0000\u0000\u0149\u0150\u0001\u0000"+
		"\u0000\u0000\u014a\u014b\u0005\u001b\u0000\u0000\u014b\u014c\u0005!\u0000"+
		"\u0000\u014c\u014d\u0003v;\u0000\u014d\u014e\u0005Z\u0000\u0000\u014e"+
		"\u0150\u0001\u0000\u0000\u0000\u014f\u013f\u0001\u0000\u0000\u0000\u014f"+
		"\u014a\u0001\u0000\u0000\u0000\u0150\u0007\u0001\u0000\u0000\u0000\u0151"+
		"\u0152\u0003\n\u0005\u0000\u0152\u0153\u0005Z\u0000\u0000\u0153\u0156"+
		"\u0001\u0000\u0000\u0000\u0154\u0156\u0003\f\u0006\u0000\u0155\u0151\u0001"+
		"\u0000\u0000\u0000\u0155\u0154\u0001\u0000\u0000\u0000\u0156\t\u0001\u0000"+
		"\u0000\u0000\u0157\u0158\u0005\u0002\u0000\u0000\u0158\u0159\u0003\u000e"+
		"\u0007\u0000\u0159\u000b\u0001\u0000\u0000\u0000\u015a\u015b\u0005\u0002"+
		"\u0000\u0000\u015b\u015c\u0005V\u0000\u0000\u015c\u0160\u0003\u0014\n"+
		"\u0000\u015d\u015f\u0003\u0014\n\u0000\u015e\u015d\u0001\u0000\u0000\u0000"+
		"\u015f\u0162\u0001\u0000\u0000\u0000\u0160\u015e\u0001\u0000\u0000\u0000"+
		"\u0160\u0161\u0001\u0000\u0000\u0000\u0161\u0163\u0001\u0000\u0000\u0000"+
		"\u0162\u0160\u0001\u0000\u0000\u0000\u0163\u0164\u0005W\u0000\u0000\u0164"+
		"\r\u0001\u0000\u0000\u0000\u0165\u016a\u0003\u0010\b\u0000\u0166\u0167"+
		"\u0005[\u0000\u0000\u0167\u0169\u0003\u0010\b\u0000\u0168\u0166\u0001"+
		"\u0000\u0000\u0000\u0169\u016c\u0001\u0000\u0000\u0000\u016a\u0168\u0001"+
		"\u0000\u0000\u0000\u016a\u016b\u0001\u0000\u0000\u0000\u016b\u000f\u0001"+
		"\u0000\u0000\u0000\u016c\u016a\u0001\u0000\u0000\u0000\u016d\u0172\u0003"+
		"\u00b2Y\u0000\u016e\u016f\u0005T\u0000\u0000\u016f\u0170\u0003\u0012\t"+
		"\u0000\u0170\u0171\u0005U\u0000\u0000\u0171\u0173\u0001\u0000\u0000\u0000"+
		"\u0172\u016e\u0001\u0000\u0000\u0000\u0172\u0173\u0001\u0000\u0000\u0000"+
		"\u0173\u0011\u0001\u0000\u0000\u0000\u0174\u0179\u0003\u00b2Y\u0000\u0175"+
		"\u0176\u0005[\u0000\u0000\u0176\u0178\u0003\u00b2Y\u0000\u0177\u0175\u0001"+
		"\u0000\u0000\u0000\u0178\u017b\u0001\u0000\u0000\u0000\u0179\u0177\u0001"+
		"\u0000\u0000\u0000\u0179\u017a\u0001\u0000\u0000\u0000\u017a\u0013\u0001"+
		"\u0000\u0000\u0000\u017b\u0179\u0001\u0000\u0000\u0000\u017c\u017d\u0003"+
		"v;\u0000\u017d\u017e\u0005c\u0000\u0000\u017e\u017f\u0003\u000e\u0007"+
		"\u0000\u017f\u0180\u0005Z\u0000\u0000\u0180\u0015\u0001\u0000\u0000\u0000"+
		"\u0181\u0183\u0003\u001a\r\u0000\u0182\u0181\u0001\u0000\u0000\u0000\u0183"+
		"\u0186\u0001\u0000\u0000\u0000\u0184\u0182\u0001\u0000\u0000\u0000\u0184"+
		"\u0185\u0001\u0000\u0000\u0000\u0185\u018c\u0001\u0000\u0000\u0000\u0186"+
		"\u0184\u0001\u0000\u0000\u0000\u0187\u018d\u0003\u001e\u000f\u0000\u0188"+
		"\u018d\u0003&\u0013\u0000\u0189\u018d\u0003.\u0017\u0000\u018a\u018d\u0003"+
		"\u008cF\u0000\u018b\u018d\u0003\u00a2Q\u0000\u018c\u0187\u0001\u0000\u0000"+
		"\u0000\u018c\u0188\u0001\u0000\u0000\u0000\u018c\u0189\u0001\u0000\u0000"+
		"\u0000\u018c\u018a\u0001\u0000\u0000\u0000\u018c\u018b\u0001\u0000\u0000"+
		"\u0000\u018d\u0017\u0001\u0000\u0000\u0000\u018e\u0194\u0003\u001a\r\u0000"+
		"\u018f\u0194\u0005\"\u0000\u0000\u0190\u0194\u00056\u0000\u0000\u0191"+
		"\u0194\u0005;\u0000\u0000\u0192\u0194\u0005B\u0000\u0000\u0193\u018e\u0001"+
		"\u0000\u0000\u0000\u0193\u018f\u0001\u0000\u0000\u0000\u0193\u0190\u0001"+
		"\u0000\u0000\u0000\u0193\u0191\u0001\u0000\u0000\u0000\u0193\u0192\u0001"+
		"\u0000\u0000\u0000\u0194\u0019\u0001\u0000\u0000\u0000\u0195\u01a1\u0003"+
		"\u0080@\u0000\u0196\u01a1\u0005,\u0000\u0000\u0197\u01a1\u0005*\u0000"+
		"\u0000\u0198\u01a1\u0005)\u0000\u0000\u0199\u01a1\u00052\u0000\u0000\u019a"+
		"\u01a1\u0005\u0001\u0000\u0000\u019b\u01a1\u0005\u0014\u0000\u0000\u019c"+
		"\u01a1\u0005 \u0000\u0000\u019d\u01a1\u00053\u0000\u0000\u019e\u01a1\u0005"+
		"0\u0000\u0000\u019f\u01a1\u0005$\u0000\u0000\u01a0\u0195\u0001\u0000\u0000"+
		"\u0000\u01a0\u0196\u0001\u0000\u0000\u0000\u01a0\u0197\u0001\u0000\u0000"+
		"\u0000\u01a0\u0198\u0001\u0000\u0000\u0000\u01a0\u0199\u0001\u0000\u0000"+
		"\u0000\u01a0\u019a\u0001\u0000\u0000\u0000\u01a0\u019b\u0001\u0000\u0000"+
		"\u0000\u01a0\u019c\u0001\u0000\u0000\u0000\u01a0\u019d\u0001\u0000\u0000"+
		"\u0000\u01a0\u019e\u0001\u0000\u0000\u0000\u01a0\u019f\u0001\u0000\u0000"+
		"\u0000\u01a1\u001b\u0001\u0000\u0000\u0000\u01a2\u01a6\u0005\u0014\u0000"+
		"\u0000\u01a3\u01a6\u0005 \u0000\u0000\u01a4\u01a6\u0003\u0080@\u0000\u01a5"+
		"\u01a2\u0001\u0000\u0000\u0000\u01a5\u01a3\u0001\u0000\u0000\u0000\u01a5"+
		"\u01a4\u0001\u0000\u0000\u0000\u01a6\u001d\u0001\u0000\u0000\u0000\u01a7"+
		"\u01a8\u0005\n\u0000\u0000\u01a8\u01aa\u0003\u00b2Y\u0000\u01a9\u01ab"+
		"\u0003 \u0010\u0000\u01aa\u01a9\u0001\u0000\u0000\u0000\u01aa\u01ab\u0001"+
		"\u0000\u0000\u0000\u01ab\u01ae\u0001\u0000\u0000\u0000\u01ac\u01ad\u0005"+
		"\u0013\u0000\u0000\u01ad\u01af\u0003\u0104\u0082\u0000\u01ae\u01ac\u0001"+
		"\u0000\u0000\u0000\u01ae\u01af\u0001\u0000\u0000\u0000\u01af\u01b2\u0001"+
		"\u0000\u0000\u0000\u01b0\u01b1\u0005\u001a\u0000\u0000\u01b1\u01b3\u0003"+
		"\u0102\u0081\u0000\u01b2\u01b0\u0001\u0000\u0000\u0000\u01b2\u01b3\u0001"+
		"\u0000\u0000\u0000\u01b3\u01b6\u0001\u0000\u0000\u0000\u01b4\u01b5\u0005"+
		"(\u0000\u0000\u01b5\u01b7\u0003\u0102\u0081\u0000\u01b6\u01b4\u0001\u0000"+
		"\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7\u01b8\u0001\u0000"+
		"\u0000\u0000\u01b8\u01b9\u00030\u0018\u0000\u01b9\u001f\u0001\u0000\u0000"+
		"\u0000\u01ba\u01bb\u0005_\u0000\u0000\u01bb\u01c0\u0003\"\u0011\u0000"+
		"\u01bc\u01bd\u0005[\u0000\u0000\u01bd\u01bf\u0003\"\u0011\u0000\u01be"+
		"\u01bc\u0001\u0000\u0000\u0000\u01bf\u01c2\u0001\u0000\u0000\u0000\u01c0"+
		"\u01be\u0001\u0000\u0000\u0000\u01c0\u01c1\u0001\u0000\u0000\u0000\u01c1"+
		"\u01c3\u0001\u0000\u0000\u0000\u01c2\u01c0\u0001\u0000\u0000\u0000\u01c3"+
		"\u01c4\u0005^\u0000\u0000\u01c4!\u0001\u0000\u0000\u0000\u01c5\u01c7\u0003"+
		"\u0080@\u0000\u01c6\u01c5\u0001\u0000\u0000\u0000\u01c7\u01ca\u0001\u0000"+
		"\u0000\u0000\u01c8\u01c6\u0001\u0000\u0000\u0000\u01c8\u01c9\u0001\u0000"+
		"\u0000\u0000\u01c9\u01cb\u0001\u0000\u0000\u0000\u01ca\u01c8\u0001\u0000"+
		"\u0000\u0000\u01cb\u01d4\u0003\u00b2Y\u0000\u01cc\u01d0\u0005\u0013\u0000"+
		"\u0000\u01cd\u01cf\u0003\u0080@\u0000\u01ce\u01cd\u0001\u0000\u0000\u0000"+
		"\u01cf\u01d2\u0001\u0000\u0000\u0000\u01d0\u01ce\u0001\u0000\u0000\u0000"+
		"\u01d0\u01d1\u0001\u0000\u0000\u0000\u01d1\u01d3\u0001\u0000\u0000\u0000"+
		"\u01d2\u01d0\u0001\u0000\u0000\u0000\u01d3\u01d5\u0003$\u0012\u0000\u01d4"+
		"\u01cc\u0001\u0000\u0000\u0000\u01d4\u01d5\u0001\u0000\u0000\u0000\u01d5"+
		"#\u0001\u0000\u0000\u0000\u01d6\u01db\u0003\u0104\u0082\u0000\u01d7\u01d8"+
		"\u0005p\u0000\u0000\u01d8\u01da\u0003\u0104\u0082\u0000\u01d9\u01d7\u0001"+
		"\u0000\u0000\u0000\u01da\u01dd\u0001\u0000\u0000\u0000\u01db\u01d9\u0001"+
		"\u0000\u0000\u0000\u01db\u01dc\u0001\u0000\u0000\u0000\u01dc%\u0001\u0000"+
		"\u0000\u0000\u01dd\u01db\u0001\u0000\u0000\u0000\u01de\u01df\u0005\u0011"+
		"\u0000\u0000\u01df\u01e2\u0003\u00b2Y\u0000\u01e0\u01e1\u0005\u001a\u0000"+
		"\u0000\u01e1\u01e3\u0003\u0102\u0081\u0000\u01e2\u01e0\u0001\u0000\u0000"+
		"\u0000\u01e2\u01e3\u0001\u0000\u0000\u0000\u01e3\u01e4\u0001\u0000\u0000"+
		"\u0000\u01e4\u01e6\u0005V\u0000\u0000\u01e5\u01e7\u0003(\u0014\u0000\u01e6"+
		"\u01e5\u0001\u0000\u0000\u0000\u01e6\u01e7\u0001\u0000\u0000\u0000\u01e7"+
		"\u01e9\u0001\u0000\u0000\u0000\u01e8\u01ea\u0005[\u0000\u0000\u01e9\u01e8"+
		"\u0001\u0000\u0000\u0000\u01e9\u01ea\u0001\u0000\u0000\u0000\u01ea\u01ec"+
		"\u0001\u0000\u0000\u0000\u01eb\u01ed\u0003,\u0016\u0000\u01ec\u01eb\u0001"+
		"\u0000\u0000\u0000\u01ec\u01ed\u0001\u0000\u0000\u0000\u01ed\u01ee\u0001"+
		"\u0000\u0000\u0000\u01ee\u01ef\u0005W\u0000\u0000\u01ef\'\u0001\u0000"+
		"\u0000\u0000\u01f0\u01f5\u0003*\u0015\u0000\u01f1\u01f2\u0005[\u0000\u0000"+
		"\u01f2\u01f4\u0003*\u0015\u0000\u01f3\u01f1\u0001\u0000\u0000\u0000\u01f4"+
		"\u01f7\u0001\u0000\u0000\u0000\u01f5\u01f3\u0001\u0000\u0000\u0000\u01f5"+
		"\u01f6\u0001\u0000\u0000\u0000\u01f6)\u0001\u0000\u0000\u0000\u01f7\u01f5"+
		"\u0001\u0000\u0000\u0000\u01f8\u01fa\u0003\u0080@\u0000\u01f9\u01f8\u0001"+
		"\u0000\u0000\u0000\u01fa\u01fd\u0001\u0000\u0000\u0000\u01fb\u01f9\u0001"+
		"\u0000\u0000\u0000\u01fb\u01fc\u0001\u0000\u0000\u0000\u01fc\u01fe\u0001"+
		"\u0000\u0000\u0000\u01fd\u01fb\u0001\u0000\u0000\u0000\u01fe\u0200\u0003"+
		"\u00b2Y\u0000\u01ff\u0201\u0003\u010e\u0087\u0000\u0200\u01ff\u0001\u0000"+
		"\u0000\u0000\u0200\u0201\u0001\u0000\u0000\u0000\u0201\u0203\u0001\u0000"+
		"\u0000\u0000\u0202\u0204\u00030\u0018\u0000\u0203\u0202\u0001\u0000\u0000"+
		"\u0000\u0203\u0204\u0001\u0000\u0000\u0000\u0204+\u0001\u0000\u0000\u0000"+
		"\u0205\u0209\u0005Z\u0000\u0000\u0206\u0208\u00034\u001a\u0000\u0207\u0206"+
		"\u0001\u0000\u0000\u0000\u0208\u020b\u0001\u0000\u0000\u0000\u0209\u0207"+
		"\u0001\u0000\u0000\u0000\u0209\u020a\u0001\u0000\u0000\u0000\u020a-\u0001"+
		"\u0000\u0000\u0000\u020b\u0209\u0001\u0000\u0000\u0000\u020c\u020d\u0005"+
		"\u001e\u0000\u0000\u020d\u020f\u0003\u00b2Y\u0000\u020e\u0210\u0003 \u0010"+
		"\u0000\u020f\u020e\u0001\u0000\u0000\u0000\u020f\u0210\u0001\u0000\u0000"+
		"\u0000\u0210\u0213\u0001\u0000\u0000\u0000\u0211\u0212\u0005\u0013\u0000"+
		"\u0000\u0212\u0214\u0003\u0102\u0081\u0000\u0213\u0211\u0001\u0000\u0000"+
		"\u0000\u0213\u0214\u0001\u0000\u0000\u0000\u0214\u0217\u0001\u0000\u0000"+
		"\u0000\u0215\u0216\u0005(\u0000\u0000\u0216\u0218\u0003\u0102\u0081\u0000"+
		"\u0217\u0215\u0001\u0000\u0000\u0000\u0217\u0218\u0001\u0000\u0000\u0000"+
		"\u0218\u0219\u0001\u0000\u0000\u0000\u0219\u021a\u00032\u0019\u0000\u021a"+
		"/\u0001\u0000\u0000\u0000\u021b\u021f\u0005V\u0000\u0000\u021c\u021e\u0003"+
		"4\u001a\u0000\u021d\u021c\u0001\u0000\u0000\u0000\u021e\u0221\u0001\u0000"+
		"\u0000\u0000\u021f\u021d\u0001\u0000\u0000\u0000\u021f\u0220\u0001\u0000"+
		"\u0000\u0000\u0220\u0222\u0001\u0000\u0000\u0000\u0221\u021f\u0001\u0000"+
		"\u0000\u0000\u0222\u0223\u0005W\u0000\u0000\u02231\u0001\u0000\u0000\u0000"+
		"\u0224\u0228\u0005V\u0000\u0000\u0225\u0227\u0003H$\u0000\u0226\u0225"+
		"\u0001\u0000\u0000\u0000\u0227\u022a\u0001\u0000\u0000\u0000\u0228\u0226"+
		"\u0001\u0000\u0000\u0000\u0228\u0229\u0001\u0000\u0000\u0000\u0229\u022b"+
		"\u0001\u0000\u0000\u0000\u022a\u0228\u0001\u0000\u0000\u0000\u022b\u022c"+
		"\u0005W\u0000\u0000\u022c3\u0001\u0000\u0000\u0000\u022d\u023a\u0005Z"+
		"\u0000\u0000\u022e\u0230\u00052\u0000\u0000\u022f\u022e\u0001\u0000\u0000"+
		"\u0000\u022f\u0230\u0001\u0000\u0000\u0000\u0230\u0231\u0001\u0000\u0000"+
		"\u0000\u0231\u023a\u0003\u00acV\u0000\u0232\u0234\u0003\u0018\f\u0000"+
		"\u0233\u0232\u0001\u0000\u0000\u0000\u0234\u0237\u0001\u0000\u0000\u0000"+
		"\u0235\u0233\u0001\u0000\u0000\u0000\u0235\u0236\u0001\u0000\u0000\u0000"+
		"\u0236\u0238\u0001\u0000\u0000\u0000\u0237\u0235\u0001\u0000\u0000\u0000"+
		"\u0238\u023a\u00036\u001b\u0000\u0239\u022d\u0001\u0000\u0000\u0000\u0239"+
		"\u022f\u0001\u0000\u0000\u0000\u0239\u0235\u0001\u0000\u0000\u0000\u023a"+
		"5\u0001\u0000\u0000\u0000\u023b\u0246\u0003\u00a2Q\u0000\u023c\u0246\u0003"+
		"8\u001c\u0000\u023d\u0246\u0003>\u001f\u0000\u023e\u0246\u0003F#\u0000"+
		"\u023f\u0246\u0003B!\u0000\u0240\u0246\u0003@ \u0000\u0241\u0246\u0003"+
		".\u0017\u0000\u0242\u0246\u0003\u008cF\u0000\u0243\u0246\u0003\u001e\u000f"+
		"\u0000\u0244\u0246\u0003&\u0013\u0000\u0245\u023b\u0001\u0000\u0000\u0000"+
		"\u0245\u023c\u0001\u0000\u0000\u0000\u0245\u023d\u0001\u0000\u0000\u0000"+
		"\u0245\u023e\u0001\u0000\u0000\u0000\u0245\u023f\u0001\u0000\u0000\u0000"+
		"\u0245\u0240\u0001\u0000\u0000\u0000\u0245\u0241\u0001\u0000\u0000\u0000"+
		"\u0245\u0242\u0001\u0000\u0000\u0000\u0245\u0243\u0001\u0000\u0000\u0000"+
		"\u0245\u0244\u0001\u0000\u0000\u0000\u02467\u0001\u0000\u0000\u0000\u0247"+
		"\u0248\u0003<\u001e\u0000\u0248\u0249\u0003\u00b2Y\u0000\u0249\u024e\u0003"+
		"j5\u0000\u024a\u024b\u0005X\u0000\u0000\u024b\u024d\u0005Y\u0000\u0000"+
		"\u024c\u024a\u0001\u0000\u0000\u0000\u024d\u0250\u0001\u0000\u0000\u0000"+
		"\u024e\u024c\u0001\u0000\u0000\u0000\u024e\u024f\u0001\u0000\u0000\u0000"+
		"\u024f\u0253\u0001\u0000\u0000\u0000\u0250\u024e\u0001\u0000\u0000\u0000"+
		"\u0251\u0252\u00059\u0000\u0000\u0252\u0254\u0003h4\u0000\u0253\u0251"+
		"\u0001\u0000\u0000\u0000\u0253\u0254\u0001\u0000\u0000\u0000\u0254\u0255"+
		"\u0001\u0000\u0000\u0000\u0255\u0256\u0003:\u001d\u0000\u02569\u0001\u0000"+
		"\u0000\u0000\u0257\u025a\u0003\u00acV\u0000\u0258\u025a\u0005Z\u0000\u0000"+
		"\u0259\u0257\u0001\u0000\u0000\u0000\u0259\u0258\u0001\u0000\u0000\u0000"+
		"\u025a;\u0001\u0000\u0000\u0000\u025b\u025e\u0003\u0104\u0082\u0000\u025c"+
		"\u025e\u0005A\u0000\u0000\u025d\u025b\u0001\u0000\u0000\u0000\u025d\u025c"+
		"\u0001\u0000\u0000\u0000\u025e=\u0001\u0000\u0000\u0000\u025f\u0260\u0003"+
		" \u0010\u0000\u0260\u0261\u00038\u001c\u0000\u0261?\u0001\u0000\u0000"+
		"\u0000\u0262\u0263\u0003 \u0010\u0000\u0263\u0264\u0003B!\u0000\u0264"+
		"A\u0001\u0000\u0000\u0000\u0265\u0266\u0003\u00b2Y\u0000\u0266\u0269\u0003"+
		"j5\u0000\u0267\u0268\u00059\u0000\u0000\u0268\u026a\u0003h4\u0000\u0269"+
		"\u0267\u0001\u0000\u0000\u0000\u0269\u026a\u0001\u0000\u0000\u0000\u026a"+
		"\u026b\u0001\u0000\u0000\u0000\u026b\u026c\u0003\u00acV\u0000\u026cC\u0001"+
		"\u0000\u0000\u0000\u026d\u026f\u0003\u0018\f\u0000\u026e\u026d\u0001\u0000"+
		"\u0000\u0000\u026f\u0272\u0001\u0000\u0000\u0000\u0270\u026e\u0001\u0000"+
		"\u0000\u0000\u0270\u0271\u0001\u0000\u0000\u0000\u0271\u0273\u0001\u0000"+
		"\u0000\u0000\u0272\u0270\u0001\u0000\u0000\u0000\u0273\u0274\u0003\u00b2"+
		"Y\u0000\u0274\u0275\u0003\u00acV\u0000\u0275E\u0001\u0000\u0000\u0000"+
		"\u0276\u0277\u0003\u0104\u0082\u0000\u0277\u0278\u0003X,\u0000\u0278\u0279"+
		"\u0005Z\u0000\u0000\u0279G\u0001\u0000\u0000\u0000\u027a\u027c\u0003\u0018"+
		"\f\u0000\u027b\u027a\u0001\u0000\u0000\u0000\u027c\u027f\u0001\u0000\u0000"+
		"\u0000\u027d\u027b\u0001\u0000\u0000\u0000\u027d\u027e\u0001\u0000\u0000"+
		"\u0000\u027e\u0280\u0001\u0000\u0000\u0000\u027f\u027d\u0001\u0000\u0000"+
		"\u0000\u0280\u0283\u0003J%\u0000\u0281\u0283\u0005Z\u0000\u0000\u0282"+
		"\u027d\u0001\u0000\u0000\u0000\u0282\u0281\u0001\u0000\u0000\u0000\u0283"+
		"I\u0001\u0000\u0000\u0000\u0284\u028d\u0003\u00a2Q\u0000\u0285\u028d\u0003"+
		"L&\u0000\u0286\u028d\u0003P(\u0000\u0287\u028d\u0003T*\u0000\u0288\u028d"+
		"\u0003.\u0017\u0000\u0289\u028d\u0003\u008cF\u0000\u028a\u028d\u0003\u001e"+
		"\u000f\u0000\u028b\u028d\u0003&\u0013\u0000\u028c\u0284\u0001\u0000\u0000"+
		"\u0000\u028c\u0285\u0001\u0000\u0000\u0000\u028c\u0286\u0001\u0000\u0000"+
		"\u0000\u028c\u0287\u0001\u0000\u0000\u0000\u028c\u0288\u0001\u0000\u0000"+
		"\u0000\u028c\u0289\u0001\u0000\u0000\u0000\u028c\u028a\u0001\u0000\u0000"+
		"\u0000\u028c\u028b\u0001\u0000\u0000\u0000\u028dK\u0001\u0000\u0000\u0000"+
		"\u028e\u028f\u0003\u0104\u0082\u0000\u028f\u0294\u0003N\'\u0000\u0290"+
		"\u0291\u0005[\u0000\u0000\u0291\u0293\u0003N\'\u0000\u0292\u0290\u0001"+
		"\u0000\u0000\u0000\u0293\u0296\u0001\u0000\u0000\u0000\u0294\u0292\u0001"+
		"\u0000\u0000\u0000\u0294\u0295\u0001\u0000\u0000\u0000\u0295\u0297\u0001"+
		"\u0000\u0000\u0000\u0296\u0294\u0001\u0000\u0000\u0000\u0297\u0298\u0005"+
		"Z\u0000\u0000\u0298M\u0001\u0000\u0000\u0000\u0299\u029e\u0003\u00b2Y"+
		"\u0000\u029a\u029b\u0005X\u0000\u0000\u029b\u029d\u0005Y\u0000\u0000\u029c"+
		"\u029a\u0001\u0000\u0000\u0000\u029d\u02a0\u0001\u0000\u0000\u0000\u029e"+
		"\u029c\u0001\u0000\u0000\u0000\u029e\u029f\u0001\u0000\u0000\u0000\u029f"+
		"\u02a1\u0001\u0000\u0000\u0000\u02a0\u029e\u0001\u0000\u0000\u0000\u02a1"+
		"\u02a2\u0005]\u0000\u0000\u02a2\u02a3\u0003^/\u0000\u02a3O\u0001\u0000"+
		"\u0000\u0000\u02a4\u02a6\u0003R)\u0000\u02a5\u02a4\u0001\u0000\u0000\u0000"+
		"\u02a6\u02a9\u0001\u0000\u0000\u0000\u02a7\u02a5\u0001\u0000\u0000\u0000"+
		"\u02a7\u02a8\u0001\u0000\u0000\u0000\u02a8\u02aa\u0001\u0000\u0000\u0000"+
		"\u02a9\u02a7\u0001\u0000\u0000\u0000\u02aa\u02ab\u0003V+\u0000\u02abQ"+
		"\u0001\u0000\u0000\u0000\u02ac\u02b3\u0003\u0080@\u0000\u02ad\u02b3\u0005"+
		",\u0000\u0000\u02ae\u02b3\u0005\u0001\u0000\u0000\u02af\u02b3\u0005\r"+
		"\u0000\u0000\u02b0\u02b3\u00052\u0000\u0000\u02b1\u02b3\u00053\u0000\u0000"+
		"\u02b2\u02ac\u0001\u0000\u0000\u0000\u02b2\u02ad\u0001\u0000\u0000\u0000"+
		"\u02b2\u02ae\u0001\u0000\u0000\u0000\u02b2\u02af\u0001\u0000\u0000\u0000"+
		"\u02b2\u02b0\u0001\u0000\u0000\u0000\u02b2\u02b1\u0001\u0000\u0000\u0000"+
		"\u02b3S\u0001\u0000\u0000\u0000\u02b4\u02b6\u0003R)\u0000\u02b5\u02b4"+
		"\u0001\u0000\u0000\u0000\u02b6\u02b9\u0001\u0000\u0000\u0000\u02b7\u02b5"+
		"\u0001\u0000\u0000\u0000\u02b7\u02b8\u0001\u0000\u0000\u0000\u02b8\u02ba"+
		"\u0001\u0000\u0000\u0000\u02b9\u02b7\u0001\u0000\u0000\u0000\u02ba\u02bb"+
		"\u0003 \u0010\u0000\u02bb\u02bc\u0003V+\u0000\u02bcU\u0001\u0000\u0000"+
		"\u0000\u02bd\u02bf\u0003\u0080@\u0000\u02be\u02bd\u0001\u0000\u0000\u0000"+
		"\u02bf\u02c2\u0001\u0000\u0000\u0000\u02c0\u02be\u0001\u0000\u0000\u0000"+
		"\u02c0\u02c1\u0001\u0000\u0000\u0000\u02c1\u02c3\u0001\u0000\u0000\u0000"+
		"\u02c2\u02c0\u0001\u0000\u0000\u0000\u02c3\u02c4\u0003<\u001e\u0000\u02c4"+
		"\u02c5\u0003\u00b2Y\u0000\u02c5\u02ca\u0003j5\u0000\u02c6\u02c7\u0005"+
		"X\u0000\u0000\u02c7\u02c9\u0005Y\u0000\u0000\u02c8\u02c6\u0001\u0000\u0000"+
		"\u0000\u02c9\u02cc\u0001\u0000\u0000\u0000\u02ca\u02c8\u0001\u0000\u0000"+
		"\u0000\u02ca\u02cb\u0001\u0000\u0000\u0000\u02cb\u02cf\u0001\u0000\u0000"+
		"\u0000\u02cc\u02ca\u0001\u0000\u0000\u0000\u02cd\u02ce\u00059\u0000\u0000"+
		"\u02ce\u02d0\u0003h4\u0000\u02cf\u02cd\u0001\u0000\u0000\u0000\u02cf\u02d0"+
		"\u0001\u0000\u0000\u0000\u02d0\u02d1\u0001\u0000\u0000\u0000\u02d1\u02d2"+
		"\u0003:\u001d\u0000\u02d2W\u0001\u0000\u0000\u0000\u02d3\u02d8\u0003Z"+
		"-\u0000\u02d4\u02d5\u0005[\u0000\u0000\u02d5\u02d7\u0003Z-\u0000\u02d6"+
		"\u02d4\u0001\u0000\u0000\u0000\u02d7\u02da\u0001\u0000\u0000\u0000\u02d8"+
		"\u02d6\u0001\u0000\u0000\u0000\u02d8\u02d9\u0001\u0000\u0000\u0000\u02d9"+
		"Y\u0001\u0000\u0000\u0000\u02da\u02d8\u0001\u0000\u0000\u0000\u02db\u02de"+
		"\u0003\\.\u0000\u02dc\u02dd\u0005]\u0000\u0000\u02dd\u02df\u0003^/\u0000"+
		"\u02de\u02dc\u0001\u0000\u0000\u0000\u02de\u02df\u0001\u0000\u0000\u0000"+
		"\u02df[\u0001\u0000\u0000\u0000\u02e0\u02e5\u0003\u00b2Y\u0000\u02e1\u02e2"+
		"\u0005X\u0000\u0000\u02e2\u02e4\u0005Y\u0000\u0000\u02e3\u02e1\u0001\u0000"+
		"\u0000\u0000\u02e4\u02e7\u0001\u0000\u0000\u0000\u02e5\u02e3\u0001\u0000"+
		"\u0000\u0000\u02e5\u02e6\u0001\u0000\u0000\u0000\u02e6]\u0001\u0000\u0000"+
		"\u0000\u02e7\u02e5\u0001\u0000\u0000\u0000\u02e8\u02eb\u0003`0\u0000\u02e9"+
		"\u02eb\u0003\u00d4j\u0000\u02ea\u02e8\u0001\u0000\u0000\u0000\u02ea\u02e9"+
		"\u0001\u0000\u0000\u0000\u02eb_\u0001\u0000\u0000\u0000\u02ec\u02f8\u0005"+
		"V\u0000\u0000\u02ed\u02f2\u0003^/\u0000\u02ee\u02ef\u0005[\u0000\u0000"+
		"\u02ef\u02f1\u0003^/\u0000\u02f0\u02ee\u0001\u0000\u0000\u0000\u02f1\u02f4"+
		"\u0001\u0000\u0000\u0000\u02f2\u02f0\u0001\u0000\u0000\u0000\u02f2\u02f3"+
		"\u0001\u0000\u0000\u0000\u02f3\u02f6\u0001\u0000\u0000\u0000\u02f4\u02f2"+
		"\u0001\u0000\u0000\u0000\u02f5\u02f7\u0005[\u0000\u0000\u02f6\u02f5\u0001"+
		"\u0000\u0000\u0000\u02f6\u02f7\u0001\u0000\u0000\u0000\u02f7\u02f9\u0001"+
		"\u0000\u0000\u0000\u02f8\u02ed\u0001\u0000\u0000\u0000\u02f8\u02f9\u0001"+
		"\u0000\u0000\u0000\u02f9\u02fa\u0001\u0000\u0000\u0000\u02fa\u02fb\u0005"+
		"W\u0000\u0000\u02fba\u0001\u0000\u0000\u0000\u02fc\u02fd\u0003d2\u0000"+
		"\u02fd\u0301\u0005\\\u0000\u0000\u02fe\u0300\u0003\u0080@\u0000\u02ff"+
		"\u02fe\u0001\u0000\u0000\u0000\u0300\u0303\u0001\u0000\u0000\u0000\u0301"+
		"\u02ff\u0001\u0000\u0000\u0000\u0301\u0302\u0001\u0000\u0000\u0000\u0302"+
		"\u0305\u0001\u0000\u0000\u0000\u0303\u0301\u0001\u0000\u0000\u0000\u0304"+
		"\u02fc\u0001\u0000\u0000\u0000\u0304\u0305\u0001\u0000\u0000\u0000\u0305"+
		"\u0306\u0001\u0000\u0000\u0000\u0306\u0308\u0003\u00b4Z\u0000\u0307\u0309"+
		"\u0003\u0108\u0084\u0000\u0308\u0307\u0001\u0000\u0000\u0000\u0308\u0309"+
		"\u0001\u0000\u0000\u0000\u0309\u030b\u0001\u0000\u0000\u0000\u030a\u0304"+
		"\u0001\u0000\u0000\u0000\u030b\u030c\u0001\u0000\u0000\u0000\u030c\u030a"+
		"\u0001\u0000\u0000\u0000\u030c\u030d\u0001\u0000\u0000\u0000\u030d\u031b"+
		"\u0001\u0000\u0000\u0000\u030e\u0312\u0005\\\u0000\u0000\u030f\u0311\u0003"+
		"\u0080@\u0000\u0310\u030f\u0001\u0000\u0000\u0000\u0311\u0314\u0001\u0000"+
		"\u0000\u0000\u0312\u0310\u0001\u0000\u0000\u0000\u0312\u0313\u0001\u0000"+
		"\u0000\u0000\u0313\u0315\u0001\u0000\u0000\u0000\u0314\u0312\u0001\u0000"+
		"\u0000\u0000\u0315\u0317\u0003\u00b4Z\u0000\u0316\u0318\u0003\u0108\u0084"+
		"\u0000\u0317\u0316\u0001\u0000\u0000\u0000\u0317\u0318\u0001\u0000\u0000"+
		"\u0000\u0318\u031a\u0001\u0000\u0000\u0000\u0319\u030e\u0001\u0000\u0000"+
		"\u0000\u031a\u031d\u0001\u0000\u0000\u0000\u031b\u0319\u0001\u0000\u0000"+
		"\u0000\u031b\u031c\u0001\u0000\u0000\u0000\u031cc\u0001\u0000\u0000\u0000"+
		"\u031d\u031b\u0001\u0000\u0000\u0000\u031e\u0323\u0003\u00b2Y\u0000\u031f"+
		"\u0320\u0005\\\u0000\u0000\u0320\u0322\u0003\u00b2Y\u0000\u0321\u031f"+
		"\u0001\u0000\u0000\u0000\u0322\u0325\u0001\u0000\u0000\u0000\u0323\u0321"+
		"\u0001\u0000\u0000\u0000\u0323\u0324\u0001\u0000\u0000\u0000\u0324e\u0001"+
		"\u0000\u0000\u0000\u0325\u0323\u0001\u0000\u0000\u0000\u0326\u0333\u0003"+
		"\u0104\u0082\u0000\u0327\u0329\u0003\u0080@\u0000\u0328\u0327\u0001\u0000"+
		"\u0000\u0000\u0329\u032c\u0001\u0000\u0000\u0000\u032a\u0328\u0001\u0000"+
		"\u0000\u0000\u032a\u032b\u0001\u0000\u0000\u0000\u032b\u032d\u0001\u0000"+
		"\u0000\u0000\u032c\u032a\u0001\u0000\u0000\u0000\u032d\u0330\u0005b\u0000"+
		"\u0000\u032e\u032f\u0007\u0000\u0000\u0000\u032f\u0331\u0003\u0104\u0082"+
		"\u0000\u0330\u032e\u0001\u0000\u0000\u0000\u0330\u0331\u0001\u0000\u0000"+
		"\u0000\u0331\u0333\u0001\u0000\u0000\u0000\u0332\u0326\u0001\u0000\u0000"+
		"\u0000\u0332\u032a\u0001\u0000\u0000\u0000\u0333g\u0001\u0000\u0000\u0000"+
		"\u0334\u0339\u0003v;\u0000\u0335\u0336\u0005[\u0000\u0000\u0336\u0338"+
		"\u0003v;\u0000\u0337\u0335\u0001\u0000\u0000\u0000\u0338\u033b\u0001\u0000"+
		"\u0000\u0000\u0339\u0337\u0001\u0000\u0000\u0000\u0339\u033a\u0001\u0000"+
		"\u0000\u0000\u033ai\u0001\u0000\u0000\u0000\u033b\u0339\u0001\u0000\u0000"+
		"\u0000\u033c\u0348\u0005T\u0000\u0000\u033d\u0340\u0003l6\u0000\u033e"+
		"\u0340\u0003p8\u0000\u033f\u033d\u0001\u0000\u0000\u0000\u033f\u033e\u0001"+
		"\u0000\u0000\u0000\u0340\u0345\u0001\u0000\u0000\u0000\u0341\u0342\u0005"+
		"[\u0000\u0000\u0342\u0344\u0003n7\u0000\u0343\u0341\u0001\u0000\u0000"+
		"\u0000\u0344\u0347\u0001\u0000\u0000\u0000\u0345\u0343\u0001\u0000\u0000"+
		"\u0000\u0345\u0346\u0001\u0000\u0000\u0000\u0346\u0349\u0001\u0000\u0000"+
		"\u0000\u0347\u0345\u0001\u0000\u0000\u0000\u0348\u033f\u0001\u0000\u0000"+
		"\u0000\u0348\u0349\u0001\u0000\u0000\u0000\u0349\u034a\u0001\u0000\u0000"+
		"\u0000\u034a\u034b\u0005U\u0000\u0000\u034bk\u0001\u0000\u0000\u0000\u034c"+
		"\u0352\u0003\u0104\u0082\u0000\u034d\u034e\u0003\u00b2Y\u0000\u034e\u034f"+
		"\u0005\\\u0000\u0000\u034f\u0351\u0001\u0000\u0000\u0000\u0350\u034d\u0001"+
		"\u0000\u0000\u0000\u0351\u0354\u0001\u0000\u0000\u0000\u0352\u0350\u0001"+
		"\u0000\u0000\u0000\u0352\u0353\u0001\u0000\u0000\u0000\u0353\u0355\u0001"+
		"\u0000\u0000\u0000\u0354\u0352\u0001\u0000\u0000\u0000\u0355\u0356\u0005"+
		"7\u0000\u0000\u0356m\u0001\u0000\u0000\u0000\u0357\u035c\u0003p8\u0000"+
		"\u0358\u0359\u0005[\u0000\u0000\u0359\u035b\u0003p8\u0000\u035a\u0358"+
		"\u0001\u0000\u0000\u0000\u035b\u035e\u0001\u0000\u0000\u0000\u035c\u035a"+
		"\u0001\u0000\u0000\u0000\u035c\u035d\u0001\u0000\u0000\u0000\u035do\u0001"+
		"\u0000\u0000\u0000\u035e\u035c\u0001\u0000\u0000\u0000\u035f\u0361\u0003"+
		"\u001c\u000e\u0000\u0360\u035f\u0001\u0000\u0000\u0000\u0361\u0364\u0001"+
		"\u0000\u0000\u0000\u0362\u0360\u0001\u0000\u0000\u0000\u0362\u0363\u0001"+
		"\u0000\u0000\u0000\u0363\u0365\u0001\u0000\u0000\u0000\u0364\u0362\u0001"+
		"\u0000\u0000\u0000\u0365\u036d\u0003\u0104\u0082\u0000\u0366\u0368\u0003"+
		"\u0080@\u0000\u0367\u0366\u0001\u0000\u0000\u0000\u0368\u036b\u0001\u0000"+
		"\u0000\u0000\u0369\u0367\u0001\u0000\u0000\u0000\u0369\u036a\u0001\u0000"+
		"\u0000\u0000\u036a\u036c\u0001\u0000\u0000\u0000\u036b\u0369\u0001\u0000"+
		"\u0000\u0000\u036c\u036e\u0005\u0082\u0000\u0000\u036d\u0369\u0001\u0000"+
		"\u0000\u0000\u036d\u036e\u0001\u0000\u0000\u0000\u036e\u036f\u0001\u0000"+
		"\u0000\u0000\u036f\u0370\u0003\\.\u0000\u0370q\u0001\u0000\u0000\u0000"+
		"\u0371\u0376\u0003t:\u0000\u0372\u0373\u0005[\u0000\u0000\u0373\u0375"+
		"\u0003t:\u0000\u0374\u0372\u0001\u0000\u0000\u0000\u0375\u0378\u0001\u0000"+
		"\u0000\u0000\u0376\u0374\u0001\u0000\u0000\u0000\u0376\u0377\u0001\u0000"+
		"\u0000\u0000\u0377s\u0001\u0000\u0000\u0000\u0378\u0376\u0001\u0000\u0000"+
		"\u0000\u0379\u037b\u0003\u001c\u000e\u0000\u037a\u0379\u0001\u0000\u0000"+
		"\u0000\u037b\u037e\u0001\u0000\u0000\u0000\u037c\u037a\u0001\u0000\u0000"+
		"\u0000\u037c\u037d\u0001\u0000\u0000\u0000\u037d\u037f\u0001\u0000\u0000"+
		"\u0000\u037e\u037c\u0001\u0000\u0000\u0000\u037f\u0380\u0005@\u0000\u0000"+
		"\u0380\u0381\u0003\u00b2Y\u0000\u0381u\u0001\u0000\u0000\u0000\u0382\u0387"+
		"\u0003\u00b2Y\u0000\u0383\u0384\u0005\\\u0000\u0000\u0384\u0386\u0003"+
		"\u00b2Y\u0000\u0385\u0383\u0001\u0000\u0000\u0000\u0386\u0389\u0001\u0000"+
		"\u0000\u0000\u0387\u0385\u0001\u0000\u0000\u0000\u0387\u0388\u0001\u0000"+
		"\u0000\u0000\u0388w\u0001\u0000\u0000\u0000\u0389\u0387\u0001\u0000\u0000"+
		"\u0000\u038a\u0392\u0003z=\u0000\u038b\u0392\u0003|>\u0000\u038c\u0392"+
		"\u0005N\u0000\u0000\u038d\u0392\u0005O\u0000\u0000\u038e\u0392\u0005M"+
		"\u0000\u0000\u038f\u0392\u0005Q\u0000\u0000\u0390\u0392\u0005P\u0000\u0000"+
		"\u0391\u038a\u0001\u0000\u0000\u0000\u0391\u038b\u0001\u0000\u0000\u0000"+
		"\u0391\u038c\u0001\u0000\u0000\u0000\u0391\u038d\u0001\u0000\u0000\u0000"+
		"\u0391\u038e\u0001\u0000\u0000\u0000\u0391\u038f\u0001\u0000\u0000\u0000"+
		"\u0391\u0390\u0001\u0000\u0000\u0000\u0392y\u0001\u0000\u0000\u0000\u0393"+
		"\u0394\u0007\u0001\u0000\u0000\u0394{\u0001\u0000\u0000\u0000\u0395\u0396"+
		"\u0007\u0002\u0000\u0000\u0396}\u0001\u0000\u0000\u0000\u0397\u0398\u0003"+
		"\u00b2Y\u0000\u0398\u0399\u0005\\\u0000\u0000\u0399\u039b\u0001\u0000"+
		"\u0000\u0000\u039a\u0397\u0001\u0000\u0000\u0000\u039b\u039e\u0001\u0000"+
		"\u0000\u0000\u039c\u039a\u0001\u0000\u0000\u0000\u039c\u039d\u0001\u0000"+
		"\u0000\u0000\u039d\u039f\u0001\u0000\u0000\u0000\u039e\u039c\u0001\u0000"+
		"\u0000\u0000\u039f\u03a0\u0005\u0081\u0000\u0000\u03a0\u03a1\u0003\u00b2"+
		"Y\u0000\u03a1\u007f\u0001\u0000\u0000\u0000\u03a2\u03a3\u0005\u0081\u0000"+
		"\u0000\u03a3\u03a4\u0003v;\u0000\u03a4\u03a6\u0001\u0000\u0000\u0000\u03a5"+
		"\u03a7\u0003\u0082A\u0000\u03a6\u03a5\u0001\u0000\u0000\u0000\u03a6\u03a7"+
		"\u0001\u0000\u0000\u0000\u03a7\u0081\u0001\u0000\u0000\u0000\u03a8\u03b1"+
		"\u0005T\u0000\u0000\u03a9\u03ae\u0003\u0084B\u0000\u03aa\u03ab\u0005["+
		"\u0000\u0000\u03ab\u03ad\u0003\u0084B\u0000\u03ac\u03aa\u0001\u0000\u0000"+
		"\u0000\u03ad\u03b0\u0001\u0000\u0000\u0000\u03ae\u03ac\u0001\u0000\u0000"+
		"\u0000\u03ae\u03af\u0001\u0000\u0000\u0000\u03af\u03b2\u0001\u0000\u0000"+
		"\u0000\u03b0\u03ae\u0001\u0000\u0000\u0000\u03b1\u03a9\u0001\u0000\u0000"+
		"\u0000\u03b1\u03b2\u0001\u0000\u0000\u0000\u03b2\u03b3\u0001\u0000\u0000"+
		"\u0000\u03b3\u03b4\u0005U\u0000\u0000\u03b4\u0083\u0001\u0000\u0000\u0000"+
		"\u03b5\u03b6\u0004B\u0000\u0000\u03b6\u03bc\u0003\u0086C\u0000\u03b7\u03b8"+
		"\u0003\u00b2Y\u0000\u03b8\u03b9\u0005]\u0000\u0000\u03b9\u03ba\u0003\u0086"+
		"C\u0000\u03ba\u03bc\u0001\u0000\u0000\u0000\u03bb\u03b5\u0001\u0000\u0000"+
		"\u0000\u03bb\u03b7\u0001\u0000\u0000\u0000\u03bc\u0085\u0001\u0000\u0000"+
		"\u0000\u03bd\u03cf\u0003\u00d4j\u0000\u03be\u03cf\u0003\u0080@\u0000\u03bf"+
		"\u03c8\u0005V\u0000\u0000\u03c0\u03c5\u0003\u0086C\u0000\u03c1\u03c2\u0005"+
		"[\u0000\u0000\u03c2\u03c4\u0003\u0086C\u0000\u03c3\u03c1\u0001\u0000\u0000"+
		"\u0000\u03c4\u03c7\u0001\u0000\u0000\u0000\u03c5\u03c3\u0001\u0000\u0000"+
		"\u0000\u03c5\u03c6\u0001\u0000\u0000\u0000\u03c6\u03c9\u0001\u0000\u0000"+
		"\u0000\u03c7\u03c5\u0001\u0000\u0000\u0000\u03c8\u03c0\u0001\u0000\u0000"+
		"\u0000\u03c8\u03c9\u0001\u0000\u0000\u0000\u03c9\u03cb\u0001\u0000\u0000"+
		"\u0000\u03ca\u03cc\u0005[\u0000\u0000\u03cb\u03ca\u0001\u0000\u0000\u0000"+
		"\u03cb\u03cc\u0001\u0000\u0000\u0000\u03cc\u03cd\u0001\u0000\u0000\u0000"+
		"\u03cd\u03cf\u0005W\u0000\u0000\u03ce\u03bd\u0001\u0000\u0000\u0000\u03ce"+
		"\u03be\u0001\u0000\u0000\u0000\u03ce\u03bf\u0001\u0000\u0000\u0000\u03cf"+
		"\u0087\u0001\u0000\u0000\u0000\u03d0\u03d4\u0003\u00d4j\u0000\u03d1\u03d4"+
		"\u0003\u0080@\u0000\u03d2\u03d4\u0003\u008aE\u0000\u03d3\u03d0\u0001\u0000"+
		"\u0000\u0000\u03d3\u03d1\u0001\u0000\u0000\u0000\u03d3\u03d2\u0001\u0000"+
		"\u0000\u0000\u03d4\u0089\u0001\u0000\u0000\u0000\u03d5\u03de\u0005V\u0000"+
		"\u0000\u03d6\u03db\u0003\u0088D\u0000\u03d7\u03d8\u0005[\u0000\u0000\u03d8"+
		"\u03da\u0003\u0088D\u0000\u03d9\u03d7\u0001\u0000\u0000\u0000\u03da\u03dd"+
		"\u0001\u0000\u0000\u0000\u03db\u03d9\u0001\u0000\u0000\u0000\u03db\u03dc"+
		"\u0001\u0000\u0000\u0000\u03dc\u03df\u0001\u0000\u0000\u0000\u03dd\u03db"+
		"\u0001\u0000\u0000\u0000\u03de\u03d6\u0001\u0000\u0000\u0000\u03de\u03df"+
		"\u0001\u0000\u0000\u0000\u03df\u03e1\u0001\u0000\u0000\u0000\u03e0\u03e2"+
		"\u0005[\u0000\u0000\u03e1\u03e0\u0001\u0000\u0000\u0000\u03e1\u03e2\u0001"+
		"\u0000\u0000\u0000\u03e2\u03e3\u0001\u0000\u0000\u0000\u03e3\u03e4\u0005"+
		"W\u0000\u0000\u03e4\u008b\u0001\u0000\u0000\u0000\u03e5\u03e6\u0005\u0081"+
		"\u0000\u0000\u03e6\u03e7\u0005\u001e\u0000\u0000\u03e7\u03e8\u0003\u00b2"+
		"Y\u0000\u03e8\u03e9\u0003\u008eG\u0000\u03e9\u008d\u0001\u0000\u0000\u0000"+
		"\u03ea\u03ee\u0005V\u0000\u0000\u03eb\u03ed\u0003\u0090H\u0000\u03ec\u03eb"+
		"\u0001\u0000\u0000\u0000\u03ed\u03f0\u0001\u0000\u0000\u0000\u03ee\u03ec"+
		"\u0001\u0000\u0000\u0000\u03ee\u03ef\u0001\u0000\u0000\u0000\u03ef\u03f1"+
		"\u0001\u0000\u0000\u0000\u03f0\u03ee\u0001\u0000\u0000\u0000\u03f1\u03f2"+
		"\u0005W\u0000\u0000\u03f2\u008f\u0001\u0000\u0000\u0000\u03f3\u03f5\u0003"+
		"\u0018\f\u0000\u03f4\u03f3\u0001\u0000\u0000\u0000\u03f5\u03f8\u0001\u0000"+
		"\u0000\u0000\u03f6\u03f4\u0001\u0000\u0000\u0000\u03f6\u03f7\u0001\u0000"+
		"\u0000\u0000\u03f7\u03f9\u0001\u0000\u0000\u0000\u03f8\u03f6\u0001\u0000"+
		"\u0000\u0000\u03f9\u03fc\u0003\u0092I\u0000\u03fa\u03fc\u0005Z\u0000\u0000"+
		"\u03fb\u03f6\u0001\u0000\u0000\u0000\u03fb\u03fa\u0001\u0000\u0000\u0000"+
		"\u03fc\u0091\u0001\u0000\u0000\u0000\u03fd\u03fe\u0003\u0104\u0082\u0000"+
		"\u03fe\u03ff\u0003\u0094J\u0000\u03ff\u0400\u0005Z\u0000\u0000\u0400\u0416"+
		"\u0001\u0000\u0000\u0000\u0401\u0403\u0003\u001e\u000f\u0000\u0402\u0404"+
		"\u0005Z\u0000\u0000\u0403\u0402\u0001\u0000\u0000\u0000\u0403\u0404\u0001"+
		"\u0000\u0000\u0000\u0404\u0416\u0001\u0000\u0000\u0000\u0405\u0407\u0003"+
		".\u0017\u0000\u0406\u0408\u0005Z\u0000\u0000\u0407\u0406\u0001\u0000\u0000"+
		"\u0000\u0407\u0408\u0001\u0000\u0000\u0000\u0408\u0416\u0001\u0000\u0000"+
		"\u0000\u0409\u040b\u0003&\u0013\u0000\u040a\u040c\u0005Z\u0000\u0000\u040b"+
		"\u040a\u0001\u0000\u0000\u0000\u040b\u040c\u0001\u0000\u0000\u0000\u040c"+
		"\u0416\u0001\u0000\u0000\u0000\u040d\u040f\u0003\u008cF\u0000\u040e\u0410"+
		"\u0005Z\u0000\u0000\u040f\u040e\u0001\u0000\u0000\u0000\u040f\u0410\u0001"+
		"\u0000\u0000\u0000\u0410\u0416\u0001\u0000\u0000\u0000\u0411\u0413\u0003"+
		"\u00a2Q\u0000\u0412\u0414\u0005Z\u0000\u0000\u0413\u0412\u0001\u0000\u0000"+
		"\u0000\u0413\u0414\u0001\u0000\u0000\u0000\u0414\u0416\u0001\u0000\u0000"+
		"\u0000\u0415\u03fd\u0001\u0000\u0000\u0000\u0415\u0401\u0001\u0000\u0000"+
		"\u0000\u0415\u0405\u0001\u0000\u0000\u0000\u0415\u0409\u0001\u0000\u0000"+
		"\u0000\u0415\u040d\u0001\u0000\u0000\u0000\u0415\u0411\u0001\u0000\u0000"+
		"\u0000\u0416\u0093\u0001\u0000\u0000\u0000\u0417\u041a\u0003\u0096K\u0000"+
		"\u0418\u041a\u0003\u0098L\u0000\u0419\u0417\u0001\u0000\u0000\u0000\u0419"+
		"\u0418\u0001\u0000\u0000\u0000\u041a\u0095\u0001\u0000\u0000\u0000\u041b"+
		"\u041c\u0003\u00b2Y\u0000\u041c\u041d\u0005T\u0000\u0000\u041d\u041f\u0005"+
		"U\u0000\u0000\u041e\u0420\u0003\u009aM\u0000\u041f\u041e\u0001\u0000\u0000"+
		"\u0000\u041f\u0420\u0001\u0000\u0000\u0000\u0420\u0097\u0001\u0000\u0000"+
		"\u0000\u0421\u0422\u0003X,\u0000\u0422\u0099\u0001\u0000\u0000\u0000\u0423"+
		"\u0424\u0005\r\u0000\u0000\u0424\u0425\u0003\u0088D\u0000\u0425\u009b"+
		"\u0001\u0000\u0000\u0000\u0426\u0428\u0003\u0080@\u0000\u0427\u0426\u0001"+
		"\u0000\u0000\u0000\u0428\u042b\u0001\u0000\u0000\u0000\u0429\u0427\u0001"+
		"\u0000\u0000\u0000\u0429\u042a\u0001\u0000\u0000\u0000\u042a\u042d\u0001"+
		"\u0000\u0000\u0000\u042b\u0429\u0001\u0000\u0000\u0000\u042c\u042e\u0005"+
		"%\u0000\u0000\u042d\u042c\u0001\u0000\u0000\u0000\u042d\u042e\u0001\u0000"+
		"\u0000\u0000\u042e\u042f\u0001\u0000\u0000\u0000\u042f\u0430\u0005!\u0000"+
		"\u0000\u0430\u0431\u0003v;\u0000\u0431\u0435\u0005V\u0000\u0000\u0432"+
		"\u0434\u0003\u009eO\u0000\u0433\u0432\u0001\u0000\u0000\u0000\u0434\u0437"+
		"\u0001\u0000\u0000\u0000\u0435\u0433\u0001\u0000\u0000\u0000\u0435\u0436"+
		"\u0001\u0000\u0000\u0000\u0436\u0438\u0001\u0000\u0000\u0000\u0437\u0435"+
		"\u0001\u0000\u0000\u0000\u0438\u0439\u0005W\u0000\u0000\u0439\u009d\u0001"+
		"\u0000\u0000\u0000\u043a\u043e\u0005.\u0000\u0000\u043b\u043d\u0003\u00a0"+
		"P\u0000\u043c\u043b\u0001\u0000\u0000\u0000\u043d\u0440\u0001\u0000\u0000"+
		"\u0000\u043e\u043c\u0001\u0000\u0000\u0000\u043e\u043f\u0001\u0000\u0000"+
		"\u0000\u043f\u0441\u0001\u0000\u0000\u0000\u0440\u043e\u0001\u0000\u0000"+
		"\u0000\u0441\u0442\u0003v;\u0000\u0442\u0443\u0005Z\u0000\u0000\u0443"+
		"\u0474\u0001\u0000\u0000\u0000\u0444\u0445\u0005\u0012\u0000\u0000\u0445"+
		"\u044f\u0003v;\u0000\u0446\u0447\u0005:\u0000\u0000\u0447\u044c\u0003"+
		"v;\u0000\u0448\u0449\u0005[\u0000\u0000\u0449\u044b\u0003v;\u0000\u044a"+
		"\u0448\u0001\u0000\u0000\u0000\u044b\u044e\u0001\u0000\u0000\u0000\u044c"+
		"\u044a\u0001\u0000\u0000\u0000\u044c\u044d\u0001\u0000\u0000\u0000\u044d"+
		"\u0450\u0001\u0000\u0000\u0000\u044e\u044c\u0001\u0000\u0000\u0000\u044f"+
		"\u0446\u0001\u0000\u0000\u0000\u044f\u0450\u0001\u0000\u0000\u0000\u0450"+
		"\u0451\u0001\u0000\u0000\u0000\u0451\u0452\u0005Z\u0000\u0000\u0452\u0474"+
		"\u0001\u0000\u0000\u0000\u0453\u0454\u0005&\u0000\u0000\u0454\u045e\u0003"+
		"v;\u0000\u0455\u0456\u0005:\u0000\u0000\u0456\u045b\u0003v;\u0000\u0457"+
		"\u0458\u0005[\u0000\u0000\u0458\u045a\u0003v;\u0000\u0459\u0457\u0001"+
		"\u0000\u0000\u0000\u045a\u045d\u0001\u0000\u0000\u0000\u045b\u0459\u0001"+
		"\u0000\u0000\u0000\u045b\u045c\u0001\u0000\u0000\u0000\u045c\u045f\u0001"+
		"\u0000\u0000\u0000\u045d\u045b\u0001\u0000\u0000\u0000\u045e\u0455\u0001"+
		"\u0000\u0000\u0000\u045e\u045f\u0001\u0000\u0000\u0000\u045f\u0460\u0001"+
		"\u0000\u0000\u0000\u0460\u0461\u0005Z\u0000\u0000\u0461\u0474\u0001\u0000"+
		"\u0000\u0000\u0462\u0463\u0005?\u0000\u0000\u0463\u0464\u0003v;\u0000"+
		"\u0464\u0465\u0005Z\u0000\u0000\u0465\u0474\u0001\u0000\u0000\u0000\u0466"+
		"\u0467\u0005+\u0000\u0000\u0467\u0468\u0003v;\u0000\u0468\u0469\u0005"+
		"E\u0000\u0000\u0469\u046e\u0003v;\u0000\u046a\u046b\u0005[\u0000\u0000"+
		"\u046b\u046d\u0003v;\u0000\u046c\u046a\u0001\u0000\u0000\u0000\u046d\u0470"+
		"\u0001\u0000\u0000\u0000\u046e\u046c\u0001\u0000\u0000\u0000\u046e\u046f"+
		"\u0001\u0000\u0000\u0000\u046f\u0471\u0001\u0000\u0000\u0000\u0470\u046e"+
		"\u0001\u0000\u0000\u0000\u0471\u0472\u0005Z\u0000\u0000\u0472\u0474\u0001"+
		"\u0000\u0000\u0000\u0473\u043a\u0001\u0000\u0000\u0000\u0473\u0444\u0001"+
		"\u0000\u0000\u0000\u0473\u0453\u0001\u0000\u0000\u0000\u0473\u0462\u0001"+
		"\u0000\u0000\u0000\u0473\u0466\u0001\u0000\u0000\u0000\u0474\u009f\u0001"+
		"\u0000\u0000\u0000\u0475\u0476\u0007\u0003\u0000\u0000\u0476\u00a1\u0001"+
		"\u0000\u0000\u0000\u0477\u0478\u0005-\u0000\u0000\u0478\u047a\u0003\u00b2"+
		"Y\u0000\u0479\u047b\u0003 \u0010\u0000\u047a\u0479\u0001\u0000\u0000\u0000"+
		"\u047a\u047b\u0001\u0000\u0000\u0000\u047b\u047c\u0001\u0000\u0000\u0000"+
		"\u047c\u047f\u0003\u00a4R\u0000\u047d\u047e\u0005\u001a\u0000\u0000\u047e"+
		"\u0480\u0003\u0102\u0081\u0000\u047f\u047d\u0001\u0000\u0000\u0000\u047f"+
		"\u0480\u0001\u0000\u0000\u0000\u0480\u0481\u0001\u0000\u0000\u0000\u0481"+
		"\u0482\u0003\u00aaU\u0000\u0482\u00a3\u0001\u0000\u0000\u0000\u0483\u0485"+
		"\u0005T\u0000\u0000\u0484\u0486\u0003\u00a6S\u0000\u0485\u0484\u0001\u0000"+
		"\u0000\u0000\u0485\u0486\u0001\u0000\u0000\u0000\u0486\u0487\u0001\u0000"+
		"\u0000\u0000\u0487\u0488\u0005U\u0000\u0000\u0488\u00a5\u0001\u0000\u0000"+
		"\u0000\u0489\u048e\u0003\u00a8T\u0000\u048a\u048b\u0005[\u0000\u0000\u048b"+
		"\u048d\u0003\u00a8T\u0000\u048c\u048a\u0001\u0000\u0000\u0000\u048d\u0490"+
		"\u0001\u0000\u0000\u0000\u048e\u048c\u0001\u0000\u0000\u0000\u048e\u048f"+
		"\u0001\u0000\u0000\u0000\u048f\u0491\u0001\u0000\u0000\u0000\u0490\u048e"+
		"\u0001\u0000\u0000\u0000\u0491\u0492\u0004S\u0001\u0000\u0492\u00a7\u0001"+
		"\u0000\u0000\u0000\u0493\u0495\u0003\u0080@\u0000\u0494\u0493\u0001\u0000"+
		"\u0000\u0000\u0495\u0498\u0001\u0000\u0000\u0000\u0496\u0494\u0001\u0000"+
		"\u0000\u0000\u0496\u0497\u0001\u0000\u0000\u0000\u0497\u0499\u0001\u0000"+
		"\u0000\u0000\u0498\u0496\u0001\u0000\u0000\u0000\u0499\u04a1\u0003\u0104"+
		"\u0082\u0000\u049a\u049c\u0003\u0080@\u0000\u049b\u049a\u0001\u0000\u0000"+
		"\u0000\u049c\u049f\u0001\u0000\u0000\u0000\u049d\u049b\u0001\u0000\u0000"+
		"\u0000\u049d\u049e\u0001\u0000\u0000\u0000\u049e\u04a0\u0001\u0000\u0000"+
		"\u0000\u049f\u049d\u0001\u0000\u0000\u0000\u04a0\u04a2\u0005\u0082\u0000"+
		"\u0000\u04a1\u049d\u0001\u0000\u0000\u0000\u04a1\u04a2\u0001\u0000\u0000"+
		"\u0000\u04a2\u04a3\u0001\u0000\u0000\u0000\u04a3\u04a4\u0003\u00b2Y\u0000"+
		"\u04a4\u00a9\u0001\u0000\u0000\u0000\u04a5\u04aa\u0005V\u0000\u0000\u04a6"+
		"\u04a9\u00034\u001a\u0000\u04a7\u04a9\u0003D\"\u0000\u04a8\u04a6\u0001"+
		"\u0000\u0000\u0000\u04a8\u04a7\u0001\u0000\u0000\u0000\u04a9\u04ac\u0001"+
		"\u0000\u0000\u0000\u04aa\u04a8\u0001\u0000\u0000\u0000\u04aa\u04ab\u0001"+
		"\u0000\u0000\u0000\u04ab\u04ad\u0001\u0000\u0000\u0000\u04ac\u04aa\u0001"+
		"\u0000\u0000\u0000\u04ad\u04ae\u0005W\u0000\u0000\u04ae\u00ab\u0001\u0000"+
		"\u0000\u0000\u04af\u04b3\u0005V\u0000\u0000\u04b0\u04b2\u0003\u00aeW\u0000"+
		"\u04b1\u04b0\u0001\u0000\u0000\u0000\u04b2\u04b5\u0001\u0000\u0000\u0000"+
		"\u04b3\u04b1\u0001\u0000\u0000\u0000\u04b3\u04b4\u0001\u0000\u0000\u0000"+
		"\u04b4\u04b6\u0001\u0000\u0000\u0000\u04b5\u04b3\u0001\u0000\u0000\u0000"+
		"\u04b6\u04b7\u0005W\u0000\u0000\u04b7\u00ad\u0001\u0000\u0000\u0000\u04b8"+
		"\u04b9\u0003\u00b0X\u0000\u04b9\u04ba\u0005Z\u0000\u0000\u04ba\u04be\u0001"+
		"\u0000\u0000\u0000\u04bb\u04be\u0003\u00b6[\u0000\u04bc\u04be\u0003\u00b8"+
		"\\\u0000\u04bd\u04b8\u0001\u0000\u0000\u0000\u04bd\u04bb\u0001\u0000\u0000"+
		"\u0000\u04bd\u04bc\u0001\u0000\u0000\u0000\u04be\u00af\u0001\u0000\u0000"+
		"\u0000\u04bf\u04c1\u0003\u001c\u000e\u0000\u04c0\u04bf\u0001\u0000\u0000"+
		"\u0000\u04c1\u04c4\u0001\u0000\u0000\u0000\u04c2\u04c0\u0001\u0000\u0000"+
		"\u0000\u04c2\u04c3\u0001\u0000\u0000\u0000\u04c3\u04cd\u0001\u0000\u0000"+
		"\u0000\u04c4\u04c2\u0001\u0000\u0000\u0000\u04c5\u04c6\u0005@\u0000\u0000"+
		"\u04c6\u04c7\u0003\u00b2Y\u0000\u04c7\u04c8\u0005]\u0000\u0000\u04c8\u04c9"+
		"\u0003\u00d4j\u0000\u04c9\u04ce\u0001\u0000\u0000\u0000\u04ca\u04cb\u0003"+
		"\u0104\u0082\u0000\u04cb\u04cc\u0003X,\u0000\u04cc\u04ce\u0001\u0000\u0000"+
		"\u0000\u04cd\u04c5\u0001\u0000\u0000\u0000\u04cd\u04ca\u0001\u0000\u0000"+
		"\u0000\u04ce\u00b1\u0001\u0000\u0000\u0000\u04cf\u04d0\u0007\u0004\u0000"+
		"\u0000\u04d0\u00b3\u0001\u0000\u0000\u0000\u04d1\u04d2\u0007\u0005\u0000"+
		"\u0000\u04d2\u00b5\u0001\u0000\u0000\u0000\u04d3\u04d5\u0003\u001a\r\u0000"+
		"\u04d4\u04d3\u0001\u0000\u0000\u0000\u04d5\u04d8\u0001\u0000\u0000\u0000"+
		"\u04d6\u04d4\u0001\u0000\u0000\u0000\u04d6\u04d7\u0001\u0000\u0000\u0000"+
		"\u04d7\u04dd\u0001\u0000\u0000\u0000\u04d8\u04d6\u0001\u0000\u0000\u0000"+
		"\u04d9\u04de\u0003\u001e\u000f\u0000\u04da\u04de\u0003.\u0017\u0000\u04db"+
		"\u04de\u0003\u00a2Q\u0000\u04dc\u04de\u0003&\u0013\u0000\u04dd\u04d9\u0001"+
		"\u0000\u0000\u0000\u04dd\u04da\u0001\u0000\u0000\u0000\u04dd\u04db\u0001"+
		"\u0000\u0000\u0000\u04dd\u04dc\u0001\u0000\u0000\u0000\u04de\u00b7\u0001"+
		"\u0000\u0000\u0000\u04df\u055b\u0003\u00acV\u0000\u04e0\u04e1\u0005\u0003"+
		"\u0000\u0000\u04e1\u04e4\u0003\u00d4j\u0000\u04e2\u04e3\u0005c\u0000\u0000"+
		"\u04e3\u04e5\u0003\u00d4j\u0000\u04e4\u04e2\u0001\u0000\u0000\u0000\u04e4"+
		"\u04e5\u0001\u0000\u0000\u0000\u04e5\u04e6\u0001\u0000\u0000\u0000\u04e6"+
		"\u04e7\u0005Z\u0000\u0000\u04e7\u055b\u0001\u0000\u0000\u0000\u04e8\u04e9"+
		"\u0005\u0019\u0000\u0000\u04e9\u04ea\u0005T\u0000\u0000\u04ea\u04eb\u0003"+
		"\u00d4j\u0000\u04eb\u04ec\u0005U\u0000\u0000\u04ec\u04ef\u0003\u00b8\\"+
		"\u0000\u04ed\u04ee\u0005\u0010\u0000\u0000\u04ee\u04f0\u0003\u00b8\\\u0000"+
		"\u04ef\u04ed\u0001\u0000\u0000\u0000\u04ef\u04f0\u0001\u0000\u0000\u0000"+
		"\u04f0\u055b\u0001\u0000\u0000\u0000\u04f1\u04f2\u0005\u0017\u0000\u0000"+
		"\u04f2\u04f3\u0005T\u0000\u0000\u04f3\u04f4\u0003\u00cae\u0000\u04f4\u04f5"+
		"\u0005U\u0000\u0000\u04f5\u04f6\u0003\u00b8\\\u0000\u04f6\u055b\u0001"+
		"\u0000\u0000\u0000\u04f7\u04f8\u0005D\u0000\u0000\u04f8\u04f9\u0005T\u0000"+
		"\u0000\u04f9\u04fa\u0003\u00d4j\u0000\u04fa\u04fb\u0005U\u0000\u0000\u04fb"+
		"\u04fc\u0003\u00b8\\\u0000\u04fc\u055b\u0001\u0000\u0000\u0000\u04fd\u04fe"+
		"\u0005\u000e\u0000\u0000\u04fe\u04ff\u0003\u00b8\\\u0000\u04ff\u0500\u0005"+
		"D\u0000\u0000\u0500\u0501\u0005T\u0000\u0000\u0501\u0502\u0003\u00d4j"+
		"\u0000\u0502\u0503\u0005U\u0000\u0000\u0503\u0504\u0005Z\u0000\u0000\u0504"+
		"\u055b\u0001\u0000\u0000\u0000\u0505\u0506\u0005=\u0000\u0000\u0506\u0510"+
		"\u0003\u00acV\u0000\u0507\u0509\u0003\u00ba]\u0000\u0508\u0507\u0001\u0000"+
		"\u0000\u0000\u0509\u050a\u0001\u0000\u0000\u0000\u050a\u0508\u0001\u0000"+
		"\u0000\u0000\u050a\u050b\u0001\u0000\u0000\u0000\u050b\u050d\u0001\u0000"+
		"\u0000\u0000\u050c\u050e\u0003\u00be_\u0000\u050d\u050c\u0001\u0000\u0000"+
		"\u0000\u050d\u050e\u0001\u0000\u0000\u0000\u050e\u0511\u0001\u0000\u0000"+
		"\u0000\u050f\u0511\u0003\u00be_\u0000\u0510\u0508\u0001\u0000\u0000\u0000"+
		"\u0510\u050f\u0001\u0000\u0000\u0000\u0511\u055b\u0001\u0000\u0000\u0000"+
		"\u0512\u0513\u0005=\u0000\u0000\u0513\u0514\u0003\u00c0`\u0000\u0514\u0518"+
		"\u0003\u00acV\u0000\u0515\u0517\u0003\u00ba]\u0000\u0516\u0515\u0001\u0000"+
		"\u0000\u0000\u0517\u051a\u0001\u0000\u0000\u0000\u0518\u0516\u0001\u0000"+
		"\u0000\u0000\u0518\u0519\u0001\u0000\u0000\u0000\u0519\u051c\u0001\u0000"+
		"\u0000\u0000\u051a\u0518\u0001\u0000\u0000\u0000\u051b\u051d\u0003\u00be"+
		"_\u0000\u051c\u051b\u0001\u0000\u0000\u0000\u051c\u051d\u0001\u0000\u0000"+
		"\u0000\u051d\u055b\u0001\u0000\u0000\u0000\u051e\u051f\u00055\u0000\u0000"+
		"\u051f\u0520\u0005T\u0000\u0000\u0520\u0521\u0003\u00d4j\u0000\u0521\u0522"+
		"\u0005U\u0000\u0000\u0522\u0526\u0005V\u0000\u0000\u0523\u0525\u0003\u00c6"+
		"c\u0000\u0524\u0523\u0001\u0000\u0000\u0000\u0525\u0528\u0001\u0000\u0000"+
		"\u0000\u0526\u0524\u0001\u0000\u0000\u0000\u0526\u0527\u0001\u0000\u0000"+
		"\u0000\u0527\u052c\u0001\u0000\u0000\u0000\u0528\u0526\u0001\u0000\u0000"+
		"\u0000\u0529\u052b\u0003\u00c8d\u0000\u052a\u0529\u0001\u0000\u0000\u0000"+
		"\u052b\u052e\u0001\u0000\u0000\u0000\u052c\u052a\u0001\u0000\u0000\u0000"+
		"\u052c\u052d\u0001\u0000\u0000\u0000\u052d\u052f\u0001\u0000\u0000\u0000"+
		"\u052e\u052c\u0001\u0000\u0000\u0000\u052f\u0530\u0005W\u0000\u0000\u0530"+
		"\u055b\u0001\u0000\u0000\u0000\u0531\u0532\u00056\u0000\u0000\u0532\u0533"+
		"\u0005T\u0000\u0000\u0533\u0534\u0003\u00d4j\u0000\u0534\u0535\u0005U"+
		"\u0000\u0000\u0535\u0536\u0003\u00acV\u0000\u0536\u055b\u0001\u0000\u0000"+
		"\u0000\u0537\u0539\u0005/\u0000\u0000\u0538\u053a\u0003\u00d4j\u0000\u0539"+
		"\u0538\u0001\u0000\u0000\u0000\u0539\u053a\u0001\u0000\u0000\u0000\u053a"+
		"\u053b\u0001\u0000\u0000\u0000\u053b\u055b\u0005Z\u0000\u0000\u053c\u053d"+
		"\u00058\u0000\u0000\u053d\u053e\u0003\u00d4j\u0000\u053e\u053f\u0005Z"+
		"\u0000\u0000\u053f\u055b\u0001\u0000\u0000\u0000\u0540\u0542\u0005\u0005"+
		"\u0000\u0000\u0541\u0543\u0003\u00b2Y\u0000\u0542\u0541\u0001\u0000\u0000"+
		"\u0000\u0542\u0543\u0001\u0000\u0000\u0000\u0543\u0544\u0001\u0000\u0000"+
		"\u0000\u0544\u055b\u0005Z\u0000\u0000\u0545\u0547\u0005\f\u0000\u0000"+
		"\u0546\u0548\u0003\u00b2Y\u0000\u0547\u0546\u0001\u0000\u0000\u0000\u0547"+
		"\u0548\u0001\u0000\u0000\u0000\u0548\u0549\u0001\u0000\u0000\u0000\u0549"+
		"\u055b\u0005Z\u0000\u0000\u054a\u054b\u0005F\u0000\u0000\u054b\u054c\u0003"+
		"\u00d4j\u0000\u054c\u054d\u0005Z\u0000\u0000\u054d\u055b\u0001\u0000\u0000"+
		"\u0000\u054e\u055b\u0005Z\u0000\u0000\u054f\u0550\u0003\u00d4j\u0000\u0550"+
		"\u0551\u0005Z\u0000\u0000\u0551\u055b\u0001\u0000\u0000\u0000\u0552\u0554"+
		"\u0003\u00e4r\u0000\u0553\u0555\u0005Z\u0000\u0000\u0554\u0553\u0001\u0000"+
		"\u0000\u0000\u0554\u0555\u0001\u0000\u0000\u0000\u0555\u055b\u0001\u0000"+
		"\u0000\u0000\u0556\u0557\u0003\u00b2Y\u0000\u0557\u0558\u0005c\u0000\u0000"+
		"\u0558\u0559\u0003\u00b8\\\u0000\u0559\u055b\u0001\u0000\u0000\u0000\u055a"+
		"\u04df\u0001\u0000\u0000\u0000\u055a\u04e0\u0001\u0000\u0000\u0000\u055a"+
		"\u04e8\u0001\u0000\u0000\u0000\u055a\u04f1\u0001\u0000\u0000\u0000\u055a"+
		"\u04f7\u0001\u0000\u0000\u0000\u055a\u04fd\u0001\u0000\u0000\u0000\u055a"+
		"\u0505\u0001\u0000\u0000\u0000\u055a\u0512\u0001\u0000\u0000\u0000\u055a"+
		"\u051e\u0001\u0000\u0000\u0000\u055a\u0531\u0001\u0000\u0000\u0000\u055a"+
		"\u0537\u0001\u0000\u0000\u0000\u055a\u053c\u0001\u0000\u0000\u0000\u055a"+
		"\u0540\u0001\u0000\u0000\u0000\u055a\u0545\u0001\u0000\u0000\u0000\u055a"+
		"\u054a\u0001\u0000\u0000\u0000\u055a\u054e\u0001\u0000\u0000\u0000\u055a"+
		"\u054f\u0001\u0000\u0000\u0000\u055a\u0552\u0001\u0000\u0000\u0000\u055a"+
		"\u0556\u0001\u0000\u0000\u0000\u055b\u00b9\u0001\u0000\u0000\u0000\u055c"+
		"\u055d\u0005\b\u0000\u0000\u055d\u0561\u0005T\u0000\u0000\u055e\u0560"+
		"\u0003\u001c\u000e\u0000\u055f\u055e\u0001\u0000\u0000\u0000\u0560\u0563"+
		"\u0001\u0000\u0000\u0000\u0561\u055f\u0001\u0000\u0000\u0000\u0561\u0562"+
		"\u0001\u0000\u0000\u0000\u0562\u0564\u0001\u0000\u0000\u0000\u0563\u0561"+
		"\u0001\u0000\u0000\u0000\u0564\u0565\u0003\u00bc^\u0000\u0565\u0566\u0003"+
		"\u00b2Y\u0000\u0566\u0567\u0005U\u0000\u0000\u0567\u0568\u0003\u00acV"+
		"\u0000\u0568\u00bb\u0001\u0000\u0000\u0000\u0569\u056e\u0003v;\u0000\u056a"+
		"\u056b\u0005q\u0000\u0000\u056b\u056d\u0003v;\u0000\u056c\u056a\u0001"+
		"\u0000\u0000\u0000\u056d\u0570\u0001\u0000\u0000\u0000\u056e\u056c\u0001"+
		"\u0000\u0000\u0000\u056e\u056f\u0001\u0000\u0000\u0000\u056f\u00bd\u0001"+
		"\u0000\u0000\u0000\u0570\u056e\u0001\u0000\u0000\u0000\u0571\u0572\u0005"+
		"\u0015\u0000\u0000\u0572\u0573\u0003\u00acV\u0000\u0573\u00bf\u0001\u0000"+
		"\u0000\u0000\u0574\u0575\u0005T\u0000\u0000\u0575\u0577\u0003\u00c2a\u0000"+
		"\u0576\u0578\u0005Z\u0000\u0000\u0577\u0576\u0001\u0000\u0000\u0000\u0577"+
		"\u0578\u0001\u0000\u0000\u0000\u0578\u0579\u0001\u0000\u0000\u0000\u0579"+
		"\u057a\u0005U\u0000\u0000\u057a\u00c1\u0001\u0000\u0000\u0000\u057b\u0580"+
		"\u0003\u00c4b\u0000\u057c\u057d\u0005Z\u0000\u0000\u057d\u057f\u0003\u00c4"+
		"b\u0000\u057e\u057c\u0001\u0000\u0000\u0000\u057f\u0582\u0001\u0000\u0000"+
		"\u0000\u0580\u057e\u0001\u0000\u0000\u0000\u0580\u0581\u0001\u0000\u0000"+
		"\u0000\u0581\u00c3\u0001\u0000\u0000\u0000\u0582\u0580\u0001\u0000\u0000"+
		"\u0000\u0583\u0585\u0003\u001c\u000e\u0000\u0584\u0583\u0001\u0000\u0000"+
		"\u0000\u0585\u0588\u0001\u0000\u0000\u0000\u0586\u0584\u0001\u0000\u0000"+
		"\u0000\u0586\u0587\u0001\u0000\u0000\u0000\u0587\u0591\u0001\u0000\u0000"+
		"\u0000\u0588\u0586\u0001\u0000\u0000\u0000\u0589\u058b\u0003\u00eew\u0000"+
		"\u058a\u058c\u0005b\u0000\u0000\u058b\u058a\u0001\u0000\u0000\u0000\u058b"+
		"\u058c\u0001\u0000\u0000\u0000\u058c\u058d\u0001\u0000\u0000\u0000\u058d"+
		"\u058e\u0003\\.\u0000\u058e\u0592\u0001\u0000\u0000\u0000\u058f\u0590"+
		"\u0005@\u0000\u0000\u0590\u0592\u0003\u00b2Y\u0000\u0591\u0589\u0001\u0000"+
		"\u0000\u0000\u0591\u058f\u0001\u0000\u0000\u0000\u0592\u0593\u0001\u0000"+
		"\u0000\u0000\u0593\u0594\u0005]\u0000\u0000\u0594\u0595\u0003\u00d4j\u0000"+
		"\u0595\u0598\u0001\u0000\u0000\u0000\u0596\u0598\u0003v;\u0000\u0597\u0586"+
		"\u0001\u0000\u0000\u0000\u0597\u0596\u0001\u0000\u0000\u0000\u0598\u00c5"+
		"\u0001\u0000\u0000\u0000\u0599\u059a\u0003\u00c8d\u0000\u059a\u059b\u0005"+
		"c\u0000\u0000\u059b\u059d\u0001\u0000\u0000\u0000\u059c\u0599\u0001\u0000"+
		"\u0000\u0000\u059d\u059e\u0001\u0000\u0000\u0000\u059e\u059c\u0001\u0000"+
		"\u0000\u0000\u059e\u059f\u0001\u0000\u0000\u0000\u059f\u05a1\u0001\u0000"+
		"\u0000\u0000\u05a0\u05a2\u0003\u00aeW\u0000\u05a1\u05a0\u0001\u0000\u0000"+
		"\u0000\u05a2\u05a3\u0001\u0000\u0000\u0000\u05a3\u05a1\u0001\u0000\u0000"+
		"\u0000\u05a3\u05a4\u0001\u0000\u0000\u0000\u05a4\u00c7\u0001\u0000\u0000"+
		"\u0000\u05a5\u05ab\u0005\u0007\u0000\u0000\u05a6\u05ac\u0003\u00d4j\u0000"+
		"\u05a7\u05ac\u0005\u0086\u0000\u0000\u05a8\u05a9\u0003\u0104\u0082\u0000"+
		"\u05a9\u05aa\u0003\u00b2Y\u0000\u05aa\u05ac\u0001\u0000\u0000\u0000\u05ab"+
		"\u05a6\u0001\u0000\u0000\u0000\u05ab\u05a7\u0001\u0000\u0000\u0000\u05ab"+
		"\u05a8\u0001\u0000\u0000\u0000\u05ac\u05af\u0001\u0000\u0000\u0000\u05ad"+
		"\u05af\u0005\r\u0000\u0000\u05ae\u05a5\u0001\u0000\u0000\u0000\u05ae\u05ad"+
		"\u0001\u0000\u0000\u0000\u05af\u00c9\u0001\u0000\u0000\u0000\u05b0\u05bd"+
		"\u0003\u00ceg\u0000\u05b1\u05b3\u0003\u00ccf\u0000\u05b2\u05b1\u0001\u0000"+
		"\u0000\u0000\u05b2\u05b3\u0001\u0000\u0000\u0000\u05b3\u05b4\u0001\u0000"+
		"\u0000\u0000\u05b4\u05b6\u0005Z\u0000\u0000\u05b5\u05b7\u0003\u00d4j\u0000"+
		"\u05b6\u05b5\u0001\u0000\u0000\u0000\u05b6\u05b7\u0001\u0000\u0000\u0000"+
		"\u05b7\u05b8\u0001\u0000\u0000\u0000\u05b8\u05ba\u0005Z\u0000\u0000\u05b9"+
		"\u05bb\u0003\u00d0h\u0000\u05ba\u05b9\u0001\u0000\u0000\u0000\u05ba\u05bb"+
		"\u0001\u0000\u0000\u0000\u05bb\u05bd\u0001\u0000\u0000\u0000\u05bc\u05b0"+
		"\u0001\u0000\u0000\u0000\u05bc\u05b2\u0001\u0000\u0000\u0000\u05bd\u00cb"+
		"\u0001\u0000\u0000\u0000\u05be\u05c1\u0003\u00b0X\u0000\u05bf\u05c1\u0003"+
		"\u00d0h\u0000\u05c0\u05be\u0001\u0000\u0000\u0000\u05c0\u05bf\u0001\u0000"+
		"\u0000\u0000\u05c1\u00cd\u0001\u0000\u0000\u0000\u05c2\u05c4\u0003\u001c"+
		"\u000e\u0000\u05c3\u05c2\u0001\u0000\u0000\u0000\u05c4\u05c7\u0001\u0000"+
		"\u0000\u0000\u05c5\u05c3\u0001\u0000\u0000\u0000\u05c5\u05c6\u0001\u0000"+
		"\u0000\u0000\u05c6\u05ca\u0001\u0000\u0000\u0000\u05c7\u05c5\u0001\u0000"+
		"\u0000\u0000\u05c8\u05cb\u0003\u0104\u0082\u0000\u05c9\u05cb\u0005@\u0000"+
		"\u0000\u05ca\u05c8\u0001\u0000\u0000\u0000\u05ca\u05c9\u0001\u0000\u0000"+
		"\u0000\u05cb\u05cc\u0001\u0000\u0000\u0000\u05cc\u05cd\u0003\\.\u0000"+
		"\u05cd\u05ce\u0005c\u0000\u0000\u05ce\u05cf\u0003\u00d4j\u0000\u05cf\u00cf"+
		"\u0001\u0000\u0000\u0000\u05d0\u05d5\u0003\u00d4j\u0000\u05d1\u05d2\u0005"+
		"[\u0000\u0000\u05d2\u05d4\u0003\u00d4j\u0000\u05d3\u05d1\u0001\u0000\u0000"+
		"\u0000\u05d4\u05d7\u0001\u0000\u0000\u0000\u05d5\u05d3\u0001\u0000\u0000"+
		"\u0000\u05d5\u05d6\u0001\u0000\u0000\u0000\u05d6\u00d1\u0001\u0000\u0000"+
		"\u0000\u05d7\u05d5\u0001\u0000\u0000\u0000\u05d8\u05dc\u0003\u00b2Y\u0000"+
		"\u05d9\u05dc\u00057\u0000\u0000\u05da\u05dc\u00054\u0000\u0000\u05db\u05d8"+
		"\u0001\u0000\u0000\u0000\u05db\u05d9\u0001\u0000\u0000\u0000\u05db\u05da"+
		"\u0001\u0000\u0000\u0000\u05dc\u05dd\u0001\u0000\u0000\u0000\u05dd\u05de"+
		"\u0003\u010e\u0087\u0000\u05de\u00d3\u0001\u0000\u0000\u0000\u05df\u05e0"+
		"\u0006j\uffff\uffff\u0000\u05e0\u060b\u0003\u00e2q\u0000\u05e1\u060b\u0003"+
		"\u00d2i\u0000\u05e2\u05e3\u0003\u0104\u0082\u0000\u05e3\u05e9\u0005\u0080"+
		"\u0000\u0000\u05e4\u05e6\u0003\u0108\u0084\u0000\u05e5\u05e4\u0001\u0000"+
		"\u0000\u0000\u05e5\u05e6\u0001\u0000\u0000\u0000\u05e6\u05e7\u0001\u0000"+
		"\u0000\u0000\u05e7\u05ea\u0003\u00b2Y\u0000\u05e8\u05ea\u0005#\u0000\u0000"+
		"\u05e9\u05e5\u0001\u0000\u0000\u0000\u05e9\u05e8\u0001\u0000\u0000\u0000"+
		"\u05ea\u060b\u0001\u0000\u0000\u0000\u05eb\u05ec\u0003b1\u0000\u05ec\u05ee"+
		"\u0005\u0080\u0000\u0000\u05ed\u05ef\u0003\u0108\u0084\u0000\u05ee\u05ed"+
		"\u0001\u0000\u0000\u0000\u05ee\u05ef\u0001\u0000\u0000\u0000\u05ef\u05f0"+
		"\u0001\u0000\u0000\u0000\u05f0\u05f1\u0005#\u0000\u0000\u05f1\u060b\u0001"+
		"\u0000\u0000\u0000\u05f2\u060b\u0003\u00e4r\u0000\u05f3\u05f4\u0007\u0006"+
		"\u0000\u0000\u05f4\u060b\u0003\u00d4j\u0012\u05f5\u05f9\u0005T\u0000\u0000"+
		"\u05f6\u05f8\u0003\u0080@\u0000\u05f7\u05f6\u0001\u0000\u0000\u0000\u05f8"+
		"\u05fb\u0001\u0000\u0000\u0000\u05f9\u05f7\u0001\u0000\u0000\u0000\u05f9"+
		"\u05fa\u0001\u0000\u0000\u0000\u05fa\u05fc\u0001\u0000\u0000\u0000\u05fb"+
		"\u05f9\u0001\u0000\u0000\u0000\u05fc\u0601\u0003\u0104\u0082\u0000\u05fd"+
		"\u05fe\u0005p\u0000\u0000\u05fe\u0600\u0003\u0104\u0082\u0000\u05ff\u05fd"+
		"\u0001\u0000\u0000\u0000\u0600\u0603\u0001\u0000\u0000\u0000\u0601\u05ff"+
		"\u0001\u0000\u0000\u0000\u0601\u0602\u0001\u0000\u0000\u0000\u0602\u0604"+
		"\u0001\u0000\u0000\u0000\u0603\u0601\u0001\u0000\u0000\u0000\u0604\u0605"+
		"\u0005U\u0000\u0000\u0605\u0606\u0003\u00d4j\u0011\u0606\u060b\u0001\u0000"+
		"\u0000\u0000\u0607\u0608\u0005#\u0000\u0000\u0608\u060b\u0003\u00f0x\u0000"+
		"\u0609\u060b\u0003\u00dcn\u0000\u060a\u05df\u0001\u0000\u0000\u0000\u060a"+
		"\u05e1\u0001\u0000\u0000\u0000\u060a\u05e2\u0001\u0000\u0000\u0000\u060a"+
		"\u05eb\u0001\u0000\u0000\u0000\u060a\u05f2\u0001\u0000\u0000\u0000\u060a"+
		"\u05f3\u0001\u0000\u0000\u0000\u060a\u05f5\u0001\u0000\u0000\u0000\u060a"+
		"\u0607\u0001\u0000\u0000\u0000\u060a\u0609\u0001\u0000\u0000\u0000\u060b"+
		"\u0662\u0001\u0000\u0000\u0000\u060c\u060d\n\u000f\u0000\u0000\u060d\u060e"+
		"\u0007\u0007\u0000\u0000\u060e\u0661\u0003\u00d4j\u0010\u060f\u0610\n"+
		"\u000e\u0000\u0000\u0610\u0611\u0007\b\u0000\u0000\u0611\u0661\u0003\u00d4"+
		"j\u000f\u0612\u061a\n\r\u0000\u0000\u0613\u0614\u0005_\u0000\u0000\u0614"+
		"\u061b\u0005_\u0000\u0000\u0615\u0616\u0005^\u0000\u0000\u0616\u0617\u0005"+
		"^\u0000\u0000\u0617\u061b\u0005^\u0000\u0000\u0618\u0619\u0005^\u0000"+
		"\u0000\u0619\u061b\u0005^\u0000\u0000\u061a\u0613\u0001\u0000\u0000\u0000"+
		"\u061a\u0615\u0001\u0000\u0000\u0000\u061a\u0618\u0001\u0000\u0000\u0000"+
		"\u061b\u061c\u0001\u0000\u0000\u0000\u061c\u0661\u0003\u00d4j\u000e\u061d"+
		"\u061e\n\f\u0000\u0000\u061e\u061f\u0007\t\u0000\u0000\u061f\u0661\u0003"+
		"\u00d4j\r\u0620\u0621\n\n\u0000\u0000\u0621\u0622\u0007\n\u0000\u0000"+
		"\u0622\u0661\u0003\u00d4j\u000b\u0623\u0624\n\t\u0000\u0000\u0624\u0625"+
		"\u0005p\u0000\u0000\u0625\u0661\u0003\u00d4j\n\u0626\u0627\n\b\u0000\u0000"+
		"\u0627\u0628\u0005r\u0000\u0000\u0628\u0661\u0003\u00d4j\t\u0629\u062a"+
		"\n\u0007\u0000\u0000\u062a\u062b\u0005q\u0000\u0000\u062b\u0661\u0003"+
		"\u00d4j\b\u062c\u062d\n\u0006\u0000\u0000\u062d\u062e\u0005h\u0000\u0000"+
		"\u062e\u0661\u0003\u00d4j\u0007\u062f\u0630\n\u0005\u0000\u0000\u0630"+
		"\u0631\u0005i\u0000\u0000\u0631\u0661\u0003\u00d4j\u0006\u0632\u0633\n"+
		"\u0004\u0000\u0000\u0633\u0634\u0005R\u0000\u0000\u0634\u0661\u0003\u00d4"+
		"j\u0004\u0635\u0636\n\u0003\u0000\u0000\u0636\u0637\u0005b\u0000\u0000"+
		"\u0637\u0638\u0003\u00d4j\u0000\u0638\u0639\u0005c\u0000\u0000\u0639\u063a"+
		"\u0003\u00d4j\u0003\u063a\u0661\u0001\u0000\u0000\u0000\u063b\u063c\n"+
		"\u0002\u0000\u0000\u063c\u063d\u0007\u000b\u0000\u0000\u063d\u0661\u0003"+
		"\u00d4j\u0002\u063e\u063f\n\u001a\u0000\u0000\u063f\u0640\u0005X\u0000"+
		"\u0000\u0640\u0641\u0003\u00d4j\u0000\u0641\u0642\u0005Y\u0000\u0000\u0642"+
		"\u0661\u0001\u0000\u0000\u0000\u0643\u0644\n\u0019\u0000\u0000\u0644\u0650"+
		"\u0007\f\u0000\u0000\u0645\u0651\u0003\u00b2Y\u0000\u0646\u0651\u0003"+
		"\u00d2i\u0000\u0647\u0651\u00057\u0000\u0000\u0648\u064a\u0005#\u0000"+
		"\u0000\u0649\u064b\u0003\u0100\u0080\u0000\u064a\u0649\u0001\u0000\u0000"+
		"\u0000\u064a\u064b\u0001\u0000\u0000\u0000\u064b\u064c\u0001\u0000\u0000"+
		"\u0000\u064c\u0651\u0003\u00f4z\u0000\u064d\u064e\u00054\u0000\u0000\u064e"+
		"\u0651\u0003\u010a\u0085\u0000\u064f\u0651\u0003\u00fa}\u0000\u0650\u0645"+
		"\u0001\u0000\u0000\u0000\u0650\u0646\u0001\u0000\u0000\u0000\u0650\u0647"+
		"\u0001\u0000\u0000\u0000\u0650\u0648\u0001\u0000\u0000\u0000\u0650\u064d"+
		"\u0001\u0000\u0000\u0000\u0650\u064f\u0001\u0000\u0000\u0000\u0651\u0661"+
		"\u0001\u0000\u0000\u0000\u0652\u0653\n\u0017\u0000\u0000\u0653\u0655\u0005"+
		"\u0080\u0000\u0000\u0654\u0656\u0003\u0108\u0084\u0000\u0655\u0654\u0001"+
		"\u0000\u0000\u0000\u0655\u0656\u0001\u0000\u0000\u0000\u0656\u0657\u0001"+
		"\u0000\u0000\u0000\u0657\u0661\u0003\u00b2Y\u0000\u0658\u0659\n\u0013"+
		"\u0000\u0000\u0659\u0661\u0007\r\u0000\u0000\u065a\u065b\n\u000b\u0000"+
		"\u0000\u065b\u065e\u0005\u001c\u0000\u0000\u065c\u065f\u0003\u0104\u0082"+
		"\u0000\u065d\u065f\u0003\u00d6k\u0000\u065e\u065c\u0001\u0000\u0000\u0000"+
		"\u065e\u065d\u0001\u0000\u0000\u0000\u065f\u0661\u0001\u0000\u0000\u0000"+
		"\u0660\u060c\u0001\u0000\u0000\u0000\u0660\u060f\u0001\u0000\u0000\u0000"+
		"\u0660\u0612\u0001\u0000\u0000\u0000\u0660\u061d\u0001\u0000\u0000\u0000"+
		"\u0660\u0620\u0001\u0000\u0000\u0000\u0660\u0623\u0001\u0000\u0000\u0000"+
		"\u0660\u0626\u0001\u0000\u0000\u0000\u0660\u0629\u0001\u0000\u0000\u0000"+
		"\u0660\u062c\u0001\u0000\u0000\u0000\u0660\u062f\u0001\u0000\u0000\u0000"+
		"\u0660\u0632\u0001\u0000\u0000\u0000\u0660\u0635\u0001\u0000\u0000\u0000"+
		"\u0660\u063b\u0001\u0000\u0000\u0000\u0660\u063e\u0001\u0000\u0000\u0000"+
		"\u0660\u0643\u0001\u0000\u0000\u0000\u0660\u0652\u0001\u0000\u0000\u0000"+
		"\u0660\u0658\u0001\u0000\u0000\u0000\u0660\u065a\u0001\u0000\u0000\u0000"+
		"\u0661\u0664\u0001\u0000\u0000\u0000\u0662\u0660\u0001\u0000\u0000\u0000"+
		"\u0662\u0663\u0001\u0000\u0000\u0000\u0663\u00d5\u0001\u0000\u0000\u0000"+
		"\u0664\u0662\u0001\u0000\u0000\u0000\u0665\u0667\u0003\u001c\u000e\u0000"+
		"\u0666\u0665\u0001\u0000\u0000\u0000\u0667\u066a\u0001\u0000\u0000\u0000"+
		"\u0668\u0666\u0001\u0000\u0000\u0000\u0668\u0669\u0001\u0000\u0000\u0000"+
		"\u0669\u066b\u0001\u0000\u0000\u0000\u066a\u0668\u0001\u0000\u0000\u0000"+
		"\u066b\u066f\u0003\u0104\u0082\u0000\u066c\u066e\u0003\u0080@\u0000\u066d"+
		"\u066c\u0001\u0000\u0000\u0000\u066e\u0671\u0001\u0000\u0000\u0000\u066f"+
		"\u066d\u0001\u0000\u0000\u0000\u066f\u0670\u0001\u0000\u0000\u0000\u0670"+
		"\u0672\u0001\u0000\u0000\u0000\u0671\u066f\u0001\u0000\u0000\u0000\u0672"+
		"\u0673\u0003X,\u0000\u0673\u067c\u0001\u0000\u0000\u0000\u0674\u0675\u0003"+
		"\u0104\u0082\u0000\u0675\u0677\u0005T\u0000\u0000\u0676\u0678\u0003\u00d8"+
		"l\u0000\u0677\u0676\u0001\u0000\u0000\u0000\u0677\u0678\u0001\u0000\u0000"+
		"\u0000\u0678\u0679\u0001\u0000\u0000\u0000\u0679\u067a\u0005U\u0000\u0000"+
		"\u067a\u067c\u0001\u0000\u0000\u0000\u067b\u0668\u0001\u0000\u0000\u0000"+
		"\u067b\u0674\u0001\u0000\u0000\u0000\u067c\u00d7\u0001\u0000\u0000\u0000"+
		"\u067d\u0682\u0003\u00dam\u0000\u067e\u067f\u0005[\u0000\u0000\u067f\u0681"+
		"\u0003\u00dam\u0000\u0680\u067e\u0001\u0000\u0000\u0000\u0681\u0684\u0001"+
		"\u0000\u0000\u0000\u0682\u0680\u0001\u0000\u0000\u0000\u0682\u0683\u0001"+
		"\u0000\u0000\u0000\u0683\u00d9\u0001\u0000\u0000\u0000\u0684\u0682\u0001"+
		"\u0000\u0000\u0000\u0685\u0686\u0003\u00d6k\u0000\u0686\u00db\u0001\u0000"+
		"\u0000\u0000\u0687\u0688\u0003\u00deo\u0000\u0688\u0689\u0005\u007f\u0000"+
		"\u0000\u0689\u068a\u0003\u00e0p\u0000\u068a\u00dd\u0001\u0000\u0000\u0000"+
		"\u068b\u06a2\u0003\u00b2Y\u0000\u068c\u068e\u0005T\u0000\u0000\u068d\u068f"+
		"\u0003n7\u0000\u068e\u068d\u0001\u0000\u0000\u0000\u068e\u068f\u0001\u0000"+
		"\u0000\u0000\u068f\u0690\u0001\u0000\u0000\u0000\u0690\u06a2\u0005U\u0000"+
		"\u0000\u0691\u0692\u0005T\u0000\u0000\u0692\u0697\u0003\u00b2Y\u0000\u0693"+
		"\u0694\u0005[\u0000\u0000\u0694\u0696\u0003\u00b2Y\u0000\u0695\u0693\u0001"+
		"\u0000\u0000\u0000\u0696\u0699\u0001\u0000\u0000\u0000\u0697\u0695\u0001"+
		"\u0000\u0000\u0000\u0697\u0698\u0001\u0000\u0000\u0000\u0698\u069a\u0001"+
		"\u0000\u0000\u0000\u0699\u0697\u0001\u0000\u0000\u0000\u069a\u069b\u0005"+
		"U\u0000\u0000\u069b\u06a2\u0001\u0000\u0000\u0000\u069c\u069e\u0005T\u0000"+
		"\u0000\u069d\u069f\u0003r9\u0000\u069e\u069d\u0001\u0000\u0000\u0000\u069e"+
		"\u069f\u0001\u0000\u0000\u0000\u069f\u06a0\u0001\u0000\u0000\u0000\u06a0"+
		"\u06a2\u0005U\u0000\u0000\u06a1\u068b\u0001\u0000\u0000\u0000\u06a1\u068c"+
		"\u0001\u0000\u0000\u0000\u06a1\u0691\u0001\u0000\u0000\u0000\u06a1\u069c"+
		"\u0001\u0000\u0000\u0000\u06a2\u00df\u0001\u0000\u0000\u0000\u06a3\u06a6"+
		"\u0003\u00d4j\u0000\u06a4\u06a6\u0003\u00acV\u0000\u06a5\u06a3\u0001\u0000"+
		"\u0000\u0000\u06a5\u06a4\u0001\u0000\u0000\u0000\u06a6\u00e1\u0001\u0000"+
		"\u0000\u0000\u06a7\u06a8\u0005T\u0000\u0000\u06a8\u06a9\u0003\u00d4j\u0000"+
		"\u06a9\u06aa\u0005U\u0000\u0000\u06aa\u06ba\u0001\u0000\u0000\u0000\u06ab"+
		"\u06ba\u00057\u0000\u0000\u06ac\u06ba\u00054\u0000\u0000\u06ad\u06ba\u0003"+
		"x<\u0000\u06ae\u06ba\u0003\u00b2Y\u0000\u06af\u06b0\u0003<\u001e\u0000"+
		"\u06b0\u06b1\u0005\\\u0000\u0000\u06b1\u06b2\u0005\n\u0000\u0000\u06b2"+
		"\u06ba\u0001\u0000\u0000\u0000\u06b3\u06b7\u0003\u0100\u0080\u0000\u06b4"+
		"\u06b8\u0003\u010c\u0086\u0000\u06b5\u06b6\u00057\u0000\u0000\u06b6\u06b8"+
		"\u0003\u010e\u0087\u0000\u06b7\u06b4\u0001\u0000\u0000\u0000\u06b7\u06b5"+
		"\u0001\u0000\u0000\u0000\u06b8\u06ba\u0001\u0000\u0000\u0000\u06b9\u06a7"+
		"\u0001\u0000\u0000\u0000\u06b9\u06ab\u0001\u0000\u0000\u0000\u06b9\u06ac"+
		"\u0001\u0000\u0000\u0000\u06b9\u06ad\u0001\u0000\u0000\u0000\u06b9\u06ae"+
		"\u0001\u0000\u0000\u0000\u06b9\u06af\u0001\u0000\u0000\u0000\u06b9\u06b3"+
		"\u0001\u0000\u0000\u0000\u06ba\u00e3\u0001\u0000\u0000\u0000\u06bb\u06bc"+
		"\u00055\u0000\u0000\u06bc\u06bd\u0005T\u0000\u0000\u06bd\u06be\u0003\u00d4"+
		"j\u0000\u06be\u06bf\u0005U\u0000\u0000\u06bf\u06c3\u0005V\u0000\u0000"+
		"\u06c0\u06c2\u0003\u00e6s\u0000\u06c1\u06c0\u0001\u0000\u0000\u0000\u06c2"+
		"\u06c5\u0001\u0000\u0000\u0000\u06c3\u06c1\u0001\u0000\u0000\u0000\u06c3"+
		"\u06c4\u0001\u0000\u0000\u0000\u06c4\u06c6\u0001\u0000\u0000\u0000\u06c5"+
		"\u06c3\u0001\u0000\u0000\u0000\u06c6\u06c7\u0005W\u0000\u0000\u06c7\u00e5"+
		"\u0001\u0000\u0000\u0000\u06c8\u06da\u0005\u0007\u0000\u0000\u06c9\u06db"+
		"\u0003\u00d0h\u0000\u06ca\u06cd\u0005Q\u0000\u0000\u06cb\u06cc\u0005["+
		"\u0000\u0000\u06cc\u06ce\u0005\r\u0000\u0000\u06cd\u06cb\u0001\u0000\u0000"+
		"\u0000\u06cd\u06ce\u0001\u0000\u0000\u0000\u06ce\u06db\u0001\u0000\u0000"+
		"\u0000\u06cf\u06d4\u0003\u00eau\u0000\u06d0\u06d1\u0005[\u0000\u0000\u06d1"+
		"\u06d3\u0003\u00eau\u0000\u06d2\u06d0\u0001\u0000\u0000\u0000\u06d3\u06d6"+
		"\u0001\u0000\u0000\u0000\u06d4\u06d2\u0001\u0000\u0000\u0000\u06d4\u06d5"+
		"\u0001\u0000\u0000\u0000\u06d5\u06d8\u0001\u0000\u0000\u0000\u06d6\u06d4"+
		"\u0001\u0000\u0000\u0000\u06d7\u06d9\u0003\u00e8t\u0000\u06d8\u06d7\u0001"+
		"\u0000\u0000\u0000\u06d8\u06d9\u0001\u0000\u0000\u0000\u06d9\u06db\u0001"+
		"\u0000\u0000\u0000\u06da\u06c9\u0001\u0000\u0000\u0000\u06da\u06ca\u0001"+
		"\u0000\u0000\u0000\u06da\u06cf\u0001\u0000\u0000\u0000\u06db\u06dc\u0001"+
		"\u0000\u0000\u0000\u06dc\u06dd\u0007\u000e\u0000\u0000\u06dd\u06e2\u0003"+
		"\u00ecv\u0000\u06de\u06df\u0005\r\u0000\u0000\u06df\u06e0\u0007\u000e"+
		"\u0000\u0000\u06e0\u06e2\u0003\u00ecv\u0000\u06e1\u06c8\u0001\u0000\u0000"+
		"\u0000\u06e1\u06de\u0001\u0000\u0000\u0000\u06e2\u00e7\u0001\u0000\u0000"+
		"\u0000\u06e3\u06e4\u0005C\u0000\u0000\u06e4\u06e5\u0003\u00d4j\u0000\u06e5"+
		"\u00e9\u0001\u0000\u0000\u0000\u06e6\u06e7\u0003\u00d6k\u0000\u06e7\u00eb"+
		"\u0001\u0000\u0000\u0000\u06e8\u06f0\u0003\u00acV\u0000\u06e9\u06eb\u0003"+
		"\u00aeW\u0000\u06ea\u06e9\u0001\u0000\u0000\u0000\u06eb\u06ee\u0001\u0000"+
		"\u0000\u0000\u06ec\u06ea\u0001\u0000\u0000\u0000\u06ec\u06ed\u0001\u0000"+
		"\u0000\u0000\u06ed\u06f0\u0001\u0000\u0000\u0000\u06ee\u06ec\u0001\u0000"+
		"\u0000\u0000\u06ef\u06e8\u0001\u0000\u0000\u0000\u06ef\u06ec\u0001\u0000"+
		"\u0000\u0000\u06f0\u00ed\u0001\u0000\u0000\u0000\u06f1\u06f2\u0003b1\u0000"+
		"\u06f2\u00ef\u0001\u0000\u0000\u0000\u06f3\u06f5\u0003\u0100\u0080\u0000"+
		"\u06f4\u06f3\u0001\u0000\u0000\u0000\u06f4\u06f5\u0001\u0000\u0000\u0000"+
		"\u06f5\u06f6\u0001\u0000\u0000\u0000\u06f6\u06f7\u0003\u00f2y\u0000\u06f7"+
		"\u06f8\u0003\u00f8|\u0000\u06f8\u06fd\u0001\u0000\u0000\u0000\u06f9\u06fa"+
		"\u0003\u00f2y\u0000\u06fa\u06fb\u0003\u00f6{\u0000\u06fb\u06fd\u0001\u0000"+
		"\u0000\u0000\u06fc\u06f4\u0001\u0000\u0000\u0000\u06fc\u06f9\u0001\u0000"+
		"\u0000\u0000\u06fd\u00f1\u0001\u0000\u0000\u0000\u06fe\u0700\u0003\u00b2"+
		"Y\u0000\u06ff\u0701\u0003\u00fc~\u0000\u0700\u06ff\u0001\u0000\u0000\u0000"+
		"\u0700\u0701\u0001\u0000\u0000\u0000\u0701\u0709\u0001\u0000\u0000\u0000"+
		"\u0702\u0703\u0005\\\u0000\u0000\u0703\u0705\u0003\u00b2Y\u0000\u0704"+
		"\u0706\u0003\u00fc~\u0000\u0705\u0704\u0001\u0000\u0000\u0000\u0705\u0706"+
		"\u0001\u0000\u0000\u0000\u0706\u0708\u0001\u0000\u0000\u0000\u0707\u0702"+
		"\u0001\u0000\u0000\u0000\u0708\u070b\u0001\u0000\u0000\u0000\u0709\u0707"+
		"\u0001\u0000\u0000\u0000\u0709\u070a\u0001\u0000\u0000\u0000\u070a\u070e"+
		"\u0001\u0000\u0000\u0000\u070b\u0709\u0001\u0000\u0000\u0000\u070c\u070e"+
		"\u0003\u0106\u0083\u0000\u070d\u06fe\u0001\u0000\u0000\u0000\u070d\u070c"+
		"\u0001\u0000\u0000\u0000\u070e\u00f3\u0001\u0000\u0000\u0000\u070f\u0711"+
		"\u0003\u00b2Y\u0000\u0710\u0712\u0003\u00fe\u007f\u0000\u0711\u0710\u0001"+
		"\u0000\u0000\u0000\u0711\u0712\u0001\u0000\u0000\u0000\u0712\u0713\u0001"+
		"\u0000\u0000\u0000\u0713\u0714\u0003\u00f8|\u0000\u0714\u00f5\u0001\u0000"+
		"\u0000\u0000\u0715\u0716\u0005X\u0000\u0000\u0716\u0718\u0005Y\u0000\u0000"+
		"\u0717\u0715\u0001\u0000\u0000\u0000\u0718\u0719\u0001\u0000\u0000\u0000"+
		"\u0719\u0717\u0001\u0000\u0000\u0000\u0719\u071a\u0001\u0000\u0000\u0000"+
		"\u071a\u071b\u0001\u0000\u0000\u0000\u071b\u072c\u0003`0\u0000\u071c\u071d"+
		"\u0005X\u0000\u0000\u071d\u071e\u0003\u00d4j\u0000\u071e\u071f\u0005Y"+
		"\u0000\u0000\u071f\u0721\u0001\u0000\u0000\u0000\u0720\u071c\u0001\u0000"+
		"\u0000\u0000\u0721\u0722\u0001\u0000\u0000\u0000\u0722\u0720\u0001\u0000"+
		"\u0000\u0000\u0722\u0723\u0001\u0000\u0000\u0000\u0723\u0728\u0001\u0000"+
		"\u0000\u0000\u0724\u0725\u0005X\u0000\u0000\u0725\u0727\u0005Y\u0000\u0000"+
		"\u0726\u0724\u0001\u0000\u0000\u0000\u0727\u072a\u0001\u0000\u0000\u0000"+
		"\u0728\u0726\u0001\u0000\u0000\u0000\u0728\u0729\u0001\u0000\u0000\u0000"+
		"\u0729\u072c\u0001\u0000\u0000\u0000\u072a\u0728\u0001\u0000\u0000\u0000"+
		"\u072b\u0717\u0001\u0000\u0000\u0000\u072b\u0720\u0001\u0000\u0000\u0000"+
		"\u072c\u00f7\u0001\u0000\u0000\u0000\u072d\u072f\u0003\u010e\u0087\u0000"+
		"\u072e\u0730\u00030\u0018\u0000\u072f\u072e\u0001\u0000\u0000\u0000\u072f"+
		"\u0730\u0001\u0000\u0000\u0000\u0730\u00f9\u0001\u0000\u0000\u0000\u0731"+
		"\u0732\u0003\u0100\u0080\u0000\u0732\u0733\u0003\u010c\u0086\u0000\u0733"+
		"\u00fb\u0001\u0000\u0000\u0000\u0734\u0735\u0005_\u0000\u0000\u0735\u0738"+
		"\u0005^\u0000\u0000\u0736\u0738\u0003\u0108\u0084\u0000\u0737\u0734\u0001"+
		"\u0000\u0000\u0000\u0737\u0736\u0001\u0000\u0000\u0000\u0738\u00fd\u0001"+
		"\u0000\u0000\u0000\u0739\u073a\u0005_\u0000\u0000\u073a\u073d\u0005^\u0000"+
		"\u0000\u073b\u073d\u0003\u0100\u0080\u0000\u073c\u0739\u0001\u0000\u0000"+
		"\u0000\u073c\u073b\u0001\u0000\u0000\u0000\u073d\u00ff\u0001\u0000\u0000"+
		"\u0000\u073e\u073f\u0005_\u0000\u0000\u073f\u0740\u0003\u0102\u0081\u0000"+
		"\u0740\u0741\u0005^\u0000\u0000\u0741\u0101\u0001\u0000\u0000\u0000\u0742"+
		"\u0747\u0003\u0104\u0082\u0000\u0743\u0744\u0005[\u0000\u0000\u0744\u0746"+
		"\u0003\u0104\u0082\u0000\u0745\u0743\u0001\u0000\u0000\u0000\u0746\u0749"+
		"\u0001\u0000\u0000\u0000\u0747\u0745\u0001\u0000\u0000\u0000\u0747\u0748"+
		"\u0001\u0000\u0000\u0000\u0748\u0103\u0001\u0000\u0000\u0000\u0749\u0747"+
		"\u0001\u0000\u0000\u0000\u074a\u074c\u0003\u0080@\u0000\u074b\u074a\u0001"+
		"\u0000\u0000\u0000\u074c\u074f\u0001\u0000\u0000\u0000\u074d\u074b\u0001"+
		"\u0000\u0000\u0000\u074d\u074e\u0001\u0000\u0000\u0000\u074e\u0752\u0001"+
		"\u0000\u0000\u0000\u074f\u074d\u0001\u0000\u0000\u0000\u0750\u0753\u0003"+
		"\u00eew\u0000\u0751\u0753\u0003\u0106\u0083\u0000\u0752\u0750\u0001\u0000"+
		"\u0000\u0000\u0752\u0751\u0001\u0000\u0000\u0000\u0753\u075e\u0001\u0000"+
		"\u0000\u0000\u0754\u0756\u0003\u0080@\u0000\u0755\u0754\u0001\u0000\u0000"+
		"\u0000\u0756\u0759\u0001\u0000\u0000\u0000\u0757\u0755\u0001\u0000\u0000"+
		"\u0000\u0757\u0758\u0001\u0000\u0000\u0000\u0758\u075a\u0001\u0000\u0000"+
		"\u0000\u0759\u0757\u0001\u0000\u0000\u0000\u075a\u075b\u0005X\u0000\u0000"+
		"\u075b\u075d\u0005Y\u0000\u0000\u075c\u0757\u0001\u0000\u0000\u0000\u075d"+
		"\u0760\u0001\u0000\u0000\u0000\u075e\u075c\u0001\u0000\u0000\u0000\u075e"+
		"\u075f\u0001\u0000\u0000\u0000\u075f\u0762\u0001\u0000\u0000\u0000\u0760"+
		"\u075e\u0001\u0000\u0000\u0000\u0761\u0763\u0005b\u0000\u0000\u0762\u0761"+
		"\u0001\u0000\u0000\u0000\u0762\u0763\u0001\u0000\u0000\u0000\u0763\u0105"+
		"\u0001\u0000\u0000\u0000\u0764\u0765\u0007\u000f\u0000\u0000\u0765\u0107"+
		"\u0001\u0000\u0000\u0000\u0766\u0767\u0005_\u0000\u0000\u0767\u076c\u0003"+
		"f3\u0000\u0768\u0769\u0005[\u0000\u0000\u0769\u076b\u0003f3\u0000\u076a"+
		"\u0768\u0001\u0000\u0000\u0000\u076b\u076e\u0001\u0000\u0000\u0000\u076c"+
		"\u076a\u0001\u0000\u0000\u0000\u076c\u076d\u0001\u0000\u0000\u0000\u076d"+
		"\u076f\u0001\u0000\u0000\u0000\u076e\u076c\u0001\u0000\u0000\u0000\u076f"+
		"\u0770\u0005^\u0000\u0000\u0770\u0109\u0001\u0000\u0000\u0000\u0771\u077b"+
		"\u0003\u010e\u0087\u0000\u0772\u0774\u0005\\\u0000\u0000\u0773\u0775\u0003"+
		"\u0108\u0084\u0000\u0774\u0773\u0001\u0000\u0000\u0000\u0774\u0775\u0001"+
		"\u0000\u0000\u0000\u0775\u0776\u0001\u0000\u0000\u0000\u0776\u0778\u0003"+
		"\u00b2Y\u0000\u0777\u0779\u0003\u010e\u0087\u0000\u0778\u0777\u0001\u0000"+
		"\u0000\u0000\u0778\u0779\u0001\u0000\u0000\u0000\u0779\u077b\u0001\u0000"+
		"\u0000\u0000\u077a\u0771\u0001\u0000\u0000\u0000\u077a\u0772\u0001\u0000"+
		"\u0000\u0000\u077b\u010b\u0001\u0000\u0000\u0000\u077c\u077d\u00054\u0000"+
		"\u0000\u077d\u0782\u0003\u010a\u0085\u0000\u077e\u077f\u0003\u00b2Y\u0000"+
		"\u077f\u0780\u0003\u010e\u0087\u0000\u0780\u0782\u0001\u0000\u0000\u0000"+
		"\u0781\u077c\u0001\u0000\u0000\u0000\u0781\u077e\u0001\u0000\u0000\u0000"+
		"\u0782\u010d\u0001\u0000\u0000\u0000\u0783\u0785\u0005T\u0000\u0000\u0784"+
		"\u0786\u0003\u00d0h\u0000\u0785\u0784\u0001\u0000\u0000\u0000\u0785\u0786"+
		"\u0001\u0000\u0000\u0000\u0786\u0787\u0001\u0000\u0000\u0000\u0787\u0788"+
		"\u0005U\u0000\u0000\u0788\u010f\u0001\u0000\u0000\u0000\u00f0\u0111\u0115"+
		"\u0117\u011d\u0122\u0124\u012b\u0130\u0138\u0141\u0146\u014f\u0155\u0160"+
		"\u016a\u0172\u0179\u0184\u018c\u0193\u01a0\u01a5\u01aa\u01ae\u01b2\u01b6"+
		"\u01c0\u01c8\u01d0\u01d4\u01db\u01e2\u01e6\u01e9\u01ec\u01f5\u01fb\u0200"+
		"\u0203\u0209\u020f\u0213\u0217\u021f\u0228\u022f\u0235\u0239\u0245\u024e"+
		"\u0253\u0259\u025d\u0269\u0270\u027d\u0282\u028c\u0294\u029e\u02a7\u02b2"+
		"\u02b7\u02c0\u02ca\u02cf\u02d8\u02de\u02e5\u02ea\u02f2\u02f6\u02f8\u0301"+
		"\u0304\u0308\u030c\u0312\u0317\u031b\u0323\u032a\u0330\u0332\u0339\u033f"+
		"\u0345\u0348\u0352\u035c\u0362\u0369\u036d\u0376\u037c\u0387\u0391\u039c"+
		"\u03a6\u03ae\u03b1\u03bb\u03c5\u03c8\u03cb\u03ce\u03d3\u03db\u03de\u03e1"+
		"\u03ee\u03f6\u03fb\u0403\u0407\u040b\u040f\u0413\u0415\u0419\u041f\u0429"+
		"\u042d\u0435\u043e\u044c\u044f\u045b\u045e\u046e\u0473\u047a\u047f\u0485"+
		"\u048e\u0496\u049d\u04a1\u04a8\u04aa\u04b3\u04bd\u04c2\u04cd\u04d6\u04dd"+
		"\u04e4\u04ef\u050a\u050d\u0510\u0518\u051c\u0526\u052c\u0539\u0542\u0547"+
		"\u0554\u055a\u0561\u056e\u0577\u0580\u0586\u058b\u0591\u0597\u059e\u05a3"+
		"\u05ab\u05ae\u05b2\u05b6\u05ba\u05bc\u05c0\u05c5\u05ca\u05d5\u05db\u05e5"+
		"\u05e9\u05ee\u05f9\u0601\u060a\u061a\u064a\u0650\u0655\u065e\u0660\u0662"+
		"\u0668\u066f\u0677\u067b\u0682\u068e\u0697\u069e\u06a1\u06a5\u06b7\u06b9"+
		"\u06c3\u06cd\u06d4\u06d8\u06da\u06e1\u06ec\u06ef\u06f4\u06fc\u0700\u0705"+
		"\u0709\u070d\u0711\u0719\u0722\u0728\u072b\u072f\u0737\u073c\u0747\u074d"+
		"\u0752\u0757\u075e\u0762\u076c\u0774\u0778\u077a\u0781\u0785";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}