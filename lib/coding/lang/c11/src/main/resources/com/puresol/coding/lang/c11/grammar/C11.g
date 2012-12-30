/****************************************************************************
 * C 11 Grammar File for Nyota Uhura
 * (c) by 2012 PureSol Technologies
 * Author: Rick-Rainer Ludwig
 ****************************************************************************/ 

/*
 * This grammar was extracted from the OpenStd.org's document n1570.pdf which
 * contains the draft for C11.
 */

/****************************************************************************
 * O P T I O N S
 ****************************************************************************/ 
OPTIONS
	grammar.name="C 11";
	grammar.checks=true;
	grammar.ignore-case=false;
	grammar.ignored-leading=true;
	grammar.normalize_to_bnf=true;					// defines whether the grammar needs to be normalized to BNF
	preprocessor.use=false;							// usage of preprocessor required? 
	//preprocessor="com.puresol.coding.lang.cpp.CPreprocessor";								// usage of preprocessor required? 
	lexer="com.puresol.uhura.lexer.RegExpLexer";
	parser="com.puresol.uhura.parser.packrat.PackratParser";
	parser.backtracking=true;

/****************************************************************************
 * H E L P E R
 ****************************************************************************/ 
HELPER

    /*
     * 6.4.2 Identifiers
     */
    identifier-nondigit:
        nondigit
    |   universal-character-name
    // |   other implementation-defined characters
    ;
    
    nondigit:
		"[_a-zA-Z]"
    ;

    digit:
        "[0-9]" 
    ;

    /*
     * 6.4.3 Universal character names
     */
    universal-character-name:
        "\\\\u" hex-quad
    |   "\\\\U" hex-quad hex-quad
    ;
    
    hex-quad:
        hexadecimal-digit hexadecimal-digit hexadecimal-digit hexadecimal-digit
    ;

    /*
     * 6.4.4.1 Integer constants
     */
    decimal-constant:
        nonzero-digit digit*
    ;

    octal-constant:
        '0' octal-digit*
    ;
    
    hexadecimal-constant:
        hexadecimal-prefix hexadecimal-digit+
    ;
    
    hexadecimal-prefix: "0[xX]";
    nonzero-digit: "[1-9]";
    octal-digit: "[0-7]";
	hexadecimal-digit: "[0-9a-fA-F]";

    integer-suffix:
        unsigned-suffix long-suffix?
    |   unsigned-suffix long-long-suffix
    |   long-suffix unsigned-suffix?
    |   long-long-suffix unsigned-suffix?
    ;
    
    unsigned-suffix: "[uU]";
    long-suffix: "[lL]";
    long-long-suffix: "(ll|LL)";

    /*
     * 6.4.4.2 Floating constants
     */
    decimal-floating-constant:
        fractional-constant exponent-part? floating-suffix?
    |   digit-sequence exponent-part floating-suffix?
    ;
    
    hexadecimal-floating-constant:
        hexadecimal-prefix hexadecimal-fractional-constant binary-exponent-part floating-suffix?
    |   hexadecimal-prefix hexadecimal-digit-sequence binary-exponent-part floating-suffix?
    ;
    
    fractional-constant:
        digit-sequence? '.' digit-sequence
    |   digit-sequence '.'
    ;
    
    exponent-part:
        "[eE]" sign? digit-sequence
    ;

    sign: "[+-]";
    digit-sequence:
        digit+
    ;
    
    hexadecimal-fractional-constant:
        hexadecimal-digit-sequence? '.' hexadecimal-digit-sequence
    |   hexadecimal-digit-sequence '.'
    ;
    
    binary-exponent-part:
        "[pP]" sign? digit-sequence
    ;
    
    hexadecimal-digit-sequence:
        hexadecimal-digit+
    ;
    
    floating-suffix: "[flFL]";
   
    /*
     * 6.4.4.4 Character constants
     */
    c-char-sequence:
        c-char+
    ;
    
    c-char:
        "[^'\\\\\\n]"
    |   escape-sequence
    ;
    
    escape-sequence:
        simple-escape-sequence
    |   octal-escape-sequence
    |   hexadecimal-escape-sequence
    |   universal-character-name
    ;
    
    simple-escape-sequence: "\\\\['\\\\\"?\\\\abfnrtv]";

    octal-escape-sequence:
        "\\\\" octal-digit
    |   "\\\\" octal-digit octal-digit
    |   "\\\\" octal-digit octal-digit octal-digit
    ;
    
    hexadecimal-escape-sequence:
        "\\\\x" hexadecimal-digit+
    ;

    /*
     * 6.4.5 String literals
     */
    encoding-prefix: "(u8|[uUL])";

    s-char-sequence:
        s-char+
    ;
    
    s-char:
        "[^\\\\\"\\\\\\\\n]"
    |   escape-sequence
    ;

 /****************************************************************************
 * T O K E N S
 ****************************************************************************/ 
 TOKENS
 
 	WhiteSpace:
		"( |\\t|\\f)" [ignore]
	;
 
 	/*
	 * 6.4.6 Punctuators
	 */
	LRECTANGULAR: "\\[" ;
	RRECTANGULAR: "\\]" ;
	LPAREN: "\\(" ;
	RPAREN: "\\)" ;
	LCURLY: "\\{" ;
	RCURLY: "\\}" ;
	DOT: "\\." ;
	AMPERSAND: "&" ;
	STAR: "\\*" ;
	PLUS: "\\+" ;
	MINUS: "-";
	TILDE : "~" ;
	EXCLAMATION_MARK: "!" ;
	SLASH: "/" ;
	PERCENT: "%";
	LESS_THAN: "<" ;
	GREATER_THAN: ">" ;
	CARET: "\\^" ;
	VERTICAL_BAR : "\\|" ;
	QUESTION_MARK : "\\?" ;
	COLON: ":" ;
	SEMICOLON: ";" ;
	EQUALS: "=" ;
	COMMA: "," ;
	SHARP: "#" ;
 
    /*
     * 6.4.1 Keywords
     */
    AUTO           : "auto(?!\\w)" ;
    BREAK          : "break(?!\\w)" ;
    CASE           : "case(?!\\w)" ;
    CHAR           : "char(?!\\w)" ;
    CONST          : "const(?!\\w)" ;
    CONTINUE       : "continue(?!\\w)" ;
    DEFAULT        : "default(?!\\w)" ;
    DO             : "do(?!\\w)" ;
    DOUBLE         : "double(?!\\w)" ;
    ELSE           : "else(?!\\w)" ;
    ENUM           : "enum(?!\\w)" ;
    EXTERN         : "extern(?!\\w)" ;
    FLOAT          : "float(?!\\w)" ;
    FOR            : "for(?!\\w)" ;
    GOTO           : "goto(?!\\w)" ;
    IF             : "if(?!\\w)" ;
    INLINE         : "inline(?!\\w)" ;
    INT            : "int(?!\\w)" ;
    LONG           : "long(?!\\w)" ;
    REGISTER       : "register(?!\\w)" ;
    RESTRICT       : "restrict(?!\\w)" ;
    RETURN         : "return(?!\\w)" ;
    SHORT          : "short(?!\\w)" ;
    SIGNED         : "signed(?!\\w)" ;
    SIZEOF         : "sizeof(?!\\w)" ;
    STATIC         : "static(?!\\w)" ;
    STRUCT         : "struct(?!\\w)" ;
    SWITCH         : "switch(?!\\w)" ;
    TYPEDEF        : "typedef(?!\\w)" ;
    UNION          : "union(?!\\w)" ;
    UNSIGNED       : "unsigned(?!\\w)" ;
    VOID           : "void(?!\\w)" ;
    VOLATILE       : "volatile(?!\\w)" ;
    WHILE          : "while(?!\\w)" ;
    _ALIGNAS       : "_Alignas(?!\\w)" ;
    _ALIGNOF       : "_Alignof(?!\\w)" ;
    _ATOMIC        : "_Atomic(?!\\w)" ;
    _BOOL          : "_Bool(?!\\w)" ;
    _COMPLEX       : "_Complex(?!\\w)" ;
    _GENERIC       : "_Generic(?!\\w)" ;
    _IMAGINARY     : "_Imaginary(?!\\w)" ;
    _NORETURN      : "_Noreturn(?!\\w)" ;
    _STATIC_ASSERT : "_Static_assert(?!\\w)" ;
    _THREAD_LOCAL  : "_Thread_local(?!\\w)" ;

    /*
     * 6.4.4.1 Integer constants
     */
    integer-constant:
        decimal-constant integer-suffix?
    |   octal-constant integer-suffix?
    |   hexadecimal-constant integer-suffix?
    ;
    
    /*
     * 6.4.4.2 Floating constants
     */
    floating-constant:
        decimal-floating-constant
    |   hexadecimal-floating-constant
    ;
    
    /*
     * 6.4.4.3 Enumeration constants
     */
    enumeration-constant:
        identifier
    ;
    
    /*
     * 6.4.4.4 Character constants
     */
    character-constant:
        "'" c-char-sequence "'"
    |   "L'" c-char-sequence "'"
    |   "u'" c-char-sequence "'"
    |   "U'" c-char-sequence "'"
    ;

    /*
     * 6.4.5 String literals
     */
    string-literal:
        encoding-prefix? "\\\"" s-char-sequence? "\\\""
    ;

    /*
     * 6.4.2 Identifiers
     */
    identifier :
        identifier-nondigit "(" identifier-nondigit "|" digit ")*"
    ;

