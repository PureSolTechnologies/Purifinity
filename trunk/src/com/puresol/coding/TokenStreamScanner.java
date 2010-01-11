/***************************************************************************
 *
 *   TokenStreamScanner.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;

public class TokenStreamScanner {

	private TokenStream tokenStream = null;

	public TokenStreamScanner(TokenStream tokenStream) {
		this.tokenStream = tokenStream;
	}

	public int findPreviousToken(String text) {
		if (tokenStream.getSize() == 0) {
			return -1;
		}
		return findPreviousToken(text, tokenStream.getSize() - 1);
	}

	public int findPreviousToken(String text, int startIndex) {
		if (tokenStream.getSize() == 0) {
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
		if (tokenStream.getSize() == 0) {
			return -1;
		}
		for (int index = startIndex - 1; index >= 0; index--) {
			Token token = tokenStream.get(index);
			if (token.getPublicity() != TokenPublicity.HIDDEN) {
				return index;
			}
		}
		return -1;
	}

	public int findNextToken(String text) {
		return findNextToken(text, 0);
	}

	public int findNextToken(String text, int startIndex) {
		for (int index = startIndex + 1; index < tokenStream.getSize(); index++) {
			Token token = tokenStream.get(index);
			if (token.getText().equals(text)) {
				return index;
			}
		}
		return -1;
	}

	public int findNextToken(int startIndex) {
		for (int index = startIndex + 1; index < tokenStream.getSize(); index++) {
			Token token = tokenStream.get(index);
			if (token.getPublicity() != TokenPublicity.HIDDEN) {
				return index;
			}
		}
		return -1;
	}

}
