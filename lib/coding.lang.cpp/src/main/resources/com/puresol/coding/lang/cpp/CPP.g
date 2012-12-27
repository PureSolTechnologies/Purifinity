/****************************************************************************
 * C Preprocessor Grammar File for Nyota Uhura
 * (c) by 2012 PureSol Technologies
 * Author: Rick-Rainer Ludwig
 ****************************************************************************/ 

/****************************************************************************
 * O P T I O N S
 ****************************************************************************/ 
OPTIONS
	grammar.name="C Preprocessor";
	grammar.checks=true;
	grammar.ignore-case=false;
	grammar.ignored-leading=true;
	grammar.normalize_to_bnf=true;					// defines whether the grammar needs to be normalized to BNF
	preprocessor.use=false;							// usage of preprocessor required? 
	preprocessor="";								// usage of preprocessor required? 
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
		"(" NotStar "+|\\*+(?!/))*\\*/"
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
		Letter LetterOrDigit "*"
	;
		
	Letter:
		"[a-zA-Z_]"
	;
		
	LetterOrDigit:
		"[a-zA-Z0-9_]"
	;

	/*************
	 3.10 Literals
	 *************/

	/* 3.10.1 Integer Literals */
	
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
		
	HexSignificand:
	"("	HexNumeral
	"|"	HexNumeral "\\."
	"|"	"0[xX](" HexDigits ")?\\." HexDigits
	")"
	;
	
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
		
 /****************************************************************************
 * T O K E N S
 ****************************************************************************/ 
 TOKENS

	LineTerminator:
		"(\\n|\\r|\\r\\n)" [ignore]
	;

	WhiteSpace:
		"( |\\t|\\f)" [ignore]
	;
	
	Comment:
		"(" TraditionalComment "|" EndOfLineComment ")" [ignore]
	;

	/**************
	 Keywords
	 **************/

	INCLUDE : "include(?!\\w)" ;
	DEFINE  : "define(?!\\w)" ;
	UNDEF   : "undef(?!\\w)" ;
	IF      : "if(?!\\w)" ;
	IFDEF   : "ifdef(?!\\w)" ;
	IFNDEF  : "ifndef(?!\\w)" ;
	ELSE    : "else(?!\\w)" ;
	ELIF    : "elif(?!\\w)" ;
	ENDIF   : "endif(?!\\w)" ;
	PRAGMA  : "pragma(?!\\w)" ;
	ERROR   : "error(?!\\w)" ;
	FILE    : "__FILE__(?!\\w)" ;
	LINE    : "__LINE__(?!\\w)" ;
	VA_ARGS : "__VA_ARGS__(?!\\w)" ;

	/*************
	 Literals
	 *************/
	StringLiteral:		
		"\"(" StringCharacters ")?\""
	;

	FileIncludeLiteral:		
		"\\<" StringCharacters "\\>"
	;
	
	SourceCodeLine:
	    ".*\\\\n" // This is a place holder! This token will be added by the PreprocessorTokenizer..,
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
	SHARP : "\\#" ;
	
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
 		SourceFile 
 	;

	SourceFile : 
		SourceLine *
	;

	SourceLine :
	    SourceCodeLine 
	|   Macro
	;

 	Macro :
 	    IncludeMacro
 	|   MacroDefinition
    |   ConditionDirective
    |   Pragma
    |   ErrorMacro
 	;
 	
 	IncludeMacro :
 	    SHARP INCLUDE IncludeFile 
 	;
 
 	IncludeFile :
 		FileIncludeLiteral 
 	|	StringLiteral
 	;
 	
 	MacroDefinition :
        DefineMacro
    |   UndefineMacro
 	;
 	
 	DefineMacro :
 	    DefineObjectLikeMacro
 	|   DefineFunctionLikeMacro
 	;
 	
 	
 	DefineObjectLikeMacro :
 	    SHARP DEFINE Identifier 
 	|   SHARP DEFINE Identifier ReplacementList
 	;
 	
 	DefineFunctionLikeMacro :
 	    SHARP DEFINE Identifier LPAREN ParameterList RPAREN ReplacementList
 	;

	ReplacementList :
	    Identifier ReplacementList
	|   Identifier
	;
	
	ParameterList :
	    Replacement COMMA ParameterList
	|   Replacement COMMA OptionalParameters
	|   OptionalParameters
	|   Replacement
	;

    Replacement:
        Identifier
    ;

	OptionalParameters :
	    DOT DOT DOT
	;

    UndefineMacro :
        SHARP UNDEF Identifier
    ;
 
 	ConditionDirective :
        IfDirective
 	|   IfDefDirective
 	|   IfNDefDirective
 	|   ElseDirective
	|   ElIfDirective
 	|   EndIfDirective
 	;

 	IfDirective :
 	    SHARP IF // TODO Condition
 	;
 	 
 	IfDefDirective :
 	    SHARP IFDEF // TODO Condition
 	;
 	 
 	IfNDefDirective :
 	    SHARP IFNDEF // TODO Condition
 	;
 	 
 	ElseDirective :
        SHARP ELSE
 	;
 	 
 	ElIfDirective :
        SHARP ELIF // TODO Condition
 	;
 	 
 	EndIfDirective :
        SHARP ENDIF
 	;
 	
 	Pragma :
 	    SHARP PRAGMA // TODO put in here the pragma content
 	;
 	
 	ErrorMacro :
 	    SHARP ERROR StringLiteral
 	;
 	