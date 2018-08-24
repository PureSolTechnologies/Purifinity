/****************************************************************************
 * Java 1.6 Grammar File for Nyota Uhura
 * (c) by 2010 PureSol Technologies
 * Author: Rick-Rainer Ludwig
 ****************************************************************************/ 

/*
 * All naming is taken out from Java Language Specification.
 */
 
/****************************************************************************
 * O P T I O N S
 ****************************************************************************/ 
OPTIONS
	grammar.name="Java-1.7";
	grammar.checks=true;
	grammar.ignore-case=false;
	grammar.ignored-leading=true;
	grammar.normalize_to_bnf=true;					// defines whether the grammar needs to be normalized to BNF
	preprocessor.use=false;							// usage of preprocessor required? 
	preprocessor="";								// usage of preprocessor required? 
	lexer="com.puresoltechnologies.parsers.lexer.RegExpLexer";
	parser="com.puresoltechnologies.parsers.parser.lr.LR1Parser";
	parser.backtracking=true;

/****************************************************************************
 * H E L P E R
 ****************************************************************************/ 
HELPER

	/*******************
	 3.3 Unicode Escapes
	 *******************/
	 
	UnicodeInputCharacter:
		"(" UnicodeEscape "|" RawInputCharacter ")"
	;
		
	UnicodeEscape:
		"\\\\" UnicodeMarker HexDigit HexDigit HexDigit HexDigit
	;
		
	UnicodeMarker:
		"(u+)"
	;
		
	RawInputCharacter:
		"\\w"
	;
		
	HexDigit:
		"[0-9a-fA-F]"
	;
	
	/************	
	 3.7 Comments
	 ************/
		
	TraditionalComment:
		"/\\*" CommentTail
	;
		
	EndOfLineComment:
		"//" CharactersInLine LineTerminator  
	;
		
	CommentTail:
		"(" NotStar "|\\*(?!/))*\\*/"
	;
		
	NotStar:
		"[^*]"
	;
		
	CharactersInLine:
		InputCharacter "*"
	;
	
	InputCharacter: "([^\\n\\r]|" UnicodeEscape ")" ;

	/***************		
	 3.8 Identifiers
	 ***************/
	
	IdentifierChars:
		JavaLetter JavaLetterOrDigit "*"
	;
		
	JavaLetter:
		"[a-zA-Z_$]"
	;
		
	JavaLetterOrDigit:
		"[a-zA-Z0-9_$]"
	;

	/*************
	 3.10 Literals
	 *************/

	/* 3.10.1 Integer Literals */
	
	DecimalIntegerLiteral:
		DecimalNumeral IntegerTypeSuffix "?"
	;
	
	HexIntegerLiteral:
		HexNumeral IntegerTypeSuffix "?"
	;
	
	OctalIntegerLiteral:
		OctalNumeral IntegerTypeSuffix "?"
	;
	
	IntegerTypeSuffix:
		"[lL]"
	;
	
	DecimalNumeral:
		"(0|" NonZeroDigit "(" Digits ")?)"
	;
	
	Digits:
		Digit "+"
	;
	
	Digit:
		"[0-9]"
	;
	
	NonZeroDigit:
		"[1-9]"
	;

	HexNumeral:
		"0[xX]" HexDigits
	;
	
	HexDigits:
		HexDigit "+"
	;

	/* already defined...
	HexDigit: one of
		0 1 2 3 4 5 6 7 8 9 a b c d e f A B C D E F
	*/
	
	OctalNumeral:
		"0" OctalDigits
	;
	
	OctalDigits:
		OctalDigit "+"
	;
	
	OctalDigit:
		"[0-7]"
	;
		
	/* 3.10.2 Floating-Point Literals */

	DecimalFloatingPointLiteral:
	"("	Digits "\\.(" Digits ")?(" ExponentPart ")?(" FloatTypeSuffix ")?"
	"|"	"\\." Digits "(" ExponentPart ")?(" FloatTypeSuffix ")?"
	"|"	Digits ExponentPart "(" FloatTypeSuffix ")?"
	"|"	Digits "(" ExponentPart ")?" FloatTypeSuffix
	")"
	;
	
	ExponentPart:
		ExponentIndicator SignedInteger
	;
	
	ExponentIndicator:
		"[eE]"
	;
	
	SignedInteger:
		Sign "?" Digits
	;
	
	Sign:
		"[+-]"
	;
	
	FloatTypeSuffix:
		"[fFdD]"
	;
	
	HexadecimalFloatingPointLiteral:
		HexSignificand BinaryExponent FloatTypeSuffix "?"
	;
	
	HexSignificand:
	"("	HexNumeral
	"|"	HexNumeral "\\."
	"|"	"0[xX](" HexDigits ")?\\." HexDigits
	")"
	;
	
	BinaryExponent:
		BinaryExponentIndicator SignedInteger
	;
	
	BinaryExponentIndicator:
		"[pP]"
	;

	/* 3.10.3 Boolean Literals */

	/* 3.10.4 Character Literals */
	
	SingleCharacter:
		"[^\\'\\\\]"
	;

	/* 3.10.5 String Literals */
	
	StringCharacters:
		StringCharacter "+"
	;
	
	StringCharacter:
		'([^\\n\\r\\\"]|' UnicodeEscape "|" EscapeSequence ")"
	;

	/* 3.10.6 Escape Sequences for Character and String Literals */
	EscapeSequence:
	"("	"\\\\b" /* \u0008: backspace BS */
	"|"	"\\\\t" /* \u0009: horizontal tab HT */
	"|"	"\\\\n" /* \u000a: linefeed LF */
	"|"	"\\\\f" /* \u000c: form feed FF */
	"|"	"\\\\r" /* \u000d: carriage return CR */
	"|"	"\\\\\"" /* \u0022: double quote " */
	"|"	"\\\\'" /* \u0027: single quote ' */
	"|"	"\\\\\\\\" /* \u005c: backslash \ */
	"|"	OctalEscape /* \u0000 to \u00ff: from octal value */
	")"
	;
	
	OctalEscape:
	"("	"\\\\" OctalDigit
	"|"	"\\\\" OctalDigit OctalDigit
	"|"	"\\\\" ZeroToThree OctalDigit OctalDigit
	")"
	;
	
	OctalDigit:
		"[0-7]"
	;
	
	ZeroToThree:
		"[0-3]"
	;
		
	/* 3.10.7 The Null Literal */

	/*************** 
	 3.11 Separators
	 ***************/
	Separator:
		"[(){}\\[\\];,\\.]"
	;

		
 /****************************************************************************
 * T O K E N S
 ****************************************************************************/ 
 TOKENS

	/********************	
	 3.4 Line Terminators
	 ********************/
		
	LineTerminator:
		"(\\n|\\r|\\r\\n)" [ignore]
	;
	/*****************************	
	 3.5 Input Elements and Tokens
	 *****************************/

	Sub:
		"\\\\x1A" [ignore]
	;

	/***************	
	 3.6 White Space
	 ***************/
		
	WhiteSpace:
		"( |\\t|\\f)" [ignore]
	;
	
	/************	
	 3.7 Comments
	 ************/
	Comment:
		"(" TraditionalComment "|" EndOfLineComment ")" [ignore]
	;

	/**************
	  3.9 Keywords
	***************/

	ABSTRACT : "abstract(?!\\w)" ;
	ASSERT : "assert(?!\\w)" ;
	BOOLEAN : "boolean(?!\\w)" ;
	BREAK : "break(?!\\w)" ;
	BYTE : "byte(?!\\w)" ;
	CASE : "case(?!\\w)" ;
	CLASS : "class(?!\\w)" ;
	CONTINUE : "continue(?!\\w)" ;
	CATCH : "catch(?!\\w)" ;
	CHAR : "char(?!\\w)" ;
	CONST : "const(?!\\w)" ;
	DEFAULT : "default(?!\\w)" ;
	DO : "do(?!\\w)" ;
	DOUBLE : "double(?!\\w)" ;
	ELSE : "else(?!\\w)" ;
	ENUM : "enum(?!\\w)" ;
	EXTENDS : "extends(?!\\w)" ;
	FINAL : "final(?!\\w)" ;
	FINALLY : "finally(?!\\w)" ;
	FLOAT : "float(?!\\w)" ;
	FOR : "for(?!\\w)" ;
	GOTO : "goto(?!\\w)" ;
	IF : "if(?!\\w)" ;
	IMPLEMENTS : "implements(?!\\w)" ;
	IMPORT : "import(?!\\w)" ;
	INSTANCEOF : "instanceof(?!\\w)" ;
	INT : "int(?!\\w)" ;
	INTERFACE : "interface(?!\\w)" ;
	LONG : "long(?!\\w)" ;
	NATIVE : "native(?!\\w)" ;
	NEW : "new(?!\\w)" ;
	PACKAGE : "package(?!\\w)" ;
	PRIVATE : "private(?!\\w)" ;
	PROTECTED : "protected(?!\\w)" ;
	PUBLIC : "public(?!\\w)" ;
	RETURN : "return(?!\\w)" ;
	SHORT : "short(?!\\w)" ;
	STATIC : "static(?!\\w)" ;
	STRICTFP : "strictfp(?!\\w)" ;
	SUPER : "super(?!\\w)" ;
	SWITCH : "switch(?!\\w)" ;
	SYNCHRONIZED : "synchronized(?!\\w)" ;
	THIS : "this(?!\\w)" ;
	THROW : "throw(?!\\w)" ;
	THROWS : "throws(?!\\w)" ;
	TRANSIENT : "transient(?!\\w)" ;
	TRY : "try(?!\\w)" ;
	VOID : "void(?!\\w)" ;
	VOLATILE : "volatile(?!\\w)" ;
	WHILE : "while(?!\\w)" ;


	/*************
	 3.10 Literals
	 *************/
	IntegerLiteral:
		"(" DecimalIntegerLiteral "|" HexIntegerLiteral "|" OctalIntegerLiteral ")(?!\\w)"
	;

	FloatingPointLiteral:
		"(" DecimalFloatingPointLiteral "|" HexadecimalFloatingPointLiteral ")(?!\\w)"
	;
	
	BooleanLiteral:
		"(true|false)(?!\\w)"
	;

	CharacterLiteral:
		"\\'(" SingleCharacter "|" EscapeSequence "|" UnicodeEscape ")\\'"
	;
	
	StringLiteral:		
		"\"(" StringCharacters ")?\""
	;
	
	NullLiteral:
		"null(?!\\w)"
	;

	/**************
	 3.12 Operators
	 **************/
	EQUALS: "=" ;
	LESS_THAN: "<" ;
	GREATER_THAN: ">" ;
	LPAREN: "\\(" ;
	RPAREN: "\\)" ;
	LCURLY: "\\{" ;
	RCURLY: "\\}" ;
	LRECTANGULAR: "\\[" ;
	RRECTANGULAR: "\\]" ;
	DOT: "\\." ;
	COMMA: "," ;
	COLON: ":" ;
	SEMICOLON: ";" ;
	DOLLAR: "\\$" ;
	CARET: "\\^" ;
	STAR: "\\*" ;
	SLASH: "/" ;
	PERCENT: "%";
	PLUS: "\\+" ;
	MINUS: "-";
	AMPERSAND: "&" ;
	AT: "@" ;
	EXCLAMATION_MARK: "!" ;
	QUESTION_MARK : "\\?" ;
	TILDE : "~" ;
	VERTICAL_BAR : "\\|" ;
	
	/***************		
	 3.8 Identifiers
	 ***************/

	/*
	 * The identifier needs to be first, otherwise keywords are caught by this definition...
	 */	
	Identifier:
		IdentifierChars
	;

