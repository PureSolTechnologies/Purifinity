/****************************************************************************
 * Fortran 2008 Grammar File for Nyota Uhura
 * (c) by 2010 PureSol Technologies
 * Author: Rick-Rainer Ludwig
 ****************************************************************************/ 
 
/****************************************************************************
 * O P T I O N S
 ****************************************************************************/ 
 OPTIONS
	grammar.checks=true;
	lexer="com.puresol.uhura.lexer.RegExpLexer";
	lexer.case_sensitive=false;
	parser="com.puresol.uhura.parser.lr.SLR1Parser";
	parser.backtracking=true;
 
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

	LETTER : "[A-Z]";
	ALPHANUMERIC_CHARACTER :
		 "(" LETTER "|" DIGIT "|_)"
	;
	DIGIT : "[0-9]";

	DIGIT_STRING : "\\d+";

	HEX_DIGIT : "[0-9A-F]";

	/* *************************
	 * 
	 * 4 Types
	 * 
	 * ************************
	 */

	/*
	 * Integer type
	 */

	SIGN : "[+-]";
	SIGNED_DIGIT_STRING : SIGN "?" DIGIT_STRING;

	KIND_PARAM : "(" DIGIT_STRING "|"
			NAME_LITERAL ")";
	SIGNED_INT_LITERAL_CONSTANT : SIGN "?"
			INT_LITERAL_CONSTANT;

	/*
	 * Real type
	 */
	EXPONENT : SIGNED_DIGIT_STRING;
	EXPONENT_LETTER : "[ED]";
	SIGNIFICANT : "(" DIGIT_STRING "\\.("
			DIGIT_STRING ")?|\\." DIGIT_STRING ")";
	SIGNED_REAL_LITERAL_CONSTANT : SIGN "?"
			REAL_LITERAL_CONSTANT;

	REP_CHAR_SINGLE_QUOTE : "([^']+|'')";
	REP_CHAR_DOUBLE_QUOTE : "([^\"]+|\"\")";

	HEX_CONSTANT : "(Z'" HEX_DIGIT "+'|Z\""
			HEX_DIGIT "+\")";

	OCTAL_CONSTANT : "(O'" DIGIT "+'|O\""
			DIGIT "+\")";

	BINARY_CONSTANT : "(B'" DIGIT "+'|B\""
			DIGIT "+\")";

	LINE_LEAD : '([C*!].....| [ \\d!]{4}[ \\d!;])';
 
/****************************************************************************
 * T O K E N S
 ****************************************************************************/ 
 TOKENS

	WHITESPACE : '[ \\t]+' [hidden] ;
	LINE_TERMINATOR : '(\\n|\\r\n|\\r)' [hidden] ;
	LINE_COMMENT : '(![^\\n\\\r]*' LINE_TERMINATOR '|' LINE_TERMINATOR '[C$*][^\\r\\n]*(?=' LINE_TERMINATOR '))' [hidden] ;

	/*
	 * 3.1.5 Special characters
	 */
	// BLANK : " ";
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
	ABSTRACT_INTERFACE : 'ABSTRACT\\s+INTERFACE' ;
	ACCESS : 'ACCESS' ;
	ACTION : 'ACTION' ;
	ALLOCATABLE : 'ALLOCATABLE' ;
	ALLOCATE : 'ALLOCATE' ;
	ACQUIRED_LOCK : 'ACQUIRED\\s+LOCK' ;
	ADVANCE : 'ADVANCE' ;
	ASSIGNMENT : 'ASSIGNMENT' ;
	ASSOCIATE : 'ASSOCIATE' ;
	ASYNCHRONOUS : 'ASYNCHRONOUS' ;
	BACKSPACE : 'BACKSPACE' ;
	BIND : 'BIND' ;
	BLANK : 'BLANK' ;
	BLOCK : 'BLOCK' ;
	BLOCK_DATA : 'BLOCK\\s+DATA' ;
	CALL : 'CALL' ;
	CASE : 'CASE' ;
	CHARACTER : 'CHARACTER' ;
	CLASS : 'CLASS' ;
	CLASS_DEFAULT : 'CLASS\\s+DEFAULT' ;
	CLASS_IS : 'CLASS\\s+IS' ;
	CLOSE : 'CLOSE' ;
	CONTINUE : 'CONTINUE' ;
	CYCLE : 'CYCLE' ;
	CODIMENSION : 'CODIMENSION' ;
	COMMON : 'COMMON' ;
	COMPLEX : 'COMPLEX' ;
	CONCURRENT : 'CONCURRENT' ;
	CONTAINS : 'CONTAINS' ;
	CONTIGUOUS : 'CONTIGUOUS' ;
	CRITICAL : 'CRITICAL' ;
	DATA : 'DATA' ;
	DEALLOCATE : 'DEALLOCATE' ;
	DECIMAL : 'DECIMAL' ;
	DEFAULT : 'DEFAULT' ;
	DEFERRED : 'DEFERRED' ;
	DELIM : 'DELIM' ;
	DIMENSION : 'DIMENSION' ;
	DIRECT : 'DIRECT' ;
	DO : 'DO' ;
	DOUBLE_PRECISION : 'DOUBLE\\s+PRECISION' ;
	ELEMENTAL : 'ELEMENTAL' ;
	ELSEWHERE : 'ELSEWHERE' ;
	ENCODING : 'ENCODING' ;
	END : 'END' ;
	END_ASSOCIATE : 'END\\s+ASSOCIATE' ;
	END_BLOCK : 'END\\s+BLOCK' ;
	END_CRITICAL : 'END\\s+CRITICAL' ;
	END_DO : 'END\\s+DO' ;
	END_FORALL : 'END\\s+FORALL' ;
	END_ENUM : 'END\\s+ENUM' ;
	END_IF : 'END\\s+IF' ;
	END_INTERFACE : 'END\\s+INTERFACE' ;
	END_SELECT : 'END\\s+SELECT' ;
	END_TYPE : 'END\\s+TYPE' ;
	END_WHERE : 'END\\s+WHERE';
	ENDFILE : 'ENDFILE' ;
	ELSE : 'ELSE' ;
	ELSE_IF : 'ELSE\\s+IF' ;
	ENCODING : 'ENCODING' ;
	ENTRY : 'ENTRY' ;
	ENUM : 'ENUM' ;
	ENUMERATOR : 'ENUMERATOR' ;
	EOR : 'EOR' ;
	EQUIVALENCE : 'EQUIVALENCE' ;
	ERR : 'ERR' ;
	ERRMSG : 'ERRMSG' ;
	ERROR_STOP : 'ERROR\\s+STOP';
	EXIST : 'EXIST' ;
	EXIT : 'EXIT' ;
	EXTENDS : 'EXTENDS' ;
	EXTERNAL : 'DIMENSION' ;
	FILE : 'FILE' ; 
	FINAL : 'FINAL' ;
	FLUSH : 'FLUSH' ;
	FMT : 'FMT' ;
	FORALL : 'FORALL' ;
	FORM : 'FORM' ;
	FORMAT : 'FORMAT' ;
	FORMATTED : 'FORMATTED' ;
	FUNCTION : 'FUNCTION' ;
	GENERIC : 'GENERIC' ;
	GO_TO : 'GO\\s*TO' ;
	ID : 'ID' ;
	IF : 'IF' ;
	IM : 'IM' ;
	IMPLICIT : 'IMPLICIT' ;
	IMPLICIT_NONE : 'IMPLICIT\\s+NONE' ;
	IMPORT : 'IMPORT' ;
	IMPURE : 'IMPURE' ;
	IN : 'IN' ;
	INOUT : 'INOUT' ;
	INTEGER : 'INTEGER' ;
	INTENT : 'INTENT' ;
	INTERFACE : 'INTERFACE' ;
	INTRINSIC : 'INTRINSIC' ;
	INQUIRE : 'INQUIRE' ;
	IOLENGTH : 'IOLENGTH' ;
	IOMSG : 'IOMSG' ;
	IOSTAT : 'IOSTAT' ;
	KIND : 'KIND' ;
	LEN : 'LEN' ;
	LOCK : 'LOCK' ;
	LOGICAL : 'LOGICAL' ;
	MODULE : 'MODULE' ;
	MODULE_PROCEDURE : 'MODULE\\s+PROCEDURE' ;
	MOLD : 'MOLD' ;
	NAME_KEYWORD : 'NAME' ;
	NAMED : 'NAMED' ;
	NAMELIST : 'NAMELIST' ;
	NEWUNIT : 'NEWUNIT' ;
	NEXTREC : 'NEXTREC' ;
	NML : 'NML' ;
	NON_INTRINSIC : 'NON\\s+INTRINSIC';
	NON_OVERRIDABLE : 'NON\\s+OVERRIDABLE' ;
	NOPASS : 'NOPASS' ;
	NULLIFY : 'NULLIFY' ;
	NUMBER : 'NUMBER' ;
	ONLY : 'ONLY' ;
	OPEN : 'OPEN' ;
	OPENED : 'OPENED' ;
	OPERATOR : 'OPERATOR' ;
	OPTIONAL : 'OPTIONAL' ;
	OUT : 'OUT' ;
	PAD : 'PAD' ;
	PASS : 'PASS' ;
	PARAMETER : 'PARAMETER' ;
	READWRITE : 'READWRITE' ;
	PENDING : 'PENDING' ;
	POINTER : 'POINTER' ;
	POS : 'POS' ;
	POSITION : 'POSITION' ;
	PRINT : 'PRINT' ;
	PRIVATE : 'PRIVATE' ;
	PROCEDURE : 'PROCEDURE' ;
	PROGRAM : 'PROGRAM' ; 
	PROTECTED : 'PROTECTED' ;
	PUBLIC : 'PUBLIC' ;
	PURE : 'PURE' ;
	RE : 'RE' ;
	REC : 'REC' ;
	RECL : 'RECL' ;
	READ : 'READ' ;
	REAL : 'REAL' ;
	RECURSIVE : 'RECURSIVE' ;
	RESULT : 'RESULT' ;
	RETURN : 'RETURN' ;
	REWIND : 'REWIND' ;
	ROUND : 'ROUND' ;
	SAVE : 'SAVE' ;
	SELECT : 'SELECT' ;
	SELECT_CASE : 'SELECT\\s+CASE' ;
	SEQUENCE : 'SEQUENCE' ;
	SEQUENTIAL : 'SEQUENTIAL' ;
	SIGN : 'SIGN' ;
	SIZE : 'SIZE' ;
	SOURCE : 'SOURCE' ;
	STAT : 'STAT' ;
	STATUS : 'STATUS' ;
	STOP : 'STOP' ;
	STREAM : 'STREAM' ;
	SUBMODULE : 'SUBMODULE' ;
	SUBROUTINE : 'SUBROUTINE' ;
	SYNC_ALL : 'SYNC\\s+ALL' ;
	SYNC_IMAGES : 'SYNC\\s+IMAGES' ;
	SYNC_MEMORY : 'SYNC\\s+MEMORY' ;
	TARGET : 'TARGET' ;
	THEN : 'THEN' ;
	TYPE : 'TYPE' ;
	TYPE_IS : 'TYPE\\s+IS' ;
	UNFORMATTED : 'UNFORMATTED' ;
	UNIT : 'UNIT' ;
	UNLOCK : 'UNLOCK' ;
	USE : 'USE' ;
	VALUE : 'SAVE' ;
	VOLATILE : 'VOLATILE' ;
	WAIT : 'WAIT' ;
	WHERE : 'WHERE' ;
	WHILE : 'WHILE' ;
	WRITE : 'WRITE' ;

	AND : '\\.AND\\.' ;
	EQ : '\\.EQ\\.' ;
	EQV : '.EQV.' ;
	GE : '\\.GE\\.' ;
	GT : '\\.GT\\.' ;
	LE : '\\.LE\\.' ;
	LT : '\\.LT\\.' ;
	NE : '\\.NE\\.' ;
	NEQV : '\\.NEQV\\.' ;
	NOT : '\\.NOT\\.' ;
	OR : '\\.OR\\.' ;

	INT_LITERAL_CONSTANT : DIGIT_STRING "(_"
			KIND_PARAM ")?";
	REAL_LITERAL_CONSTANT : "(" SIGNIFICANT "("
			EXPONENT_LETTER EXPONENT ")?(_" KIND_PARAM ")?|"
			DIGIT_STRING EXPONENT_LETTER EXPONENT "(_" KIND_PARAM
			")?)";
	CHAR_LITERAL_CONSTANT : "(" KIND_PARAM
			"_)?(\"" REP_CHAR_DOUBLE_QUOTE "*\"|'"
			REP_CHAR_SINGLE_QUOTE "*')";
	LOGICAL_LITERAL_CONSTANT : "(\\.TRUE\\.|\\.FALSE\\.)(_"
			KIND_PARAM ")?";
	BOZ_LITERAL_CONSTANT : "(" BINARY_CONSTANT
			"|" OCTAL_CONSTANT "|" HEX_CONSTANT ")";
	NAME_LITERAL : LETTER ALPHANUMERIC_CHARACTER
			"{0,62}";


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
	
	not needed...
