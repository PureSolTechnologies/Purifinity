package com.puresol.coding.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class MinusMinus extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "--";
    }

}
