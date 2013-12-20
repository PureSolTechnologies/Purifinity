package com.puresoltechnologies.purifinity.framework.commons.utils;

/**
 * This class contains some helper functions to deal with enums.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EnumUtilities {

	/**
	 * This function looks up a specified enum constant named by a String.
	 * 
	 * @param <T>
	 * @param enumClass
	 * @param constantName
	 * @return
	 */
	public static <T> T findEnumConstante(Class<T> enumClass,
			String constantName) {
		for (T constant : enumClass.getEnumConstants()) {
			if (constant.toString().equals(constantName)) {
				return constant;
			}
		}
		return null;
	}

}
