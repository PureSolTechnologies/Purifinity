package com.puresol.coding.java.keywords;

import com.puresol.parser.AbstractTokenDefinition;

public class BreakKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "break";
    }

}
