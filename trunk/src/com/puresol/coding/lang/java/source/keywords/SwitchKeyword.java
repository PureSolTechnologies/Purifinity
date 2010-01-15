package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class SwitchKeyword extends KeywordOperator {

    @Override
    public String getPatternString() {
	return "switch";
    }

    @Override
    public String getHalsteadSymbol() {
	return "switch()";
    }

    @Override
    public int getCyclomaticNumber(Token token, TokenStream tokenStream) {
	return 1;
    }
}
