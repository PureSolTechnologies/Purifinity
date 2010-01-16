package com.puresol.coding.tokentypes;

public class KeywordOperant extends Operant {

	@Override
	protected void initialize() {
		super.initialize();
		setLookAheadPatternString("(?!\\w)");
	}
}
