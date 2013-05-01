package com.puresol.utils;

/**
 * This is a simple utilities class containing some functionality for
 * {@link String}.
 * 
 * @author Rick-Rainer Ludwig
 */
public class StringUtils {

	/**
	 * This methods auto breaks a long line of text into several lines by adding
	 * line breaks.
	 * 
	 * @param text
	 *            is the {@link String} to be broken down into several line.
	 * @param maxLen
	 *            is the maximum number of characters per line.
	 * @return Returned is the original text with additional line breaks.
	 */
	public static String wrapLinesByWords(String text, int maxLen) {
		StringBuffer buffer = new StringBuffer();
		int lineLength = 0;
		for (String token : text.split(" ")) {
			if (lineLength + token.length() + 1 > maxLen) {
				buffer.append("\n");
				lineLength = 0;
			} else if (lineLength > 0) {
				buffer.append(" ");
				lineLength++;
			}
			buffer.append(token);
			lineLength += token.length();
		}
		text = buffer.toString();
		return text;
	}

}
