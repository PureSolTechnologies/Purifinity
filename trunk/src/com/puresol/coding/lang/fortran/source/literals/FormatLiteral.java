package com.puresol.coding.lang.fortran.source.literals;

import com.puresol.coding.tokentypes.KeywordOperant;

public class FormatLiteral extends KeywordOperant {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString("[0-9ADEFGIPX.]+");
	}

}