/****************************************************************************
 * P R O D U C T I O N S
 ****************************************************************************/ 
 PRODUCTIONS
 
 	_START_ : 
 		CompilationUnit 
 	;

/******************************
 4 Types, Values, and Variables
 ******************************/

/* 4.1 The Kinds of Types and Values */
	
	Type:
		PrimitiveType
	|	ReferenceType
	;
	
/* 4.2 Primitive Types and Values */
	
	PrimitiveType:
		NumericType
	|	BOOLEAN
	;
	
	NumericType:
		IntegralType
	|	FloatingPointType
	;
	
	IntegralType:
		BYTE
	|	SHORT
	|	INT 
	|	LONG
	|	CHAR
	;

	FloatingPointType: 
		FLOAT
	|	DOUBLE
	;

/* 4.3 Reference Types and Values */

	ReferenceType:
/*
		TypeVariable
	|*/	
		ArrayType
	|	ClassOrInterfaceType
	;
	
	ClassOrInterfaceType:
		ClassOrInterfaceType DOT Identifier TypeArguments ?
	|	Identifier TypeArguments ?
	/*
	removed due to ambiguous productions...
		ClassType
	|	InterfaceType
	*/
	;
	
	ClassType:
		ClassType DOT Identifier TypeArguments ?
	|	Identifier TypeArguments ?
	;
	
	InterfaceType:
		InterfaceType DOT Identifier TypeArguments ?
	|	Identifier TypeArguments ?
	;
	
	TypeDeclSpecifier:
	//	TypeName speed improvement...
		TypeDeclSpecifier TypeArguments ? DOT Identifier
	|	Identifier
	;
	
	TypeVariable:
		Identifier
	;
	
	ArrayType:
		Type LRECTANGULAR RRECTANGULAR
	;

