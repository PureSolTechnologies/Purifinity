package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class BooleanLiteral extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "(true|false)";
    }

}
