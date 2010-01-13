package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.Operator;

public class Star extends Operator {

    @Override
    public String getPatternString() {
	return "\\*";
    }

    @Override
    public String getHalsteadSymbol() {
	return "*";
    }

}