/* 4.4 Type Variables */
	
	TypeParameter:
		TypeVariable TypeBound ?
	;
	
	TypeBound:
		EXTENDS ClassOrInterfaceType AdditionalBoundList ?
	;
	
	AdditionalBoundList:
		AdditionalBoundList AdditionalBound
	|	AdditionalBound
	;
	
	AdditionalBound:
		AMPERSAND InterfaceType
	;

/* 4.5 Parameterized Types */
	
	TypeArguments:
		LESS_THAN ActualTypeArgumentList GREATER_THAN
	;
	
	ActualTypeArgumentList:
		ActualTypeArgumentList COMMA ActualTypeArgument
	|	ActualTypeArgument
	;
	
	ActualTypeArgument:
		ReferenceType
	|	Wildcard
	;
	
	Wildcard:
		QUESTION_MARK WildcardBounds ?
	;
	
	WildcardBounds:
		EXTENDS ReferenceType
	|	SUPER ReferenceType
	;

/*******
 6 Names
 *******/

/* 6.5 Determining the Meaning of a Name */

	// This Name is used for all names which can appear to reduce ambiguity.
	Name:
		Name DOT Identifier			[stack=false]
	|	Identifier					[stack=false]
	;

/**********
 7 Packages
 **********/

/* 7.3 Compilation Units */

	CompilationUnit:
		PackageDeclaration ? ImportDeclarations ? TypeDeclarations ? Sub ?
	;

	ImportDeclarations:
		ImportDeclarations ImportDeclaration
	|	ImportDeclaration
	;
	
	TypeDeclarations:
		TypeDeclarations TypeDeclaration
	|	TypeDeclaration
	;

/* 7.4 Package Declarations */
	
	PackageDeclaration:
		Annotations ? PACKAGE Name SEMICOLON
	;
	
/* 7.5 Import Declarations */
	
	ImportDeclaration:
		SingleTypeImportDeclaration
	|	TypeImportOnDemandDeclaration
	|	SingleStaticImportDeclaration
	|	StaticImportOnDemandDeclaration
	;
	
	SingleTypeImportDeclaration:
		IMPORT Name SEMICOLON
	;
	
	TypeImportOnDemandDeclaration:
		IMPORT Name DOT STAR SEMICOLON
	;
	
	SingleStaticImportDeclaration:
		IMPORT STATIC Name SEMICOLON
	;
	
	StaticImportOnDemandDeclaration:
		IMPORT STATIC Name DOT STAR SEMICOLON
	;
	