*/

/***************
	Clause 3:	
 ***************/
/*
	R301 alphanumeric-character is letter
	or digit
	or underscore
	
	not needed...
*/

/*
	R302 underscore is _
	
	not needed...
*/

/*
	R303 name is letter [ alphanumeric-character ] ...
	
	not needed...
*/

/*
	R304 constant is literal-constant
	or named-constant
*/
	constant :
		literal-constant
	|	NAME_LITERAL
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
	|	complex-literal-constant
	|	LOGICAL_LITERAL_CONSTANT
	|	CHAR_LITERAL_CONSTANT
	|	BOZ_LITERAL_CONSTANT
	;

/*
	R306 named-constant is name
	
	not needed...
*/

/*
	R307 int-constant is constant
	
	not needed...
*/

/*
	R308 char-constant is constant
	
	not needed...
*/

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
		defined-op
	|	intrinsic-operator
	;

/*
	R311 extended-intrinsic-op is intrinsic-operator
	
	not needed...
*/

/*
	R312 label is digit [ digit [ digit [ digit [ digit ] ] ] ]
	
	not needed...
*/
	label-list :
		label-list ',' INT_LITERAL_CONSTANT
	|	INT_LITERAL_CONSTANT
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
		expr
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
		INTEGER kind-selector
	|	INTEGER 
	|	REAL kind-selector
	|	REAL 
	|	DOUBLE_PRECISION
	|	COMPLEX kind-selector
	|	COMPLEX 
	|	CHARACTER char-selector
	|	CHARACTER 
	|	LOGICAL kind-selector
	|	LOGICAL 
	;

/*
	R405 kind-selector is ( [ KIND = ] scalar-int-constant-expr )
*/
	kind-selector :
		'(' KIND '=' expr ')'
	|	'(' expr ')'
	;

/*
	R406 signed-int-literal-constant is [ sign ] int-literal-constant
*/
	signed-int-literal-constant :
		sign INT_LITERAL_CONSTANT
	|	INT_LITERAL_CONSTANT
	;

/*
	R407 int-literal-constant is digit-string [ kind-param ]
	
	see tokens...
*/

/*
	R408 kind-param is digit-string
	or scalar-int-constant-name
	
	not needed...
*/

/*
	R409 signed-digit-string is [ sign ] digit-string
	
	not needed...
*/

/*
	R410 digit-string is digit [ digit ] ...
	
	not needed...
*/

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
		sign REAL_LITERAL_CONSTANT
	|	REAL_LITERAL_CONSTANT
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
*/
	complex-literal-constant :
		'(' real-part ',' imag-part ')'
	;

/*
	R418 real-part is signed-int-literal-constant
	or signed-real-literal-constant
	or named-constant
*/
	real-part :
		signed-int-literal-constant
	|	signed-real-literal-constant
	|	NAME_LITERAL
	;

/*
	R419 imag-part is signed-int-literal-constant
	or signed-real-literal-constant
	or named-constant
*/
	imag-part :
		signed-int-literal-constant
	|	signed-real-literal-constant
	|	NAME_LITERAL
	;

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
	|	'(' 'LEN' '=' type-param-value ',' KIND '=' expr ')'
	|	'(' type-param-value ',' KIND '=' expr ')'
	|	'(' type-param-value ',' expr ')'
	|	'(' 'KIND' '=' expr	',' LEN '=' type-param-value ')'
	|	'(' 'KIND' '=' expr	')'
	;

/*
	R421 length-selector is ( [ LEN = ] type-param-value )
	or * char-length [ , ]
*/
	length-selector :
		'(' LEN '=' type-param-value ')'
	|	type-param-value ')'
	|	'*' char-length ','
	|	'*' char-length
	; 

/*
	R422 char-length is ( type-param-value )
	or int-literal-constant
*/
	char-length :
		'(' type-param-value ')'
	|	INT_LITERAL_CONSTANT
	;

/*
	R423 char-literal-constant is [ kind-param ] ' [ rep-char ] ... '
	or [ kind-param ] " [ rep-char ] ... "
	
	not needed...
*/

/*
	R424 logical-literal-constant is .TRUE. [ kind-param ]
	or .FALSE. [ kind-param ]
	
	not needed...
*/

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
		'TYPE' ( ( ',' type-attr-spec-list ) ? ':' ':' ) ? NAME_LITERAL	'(' name-list ')'
	|	'TYPE' ( ( ',' type-attr-spec-list ) ? ':' ':' ) ? NAME_LITERAL
	;
	// added...
	name-list :
		name-list ',' NAME_LITERAL
	|	NAME_LITERAL
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
	|	EXTENDS '(' NAME_LITERAL ')'
	;
	type-attr-spec-list :
		type-attr-spec-list ',' type-attr-spec
	|	type-attr-spec
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
		END_TYPE NAME_LITERAL
	|	END_TYPE 
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
		INTEGER kind-selector ',' type-param-attr-spec ':' ':' type-param-decl-list
	|	INTEGER  ',' type-param-attr-spec ':' ':' type-param-decl-list
	;

/*
	R432 type-param-decl is type-param-name [ = scalar-int-constant-expr ]
*/
	type-param-decl :
		NAME_LITERAL '=' expr
	|	NAME_LITERAL
	;

	type-param-decl-list :
		type-param-decl-list ',' type-param-decl
	|	type-param-decl
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
		component-part component-def-stmt
	|	component-def-stmt
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
		declaration-type-spec ',' component-attr-spec-list ':' ':' component-decl-list
	|	declaration-type-spec  ':' ':' component-decl-list
	|	declaration-type-spec component-decl-list
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
	// added...
	component-attr-spec-list :
		component-attr-spec-list ',' component-attr-spec
	|	component-attr-spec
	;

/*
	R438 component-decl is component-name [ ( component-array-spec ) ]
	[ lbracket coarray-spec rbracket ]
	[ * char-length ] [ component-initialization ]
*/
	component-decl :
		NAME_LITERAL ( '(' component-array-spec ')' ) ? ( '[' coarray-spec ']' ) ? ( '*' char-length ) ? component-initialization ?
	;
	
	component-decl-list :
		component-decl-list ',' component-decl
	|	component-decl
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
	|	PROCEDURE '(' ')' ',' proc-component-attr-spec-list ':' ':' proc-decl-list
	;

/*
	R441 proc-component-attr-spec is POINTER
	or PASS [ (arg-name) ]
	or NOPASS
	or access-spec
*/
	proc-component-attr-spec :
		POINTER
	|	PASS '(' NAME_LITERAL ')'
	|	PASS
	|	NOPASS
	|	access-spec
	;
	proc-component-attr-spec-list :
		proc-component-attr-spec-list ',' proc-component-attr-spec
	|	proc-component-attr-spec
	;

/*
	R442 component-initialization is = constant-expr
	or => null-init
	or => initial-data-target
*/
	component-initialization :
		'=' expr
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
		PROCEDURE ',' binding-attr-list ':' ':' type-bound-proc-decl-list
	|	PROCEDURE ':' ':' type-bound-proc-decl-list
	|	PROCEDURE type-bound-proc-decl-list
	|	PROCEDURE '(' NAME_LITERAL ')' ',' binding-attr-list ':' ':' name-list
	;

/*
	R449 type-bound-proc-decl is binding-name [ => procedure-name ]
*/
	type-bound-proc-decl :
		NAME_LITERAL '=' '>' NAME_LITERAL
	|	NAME_LITERAL
	;
	type-bound-proc-decl-list :
		type-bound-proc-decl-list ',' type-bound-proc-decl
	|	type-bound-proc-decl
	;

/*
	R450 type-bound-generic-stmt is GENERIC [ , access-spec ] :: generic-spec => binding-name-list
*/
	type-bound-generic-stmt :
		GENERIC ',' access-spec ':' ':' generic-spec '=' '>' name-list
	|	GENERIC ':' ':' generic-spec '=' '>' name-list
	;

/*
	R451 binding-attr is PASS [ (arg-name) ]
	or NOPASS
	or NON OVERRIDABLE
	or DEFERRED
	or access-spec
*/
	binding-attr :
		PASS '(' NAME_LITERAL ')'
	|	PASS
	|	NOPASS
	|	NON_OVERRIDABLE
	|	DEFERRED
	|	access-spec
	;
	binding-attr-list :
		binding-attr-list ',' binding-attr
	|	binding-attr
	;

/*
	R452 final-procedure-stmt is FINAL [ :: ] final-subroutine-name-list
*/
	final-procedure-stmt :
		FINAL ':' ':' name-list
	|	FINAL name-list
	;

/*
	R453 derived-type-spec is type-name [ ( type-param-spec-list ) ]
*/
	derived-type-spec :
		NAME_LITERAL '(' type-param-spec-list ')'
	|	NAME_LITERAL
	;

/*
	R454 type-param-spec is [ keyword = ] type-param-value
*/
	type-param-spec :
		NAME_LITERAL '=' type-param-value
	|	type-param-value
	;
	type-param-spec-list :
		type-param-spec-list ',' type-param-spec
	|	type-param-spec
	;

/*
	R455 structure-constructor is derived-type-spec ( [ component-spec-list ] )
*/
	structure-constructor :
		derived-type-spec '(' component-spec-list ')'
	|	derived-type-spec '(' ')'
	;

/*
	R456 component-spec is [ keyword = ] component-data-source
*/
	component-spec : 
		NAME_LITERAL '=' component-data-source
	|	component-data-source
	;
	component-spec-list :
		component-spec-list ',' component-spec
	|	component-spec
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
		enumerator-def-stmt +
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
		ENUMERATOR ':' ':' enumerator-list
	|	ENUMERATOR enumerator-list
	;

/*
	R461 enumerator is named-constant [ = scalar-int-constant-expr ]
*/
	enumerator :
		NAME_LITERAL '=' expr
	|	NAME_LITERAL
	;
	enumerator-list :
		enumerator-list ',' enumerator
	|	enumerator
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
	| type-spec ':' ':' ac-value-list
	| ac-value-list
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
	ac-value-list :
		ac-value-list ',' ac-value
	|	ac-value
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
		NAME_LITERAL '=' expr ',' expr ',' expr
	|	NAME_LITERAL '=' expr ',' expr 
	;

/*
	R475 ac-do-variable is do-variable
	
	not needed...
*/

/***************
	Clause 5:	
 ***************/

/*	
	R501 type-declaration-stmt is declaration-type-spec [ [ , attr-spec ] ... :: ] entity-decl-list
*/
	type-declaration-stmt :
		declaration-type-spec ( ( ',' attr-spec ) * ':' ':' ) ? entity-decl-list
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
		NAME_LITERAL ( '(' array-spec ')' ) ? ( '[' coarray-spec ']' ) ?	( '*' char-length ) ? initialization ?
	|	NAME_LITERAL '*' char-length
	|	NAME_LITERAL
	;
	entity-decl-list :
		entity-decl-list ',' entity-decl
	|	entity-decl
	;

