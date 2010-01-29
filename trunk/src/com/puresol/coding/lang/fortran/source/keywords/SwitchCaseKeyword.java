package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;

public class SwitchCaseKeyword extends KeywordOperator {

    @Override
    protected void initialize() {
	super.initialize();
	setCaseInsensitive();
	setPatternString("SWITCH\\s+CASE");
    }

}
