package com.puresol.coding.lang.fortran;

/**
 * This class is based on Chapter 3 of
 * 
 * "ISO/IEC JTC 1/SC 22/WG 5/N1826 DRAFT (20th April 2010 19:13) --
 * INTERNATIONAL STANDARD ISO/IEC DIS 1539-1 Third edition 2010-04-20
 * 
 * named "Lexical tokens and source form".
 * 
 * This class is meant to hold all regexp string constant representing lexical
 * character groups and rules.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LexicalStructure {

    public static final String LINE_TERMINATOR = "(\\r\\n|\\r|\\n)";

    /* **********************************
     * 
     * 3.1 Prozessor character set
     * 
     * *********************************
     */

    /*
     * 3.1.1 Characters
     */
    public static final String ALPHANUMERIC_CHARACTER = "("
	    + LexicalStructure.LETTER + "|" + LexicalStructure.DIGIT + "|"
	    + LexicalStructure.UNDERSCORE + ")";

    /*
     * 3.1.2 Letters
     */
    public static final String LETTER = "[A-Z]";

    /*
     * 3.1.3 Digits
     */
    public static final String DIGIT = "[0-9]";

    /*
     * 3.1.4 Underscore
     */
    public static final String UNDERSCORE = "_";

    /**
     * 3.1.5 Special characters
     */
    public static final String BLANK = " ";
    public static final String SEMICOLON = ";";
    public static final String EQUALS = "=";
    public static final String EXCLAMATION_POINT = "!";
    public static final String PLUS = "\\+";
    public static final String QUOTATION_MARK_OR_QUOTE = "\"";
    public static final String MINUS = "-";
    public static final String PERCENT = "%";
    public static final String ASTERIK = "*";
    public static final String AMPERSAND = "&";
    public static final String SLASH = "/";
    public static final String TILDE = "~";
    public static final String BACKSLASH = "\\\\";
    public static final String LESS_THAN = "<";
    public static final String LEFT_PARENTHESIS = "\\(";
    public static final String GREATER_THAN = ">";
    public static final String RIGHT_PARENTHESIS = "\\(";
    public static final String QUESTION_MARK = "\\?";
    public static final String LEFT_SQUARE_BRACKET = "\\[";
    public static final String APOSTROPHE = "'";
    public static final String RIGHT_SQUARE_BRACKET = "\\]";
    public static final String GRAVE_ACCENT = "`";
    public static final String LEFT_CURLY_BRACKET = "{";
    public static final String CIRCUMFLEX_ACCENT = "^";
    public static final String RIGHT_CURLY_BRACKET = "{";
    public static final String VERTICAL_LINE = "|";
    public static final String COMMA = ",";
    public static final String CURRENCY_SYMBOL = "$";
    public static final String DECIMALPOINT_OR_PERIOD = "\\.";
    public static final String NUMBER_SIGN = "#";
    public static final String COLON = ":";
    public static final String COMMERCIAL_AT = "@";

    /* **********************************
     * 
     * 3.2 Low-level syntax
     * 
     * *********************************
     */

    /*
     * 3.2.2 Names
     */
    public static final String NAME = LETTER + ALPHANUMERIC_CHARACTER
	    + "{0,62}";

    /*
     * 3.2.5 Statement labels
     */
    public static final String LABEL = DIGIT + "{1,5}";

    public static final String DELIMETERS = "(" + LEFT_PARENTHESIS + "|"
	    + RIGHT_PARENTHESIS + "|" + SLASH + "|" + LEFT_SQUARE_BRACKET + "|"
	    + RIGHT_SQUARE_BRACKET + "|" + LEFT_PARENTHESIS + SLASH + "|"
	    + SLASH + RIGHT_PARENTHESIS + ")";

    /* *************************
     * 
     * 4 Types
     * 
     * ************************
     */

    /*
     * 4.4.2.2 Integer type
     */

    public static final String SIGN = "[+-]";
    public static final String DIGIT_STRING = "\\d+";
    public static final String SIGNED_DIGIT_STRING = SIGN + "?" + DIGIT_STRING;
    /*
     * In following rule NAME = SCALAR-INT-CONSTANT-NAME!
     */
    public static final String KIND_PARAM = "(" + DIGIT_STRING + "|" + NAME
	    + ")";
    public static final String INT_LITERAL_CONSTANT = DIGIT_STRING + "(_"
	    + KIND_PARAM + ")?";
    public static final String SIGNED_INT_LITERAL_CONSTANT = SIGN + "?"
	    + INT_LITERAL_CONSTANT;

    /*
     * 4.4.2.3 Real type
     */
    public static final String EXPONENT = SIGNED_DIGIT_STRING;
    public static final String EXPONENT_LETTER = "[ED]";
    public static final String SIGNIFICANT = "(" + DIGIT_STRING + "\\.("
	    + DIGIT_STRING + ")?|\\." + DIGIT_STRING + ")";
    public static final String REAL_LITERAL_CONSTANT = "(" + SIGNIFICANT + "("
	    + EXPONENT_LETTER + EXPONENT + ")?(_" + KIND_PARAM + ")?|"
	    + DIGIT_STRING + EXPONENT_LETTER + ")(_" + KIND_PARAM + ")?)";
}
