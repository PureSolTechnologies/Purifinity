package com.puresol.coding.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class BooleanLiteral extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "(true|false)";
    }

}
