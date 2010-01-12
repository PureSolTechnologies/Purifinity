package com.puresol.coding.java.source.keywords;

import com.puresol.coding.tokentypes.Operator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class WhileKeyword extends Operator {

	@Override
	public String getPatternString() {
		return "while";
	}

	@Override
	public String getHalsteadSymbol() {
		return "while()";
	}

	@Override
	public int getCyclomaticNumber(Token token, TokenStream tokenStream) {
		return 1;
	}
}
