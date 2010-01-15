package com.puresol.coding.tokentypes;

public abstract class PrimitiveDataType extends Operant {

    @Override
    public boolean isPrimitiveDataType() {
	return true;
    }

    @Override
    public String getLookAheadPatternString() {
	return "(?!\\w)";
    }
}
