package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class CatchKeyword extends KeywordOperator {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("catch");
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
