package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class ShortKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "short";
    }

}
