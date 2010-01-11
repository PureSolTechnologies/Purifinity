package com.puresol.coding.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class Assign extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "=";
    }

}
