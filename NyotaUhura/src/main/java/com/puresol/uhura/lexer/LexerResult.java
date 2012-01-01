package com.puresol.uhura.lexer;

import java.io.Serializable;

public class LexerResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private final TokenStream tokenStream;

	public LexerResult(TokenStream tokenStream) {
		super();
		this.tokenStream = tokenStream;
	}

	public TokenStream getTokenStream() {
		return tokenStream;
	}

}
