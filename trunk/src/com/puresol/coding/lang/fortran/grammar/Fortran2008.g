/****************************************************************************
 * Fortran 2008 Grammar File for Nyota Uhura
 * (c) by 2010 PureSol Technologies
 * Author: Rick-Rainer Ludwig
 ****************************************************************************/ 
 
/****************************************************************************
 * O P T I O N S
 ****************************************************************************/ 
 OPTIONS
	grammar.checks=false;
	lexer="com.puresol.uhura.lexer.RegExpLexer";
	lexer.case_sensitive=false;
	parser="com.puresol.uhura.parser.LR1Parser";
 
/****************************************************************************
 * H E L P E R
 ****************************************************************************/ 
 HELPER
 
 	/* **********************************
	 * 
	 * 3.1 Prozessor character set
	 * 
	 * *********************************
	 */

	/*
	 * 3.1.1 Characters
	 */
	ALPHANUMERIC_CHARACTER :
		 "(" LETTER "|" DIGIT "|" UNDERSCORE ")"
	;

	/*
	 * 3.1.2 Letters
	 */
	LETTER : "[A-Z]";

	/*
	 * 3.1.3 Digits
	 */
	DIGIT : "[0-9]";

	/*
	 * 3.1.4 Underscore
	 */
	UNDERSCORE : "_";

	/* **********************************
	 * 
	 * 3.2 Low-level syntax
	 * 
	 * *********************************
	 */

	/*
	 * 3.2.2 Names
	 */
	NAME : 
			LETTER LETTER ALPHANUMERIC_CHARACTER	"{0,61}"
	;

	/*
	 * 3.2.5 Statement labels
	 */
	LABEL : 
			DIGIT "{1,5}"
	;

	DELIMETERS : 
		"(" LEFT_PARENTHESIS 
		"|"	RIGHT_PARENTHESIS 
		"|"	SLASH 
		"|"	LEFT_SQUARE_BRACKET 
		"|"	RIGHT_SQUARE_BRACKET
		"|"	LEFT_PARENTHESIS SLASH
		"|"	SLASH RIGHT_PARENTHESIS 
		")"
	;

	/* *************************
	 * 
	 * 4 Types
	 * 
	 * ************************
	 */

	/*
	 * 4.4.2.2 Integer type
	 */

	SIGN : "[+-]";
	DIGIT_STRING : "\\d+";
	SIGNED_DIGIT_STRING : SIGN "?" DIGIT_STRING;

	SCALAR_INT_CONSTANT_NAME : NAME; // workaround!
	KIND_PARAM : "(" DIGIT_STRING "|"
			SCALAR_INT_CONSTANT_NAME ")";
	SIGNED_INT_LITERAL_CONSTANT : SIGN "?"
			INT_LITERAL_CONSTANT;

	/*
	 * 4.4.2.3 Real type
	 */
	EXPONENT : SIGNED_DIGIT_STRING;
	EXPONENT_LETTER : "[ED]";
	SIGNIFICANT : "(" DIGIT_STRING "\\.("
			DIGIT_STRING ")?|\\." DIGIT_STRING ")";
	SIGNED_REAL_LITERAL_CONSTANT : SIGN "?"
			REAL_LITERAL_CONSTANT;

	/*
	 * 4.4.2.4 Complex type
	 */
	NAMED_CONSTANT : NAME;
	IMAG_PART : "(" SIGNED_INT_LITERAL_CONSTANT
			"|" SIGNED_REAL_LITERAL_CONSTANT "|" NAMED_CONSTANT ")";
	REAL_PART : "(" SIGNED_INT_LITERAL_CONSTANT
			"|" SIGNED_REAL_LITERAL_CONSTANT "|" NAMED_CONSTANT ")";

	/*
	 * 4.4.3.2 Character type specifier
	 */

	REP_CHAR_SINGLE_QUOTE : "([^']+|'')";
	REP_CHAR_DOUBLE_QUOTE : "([^\"]+|\"\")";

	/*
	 * 4.4.4 Logical Type
	 */

	/* *****************************************
	 * 
	 * 4.7 Binary, octal, and hexadecimal literal constants
	 * 
	 * *****************************************
	 */

	HEX_DIGIT : "[0-9A-F]";
	HEX_CONSTANT : "(Z'" HEX_DIGIT "+'|Z\""
			HEX_DIGIT "+\")";
	OCTAL_CONSTANT : "(O'" DIGIT "+'|O\""
			DIGIT "+\")";
	BINARY_CONSTANT : "(B'" DIGIT "+'|B\""
			DIGIT "+\")";
 
