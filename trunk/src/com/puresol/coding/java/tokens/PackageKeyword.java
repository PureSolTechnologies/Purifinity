package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class PackageKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "package";
    }

}
