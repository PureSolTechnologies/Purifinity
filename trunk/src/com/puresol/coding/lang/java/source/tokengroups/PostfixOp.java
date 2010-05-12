package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.symbols.MinusMinus;
import com.puresol.coding.lang.java.source.symbols.PlusPlus;
import com.puresol.parser.TokenDefinition;

public class PostfixOp {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();
    static {
	DEFINITIONS.add(PlusPlus.class);
	DEFINITIONS.add(MinusMinus.class);
    }

}