/****************************************************************************
 * T O K E N S
 ****************************************************************************/ 
 TOKENS

	/*
	 * 3.1.5 Special characters
	 */
	BLANK : " ";
	SEMICOLON : ";";
	EQUALS : "=";
	EXCLAMATION_POINT : "!";
	PLUS : "\\+";
	QUOTATION_MARK_OR_QUOTE : "\"";
	MINUS : "-";
	PERCENT : "%";
	ASTERIK : "\\*";
	AMPERSAND : "&";
	SLASH : "/";
	TILDE : "~";
	BACKSLASH : "\\\\";
	LESS_THAN : "<";
	LEFT_PARENTHESIS : "\\(";
	GREATER_THAN : ">";
	RIGHT_PARENTHESIS : "\\)";
	QUESTION_MARK : "\\?";
	LEFT_SQUARE_BRACKET : "\\[";
	APOSTROPHE : "'";
	RIGHT_SQUARE_BRACKET : "\\]";
	GRAVE_ACCENT : "`";
	LEFT_CURLY_BRACKET : "\\{";
	CIRCUMFLEX_ACCENT : "\\^";
	RIGHT_CURLY_BRACKET : "\\}";
	VERTICAL_LINE : "\\|";
	COMMA : ",";
	CURRENCY_SYMBOL : "\\$";
	DECIMALPOINT_OR_PERIOD : "\\.";
	NUMBER_SIGN : "#";
	COLON : ":";
	COMMERCIAL_AT : "@";

	/*
	 * Keywords
	 */ 
	ABSTRACT : 'ABSTRACT' ;
	ALLOCATABLE : 'ALLOCATABLE' ;
	ALLOCATE : 'ALLOCATE' ;
	ASYNCHRONOUS : 'ASYNCHRONOUS' ;
	BIND : 'BIND' ;
	CHARACTER : 'CHARACTER' ;
	CLASS : 'CLASS' ; 
	CODIMENSION : 'CODIMENSION' ;
	COMMON : 'COMMON' ;
	COMPLEX : 'COMPLEX' ;
	CONTIGUOUS : 'CONTIGUOUS' ;
	DATA : 'DATA' ;
	DEALLOCATE : 'DEALLOCATE' ;
	DEFERRED : 'DEFERRED' ;
	DIMENSION : 'DIMENSION' ;
	DOUBLE_PRECISION : 'DOUBLE\\s+PRECISION' ;
	END_ENUM : 'END\\s+ENUM' ;
	END_TYPE : 'END\\s+TYPE' ;
	ENUM : 'ENUM' ;
	ENUMERATOR : 'ENUMERATOR' ;
	EQUIVALENCE : 'EQUIVALENCE' ;
	ERRMSG : 'ERRMSG' ;
	EXTENDS : 'EXTENDS' ;
	EXTERNAL : 'DIMENSION' ;
	FINAL : 'FINAL' ;
	GENERIC : 'GENERIC' ;
	IM : 'IM' ;
	IMPLICIT : 'IMPLICIT' ;
	IMPLICIT_NONE : 'IMPLICIT\\s+NONE' ;
	IN : 'IN' ;
	INOUT : 'INOUT' ;
	INTEGER : 'INTEGER' ;
	INTENT : 'INTENT'  ;
	INTRINSIC : 'INTRINSIC' ;
	KIND : 'KIND' ;
	LEN : 'LEN' ;
	LOGICAL : 'LOGICAL' ;
	MOLD : 'MOLD' ;
	NAME : 'NAME' ;
	NAMELIST : 'NAMELIST' ;
	NON_OVERRIDABLE : 'NON\\s+OVERRIDABLE' ;
	NOPASS : 'NOPASS' ;
	NULLIFY : 'NULLIFY' ;
	OPTIONAL : 'OPTIONAL' ;
	OUT : 'OUT' ;
	PASS : 'PASS' ;
	PARAMETER : 'PARAMETER' ;
	POINTER : 'POINTER' ;
	PRIVATE : 'PRIVATE' ;
	PROCEDURE : 'PROCEDURE' ; 
	PROTECTED : 'PROTECTED' ;
	PUBLIC : 'PUBLIC' ;
	RE : 'RE' ;
	REAL : 'REAL' ;
	SAVE : 'SAVE' ;
	SEQUENCE : 'SEQUENCE' ;
	SOURCE : 'SOURCE' ;
	STAT : 'STAT' ;
	TARGET : 'TARGET' ;
	TYPE : 'TYPE' ;
	VALUE : 'SAVE' ;
	VOLATILE : 'VOLATILE' ;


	NAME : LETTER ALPHANUMERIC_CHARACTER
			"{0,62}";
	INT_LITERAL_CONSTANT : DIGIT_STRING "(_"
			KIND_PARAM ")?";
	REAL_LITERAL_CONSTANT : "(" SIGNIFICANT "("
			EXPONENT_LETTER EXPONENT ")?(_" KIND_PARAM ")?|"
			DIGIT_STRING EXPONENT_LETTER EXPONENT "(_" KIND_PARAM
			")?)";
	COMPLEX_LITERAL_CONSTANT : "\\(" REAL_PART
			"\\s*,\\s*" IMAG_PART "\\)";
	CHAR_LITERAL_CONSTANT : "(" KIND_PARAM
			"_)?(\"" REP_CHAR_DOUBLE_QUOTE "*\"|'"
			REP_CHAR_SINGLE_QUOTE "*')";
	LOGICAL_LITERAL_CONSTANT : "(\\.TRUE\\.|\\.FALSE\\.)(_"
			KIND_PARAM ")?";
	BOZ_LITERAL_CONSTANT : "(" BINARY_CONSTANT
			"|" OCTAL_CONSTANT "|" HEX_CONSTANT ")";

/****************************************************************************
 * P R O D U C T I O N S
 ****************************************************************************/ 
 PRODUCTIONS
 
 	_START_ : 
 			program 
 	;

/***************
	Clause 1:	
 ***************/
 
 /*
 	Some essential definitions:
 	
	R101 xyz-list is xyz [ , xyz ] ...
	R102 xyz-name is name
	R103 scalar-xyz is xyz
	C101 (R103) scalar-xyz shall be scalar.
 */
 
/***************
	Clause 2:	
 ***************/

/*
	R201 program is program-unit
	[ program-unit ] ...
*/
	program : 
			program-unit +
	;
	
/*	
	R202 program-unit is main-program
	or external-subprogram
	or module
	or submodule
	or block-data
*/
	program-unit : 
		main-program
	| 	external-subprogram
	|	module
	|	submodule
	|	block-data
	;
	
/*
	R203 external-subprogram is function-subprogram
	or subroutine-subprogram
*/
	external-subprogram :
		function-subprogram
	|	subroutine-subprogram
	;

/*
	R204 specification-part is [ use-stmt ] ...
	[ import-stmt ] ...
	[ implicit-part ]
	[ declaration-construct ] ...
*/
	specification-part :
		use-stmt *
		import-stmt *
		implicit-part ?
		declaration-construct *
	;

/*
	R205 implicit-part is [ implicit-part-stmt ] ...
	implicit-stmt
*/
	implicit-part : 
		implicit-part-stmt *
		implicit-stmt
	;

/*
	R206 implicit-part-stmt is implicit-stmt
	or parameter-stmt
	or format-stmt
	or entry-stmt
*/
	implicit-part-stmt :
		implicit-stmt
	|	parameter-stmt
	|	format-stmt
	|	entry-stmt
	;

/*
	R207 declaration-construct is derived-type-def
	or entry-stmt
	or enum-def
	or format-stmt
	or interface-block
	or parameter-stmt
	or procedure-declaration-stmt
	or other-specification-stmt
	or type-declaration-stmt
	or stmt-function-stmt
*/
	declaration-construct :
		derived-type-def
	|	entry-stmt
	|	enum-def
	|	format-stmt
	|	interface-block
	|	parameter-stmt
	|	procedure-declaration-stmt
	|	other-specification-stmt
	|	type-declaration-stmt
	|	stmt-function-stmt
	;

/*	
	R208 execution-part is executable-construct
	[ execution-part-construct ] ...
*/
	execution-part :
		executable-construct
		execution-part-construct *
	;

/*	
	R209 execution-part-construct is executable-construct
	or format-stmt
	or entry-stmt
	or data-stmt
*/
	execution-part-construct :
		executable-construct
	|	format-stmt
	|	entry-stmt
	|	data-stmt
	;
	
/*	
	R210 internal-subprogram-part is contains-stmt
	[ internal-subprogram ] ...
*/
	internal-subprogram-part :
		contains-stmt
		internal-subprogram *
	;

/*	
	R211 internal-subprogram is function-subprogram
	or subroutine-subprogram
*/
	internal-subprogram :
		function-subprogram
	|	subroutine-subprogram
	;

/*	
	R212 other-specification-stmt is access-stmt
	or allocatable-stmt
	or asynchronous-stmt
	or bind-stmt
	or codimension-stmt
	or common-stmt
	or data-stmt
	or dimension-stmt
	or equivalence-stmt
	or external-stmt
	or intent-stmt
	or intrinsic-stmt
	or namelist-stmt
	or optional-stmt
	or pointer-stmt
	or protected-stmt
	or save-stmt
	or target-stmt
	or volatile-stmt
	or value-stmt
*/
	other-specification-stmt :
		access-stmt
	|	allocatable-stmt
	|	asynchronous-stmt
	|	bind-stmt
	|	codimension-stmt
	|	common-stmt
	|	data-stmt
	|	dimension-stmt
	|	equivalence-stmt
	|	external-stmt
	|	intent-stmt
	|	intrinsic-stmt
	|	namelist-stmt
	|	optional-stmt
	|	pointer-stmt
	|	protected-stmt
	|	save-stmt
	|	target-stmt
	|	volatile-stmt
	|	value-stmt
	;
	
