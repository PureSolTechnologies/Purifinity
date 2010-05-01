package com.puresol.coding.lang.java.source.literals;

import com.puresol.coding.tokentypes.KeywordOperant;

public class NullKeyword extends KeywordOperant {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("null");
	}

}
