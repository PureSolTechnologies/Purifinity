package com.puresol.parser;

public class TokenStreamReader {

    private final TokenStream tokenStream;
    private int currentPosition = 0;

    public TokenStreamReader(TokenStream tokenStream) {
	this.tokenStream = tokenStream;
    }

    public int getCurrentPosition() {
	return currentPosition;
    }

    public int getSize() {
	return tokenStream.getSize();
    }

    public boolean hasNext() {
	return currentPosition < (tokenStream.getSize() - 1);
    }

    public boolean hasPrevious() {
	return currentPosition > 0;
    }

    public Token next() {
	if (!hasNext()) {
	    throw new IllegalStateException();
	}
	currentPosition++;
	return tokenStream.get(currentPosition);
    }

    public Token previous() {
	if (!hasPrevious()) {
	    throw new IllegalStateException();
	}
	currentPosition--;
	return tokenStream.get(currentPosition);
    }
}
