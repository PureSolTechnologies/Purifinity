package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class ContinueKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "continue";
    }

}
