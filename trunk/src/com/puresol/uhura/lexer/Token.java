package com.puresol.uhura.lexer;

/**
 * This class represents a single token which was identified by a lexer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Token {

	/**
	 * This is the name of the token. It comes from the token definition.
	 */
	private final String name;
	/**
	 * This is the text which was found in the source input which matched the
	 * token definition with the name defined in name.
	 */
	private final String text;
	private final Visibility visibility;
	/**
	 * This is an optional id which is used for reference in symbol tables. This
	 * id is given optional. All id entries smaller than zero are not set and
	 * should be neglected.
	 */
	private final int id;

	public Token(String name, String text, Visibility visibility) {
		super();
		this.name = name;
		this.text = text;
		this.visibility = visibility;
		this.id = -1;
	}

	public Token(String name, String text, Visibility visibility, int id) {
		super();
		this.name = name;
		this.text = text;
		this.visibility = visibility;
		this.id = id;
	}

	/**
	 * This is the name of the token. It comes from the token definition.
	 * 
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

	/**
	 * This is an optional id which is used for reference in symbol tables. This
	 * id is given optional. All id entries smaller than zero are not set and
	 * should be neglected.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "\"" + text + "\" (" + name + " / " + id + ")";
	}
}