/*
	R504 object-name is name
	
	not needed...
*/

/*
	R505 initialization is = constant-expr
	or => null-init
	or => initial-data-target
*/
	initialization :
		'=' expr
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
		BIND '(' 'C' ',' NAME_KEYWORD '=' expr ')'
	|	BIND '(' 'C' ')'
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
	deferred-coshape-spec-list :
		deferred-coshape-spec-list ',' deferred-coshape-spec
	|	deferred-coshape-spec
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
		expr
	;

/*
	R513 upper-cobound is specification-expr
*/
	upper-cobound :
		expr
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
		lower-bound ':' upper-bound
	|	upper-bound
	;
	explicit-shape-spec-list :
		explicit-shape-spec-list ',' explicit-shape-spec
	|	explicit-shape-spec
	;

/*
	R517 lower-bound is specification-expr
*/
	lower-bound :
		expr
	;

/*
	R518 upper-bound is specification-expr
*/
	upper-bound :
		expr
	;

/*
	R519 assumed-shape-spec is [ lower-bound ] :
*/
	assumed-shape-spec :
		lower-bound ':'
	|	':'
	;
	assumed-shape-spec-list :
		assumed-shape-spec-list ',' assumed-shape-spec
	|	assumed-shape-spec
	;

/*
	R520 deferred-shape-spec is :
*/
	deferred-shape-spec :
		':'
	;
	deferred-shape-spec-list :
		deferred-shape-spec-list ',' deferred-shape-spec
	|	deferred-shape-spec
	;

/*
	R521 assumed-size-spec is [ explicit-shape-spec , ]... [ lower-bound : ] *
*/
	assumed-size-spec :
		( explicit-shape-spec ',' ) * lower-bound ':' '*'
	|	( explicit-shape-spec ',' ) * '*'
	;
	assumed-size-spec-list :
		assumed-size-spec-list ',' assumed-size-spec
	|	assumed-size-spec
	;
	
/*
	R522 implied-shape-spec is [ lower-bound : ] *
*/
	implied-shape-spec :
		lower-bound ':' '*'
	|	'*'
	;
	implied-shape-spec-list :
		implied-shape-spec-list ',' implied-shape-spec
	|	implied-shape-spec
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
	R524 access-stmt is access-spec [ [ :: ] access-id-list ]
*/
	access-stmt :
		access-spec ( ( ':' ':' ) ? access-id-list ) ?
	;

/*
	R525 access-id is use-name
	or generic-spec
*/
	access-id :
		NAME_LITERAL
	|	generic-spec
	;
	access-id-list :
		access-id-list ',' access-id
	|	access-id
	;

/*
	R526 allocatable-stmt is ALLOCATABLE [ :: ] allocatable-decl-list
*/
	allocatable-stmt :
		ALLOCATABLE ':' ':' allocatable-decl-list
	|	ALLOCATABLE allocatable-decl-list
	;

/*
	R527 allocatable-decl is object-name [ ( array-spec ) ]
	[ lbracket coarray-spec rbracket ]
*/
	allocatable-decl :
		NAME_LITERAL ( '(' array-spec ')' ) ?
		( '[' coarray-spec ']' ) ?
	;
	allocatable-decl-list :
		allocatable-decl-list ',' allocatable-decl
	|	allocatable-decl
	;

/*
	R528 asynchronous-stmt is ASYNCHRONOUS [ :: ] object-name-list
*/
	asynchronous-stmt :
		ASYNCHRONOUS ':' ':' name-list
	|	ASYNCHRONOUS name-list
	;

/*
	R529 bind-stmt is language-binding-spec [ :: ] bind-entity-list
*/
	bind-stmt :
		language-binding-spec ':' ':' bind-entity-list
	|	language-binding-spec bind-entity-list
	;

/*
	R530 bind-entity is entity-name
	or / common-block-name /
*/
	bind-entity :
		NAME_LITERAL
	|	'/' NAME_LITERAL '/'
	;
	bind-entity-list :
		bind-entity-list ',' bind-entity
	|	bind-entity
	;

/*
	R531 codimension-stmt is CODIMENSION [ :: ] codimension-decl -list
*/
	codimension-stmt :
		CODIMENSION ':' ':' codimension-decl-list
	|	CODIMENSION codimension-decl-list
	;

/*
	R532 codimension-decl is coarray-name lbracket coarray-spec rbracket
*/
	codimension-decl :
		NAME_LITERAL '[' coarray-spec ']'
	;
	codimension-decl-list :
		codimension-decl-list ',' codimension-decl
	|	codimension-decl
	;
	
/*
	R533 contiguous-stmt is CONTIGUOUS [ :: ] object-name-list
*/
	contiguous-stmt :
		CONTIGUOUS ':' ':' name-list
	|	CONTIGUOUS name-list
	;

/*
	R534 data-stmt is DATA data-stmt-set [ [ , ] data-stmt-set ] ...
*/
	data-stmt :
		DATA data-stmt-set ( ',' data-stmt-set ) *
	|	DATA data-stmt-set ( data-stmt-set ) *
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
	data-stmt-object-list :
		data-stmt-object-list ',' data-stmt-object
	|	data-stmt-object
	;

/*
	R537 data-implied-do is ( data-i-do-object-list , data-i-do-variable =
	scalar-int-constant-expr ,
	scalar-int-constant-expr
	[ , scalar-int-constant-expr ] )
*/
	data-implied-do :
		'(' data-i-do-object-list ',' NAME_LITERAL '=' expr ',' expr ',' expr ')'
	|	'(' data-i-do-object-list ',' NAME_LITERAL '=' expr ',' expr  ')'
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
	data-i-do-object-list :
		data-i-do-object-list ',' data-i-do-object
	|	data-i-do-object
	;

/*
	R539 data-i-do-variable is do-variable
	
	not needed...
*/

/*
	R540 data-stmt-value is [ data-stmt-repeat * ] data-stmt-constant
*/
	data-stmt-value :
		data-stmt-repeat '*' data-stmt-constant
	|	data-stmt-constant
	;
	data-stmt-value-list :
		data-stmt-value-list ',' data-stmt-value
	|	data-stmt-value
	;

/*
	R541 data-stmt-repeat is scalar-int-constant
	or scalar-int-constant-subobject
*/
	data-stmt-repeat :
		constant
	|	constant-subobject
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
		constant
	|	constant-subobject
	|	signed-int-literal-constant
	|	signed-real-literal-constant
	|	null-init
	|	initial-data-target
	|	structure-constructor
	;

/*
	R543 int-constant-subobject is constant-subobject
	
	not needed...
*/

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
		DIMENSION ':' ':' NAME_LITERAL '(' array-spec ')' ( ',' NAME_LITERAL '(' array-spec ')' ) *
	|	DIMENSION NAME_LITERAL '(' array-spec ')' ( ',' NAME_LITERAL '(' array-spec ')' ) *
	;
	
/*
	R546 intent-stmt is INTENT ( intent-spec ) [ :: ] dummy-arg-name-list
*/
	intent-stmt :
		INTENT '(' intent-spec ')' ':' ':' name-list
	|	INTENT '(' intent-spec ')' name-list
	;

/*
	R547 optional-stmt is OPTIONAL [ :: ] dummy-arg-name-list
*/
	optional-stmt :
		OPTIONAL ':' ':' name-list
	|	OPTIONAL name-list
	;

/*
	R548 parameter-stmt is PARAMETER ( named-constant-def-list )
*/
	parameter-stmt :
		PARAMETER '(' named-constant-def-list ')'
	;
	
/*
	R549 named-constant-def is named-constant = constant-expr
*/
	named-constant-def :
		NAME_LITERAL '=' expr
	;
	named-constant-def-list :
		named-constant-def ( ',' named-constant-def ) *
	;

/*
	R550 pointer-stmt is POINTER [ :: ] pointer-decl-list
*/
	pointer-stmt : 
		POINTER ( ':' ':' ) pointer-decl-list
	;

/*
	R551 pointer-decl is object-name [ ( deferred-shape-spec-list ) ]
	or proc-entity-name
*/
	pointer-decl :
		NAME_LITERAL '(' deferred-shape-spec-list ')'
	|	NAME_LITERAL
	;
	pointer-decl-list :
		pointer-decl-list ',' pointer-decl
	|	pointer-decl
	;

/*
	R552 protected-stmt is PROTECTED [ :: ] entity-name-list
*/
	protected-stmt :
		PROTECTED ':' ':' name-list
	|	PROTECTED name-list
	;

/*
	R553 save-stmt is SAVE [ [ :: ] saved-entity-list ]
*/
	save-stmt :
		SAVE ':' ':' saved-entity-list
	|	SAVE saved-entity-list
	|	SAVE
	;

/*
	R554 saved-entity is object-name
	or proc-pointer-name
	or / common-block-name /
*/
	saved-entity :
		NAME_LITERAL
	|	'/' NAME_LITERAL '/'
	;
	saved-entity-list :
		saved-entity ( ',' saved-entity ) *
	;

/*
	R555 proc-pointer-name is name
	
	not needed...
*/

/*
	R556 target-stmt is TARGET [ :: ] target-decl-list
*/
	target-stmt :
		TARGET ':' ':' target-decl-list
	|	TARGET target-decl-list
	;

/*
	R557 target-decl is object-name [ ( array-spec ) ]
	[ lbracket coarray-spec rbracket ]
*/
	target-decl :
		NAME_LITERAL ( '(' array-spec ')' ) ?
		( '[' coarray-spec ']' ) ?
	;
	target-decl-list :
		target-decl ( ',' target-decl ) *
	;

/*
	R558 value-stmt is VALUE [ :: ] dummy-arg-name-list
*/
	value-stmt :
		VALUE ':' ':' name-list
	|	VALUE name-list
	;

/*
	R559 volatile-stmt is VOLATILE [ :: ] object-name-list
*/
	volatile-stmt :
		VOLATILE ':' ':' name-list
	|	VOLATILE name-list
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
	implicit-spec-list :
		implicit-spec ( ',' implicit-spec ) *
	;
	
/*
	R562 letter-spec is letter [ - letter ]
*/
	letter-spec :
		NAME_LITERAL '-' NAME_LITERAL // the NAME_LITERAL is used to amiguousy with LETTER_LITERAL
	|	NAME_LITERAL
	;
	letter-spec-list :
		letter-spec-list ',' letter-spec
	|	letter-spec
	;

/*
	R563 namelist-stmt is NAMELIST
	/ namelist-group-name / namelist-group-object-list
	[ [ , ] / namelist-group-name /
	namelist-group-object-list ] . . .
*/
	namelist-stmt :
		NAMELIST
		'/' NAME_LITERAL '/' name-list
		(  ',' ? '/' NAME_LITERAL '/' name-list ) *
	;
	
/*
	R564 namelist-group-object is variable-name
	
	not needed...
*/

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
	equivalence-set-list :
		equivalence-set ( ',' equivalence-set ) *
	;

/*
	R567 equivalence-object is variable-name
	or array-element
	or substring
*/
	equivalence-object :
		NAME_LITERAL
	|	array-element
	|	substring
	;
	equivalence-object-list :
		equivalence-object ( ',' equivalence-object ) *
	;

/*
	R568 common-stmt is COMMON
	[ / [ common-block-name ] / ] common-block-object-list
	[ [ , ] / [ common-block-name ] /
	common-block-object-list ] ...
*/
	common-stmt :
		COMMON
		( '/' NAME_LITERAL ? '/' ) ? common-block-object-list
		(  ',' ? '/' NAME_LITERAL ? '/' common-block-object-list ) *
	;

/*
	R569 common-block-object is variable-name [ ( array-spec ) ]
*/
	common-block-object :
		NAME_LITERAL '(' array-spec ')'
	|	NAME_LITERAL
	;
	common-block-object-list :
		common-block-object-list ',' common-block-object
	|	common-block-object
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
		NAME_LITERAL
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
	
	not needed...
