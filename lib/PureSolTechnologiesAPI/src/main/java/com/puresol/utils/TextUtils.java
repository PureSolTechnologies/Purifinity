package com.puresol.utils;

public class TextUtils {

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

}
