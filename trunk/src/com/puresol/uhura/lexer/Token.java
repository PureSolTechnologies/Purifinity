package com.puresol.uhura.lexer;

import java.io.Serializable;

import com.puresol.uhura.grammar.token.Visibility;

/**
 * This class represents a single token which was identified by a lexer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Token implements Serializable {

	private static final long serialVersionUID = -9005444686111333074L;

	/**
	 * This is the type id of the token. The true name is stored within the
	 * token name reference table. Integers are better to handle and names
	 * should be unique, too.
	 */
	private final String name;
	/**
	 * This is the text which was found in the source input which matched the
	 * token definition with the name defined in name.
	 */
	private final String text;
	private final Visibility visibility;

	public Token(String name, String text, Visibility visibility) {
		super();
		this.name = name;
		this.text = text;
		this.visibility = visibility;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This is the text which was found in the source input which matched the
	 * token definition with the name defined in name.
	 * 
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
		return "\"" + text + "\" (" + name + ")";
	}
}
