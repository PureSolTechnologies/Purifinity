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

    public static int countLineBreaks(String text) {
	char[] chars = text.toCharArray();
	int count = 0;
	for (int index = 0; index < text.length(); index++) {
	    if (chars[index] == '\n') {
		count++;
	    }
	}
	return count;
    }

    /**
     * This method converts a byte array into a string converting each byte into
     * a 2-digit hex representation and appending them all together.
     * 
     * @param byteArray
     *            is the array of bytes to be converted.
     * @return A {@link String} is returned representing the byte array.
     */
    public static String convertByteArrayToString(byte[] byteArray) {
	if (byteArray == null) {
	    throw new IllegalArgumentException("Byte array must not be null!");
	}
	StringBuffer hexString = new StringBuffer();
	for (int i = 0; i < byteArray.length; i++) {
	    int digit = 0xFF & byteArray[i];
	    String hexDigits = Integer.toHexString(digit);
	    if (hexDigits.length() < 2) {
		hexString.append("0");
	    }
	    hexString.append(hexDigits);
	}
	return hexString.toString();
    }

    /**
     * 
     * @param string
     * @return
     */
    public static byte[] convertStringToByteArray(String string) {
	if (string == null) {
	    throw new IllegalArgumentException(
		    "String argument must not be null!");
	}
	if (string.length() % 2 != 0) {
	    throw new IllegalArgumentException(
		    "String argument has to have a length which can be divided by 2!");
	}
	byte[] bytes = new byte[string.length() / 2];
	StringBuffer buffer = new StringBuffer(string);
	for (int i = 0; i < bytes.length; i++) {
	    String hexByteString = buffer.substring(0, 2);
	    bytes[i] = Integer.valueOf(hexByteString, 16).byteValue();
	    buffer.delete(0, 2);
	}
	return bytes;
    }

}