/****************************************************************************
 * P R O D U C T I O N S
 ****************************************************************************/ 
 PRODUCTIONS
 
 	_START_ : 
 		// TODO 
 	;

    /*
     * 6.4 Lexical elements
     */
    
    token :
        keyword
	|	identifier
	|	constant
	|	string-literal
	|	punctuator
	;

    punctuator :
        LRECTANGULAR
    |	RRECTANGULAR
    |	LPAREN
    |	RPAREN
    |	LCURLY
    |	RCURLY
    |	DOT
    |	AMPERSAND
    |	STAR
    |	PLUS
    |	MINUS
    |	TILDE
    |	EXCLAMATION_MARK
    |	SLASH
    |	PERCENT
    |	LESS_THAN
    |	GREATER_THAN
    |	CARET
    |	VERTICAL_BAR
    |	QUESTION_MARK
    |	COLON
    |	SEMICOLON
    |	EQUALS
    |	COMMA
    |	SHARP
   ;
    
    /*
     * 6.4.1 Keywords
     */
    keyword :
        AUTO
    |   BREAK
    |   CASE
    |   CHAR
    |   CONST
    |   CONTINUE
    |   DEFAULT
    |   DO
    |   DOUBLE
    |   ELSE
    |   ENUM
    |   EXTERN
    |   FLOAT
    |   FOR
    |   GOTO
    |   IF
    |   INLINE
    |   INT
    |   LONG
    |   REGISTER
    |   RESTRICT
    |   RETURN
    |   SHORT
    |   SIGNED
    |   SIZEOF
    |   STATIC
    |   STRUCT
    |   SWITCH
    |   TYPEDEF
    |   UNION
    |   UNSIGNED
    |   VOID
    |   VOLATILE
    |   WHILE
    |   _ALIGNAS
    |   _ALIGNOF
    |   _ATOMIC
    |   _BOOL
    |   _COMPLEX
    |   _GENERIC
    |   _IMAGINARY
    |   _NORETURN
    |   _STATIC_ASSERT
    |   _THREAD_LOCAL
    ;
    
    /*
     * 6.4.4 Constants
     */
    constant:
        integer-constant
    |   floating-constant
    |   enumeration-constant
    |   character-constant
    ;
