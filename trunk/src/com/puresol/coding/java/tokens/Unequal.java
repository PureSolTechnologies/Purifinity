package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class Unequal extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "!=";
    }

}
