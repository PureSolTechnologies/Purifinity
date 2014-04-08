package com.puresol.commons.utils;

import java.util.Locale;

public class LocaleUtils {

	/**
	 * This method converts a locale string into a {@link Locale} object.
	 * 
	 * @param localeString
	 * @return
	 */
	public static Locale convertFromString(String localeString) {
		String[] split = localeString.split("_");
		switch (split.length) {
		case 1:
			return new Locale(split[0]);
		case 2:
			return new Locale(split[0], split[1]);
		case 3:
			return new Locale(split[0], split[1], split[2]);
		default:
			throw new IllegalArgumentException("Locale string '" + localeString
					+ "' is invalid!");
		}
	}

}
