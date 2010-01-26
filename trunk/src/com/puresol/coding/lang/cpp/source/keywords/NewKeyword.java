package com.puresol.coding.lang.cpp.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperator;

public class NewKeyword extends KeywordOperator {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("new");
	}

}
