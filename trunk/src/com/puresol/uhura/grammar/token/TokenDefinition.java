package com.puresol.uhura.grammar.token;

import java.util.regex.Pattern;

/**
 * This class represents a single token definition for a lexer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TokenDefinition {

	private final String name;
	private final Pattern pattern;
	private final String text;
	private final Visibility visibility;

	public TokenDefinition(String name, String regex) {
		this.name = name;
		this.pattern = Pattern.compile("^" + regex);
		this.visibility = Visibility.VISIBLE;
		this.text = regex;
	}

	public TokenDefinition(String name, String regex, Visibility visibility) {
		this.name = name;
		this.pattern = Pattern.compile("^" + regex);
		this.visibility = visibility;
		this.text = regex;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the patterns
	 */
	public Pattern getPattern() {
		return pattern;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the visibility
	 */
	public Visibility getVisibility() {
		return visibility;
	}

	@Override
	public String toString() {
		return getName() + ": '" + getPattern() + "' (" + visibility + ")";
	}
}
