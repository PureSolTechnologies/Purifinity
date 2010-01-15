package com.puresol.coding.lang.java.source.literals;

import com.puresol.coding.tokentypes.Literal;

public class IdLiteral extends Literal {

    @Override
    public String getPatternString() {
	return "[a-zA-Z_]([a-zA-Z_0-9])*";
    }

}
