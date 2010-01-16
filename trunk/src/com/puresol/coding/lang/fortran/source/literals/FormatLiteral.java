package com.puresol.coding.lang.fortran.source.literals;

import com.puresol.coding.tokentypes.KeywordOperator;

public class FormatLiteral extends KeywordOperator {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString("[0-9ADEFIPX.]+");
	}

}
