package com.puresol.parser.lexer;

public class NoMatchingTokenDefinitionFound extends Exception {

	private static final long serialVersionUID = -1406591512595275448L;

	public NoMatchingTokenDefinitionFound(int pos, String text) {
		super(pos + ": '" + text.substring(pos) + "'");
	}

}