/* 7.6 Top Level Type Declarations */
	TypeDeclaration:
		ClassDeclaration
	|	InterfaceDeclaration
	|	SEMICOLON
	;
	
/*********
 8 Classes
 *********/
 
 /* 8.1 Class Declaration */
 
	ClassDeclaration:
		NormalClassDeclaration
	|	EnumDeclaration
	;
	
	NormalClassDeclaration:
		ClassModifiers ? CLASS Identifier TypeParameters ? Super ? Interfaces ? ClassBody
	;
	
	ClassModifiers:
		ClassModifiers ClassModifier
	|	ClassModifier
	;
	
	ClassModifier:
		Annotation
	|	PUBLIC
	|	PROTECTED
	|	PRIVATE
	|	ABSTRACT
	|	STATIC
	|	FINAL
	|	STRICTFP
	;
	
	TypeParameters :
		LESS_THAN TypeParameterList GREATER_THAN
	;
	
	TypeParameterList :
		TypeParameterList COMMA TypeParameter
	|	TypeParameter
	;

	Super:
		EXTENDS ClassType
	;

	Interfaces:
		IMPLEMENTS InterfaceTypeList
	;
	
	InterfaceTypeList:
		InterfaceTypeList COMMA InterfaceType
	|	InterfaceType
	;
	
	ClassBody:
		LCURLY ClassBodyDeclarations ? RCURLY
	;
	
	ClassBodyDeclarations:
		ClassBodyDeclarations ClassBodyDeclaration
	|	ClassBodyDeclaration
	;
	
	ClassBodyDeclaration:
		ClassMemberDeclaration
	|	InstanceInitializer
	|	StaticInitializer
	|	ConstructorDeclaration
	;

	ClassMemberDeclaration:
		FieldDeclaration
	|	MethodDeclaration
	|	ClassDeclaration
	|	InterfaceDeclaration
	|	SEMICOLON
	;

/* 8.3 Field Declarations */

	FieldDeclaration:
		FieldModifiers ? Type VariableDeclarators SEMICOLON
	;
	
	VariableDeclarators:
		VariableDeclarators COMMA VariableDeclarator
	|	VariableDeclarator
	;
	
	VariableDeclarator:
		VariableDeclaratorId EQUALS VariableInitializer
	|	VariableDeclaratorId
	;
	
	VariableDeclaratorId:
		VariableDeclaratorId LRECTANGULAR RRECTANGULAR
	|	Identifier
	;
	
	VariableInitializer:
		Expression
	|	ArrayInitializer
	;
	
	FieldModifiers:
		FieldModifiers FieldModifier
	|	FieldModifier
	;
	
	FieldModifier:
		Annotation
	|	PUBLIC
	|	PROTECTED
	|	PRIVATE
	|	STATIC
	|	FINAL
	|	TRANSIENT
	|	VOLATILE
	;
	
/* 8.4 Method Declarations */

	MethodDeclaration:
		MethodHeader MethodBody
	;
	
	MethodHeader:
		MethodModifiers ? TypeParameters ? ResultType MethodDeclarator Throws ?
	;
	
	ResultType:
		Type
	|	VOID
	;
	
	MethodDeclarator:
		Identifier LPAREN FormalParameterList ? RPAREN
	|	MethodDeclarator LRECTANGULAR RRECTANGULAR	// is obsolete, but can be found...
	;
	
	FormalParameterList:
		LastFormalParameter
	|	FormalParameters ( COMMA LastFormalParameter ) ?
	;
	
	FormalParameters:
		FormalParameters COMMA FormalParameter
	|	FormalParameter
	;
	
	FormalParameter:
		VariableModifiers ? Type VariableDeclaratorId
	;
	
	VariableModifiers:
		VariableModifiers VariableModifier
	|	VariableModifier
	;
	
	VariableModifier:
		FINAL
	|	Annotation
	;
	
	LastFormalParameter:
		VariableModifiers ? Type DOT DOT DOT VariableDeclaratorId
	// |	FormalParameter changed for performance...
	;
	
	MethodModifiers:
		MethodModifiers MethodModifier
	|	MethodModifier
	;
	
	MethodModifier:
		Annotation
	|	PUBLIC
	|	PROTECTED
	|	PRIVATE
	|	ABSTRACT
	|	STATIC
	|	FINAL
	|	SYNCHRONIZED
	|	NATIVE
	|	STRICTFP
	;
	
	Throws:
		THROWS ExceptionTypeList
	;
	
	ExceptionTypeList:
		ExceptionTypeList COMMA ExceptionType
	|	ExceptionType
	;
	
	ExceptionType:
		ClassType
	// |	TypeVariable ClassType also contains a single Identifier 
	;
	
	MethodBody:
		Block
	|	SEMICOLON
	;
	
/* 8.6 Instance Initializers */

	InstanceInitializer:
		Block
	;

/* 8.7 Static Initializers */
	StaticInitializer:
		STATIC Block
	;
	
