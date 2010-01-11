package com.puresol.coding.java.keywords;

import com.puresol.parser.AbstractTokenDefinition;

public class PackageKeyword extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	return "package";
    }

}
