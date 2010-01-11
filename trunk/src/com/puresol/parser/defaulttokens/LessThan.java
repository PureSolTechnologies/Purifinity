package com.puresol.parser.defaulttokens;

import com.puresol.parser.AbstractTokenDefinition;

public class LessThan extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "<";
    }

}
