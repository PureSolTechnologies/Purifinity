package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.symbols.Equal;
import com.puresol.coding.lang.java.source.symbols.GreaterEqual;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.LessEqual;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.coding.lang.java.source.symbols.LogicalAnd;
import com.puresol.coding.lang.java.source.symbols.LogicalOr;
import com.puresol.coding.lang.java.source.symbols.Unequal;
import com.puresol.parser.tokens.TokenDefinition;

public class Conditionals {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();
    static {
	DEFINITIONS.add(Equal.class);
	DEFINITIONS.add(Unequal.class);
	DEFINITIONS.add(LessThan.class);
	DEFINITIONS.add(GreaterThan.class);
	DEFINITIONS.add(LessEqual.class);
	DEFINITIONS.add(GreaterEqual.class);
	DEFINITIONS.add(LogicalAnd.class);
	DEFINITIONS.add(LogicalOr.class);
    }

}
