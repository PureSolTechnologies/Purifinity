package com.puresol.coding.lang.fortran.source.symbols;

import com.puresol.coding.lang.fortran.LexicalStructure;
import com.puresol.coding.tokentypes.Hidden;

public class LineTerminator extends Hidden {

    @Override
    protected void initialize() {
	super.initialize();
	setCaseInsensitive();
	setPatternString(LexicalStructure.LINE_TERMINATOR);
    }

}
