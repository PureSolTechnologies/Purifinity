package com.puresol.coding.tokentypes;

import com.puresol.parser.TokenPublicity;

public abstract class Comment extends
		AbstractProgrammingLanguageTokenDefinition {

	@Override
	public int changeBlockLayer() {
		return 0;
	}

	@Override
	public TokenPublicity getDefaultPublicity() {
		return TokenPublicity.HIDDEN;
	}

	@Override
	public SymbolType getSymbolType() {
		return SymbolType.COMMENT;
	}

	@Override
	public boolean isPrimitiveDataType() {
		return false;
	}
}
