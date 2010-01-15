package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class WhileKeyword extends KeywordOperator {

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
