package com.puresol.coding.java.keywords;

import com.puresol.parser.AbstractTokenDefinition;

public class ExtendsKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "extends";
    }

}
