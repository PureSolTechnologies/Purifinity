package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;

public class ContinueKeyword extends KeywordOperator {

    @Override
    public String getPatternString() {
	setCaseInsensitive();
	return "CONTINUE";
    }

}
