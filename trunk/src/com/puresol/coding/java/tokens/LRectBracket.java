package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.Operator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class LRectBracket extends Operator {

	@Override
	public String getPatternString() {
		return "\\[";
	}

	@Override
	public String getHalsteadSymbol() {
		return "[]";
	}

	@Override
	public boolean countForHalstead(Token token, TokenStream tokenStream) {
		return true;
	}

}
