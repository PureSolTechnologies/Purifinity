package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class ID extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "[a-zA-Z_]([a-zA-Z_0-9])*";
    }

}
