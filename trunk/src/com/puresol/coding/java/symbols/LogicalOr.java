package com.puresol.coding.java.symbols;

import com.puresol.coding.tokentypes.Operator;

public class LogicalOr extends Operator {

    @Override
    public String getPatternString() {
	return "\\|\\|";
    }

}
