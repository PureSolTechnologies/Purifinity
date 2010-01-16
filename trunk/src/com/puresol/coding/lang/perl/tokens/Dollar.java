package com.puresol.coding.lang.perl.tokens;

import com.puresol.coding.tokentypes.Operator;

public class Dollar extends Operator {

	@Override
	protected void initialize() {
		setPatternString("\\$");
	}

}
