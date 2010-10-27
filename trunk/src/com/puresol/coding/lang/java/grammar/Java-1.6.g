/****************************************************************************
 * Java 1.6 Grammar File for Nyota Uhura
 * (c) by 2010 PureSol Technologies
 * Author: Rick-Rainer Ludwig
 ****************************************************************************/ 
 
/****************************************************************************
 * O P T I O N S
 ****************************************************************************/ 
OPTIONS
	grammar.checks=false; // TODO
	grammar.ignore-case=false;
	lexer="com.puresol.uhura.lexer.RegExpLexer";
	parser="com.puresol.uhura.parser.lr.SLR1Parser";
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
		"(u|" UnicodeMarker "u)"
	;
		
	RawInputCharacter:
		"\\w"
	;
		
	HexDigit:
		"[0-9a-fA-F]"
	;
	
	/********************	
	 3.4 Line Terminators
	 ********************/
		
	LineTerminator:
		"(\\n|\\r|\\r\\n)"
	;
		
	InputCharacter:
		"[^\\r\\n]"
	;
	
	/*****************************	
	 3.5 Input Elements and Tokens
	 *****************************/
		
	/* This token is not used, but from the lexer it self through the TOKEN definitions...	
	Input:
		InputElements Sub "?"
	;
	*/
		
	/* This token is not used, but from the lexer it self through the TOKEN definitions...	
	InputElements:
		InputElement "*"
	;
	*/
		
	/* This token is not used, but from the lexer it self through the TOKEN definitions...	
	InputElement:
		WhiteSpace
	|	Comment
	|	Token
	;
	*/
	
	/* This token is not used, but from the lexer it self through the TOKEN definitions...	
	Token:
		Identifier
	|	Keyword
	|	Literal
	|	Separator
	|	Operator
	;
	*/
		
	
	/************	
	 3.7 Comments
	 ************/
		
	TraditionalComment:
		"/\\*(\\*[^/]|[^*]+)\\*/"
	;
		
	EndOfLineComment:
		"//[^\\n\\r]*" LineTerminator  
	;
		
	CommentTail:
		"(\\*|" CommentTailStar	"|" NotStar CommentTail ")"
	;
		
	CommentTailStar:
		"(/|\\*" CommentTailStar "|" NotStarNotSlash CommentTail ")"
	;
		
	NotStar:
		"([^*]|" LineTerminator ")"
	;
		
	NotStarNotSlash:
		"([^*/]|" LineTerminator ")"
	;
		
	CharactersInLine:
		InputCharacter "*"
	;

	/***************		
	 3.8 Identifiers
	 ***************/
	
	
	IdentifierChars:
		JavaLetter JavaLetterOrDigit "*"
	;
		
	JavaLetter:
		"[a-zA-Z]"
	;
		
	JavaLetterOrDigit:
		"\\w"
	;

	/*************
	 3.10 Literals
	 *************/

	/* 3.10.1 Integer Literals */
	IntegerLiteral:
		"(" DecimalIntegerLiteral "|" HexIntegerLiteral "|" OctalIntegerLiteral ")"
	;
	
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
		"(0|" NonZeroDigit Digits ")"
	;
	
	Digits:
		Digit "*"
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

	FloatingPointLiteral:
		"(" DecimalFloatingPointLiteral "|" HexadecimalFloatingPointLiteral ")"
	;
	
	DecimalFloatingPointLiteral:
	"("	Digits "\\." Digits ? ExponentPart ? FloatTypeSuffix ?
	"|"	"\\." Digits ExponentPart ? FloatTypeSuffix ?
	"|"	Digits ExponentPart FloatTypeSuffix ?
	"|"	Digits ExponentPart ? FloatTypeSuffix
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
	BooleanLiteral:
		"(true|false)"
	;

	/* 3.10.4 Character Literals */
	CharacterLiteral:
		"(\\'" SingleCharacter "\\'|\\'" EscapeSequence "\\')"
	;
	
	SingleCharacter:
		"[^\\'\\\\]"
	;

	/* 3.10.5 String Literals */
	StringLiteral:		
		"\"" StringCharacters "?\""
	;
	
	StringCharacters:
		StringCharacter "*"
	;
	
	StringCharacter:
		"([^\"\\\\]|" EscapeSequence ")"
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
	"|"	"\\\\ \\\\" /* \u005c: backslash \ */
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
	NullLiteral:
		"null"
	;

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

	/*****************************	
	 3.5 Input Elements and Tokens
	 *****************************/

	Sub:
		"\\\\x1A"
	;

	/***************	
	 3.6 White Space
	 ***************/
		
	WhiteSpace:
		"( |\\t|\\f|" LineTerminator ")" [hidden]
	;
	
	/**************
	  3.9 Keywords
	***************/

	ABSTRACT : "abstract" ;
	ASSERT : "assert" ;
	BOOLEAN : "boolean" ;
	BREAK : "break" ;
	BYTE : "byte" ;
	CASE : "case" ;
	CLASS : "class" ;
	CONTINUE : "continue" ;
	CATCH : "catch" ;
	CHAR : "char" ;
	CONST : "const" ;
	DEFAULT : "default" ;
	DO : "do" ;
	DOUBLE : "double" ;
	ELSE : "else" ;
	ENUM : "enum" ;
	EXTENDS : "extends" ;
	FINAL : "final" ;
	FINALLY : "finally" ;
	FLOAT : "float" ;
	FOR : "for" ;
	GOTO : "goto" ;
	IF : "if" ;
	IMPLEMENTS : "implements" ;
	IMPORT : "import" ;
	INSTANCE : "instanceof" ;
	INT : "int" ;
	INTERFACE : "interface" ;
	LONG : "long" ;
	NATIVE : "native" ;
	NEW : "new" ;
	PACKAGE : "package" ;
	PRIVATE : "private" ;
	PUBLIC : "public" ;
	RETURN : "return" ;
	SHORT : "short" ;
	STATIC : "static" ;
	STRICTFP : "strictfp" ;
	SUPER : "super" ;
	SWITCH : "switch" ;
	SYNCHRONIZED : "synchronized" ;
	THIS : "this" ;
	THROW : "throw" ;
	THROWS : "throws" ;
	TRANSIENT : "transient" ;
	TRY : "try" ;
	VOID : "void" ;
	VOLATILE : "volatile" ;
	WHILE : "while" ;

	/************	
	 3.7 Comments
	 ************/
	Comment:
		"(" TraditionalComment "|" EndOfLineComment ")"
	;

	/***************		
	 3.8 Identifiers
	 ***************/
	
	Identifier:
		IdentifierChars
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
	LRECTANGULAR: "\\]" ;
	DOT: "\\." ;
	COMMA: "," ;
	COLON: ":" ;
	SEMICOLON: ";" ;
	DOLLAR: "\\$" ;
	CARET: "\\^" ;
	STAR: "\\*" ;
	SLASH: "\\*" ;
	AMPERSAND: "&" ;
	AT: "@" ;
	
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
		ClassOrInterfaceType
	|	TypeVariable
	|	ArrayType
	;
	
	ClassOrInterfaceType:
		ClassType
	|	InterfaceType
	;
	
	ClassType:
		TypeDeclSpecifier TypeArguments ?
	;
	
	InterfaceType:
		TypeDeclSpecifier TypeArguments ?
	;
	
	TypeDeclSpecifier:
		TypeName
	|	ClassOrInterfaceType DOT Identifier
	;
	
	TypeName:
		Identifier
	|	TypeName DOT Identifier
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
		ActualTypeArgument
	|	ActualTypeArgumentList COMMA ActualTypeArgument
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

	PackageName:
		Identifier
	|	PackageName DOT Identifier
	;
	
	TypeName:
		Identifier
	|	PackageOrTypeName DOT Identifier
	;
	
	ExpressionName:
		Identifier
	|	AmbiguousName DOT Identifier
	;
	
	MethodName:
		Identifier
	|	AmbiguousName DOT Identifier
	;
	
	PackageOrTypeName:
		Identifier
	|	PackageOrTypeName DOT Identifier
	;
	
	AmbiguousName:
		Identifier
	|	AmbiguousName DOT Identifier
	;

