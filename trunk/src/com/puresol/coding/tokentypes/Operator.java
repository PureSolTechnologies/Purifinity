package com.puresol.coding.tokentypes;

import com.puresol.parser.TokenPublicity;

public class Operator extends AbstractSourceTokenDefinition {

	@Override
	protected void initialize() {
		super.initialize();
		setPublicity(TokenPublicity.VISIBLE);
	}

	@Override
	public int changeBlockLayer() {
		return 0;
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