/*
	R213 executable-construct is action-stmt
	or associate-construct
	or block-construct
	or case-construct
	or critical-construct
	or do-construct
	or forall-construct
	or if-construct
	or select-type-construct
	or where-construct
*/
	executable-construct :
		action-stmt
	|	associate-construct
	|	block-construct
	|	case-construct
	|	critical-construct
	|	do-construct
	|	forall-construct
	|	if-construct
	|	select-type-construct
	|	where-construct
	;
	
/*
	R214 action-stmt is allocate-stmt
	or assignment-stmt
	or backspace-stmt
	or call-stmt
	or close-stmt
	or continue-stmt
	or cycle-stmt
	or deallocate-stmt
	or end-function-stmt
	or end-mp-subprogram-stmt
	or end-program-stmt
	or end-subroutine-stmt
	or endfile-stmt
	or error-stop-stmt
	or exit-stmt
	or flush-stmt
	or forall-stmt
	or goto-stmt
	or if-stmt
	or inquire-stmt
	or lock-stmt
	or nullify-stmt
	or open-stmt
	or pointer-assignment-stmt
	or print-stmt
	or read-stmt
	or return-stmt
	or rewind-stmt
	or stop-stmt
	or sync-all-stmt
	or sync-images-stmt
	or sync-memory-stmt
	or unlock-stmt
	or wait-stmt
	or where-stmt
	or write-stmt
	or arithmetic-if-stmt
	or computed-goto-stmt
*/
	action-stmt :
		allocate-stmt
	|	assignment-stmt
	|	backspace-stmt
	|	call-stmt
	|	close-stmt
	|	continue-stmt
	|	cycle-stmt
	|	deallocate-stmt
	|	end-function-stmt
	|	end-mp-subprogram-stmt
	|	end-program-stmt
	|	end-subroutine-stmt
	|	endfile-stmt
	|	error-stop-stmt
	|	exit-stmt
	|	flush-stmt
	|	forall-stmt
	|	goto-stmt
	|	if-stmt
	|	inquire-stmt
	|	lock-stmt
	|	nullify-stmt
	|	open-stmt
	|	pointer-assignment-stmt
	|	print-stmt
	|	read-stmt
	|	return-stmt
	|	rewind-stmt
	|	stop-stmt
	|	sync-all-stmt
	|	sync-images-stmt
	|	sync-memory-stmt
	|	unlock-stmt
	|	wait-stmt
	|	where-stmt
	|	write-stmt
	|	arithmetic-if-stmt
	|	computed-goto-stmt
	;
	
/*
R215 keyword is name
*/
	keyword :
		name
	;

/***************
	Clause 3:	
 ***************/
/*
	R301 alphanumeric-character is letter
	or digit
	or underscore
*/
	alphanumeric-character :
		ALPHANUMERIC_CHARACTER	
	;

/*
	R302 underscore is _
*/
	underscore :
		UNDERSCORE
	;

/*
	R303 name is letter [ alphanumeric-character ] ...
*/
	name :
		NAME
	;

/*
	R304 constant is literal-constant
	or named-constant
*/
	constant :
		literal-constant
	|	named-constant
	;

/*
	R305 literal-constant is int-literal-constant
	or real-literal-constant
	or complex-literal-constant
	or logical-literal-constant
	or char-literal-constant
	or boz-literal-constant
*/
	literal-constant :
		INT_LITERAL_CONSTANT
	|	REAL_LITERAL_CONSTANT
	|	COMPLEX_LITERAL_CONSTANT
	|	LOCGICAL_LITERAL_CONSTANT
	|	CHAR_LITERAL_CONSTANT
	|	BOZ_LITERAL_CONSTANT
	;

/*
	R306 named-constant is name
*/
	named-constant :
		name
	;

/*
	R307 int-constant is constant
*/
	int-constant :
		constant
	;

/*
	R308 char-constant is constant
*/
	char-constant :
		constant
	;

/*
	R309 intrinsic-operator is power-op
	or mult-op
	or add-op
	or concat-op
	or rel-op
	or not-op
	or and-op
	or or-op
	or equiv-op
*/
	intrinsic-operator :
		power-op
	|	mult-op
	|	add-op
	|	concat-op
	|	rel-op
	|	not-op
	|	and-op
	|	or-op
	|	equiv-op
	;
	
/*
	R310 defined-operator is defined-unary-op
	or defined-binary-op
	or extended-intrinsic-op
*/
	defined-operator :
		defined-unary-op
	|	defined-binary-op
	|	extended-intrinsic-op
	;

/*
	R311 extended-intrinsic-op is intrinsic-operator
*/
	extended-intrinsic-op :
		intrinsic-operator
	;

/*
	R312 label is digit [ digit [ digit [ digit [ digit ] ] ] ]
*/
	label :
		LABEL
	;

/***************
	Clause 4:	
 ***************/

/*
	R401 type-param-value is scalar-int-expr
	or *
	or :
*/
	type-param-value :
		scalar-int-expr
	|	'*'
	|	':'
	;

/*
	R402 type-spec is intrinsic-type-spec
	or derived-type-spec
*/
	type-spec :
		intrinsic-type-spec
	|	derived-type-spec
	;

/*
	R403 declaration-type-spec is intrinsic-type-spec
	or TYPE ( intrinsic-type-spec )
	or TYPE ( derived-type-spec )
	or CLASS ( derived-type-spec )
	or CLASS ( * )
*/
	declaration-type-spec :
		intrinsic-type-spec
	|	'TYPE' '(' intrinsic-type-spec ')'
	|	'TYPE' '(' derived-type-spec ')'
	|	'CLASS' '(' derived-type-spec ')'
	|	'CLASS' '(' '*' ')'
	;

/*
	R404 intrinsic-type-spec is INTEGER [ kind-selector ]
	or REAL [ kind-selector ]
	or DOUBLE PRECISION
	or COMPLEX [ kind-selector ]
	or CHARACTER [ char-selector ]
	or LOGICAL [ kind-selector ]
*/
	intrinsic-type-spec :
		INTEGER kind-selector ?
	|	REAL kind-selector ?
	|	DOUBLE_PRECISION
	|	COMPLEX kind-selector ?
	|	CHARACTER char-selector ?
	|	LOGICAL kind-selector ?
	;

/*
	R405 kind-selector is ( [ KIND = ] scalar-int-constant-expr )
*/
	kind-selector :
		'(' ( KIND '=' ) ? scalar-int-constant-expr ')'
	;

/*
	R406 signed-int-literal-constant is [ sign ] int-literal-constant
*/
	signed-int-literal-constant :
		sign ? INT_LITERAL_CONSTANT
	;

/*
	R407 int-literal-constant is digit-string [ kind-param ]
	
	see tokens...
*/

/*
	R408 kind-param is digit-string
	or scalar-int-constant-name
*/

