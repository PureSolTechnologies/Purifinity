package com.puresol.purifinity.uhura.ust;

public class NameTranslator {

	public static String getProductionClassName(String productionName) {
		StringBuffer buffer = new StringBuffer(productionName);
		makeFirstLetterUpperCase(buffer);
		replaceDashWithUpperCase(buffer);
		return buffer.toString();
	}

	private static void replaceDashWithUpperCase(StringBuffer buffer) {
		char chars[] = new char[1];
		int index = buffer.indexOf("-");
		while (index >= 0) {
			buffer.getChars(index + 1, index + 2, chars, 0);
			buffer.delete(index, index + 1);
			if (Character.isLowerCase(chars[0])) {
				chars[0] = Character.toUpperCase(chars[0]);
				buffer.replace(index, index + 1, new String(chars));
			}
			index = buffer.indexOf("-");
		}
	}

	private static void makeFirstLetterUpperCase(StringBuffer buffer) {
		char chars[] = new char[1];
		buffer.getChars(0, 1, chars, 0);
		if (Character.isLowerCase(chars[0])) {
			chars[0] = Character.toUpperCase(chars[0]);
			buffer.replace(0, 1, new String(chars));
		}
	}
}
