package com.puresol.uhura.lexer;

import com.puresol.uhura.grammar.token.Visibility;

/**
 * This class represents a single token which was identified by a lexer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Token {

	/**
	 * This is the type id of the token. The true name is stored within the
	 * token name reference table. Integers are better to handle and names
	 * should be unique, too.
	 */
	private final int typeId;
	/**
	 * This is the text which was found in the source input which matched the
	 * token definition with the name defined in name.
	 */
	private final String text;
	private final Visibility visibility;

	public Token(int typeId, String text, Visibility visibility) {
		super();
		this.typeId = typeId;
		this.text = text;
		this.visibility = visibility;
	}

	/**
	 * This is the type id of the token. The true name is stored within the
	 * token name reference table. Integers are better to handle and names
	 * should be unique, too.
	 * 
	 * @return the name
	 */
	public int getTypeId() {
		return typeId;
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
		return "\"" + text + "\" (" + typeId + ")";
	}
}
