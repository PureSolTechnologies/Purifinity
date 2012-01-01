package com.puresol.utils;

public class RegExpUtilities {

	/**
	 * This method converts a string into a regexp pattern which fixes special
	 * characters to look for a similar string within a later regexp search.
	 * 
	 * @param string
	 * @return
	 */
	public static String string2RegExp(String string) {
		/*
		 * Included backslashes go first due to additional introduction in next
		 * steps which are not to be replaced.
		 */
		string = string.replaceAll("\\\\", "\\\\\\\\");
		/*
		 * start and end tag...
		 */
		string = string.replaceAll("\\^", "\\\\\\^");
		string = string.replaceAll("\\$", "\\\\\\$");
		/*
		 * quantifiers, back slash and joker...
		 */
		string = string.replaceAll("\\+", "\\\\\\+");
		string = string.replaceAll("\\*", "\\\\\\*");
		string = string.replaceAll("\\?", "\\\\\\?");
		string = string.replaceAll("\\.", "\\\\\\.");
		/*
		 * parenthesis, brackets...
		 */
		string = string.replaceAll("\\(", "\\\\\\(");
		string = string.replaceAll("\\)", "\\\\\\)");
		string = string.replaceAll("\\[", "\\\\\\[");
		string = string.replaceAll("\\]", "\\\\\\]");
		string = string.replaceAll("\\{", "\\\\\\{");
		string = string.replaceAll("\\}", "\\\\\\}");
		return string;
	}

}
