package com.puresol.parser.defaulttokens;

import com.puresol.parser.AbstractTokenDefinition;

public class LRectBracket extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "\\[";
    }

}
