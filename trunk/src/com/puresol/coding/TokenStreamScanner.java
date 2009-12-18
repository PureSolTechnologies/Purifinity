package com.puresol.coding;

import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public class TokenStreamScanner {

	private TokenStream tokenStream = null;

	public TokenStreamScanner(TokenStream tokenStream) {
		this.tokenStream = tokenStream;
	}

	public int findPreviousToken(String text) {
		if (tokenStream.size() == 0) {
			return -1;
		}
		return findPreviousToken(text, tokenStream.size() - 1);
	}

	public int findPreviousToken(String text, int startIndex) {
		if (tokenStream.size() == 0) {
			return -1;
		}
		for (int index = startIndex - 1; index >= 0; index--) {
			Token token = tokenStream.get(index);
			if (token.getText().equals(text)) {
				return index;
			}
		}
		return -1;
	}

	public int findPreviousToken(int startIndex) {
		if (tokenStream.size() == 0) {
			return -1;
		}
		for (int index = startIndex - 1; index >= 0; index--) {
			Token token = tokenStream.get(index);
			if (token.getChannel() != Token.HIDDEN_CHANNEL) {
				return index;
			}
		}
		return -1;
	}

	public int findNextToken(String text) {
		return findNextToken(text, 0);
	}

	public int findNextToken(String text, int startIndex) {
		for (int index = startIndex + 1; index < tokenStream.size(); index++) {
			Token token = tokenStream.get(index);
			if (token.getText().equals(text)) {
				return index;
			}
		}
		return -1;
	}

	public int findNextToken(int startIndex) {
		for (int index = startIndex + 1; index < tokenStream.size(); index++) {
			Token token = tokenStream.get(index);
			if (token.getChannel() != Token.HIDDEN_CHANNEL) {
				return index;
			}
		}
		return -1;
	}

}
