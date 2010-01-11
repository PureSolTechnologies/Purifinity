package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class CharKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "char";
    }

}
