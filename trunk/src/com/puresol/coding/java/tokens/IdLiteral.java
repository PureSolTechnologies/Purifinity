package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class IdLiteral extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "[a-zA-Z_]([a-zA-Z_0-9])*";
    }

}
