package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class CaseKeyword extends KeywordOperator {

    @Override
    public String getPatternString() {
	return "case";
    }

    @Override
    public String getHalsteadSymbol() {
	return "case:";
    }

    @Override
    public int getCyclomaticNumber(Token token, TokenStream tokenStream) {
	// TODO to be corrected for case 1:case 2:...
	return 1;
    }
}
