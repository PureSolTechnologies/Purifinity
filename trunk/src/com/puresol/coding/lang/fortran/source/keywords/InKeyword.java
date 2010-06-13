package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;

public class InKeyword extends KeywordOperator {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString("IN");
	}

}
