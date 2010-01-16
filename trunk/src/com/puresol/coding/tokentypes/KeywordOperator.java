package com.puresol.coding.tokentypes;

public abstract class KeywordOperator extends Operator {

	@Override
	protected void initialize() {
		super.initialize();
		setLookAheadPatternString("(?!\\w)");
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
