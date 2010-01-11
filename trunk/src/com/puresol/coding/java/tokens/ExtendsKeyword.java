package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class ExtendsKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "extends";
    }

}
