package com.puresol.coding.java.keywords;

import com.puresol.parser.AbstractTokenDefinition;

public class ProtectedKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "protected";
    }

}