*/

/*
	R604 logical-variable is variable
	
	not needed...
*/

/*
	R605 char-variable is variable
	
	not needed...
*/

/*
	R606 default-char-variable is variable
	
	not needed...
*/

/*
	R607 int-variable is variable
	
	not needed...
*/

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
		NAME_LITERAL
	|	array-element
	|	coindexed-named-object
	|	scalar-structure-component
	|	constant
	;
	
/*
	R610 substring-range is [ scalar-int-expr ] : [ scalar-int-expr ]
*/
	substring-range :
		expr ? ':' expr ?
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
		NAME_LITERAL ( '(' section-subscript-list ')' ) ? image-selector ?
	;

/*
	R613 structure-component is data-ref
*/
	structure-component :
		data-ref
	;
	scalar-structure-component :
		structure-component
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
		designator '%' NAME_LITERAL
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
		expr
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
	section-subscript-list :
		section-subscript ( ',' section-subscript ) *
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
		expr
	;
	
/*
	R623 vector-subscript is int-expr
*/
	vector-subscript :
		expr
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
		expr
	;
	cosubscript-list :
		cosubscript ( ',' cosubscript ) *
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
		ERRMSG '=' variable
	|	MOLD '=' expr
	|	SOURCE '=' expr
	|	STAT '=' variable
	;
	alloc-opt-list :
		alloc-opt ( ',' alloc-opt ) *
	;
	
/*
	R628 stat-variable is scalar-int-variable
	
	not needed...
*/

/*
	R629 errmsg-variable is scalar-default-char-variable
	
	not needed...
*/

/*
	R630 source-expr is expr
	
	not needed...
*/

/*
	R631 allocation is allocate-object [ ( allocate-shape-spec-list ) ]
	[ lbracket allocate-coarray-spec rbracket ]
*/
	allocation :
		allocate-object ( '(' allocate-shape-spec-list ')' ) ?
		( '[' allocate-coarray-spec ']' ) ?
	;
	allocation-list :
		allocation ( ',' allocation ) *
	;

/*
	R632 allocate-object is variable-name
	or structure-component
*/
	allocate-object :
		NAME_LITERAL
	|	structure-component
	;
	allocate-object-list : 
		allocate-object ( ',' allocate-object ) *
	;

/*
	R633 allocate-shape-spec is [ lower-bound-expr : ] upper-bound-expr
*/
	allocate-shape-spec :
		expr ':' expr
	|	expr
	;
	allocate-shape-spec-list :
		allocate-shape-spec ( ',' allocate-shape-spec ) *
	;

/*
	R634 lower-bound-expr is scalar-int-expr
	
	not needed...
*/

/*
	R635 upper-bound-expr is scalar-int-expr
	
	not needed...
*/

/*
	R636 allocate-coarray-spec is [ allocate-coshape-spec-list , ] [ lower-bound-expr : ] *
*/
	allocate-coarray-spec :
		( allocate-coshape-spec-list ',' ) ? ( expr ':' ) ? '*'
	;

/*
	R637 allocate-coshape-spec is [ lower-bound-expr : ] upper-bound-expr
*/
	allocate-coshape-spec :
		expr ':' expr
	|	expr
	;
	allocate-coshape-spec-list :
		allocate-coshape-spec ( ',' allocate-coshape-spec ) *
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
		NAME_LITERAL
	|	structure-component
	;
	pointer-object-list :
		pointer-object ( ',' pointer-object ) *
	;

/*
	R640 deallocate-stmt is DEALLOCATE ( allocate-object-list [ , dealloc-opt-list ] )
*/
	deallocate-stmt :
		DEALLOCATE '(' allocate-object-list ',' dealloc-opt-list ')'
	|	DEALLOCATE '(' allocate-object-list ')'
	;

/*
	R641 dealloc-opt is STAT = stat-variable
	or ERRMSG = errmsg-variable
*/
	dealloc-opt :
		STAT '=' variable
	|	ERRMSG '=' variable
	;
	dealloc-opt-list :
		dealloc-opt-list ',' dealloc-opt
	|	dealloc-opt
	;

/***************
	Clause 7:	
 ***************/

/*
	R701 primary is constant
	or designator
	or array-constructor
	or structure-constructor
	or function-reference
	or type-param-inquiry
	or type-param-name
	or ( expr )
*/
	primary :
		constant
	|	designator
	|	array-constructor
	|	structure-constructor
	|	function-reference
	|	type-param-inquiry
	|	NAME_LITERAL
	|	'(' expr ')'
	;

/*
	R702 level-1-expr is [ defined-unary-op ] primary
*/
	level-1-expr :
		defined-op primary
	|	primary
	;

/*
	R703 defined-unary-op is . letter [ letter ] ... .
	
	not needed...
*/

// defined as replacement:
	defined-op :
		'.' NAME_LITERAL '.'
	;

/*
	R704 mult-operand is level-1-expr [ power-op mult-operand ]
*/
	mult-operand :
		level-1-expr power-op mult-operand 
	|	level-1-expr
	;

/*
	R705 add-operand is [ add-operand mult-op ] mult-operand
*/
	add-operand :
		add-operand mult-op mult-operand
	| 	mult-operand
	;

/*
	R706 level-2-expr is [ [ level-2-expr ] add-op ] add-operand
*/
	level-2-expr :
		level-2-expr add-op add-operand
	|	add-op add-operand
	|	add-operand
	;

/*
	R707 power-op is **
*/
	power-op :
		'*' '*'
	;

/*
	R708 mult-op is *
	or /
*/
	mult-op :
		'*'
	|	'/'
	;

/*
	R709 add-op is +
	or -
*/
	add-op :
		'+'
	|	'-'
	;

/*
	R710 level-3-expr is [ level-3-expr concat-op ] level-2-expr
*/
	level-3-expr :
		level-3-expr concat-op level-2-expr
	|	level-2-expr
	;

/*
	R711 concat-op is //
*/
	concat-op :
		'/'	'/'
	;

/*
	R712 level-4-expr is [ level-3-expr rel-op ] level-3-expr
*/
	level-4-expr :
		level-3-expr rel-op level-3-expr
	|	level-3-expr
	;

/*
	R713 rel-op is .EQ.
	or .NE.
	or .LT.
	or .LE.
	or .GT.
	or .GE.
	or ==
	or /=
	or <
	or <=
	or >
	or >=
*/
	rel-op :
		EQ
	|	NE
	|	LT
	|	LE
	|	GT
	|	GE
	|	'=' '='
	|	'/' '='
	|	'<'
	|	'<' '='
	|	'>'
	|	'>' '='
	;

/*
	R714 and-operand is [ not-op ] level-4-expr
*/
	and-operand :
		not-op level-4-expr
	|	level-4-expr
	;

/*
	R715 or-operand is [ or-operand and-op ] and-operand
*/
	or-operand :
		or-operand and-op and-operand
	|	and-operand
	;

/*
	R716 equiv-operand is [ equiv-operand or-op ] or-operand
*/
	equiv-operand :
		equiv-operand or-op or-operand
	|	or-operand
	;

/*
	R717 level-5-expr is [ level-5-expr equiv-op ] equiv-operand
*/
	level-5-expr :
		level-5-expr equiv-op equiv-operand
	|	equiv-operand
	;

/*
	R718 not-op is .NOT.
*/
	not-op :
		NOT
	;

/*
	R719 and-op is .AND.
*/
	and-op :
		AND
	;

/*
	R720 or-op is .OR.
*/
	or-op :
		OR
	;

/*
	R721 equiv-op is .EQV.
	or .NEQV.
*/
	equiv-op :
		EQV
	|	NEQV
	;

/*
	R722 expr is [ expr defined-binary-op ] level-5-expr
*/
	expr :
		expr defined-op level-5-expr
	|	level-5-expr
	;

/*
	R723 defined-binary-op is . letter [ letter ] ... .
	
	not needed...
*/

/*
	R724 logical-expr is expr
	
	not needed...
*/

/*
	R725 default-char-expr is expr
	
	not needed...
*/

/*
	R726 int-expr is expr
	
	not needed...
*/

/*
	R727 numeric-expr is expr
	
	not needed...
*/

/*
	R728 specification-expr is scalar-int-expr
	
	not needed...
*/

/*
	R729 constant-expr is expr
	
	not needed...
*/

/*
	R730 default-char-constant-expr is default-char-expr
	
	not needed...
*/

/*
	R731 int-constant-expr is int-expr
	
	not needed...
*/
	

/*
	R732 assignment-stmt is variable = expr
*/
	assignment-stmt :
		variable '=' expr
	;

/*
	R733 pointer-assignment-stmt is data-pointer-object [ (bounds-spec-list) ] => data-target
	or data-pointer-object (bounds-remapping-list ) => data-target
	or proc-pointer-object => proc-target
*/
	pointer-assignment-stmt :
		data-pointer-object ( '(' bounds-spec-list ')' ) ? '=' '>' data-target
	|	data-pointer-object '(' bounds-remapping-list ')' '=' '>' data-target
	|	proc-pointer-object '=' '>' proc-target
	;

/*
	R734 data-pointer-object is variable-name
	or scalar-variable % data-pointer-component-name
*/
	data-pointer-object :
		NAME_LITERAL
	|	variable '%' NAME_LITERAL
	;

/*
	R735 bounds-spec is lower-bound-expr :
*/
	bounds-spec :
		expr ':'
	;
	bounds-spec-list :
		bounds-spec ( ',' bounds-spec ) *
	;

/*
	R736 bounds-remapping is lower-bound-expr : upper-bound-expr
*/
	bounds-remapping :
		expr ':' expr
	;
	bounds-remapping-list :
		bounds-remapping ( ',' bounds-remapping ) *
	;

/*
	R737 data-target is variable
*/
	data-target :
		variable
	;

/*
	R738 proc-pointer-object is proc-pointer-name
	or proc-component-ref
*/
	proc-pointer-object :
		NAME_LITERAL
	|	proc-component-ref
	;

/*
	R739 proc-component-ref is scalar-variable % procedure-component-name
*/
	proc-component-ref :
		variable '%' NAME_LITERAL
	;

/*
	R740 proc-target is expr
	or procedure-name
	or proc-component-ref
*/
	proc-target :
		expr
	|	NAME_LITERAL
	|	proc-component-ref
	;

/*
	R741 where-stmt is WHERE ( mask-expr ) where-assignment-stmt
*/
	where-stmt :
		WHERE '(' expr ')' where-assignment-stmt
	;

/*
	R742 where-construct is where-construct-stmt
	[ where-body-construct ] ...
	[ masked-elsewhere-stmt
	[ where-body-construct ] ... ] ...
	[ elsewhere-stmt
	[ where-body-construct ] ... ]
	end-where-stmt
*/
	where-construct :
		where-construct-stmt
		where-body-construct *
		( masked-elsewhere-stmt	where-body-construct * ) *
		( elsewhere-stmt where-body-construct * ) ?
		end-where-stmt
	;

/*
	R743 where-construct-stmt is [where-construct-name:] WHERE ( mask-expr )
*/
	where-construct-stmt :
		( NAME_LITERAL ':' ) ? WHERE '(' expr ')'
	;

/*
	R744 where-body-construct is where-assignment-stmt
	or where-stmt
	or where-construct
*/
	where-body-construct :
		where-assignment-stmt
	|	where-stmt
	|	where-construct
	;

/*
	R745 where-assignment-stmt is assignment-stmt
*/
	where-assignment-stmt :
		assignment-stmt
	;

/*
	R746 mask-expr is logical-expr
	
	not needed...
*/

/*
	R747 masked-elsewhere-stmt is ELSEWHERE (mask-expr) [where-construct-name]
*/
	masked-elsewhere-stmt :
		ELSEWHERE '(' expr ')' NAME_LITERAL ?
	;

/*
	R748 elsewhere-stmt is ELSEWHERE [where-construct-name]
*/
	elsewhere-stmt :
		ELSEWHERE NAME_LITERAL ?
	;

