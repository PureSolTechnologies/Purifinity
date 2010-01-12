package com.puresol.coding.lang.perl.tokens;

import com.puresol.coding.tokentypes.Operator;

public class Sharp extends Operator {

	@Override
	public String getPatternString() {
		return "#";
	}

}
