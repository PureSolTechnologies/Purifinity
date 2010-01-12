package com.puresol.coding.java.tokengroups;

import com.puresol.coding.java.keywords.BooleanKeyword;
import com.puresol.coding.java.keywords.ByteKeyword;
import com.puresol.coding.java.keywords.CharKeyword;
import com.puresol.coding.java.keywords.ClassKeyword;
import com.puresol.coding.java.keywords.DoubleKeyword;
import com.puresol.coding.java.keywords.FloatKeyword;
import com.puresol.coding.java.keywords.ImportKeyword;
import com.puresol.coding.java.keywords.IntKeyword;
import com.puresol.coding.java.keywords.LongKeyword;
import com.puresol.coding.java.keywords.PackageKeyword;
import com.puresol.coding.java.keywords.ShortKeyword;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class JavaKeywords extends AbstractTokenDefinitionGroup {

    @Override
    protected void initialize() {
	addKeyword(PackageKeyword.class);
	addKeyword(ImportKeyword.class);

	addKeyword(ClassKeyword.class);

	addKeyword(BooleanKeyword.class);
	addKeyword(CharKeyword.class);
	addKeyword(ByteKeyword.class);
	addKeyword(ShortKeyword.class);
	addKeyword(IntKeyword.class);
	addKeyword(LongKeyword.class);
	addKeyword(FloatKeyword.class);
	addKeyword(DoubleKeyword.class);
    }
}
