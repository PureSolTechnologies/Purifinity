package com.puresol.coding.lang.fortran.source;

import com.puresol.coding.tokentypes.Operator;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenPublicity;
import com.puresol.parser.tokens.TokenStream;

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
