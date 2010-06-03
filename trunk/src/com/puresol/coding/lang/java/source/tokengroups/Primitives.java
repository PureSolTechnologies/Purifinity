package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.keywords.BooleanKeyword;
import com.puresol.coding.lang.java.source.keywords.ByteKeyword;
import com.puresol.coding.lang.java.source.keywords.CharKeyword;
import com.puresol.coding.lang.java.source.keywords.DoubleKeyword;
import com.puresol.coding.lang.java.source.keywords.FloatKeyword;
import com.puresol.coding.lang.java.source.keywords.IntKeyword;
import com.puresol.coding.lang.java.source.keywords.LongKeyword;
import com.puresol.coding.lang.java.source.keywords.ShortKeyword;
import com.puresol.parser.TokenDefinition;

public class Primitives {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();

    static {
	DEFINITIONS.add(BooleanKeyword.class);
	DEFINITIONS.add(CharKeyword.class);
	DEFINITIONS.add(ByteKeyword.class);
	DEFINITIONS.add(ShortKeyword.class);
	DEFINITIONS.add(IntKeyword.class);
	DEFINITIONS.add(LongKeyword.class);
	DEFINITIONS.add(FloatKeyword.class);
	DEFINITIONS.add(DoubleKeyword.class);
    }
}
