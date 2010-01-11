package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class FloatingPointLiteral extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "(\\+|-)?\\d*\\.\\d+((e|E)(\\+|-)?\\d+)?";
    }

}
