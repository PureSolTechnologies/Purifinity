package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class VoidKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "void";
    }

}
