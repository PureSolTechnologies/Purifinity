package com.puresol.coding.lang.fortran.source.literals;

import com.puresol.coding.lang.fortran.LexicalStructure;
import com.puresol.coding.tokentypes.Operant;

public class ComplexLiteral extends Operant {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setPatternString(LexicalStructure.COMPLEX_LITERAL_CONSTANT);
	}

}
