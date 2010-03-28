package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class ElseIfKeyword extends KeywordOperator {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString("ELSE\\s*IF");
	}

	public int getCyclomaticNumber(Token token, TokenStream tokenStream) {
		return 1;
	}

}
