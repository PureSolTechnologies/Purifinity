package com.puresol.coding.lang.fortran.source.symbols;

import com.puresol.coding.lang.fortran.LexicalStructure;
import com.puresol.coding.tokentypes.Operator;

public class Backslash extends Operator {

    @Override
    protected void initialize() {
	super.initialize();
	setCaseInsensitive();
	setPatternString(LexicalStructure.BACKSLASH);
    }

}
