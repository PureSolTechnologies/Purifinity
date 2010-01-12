package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.Operator;

public class SubRoutineKeyword extends Operator {

	@Override
	public String getPatternString() {
		setCaseInsensitive();
		return "SUBROUTINE";
	}

}
