package com.puresol.coding.lang.fortran.source.literals;

import com.puresol.coding.tokentypes.Operant;

public class FloatingPointLiteral extends Operant {

	@Override
	public String getPatternString() {
		return "(\\+|-)?\\d*\\.\\d+((e|E)(\\+|-)?\\d+)?";
	}

}
