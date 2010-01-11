package com.puresol.coding.perl.tokens;

import com.puresol.coding.tokentypes.Operator;

public class Dollar extends Operator {

	@Override
	public String getPatternString() {
		return "\\$";
	}

}