/*
	R409 signed-digit-string is [ sign ] digit-string
*/
	signed-digit-string : 
		sign ? digit-string
	;

/*
	R410 digit-string is digit [ digit ] ...
*/
	digit-string : 
		DIGIT_STRING
	;

/*
	R411 sign is +
	or -
*/
	sign :
		'+'
	|	'-'
	;

/*
	R412 signed-real-literal-constant is [ sign ] real-literal-constant
*/
	signed-real-literal-constant :
		sign ? REAL_LITERAL_CONSTANT
	;

/*
	R413 real-literal-constant is significand [ exponent-letter exponent ] [ kind-param ]
	or digit-string exponent-letter exponent [ kind-param ]
	
	see tokens...
*/

/*
	R414 significand is digit-string . [ digit-string ]
	or . digit-string

	see tokens...
*/

/*
	R415 exponent-letter is E
	or D

	see tokens...
*/

/*
	R416 exponent is signed-digit-string

	see tokens...
*/

/*
	R417 complex-literal-constant is ( real-part , imag-part )

	see tokens...
*/

/*
	R418 real-part is signed-int-literal-constant
	or signed-real-literal-constant
	or named-constant

	see tokens...
*/

/*
	R419 imag-part is signed-int-literal-constant
	or signed-real-literal-constant
	or named-constant

	see tokens...
*/

/*
	R420 char-selector is length-selector
	or ( LEN = type-param-value ,
	KIND = scalar-int-constant-expr )
	or ( type-param-value ,
	[ KIND = ] scalar-int-constant-expr )
	or ( KIND = scalar-int-constant-expr
	[ , LEN =type-param-value ] )
*/
	char-selector :
		length-selector
	|	'(' 'LEN' '=' type-param-value ',' KIND '=' scalar-int-constant-expr ')'
	|	'(' type-param-value ',' ( KIND '=' ) ? scalar-int-constant-expr ')'
	|	'(' 'KIND' '=' scalar-int-constant-expr	( ',' LEN '=' type-param-value ) ? ')'
	;

/*
	R421 length-selector is ( [ LEN = ] type-param-value )
	or * char-length [ , ]
*/
	length-selector :
		'(' ( LEN '=' ) ? type-param-value ')'
	|	'*' char-length ',' ?
	; 

/*
	R422 char-length is ( type-param-value )
	or int-literal-constant
*/
	char-length :
		'(' type-param-value ')'
	|	int-literal-constant
	;

/*
	R423 char-literal-constant is [ kind-param ] ' [ rep-char ] ... '
	or [ kind-param ] " [ rep-char ] ... "
*/
	char-literal-constant :
		CHAR_LITERAL_CONSTANT
	;

/*
	R424 logical-literal-constant is .TRUE. [ kind-param ]
	or .FALSE. [ kind-param ]
*/
	logical-literal-constant :
		LOGICAL_LITERAL_CONSTANT
	;

/*
	R425 derived-type-def is derived-type-stmt
	[ type-param-def-stmt ] ...
	[ private-or-sequence ] ...
	[ component-part ]
	[ type-bound-procedure-part ]
	end-type-stmt
*/
	derived-type-def :
		derived-type-stmt
		type-param-def-stmt *
		private-or-sequence *
		component-part ?
		type-bound-procedure-part ?
		end-type-stmt
	;

/*
	R426 derived-type-stmt is TYPE [ [ , type-attr-spec-list ] :: ] type-name
	[ ( type-param-name-list ) ]
*/
	derived-type-stmt :
		'TYPE' ( ( ',' type-attr-spec-list ) ? ':' ':' ) ? type-name
		( '(' type-param-name-list ')' ) ?
	;

/*
	R427 type-attr-spec is ABSTRACT
	or access-spec
	or BIND (C)
	or EXTENDS ( parent-type-name )
*/
	type-attr-spec :
		ABSTRACT
	|	access-spec
	|	BIND '(' 'C' ')'
	|	EXTENDS '(' parent-type-name ')'
	;

/*
	R428 private-or-sequence is private-components-stmt
	or sequence-stmt
*/
	private-or-sequence :
		private-components-stmt
	|	sequence-stmt
	;

/*
	R429 end-type-stmt is END TYPE [ type-name ]
*/
	end-type-stmt :
		END_TYPE type-name ?
	;

/*
	R430 sequence-stmt is SEQUENCE
*/
	sequence-stmt :
		SEQUENCE
	;

/*
	R431 type-param-def-stmt is INTEGER [ kind-selector ] , type-param-attr-spec ::
	type-param-decl -list
*/
	type-param-def-stmt :
		INTEGER kind-selector ? ',' type-param-attr-spec ':' ':' type-param-decl-list
	;

/*
	R432 type-param-decl is type-param-name [ = scalar-int-constant-expr ]
*/
	type-param-decl :
		type-param-name ( '=' scalar-int-constant-expr ) ?
	;

	type-param-decl-list :
		type-param-decl ( ',' type-param-decl ) *
	;

/*
	R433 type-param-attr-spec is KIND
	or LEN
*/
	type-param-attr-spec :
		KIND
	|	LEN
	;

/*
	R434 component-part is [ component-def-stmt ] ...
*/
	component-part : 
		component-def-stmt *
	;

/*
	R435 component-def-stmt is data-component-def-stmt
	or proc-component-def-stmt
*/
	component-def-stmt :
		data-component-def-stmt
	|	proc-component-def-stmt
	;

/*
	R436 data-component-def-stmt is declaration-type-spec [ [ , component-attr-spec-list ] :: ]
	component-decl-list
*/
	data-component-def-stmt :
		declaration-type-spec ( ( ',' component-attr-spec-list ) ? ':' ':' ) ? component-decl-list
	;

/*
	R437 component-attr-spec is access-spec
	or ALLOCATABLE
	or CODIMENSION lbracket coarray-spec rbracket
	or CONTIGUOUS
	or DIMENSION ( component-array-spec )
	or POINTER
*/
	component-attr-spec :
		access-spec
	|	ALLOCATABLE
	|	CODIMENSION '[' coarray-spec ']'
	|	CONTIGUOUS
	|	DIMENSION '(' component-array-spec ')'
	|	POINTER
	;

/*
	R438 component-decl is component-name [ ( component-array-spec ) ]
	[ lbracket coarray-spec rbracket ]
	[ * char-length ] [ component-initialization ]
*/
	component-decl :
		component-name ( '(' component-array-spec ')' ) ? ( '[' coarray-spec ']' ) ? ( '*' char-length ) ? component-initialization ?
	;
	
	component-decl-list :
		component-decl ( ',' component-decl ) *
	;

/*
	R439 component-array-spec is explicit-shape-spec-list
	or deferred-shape-spec-list
*/
	component-array-spec :
		explicit-shape-spec-list
	|	deferred-shape-spec-list
	;

/*
	R440 proc-component-def-stmt is PROCEDURE ( [ proc-interface ] ) ,
	proc-component-attr-spec-list :: proc-decl -list
*/
	proc-component-def-stmt :
		PROCEDURE '(' proc-interface ? ')' ',' proc-component-attr-spec-list ':' ':' proc-decl-list
	;

