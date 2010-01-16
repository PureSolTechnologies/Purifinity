package com.puresol.coding.tokentypes;

import com.puresol.parser.AbstractTokenDefinition;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public abstract class AbstractSourceTokenDefinition extends
		AbstractTokenDefinition implements SourceTokenDefinition {

	@Override
	protected void initialize() {
	}

	@Override
	public boolean countForHalstead(Token token, TokenStream tokenStream) {
		return true;
	}

	@Override
	public int getCyclomaticNumber(Token token, TokenStream tokenStream) {
		return 0;
	}

	@Override
	public String getHalsteadSymbol() {
		return getPatternString();
	}

}
