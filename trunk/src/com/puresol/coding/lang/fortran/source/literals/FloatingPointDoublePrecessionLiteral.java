package com.puresol.coding.lang.fortran.source.literals;

import com.puresol.coding.lang.fortran.LexicalStructure;
import com.puresol.coding.tokentypes.Operant;

public class FloatingPointDoublePrecessionLiteral extends Operant {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString(LexicalStructure.REAL_LITERAL_CONSTANT);
	}

}
