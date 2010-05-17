package com.puresol.parser;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TokenStream is a special kind of stream for processing of text inputs. The
 * sum of all (non-added) token texts are the complete content of the previous
 * input.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class TokenStream implements Serializable {

	private static final long serialVersionUID = -4128486002145990691L;

	private final File file;
	private final List<Token> tokens = new ArrayList<Token>();
	private boolean locked = false;

	public TokenStream(File file) {
		this.file = file;
	}

	public final File getFile() {
		return file;
	}

	public final List<Token> getTokens() {
		return tokens;
	}

	public final boolean isLocked() {
		return locked;
	}

	public final void lock() {
		locked = true;
	}

	public final void addToken(Token token) {
		if (locked) {
			throw new IllegalStateException("TokenStream is locked!");
		}
		tokens.add(token);
	}

	public final Token get(int index) {
		if ((index >= tokens.size()) || (index < 0)) {
			return null;
		}
		return tokens.get(index);
	}

	public TokenStreamReader getReader() {
		return new TokenStreamReader(this);
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

	public Token findPreviousToken(int tokenID) throws NoMatchingTokenException {
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

	public Token findNextToken(int tokenID) throws NoMatchingTokenException {
		if (tokenID >= getSize() - 1) {
			throw new NoMatchingTokenException();
		}
		int position = tokenID + 1;
		while (get(position).getPublicity() == TokenPublicity.HIDDEN) {
			if (position >= getSize() - 1) {
				throw new NoMatchingTokenException();
			}
			position++;
		}
		return get(position);
	}

	public TokenStream getLineStream(int line) {
		TokenStream tokenStream = new TokenStream(getFile());
		int tokenID = 0;
		for (Token token : tokens) {
			if ((token.getStartLine() <= line) && (token.getStopLine() >= line)) {
				if ((token.getStopLine() != line)
						|| (!token.getText().endsWith("\n"))) {
					Token lineToken = Token.createWithNewID(token, tokenID);
					tokenStream.addToken(lineToken);
					tokenID++;
				}
			}
		}
		return tokenStream;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + ((tokens == null) ? 0 : tokens.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenStream other = (TokenStream) obj;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (tokens == null) {
			if (other.tokens != null)
				return false;
		} else if (!tokens.equals(other.tokens))
			return false;
		return true;
	}

}
