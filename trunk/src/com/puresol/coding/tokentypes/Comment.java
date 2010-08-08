package com.puresol.coding.tokentypes;

import com.puresol.parser.tokens.TokenPublicity;

public class Comment extends AbstractSourceTokenDefinition {

	@Override
	protected void initialize() {
		super.initialize();
		setPublicity(TokenPublicity.HIDDEN);
	}

	@Override
	public int changeBlockLayer() {
		return 0;
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
