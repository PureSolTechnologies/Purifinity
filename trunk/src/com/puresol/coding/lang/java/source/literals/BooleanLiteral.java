package com.puresol.coding.lang.java.source.literals;

import com.puresol.coding.tokentypes.Literal;

public class BooleanLiteral extends Literal {

    @Override
    public String getPatternString() {
	return "(true|false)";
    }

}
