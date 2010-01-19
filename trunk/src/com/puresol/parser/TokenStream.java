package com.puresol.parser;

import java.util.ArrayList;

public class TokenStream {

    private final String name;
    private final ArrayList<Token> tokens = new ArrayList<Token>();

    public TokenStream(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public ArrayList<Token> getTokens() {
	return tokens;
    }

    public void addToken(Token token) {
	tokens.add(token);
    }

    public void addTokenAtBegining(Token token) {
	tokens.add(0, token);
    }

    public Token get(int index) {
	return tokens.get(index);
    }

    public int getFirstVisbleTokenID() throws NoMatchingTokenException {
	for (int index = 0; index < tokens.size(); index++) {
	    if (get(index).getPublicity() != TokenPublicity.HIDDEN) {
		return index;
	    }
	}
	throw new NoMatchingTokenException();
    }

    public int getSize() {
	return tokens.size();
    }

    public Token findPreviousToken(int tokenID)
	    throws NoMatchingTokenException {
	int position = tokenID - 1;
	while (get(position).getPublicity() == TokenPublicity.HIDDEN) {
	    if (position == 0) {
		throw new NoMatchingTokenException();
	    }
	    position--;
	}
	return get(position);
    }
}