/*
	R441 proc-component-attr-spec is POINTER
	or PASS [ (arg-name) ]
	or NOPASS
	or access-spec
*/
	proc-component-attr-spec :
		POINTER
	|	PASS ( '(' arg-name ')' ) ?
	|	NOPASS
	|	access-spec
	;

/*
	R442 component-initialization is = constant-expr
	or => null-init
	or => initial-data-target
*/
	component-initialization :
		'=' constant-expr
	|	'=' '>' null-init
	|	'=' '>' initial-data-target
	;

/*
	R443 initial-data-target is designator
*/
	initial-data-target :
		designator
	;

/*
	R444 private-components-stmt is PRIVATE
*/
	private-components-stmt :
		PRIVATE
	;

/*
	R445 type-bound-procedure-part is contains-stmt
	[ binding-private-stmt ]
	[ type-bound-proc-binding ] ...
*/
	type-bound-procedure-part :
		contains-stmt
		binding-private-stmt ?
		type-bound-proc-binding *
	;

/*
	R446 binding-private-stmt is PRIVATE
*/
	binding-private-stmt :
		PRIVATE
	;

/*
	R447 type-bound-proc-binding is type-bound-procedure-stmt
	or type-bound-generic-stmt
	or final-procedure-stmt
*/
	type-bound-proc-binding :
		type-bound-procedure-stmt
	|	type-bound-generic-stmt
	|	final-procedure-stmt
	;

/*
	R448 type-bound-procedure-stmt is PROCEDURE [ [ , binding-attr -list ] :: ] type-bound-proc-decl -list
	or PROCEDURE (interface-name), binding-attr -list :: binding-name-list
*/
	type-bound-procedure-stmt :
		PROCEDURE ( ( ',' binding-attr-list ) ? ':' ':' ) ? type-bound-proc-decl-list
	|	PROCEDURE '(' interface-name ')' ',' binding-attr-list ':' ':' binding-name-list
	;

/*
	R449 type-bound-proc-decl is binding-name [ => procedure-name ]
*/
	type-bound-proc-decl :
		binding-name ( '=' '>' procedure-name ) ?
	;

/*
	R450 type-bound-generic-stmt is GENERIC [ , access-spec ] :: generic-spec => binding-name-list
*/
	type-bound-generic-stmt :
		GENERIC ( ',' access-spec ) ? ':' ':' generic-spec '=' '>' binding-name-list
	;

/*
	R451 binding-attr is PASS [ (arg-name) ]
	or NOPASS
	or NON OVERRIDABLE
	or DEFERRED
	or access-spec
*/
	binding-attr :
		PASS ( '(' arg-name ')' ) ?
	|	NOPASS
	|	NON_OVERRIDABLE
	|	DEFERRED
	|	access-spec
	;

/*
	R452 final-procedure-stmt is FINAL [ :: ] final-subroutine-name-list
*/
	final-procedure-stmt :
		FINAL ( ':' ':' ) ? final-subroutine-name-list
	;

/*
	R453 derived-type-spec is type-name [ ( type-param-spec-list ) ]
*/
	derived-type-spec :
		type-name ( '(' type-param-spec-list ')' ) ?
	;

/*
	R454 type-param-spec is [ keyword = ] type-param-value
*/
	type-param-spec :
		( keyword '=' ) ? type-param-value
	;

/*
	R455 structure-constructor is derived-type-spec ( [ component-spec-list ] )
*/
	structure-constructor :
		derived-type-spec '(' component-spec-list ? ')'
	;

/*
	R456 component-spec is [ keyword = ] component-data-source
*/
	component-spec : 
		( keyword '=' ) ? component-data-source
	;
/*
	R457 component-data-source is expr
	or data-target
	or proc-target
*/
	component-data-source :
		expr
	|	data-target
	|	proc-target
	;

/*
	R458 enum-def is enum-def-stmt
	enumerator-def-stmt
	[ enumerator-def-stmt ] ...
	end-enum-stmt
*/
	enum-def :
		enum-def-stmt
		enumerator-def-stmt
		enumerator-def-stmt *
		end-enum-stmt
	;

/*
	R459 enum-def-stmt is ENUM, BIND(C)
*/
	enum-def-stmt :
		ENUM ',' BIND '(' 'C' ')'
	;

/*
	R460 enumerator-def-stmt is ENUMERATOR [ :: ] enumerator-list
*/
	enumerator-def-stmt :
		ENUMERATOR ( ':' ':' ) ? enumerator-list
	;

/*
	R461 enumerator is named-constant [ = scalar-int-constant-expr ]
*/
	enumerator :
		named-constant ( '=' scalar-int-constant-expr ) ?
	;

/*
	R462 end-enum-stmt is END ENUM
*/
	end-enum-stmt :
		END_ENUM
	;

/*
	R463 boz-literal-constant is binary-constant
	or octal-constant
	or hex-constant
*/
	boz-literal-constant :
		BOZ_LITERAL_CONSTANT
	;

/*
	R464 binary-constant is B ' digit [ digit ] ... '
	or B " digit [ digit ] ... "
	
	see tokens...
*/

/*
	R465 octal-constant is O ' digit [ digit ] ... '
	or O " digit [ digit ] ... "

	see tokens...
*/

/*
	R466 hex-constant is Z ' hex-digit [ hex-digit ] ... '
	or Z " hex-digit [ hex-digit ] ... "

	see tokens...
*/

/*
	R467 hex-digit is digit
	or A
	or B
	or C
	or D
	or E
	or F

	see tokens...
*/

/*
	R468 array-constructor is (/ ac-spec /)
	or lbracket ac-spec rbracket
*/
	array-constructor :
		'(' '/' ac-spec '/' ')'
	|	'[' ac-spec ']'
	;

/*
	R469 ac-spec is type-spec ::
	or [type-spec ::] ac-value-list
*/
	ac-spec :
		type-spec ':' ':'
	| ( type-spec ':' ':' ) ? ac-value-list
	;

/*
	R470 lbracket is [
	
	not needed...
*/

/*
	R471 rbracket is ]

	not needed...
*/

/*
	R472 ac-value is expr
	or ac-implied-do
*/
	ac-value :
		expr
	|	ac-implied-do
	;

/*
	R473 ac-implied-do is ( ac-value-list , ac-implied-do-control )
*/
	ac-implied-do :
		'(' ac-value-list ',' ac-implied-do-control ')'
	;

/*
	R474 ac-implied-do-control is ac-do-variable = scalar-int-expr , scalar-int-expr
	[ , scalar-int-expr ]
*/
	ac-implied-do-control :
		ac-do-variable '=' scalar-int-expr ',' scalar-int-expr ( ',' scalar-int-expr ) ?
	;

/*
	R475 ac-do-variable is do-variable
*/
	ac-do-variable :
		do-variable
	;

/***************
	Clause 5:	
 ***************/

/*	
	R501 type-declaration-stmt is declaration-type-spec [ [ , attr-spec ] ... :: ] entity-decl -list
*/
	type-declaration-stmt :
		declaration-type-spec ( ( ',' attr-spec ) * ':' ':' ) entity-decl-list
	;

