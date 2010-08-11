package com.puresol.coding.lang.fortran.source.symbols;

import com.puresol.coding.lang.fortran.LexicalStructure;
import com.puresol.coding.tokentypes.Operator;
import com.puresol.parser.tokens.TokenPublicity;

public class Blank extends Operator {

	@Override
	protected void initialize() {
		super.initialize();
		setPublicity(TokenPublicity.HIDDEN);
		setCaseInsensitive();
		setPatternString(LexicalStructure.BLANK);
	}

}
