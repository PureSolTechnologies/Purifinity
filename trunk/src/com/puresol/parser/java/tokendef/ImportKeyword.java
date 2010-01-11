package com.puresol.parser.java.tokendef;

import com.puresol.parser.AbstractTokenDefinition;

public class ImportKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "import";
    }

}
