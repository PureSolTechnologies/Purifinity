package com.puresol.coding.lang.java;

/**
 * This class is a collection of all elements of Java's lexical structure. This
 * elements are take out of "The Java™ Language Specification -- Third Edition"
 * chapter 3 "Lexical Structure".
 * 
 * The element name is the same. It was only changed in notation to regular
 * expressions. The naming itself was changed to upper case characters with
 * underline word separation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LexicalStructure {

	/********************************
	 * 3.3 Unicode Escapes
	 ********************************/
	public static final String HEX_DIGIT = "[0-9a-fA-F]";
	public static final String RAW_INPUT_CHARACTER = "\\w"; /*
															 * TODO check for
															 * UNITCODE!!
															 */
	public static final String UNICODE_MARKER = "u+";
	public static final String UNICODE_ESCAPE = "\\\\" + UNICODE_MARKER
			+ HEX_DIGIT + "{4}";
	public static final String UNICODE_INPUT_CHARACTER = "(" + UNICODE_ESCAPE
			+ "|" + RAW_INPUT_CHARACTER + ")";

	/********************************
	 * 3.4 Line Terminators
	 ********************************/
	public static final String INPUT_CHARACTER = "[^\\r\\n]";
	public static final String LINE_TERMINATOR = "(\\r\\n|\\r|\\n)";

	/********************************
	 * 3.5 Input Elements and Tokens
	 ********************************/
	public static final String SUB = "\\x1A";
	/*
	 * All other things are used and separated into the lexer engine.
	 */

	/********************************
	 * 3.6 White Space
	 ********************************/
	public static final String WHITE_SPACE = "( |\\t|\\f|" + LINE_TERMINATOR
			+ ")";

	/********************************
	 * 3.7 Comments
	 ********************************/
	public static final String CHARACTERS_IN_LINE = INPUT_CHARACTER + "+";

	public static final String NOT_STAR = "[^*]";

	public static final String COMMENT_TAIL = "(" + NOT_STAR
			+ "+|\\*+(?!/))*\\*/";

	public static final String END_OF_LINE_COMMENT = "//(" + CHARACTERS_IN_LINE
			+ ")?" + LINE_TERMINATOR;
	public static final String TRADITIONAL_COMMENT = "/\\*" + COMMENT_TAIL;
	public static final String COMMENT = "(" + TRADITIONAL_COMMENT + "|"
			+ END_OF_LINE_COMMENT + ")";

	/********************************
	 * 3.8 Identifiers
	 ********************************/
	public static final String JAVA_LETTER_OR_DIGIT = "[a-zA-Z_$0-9]";
	public static final String JAVA_LETTER = "[a-zA-Z_$]";
	public static final String IDENTIFIER_CHARS = JAVA_LETTER
			+ JAVA_LETTER_OR_DIGIT + "*";
	/*
	 * The absence of a keyword, boolean oder null literal has to be assured
	 * during lexical scan!
	 */
	public static final String IDENTIFIER = IDENTIFIER_CHARS;

	/********************************
	 * 3.9 Keywords
	 ********************************/
	public static final String ABSTRACT_KEYWORD = "abstract";
	public static final String ASSERT_KEYWORD = "assert";
	public static final String BOOLEAN_KEYWORD = "boolean";
	public static final String BREAK_KEYWORD = "break";
	public static final String BYTE_KEYWORD = "byte";
	public static final String CASE_KEYWORD = "case";
	public static final String CATCH_KEYWORD = "catch";
	public static final String CHAR_KEYWORD = "char";
	public static final String CLASS_KEYWORD = "class";
	public static final String CONTINUE_KEYWORD = "continue";
	public static final String DEFAULT_KEYWORD = "default";
	public static final String DO_KEYWORD = "do";
	public static final String DOUBLE_KEYWORD = "double";
	public static final String ELSE_KEYWORD = "else";
	public static final String ENUM_KEYWORD = "enum";
	public static final String EXTENDS_KEYWORD = "extends";
	public static final String FINAL_KEYWORD = "final";
	public static final String FINALLY_KEYWORD = "finally";
	public static final String FOR_KEYWORD = "for";
	public static final String IF_KEYWORD = "if";
	public static final String GOTO_KEYWORD = "goto";
	public static final String IMPLEMENTS_KEYWORD = "implements";
	public static final String IMPORT_KEYWORD = "import";
	public static final String INSTANCEOF_KEYWORD = "instanceof";
	public static final String INT_KEYWORD = "int";
	public static final String INTERFACE_KEYWORD = "interface";
	public static final String LONG_KEYWORD = "long";
	public static final String NATIVE_KEYWORD = "native";
	public static final String NEW_KEYWORD = "new";
	public static final String PACKAGE_KEYWORD = "package";
	public static final String PRIVATE_KEYWORD = "private";
	public static final String PROTECTED_KEYWORD = "protected";
	public static final String PUBLIC_KEYWORD = "public";
	public static final String RETURN_KEYWORD = "return";
	public static final String SHORT_KEYWORD = "short";
	public static final String STATIC_KEYWORD = "static";
	public static final String STRICTFP_KEYWORD = "strictfp";
	public static final String SUPER_KEYWORD = "super";
	public static final String SWITCH_KEYWORD = "switch";
	public static final String SYNCHRONIZED_KEYWORD = "synchronized";
	public static final String THIS_KEYWORD = "this";
	public static final String THROW_KEYWORD = "throw";
	public static final String THROWS_KEYWORD = "throws";
	public static final String TRANSIENT_KEYWORD = "transient";
	public static final String TRY_KEYWORD = "try";
	public static final String VOID_KEYWORD = "void";
	public static final String VOLATILE_KEYWORD = "volatile";
	public static final String WHILE_KEYWORD = "while";

	/********************************
	 * 3.10 Literals
	 ********************************/

	/*
	 * 3.10.1 Integer Literals
	 */
	public static final String INTEGER_TYPE_SUFFIX = "[lL]";

	public static final String NON_ZERO_DIGIT = "[1-9]";
	public static final String DIGIT = "[0-9]";
	public static final String DIGITS = DIGIT + "+";
	public static final String DECIMAL_NUMERAL = "(0|" + NON_ZERO_DIGIT + DIGIT
			+ "*)";

	public static final String HEX_DIGITS = HEX_DIGIT + "+";
	public static final String HEX_NUMERAL = "0[xX]" + HEX_DIGITS;

	public static final String OCTAL_DIGIT = "[0-7]";
	public static final String OCTAL_DIGITS = OCTAL_DIGIT + "+";
	public static final String OCTAL_NUMERAL = "0" + OCTAL_DIGITS;

	public static final String DECIMAL_INTEGER_LITERAL = DECIMAL_NUMERAL
			+ INTEGER_TYPE_SUFFIX + "?";
	public static final String HEX_INTEGER_LITERAL = HEX_NUMERAL
			+ INTEGER_TYPE_SUFFIX + "?";
	public static final String OCTAL_INTEGER_LITERAL = OCTAL_NUMERAL
			+ INTEGER_TYPE_SUFFIX + "?";

	public static final String INTEGER_LITERAL = "(" + HEX_INTEGER_LITERAL
			+ "|" + OCTAL_INTEGER_LITERAL + "|" + DECIMAL_INTEGER_LITERAL + ")";

	/*
	 * 3.10.2 Floating-Point Literals
	 */
	public static final String FLOAT_TYPE_SUFFIX = "[fFdD]";
	public static final String SIGN = "[+-]";
	public static final String SIGNED_INTEGER = SIGN + "?" + DIGITS;

	public static final String BINARY_EXPONENT_INDICATOR = "[pP]";
	public static final String BINARY_EXPONENT = BINARY_EXPONENT_INDICATOR
			+ SIGNED_INTEGER;
	public static final String HEX_SIGNIFICAND = "(" + HEX_NUMERAL
			+ "\\.?|0[xX](" + HEX_DIGITS + ")?\\." + HEX_DIGITS + ")";
	public static final String HEXADECIMAL_FLOATING_POINT_LITERAL = HEX_SIGNIFICAND
			+ BINARY_EXPONENT + FLOAT_TYPE_SUFFIX + "?";

	public static final String EXPONENT_INDICATOR = "[eE]";
	public static final String EXPONENT_PART = EXPONENT_INDICATOR
			+ SIGNED_INTEGER;
	public static final String DECIMAL_FLOATING_POINT_LITERAL = "(" + DIGITS
			+ "\\.(" + DIGITS + ")?(" + EXPONENT_PART + ")?"
			+ FLOAT_TYPE_SUFFIX + "?|\\." + DIGITS + "(" + EXPONENT_PART + ")?"
			+ FLOAT_TYPE_SUFFIX + "?|" + DIGITS + EXPONENT_PART
			+ FLOAT_TYPE_SUFFIX + "?|" + DIGITS + "(" + EXPONENT_PART + ")?"
			+ FLOAT_TYPE_SUFFIX + ")";

	public static final String FLOATING_POINT_LITERAL = "("
			+ DECIMAL_FLOATING_POINT_LITERAL + "|"
			+ HEXADECIMAL_FLOATING_POINT_LITERAL + ")";

	/*
	 * 3.10.3 Boolean Literals
	 */
	public static final String BOOLEAN_LITERAL = "(true|false)";

	/*
	 * 3.10.6 Escape Sequences for Character and String Literals (This was moved
	 * forward due to a need for these definitions in Character and String
	 * Literals)
	 */
	public static final String ZERO_TO_THREE = "[0-3]";
	public static final String OCTAL_ESCAPE = "(\\\\" + OCTAL_DIGIT
			+ "{1,2}|\\\\" + ZERO_TO_THREE + OCTAL_DIGIT + "{2})";
	/*
	 * The UNICODE_ESCAPE was introduced to handle them in a general approach.
	 * Normally all the unicode escapes are translated in the first step of
	 * source processing.
	 */
	public static final String ESCAPE_SEQUENCE = "(\\\\b|\\\\t|\\\\n|\\\\f|\\\\r|\\\\\"|\\\\'|\\\\\\\\|"
			+ OCTAL_ESCAPE + "|" + UNICODE_ESCAPE + ")";

	/*
	 * 3.10.4 Character Literals
	 */
	public static final String SINGLE_CHARACTER = "[^'\\\\]";
	/*
	 * TODO Check for UNICODE raws ^^^!
	 */
	public static final String CHARACTER_LITERAL = "('" + SINGLE_CHARACTER
			+ "'|'" + ESCAPE_SEQUENCE + "')";

	/**
	 * 3.10.5 String Literals
	 */
	public static final String STRING_CHARACTER = "([^\"\\\\]|"
			+ ESCAPE_SEQUENCE + ")";
	public static final String STRING_CHARACTERS = STRING_CHARACTER + "+";
	public static final String STRING_LITERAL = "\"(" + STRING_CHARACTERS
			+ ")?\"";

	/**
	 * 3.10.7 The Null Literal
	 */
	public static final String NULL_LITERAL = "null";

	/********************************
	 * 3.11 Separators
	 ********************************/
	public static final String SEPARATORS = "[\\(\\)\\[\\]\\{\\};,.]";

	/********************************
	 * 3.12 Operators
	 ********************************/
	// == <= >= != && || ++ --
	// + - * / & | ^ % << >> >>>
	// += -= *= /= &= |= ^= %= <<= >>= >>>=
}
