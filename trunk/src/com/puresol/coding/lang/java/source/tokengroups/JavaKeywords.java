package com.puresol.coding.lang.java.source.tokengroups;

import com.puresol.coding.lang.java.source.keywords.BooleanKeyword;
import com.puresol.coding.lang.java.source.keywords.ByteKeyword;
import com.puresol.coding.lang.java.source.keywords.CharKeyword;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.keywords.DoubleKeyword;
import com.puresol.coding.lang.java.source.keywords.FloatKeyword;
import com.puresol.coding.lang.java.source.keywords.ImportKeyword;
import com.puresol.coding.lang.java.source.keywords.IntKeyword;
import com.puresol.coding.lang.java.source.keywords.LongKeyword;
import com.puresol.coding.lang.java.source.keywords.PackageKeyword;
import com.puresol.coding.lang.java.source.keywords.ShortKeyword;
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
