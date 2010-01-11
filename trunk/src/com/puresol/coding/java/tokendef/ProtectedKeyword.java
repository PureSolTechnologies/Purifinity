package com.puresol.coding.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class ProtectedKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "protected";
    }

}
