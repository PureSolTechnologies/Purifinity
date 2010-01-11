package com.puresol.coding.java;

import com.puresol.coding.java.tokendef.BooleanKeyword;
import com.puresol.coding.java.tokendef.ByteKeyword;
import com.puresol.coding.java.tokendef.CharKeyword;
import com.puresol.coding.java.tokendef.IntKeyword;
import com.puresol.coding.java.tokendef.LongKeyword;
import com.puresol.coding.java.tokendef.ShortKeyword;
import com.puresol.coding.java.tokendef.VoidKeyword;
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