/* 8.8 Constructor Declarations */

	ConstructorDeclaration:
		ConstructorModifiers ? ConstructorDeclarator Throws ? ConstructorBody
	;
	
	ConstructorDeclarator:
		TypeParameters ? Name LPAREN FormalParameterList ? RPAREN
	;
	
	ConstructorModifiers:
		ConstructorModifiers ConstructorModifier
	|	ConstructorModifier
	;
	
	ConstructorModifier:
		Annotation
	|	PUBLIC
	|	PROTECTED
	|	PRIVATE
	;
	
	ConstructorBody:
		LCURLY ExplicitConstructorInvocation ? BlockStatements ? RCURLY
	;
	
	ExplicitConstructorInvocation:
		NonWildTypeArguments ? THIS Arguments SEMICOLON
	|	NonWildTypeArguments ? SUPER Arguments SEMICOLON
	|	Primary DOT NonWildTypeArguments ? SUPER Arguments SEMICOLON
	;
	
	NonWildTypeArguments:
		LESS_THAN ReferenceTypeList GREATER_THAN
	;
	
	ReferenceTypeList:
		ReferenceTypeList COMMA ReferenceType
	|	ReferenceType
	;
	
/* 8.9 Enums */

	EnumDeclaration:
		ClassModifiers ? ENUM Identifier Interfaces ? EnumBody
	;
	
	EnumBody:
		LCURLY EnumConstants ? COMMA ? EnumBodyDeclarations ? RCURLY
	;
	
	EnumConstants:
		EnumConstants COMMA EnumConstant
	|	EnumConstant
	;
	
	EnumConstant:
		Annotations ? Identifier Arguments ? ClassBody ?
	;
	
	Arguments:
		LPAREN ArgumentList ? RPAREN
	;
	
	EnumBodyDeclarations:
		SEMICOLON ClassBodyDeclarations ?
	;
	
/************
 9 Interfaces
 ************/
 
 /* 9.1 Interface Declarations */

	InterfaceDeclaration:
		NormalInterfaceDeclaration
	|	AnnotationTypeDeclaration
	;
		
	NormalInterfaceDeclaration:
		InterfaceModifiers ? INTERFACE Identifier TypeParameters ? ExtendsInterfaces ? InterfaceBody
  	;
  	
  	InterfaceModifiers:
		InterfaceModifiers InterfaceModifier
	|	InterfaceModifier
	;
	
	InterfaceModifier:
		Annotation
	|	PUBLIC
	|	PROTECTED
	|	PRIVATE
	|	ABSTRACT
	|	STATIC
	|	STRICTFP
	;
	
	ExtendsInterfaces:
		EXTENDS InterfaceType
	|	ExtendsInterfaces COMMA InterfaceType
	;
	
	InterfaceBody:
		LCURLY InterfaceMemberDeclarations ? RCURLY
	;
	
	InterfaceMemberDeclarations:
		InterfaceMemberDeclarations InterfaceMemberDeclaration
	|	InterfaceMemberDeclaration
	;
		
	InterfaceMemberDeclaration:
		ConstantDeclaration
	|	AbstractMethodDeclaration
	|	ClassDeclaration
	|	InterfaceDeclaration
	|	SEMICOLON
	;
	
/* 9.3 Field (Constant) Declarations */

	ConstantDeclaration:
		ConstantModifiers ? Type VariableDeclarators SEMICOLON
	;
	
	ConstantModifiers:
		ConstantModifiers ConstantModifier
	|	ConstantModifier
	;
	 
	ConstantModifier:
		Annotation
	|	PUBLIC
	|	STATIC
	|	FINAL
	;
	
/* 9.4 Abstract Method Declarations */

	AbstractMethodDeclaration:
		AbstractMethodModifiers ? TypeParameters ? ResultType MethodDeclarator Throws ? SEMICOLON
	;
	
	AbstractMethodModifiers:
		AbstractMethodModifiers AbstractMethodModifier
	|	AbstractMethodModifier
	;
	
	AbstractMethodModifier:
		Annotation
	|	PUBLIC
	|	ABSTRACT
	;
	
/* 9.6 Annotation Types */

	AnnotationTypeDeclaration:
		InterfaceModifiers ? AT INTERFACE Identifier AnnotationTypeBody
	;
	
	AnnotationTypeBody:
		LCURLY AnnotationTypeElementDeclarations ? RCURLY
	;
	
	AnnotationTypeElementDeclarations:
		AnnotationTypeElementDeclarations AnnotationTypeElementDeclaration
	|	AnnotationTypeElementDeclaration
	;
	
	AnnotationTypeElementDeclaration:
		AbstractMethodModifiers ? Type Identifier LPAREN RPAREN Dims ? DefaultValue ? SEMICOLON
	|	ConstantDeclaration
	|	ClassDeclaration
	|	InterfaceDeclaration
	|	EnumDeclaration
	|	AnnotationTypeDeclaration
	|	SEMICOLON
	;
	
	DefaultValue:
		DEFAULT ElementValue
	;
	
