package com.puresol.parser.tokens;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresol.exceptions.StrangeSituationException;

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
		try {
			addToken(Token.createByDefinition(EndOfTokenStreamToken.class,
					getSize(),
					get(getSize() - 1).getPosition()
							+ get(getSize() - 1).getLength(),
					get(getSize() - 1).getStopLine(), ""));
			locked = true;
		} catch (TokenCreationException e) {
			throw new StrangeSituationException(e);
		}
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

	public TokenStreamIterator createNewIterator() {
		return new TokenStreamIterator(this);
	}

	public int getSize() {
		return tokens.size();
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
