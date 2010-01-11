package com.puresol.coding.java.tokens;

import com.puresol.coding.java.keywords.CatchKeyword;
import com.puresol.coding.java.keywords.ForKeyword;
import com.puresol.coding.java.keywords.IfKeyword;
import com.puresol.coding.java.keywords.WhileKeyword;
import com.puresol.coding.tokentypes.Operator;
import com.puresol.parser.NoMatchingTokenException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class LParen extends Operator {

	@Override
	public String getPatternString() {
		return "\\(";
	}

	@Override
	public String getHalsteadSymbol() {
		return "()";
	}

	@Override
	public boolean countForHalstead(Token token, TokenStream tokenStream) {
		try {
			Token prevToken;
			prevToken = tokenStream.findPreviousToken(token.getTokenID());
			if (prevToken.getDefinition().equals(ForKeyword.class)) {
				return false;
			}
			if (prevToken.getDefinition().equals(WhileKeyword.class)) {
				return false;
			}
			if (prevToken.getDefinition().equals(IfKeyword.class)) {
				return false;
			}
			if (prevToken.getDefinition().equals(CatchKeyword.class)) {
				return false;
			}
			return true;
		} catch (NoMatchingTokenException e) {
			return true;
		}
	}

}
