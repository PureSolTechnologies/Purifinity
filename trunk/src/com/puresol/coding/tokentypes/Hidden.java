package com.puresol.coding.tokentypes;

import com.puresol.parser.TokenPublicity;

public abstract class Hidden extends
	AbstractProgrammingLanguageTokenDefinition {

    @Override
    public int changeBlockLayer() {
	return 0;
    }

    @Override
    public TokenPublicity getPublicity() {
	return TokenPublicity.HIDDEN;
    }

    @Override
    public SymbolType getSymbolType() {
	return SymbolType.HIDDEN;
    }

    @Override
    public boolean isPrimitiveDataType() {
	return false;
    }
}
