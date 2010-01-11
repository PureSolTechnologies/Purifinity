package com.puresol.coding.java.keywords;

import com.puresol.parser.AbstractTokenDefinition;

public class TransientKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "transient";
    }

}