/*
	R502 attr-spec is access-spec
	or ALLOCATABLE
	or ASYNCHRONOUS
	or CODIMENSION lbracket coarray-spec rbracket
	or CONTIGUOUS
	or DIMENSION ( array-spec )
	or EXTERNAL
	or INTENT ( intent-spec )
	or INTRINSIC
	or language-binding-spec
	or OPTIONAL
	or PARAMETER
	or POINTER
	or PROTECTED
	or SAVE
	or TARGET
	or VALUE
	or VOLATILE
*/
	attr-spec :
		access-spec
	|	ALLOCATABLE
	|	ASYNCHRONOUS
	|	CODIMENSION '[' coarray-spec ']'
	|	CONTIGUOUS
	|	DIMENSION '(' array-spec ')'
	|	EXTERNAL
	|	INTENT '(' intent-spec ')'
	|	INTRINSIC
	|	language-binding-spec
	|	OPTIONAL
	|	PARAMETER
	|	POINTER
	|	PROTECTED
	|	SAVE
	|	TARGET
	|	VALUE
	|	VOLATILE
	;
	
/*
	R503 entity-decl is object-name [( array-spec )]
	[ lbracket coarray-spec rbracket ]
	[ * char-length ] [ initialization ]
	or function-name [ * char-length ]
*/
	entity-decl :
		object-name ( '(' array-spec ')' ) ? ( '[' coarray-spec ']' ) ?	( '*' char-length ) ? initialization ?
	|	function-name ( '*' char-length ) ?
	;

/*
	R504 object-name is name
*/
	object-name :
		name
	;

/*
	R505 initialization is = constant-expr
	or => null-init
	or => initial-data-target
*/
	initialization :
		'=' constant-expr
	|	'=' '>' null-init
	|	'=' '>' initial-data-target
	;

/*
	R506 null-init is function-reference
*/
	null-init :
		function-reference
	;

/*
	R507 access-spec is PUBLIC
	or PRIVATE
*/
	access-spec :
		PUBLIC
	|	PRIVATE
	;

/*
	R508 language-binding-spec is BIND (C [, NAME = scalar-default-char-constant-expr ])
*/
	language-binding-spec :
		BIND '(' 'C' ( ',' NAME '=' scalar-default-char-constant-expr ) ? ')'
	;

/*
	R509 coarray-spec is deferred-coshape-spec-list
	or explicit-coshape-spec
*/
	coarray-spec :
		deferred-coshape-spec-list
	|	explicit-coshape-spec
	;

/*
	R510 deferred-coshape-spec is :
*/
	deferred-coshape-spec :
		':'
	;

/*
	R511 explicit-coshape-spec is [ [ lower-cobound : ] upper-cobound, ]...
	[ lower-cobound : ] *
*/
	explicit-coshape-spec :
		( ( lower-cobound ':' ) ? upper-cobound ',' ) *	( lower-cobound ':' ) ? '*'
	;

/*
	R512 lower-cobound is specification-expr
*/
	lower-cobound :
		specification-expr
	;

/*
	R513 upper-cobound is specification-expr
*/
	upper-cobound :
		specification-expr
	;

/*
	R514 dimension-spec is DIMENSION ( array-spec )
*/
	dimension-spec :
		DIMENSION '(' array-spec ')'
	;

/*
	R515 array-spec is explicit-shape-spec-list
	or assumed-shape-spec-list
	or deferred-shape-spec-list
	or assumed-size-spec
	or implied-shape-spec-list
*/
	array-spec :
		explicit-shape-spec-list
	|	assumed-shape-spec-list
	|	deferred-shape-spec-list
	|	assumed-size-spec
	|	implied-shape-spec-list
	;

/*
	R516 explicit-shape-spec is [ lower-bound : ] upper-bound
*/
	explicit-shape-spec :
		( lower-bound ':' ) ? upper-bound
	;

/*
	R517 lower-bound is specification-expr
*/
	lower-bound :
		specification-expr
	;

/*
	R518 upper-bound is specification-expr
*/
	upper-bound :
		specification-expr
	;

/*
	R519 assumed-shape-spec is [ lower-bound ] :
*/
	assumed-shape-spec :
		lower-bound ? ':'
	;

/*
	R520 deferred-shape-spec is :
*/
	deferred-shape-spec :
		':'
	;

/*
	R521 assumed-size-spec is [ explicit-shape-spec , ]... [ lower-bound : ] *
*/
	assumed-size-spec :
		( explicit-shape-spec ',' ) * ( lower-bound ':' ) ? '*'
	;
	
/*
	R522 implied-shape-spec is [ lower-bound : ] *
*/
	implied-shape-spec :
		( lower-bound ':' ) ? '*'
	;
	
/*
	R523 intent-spec is IN
	or OUT
	or INOUT
*/
	intent-spec :
		IN
	|	OUT
	|	INOUT
	;

/*
	R524 access-stmt is access-spec [ [ :: ] access-id -list ]
*/
	access-stmt :
		access-spec ( ( ':' ':' ) ? access-id-list ) ?
	;

/*
	R525 access-id is use-name
	or generic-spec
*/
	access-id :
		use-name
	|	generic-spec
	;

/*
	R526 allocatable-stmt is ALLOCATABLE [ :: ] allocatable-decl -list
*/
	allocatable-stmt :
		ALLOCATABLE ( ':' ':' ) allocatable-decl-list
	;

/*
	R527 allocatable-decl is object-name [ ( array-spec ) ]
	[ lbracket coarray-spec rbracket ]
*/
	allocatable-decl :
		object-name ( '(' array-spec ')' ) ?
		( '[' coarray-spec ']' ) ?
	;

/*
	R528 asynchronous-stmt is ASYNCHRONOUS [ :: ] object-name-list
*/
	asynchronous-stmt :
		ASYNCHRONOUS ( ':' ':' ) ? object-name-list
	;

/*
	R529 bind-stmt is language-binding-spec [ :: ] bind-entity-list
*/
	bind-stmt :
		language-binding-spec ( ':' ':' ) ? bind-entity-list
	;

/*
	R530 bind-entity is entity-name
	or / common-block-name /
*/
	bind-entity :
		entity-name
	|	'/' common-block-name '/'
	;

/*
	R531 codimension-stmt is CODIMENSION [ :: ] codimension-decl -list
*/
	codimension-stmt :
		CODIMENSION ( ':' ':' ) ? codimension-decl-list
	;

/*
	R532 codimension-decl is coarray-name lbracket coarray-spec rbracket
*/
	codimension-decl :
		coarray-name '[' coarray-spec ']'
	;

/*
	R533 contiguous-stmt is CONTIGUOUS [ :: ] object-name-list
*/
	contiguous-stmt :
		CONTIGUOUS ( ':' ':' ) ? object-name-list
	;

/*
	R534 data-stmt is DATA data-stmt-set [ [ , ] data-stmt-set ] ...
*/
	data-stmt :
		DATA data-stmt-set ( ',' ? data-stmt-set ) *
	;

