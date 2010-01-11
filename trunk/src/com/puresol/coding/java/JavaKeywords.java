package com.puresol.coding.java;

import com.puresol.coding.java.tokens.ClassKeyword;
import com.puresol.coding.java.tokens.PackageKeyword;
import com.puresol.parser.AbstractTokenDefinitions;

public class JavaKeywords extends AbstractTokenDefinitions {

    @Override
    protected void initialize() {
	addKeyword(PackageKeyword.class);
	addKeyword(ClassKeyword.class);
	addKeywords(Primitives.class);
    }
}
