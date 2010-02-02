package com.puresol.parser;

import java.io.File;
import java.util.ArrayList;

/**
 * TokenStream is a special kind of stream for processing of text inputs.
 * The sum of all (non-added) token texts are the complete content of the
 * previous input.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class TokenStream {

    private final File file;
    private final ArrayList<Token> tokens = new ArrayList<Token>();

    public TokenStream(File file) {
	this.file = file;
    }

    public File getFile() {
	return file;
    }

    public ArrayList<Token> getTokens() {
	return tokens;
    }

    public void addToken(Token token) {
	tokens.add(token);
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
	if (tokenID <= 0) {
	    throw new NoMatchingTokenException();
	}
	int position = tokenID - 1;
	while (get(position).getPublicity() == TokenPublicity.HIDDEN) {
	    if (position == 0) {
		throw new NoMatchingTokenException();
	    }
	    position--;
	}
	return get(position);
    }

    public Token findNextToken(int tokenID)
	    throws NoMatchingTokenException {
	if (tokenID >= getSize() - 2) {
	    throw new NoMatchingTokenException();
	}
	int position = tokenID + 1;
	while (get(position).getPublicity() == TokenPublicity.HIDDEN) {
	    if (position >= getSize() - 2) {
		throw new NoMatchingTokenException();
	    }
	    position++;
	}
	return get(position);
    }
}
