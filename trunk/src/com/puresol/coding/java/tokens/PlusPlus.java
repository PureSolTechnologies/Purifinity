package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class PlusPlus extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "\\+\\+";
    }

}
