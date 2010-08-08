package com.puresol.parser.tokens;

import java.io.Serializable;

import com.puresol.utils.TextUtils;

/**
 * This is an iterator object to iterate over a token stream for processing.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class TokenStreamIterator implements Serializable {

	private static final long serialVersionUID = -3522940623822464768L;

	private final TokenStream tokenStream;
	private int position = 0;

	public TokenStreamIterator(TokenStream tokenStream) {
		this.tokenStream = tokenStream;
		checkConditions();
	}

	public TokenStreamIterator(TokenStream tokenStream, int position) {
		this.tokenStream = tokenStream;
		this.position = position;
		checkConditions();
	}

	private void checkConditions() {
		if (!tokenStream.isLocked()) {
			throw new IllegalStateException(
					"Only locked token streams can be process by a reader to avoid data conflicts!");
		}
		if ((position < 0) || (position > tokenStream.getSize())) {
			throw new IllegalArgumentException(
					"Initial position of TokenStream is out of range!");
		}
	}

	public final int getPosition() {
		return position;
	}

	public final int getSize() {
		return tokenStream.getSize();
	}

	public final TokenStream getTokenStream() {
		return tokenStream;
	}

	public final boolean canGoForward() {
		return position < (tokenStream.getSize() - 1);
	}

	public final boolean canGoBackward() {
		return position > 0;
	}

	public final Token forward() throws EndOfTokenStreamException {
		move(+1);
		return getToken();
	}

	public final Token backward() throws EndOfTokenStreamException {
		move(-1);
		return getToken();
	}

	public final boolean isEOS() {
		return ((position < 0) || (position >= tokenStream.getSize()));
	}

	public final Token getToken() throws EndOfTokenStreamException {
		if (isEOS()) {
			throw new EndOfTokenStreamException(this);
		}

		return tokenStream.get(position);

	}

	public final int getStartLine() throws EndOfTokenStreamException {
		return getToken().getStartLine();

	}

	public final int getStopLine() throws EndOfTokenStreamException {
		return getToken().getStopLine();
	}

	public final Token getToken(int position) throws EndOfTokenStreamException {
		if ((position < 0) || (position >= tokenStream.getSize())) {
			throw new EndOfTokenStreamException(this);
		}
		return tokenStream.get(position);

	}

	public final int getStartLine(int position)
			throws EndOfTokenStreamException {
		if ((position < 0) || (position >= tokenStream.getSize())) {
			throw new EndOfTokenStreamException(this);
		}
		return tokenStream.get(position).getStartLine();

	}

	public final int getStopLine(int position) throws EndOfTokenStreamException {
		if ((position < 0) || (position >= tokenStream.getSize())) {
			throw new EndOfTokenStreamException(this);
		}
		return tokenStream.get(position).getStopLine();
	}

	public final int getFirstVisbleTokenID() throws EndOfTokenStreamException {
		for (int index = 0; index < getSize(); index++) {
			if (tokenStream.get(index).getPublicity() != TokenPublicity.HIDDEN) {
				return index;
			}
		}
		throw new EndOfTokenStreamException(this);
	}

	public final void move(int steps) throws EndOfTokenStreamException {
		if ((position + steps < 0)
				|| (position + steps >= tokenStream.getSize())) {
			throw new EndOfTokenStreamException(this);
		}
		position += steps;
	}

	public final void moveTo(int position) throws EndOfTokenStreamException {
		if ((position < 0) || (position >= tokenStream.getSize())) {
			throw new EndOfTokenStreamException(this);
		}
		this.position = position;
	}

	/**
	 * This method moves specified steps and than goes forward until the next
	 * visible token is found. If there is no remaining token found, the
	 * position found is at the end of tokenstream.
	 * 
	 * @param steps
	 * @throws EndOfTokenStreamException
	 */
	public final void moveToNextVisibleToken(int steps)
			throws EndOfTokenStreamException {
		move(steps);
		while (getToken().getPublicity() != TokenPublicity.VISIBLE) {
			move(1);
		}
	}

	public final TokenStream getLineStream(int line) {
		TokenStream tokenStream = new TokenStream(this.tokenStream.getFile());
		int currentLine = 1;
		int tokenIndex = 0;
		int tokenPosition = 0;
		for (int index = 0; index < tokenStream.getSize(); index++) {
			Token token = tokenStream.get(index);
			if ((token.getStartLine() <= line) && (token.getStopLine() >= line)) {
				if ((token.getStopLine() != line)
						|| (!token.getText().endsWith("\n"))) {
					Token lineToken = Token.createWithNewPositions(token,
							tokenIndex, tokenPosition, currentLine);
					tokenStream.addToken(lineToken);
					currentLine += TextUtils.countLineBreaks(token.getText());
					tokenIndex++;
					tokenPosition += token.getText().length();
				}
			}
		}
		return tokenStream;
	}
}
