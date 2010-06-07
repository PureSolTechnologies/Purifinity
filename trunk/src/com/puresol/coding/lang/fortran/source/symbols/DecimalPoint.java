package com.puresol.coding.lang.fortran.source.symbols;

import com.puresol.coding.lang.fortran.LexicalStructure;
import com.puresol.coding.tokentypes.Operator;

public class DecimalPoint extends Operator {

    @Override
    protected void initialize() {
	super.initialize();
	setCaseInsensitive();
	setPatternString(LexicalStructure.DECIMALPOINT_OR_PERIOD);
    }

}
