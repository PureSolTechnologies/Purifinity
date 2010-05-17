package com.puresol.parser;

import java.io.Serializable;

public final class TokenStreamReader implements Serializable {

	private static final long serialVersionUID = -3522940623822464768L;

	private final TokenStream tokenStream;
	private int position = 0;

	public TokenStreamReader(TokenStream tokenStream) {
		this.tokenStream = tokenStream;
	}

	public TokenStreamReader(TokenStream tokenStream, int position) {
		this.tokenStream = tokenStream;
		if ((position < 0) || (position > tokenStream.getSize())) {
			throw new IllegalArgumentException(
					"Initial position of TokenStream is out of range!");
		}
		this.position = position;
	}

	public final int getPosition() {
		return position;
	}

	public final int getSize() {
		return tokenStream.getSize();
	}

	public final boolean canGoForward() {
		return position < (tokenStream.getSize());
	}

	public final boolean canGoBackward() {
		return position > 0;
	}

	public final Token forward() {
		if (!canGoForward()) {
			throw new IllegalStateException();
		}
		position++;
		return tokenStream.get(position);
	}

	public final Token backward() {
		if (!canGoBackward()) {
			throw new IllegalStateException();
		}
		position--;
		return tokenStream.get(position);
	}

	public final boolean isEOS() {
		return (position >= tokenStream.getSize());
	}

	public final Token getToken() {
		return tokenStream.get(position);
	}
}
