package com.puresol.coding.java.keywords;

import com.puresol.parser.AbstractTokenDefinition;

public class StaticKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "static";
    }

}
