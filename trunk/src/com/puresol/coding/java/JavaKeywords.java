package com.puresol.coding.java;

import com.puresol.coding.java.keywordgroups.Primitives;
import com.puresol.coding.java.keywords.ClassKeyword;
import com.puresol.coding.java.keywords.PackageKeyword;
import com.puresol.parser.AbstractTokenDefinitions;

public class JavaKeywords extends AbstractTokenDefinitions {

    @Override
    protected void initialize() {
	addKeyword(PackageKeyword.class);
	addKeyword(ClassKeyword.class);
	addKeywords(Primitives.class);
    }
}