/* 9.7 Annotations */

	Annotations:
		Annotations Annotation
	|	Annotation
	;
	
	Annotation:
		NormalAnnotation
	|	MarkerAnnotation
	|	SingleElementAnnotation
	;
	
	NormalAnnotation:
		AT Name LPAREN ElementValuePairs ? RPAREN
	;

	ElementValuePairs:
		ElementValuePairs COMMA ElementValuePair
	|	ElementValuePair
	;
	
	ElementValuePair:
		Identifier EQUALS ElementValue
	;
	
	ElementValue:
		ConditionalExpression
	|	Annotation
	|	ElementValueArrayInitializer
	;
	
	ElementValueArrayInitializer:
		LCURLY ElementValues ? COMMA ? RCURLY
	;
	
	ElementValues:
		ElementValues COMMA ElementValue
	|	ElementValue
	;
	
	MarkerAnnotation:
		AT Name
	;
	
	SingleElementAnnotation:
		AT Name LPAREN ElementValue RPAREN
	;

/*********
 10 Arrays
 *********/
 
 /* 10.6 Array Initializers */
 
 	ArrayInitializer:
		LCURLY VariableInitializers ? COMMA ? RCURLY
	;
	
	VariableInitializers:
		VariableInitializers COMMA VariableInitializer
	|	VariableInitializer
	;

/************************	
 14 Blocks and Statements
 ************************/
 
/* 14.2 Blocks */

	Block:
		LCURLY BlockStatements ? RCURLY
	;
	
	BlockStatements:
		BlockStatements BlockStatement
	|	BlockStatement
	;
	
	BlockStatement:
		ClassDeclaration
	|	LocalVariableDeclarationStatement
	|	Statement
	;
	
/* 14.4 Local Variable Declaration Statements */

	LocalVariableDeclarationStatement:
		LocalVariableDeclaration SEMICOLON
	;
	
	LocalVariableDeclaration:
		VariableModifiers ? Type VariableDeclarators
	;

/* 14.5 Statements */

	Statement:
		StatementWithoutTrailingSubstatement
	|	LabeledStatement
	|	IfThenStatement
	|	IfThenElseStatement
	|	WhileStatement
	|	ForStatement
	;
	
	StatementWithoutTrailingSubstatement:
		Block
	|	EmptyStatement
	|	ExpressionStatement
	|	AssertStatement
	|	SwitchStatement
	|	DoStatement
	|	BreakStatement
	|	ContinueStatement
	|	ReturnStatement
	|	SynchronizedStatement
	|	ThrowStatement
	|	TryStatement
	;
	
	StatementNoShortIf:
		StatementWithoutTrailingSubstatement
	|	LabeledStatementNoShortIf
	|	IfThenElseStatementNoShortIf
	|	WhileStatementNoShortIf
	|	ForStatementNoShortIf
	;
	
/* 14.6 The Empty Statement */

	EmptyStatement:
		SEMICOLON
	;
	
/* 14.7 Labeled Statements */
	
	LabeledStatement:
		Identifier COLON Statement
	;
	
	LabeledStatementNoShortIf:
		Identifier COLON StatementNoShortIf
	;

/* 14.8 Expression Statements */

	ExpressionStatement:
		StatementExpression SEMICOLON
	;
	
	StatementExpression:
		Assignment
	|	PreIncrementExpression
	|	PreDecrementExpression
	|	PostIncrementExpression
	|	PostDecrementExpression
	|	MethodInvocation
	|	ClassInstanceCreationExpression
	;
	
/* 14.9 The if Statement */

	IfThenStatement:
		IF LPAREN Expression RPAREN Statement
	;
	
	IfThenElseStatement:
		IF LPAREN Expression RPAREN StatementNoShortIf ELSE Statement
	;
	
	IfThenElseStatementNoShortIf:
		IF LPAREN Expression RPAREN StatementNoShortIf ELSE StatementNoShortIf
	;
		
/* 14.10 The assert Statement */

	AssertStatement:
		ASSERT Expression SEMICOLON
	|	ASSERT Expression COLON Expression SEMICOLON
	;
	
/* 14.11 The switch Statement */

	SwitchStatement:
		SWITCH LPAREN Expression RPAREN SwitchBlock
	;
	
	SwitchBlock:
		LCURLY SwitchBlockStatementGroups ? SwitchLabels ? RCURLY
	;
	
	SwitchBlockStatementGroups:
		SwitchBlockStatementGroups SwitchBlockStatementGroup
	|	SwitchBlockStatementGroup
	;
	
	SwitchBlockStatementGroup:
		SwitchLabels BlockStatements
	;
	
	SwitchLabels:
		SwitchLabels SwitchLabel
	|	SwitchLabel
	;
	
	SwitchLabel:
		CASE ConstantExpression COLON
	// |	CASE Identifier COLON ; removed for speed...
	|	DEFAULT COLON
	;
		
/* 14.12 The while Statement */

	WhileStatement:
		WHILE LPAREN Expression RPAREN Statement
	;
	
	WhileStatementNoShortIf:
		WHILE LPAREN Expression RPAREN StatementNoShortIf
	;
	
/* 14.13 The do Statement */

	DoStatement:
		DO Statement WHILE LPAREN Expression RPAREN SEMICOLON
	;
	
