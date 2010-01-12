package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.Operator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class RRectBracket extends Operator {

	@Override
	public String getPatternString() {
		return "\\]";
	}

	@Override
	public String getHalsteadSymbol() {
		return "[]";
	}

	@Override
	public boolean countForHalstead(Token token, TokenStream tokenStream) {
		return false;
	}

}
