package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class IntegerLiteral extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "\\d+";
    }

}
