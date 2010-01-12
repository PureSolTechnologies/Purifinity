package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.Operator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class ForKeyword extends Operator {

	@Override
	public String getPatternString() {
		return "for";
	}

	@Override
	public String getHalsteadSymbol() {
		return "for()";
	}

	@Override
	public int getCyclomaticNumber(Token token, TokenStream tokenStream) {
		return 1;
	}
}