/*
	R749 end-where-stmt is END WHERE [where-construct-name]
*/
	end-where-stmt :
		END_WHERE NAME_LITERAL ?
	;

/*
	R750 forall-construct is forall-construct-stmt
	[forall-body-construct ] ...
	end-forall-stmt
*/
	forall-construct :	
		forall-construct-stmt
		forall-body-construct *
		end-forall-stmt
	;

/*
	R751 forall-construct-stmt is [forall-construct-name :] FORALL forall-header
*/
	forall-construct-stmt :
		( NAME_LITERAL ':' ) ? FORALL forall-header
	;

/*
	R752 forall-header is ( [ type-spec :: ] forall-triplet-spec-list [, scalar-mask-expr] )
*/
	forall-header :
		'(' ( type-spec ':' ':' ) ? forall-triplet-spec-list ( ',' expr ) ? ')'
	;

/*
	R753 forall-triplet-spec is index-name = forall-limit : forall-limit [ : forall-step]
*/
	forall-triplet-spec :
		NAME_LITERAL '=' forall-limit ':' forall-limit ( ':' forall-step ) ?
	;
	forall-triplet-spec-list :
		forall-triplet-spec ( ',' forall-triplet-spec ) *
	;

/*
	R754 forall-limit is scalar-int-expr
*/
	forall-limit :
		expr
	;

/*
	R755 forall-step is scalar-int-expr
*/
	forall-step :
		expr
	;

/*
	R756 forall-body-construct is forall-assignment-stmt
	or where-stmt
	or where-construct
	or forall-construct
	or forall-stmt
*/
	forall-body-construct :
		forall-assignment-stmt
	|	where-stmt
	|	where-construct
	|	forall-construct
	|	forall-stmt
	;

/*
	R757 forall-assignment-stmt is assignment-stmt
	or pointer-assignment-stmt
*/
	forall-assignment-stmt :
		assignment-stmt
	|	pointer-assignment-stmt
	;

/*
	R758 end-forall-stmt is END FORALL [forall-construct-name ]
*/
	end-forall-stmt :
		END_FORALL NAME_LITERAL ?
	;

/*
	R759 forall-stmt is FORALL forall-header forall-assignment-stmt
*/
	forall-stmt :
		FORALL forall-header forall-assignment-stmt
	;

/***************
	Clause 8:	
 ***************/

/*
	R801 block is [ execution-part-construct ] ...
*/
	block :
		execution-part-construct *
	;

/*
	R802 associate-construct is associate-stmt
	block
	end-associate-stmt
*/
	associate-construct :
		associate-stmt
		block
		end-associate-stmt
	;

/*
	R803 associate-stmt is [ associate-construct-name : ] ASSOCIATE
	(association-list )
*/
	associate-stmt :
		( NAME_LITERAL ':' ) ? ASSOCIATE '(' association-list ')'
	;

/*
	R804 association is associate-name => selector
*/
	association :
		NAME_LITERAL '=' '>' selector
	;
	association-list :
		association ( ',' association ) *
	;

/*
	R805 selector is expr
	or variable
*/
	selector :
		expr
	|	variable
	;

/*
	R806 end-associate-stmt is END ASSOCIATE [ associate-construct-name ]
*/
	end-associate-stmt :
		END_ASSOCIATE NAME_LITERAL ?
	;

/*
	R807 block-construct is block-stmt
	[ specification-part ]
	block
	end-block-stmt
*/
	block-construct :
		block-stmt
		specification-part ?
		block
		end-block-stmt
	;

/*
	R808 block-stmt is [ block-construct-name : ] BLOCK
*/
	block-stmt :
		( NAME_LITERAL ':' ) ? BLOCK
	;

/*
	R809 end-block-stmt is END BLOCK [ block-construct-name ]
*/
	end-block-stmt :
		END_BLOCK NAME_LITERAL ?
	;

/*
	R810 critical-construct is critical-stmt
	block
	end-critical-stmt
*/
	critical-construct :
		critical-stmt
		block
		end-critical-stmt
	;

/*
	R811 critical-stmt is [ critical-construct-name : ] CRITICAL
*/
	critical-stmt :
		( NAME_LITERAL ':' ) ? CRITICAL
	;

/*
	R812 end-critical-stmt is END CRITICAL [ critical-construct-name ]
*/
	end-critical-stmt :
		END_CRITICAL NAME_LITERAL ?
	;

/*
	R813 do-construct is block-do-construct
	or nonblock-do-construct
*/
	do-construct :
		block-do-construct
	|	nonblock-do-construct
	;
	
/*
	R814 block-do-construct is do-stmt
	do-block
	end-do
*/
	block-do-construct :
		do-stmt
		block
		end-do
	;

/*
	R815 do-stmt is label-do-stmt
	or nonlabel-do-stmt
*/
	do-stmt :
		label-do-stmt
	|	nonlabel-do-stmt
	;

/*
	R816 label-do-stmt is [ do-construct-name : ] DO label [ loop-control ]
*/
	label-do-stmt :
		( NAME_LITERAL ':' ) ? DO INT_LITERAL_CONSTANT loop-control ?
	;

/*
	R817 nonlabel-do-stmt is [ do-construct-name : ] DO [ loop-control ]
*/
	nonlabel-do-stmt :
		( NAME_LITERAL ':' ) ? DO loop-control ?
	;

/*
	R818 loop-control is [ , ] do-variable = scalar-int-expr , scalar-int-expr
	[ , scalar-int-expr ]
	or [ , ] WHILE ( scalar-logical-expr )
	or [ , ] CONCURRENT forall-header
*/
	loop-control :
		',' ? NAME_LITERAL '=' expr ',' expr ( ',' expr ) ?
	|	',' ? WHILE '(' expr ')'
	|	',' ? CONCURRENT forall-header
	;

/*
	R819 do-variable is scalar-int-variable-name
	
	not needed...
*/

/*
	R820 do-block is block
	
	not needed...
*/

/*
	R821 end-do is end-do-stmt
	or continue-stmt
*/
	end-do :
		end-do-stmt
	|	continue-stmt
	;

/*
R822 end-do-stmt is END DO [ do-construct-name ]
*/
	end-do-stmt :
		END_DO NAME_LITERAL ?
	;

/*
	R823 nonblock-do-construct is action-term-do-construct
	or outer-shared-do-construct
*/
	nonblock-do-construct :
		action-term-do-construct
	|	outer-shared-do-construct
	;

/*
	R824 action-term-do-construct is label-do-stmt
	do-body
	do-term-action-stmt
*/
	action-term-do-construct :
		label-do-stmt
		do-body
		action-stmt
	;

/*
	R825 do-body is [ execution-part-construct ] ...
*/
	do-body :
		execution-part-construct *
	;

/*
	R826 do-term-action-stmt is action-stmt
	
	not needed...
*/

/*
	R827 outer-shared-do-construct is label-do-stmt
	do-body
	shared-term-do-construct
*/
	outer-shared-do-construct :
		label-do-stmt
		do-body
		shared-term-do-construct
	;

/*
	R828 shared-term-do-construct is outer-shared-do-construct
	or inner-shared-do-construct
*/
	shared-term-do-construct :
		outer-shared-do-construct
	|	inner-shared-do-construct
	;

/*
	R829 inner-shared-do-construct is label-do-stmt
	do-body
	do-term-shared-stmt
*/
	inner-shared-do-construct :
		label-do-stmt
		do-body
		do-term-shared-stmt
	;

/*
	R830 do-term-shared-stmt is action-stmt
*/
	do-term-shared-stmt :
		action-stmt
	;

/*
	R831 cycle-stmt is CYCLE [ do-construct-name ]
*/
	cycle-stmt :
		CYCLE NAME_LITERAL ?
	;

/*
	R832 if-construct is if-then-stmt
	block
	[ else-if-stmt
	block ] ...
	[ else-stmt
	block ]
	end-if-stmt
*/
	if-construct :
		if-then-stmt
		block
		( else-if-stmt block ) *
		( else-stmt block ) ?
		end-if-stmt
	;

/*
	R833 if-then-stmt is [ if-construct-name : ] IF ( scalar-logical-expr ) THEN
*/
	if-then-stmt :
		( NAME_LITERAL ':' ) ? IF '(' expr ')' THEN
	;

/*
	R834 else-if-stmt is ELSE IF ( scalar-logical-expr ) THEN [ if-construct-name ]
*/
	else-if-stmt :
		ELSE_IF '(' expr ')' THEN NAME_LITERAL ?
	;

/*
	R835 else-stmt is ELSE [ if-construct-name ]
*/
	else-stmt :
		ELSE NAME_LITERAL ?
	;

/*
	R836 end-if-stmt is END IF [ if-construct-name ]
*/
	end-if-stmt :
		END_IF NAME_LITERAL ?
	;

/*
	R837 if-stmt is IF ( scalar-logical-expr ) action-stmt
*/
	if-stmt :
		IF '(' expr ')' action-stmt
	;

/*
	R838 case-construct is select-case-stmt
	[ case-stmt
	block ] ...
	end-select-stmt
*/
	case-construct :
		select-case-stmt
		( case-stmt block ) *
		end-select-stmt
	;

/*
	R839 select-case-stmt is [ case-construct-name : ] SELECT CASE ( case-expr )
*/
	select-case-stmt :
		( NAME_LITERAL ':' ) ? SELECT_CASE '(' expr ')'
	;

/*
	R840 case-stmt is CASE case-selector [case-construct-name]
*/
	case-stmt :
		CASE case-selector NAME_LITERAL ?
	;

/*
	R841 end-select-stmt is END SELECT [ case-construct-name ]
*/
	end-select-stmt :
		END_SELECT NAME_LITERAL ?
	;

/*
	R842 case-expr is scalar-expr
	
	not needed...
*/

/*
	R843 case-selector is ( case-value-range-list )
	or DEFAULT
*/
	case-selector :
		'(' case-value-range-list ')'
	|	DEFAULT
	;

/*
	R844 case-value-range is case-value
	or case-value :
	or : case-value
	or case-value : case-value
*/
	case-value-range :
		case-value
	|	case-value ':'
	|	':' case-value
	|	case-value ':' case-value
	;
	case-value-range-list :
		case-value-range ( ',' case-value-range ) *
	;

/*
	R845 case-value is scalar-constant-expr
*/
	case-value :
		expr
	;

/*
	R846 select-type-construct is select-type-stmt
	[ type-guard-stmt
	block ] ...
	end-select-type-stmt
*/
	select-type-construct :
		select-type-stmt
		( type-guard-stmt block ) *
		end-select-type-stmt
	;

/*
	R847 select-type-stmt is [ select-construct-name : ] SELECT TYPE
	( [ associate-name => ] selector )
*/
	select-type-stmt :
		( NAME_LITERAL ':' ) ? SELECT TYPE	'(' ( NAME_LITERAL '=' '>' ) ? selector ')'
	;

/*
	R848 type-guard-stmt is TYPE IS ( type-spec ) [ select-construct-name ]
	or CLASS IS ( derived-type-spec ) [ select-construct-name ]
	or CLASS DEFAULT [ select-construct-name ]
*/
	type-guard-stmt :
		TYPE_IS '(' type-spec ')' NAME_LITERAL ?
	|	CLASS_IS '(' derived-type-spec ')' NAME_LITERAL ?
	|	CLASS_DEFAULT NAME_LITERAL ?
	;

/*
	R849 end-select-type-stmt is END SELECT [ select-construct-name ]
*/
	end-select-type-stmt :
		END_SELECT NAME_LITERAL ?
	;

/*
	R850 exit-stmt is EXIT [ construct-name ]
*/
	exit-stmt :
		EXIT NAME_LITERAL ?
	;

/*
	R851 goto-stmt is GO TO label
*/
	goto-stmt :
		GO_TO INT_LITERAL_CONSTANT
	;

/*
	R852 computed-goto-stmt is GO TO ( label -list ) [ , ] scalar-int-expr
*/
	computed-goto-stmt :
		GO_TO '(' label-list ')' ',' ? expr
	;

