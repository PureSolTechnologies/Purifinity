package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;

public class EndBlockDataKeyword extends KeywordOperator {

    @Override
    protected void initialize() {
	super.initialize();
	setCaseInsensitive();
	setPatternString("END\\s*BLOCK\\s*DATA");
    }

    public int changeBlockLayer() {
	return -1;
    }

}
