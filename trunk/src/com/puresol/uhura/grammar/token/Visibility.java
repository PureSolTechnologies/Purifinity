package com.puresol.uhura.grammar.token;

/**
 * This enum is used to define the visibility of a token.
 * 
 * @author ludwig
 * 
 */
public enum Visibility {
	/**
	 * This means the token is visible and given to the later parsing process.
	 */
	VISIBLE,
	/**
	 * This means the token is dropped direcly after lexing. The parser will
	 * never see this token.
	 */
	HIDDEN;

	public static Visibility fromName(String name) {
		for (Visibility visibility : Visibility.class.getEnumConstants()) {
			if (visibility.name().equalsIgnoreCase(name)) {
				return visibility;
			}
		}
		return null;
	}
}
