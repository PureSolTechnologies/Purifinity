package com.puresol.coding.tokentypes;

import com.puresol.parser.tokens.TokenPublicity;

public class BlockEnd extends AbstractSourceTokenDefinition {

	@Override
	protected void initialize() {
		super.initialize();
		setPublicity(TokenPublicity.VISIBLE);
	}

	@Override
	public int changeBlockLayer() {
		return -1;
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
