package com.puresol.parser.java;

import com.puresol.parser.AbstractTokenDefinitions;
import com.puresol.parser.java.tokendef.ClassKeyword;
import com.puresol.parser.java.tokendef.PackageKeyword;

public class JavaKeywords extends AbstractTokenDefinitions {

    @Override
    protected void initialize() {
	addKeyword(PackageKeyword.class);
	addKeyword(ClassKeyword.class);
	addKeywords(Primitives.class);
    }
}
