package com.puresol.coding.lang.java.source.tokengroups;

import com.puresol.coding.lang.java.source.keywords.BooleanKeyword;
import com.puresol.coding.lang.java.source.keywords.ByteKeyword;
import com.puresol.coding.lang.java.source.keywords.CharKeyword;
import com.puresol.coding.lang.java.source.keywords.IntKeyword;
import com.puresol.coding.lang.java.source.keywords.LongKeyword;
import com.puresol.coding.lang.java.source.keywords.ShortKeyword;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class Primitives extends AbstractTokenDefinitionGroup {

	@Override
	protected void initialize() {
		addKeyword(BooleanKeyword.class);
		addKeyword(CharKeyword.class);
		addKeyword(ByteKeyword.class);
		addKeyword(ShortKeyword.class);
		addKeyword(IntKeyword.class);
		addKeyword(LongKeyword.class);
		addKeyword(VoidKeyword.class);
	}
}
