package com.puresol.coding.tokentypes;

import com.puresol.parser.AbstractTokenDefinition;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public abstract class AbstractProgrammingLanguageTokenDefinition extends
		AbstractTokenDefinition implements ProgrammingLanguageTokenDefinition {

	@Override
	public boolean countForHalstead() {
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
