package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;

public class EndForAllKeyword extends KeywordOperator {

    @Override
    protected void initialize() {
	super.initialize();
	setCaseInsensitive();
	setPatternString("END\\s+FORALL");
    }

}
