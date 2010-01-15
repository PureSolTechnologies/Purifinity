package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class IfKeyword extends KeywordOperator {

    @Override
    public String getPatternString() {
	return "if";
    }

    @Override
    public String getHalsteadSymbol() {
	return "if()";
    }

    @Override
    public int getCyclomaticNumber(Token token, TokenStream tokenStream) {
	return 1;
    }
}