/**********
 7 Packages
 **********/

/* 7.3 Compilation Units */

	CompilationUnit:
		PackageDeclaration ? ImportDeclarations ? TypeDeclarations ?
	;

	ImportDeclarations:
		ImportDeclaration
	|	ImportDeclarations ImportDeclaration
	;
	
	TypeDeclarations:
		TypeDeclaration
	|	TypeDeclarations TypeDeclaration
	;

/* 7.4 Package Declarations */
	
	PackageDeclaration:
		Annotations ? PACKAGE PackageName SEMICOLON
	;
	
/* 7.5 Import Declarations */
	
	ImportDeclaration:
		SingleTypeImportDeclaration
	|	TypeImportOnDemandDeclaration
	|	SingleStaticImportDeclaration
	|	StaticImportOnDemandDeclaration
	;
	
	SingleTypeImportDeclaration:
		IMPORT TypeName SEMICOLON
	;
	
	TypeImportOnDemandDeclaration:
		IMPORT PackageOrTypeName DOT STAR SEMICOLON
	;
	
	SingleStaticImportDeclaration:
		IMPORT STATIC TypeName DOT Identifier SEMICOLON
	;
	
	StaticImportOnDemandDeclaration:
		IMPORT STATIC TypeName DOT STAR SEMICOLON
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
		ClassModifier
		ClassModifiers ClassModifier
	;
	
	ClassModifier: one of
		Annotation
	|	PUBLIC
	|	PROTECTED
	|	PRIVATE
	|	ABSTRACT
	|	STATIC
	|	FINAL
	|	STRICTFP
	;
	
	Super:
		EXTENDS ClassType
	;

	Interfaces:
		IMPLEMENTS InterfaceTypeList
	;
	
	InterfaceTypeList:
		InterfaceType
	|	InterfaceTypeList COMMA InterfaceType
	;
	
	ClassBody:
		LCURLY ClassBodyDeclarations ? RCURLY
	;
	
	ClassBodyDeclarations:
		ClassBodyDeclaration
	|	ClassBodyDeclarations ClassBodyDeclaration
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
		VariableDeclarator
	|	VariableDeclarators COMMA VariableDeclarator
	;
	
	VariableDeclarator:
		VariableDeclaratorId
	|	VariableDeclaratorId EQUALS VariableInitializer
	;
	
	VariableDeclaratorId:
		Identifier
	|	VariableDeclaratorId LRECTANGULAR RRECTANGULAR
	;
	
	VariableInitializer:
		Expression
	|	ArrayInitializer
	;
	
	FieldModifiers:
		FieldModifier
	|	FieldModifiers FieldModifier
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
		Identifier LPAREN FormalParameterListopt RPAREN
	|	MethodDeclarator LRECTANGULAR RRECTANGULAR	// is obsolete, but can be found...
	;
	
	FormalParameterList:
		LastFormalParameter
	|	FormalParameters COMMA LastFormalParameter
	;
	
	FormalParameters:
		FormalParameter
	|	FormalParameters COMMA FormalParameter
	;
	
	FormalParameter:
		VariableModifiers Type VariableDeclaratorId
	;
	
	VariableModifiers:
		VariableModifier
	|	VariableModifiers VariableModifier
	;
	
	VariableModifier:
		FINAL
	|	Annotation
	;
	
	LastFormalParameter:
		VariableModifiers Type DOT DOT DOT VariableDeclaratorId
	|	FormalParameter
	;
	
	MethodModifiers:
		MethodModifier
	|	MethodModifiers MethodModifier
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
		ExceptionType
		ExceptionTypeList COMMA ExceptionType
	;
	
	ExceptionType:
		ClassType
	|	TypeVariable
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
		TypeParameters ? SimpleTypeName LPAREN FormalParameterList ? RPAREN
	;
	
	ConstructorModifiers:
		ConstructorModifier
	|	ConstructorModifiers ConstructorModifier
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
		NonWildTypeArguments ? THIS LPAREN ArgumentListopt RPAREN SEMICOLON
	|	NonWildTypeArguments ? SUPER LPAREN ArgumentListopt RPAREN SEMICOLON
	|	Primary DOT NonWildTypeArguments ? SUPER LPAREN ArgumentList ? RPAREN SEMICOLON
	;
	
	NonWildTypeArguments:
		LESS_THAN ReferenceTypeList GREATER_THAN
	;
	
	ReferenceTypeList:
		ReferenceType
	|	ReferenceTypeList COMMA ReferenceType
	;
	
