package com.puresol.uhura.grammar.token;

import java.util.regex.Pattern;


/**
 * This class represents a single token definition for a lexer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TokenDefinition {

	private final int typeId;
	private final Pattern pattern;
	private final Visibility visibility;

	public TokenDefinition(int typeId, String regex) {
		this(typeId, Pattern.compile(regex));
	}

	public TokenDefinition(int typeId, String regex, Visibility visibility) {
		this(typeId, Pattern.compile(regex), visibility);
	}

	public TokenDefinition(int typeId, Pattern pattern, Visibility visibility) {
		this.typeId = typeId;
		this.pattern = pattern;
		this.visibility = visibility;
	}

	public TokenDefinition(int typeId, Pattern pattern) {
		this.typeId = typeId;
		this.pattern = pattern;
		this.visibility = Visibility.VISIBLE;
	}

	/**
	 * @return the name
	 */
	public int getTypeId() {
		return typeId;
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