/*
R853 arithmetic-if-stmt is IF ( scalar-numeric-expr ) label , label , label
*/
	arithmetic-if-stmt :
		IF '(' expr ')' INT_LITERAL_CONSTANT ',' INT_LITERAL_CONSTANT ',' INT_LITERAL_CONSTANT
	;

/*
	R854 continue-stmt is CONTINUE
*/
	continue-stmt :
		CONTINUE
	;

/*
	R855 stop-stmt is STOP [ stop-code ]
*/
	stop-stmt :
		STOP stop-code ?
	;

/*
	R856 error-stop-stmt is ERROR STOP [ stop-code ]
*/
	error-stop-stmt :
		ERROR_STOP stop-code ?
	;

/*
	R857 stop-code is scalar-default-char-constant-expr
	or scalar-int-constant-expr
*/
	stop-code :
		expr
	;

/*
	R858 sync-all-stmt is SYNC ALL [ ( [ sync-stat-list ] ) ]
*/
	sync-all-stmt :
		SYNC_ALL ( '(' sync-stat-list ? ')' ) ?
	;

/*
	R859 sync-stat is STAT = stat-variable
	or ERRMSG = errmsg-variable
*/
	sync-stat :
		STAT '=' variable
	|	ERRMSG '=' variable
	;
	sync-stat-list :
		sync-stat ( ',' sync-stat ) *
	;

/*
	R860 sync-images-stmt is SYNC IMAGES ( image-set [ , sync-stat-list ] )
*/
	sync-images-stmt :
		SYNC_IMAGES '(' image-set ( ',' sync-stat-list ) ? ')'
	;

/*
	R861 image-set is int-expr
	or *
*/
	image-set :
		expr
	|	'*'
	;

/*
	R862 sync-memory-stmt is SYNC MEMORY [ ( [ sync-stat-list ] ) ]
*/
	sync-memory-stmt :
		SYNC_MEMORY ( '(' sync-stat-list ? ')' ) ?
	;

/*
	R863 lock-stmt is LOCK ( lock-variable [ , lock-stat-list ] )
*/
	lock-stmt :
		LOCK '(' variable ( ',' lock-stat-list ) ? ')'
	;

/*
	R864 lock-stat is ACQUIRED LOCK = scalar-logical-variable
	or sync-stat
*/
	lock-stat :
		ACQUIRED_LOCK '=' variable
	|	sync-stat
	;
	lock-stat-list :
		lock-stat ( ',' lock-stat ) *
	;

/*
	R865 unlock-stmt is UNLOCK ( lock-variable [ , sync-stat-list ] )
*/
	unlock-stmt :
		UNLOCK '(' variable ( ',' sync-stat-list ) ? ')'
	;

/*
	R866 lock-variable is scalar-variable
	
	not needed...
*/

/***************
	Clause 9:	
 ***************/

/*
	R901 io-unit is file-unit-number
	or *
	or internal-file-variable
*/
	io-unit :
		file-unit-number
	|	'*'
	|	variable
	;

/*
	R902 file-unit-number is scalar-int-expr
*/
	file-unit-number :
		expr
	;

/*
	R903 internal-file-variable is char-variable
	
	not needed...
*/

/*
	R904 open-stmt is OPEN ( connect-spec-list )
*/
	open-stmt :
		OPEN '(' connect-spec-list ')'
	;

/*
	R905 connect-spec is [ UNIT = ] file-unit-number
	or ACCESS = scalar-default-char-expr
	or ACTION = scalar-default-char-expr
	or ASYNCHRONOUS = scalar-default-char-expr
	or BLANK = scalar-default-char-expr
	or DECIMAL = scalar-default-char-expr
	or DELIM = scalar-default-char-expr
	or ENCODING = scalar-default-char-expr
	or ERR = label
	or FILE = file-name-expr
	or FORM = scalar-default-char-expr
	or IOMSG = iomsg-variable
	or IOSTAT = scalar-int-variable
	or NEWUNIT = scalar-int-variable
	or PAD = scalar-default-char-expr
	or POSITION = scalar-default-char-expr
	or RECL = scalar-int-expr
	or ROUND = scalar-default-char-expr
	or SIGN = scalar-default-char-expr
	or STATUS = scalar-default-char-expr
*/
	connect-spec :
		( UNIT '=' ) ? file-unit-number
	|	ACCESS '=' expr
	|	ACTION '=' expr
	|	ASYNCHRONOUS '=' expr
	|	BLANK '=' expr
	|	DECIMAL '=' expr
	|	DELIM '=' expr
	|	ENCODING '=' expr
	|	ERR '=' INT_LITERAL_CONSTANT
	|	FILE '=' expr
	|	FORM '=' expr
	|	IOMSG '=' iomsg-variable
	|	IOSTAT '=' variable
	|	NEWUNIT '=' variable
	|	PAD '=' expr
	|	POSITION '=' expr
	|	RECL '=' expr
	|	ROUND '=' expr
	|	SIGN '=' expr
	|	STATUS '=' expr
	;
	connect-spec-list :
		connect-spec ( ',' connect-spec ) *
	;

/*
	R906 file-name-expr is scalar-default-char-expr
	
	not needed...
*/

/*
	R907 iomsg-variable is scalar-default-char-variable
*/
	iomsg-variable :
		variable
	;

/*
	R908 close-stmt is CLOSE ( close-spec-list )
*/
	close-stmt :
		CLOSE '(' close-spec-list ')'
	;

/*
	R909 close-spec is [ UNIT = ] file-unit-number
	or IOSTAT = scalar-int-variable
	or IOMSG = iomsg-variable
	or ERR = label
	or STATUS = scalar-default-char-expr
*/
	close-spec :
		( UNIT '=' ) ? file-unit-number
	|	IOSTAT '=' variable
	|	IOMSG '=' iomsg-variable
	|	ERR '=' INT_LITERAL_CONSTANT
	|	STATUS '=' expr
	;
	close-spec-list :
		close-spec ( ',' close-spec ) *
	;

/*
	R910 read-stmt is READ ( io-control-spec-list ) [ input-item-list ]
	or READ format [ , input-item-list ]
*/
	read-stmt :
		READ '(' io-control-spec-list ')' input-item-list ?
	|	READ format ( ',' input-item-list ) ?
	;

/*
	R911 write-stmt is WRITE ( io-control-spec-list ) [ output-item-list ]
*/
	write-stmt :
		WRITE '(' io-control-spec-list ')' output-item-list ?
	;

/*
	R912 print-stmt is PRINT format [ , output-item-list ]
*/
	print-stmt :
		PRINT format ( ',' output-item-list ) ?
	;

/*
	R913 io-control-spec is [ UNIT = ] io-unit
	or [ FMT = ] format
	or [ NML = ] NAME_LITERAL
	or ADVANCE = expr
	or ASYNCHRONOUS = expr
	or BLANK = expr
	or DECIMAL = expr
	or DELIM = expr
	or END = label
	or EOR = label
	or ERR = label
	or ID = id-variable
	or IOMSG = iomsg-variable
	or IOSTAT = variable
	or PAD = expr
	or POS = expr
	or REC = expr
	or ROUND = expr
	or SIGN = expr
	or SIZE = variable
*/
	io-control-spec :
		( UNIT '=' ) ? io-unit
	|	( FMT '=' ) ? format
	|	( NML '=' ) ? NAME_LITERAL
	|	ADVANCE '=' expr
	|	ASYNCHRONOUS '=' expr
	|	BLANK '=' expr
	|	DECIMAL '=' expr
	|	DELIM '=' expr
	|	END '=' INT_LITERAL_CONSTANT
	|	EOR '=' INT_LITERAL_CONSTANT
	|	ERR '=' INT_LITERAL_CONSTANT
	|	ID '=' variable
	|	IOMSG '=' iomsg-variable
	|	IOSTAT '=' variable
	|	PAD '=' expr
	|	POS '=' expr
	|	REC '=' expr
	|	ROUND '=' expr
	|	SIGN '=' expr
	|	SIZE '=' variable
	;
	io-control-spec-list :
		io-control-spec ( ',' io-control-spec ) *
	;

/*
	R914 id-variable is scalar-int-variable
	
	not needed...
*/

/*
	R915 format is default-char-expr
	or label
	or *
*/
	format :
		expr
	|	INT_LITERAL_CONSTANT
	|	'*'
	;

/*
	R916 input-item is variable
	or io-implied-do
*/
	input-item :
		variable
	|	io-implied-do
	;
	input-item-list :
		input-item ( ',' input-item ) *
	;

/*
	R917 output-item is expr
	or io-implied-do
*/
	output-item :
		expr
	|	io-implied-do
	;
	output-item-list :
		output-item ( ',' output-item ) *
	;

/*
	R918 io-implied-do is ( io-implied-do-object-list , io-implied-do-control )
*/
	io-implied-do :
		'(' io-implied-do-object-list ',' io-implied-do-control ')'
	;

/*
	R919 io-implied-do-object is input-item
	or output-item
*/
	io-implied-do-object :
		input-item
	|	output-item
	;
	io-implied-do-object-list :
		io-implied-do-object ( ',' io-implied-do-object ) *
	;

/*
	R920 io-implied-do-control is do-variable = scalar-int-expr ,
	scalar-int-expr [ , scalar-int-expr ]
*/
	io-implied-do-control :
		NAME_LITERAL '=' expr ',' expr ( ',' expr ) ?
	;

/*
	R921 dtv-type-spec is TYPE( derived-type-spec )
	or CLASS( derived-type-spec )
*/
	dtv-type-spec :
		TYPE '(' derived-type-spec ')'
	|	CLASS '(' derived-type-spec ')'
	;

/*
	R922 wait-stmt is WAIT (wait-spec-list)
*/
	wait-stmt :
		WAIT '(' wait-spec-list ')'
	;

/*
	R923 wait-spec is [ UNIT = ] file-unit-number
	or END = label
	or EOR = label
	or ERR = label
	or ID = scalar-int-expr
	or IOMSG = iomsg-variable
	or IOSTAT = scalar-int-variable
*/
	wait-spec : 
		( UNIT '=' ) ? file-unit-number
	|	END '=' INT_LITERAL_CONSTANT
	|	EOR '=' INT_LITERAL_CONSTANT
	|	ERR '=' INT_LITERAL_CONSTANT
	|	ID '=' expr
	|	IOMSG '=' iomsg-variable
	|	IOSTAT '=' variable
	;
	wait-spec-list :
		wait-spec ( ',' wait-spec ) *
	;

/*
	R924 backspace-stmt is BACKSPACE file-unit-number
	or BACKSPACE ( position-spec-list )
*/
	backspace-stmt :
		BACKSPACE file-unit-number
	|	BACKSPACE '(' position-spec-list ')'
	;

/*
	R925 endfile-stmt is ENDFILE file-unit-number
	or ENDFILE ( position-spec-list )
*/
	endfile-stmt :
		ENDFILE file-unit-number
	|	ENDFILE '(' position-spec-list ')'
	;

/*
	R926 rewind-stmt is REWIND file-unit-number
	or REWIND ( position-spec-list )
*/
	rewind-stmt :
		REWIND file-unit-number
	|	REWIND '(' position-spec-list ')'
	;

/*
	R927 position-spec is [ UNIT = ] file-unit-number
	or IOMSG = iomsg-variable
	or IOSTAT = scalar-int-variable
	or ERR = label
*/
	position-spec :
		( UNIT '=' ) ? file-unit-number
	|	IOMSG '=' iomsg-variable
	|	IOSTAT '=' variable
	|	ERR '=' INT_LITERAL_CONSTANT
	;
	position-spec-list :
		position-spec ( ',' position-spec ) *
	;

/*
	R928 flush-stmt is FLUSH file-unit-number
	or FLUSH ( 
	flush-spec-list )
*/
	flush-stmt :
		FLUSH file-unit-number
	|	FLUSH '(' flush-spec-list ')'
	;