/* 14.14 The for Statement */

	ForStatement:
		BasicForStatement
	|	EnhancedForStatement
	;
	
	BasicForStatement:
		FOR LPAREN ForInit ? SEMICOLON Expression ? SEMICOLON ForUpdate ? RPAREN Statement
	;
	
	ForStatementNoShortIf:
		FOR LPAREN ForInit ? SEMICOLON Expression ? SEMICOLON ForUpdate ? RPAREN StatementNoShortIf
	;
	
	ForInit:
		StatementExpressionList
	|	LocalVariableDeclaration
	;
	
	ForUpdate:
		StatementExpressionList
	;
	
	StatementExpressionList:
		StatementExpressionList COMMA StatementExpression
	|	StatementExpression
	;
	
	EnhancedForStatement:
		FOR LPAREN VariableModifiers ? Type Identifier COLON Expression RPAREN Statement
	;
	
/* 14.15 The break Statement */

	BreakStatement:
		BREAK Identifier ? SEMICOLON
	;
	
/* 14.16 The continue Statement */

	ContinueStatement:
		CONTINUE Identifier ? SEMICOLON
	;
	
/* 14.17 The return Statement */

	ReturnStatement:
		RETURN Expression ? SEMICOLON
	;

/* 14.18 The throw Statement */

	ThrowStatement:
		THROW Expression SEMICOLON
	;
	
/* 14.19 The synchronized Statement */

	SynchronizedStatement:
		SYNCHRONIZED LPAREN Expression RPAREN Block
	;
	
/* 14.20 The try statement */

	TryStatement:
		TRY Block Catches
	|	TRY Block Catches ? Finally
	|	TryWithResourcesStatement
	;
	
	Catches:
		Catches CatchClause
	|	CatchClause
	;
	
	CatchClause:
		CATCH LPAREN CatchFormalParameter RPAREN Block
	;
	
	CatchFormalParameter:
		VariableModifier ? CatchType VariableDeclaratorId
	;
	
	CatchType:
		ClassType
	|	ClassType VERTICAL_BAR CatchType
	;
	
	Finally:
		FINALLY Block
	;
	
	TryWithResourcesStatement:
		TRY ResourceSpecification Block Catches ? Finally ?
	;

	ResourceSpecification:
		LPAREN Resources SEMICOLON ? RPAREN
	;

	Resources:
		Resource
	|	Resource SEMICOLON Resources
	;

	Resource:
		VariableModifiers ? Type VariableDeclaratorId EQUALS Expression
	;
	
/**************
 15 Expressions
 **************/
 
/* 15.8 Primary Expressions */

	Primary:
		ArrayCreationExpression
	|	PrimaryNoNewArray
	;
	
	PrimaryNoNewArray:
		Literal
	|	VOID DOT CLASS
	|	THIS
	|	LPAREN Expression RPAREN
	|	Type DOT CLASS
	|	Name DOT THIS
	|	ClassInstanceCreationExpression
	|	MethodInvocation
	|	ArrayAccess
	|	FieldAccess
	;	
	
	Literal:
		FloatingPointLiteral
	|	IntegerLiteral
	|	BooleanLiteral
	|	CharacterLiteral
	|	StringLiteral
	|	NullLiteral
	;

/* 15.9 Class Instance Creation Expressions */

	ClassInstanceCreationExpression:
		NEW TypeArguments ? TypeDeclSpecifier TypeArgumentsOrDiamond ? LPAREN ArgumentList ? RPAREN ClassBody ?
	|	Primary DOT NEW TypeArguments ? Identifier TypeArgumentsOrDiamond ? LPAREN ArgumentList ? RPAREN ClassBody ?
	/*
		this was added due to: 
		hotspot/src/share/tools/IdealGraphVisualizer/HierarchicalLayout/src/com/sun/hotspot/igv/hierarchicallayout/OldHierarchicalLayoutManager.java 
		"[...]graph.new[...]"
	*/
	|	Identifier DOT NEW TypeArgumentsOrDiamond ? Identifier TypeArguments ? Arguments ClassBody ? 
	;
	
	TypeArgumentsOrDiamond:
		TypeArguments
	|	LESS_THAN GREATER_THAN
	;
	
	ArgumentList:
		ArgumentList COMMA Expression
	|	Expression
	;
	
/* 15.10 Array Creation Expressions */

	ArrayCreationExpression:
		NEW PrimitiveType DimExprs Dims ?
	|	NEW ClassOrInterfaceType DimExprs Dims ?
	|	NEW PrimitiveType Dims ArrayInitializer
	|	NEW ClassOrInterfaceType Dims ArrayInitializer
	;
		
	DimExprs:
		DimExprs DimExpr
	|	DimExpr
	;
	
	DimExpr:
		LRECTANGULAR Expression RRECTANGULAR
	;
	
	Dims:
		Dims LRECTANGULAR RRECTANGULAR
	|	LRECTANGULAR RRECTANGULAR
	;

/* 15.11 Field Access Expressions */

	FieldAccess:
		SUPER DOT Identifier
	|	Primary DOT Identifier
	|	Name DOT SUPER DOT Identifier
	;
	
/* 15.12 Method Invocation Expressions */

	MethodInvocation:
		SUPER DOT NonWildTypeArguments ? Identifier Arguments
	|	Name DOT SUPER DOT NonWildTypeArguments ? Identifier Arguments
	|	Name DOT NonWildTypeArguments Identifier Arguments
	|	Primary DOT NonWildTypeArguments ? Identifier Arguments
	|	Name Arguments
	;

