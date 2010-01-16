package com.puresol.coding.lang.perl.tokens;

import com.puresol.coding.tokentypes.Operator;

public class Sharp extends Operator {

	@Override
	protected void initialize() {
		setPatternString("#");
	}

}
