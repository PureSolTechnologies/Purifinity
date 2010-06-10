package com.puresol.coding.lang.fortran.source.literals;

import com.puresol.coding.lang.fortran.LexicalStructure;
import com.puresol.coding.tokentypes.Operant;

public class CharLiteral extends Operant {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString(LexicalStructure.CHAR_LITERAL_CONSTANT);
	}
}
