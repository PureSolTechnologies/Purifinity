package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenStream;

public class ElseWhereKeyword extends KeywordOperator {

    @Override
    protected void initialize() {
	super.initialize();
	setCaseInsensitive();
	setPatternString("ELSE\\s*WHERE");
    }

    public int getCyclomaticNumber(Token token, TokenStream tokenStream) {
	return 1;
    }

}
