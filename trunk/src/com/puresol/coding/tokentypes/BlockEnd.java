package com.puresol.coding.tokentypes;

import com.puresol.parser.TokenPublicity;

public abstract class BlockEnd extends
		AbstractProgrammingLanguageTokenDefinition {

	@Override
	public int changeBlockLayer() {
		return -1;
	}

	@Override
	public TokenPublicity getPublicity() {
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
