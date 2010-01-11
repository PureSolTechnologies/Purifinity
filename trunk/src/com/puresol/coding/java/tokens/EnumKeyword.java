package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class EnumKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "enum";
    }

}
