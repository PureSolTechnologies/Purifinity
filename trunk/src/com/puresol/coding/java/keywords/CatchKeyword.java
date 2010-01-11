package com.puresol.coding.java.keywords;

import com.puresol.coding.tokentypes.Operator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class CatchKeyword extends Operator {

	@Override
	public String getPatternString() {
		return "catch";
	}

	@Override
	public String getHalsteadSymbol() {
		return "catch()";
	}

	@Override
	public int getCyclomaticNumber(Token token, TokenStream tokenStream) {
		return 1;
	}
}
