package com.puresol.coding.java.keywords;

import com.puresol.parser.AbstractTokenDefinition;

public class ClassKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "class";
    }

}
