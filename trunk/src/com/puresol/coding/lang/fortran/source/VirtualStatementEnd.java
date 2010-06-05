package com.puresol.coding.lang.fortran.source;

import com.puresol.coding.tokentypes.Operator;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;

public class VirtualStatementEnd extends Operator {

	@Override
	protected void initialize() {
		super.initialize();
		setPublicity(TokenPublicity.ADDED);
		setCaseInsensitive();
		setPatternString("");
	}

	@Override
	public boolean countForHalstead(Token token, TokenStream tokenStream) {
		return false;
	}

}
