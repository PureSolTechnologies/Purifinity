package com.puresol.coding.java.tokengroups;

import com.puresol.coding.java.keywords.BooleanKeyword;
import com.puresol.coding.java.keywords.ByteKeyword;
import com.puresol.coding.java.keywords.CharKeyword;
import com.puresol.coding.java.keywords.IntKeyword;
import com.puresol.coding.java.keywords.LongKeyword;
import com.puresol.coding.java.keywords.ShortKeyword;
import com.puresol.coding.java.keywords.VoidKeyword;
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
