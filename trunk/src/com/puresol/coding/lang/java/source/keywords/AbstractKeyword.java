package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;

public class AbstractKeyword extends KeywordOperator {

    @Override
    public String getPatternString() {
	return "abstract";
    }

}
