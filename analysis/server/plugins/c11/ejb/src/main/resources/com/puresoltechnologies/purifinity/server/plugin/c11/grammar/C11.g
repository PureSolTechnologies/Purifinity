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
	//preprocessor="com.puresoltechnologies.purifinity.coding.lang.cpp.CPreprocessor";								// usage of preprocessor required? 
	lexer="com.puresoltechnologies.parsers.lexer.RegExpLexer";
	parser="com.puresoltechnologies.parsers.parser.packrat.PackratParser";
	parser.backtracking=true;

/****************************************************************************
 * H E L P E R
 ****************************************************************************/ 
HELPER

	/*
	 * 6.4.9 Comments
	 */
		
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
	
	InputCharacter: "[^\\n\\r]" ;

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
        s-char*
    ;
    
    s-char:
        "[^\"\\\\\\n]"
    |   escape-sequence
    ;

    /*
     * 6.4.7 Header names
     */
    h-char-sequence:
        h-char+
    ;
    
    h-char: "[^\\n>]";
    
    q-char-sequence:
        q-char+
    ;
    
    q-char: "[^\\n\"]";

 /****************************************************************************
 * T O K E N S
 ****************************************************************************/ 
 TOKENS
 
 	WhiteSpace:
		"( |\\t|\\f)" [ignore]
	;
 
    LineTerminator:
        "(\\n|\\r\\n|\\r)" [ignore]
    ;

    LineConcatenation:
        BACKSLASH LineTerminator [ignore]
    ;
 
    /*
 	 * 6.4.9 Comments
 	 */
 	Comment:
		"(" TraditionalComment "|" EndOfLineComment ")" [ignore]
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
	BACKSLASH: "\\\\" ;
 
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

    PP_IF : "#\\s*if(?!\\w)";
    PP_IFDEF : "#\\s*ifdef(?!\\w)";
    PP_IFNDEF : "#\\s*ifndef(?!\\w)";
    PP_ELIF : "#\\s*elif(?!\\w)";
    PP_ELSE : "#\\s*else(?!\\w)";
    PP_ENDIF : "#\\s*endif(?!\\w)";
    PP_INCLUDE : "#\\s*include(?!\\w)";
    PP_DEFINE : "#\\s*define(?!\\w)";
    PP_UNDEF : "#\\s*undef(?!\\w)";
    PP_LINE : "#\\s*line(?!\\w)";
    PP_ERROR : "#\\s*error(?!\\w)";
    PP_PRAGMA : "#\\s*pragma(?!\\w)";
    
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
        encoding-prefix? "\"" s-char-sequence "\""
    ;

    /*
     * 6.4.7 Header names
     */
    header-name:
        "<" h-char-sequence ">"
    |   "\"" q-char-sequence "\""
    ;

    /*
     * 6.4.8 Preprocessing numbers
     */
    pp-number:
        "(" digit "|\\." digit ")" "(" digit "|" identifier-nondigit "|[eEpP]" sign "|\\.)*"
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
        translation-unit 
 	;

    /**********************
     * 6.4 Lexical elements
     **********************/
    
    token:
        keyword
	|	identifier
	|	constant
	|	string-literal
	|	punctuator
	;

	preprocessing-token:
		header-name
	|	identifier
	|	pp-number
	|	character-constant
	|	string-literal
	|	punctuator
	|	"."
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

    /*****************
     * 6.5 Expressions
     *****************/
     
    primary-expression:
        identifier
    |   constant
    |   string-literal
    |   LPAREN expression RPAREN
    |   generic-selection
    ;

    generic-selection:
        _GENERIC LPAREN assignment-expression COMMA generic-assoc-list RPAREN
    ;
    
    generic-assoc-list:
        generic-association
    |   generic-assoc-list COMMA generic-association
    ;
    
    generic-association:
        type-name COLON assignment-expression
    |   DEFAULT COLON assignment-expression
    ;
    
    postfix-expression:
        primary-expression
    |   postfix-expression LRECTANGULAR expression RRECTANGULAR
    |   postfix-expression LPAREN argument-expression-list? RPAREN
    |   postfix-expression COMMA identifier
    |   postfix-expression MINUS GREATER_THAN identifier
    |   postfix-expression PLUS PLUS
    |   postfix-expression MINUS MINUS
    |   LPAREN type-name RPAREN LCURLY initializer-list RCURLY
    |   LPAREN type-name RPAREN LCURLY initializer-list COMMA RCURLY
    ;
    
    argument-expression-list:
        assignment-expression
    |   argument-expression-list COMMA assignment-expression
    ;
    
    unary-expression:
        postfix-expression
    |   PLUS PLUS unary-expression
    |   MINUS MINUS unary-expression
    |   unary-operator cast-expression
    |   SIZEOF unary-expression
    |   LPAREN type-name RPAREN
    |   _ALIGNOF LPAREN type-name RPAREN
    ;
        
    unary-operator: 
        AMPERSAND
    |   STAR
    |   PLUS
    |   MINUS
    |   TILDE
    |   EXCLAMATION_MARK
    ;
    
    cast-expression:
        unary-expression
    |   LPAREN type-name RPAREN cast-expression
    ;
    
    multiplicative-expression:
        cast-expression
    |   multiplicative-expression STAR cast-expression
    |   multiplicative-expression SLASH cast-expression
    |   multiplicative-expression PERCENT cast-expression
    ;
    
    additive-expression:
        multiplicative-expression
    |   additive-expression PLUS multiplicative-expression
    |   additive-expression MINUS multiplicative-expression
    ;
    
    shift-expression:
        additive-expression
    |   shift-expression LESS_THAN LESS_THAN additive-expression
    |   shift-expression GREATER_THAN GREATER_THAN additive-expression
    ;
    
    relational-expression:
        shift-expression
    |   relational-expression LESS_THAN shift-expression
    |   relational-expression GREATER_THAN shift-expression
    |   relational-expression LESS_THAN EQUALS shift-expression
    |   relational-expression GREATER_THAN EQUALS shift-expression
    ;
    
    equality-expression:
        relational-expression
    |   equality-expression EQUALS EQUALS relational-expression
    |   equality-expression EXCLAMATION_MARK EQUALS relational-expression
    ;
    
    AND-expression:
        equality-expression
    |   AND-expression AMPERSAND equality-expression
    ;
    
    exclusive-OR-expression:
        AND-expression
    |   exclusive-OR-expression CARET AND-expression
    ;
    
    inclusive-OR-expression:
        exclusive-OR-expression
    |   inclusive-OR-expression VERTICAL_BAR exclusive-OR-expression
    ;
    
    logical-AND-expression:
        inclusive-OR-expression
    |   logical-AND-expression AMPERSAND AMPERSAND inclusive-OR-expression
    ;

    logical-OR-expression:
        logical-AND-expression
    |   logical-OR-expression VERTICAL_BAR VERTICAL_BAR logical-AND-expression
    ;
    
    conditional-expression:
        logical-OR-expression
    |   logical-OR-expression QUESTION_MARK expression COLON conditional-expression
    ;
    
    assignment-expression:
        conditional-expression
    |   unary-expression assignment-operator assignment-expression
    ;
    
    assignment-operator: 
        EQUALS
    |   STAR EQUALS
	|   SLASH EQUALS
	|   PERCENT EQUALS
	|   PLUS EQUALS
    |   MINUS EQUALS
    |   LESS_THAN LESS_THAN EQUALS
    |   GREATER_THAN GREATER_THAN EQUALS
    |   AMPERSAND EQUALS
    |   CARET EQUALS
    |   VERTICAL_BAR EQUALS
    ;
    
    expression:
        assignment-expression
    |   expression COMMA assignment-expression
    ;
    
    /**************************
     * 6.6 Constant expressions
     **************************/
    
    constant-expression:
        conditional-expression
    ;
    
    /******************
     * 6.7 Declarations
     ******************/
     
     declaration:
          declaration-specifiers init-declarator-list? SEMICOLON
     |    static_assert-declaration
     ;
     
     declaration-specifiers:
          storage-class-specifier declaration-specifiers?
     |    type-specifier declaration-specifiers?
     |    type-qualifier declaration-specifiers?
     |    function-specifier declaration-specifiers?
     |    alignment-specifier declaration-specifiers?
     ;
     
     init-declarator-list:
          init-declarator
     |    init-declarator-list COMMA init-declarator
     ;
     
     init-declarator:
          declarator
     |    declarator EQUALS initializer
     ;
     
     storage-class-specifier:
          TYPEDEF
     |    EXTERN
     |    STATIC
     |    _THREAD_LOCAL
     |    AUTO
     |    REGISTER
     ;
    
    type-specifier:
        VOID
    |   CHAR
    |   SHORT
    |   INT
    |   LONG
    |   FLOAT
    |   DOUBLE
    |   SIGNED
    |   UNSIGNED
    |   _BOOL
    |   _COMPLEX
    |   atomic-type-specifier
    |   struct-or-union-specifier
    |   enum-specifier
    |   typedef-name
    ;
    
    struct-or-union-specifier:
        struct-or-union identifier? LCURLY struct-declaration-list RCURLY
    |   struct-or-union identifier
    ;

    struct-or-union:
        STRUCT
    |   UNION
    ;
    
    struct-declaration-list:
        struct-declaration
    |   struct-declaration-list struct-declaration
    ;
    
    struct-declaration:
        specifier-qualifier-list struct-declarator-list? SEMICOLON
    |   static_assert-declaration
    ;
    
    specifier-qualifier-list:
        type-specifier specifier-qualifier-list?
    |   type-qualifier specifier-qualifier-list?
    ;
    
    struct-declarator-list:
        struct-declarator
    |   struct-declarator-list COMMA struct-declarator
    ;
    
    struct-declarator:
        declarator
        declarator? COLON constant-expression
    ;
    
    enum-specifier:
        ENUM identifier? LCURLY enumerator-list RCURLY
    |   ENUM identifier? LCURLY enumerator-list COMMA RCURLY
    |   ENUM identifier
    ;
    
    enumerator-list:
        enumerator
    |   enumerator-list COMMA enumerator
    ;
    
    enumerator:
        enumeration-constant
    |   enumeration-constant EQUALS constant-expression
    ;
    
    atomic-type-specifier:
        _ATOMIC LPAREN type-name RPAREN
    ;
    
    type-qualifier:
        CONST
    |   RESTRICT
    |   VOLATILE
    |   _ATOMIC
    ;
    
    function-specifier:
        INLINE
    |   _NORETURN
    ;
    
    alignment-specifier:
        _ALIGNAS LPAREN type-name RPAREN
    |   _ALIGNAS LPAREN constant-expression RPAREN
    ;
    
    declarator:
        pointer? direct-declarator
    ;
    
    direct-declarator:
        identifier
    |   LPAREN declarator RPAREN
    |   direct-declarator LRECTANGULAR type-qualifier-list? assignment-expression? RRECTANGULAR
    |   direct-declarator LRECTANGULAR STATIC type-qualifier-list? assignment-expression RRECTANGULAR
    |   direct-declarator LRECTANGULAR type-qualifier-list STATIC assignment-expression RRECTANGULAR
    |   direct-declarator LRECTANGULAR type-qualifier-list? STAR RRECTANGULAR
    |   direct-declarator LPAREN parameter-type-list RPAREN
    |   direct-declarator LPAREN identifier-list? RPAREN
    ;
    
    pointer:
        STAR type-qualifier-list?
    |   STAR type-qualifier-list? pointer
    ;
    
    type-qualifier-list:
        type-qualifier
    |   type-qualifier-list type-qualifier
    ;
    
    parameter-type-list:
        parameter-list
    |   parameter-list COMMA DOT DOT DOT
    ;
    
    parameter-list:
        parameter-declaration
    |   parameter-list COMMA parameter-declaration
    ;
       
    parameter-declaration:
        declaration-specifiers declarator
    |   declaration-specifiers abstract-declarator?
    ;
    
    identifier-list:
        identifier
    |   identifier-list COMMA identifier
    ;
    
    type-name:
        specifier-qualifier-list abstract-declarator?
    ;
    
    abstract-declarator:
        pointer
    |   pointer? direct-abstract-declarator
    ;
    
    direct-abstract-declarator:
        LPAREN abstract-declarator RPAREN
    |   direct-abstract-declarator? LRECTANGULAR type-qualifier-list? assignment-expression? RRECTANGULAR
    |   direct-abstract-declarator? LRECTANGULAR STATIC type-qualifier-list? assignment-expression RRECTANGULAR
    |   direct-abstract-declarator? LRECTANGULAR type-qualifier-list STATIC assignment-expression RRECTANGULAR
    |   direct-abstract-declarator? LRECTANGULAR STAR RRECTANGULAR
    |   direct-abstract-declarator? LPAREN parameter-type-list? RPAREN
    ;
    
    typedef-name:
        identifier
    ;
    
    initializer:
        assignment-expression
    |   LCURLY initializer-list RCURLY
    |   LCURLY initializer-list COMMA RCURLY
    ;
    
    initializer-list:
        designation? initializer
    |   initializer-list COMMA designation? initializer
    ;
    
    designation:
        designator-list EQUALS
    ;
    
    designator-list:
        designator
    |   designator-list designator
    ;
    
    designator:
        LRECTANGULAR constant-expression RRECTANGULAR
    |   DOT identifier
    ;
    
    static_assert-declaration:
        _STATIC_ASSERT LPAREN constant-expression COMMA string-literal RPAREN SEMICOLON
    ;
    
    /***************************
     * 6.8 Statements and blocks
     ***************************/
    
    statement:
        labeled-statement
    |   compound-statement
    |   expression-statement
    |   selection-statement
    |   iteration-statement
    |   jump-statement
    ;
    
    labeled-statement:
        identifier COLON statement
    |   CASE constant-expression COLON statement
    |   statement
    ;
    
    compound-statement:
        LCURLY block-item-list? RCURLY
    ;
    
    block-item-list:
        block-item
    |   block-item-list block-item
    ;
    
    block-item:
        declaration
    |   statement
    ;
    
    expression-statement:
        expression? SEMICOLON
    ;
    
    selection-statement:
        IF LPAREN expression RPAREN statement
    |   IF LPAREN expression RPAREN statement ELSE statement
    |   SWITCH LPAREN expression RPAREN statement
    ;
    
    iteration-statement:
        WHILE LPAREN expression RPAREN statement
    |   DO statement WHILE LPAREN expression RPAREN SEMICOLON
    |   FOR LPAREN expression? SEMICOLON expression? SEMICOLON expression? RPAREN statement
    |   FOR LPAREN declaration expression SEMICOLON expression RPAREN statement
    ;
    
    jump-statement:
        GOTO identifier SEMICOLON
    |   CONTINUE SEMICOLON
    |   BREAK SEMICOLON
    |   RETURN expression? SEMICOLON
    ;

    /**************************
     * 6.9 External definitions
     **************************/
    
    translation-unit:
        external-declaration
    |   translation-unit external-declaration
    ;
    
    external-declaration:
        function-definition
    |   declaration
    ;
    
    function-definition:
        declaration-specifiers declarator declaration-list? compound-statement
    ;
    
    declaration-list:
        declaration
    |   declaration-list declaration
    ;
    
    /*******************************
     * 6.10 Preprocessing directives
     *******************************/
    
    preprocessing-file:
        group?
    ;
    
    group:
        group-part
    |   group group-part
    ;
    
    group-part:
        if-section
    |   control-line
    |   non-directive-line // added for better separation of group parts
    |   text-line
    ;
    
    if-section:
        if-group elif-groups ? else-group ? endif-line
    ;
    
    if-group:
        PP_IF constant-expression new-line group ?
    |   PP_IFDEF identifier new-line group ?
    |   PP_IFNDEF identifier new-line group ?
    ;
    
    elif-groups:
        elif-group
    |   elif-groups elif-group
    ;
    
    elif-group:
        PP_ELIF constant-expression new-line group?
    ;
    
    else-group:
        PP_ELSE new-line group?
    ;
    
    endif-line:
        PP_ENDIF new-line
    ;
    
    control-line:
        PP_INCLUDE pp-tokens new-line
    |   PP_DEFINE identifier LPAREN RPAREN replacement-list new-line
    |   PP_DEFINE identifier LPAREN identifier-list RPAREN replacement-list new-line
    |   PP_DEFINE identifier LPAREN DOT DOT DOT RPAREN replacement-list new-line
    |   PP_DEFINE identifier LPAREN identifier-list COMMA DOT DOT DOT RPAREN replacement-list new-line
    |   PP_DEFINE identifier replacement-list new-line
    |   PP_UNDEF identifier new-line
    |   PP_LINE pp-tokens new-line
    |   PP_ERROR pp-tokens? new-line
    |   PP_PRAGMA pp-tokens? new-line
    |   SHARP new-line
    ;
    
    text-line:
        pp-tokens? new-line
    ;
    
    non-directive-line :
        SHARP non-directive
    ;
    
    non-directive:
        pp-tokens new-line
    ;
    
    replacement-list:
        pp-tokens?
    ;
    
    pp-tokens:
        preprocessing-token
    |   pp-tokens preprocessing-token
    ;
    
    new-line:
        LineTerminator
    ;
    