/*
	R535 data-stmt-set is data-stmt-object-list / data-stmt-value-list /
*/
	data-stmt-set :
		data-stmt-object-list '/' data-stmt-value-list '/'
	;

/*
	R536 data-stmt-object is variable
	or data-implied-do
*/
	data-stmt-object :
		variable
	|	data-implied-do
	;

/*
	R537 data-implied-do is ( data-i-do-object-list , data-i-do-variable =
	scalar-int-constant-expr ,
	scalar-int-constant-expr
	[ , scalar-int-constant-expr ] )
*/
	data-implied-do :
		'(' data-i-do-object-list ',' data-i-do-variable '='
		scalar-int-constant-expr ','
		scalar-int-constant-expr
		( ',' scalar-int-constant-expr ) ? ')'
	;
/*
	R538 data-i-do-object is array-element
	or scalar-structure-component
	or data-implied-do
*/
	data-i-do-object :
		array-element
	|	scalar-structure-component
	|	data-implied-do
	;

/*
	R539 data-i-do-variable is do-variable
*/
	data-i-do-variable :
		do-variable
	;

/*
	R540 data-stmt-value is [ data-stmt-repeat * ] data-stmt-constant
*/
	data-stmt-value :
		( data-stmt-repeat '*' ) ? data-stmt-constant
	;

/*
	R541 data-stmt-repeat is scalar-int-constant
	or scalar-int-constant-subobject
*/
	data-stmt-repeat :
		scalar-int-constant
	|	scalar-int-constant-subobject
	;

/*
	R542 data-stmt-constant is scalar-constant
	or scalar-constant-subobject
	or signed-int-literal-constant
	or signed-real-literal-constant
	or null-init
	or initial-data-target
	or structure-constructor
*/
	data-stmt-constant :
		scalar-constant
	|	scalar-constant-subobject
	|	signed-int-literal-constant
	|	signed-real-literal-constant
	|	null-init
	|	initial-data-target
	|	structure-constructor
	;

/*
	R543 int-constant-subobject is constant-subobject
*/
	int-constant-subobject :
		constant-subobject
	;

/*
	R544 constant-subobject is designator
*/
	constant-subobject :
		designator
	;

/*
	R545 dimension-stmt is DIMENSION [ :: ] array-name ( array-spec )
	[ , array-name ( array-spec ) ] ...
*/
	dimension-stmt :
		DIMENSION ( ':' ':' ) ? array-name '(' array-spec ')'
		( ',' array-name '(' array-spec ')' ) *
	;
	
/*
	R546 intent-stmt is INTENT ( intent-spec ) [ :: ] dummy-arg-name-list
*/
	intent-stmt :
		INTENT '(' intent-spec ')' ( ':' ':' ) ? dummy-arg-name-list
	;

/*
	R547 optional-stmt is OPTIONAL [ :: ] dummy-arg-name-list
*/
	optional-stmt :
		OPTIONAL ( ':' ':' ) ? dummy-arg-name-list
	;

/*
	R548 parameter-stmt is PARAMETER ( named-constant-def -list )
*/
	parameter-stmt :
		PARAMETER '(' named-constant-def-list ')'
	;
	
/*
	R549 named-constant-def is named-constant = constant-expr
*/
	named-constant-def :
		named-constant '=' constant-expr
	;

/*
	R550 pointer-stmt is POINTER [ :: ] pointer-decl -list
*/
	pointer-stmt : 
		POINTER ( ':' ':' ) pointer-decl-list
	;

/*
	R551 pointer-decl is object-name [ ( deferred-shape-spec-list ) ]
	or proc-entity-name
*/
	pointer-decl :
		object-name ( '(' deferred-shape-spec-list ')' ) ?
	|	proc-entity-name
	;

/*
	R552 protected-stmt is PROTECTED [ :: ] entity-name-list
*/
	protected-stmt :
		PROTECTED ( ':' ':' ) ? entity-name-list
	;

/*
	R553 save-stmt is SAVE [ [ :: ] saved-entity-list ]
*/
	save-stmt :
		SAVE ( ( ':' ':' ) ? saved-entity-list ) ?
	;

/*
	R554 saved-entity is object-name
	or proc-pointer-name
	or / common-block-name /
*/
	saved-entity :
		object-name
	|	proc-pointer-name
	|	'/' common-block-name '/'
	;

/*
	R555 proc-pointer-name is name
*/
	proc-pointer-name :
		name
	;

/*
	R556 target-stmt is TARGET [ :: ] target-decl -list
*/
	target-stmt :
		TARGET ( ':' ':' ) ? target-decl-list
	;

/*
	R557 target-decl is object-name [ ( array-spec ) ]
	[ lbracket coarray-spec rbracket ]
*/
	target-decl :
		object-name ( '(' array-spec ')' ) ?
		( '[' coarray-spec ']' ) ?
	;

/*
	R558 value-stmt is VALUE [ :: ] dummy-arg-name-list
*/
	value-stmt :
		VALUE ( ':' ':' ) ? dummy-arg-name-list
	;

/*
	R559 volatile-stmt is VOLATILE [ :: ] object-name-list
*/
	volatile-stmt :
		VOLATILE ( ':' ':' ) ? object-name-list
	;

/*
	R560 implicit-stmt is IMPLICIT implicit-spec-list
	or IMPLICIT NONE
*/
	implicit-stmt :
		IMPLICIT implicit-spec-list
	|	IMPLICIT_NONE
	;

/*
	R561 implicit-spec is declaration-type-spec ( letter-spec-list )
*/
	implicit-spec :
		declaration-type-spec '(' letter-spec-list ')'
	;
	
/*
	R562 letter-spec is letter [ - letter ]
*/
	letter-spec :
		letter ( '-' letter ) ?
	;

/*
	R563 namelist-stmt is NAMELIST
	/ namelist-group-name / namelist-group-object-list
	[ [ , ] / namelist-group-name /
	namelist-group-object-list ] . . .
*/
	namelist-stmt :
		NAMELIST
		'/' namelist-group-name '/' namelist-group-object-list
		(  ',' ? '/' namelist-group-name '/' namelist-group-object-list ) *
	;
	
/*
	R564 namelist-group-object is variable-name
*/
	namelist-group-object :
		variable-name
	;

/*
	R565 equivalence-stmt is EQUIVALENCE equivalence-set-list
*/
	equivalence-stmt :
		EQUIVALENCE equivalence-set-list
	;

/*
	R566 equivalence-set is ( equivalence-object , equivalence-object-list )
*/
	equivalence-set :
		'(' equivalence-object ',' equivalence-object-list ')'
	;

/*
	R567 equivalence-object is variable-name
	or array-element
	or substring
*/
	equivalence-object :
		variable-name
	|	array-element
	|	substring
	;

/*
	R568 common-stmt is COMMON
	[ / [ common-block-name ] / ] common-block-object-list
	[ [ , ] / [ common-block-name ] /
	common-block-object-list ] ...
*/
	common-stmt :
		COMMON
		( '/' common-block-name ? '/' ) ? common-block-object-list
		(  ',' ? '/' common-block-name ? '/' common-block-object-list ) *
	;

