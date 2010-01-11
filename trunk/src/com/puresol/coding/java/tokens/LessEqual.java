package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class LessEqual extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "<=";
    }

}
