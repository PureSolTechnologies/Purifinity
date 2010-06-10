package com.puresol.coding.lang.fortran.source.literals;

import com.puresol.coding.lang.fortran.LexicalStructure;
import com.puresol.coding.tokentypes.Operant;

public class NameLiteral extends Operant {

	@Override
	protected void initialize() {
		super.initialize();
		setCaseInsensitive();
		setLookAheadPatternString("(?!\\w)");
		setPatternString(LexicalStructure.NAME);
	}

}
