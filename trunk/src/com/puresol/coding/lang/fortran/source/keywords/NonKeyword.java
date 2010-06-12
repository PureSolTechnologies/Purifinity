package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class NonKeyword extends KeywordOperator {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString("NON");
	}

	public int getCyclomaticNumber(Token token, TokenStream tokenStream) {
		return 1;
	}

	public int changeBlockLayer() {
		return 1;
	}

}
