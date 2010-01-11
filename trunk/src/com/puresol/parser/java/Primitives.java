package com.puresol.parser.java;

import com.puresol.parser.AbstractTokenDefinitions;
import com.puresol.parser.java.tokendef.BooleanKeyword;
import com.puresol.parser.java.tokendef.ByteKeyword;
import com.puresol.parser.java.tokendef.CharKeyword;
import com.puresol.parser.java.tokendef.IntKeyword;
import com.puresol.parser.java.tokendef.LongKeyword;
import com.puresol.parser.java.tokendef.ShortKeyword;
import com.puresol.parser.java.tokendef.VoidKeyword;

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