/*
	R929 flush-spec is [UNIT =] file-unit-number
	or IOSTAT = scalar-int-variable
	or IOMSG = iomsg-variable
	or ERR = label
*/
	flush-spec : 
		( UNIT '=' ) ? file-unit-number
	|	IOSTAT '=' variable
	|	IOMSG '=' iomsg-variable
	|	ERR '=' INT_LITERAL_CONSTANT
	;
	flush-spec-list :
		flush-spec ( ',' flush-spec ) *
	;

/*
	R930 inquire-stmt is INQUIRE ( inquire-spec-list )
	or INQUIRE ( IOLENGTH = scalar-int-variable )
	output-item-list
*/
	inquire-stmt :
		INQUIRE '(' inquire-spec-list ')'
	|	INQUIRE '(' IOLENGTH '=' variable ')' output-item-list
	;

/*
	R931 inquire-spec is [ UNIT = ] file-unit-number
	or FILE = file-name-expr
	or ACCESS = scalar-default-char-variable
	or ACTION = scalar-default-char-variable
	or ASYNCHRONOUS = scalar-default-char-variable
	or BLANK = scalar-default-char-variable
	or DECIMAL = scalar-default-char-variable
	or DELIM = scalar-default-char-variable
	or DIRECT = scalar-default-char-variable
	or ENCODING = scalar-default-char-variable
	or ERR = label
	or EXIST = scalar-logical-variable
	or FORM = scalar-default-char-variable
	or FORMATTED = scalar-default-char-variable
	or ID = scalar-int-expr
	or IOMSG = iomsg-variable
	or IOSTAT = scalar-int-variable
	or NAME = scalar-default-char-variable
	or NAMED = scalar-logical-variable
	or NEXTREC = scalar-int-variable
	or NUMBER = scalar-int-variable
	or OPENED = scalar-logical-variable
	or PAD = scalar-default-char-variable
	or PENDING = scalar-logical-variable
	or POS = scalar-int-variable
	or POSITION = scalar-default-char-variable
	or READ = scalar-default-char-variable
	or READWRITE = scalar-default-char-variable
	or RECL = scalar-int-variable
	or ROUND = scalar-default-char-variable
	or SEQUENTIAL = scalar-default-char-variable
	or SIGN = scalar-default-char-variable
	or SIZE = scalar-int-variable
	or STREAM = scalar-default-char-variable
	or UNFORMATTED = scalar-default-char-variable
	or WRITE = scalar-default-char-variable
*/
	inquire-spec :
		( UNIT '=' ) ? file-unit-number
	|	FILE '=' expr
	|	ACCESS '=' variable
	|	ACTION '=' variable
	|	ASYNCHRONOUS '=' variable
	|	BLANK '=' variable
	|	DECIMAL '=' variable
	|	DELIM '=' variable
	|	DIRECT '=' variable
	|	ENCODING '=' variable
	|	ERR '=' INT_LITERAL_CONSTANT
	|	EXIST '=' variable
	|	FORM '=' variable
	|	FORMATTED '=' variable
	|	ID '=' expr
	|	IOMSG '=' iomsg-variable
	|	IOSTAT '=' variable
	|	NAME_KEYWORD '=' variable
	|	NAMED '=' variable
	|	NEXTREC '=' variable
	|	NUMBER '=' variable
	|	OPENED '=' variable
	|	PAD '=' variable
	|	PENDING '=' variable
	|	POS '=' variable
	|	POSITION '=' variable
	|	READ '=' variable
	|	READWRITE '=' variable
	|	RECL '=' variable
	|	ROUND '=' variable
	|	SEQUENTIAL '=' variable
	|	SIGN '=' variable
	|	SIZE '=' variable
	|	STREAM '=' variable
	|	UNFORMATTED '=' variable
	|	WRITE '=' variable
	;
	inquire-spec-list :
		inquire-spec ( ',' inquire-spec ) *
	;

/***************
	Clause 10:	
 ***************/

/*
	R1001 format-stmt is FORMAT format-specification
*/
	format-stmt :
		FORMAT format-specification
	;

/*
	R1002 format-specification is ( [ format-items ] )
	or ( [ format-items, ] unlimited-format-item )
*/
	format-specification :
		'(' format-items ? ')'
	|	'(' ( format-items ',' ) ? unlimited-format-item ')'
	;

/*
	R1003 format-items is format-item [ [ , ] format-item ] ...
*/
	format-items :
		format-item ( ',' ? format-item ) *
	;

/*
	R1004 format-item is [ r ] data-edit-desc
	or control-edit-desc
	or char-string-edit-desc
	or [ r ] ( format-items )
*/
	format-item :	
		r ? data-edit-desc
	|	control-edit-desc
	|	CHAR_LITERAL_CONSTANT
	|	r ? '(' format-items ')'
	;

/*
	R1005 unlimited-format-item is * ( format-items )
*/
	unlimited-format-item :
		'*' '(' format-items ')'
	;

/*
	R1006 r is int-literal-constant
*/
	r :
		INT_LITERAL_CONSTANT
	;

/*
	R1007 data-edit-desc is I w [ . m ]
	or B w [ . m ]
	or O w [ . m ]
	or Z w [ . m ]
	or F w . d
	or E w . d [ E e ]
	or EN w . d [ E e ]
	or ES w . d [ E e ]
	or G w [ . d [ E e ] ]
	or L w
	or A [ w ]
	or D w . d
	or DT [ char-literal-constant ] [ ( v-list ) ]
*/
	data-edit-desc :
		'I' w ( '.' m ) ?
	|	'B' w ( '.' m ) ?
	|	'O' w ( '.' m ) ?
	|	'Z' w ( '.' m ) ?
	|	'F' w '.' d
	|	'E' w '.' d ( 'E' e ) ?
	|	'EN' w '.' d ( 'E' e ) ?
	|	'ES' w '.' d ( 'E' e ) ?
	|	'G' w ( '.' d ( 'E' e ) ? ) ?
	|	'L' w
	|	'A' w ?
	|	'D' w '.' d
	|	'DT' CHAR_LITERAL_CONSTANT ? ( '(' v-list ')' ) ?
	;

/*
	R1008 w is int-literal-constant
*/
	w :
		INT_LITERAL_CONSTANT
	;

/*
	R1009 m is int-literal-constant
*/
	m :
		INT_LITERAL_CONSTANT
	;

/*
	R1010 d is int-literal-constant
*/
	d :
		INT_LITERAL_CONSTANT
	;

/*
	R1011 e is int-literal-constant
*/
	e :
		INT_LITERAL_CONSTANT
	;

/*
	R1012 v is signed-int-literal-constant
*/
	v :
		signed-int-literal-constant
	;
	v-list :
		v ( ',' v ) *
	;

/*
	R1013 control-edit-desc is position-edit-desc
	or [ r ] /
	or :
	or sign-edit-desc
	or k P
	or blank-interp-edit-desc
	or round-edit-desc
	or decimal-edit-desc
*/
	control-edit-desc :
		position-edit-desc
	|	r ? '/'
	|	':'
	|	sign-edit-desc
	|	k 'P'
	|	blank-interp-edit-desc
	|	round-edit-desc
	|	decimal-edit-desc
	;

/*
	R1014 k is signed-int-literal-constant
*/
	k :
		signed-int-literal-constant
	;

/*
	R1015 position-edit-desc is T n
	or TL n
	or TR n
	or n X
*/
	position-edit-desc :
		'T' n
	|	'TL' n
	|	'TR' n
	|	n 'X'
	;

/*
	R1016 n is int-literal-constant
*/
	n :
		INT_LITERAL_CONSTANT
	;

/*
	R1017 sign-edit-desc is SS
	or SP
	or S
*/
	sign-edit-desc :
		'SS'
	|	'SP'
	|	'S'
	;

/*
	R1018 blank-interp-edit-desc is BN
	or BZ
*/
	blank-interp-edit-desc :
		'BN'
	|	'BZ'
	;

/*
	R1019 round-edit-desc is RU
	or RD
	or RZ
	or RN
	or RC
	or RP
*/
	round-edit-desc :
		'RU'
	|	'RD'
	|	'RZ'
	|	'RN'
	|	'RC'
	|	'RP'
	;

/*
	R1020 decimal-edit-desc is DC
	or DP
*/
	decimal-edit-desc :
		'DC'
	|	'DP'
	;

/*
	R1021 char-string-edit-desc is char-literal-constant
	
	not needed...
*/

/*
	R1022 hex-digit-string is hex-digit [ hex-digit ] ...
	
	not needed...
*/

/***************
	Clause 11:	
 ***************/

/*
	R1101 main-program is [ program-stmt ]
	[ specification-part ]
	[ execution-part ]
	[ internal-subprogram-part ]
	end-program-stmt
*/
	main-program :
		program-stmt ?
		specification-part ?
		execution-part ?
		internal-subprogram-part ?
		end-program-stmt
	;

/*
	R1102 program-stmt is PROGRAM program-name
*/
	program-stmt :
		PROGRAM NAME_LITERAL
	;

/*
	R1103 end-program-stmt is END [ PROGRAM [ program-name ] ]
*/
	end-program-stmt :
		END ( PROGRAM NAME_LITERAL ? ) ?
	;

/*
	R1104 module is module-stmt
	[ specification-part ]
	[ module-subprogram-part ]
	end-module-stmt
*/
	module :
		module-stmt
		specification-part ?
		module-subprogram-part ?
		end-module-stmt
	;

/*
	R1105 module-stmt is MODULE module-name
*/
	module-stmt :
		MODULE NAME_LITERAL
	;

/*
	R1106 end-module-stmt is END [ MODULE [ module-name ] ]
*/
	end-module-stmt :
		END ( MODULE NAME_LITERAL ? ) ?
	;

/*
	R1107 module-subprogram-part is contains-stmt
	[ module-subprogram ] ...
*/
	module-subprogram-part :
		contains-stmt
		module-subprogram *
	;

/*
	R1108 module-subprogram is function-subprogram
	or subroutine-subprogram
	or separate-module-subprogram
*/
	module-subprogram :
		function-subprogram
	|	subroutine-subprogram
	|	separate-module-subprogram
	;

/*
	R1109 use-stmt is USE [ [ , module-nature ] :: ] module-name [ , rename-list ]
	or USE [ [ , module-nature ] :: ] module-name ,
	ONLY : [ only-list ]
*/
	use-stmt :
		USE ( ( ',' module-nature ) ? ':' ':' ) ? NAME_LITERAL ( ',' rename-list ) ?
	|	USE ( ( ',' module-nature ) ? ':' ':' ) ? NAME_LITERAL ',' ONLY ':' only-list ?
	;

/*
	R1110 module-nature is INTRINSIC
	or NON INTRINSIC
*/
	module-nature :
		INTRINSIC
	|	NON_INTRINSIC
	;

/*
	R1111 rename is local-name => use-name
	or OPERATOR (local-defined-operator) =>
	OPERATOR (use-defined-operator)
*/
	rename :
		NAME_LITERAL '=' '>' NAME_LITERAL
	|	OPERATOR '(' defined-operator ')' '=' '>'	OPERATOR '(' defined-operator ')'
	;
	rename-list :
		rename ( ',' rename ) *
	;

/*
	R1112 only is generic-spec
	or only-use-name
	or rename
*/
	only :
		generic-spec
	|	only-use-name
	|	rename
	;
	only-list :
		only ( ',' only ) *
	;

/*
	R1113 only-use-name is use-name
*/
	only-use-name :
		NAME_LITERAL
	;

/*
	R1114 local-defined-operator is defined-unary-op
	or defined-binary-op
	
	not needed...
*/

/*
	R1115 use-defined-operator is defined-unary-op
	or defined-binary-op
	
	not needed...
*/

/*
	R1116 submodule is submodule-stmt
	[ specification-part ]
	[ module-subprogram-part ]
	end-submodule-stmt
*/
	submodule :
		submodule-stmt
		specification-part ?
		module-subprogram-part ?
		end-submodule-stmt
	;

