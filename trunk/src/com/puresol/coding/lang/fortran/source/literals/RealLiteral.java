package com.puresol.coding.lang.fortran.source.literals;

import com.puresol.coding.tokentypes.Operant;

public class RealLiteral extends Operant {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString("(\\+|-)?\\d*\\.\\d+((E|D)(\\+|-)?\\d+)?(?!\\w)");
	}

}
