package com.puresol.parser;

import java.io.InputStream;
import java.util.ArrayList;

public class TokenStream {

    private InputStream inputStream = null;
    private ArrayList<Token> tokens = new ArrayList<Token>();

    public TokenStream(InputStream stream) {
	this.inputStream = stream;
    }

    public InputStream getInputStream() {
	return inputStream;
    }

    public ArrayList<Token> getTokens() {
	return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
	this.tokens = tokens;
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