/* 15.13 Array Access Expressions */

	ArrayAccess:
		PrimaryNoNewArray LRECTANGULAR Expression RRECTANGULAR
	|	Name LRECTANGULAR Expression RRECTANGULAR
	;
	
/* 15.14 Postfix Expressions */

	PostfixExpression:
		Primary
	|	PostIncrementExpression
	|	PostDecrementExpression
	|	Name
	;
	
	PostIncrementExpression:
		PostfixExpression PLUS PLUS
	;
	
	PostDecrementExpression:
		PostfixExpression MINUS MINUS
	;
	
/* 15.15 Unary Operators */

	UnaryExpression:
		PLUS UnaryExpression
	|	MINUS UnaryExpression
	|	PreIncrementExpression
	|	PreDecrementExpression
	|	UnaryExpressionNotPlusMinus
	;
	
	PreIncrementExpression:
		PLUS PLUS UnaryExpression
	;
	
	PreDecrementExpression:
		MINUS MINUS UnaryExpression
	;
	
	UnaryExpressionNotPlusMinus:
		TILDE UnaryExpression
	|	EXCLAMATION_MARK UnaryExpression
	|	CastExpression
	|	PostfixExpression
	;
	
/* 15.16 Cast Expressions */

	CastExpression:
		LPAREN PrimitiveType Dims ? RPAREN UnaryExpression
	|	LPAREN ReferenceType RPAREN UnaryExpressionNotPlusMinus
	;
	
/* 15.17 Multiplicative Operators */

	MultiplicativeExpression:
		MultiplicativeExpression STAR UnaryExpression
	|	MultiplicativeExpression SLASH UnaryExpression
	|	MultiplicativeExpression PERCENT UnaryExpression
	|	UnaryExpression
	;

/* 15.18 Additive Operators */

	AdditiveExpression:
		AdditiveExpression PLUS MultiplicativeExpression
	|	AdditiveExpression MINUS MultiplicativeExpression
	|	MultiplicativeExpression
	;

/* 15.19 Shift Operators */

	ShiftExpression:
		ShiftExpression LESS_THAN LESS_THAN AdditiveExpression
	|	ShiftExpression GREATER_THAN GREATER_THAN AdditiveExpression
	|	ShiftExpression GREATER_THAN GREATER_THAN GREATER_THAN AdditiveExpression
	|	AdditiveExpression
	;

/* 15.20 Relational Operators */

	RelationalExpression:
		RelationalExpression LESS_THAN EQUALS ShiftExpression
	|	RelationalExpression GREATER_THAN EQUALS ShiftExpression
	|	RelationalExpression LESS_THAN ShiftExpression
	|	RelationalExpression GREATER_THAN ShiftExpression
	|	RelationalExpression INSTANCEOF ReferenceType
	|	ShiftExpression
	;

/* 15.21 Equality Operators */

	EqualityExpression:
		EqualityExpression EQUALS EQUALS RelationalExpression
	|	EqualityExpression EXCLAMATION_MARK EQUALS RelationalExpression
	|	RelationalExpression
	;

/* 15.22 Bitwise and Logical Operators */

	AndExpression:
		AndExpression AMPERSAND EqualityExpression
	|	EqualityExpression
	;
	
	ExclusiveOrExpression:
		ExclusiveOrExpression CARET AndExpression
	|	AndExpression
	;
	
	InclusiveOrExpression:
		InclusiveOrExpression VERTICAL_BAR ExclusiveOrExpression
	|	ExclusiveOrExpression
	;

/* 15.23 Conditional-And Operator && */

	ConditionalAndExpression:
		ConditionalAndExpression AMPERSAND AMPERSAND InclusiveOrExpression
	|	InclusiveOrExpression
	;

/* 15.24 Conditional-Or Operator || */

	ConditionalOrExpression:
		ConditionalOrExpression VERTICAL_BAR VERTICAL_BAR ConditionalAndExpression
	|	ConditionalAndExpression
	;

/* 15.25 Conditional Operator ? : */

	ConditionalExpression:
		ConditionalOrExpression QUESTION_MARK Expression COLON ConditionalExpression
	|	ConditionalOrExpression
	;

/* 15.26 Assignment Operators */

	AssignmentExpression:
		ConditionalExpression
	|	Assignment
	;
	
	Assignment:
		LeftHandSide AssignmentOperator AssignmentExpression
	;
	
	LeftHandSide:
		FieldAccess
	|	ArrayAccess
	|	Name
	;
	
	AssignmentOperator:
		EQUALS
	|	STAR EQUALS
	|	SLASH EQUALS
	|	PERCENT EQUALS
	|	PLUS EQUALS
	|	MINUS EQUALS
	|	LESS_THAN LESS_THAN EQUALS
	|	GREATER_THAN GREATER_THAN EQUALS
	|	GREATER_THAN GREATER_THAN GREATER_THAN EQUALS
	|	AMPERSAND EQUALS
	|	CARET EQUALS
	|	VERTICAL_BAR EQUALS
	;

/* 15.27 Expression */

	Expression:
		AssignmentExpression
	;

/* 15.28 Constant Expression */

	ConstantExpression:
		Expression
	;
