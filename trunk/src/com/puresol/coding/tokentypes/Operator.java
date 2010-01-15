package com.puresol.coding.tokentypes;

import com.puresol.parser.TokenPublicity;

public abstract class Operator extends AbstractSourceTokenDefinition {

    @Override
    public int changeBlockLayer() {
	return 0;
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

    @Override
    public String getLookAheadPatternString() {
	return "";
    }
}
