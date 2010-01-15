package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;

public class NewKeyword extends KeywordOperator {

    @Override
    public String getPatternString() {
	return "new";
    }

}