/* 8.9 Enums */

	EnumDeclaration:
		ClassModifiers ? ENUM Identifier Interfaces ? EnumBody
	;
	
	EnumBody:
		LCURLY EnumConstants ? COMMA ? EnumBodyDeclarations ? RCURLY
	;
	
	EnumConstants:
		EnumConstant
	|	EnumConstants COMMA EnumConstant
	;
	
	EnumConstant:
		Annotations Identifier Arguments ? ClassBody ?
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
		InterfaceModifier
	|	InterfaceModifiers InterfaceModifier
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
		InterfaceMemberDeclaration
	|	InterfaceMemberDeclarations InterfaceMemberDeclaration
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
		ConstantModifier
	|	ConstantModifers ConstantModifier
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
		AbstractMethodModifier
	|	AbstractMethodModifiers AbstractMethodModifier
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
		AnnotationTypeElementDeclaration
	|	AnnotationTypeElementDeclarations AnnotationTypeElementDeclaration
	;
	
	AnnotationTypeElementDeclaration:
		AbstractMethodModifiers ? Type Identifier LPAREN RPAREN DefaultValue ? SEMICOLON
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
		Annotation
	|	Annotations Annotation
	;
	
	Annotation:
		NormalAnnotation
	|	MarkerAnnotation
	|	SingleElementAnnotation
	;
	
	NormalAnnotation:
		AT TypeName LPAREN ElementValuePairs ? RPAREN
	;

	ElementValuePairs:
		ElementValuePair
	|	ElementValuePairs COMMA ElementValuePair
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
		ElementValue
	|	ElementValues COMMA ElementValue
	;
	
	MarkerAnnotation:
		AT TypeName
	;
	
	SingleElementAnnotation:
		AT TypeName LPAREN ElementValue RPAREN
	;

