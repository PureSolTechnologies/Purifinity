OPTIONS
	lexer="com.puresol.uhura.lexer.RegExpLexer";
	lexer.case_sensitive=true;
	parser="com.puresol.uhura.parser.LR1Parser";

HELPER 

	/********************************
	 * 3.3 Unicode Escapes
	 ********************************/
	HEX_DIGIT = "[0-9a-fA-F]";
	RAW_INPUT_CHARACTER = "\\w"; //TODO check for UNITCODE!!
	UNICODE_MARKER = "u+";
	UNICODE_ESCAPE = "\\\\" UNICODE_MARKER HEX_DIGIT "{4}";
	UNICODE_INPUT_CHARACTER = "(" UNICODE_ESCAPE "|" RAW_INPUT_CHARACTER  ")";

	/********************************
	 * 3.4 Line Terminators
	 ********************************/
	INPUT_CHARACTER = "[^\\r\\n]";
	LINE_TERMINATOR = "(\\r\\n|\\r|\\n)";

	/********************************
	 * 3.5 Input Elements and Tokens
	 ********************************/
	SUB = "\\x1A";

	/********************************
	 * 3.6 White Space
	 ********************************/
	WHITE_SPACE = "( |\\t|\\f|" LINE_TERMINATOR ")";

	/********************************
	 * 3.7 Comments
	 ********************************/
	CHARACTERS_IN_LINE = INPUT_CHARACTER "+";

	NOT_STAR = "[^*]";

	COMMENT_TAIL = "(" NOT_STAR "+|\\*+(?!/))*\\*/";

	END_OF_LINE_COMMENT = "//(" CHARACTERS_IN_LINE ")?" LINE_TERMINATOR;
	TRADITIONAL_COMMENT = "/\\*" COMMENT_TAIL;
	COMMENT = "(" TRADITIONAL_COMMENT  "|" END_OF_LINE_COMMENT ")";

	/********************************
	 * 3.8 Identifiers
	 ********************************/
	JAVA_LETTER_OR_DIGIT = "[a-zA-Z_$0-9]";
	JAVA_LETTER = "[a-zA-Z_$]";
	IDENTIFIER_CHARS = JAVA_LETTER JAVA_LETTER_OR_DIGIT "*";
	/*
	 * The absence of a keyword, boolean oder null literal has to be assured
	 * during lexical scan!
	 */
	IDENTIFIER = IDENTIFIER_CHARS;

	/********************************
	 * 3.10 Literals
	 ********************************/

	/*
	 * 3.10.1 Integer Literals
	 */
	INTEGER_TYPE_SUFFIX = "[lL]";

	NON_ZERO_DIGIT = "[1-9]";
	DIGIT = "[0-9]";
	DIGITS = DIGIT "+";
	DECIMAL_NUMERAL = "(0|" NON_ZERO_DIGIT DIGIT "*)";

	HEX_DIGITS = HEX_DIGIT "+";
	HEX_NUMERAL = "0[xX]" HEX_DIGITS;

	OCTAL_DIGIT = "[0-7]";
	OCTAL_DIGITS = OCTAL_DIGIT "+";
	OCTAL_NUMERAL = "0" OCTAL_DIGITS;

	DECIMAL_INTEGER_LITERAL = DECIMAL_NUMERAL INTEGER_TYPE_SUFFIX "?";
	HEX_INTEGER_LITERAL = HEX_NUMERAL INTEGER_TYPE_SUFFIX "?";
	OCTAL_INTEGER_LITERAL = OCTAL_NUMERAL INTEGER_TYPE_SUFFIX "?";

	INTEGER_LITERAL = "(" HEX_INTEGER_LITERAL "|" OCTAL_INTEGER_LITERAL "|" DECIMAL_INTEGER_LITERAL ")";

	/*
	 * 3.10.2 Floating-Point Literals
	 */
	FLOAT_TYPE_SUFFIX = "[fFdD]";
	SIGN = "[+-]";
	SIGNED_INTEGER = SIGN "?" DIGITS;

	BINARY_EXPONENT_INDICATOR = "[pP]";
	BINARY_EXPONENT = BINARY_EXPONENT_INDICATOR SIGNED_INTEGER;
	HEX_SIGNIFICAND = "(" HEX_NUMERAL "\\.?|0[xX](" HEX_DIGITS ")?\\." HEX_DIGITS ")";
	HEXADECIMAL_FLOATING_POINT_LITERAL = HEX_SIGNIFICAND BINARY_EXPONENT FLOAT_TYPE_SUFFIX "?";

	EXPONENT_INDICATOR = "[eE]";
	EXPONENT_PART = EXPONENT_INDICATOR SIGNED_INTEGER;
	DECIMAL_FLOATING_POINT_LITERAL = "(" DIGITS "\\.("  DIGITS ")?(" EXPONENT_PART ")?" 
			FLOAT_TYPE_SUFFIX "?|\\." DIGITS "(" EXPONENT_PART ")?"
			FLOAT_TYPE_SUFFIX "?|" DIGITS EXPONENT_PART
			FLOAT_TYPE_SUFFIX "?|" DIGITS "(" EXPONENT_PART ")?"
			FLOAT_TYPE_SUFFIX ")";

	FLOATING_POINT_LITERAL = "("
			DECIMAL_FLOATING_POINT_LITERAL "|"
			HEXADECIMAL_FLOATING_POINT_LITERAL ")";

	/*
	 * 3.10.3 Boolean Literals
	 */
	BOOLEAN_LITERAL = "(true|false)";

	/*
	 * 3.10.6 Escape Sequences for Character and String Literals (This was moved
	 * forward due to a need for these definitions in Character and String
	 * Literals)
	 */
	ZERO_TO_THREE = "[0-3]";
	OCTAL_ESCAPE = "(\\\\" OCTAL_DIGIT
			"{1,2}|\\\\" ZERO_TO_THREE OCTAL_DIGIT "{2})";
	/*
	 * The UNICODE_ESCAPE was introduced to handle them in a general approach.
	 * Normally all the unicode escapes are translated in the first step of
	 * source processing.
	 */

//	ESCAPE_SEQUENCE = "(\\\\b|\\\\t|\\\\n|\\\\f|\\\\r|\\\\\"|\\\\'|\\\\\\\\|" OCTAL_ESCAPE "|" UNICODE_ESCAPE ")";

	/*
	 * 3.10.4 Character Literals
	 */
//	SINGLE_CHARACTER = "[^'\\\\]"; 
	
//	CHARACTER_LITERAL = "('" SINGLE_CHARACTER "'|'" ESCAPE_SEQUENCE "')";

//	/*
//	 * 3.10.5 String Literals
//	 */
//	STRING_CHARACTER = "([^\"\\\\]|" ESCAPE_SEQUENCE ")";
//	STRING_CHARACTERS = STRING_CHARACTER "+";
//	STRING_LITERAL = "\"(" STRING_CHARACTERS ")?\"";

TOKENS
	NEWLINE = "(\r\n|\n|\r";
	WHITESPACE = "[ \t]";

PRODUCTIONS
