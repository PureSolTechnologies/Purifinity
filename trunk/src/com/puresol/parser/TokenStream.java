package com.puresol.parser;

import java.io.InputStream;
import java.util.ArrayList;

public class TokenStream {

	private final String name;
	private final InputStream inputStream;
	private final ArrayList<Token> tokens = new ArrayList<Token>();

	public TokenStream(String name, InputStream stream) {
		this.name = name;
		this.inputStream = stream;
	}

	public String getName() {
		return name;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public ArrayList<Token> getTokens() {
		return tokens;
	}

	public void addToken(Token token) throws InvalidInputStreamException {
		if (token.getStream() != inputStream) {
			throw new InvalidInputStreamException(
					"Invalid input stream in token for this token stream!");
		}
		tokens.add(token);
	}

	public Token get(int index) {
		return tokens.get(index);
	}

	public int getSize() {
		return tokens.size();
	}
}