/*
	R569 common-block-object is variable-name [ ( array-spec ) ]
*/
	common-block-object :
		variable-name ( '(' array-spec ')' ) ?
	;

/***************
	Clause 6:	
 ***************/

/*
	R601 designator is object-name
	or array-element
	or array-section
	or coindexed-named-object
	or complex-part-designator
	or structure-component
	or substring
*/
	designator :
		object-name
	|	array-element
	|	array-section
	|	coindexed-named-object
	|	complex-part-designator
	|	structure-component
	|	substring
	;

/*
	R602 variable is designator
	or expr
*/
	variable :
		designator
	|	expr
	;

/*
	R603 variable-name is name
*/
	variable-name :
		name
	;

/*
	R604 logical-variable is variable
*/
	logical-variable :
		variable
	;

/*
	R605 char-variable is variable
*/
	char-variable :
		variable
	;

/*
	R606 default-char-variable is variable
*/
	default-char-variable :
		variable
	;

/*
	R607 int-variable is variable
*/
	int-variable :
		variable
	;

/*
	R608 substring is parent-string ( substring-range )
*/
	substring :
		parent-string '(' substring-range ')'
	;

/*
	R609 parent-string is scalar-variable-name
	or array-element
	or coindexed-named-object
	or scalar-structure-component
	or scalar-constant
*/
	parent-string :
		scalar-variable-name
	|	array-element
	|	coindexed-named-object
	|	scalar-structure-component
	|	scalar-constant
	;
	
/*
	R610 substring-range is [ scalar-int-expr ] : [ scalar-int-expr ]
*/
	substring-range :
		scalar-int-expr ? ':' scalar-int-expr ?
	;

/*
	R611 data-ref is part-ref [ % part-ref ] ...
*/
	data-ref :
		part-ref ( '%' part-ref ) ?
	;

/*
	R612 part-ref is part-name [ ( section-subscript-list ) ] [ image-selector ]
*/
	part-ref :
		part-name ( '(' section-subscript-list ')' ) ? image-selector ?
	;

/*
	R613 structure-component is data-ref
*/
	structure-component :
		data-ref
	;

/*
	R614 coindexed-named-object is data-ref
*/
	coindexed-named-object :
		data-ref
	;

/*
	R615 complex-part-designator is designator % RE
	or designator % IM
*/
	complex-part-designator :
		designator '%' RE
	|	designator '%' IM
	;

/*
	R616 type-param-inquiry is designator % type-param-name
*/
	type-param-inquiry :
		designator '%' type-param-name
	;

/*
	R617 array-element is data-ref
*/
	array-element :
		data-ref
	;

/*
	R618 array-section is data-ref [ ( substring-range ) ]
	or complex-part-designator
*/
	array-section :
		data-ref ( '(' substring-range ')' ) ?
	|	complex-part-designator
	;

/*
	R619 subscript is scalar-int-expr
*/
	subscript :
		scalar-int-expr
	;

/*
	R620 section-subscript is subscript
	or subscript-triplet
	or vector-subscript
*/
	section-subscript :
		subscript
	|	subscript-triplet
	|	vector-subscript
	;

/*
	R621 subscript-triplet is [ subscript ] : [ subscript ] [ : stride ]
*/
	subscript-triplet :
		subscript ? ':' subscript ? ( ':' stride ) ?
	;

/*
	R622 stride is scalar-int-expr
*/
	stride :
		scalar-int-expr
	;
	
/*
	R623 vector-subscript is int-expr
*/
	vector-subscript :
		int-expr
	;

/*
	R624 image-selector is lbracket cosubscript-list rbracket
*/
	image-selector :
		'[' cosubscript-list ']'
	;
	
/*
	R625 cosubscript is scalar-int-expr
*/
	cosubscript :
		scalar-int-expr
	;

/*
	R626 allocate-stmt is ALLOCATE ( [ type-spec :: ] allocation-list
	[, alloc-opt-list ] )
*/
	allocate-stmt :
		ALLOCATE '(' ( type-spec ':' ':' ) ? allocation-list
		( ',' alloc-opt-list ) ? ')'
	;

/*
	R627 alloc-opt is ERRMSG = errmsg-variable
	or MOLD = source-expr
	or SOURCE = source-expr
	or STAT = stat-variable
*/
	alloc-opt :
		ERRMSG '=' errmsg-variable
	|	MOLD '=' source-expr
	|	SOURCE '=' source-expr
	|	STAT '=' stat-variable
	;
	
/*
	R628 stat-variable is scalar-int-variable
*/
	stat-variable :
		scalar-int-variable
	;

/*
	R629 errmsg-variable is scalar-default-char-variable
*/
	errmsg-variable :
		scalar-default-char-variable
	;

/*
	R630 source-expr is expr
*/
	source-expr :
		expr
	;

/*
	R631 allocation is allocate-object [ ( allocate-shape-spec-list ) ]
	[ lbracket allocate-coarray-spec rbracket ]
*/
	allocation :
		allocate-object ( '(' allocate-shape-spec-list ')' ) ?
		( '[' allocate-coarray-spec ']' ) ?
	;

/*
	R632 allocate-object is variable-name
	or structure-component
*/
	allocate-object :
		variable-name
	|	structure-component
	;

/*
	R633 allocate-shape-spec is [ lower-bound-expr : ] upper-bound-expr
*/
	allocate-shape-spec :
		( lower-bound-expr ':' ) ? upper-bound-expr
	;

/*
	R634 lower-bound-expr is scalar-int-expr
*/
	lower-bound-expr :
		scalar-int-expr
	;

/*
	R635 upper-bound-expr is scalar-int-expr
*/
	upper-bound-expr :
		scalar-int-expr
	;

/*
	R636 allocate-coarray-spec is [ allocate-coshape-spec-list , ] [ lower-bound-expr : ] *
*/
	allocate-coarray-spec :
		( allocate-coshape-spec-list ',' ) ? ( lower-bound-expr ':' ) ? '*'
	;

/*
	R637 allocate-coshape-spec is [ lower-bound-expr : ] upper-bound-expr
*/
	allocate-coshape-spec :
		( lower-bound-expr ':' ) ? upper-bound-expr
	;

/*
	R638 nullify-stmt is NULLIFY ( pointer-object-list )
*/
	nullify-stmt :
		NULLIFY '(' pointer-object-list ')'
	;

/*
	R639 pointer-object is variable-name
	or structure-component
	or proc-pointer-name
*/
	pointer-object :
		variable-name
	|	structure-component
	|	proc-pointer-name
	;

/*
	R640 deallocate-stmt is DEALLOCATE ( allocate-object-list [ , dealloc-opt-list ] )
*/
	deallocate-stmt :
		DEALLOCATE '(' allocate-object-list ( ',' dealloc-opt-list ) ? ')'
	;

/*
	R641 dealloc-opt is STAT = stat-variable
	or ERRMSG = errmsg-variable
*/
	dealloc-opt :
		STAT '=' stat-variable
	|	ERRMSG '=' errmsg-variable
	;

/***************
	Clause 7:	
 ***************/