/*
	R1117 submodule-stmt is SUBMODULE ( parent-identifier ) submodule-name
*/
	submodule-stmt :
		SUBMODULE '(' parent-identifier ')' NAME_LITERAL
	;

/*
	R1118 parent-identifier is ancestor-module-name [ : parent-submodule-name ]
*/
	parent-identifier :
		NAME_LITERAL ( ':' NAME_LITERAL ) ?
	;

/*
	R1119 end-submodule-stmt is END [ SUBMODULE [ submodule-name ] ]
*/
	end-submodule-stmt :
		END ( SUBMODULE NAME_LITERAL ? ) ?
	;

/*
	R1120 block-data is block-data-stmt
	[ specification-part ]
	end-block-data-stmt
*/
	block-data :
		block-data-stmt
		specification-part ?
		end-block-data-stmt
	;

/*
	R1121 block-data-stmt is BLOCK DATA [ block-data-name ]
*/
	block-data-stmt :
		BLOCK_DATA NAME_LITERAL ?
	;

/*
	R1122 end-block-data-stmt is END [ BLOCK DATA [ block-data-name ] ]
*/
	end-block-data-stmt :
		END ( BLOCK_DATA NAME_LITERAL ? ) ?
	;

/***************
	Clause 12:	
 ***************/

/*
	R1201 interface-block is interface-stmt
	[ interface-specification ] ...
	end-interface-stmt
*/
	interface-block :
		interface-stmt
		interface-specification *
		end-interface-stmt
	;

/*
	R1202 interface-specification is interface-body
	or procedure-stmt
*/
	interface-specification :
		interface-body
	|	procedure-stmt
	;

/*
	R1203 interface-stmt is INTERFACE [ generic-spec ]
	or ABSTRACT INTERFACE
*/
	interface-stmt :
		INTERFACE generic-spec ?
	|	ABSTRACT_INTERFACE
	;

/*
	R1204 end-interface-stmt is END INTERFACE [ generic-spec ]
*/
	end-interface-stmt :
		END_INTERFACE generic-spec ?
	;

/*
	R1205 interface-body is function-stmt
	[ specification-part ]
	end-function-stmt
	or subroutine-stmt
	[ specification-part ]
	end-subroutine-stmt
*/
	interface-body :
		function-stmt specification-part end-function-stmt
	|	function-stmt end-function-stmt
	|	subroutine-stmt specification-part end-subroutine-stmt
	|	subroutine-stmt end-subroutine-stmt
	;

/*
	R1206 procedure-stmt is [ MODULE ] PROCEDURE [ :: ] procedure-name-list
*/
	procedure-stmt :
		MODULE ? PROCEDURE ( ':' ':' ) ? name-list
	;

/*
	R1207 generic-spec is generic-name
	or OPERATOR ( defined-operator )
	or ASSIGNMENT ( = )
	or defined-io-generic-spec
*/
	generic-spec :
		NAME_LITERAL
	|	OPERATOR '(' defined-operator ')'
	|	ASSIGNMENT '(' '=' ')'
	|	defined-io-generic-spec
	;

/*
	R1208 defined-io-generic-spec is READ (FORMATTED)
	or READ (UNFORMATTED)
	or WRITE (FORMATTED)
	or WRITE (UNFORMATTED)
*/
	defined-io-generic-spec :
		READ '(' FORMATTED ')'
	|	READ '(' UNFORMATTED ')'
	|	WRITE '(' FORMATTED ')'
	|	WRITE '(' UNFORMATTED ')'
	;

/*
	R1209 import-stmt is IMPORT [[ :: ] import-name-list
*/
	import-stmt :
		IMPORT ( ':' ':' ) ? name-list
	;

/*
	R1210 external-stmt is EXTERNAL [ :: ] external-name-list
*/
	external-stmt :
		EXTERNAL ( ':' ':' ) name-list
	;

/*
	R1211 procedure-declaration-stmt is PROCEDURE ( [ proc-interface ] )
	[ [ , proc-attr-spec ] ... :: ] proc-decl-list
*/
	procedure-declaration-stmt :
		PROCEDURE '(' proc-interface ? ')'
		( ( ',' proc-attr-spec ) * ':' ':' ) ?
		proc-decl-list
	;

/*
	R1212 proc-interface is interface-name
	or declaration-type-spec
*/
	proc-interface :
		NAME_LITERAL
	|	declaration-type-spec
	;

/*
	R1213 proc-attr-spec is access-spec
	or proc-language-binding-spec
	or INTENT ( intent-spec )
	or OPTIONAL
	or POINTER
	or SAVE
*/
	proc-attr-spec :
		access-spec
	|	proc-language-binding-spec
	|	INTENT ( intent-spec )
	|	OPTIONAL
	|	POINTER
	|	SAVE
	;

/*
	R1214 proc-decl is procedure-entity-name [ => proc-pointer-init ]
*/
	proc-decl :
		NAME_LITERAL ( '=' '>' proc-pointer-init ) ?
	;
	proc-decl-list :
		proc-decl ( ',' proc-decl ) *
	;

/*
	R1215 interface-name is name
	
	not needed...
*/

/*
	R1216 proc-pointer-init is null-init
	or initial-proc-target
*/
	proc-pointer-init :
		null-init
	|	initial-proc-target
	;

/*
	R1217 initial-proc-target is procedure-name
*/
	initial-proc-target :
		NAME_LITERAL
	;

/*
	R1218 intrinsic-stmt is INTRINSIC [ :: ] intrinsic-procedure-name-list
*/
	intrinsic-stmt :
		INTRINSIC ( ':' ':' ) ? name-list
	;

/*
	R1219 function-reference is procedure-designator ( [ actual-arg-spec-list ] )
*/
	function-reference :
		procedure-designator '(' actual-arg-spec-list ? ')'
	;

/*
	R1220 call-stmt is CALL procedure-designator [ ( [ actual-arg-spec-list ] ) ]
*/
	call-stmt :
		CALL procedure-designator ( '(' actual-arg-spec-list ? ')' ) ?
	;

/*
	R1221 procedure-designator is procedure-name
	or proc-component-ref
	or data-ref % binding-name
*/
	procedure-designator :
		NAME_LITERAL
	|	proc-component-ref
	|	data-ref '%' NAME_LITERAL
	;

/*
	R1222 actual-arg-spec is [ keyword = ] actual-arg
*/
	actual-arg-spec :
		( NAME_LITERAL '=' ) ? actual-arg
	;
	actual-arg-spec-list :
		actual-arg-spec ( ',' actual-arg-spec ) *
	;

/*
	R1223 actual-arg is expr
	or variable
	or procedure-name
	or proc-component-ref
	or alt-return-spec
*/
	actual-arg :
		expr
	|	variable
	|	NAME_LITERAL
	|	proc-component-ref
	|	alt-return-spec
	;

/*
	R1224 alt-return-spec is * label
*/
	alt-return-spec :
		'*' INT_LITERAL_CONSTANT
	;

/*
	R1225 prefix is prefix-spec [ prefix-spec ] ...
*/
	prefix :
		prefix-spec +
	;

/*
	R1226 prefix-spec is declaration-type-spec
	or ELEMENTAL
	or IMPURE
	or MODULE
	or PURE
	or RECURSIVE
*/
	prefix-spec :
		declaration-type-spec
	|	ELEMENTAL
	|	IMPURE
	|	MODULE
	|	PURE
	|	RECURSIVE
	;

/*
	R1227 function-subprogram is function-stmt
	[ specification-part ]
	[ execution-part ]
	[ internal-subprogram-part ]
	end-function-stmt
*/
	function-subprogram :
		function-stmt
		specification-part ?
		execution-part ?
		internal-subprogram-part ?
		end-function-stmt
	;

/*
	R1228 function-stmt is [ prefix ] FUNCTION function-name
	( [ dummy-arg-name-list ] ) [ sufix ]
*/
	function-stmt : 
		prefix ? FUNCTION NAME_LITERAL '(' name-list ? ')' sufix ?
	;

/*
	R1229 proc-language-binding-spec is language-binding-spec
*/
	proc-language-binding-spec :
		language-binding-spec
	;

/*
	R1230 dummy-arg-name is name
	
	not needed...
*/

/*
	R1231 sufix is proc-language-binding-spec [ RESULT ( result-name ) ]
	or RESULT ( result-name ) [ proc-language-binding-spec ]
*/
	sufix :
		proc-language-binding-spec ( RESULT '(' NAME_LITERAL ')' ) ?
	|	RESULT '(' NAME_LITERAL ')' proc-language-binding-spec ?
	;

/*
	R1232 end-function-stmt is END [ FUNCTION [ function-name ] ]
*/
	end-function-stmt :
		END ( FUNCTION NAME_LITERAL ? ) ?
	;

/*
	R1233 subroutine-subprogram is subroutine-stmt
	[ specification-part ]
	[ execution-part ]
	[ internal-subprogram-part ]
	end-subroutine-stmt
*/
	subroutine-subprogram :
		subroutine-stmt
		specification-part ?
		execution-part ?
		internal-subprogram-part ?
		end-subroutine-stmt
	;

/*
	R1234 subroutine-stmt is [ prefix ] SUBROUTINE subroutine-name
	[ ( [ dummy-arg-list ] ) [ proc-language-binding-spec ] ]
*/
	subroutine-stmt :
		prefix SUBROUTINE NAME_LITERAL '(' dummy-arg-list ? ')' proc-language-binding-spec
	|	prefix SUBROUTINE NAME_LITERAL '(' dummy-arg-list ? ')' 
	|	prefix SUBROUTINE NAME_LITERAL
	|	SUBROUTINE NAME_LITERAL '(' dummy-arg-list ? ')' proc-language-binding-spec
	|	SUBROUTINE NAME_LITERAL '(' dummy-arg-list ? ')' 
	|	SUBROUTINE NAME_LITERAL
	;

/*
	R1235 dummy-arg is dummy-arg-name
	or *
*/
	dummy-arg :
		NAME_LITERAL
	|	'*'
	;
	dummy-arg-list :
		dummy-arg-list ',' dummy-arg 
	|	dummy-arg
	;

/*
	R1236 end-subroutine-stmt is END [ SUBROUTINE [ subroutine-name ] ]
*/
	end-subroutine-stmt :
		END SUBROUTINE NAME_LITERAL
	|	END SUBROUTINE
	|	END
	;

/*
	R1237 separate-module-subprogram is mp-subprogram-stmt
	[ specification-part ]
	[ execution-part ]
	[ internal-subprogram-part ]
	end-mp-subprogram-stmt
*/
	separate-module-subprogram :
		mp-subprogram-stmt
		specification-part ?
		execution-part ?
		internal-subprogram-part ?
		end-mp-subprogram-stmt
	;

/*
	R1238 mp-subprogram-stmt is MODULE PROCEDURE procedure-name
*/
	mp-subprogram-stmt :
		MODULE_PROCEDURE NAME_LITERAL
	;

/*
	R1239 end-mp-subprogram-stmt is END [PROCEDURE [procedure-name]]
*/
	end-mp-subprogram-stmt :
		END PROCEDURE NAME_LITERAL
	|	END PROCEDURE
	|	END
	;

/*
	R1240 entry-stmt is ENTRY entry-name [ ( [ dummy-arg-list ] ) [ sufix ] ]
*/
	entry-stmt :
		ENTRY NAME_LITERAL ( '(' dummy-arg-list ? ')' sufix ? ) ?
	;

/*
	R1241 return-stmt is RETURN [ scalar-int-expr ]
*/
	return-stmt :
		RETURN expr
	|	RETURN 
	;

/*
	R1242 contains-stmt is CONTAINS
*/
	contains-stmt :
		CONTAINS
	;

/*
	R1243 stmt-function-stmt is function-name ( [ dummy-arg-name-list ] ) = scalar-expr
*/
	stmt-function-stmt :
		NAME_LITERAL '(' name-list ')' '=' expr
	;
