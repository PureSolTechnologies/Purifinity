package com.puresol.uhura.parser;

import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;

public class ParserException extends Exception {

	private static final long serialVersionUID = 6292645686476419232L;

	private final Token token;

	public ParserException(String message, Token token) {
		super(message);
		this.token = token;
	}

	public ParserException(String message) {
		super(message);
		this.token = new Token("", "", Visibility.HIDDEN);
	}

	/**
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}

}
