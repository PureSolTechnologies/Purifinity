package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.Operator;

public class GreaterThanGreaterThanAssign extends Operator {

    @Override
    protected void initialize() {
	super.initialize();
	setPatternString(">>=");
    }

}
