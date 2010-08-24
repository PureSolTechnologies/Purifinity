package com.puresol.uhura.grammar.production;

/**
 * This enum specifies the type of a construction. There are three
 * possibilities:
 * 
 * 1) Production: Which means the reference to a sub production defined by its
 * name.
 * 
 * 2) Token: This is a reference to a single token defined by its name.
 * 
 * 3) Text: Refers to a text within a token. The type of the token does not
 * matter in this sense.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum ConstructionType {

	PRODUCTION, TOKEN, TEXT;

	@Override
	public String toString() {
		return name();
	}

}
