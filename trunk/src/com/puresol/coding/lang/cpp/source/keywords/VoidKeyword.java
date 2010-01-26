package com.puresol.coding.lang.cpp.source.keywords;

import com.puresol.coding.tokentypes.KeywordOperant;

public class VoidKeyword extends KeywordOperant {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("void");
	}

}
