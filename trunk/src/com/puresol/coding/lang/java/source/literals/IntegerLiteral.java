package com.puresol.coding.lang.java.source.literals;

import com.puresol.coding.tokentypes.Literal;

public class IntegerLiteral extends Literal {

    @Override
    public String getPatternString() {
	return "\\d+L?";
    }

}
