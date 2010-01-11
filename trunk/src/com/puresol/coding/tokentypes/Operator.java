package com.puresol.coding.tokentypes;

import com.puresol.parser.TokenPublicity;

public abstract class Operator extends AbstractProgrammingLanguageTokenDefinition {

	@Override
	public int changeBlockLayer() {
		return 0;
	}

	@Override
	public TokenPublicity getDefaultPublicity() {
		return TokenPublicity.VISIBLE;
	}

	@Override
	public SymbolType getSymbolType() {
		return SymbolType.OPERATOR;
	}

	@Override
	public boolean isPrimitiveDataType() {
		return false;
	}
}
