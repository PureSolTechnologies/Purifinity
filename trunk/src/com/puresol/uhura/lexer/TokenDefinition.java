package com.puresol.uhura.lexer;

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
	private final Visibility visibility;

	public TokenDefinition(String name, String regex) {
		this(name, Pattern.compile(regex));
	}

	public TokenDefinition(String name, String regex, Visibility visibility) {
		this(name, Pattern.compile(regex), visibility);
	}

	public TokenDefinition(String name, Pattern pattern, Visibility visibility) {
		this.name = name;
		this.pattern = pattern;
		this.visibility = visibility;
	}

	public TokenDefinition(String name, Pattern pattern) {
		this.name = name;
		this.pattern = pattern;
		this.visibility = Visibility.VISIBLE;
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
	 * @return the visibility
	 */
	public Visibility getVisibility() {
		return visibility;
	}

}
