package com.puresol.coding.java.keywords;

import com.puresol.parser.AbstractTokenDefinition;

public class ImportKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "import";
    }

}
