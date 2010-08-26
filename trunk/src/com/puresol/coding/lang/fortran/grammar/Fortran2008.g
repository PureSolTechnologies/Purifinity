
/*
 * Fortran 2008 Grammar File for Nyota Uhura
 * (c) by PureSol Technologies
 */ 
 
 /*
	Clause 1:
	R101 xyz-list is xyz [ , xyz ] ...
	R102 xyz-name is name
	R103 scalar-xyz is xyz
	C101 (R103) scalar-xyz shall be scalar.
 */
 
 OPTIONS
 
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
	NAME : LETTER ALPHANUMERIC_CHARACTER
			"{0,62}";

	/*
	 * 3.2.5 Statement labels
	 */
	LABEL : DIGIT "{1,5}";

	DELIMETERS : "(" LEFT_PARENTHESIS "|"
			RIGHT_PARENTHESIS "|" SLASH "|" LEFT_SQUARE_BRACKET "|"
			RIGHT_SQUARE_BRACKET "|" LEFT_PARENTHESIS SLASH "|"
			SLASH RIGHT_PARENTHESIS ")";

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

	/*
	 * 4.4.2.3 Real type
	 */
	EXPONENT : SIGNED_DIGIT_STRING;
	EXPONENT_LETTER : "[ED]";
	SIGNIFICANT : "(" DIGIT_STRING "\\.("
			DIGIT_STRING ")?|\\." DIGIT_STRING ")";

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
 
 TOKENS

	NAME : LETTER ALPHANUMERIC_CHARACTER
			"{0,62}";
	INT_LITERAL_CONSTANT : DIGIT_STRING "(_"
			KIND_PARAM ")?";
	SIGNED_INT_LITERAL_CONSTANT : SIGN "?"
			INT_LITERAL_CONSTANT;

	REAL_LITERAL_CONSTANT : "(" SIGNIFICANT "("
			EXPONENT_LETTER EXPONENT ")?(_" KIND_PARAM ")?|"
			DIGIT_STRING EXPONENT_LETTER EXPONENT "(_" KIND_PARAM
			")?)";
	SIGNED_REAL_LITERAL_CONSTANT : SIGN "?"
			REAL_LITERAL_CONSTANT;

	COMPLEX_LITERAL_CONSTANT : "\\(" REAL_PART
			"\\s*,\\s*" IMAG_PART "\\)";

	CHAR_LITERAL_CONSTANT : "(" KIND_PARAM
			"_)?(\"" REP_CHAR_DOUBLE_QUOTE "*\"|'"
			REP_CHAR_SINGLE_QUOTE "*')";

	LOGICAL_LITERAL_CONSTANT : "(\\.TRUE\\.|\\.FALSE\\.)(_"
			KIND_PARAM ")?";

	BOZ_LITERAL_CONSTANT : "(" BINARY_CONSTANT
			"|" OCTAL_CONSTANT "|" HEX_CONSTANT ")";

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
 
 PRODUCTIONS
 
 /*
 	Clause 2:
	R201 program is program-unit
	[ program-unit ] ...
	
	R202 program-unit is main-program
	or external-subprogram
	or module
	or submodule
	or block-data
	
	R203 external-subprogram is function-subprogram
	or subroutine-subprogram
	
	R204 specification-part is [ use-stmt ] ...
	[ import-stmt ] ...
	[ implicit-part ]
	[ declaration-construct ] ...
	
	R205 implicit-part is [ implicit-part-stmt ] ...
	implicit-stmt
	
	R206 implicit-part-stmt is implicit-stmt
	or parameter-stmt
	or format-stmt
	or entry-stmt
	
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
