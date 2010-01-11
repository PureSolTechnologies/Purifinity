package com.puresol.coding.java;

import com.puresol.coding.java.tokens.BooleanKeyword;
import com.puresol.coding.java.tokens.ByteKeyword;
import com.puresol.coding.java.tokens.CharKeyword;
import com.puresol.coding.java.tokens.IntKeyword;
import com.puresol.coding.java.tokens.LongKeyword;
import com.puresol.coding.java.tokens.ShortKeyword;
import com.puresol.coding.java.tokens.VoidKeyword;
import com.puresol.parser.AbstractTokenDefinitions;

public class Primitives extends AbstractTokenDefinitions {

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