/*********
 10 Arrays
 *********/
 
 /* 10.6 Array Initializers */
 
 	ArrayInitializer:
		LCURLY VariableInitializers ? COMMA ? RCURLY
	;
	
	VariableInitializers:
		VariableInitializer
	|	VariableInitializers COMMA VariableInitializer
	;

/************************	
 14 Blocks and Statements
 ************************/
 
/* 14.2 Blocks */

	Block:
		LCURLY BlockStatements ? RCURLY
	;
	
	BlockStatements:
		BlockStatement
	|	BlockStatements BlockStatement
	;
	
	BlockStatement:
		LocalVariableDeclarationStatement
	|	ClassDeclaration
	|	Statement
	;
	
/* 14.4 Local Variable Declaration Statements */

	LocalVariableDeclarationStatement:
		LocalVariableDeclaration SEMICOLON
	;
	
	LocalVariableDeclaration:
		VariableModifiers Type VariableDeclarators
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
		IF LPAREN Expression LPAREN StatementNoShortIf ELSE StatementNoShortIf
	;
		
/* 14.10 The assert Statement */

	AssertStatement:
		ASSERT Expression1 SEMICOLON
	|	ASSERT Expression1 COLON Expression2 SEMICOLON
	;
	
/* 14.11 The switch Statement */

	SwitchStatement:
		SWITCH LPAREN Expression RPAREN SwitchBlock
	;
	
	SwitchBlock:
		LCURLY SwitchBlockStatementGroups ? SwitchLabelsopt RCURLY
	;
	
	SwitchBlockStatementGroups:
		SwitchBlockStatementGroup
	|	SwitchBlockStatementGroups SwitchBlockStatementGroup
	;
	
	SwitchBlockStatementGroup:
		SwitchLabels BlockStatements
	;
	
	SwitchLabels:
		SwitchLabel
	|	SwitchLabels SwitchLabel
	;
	
	SwitchLabel:
		CASE ConstantExpression COLON
	|	CASE EnumConstantName COLON
	|	DEFAULT COLON
	;
		
	EnumConstantName:
		Identifier
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
		FOR LPAREN ForInitopt SEMICOLON Expression ? SEMICOLON ForUpdate ? RPAREN
	|	StatementNoShortIf
	;
	
	ForInit:
		StatementExpressionList
	|	LocalVariableDeclaration
	;
	
	ForUpdate:
		StatementExpressionList
	;
	
	StatementExpressionList:
		StatementExpression
	|	StatementExpressionList COMMA StatementExpression
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
	;
	
	Catches:
		CatchClause
	|	Catches CatchClause
	;
	
	CatchClause:
		CATCH LPAREN FormalParameter RPAREN Block
	;
	
	Finally:
		FINALLY Block
	;
	
/**************
 15 Expressions
 **************/
