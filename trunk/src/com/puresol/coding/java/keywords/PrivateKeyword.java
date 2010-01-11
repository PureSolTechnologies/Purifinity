package com.puresol.coding.java.keywords;

import com.puresol.parser.AbstractTokenDefinition;

public class PrivateKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "private";
    }

}
