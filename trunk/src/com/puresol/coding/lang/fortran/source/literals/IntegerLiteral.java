package com.puresol.coding.lang.fortran.source.literals;

import com.puresol.coding.tokentypes.Operant;

public class IntegerLiteral extends Operant {

	@Override
	public String getPatternString() {
		return "\\d+(?!\\w)";
	}

}
