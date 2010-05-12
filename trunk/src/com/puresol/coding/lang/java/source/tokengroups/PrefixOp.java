package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.symbols.Minus;
import com.puresol.coding.lang.java.source.symbols.MinusMinus;
import com.puresol.coding.lang.java.source.symbols.Not;
import com.puresol.coding.lang.java.source.symbols.Plus;
import com.puresol.coding.lang.java.source.symbols.PlusPlus;
import com.puresol.coding.lang.java.source.symbols.Tilde;
import com.puresol.parser.TokenDefinition;

public class PrefixOp {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();
    static {
	DEFINITIONS.add(PlusPlus.class);
	DEFINITIONS.add(MinusMinus.class);
	DEFINITIONS.add(Not.class);
	DEFINITIONS.add(Tilde.class);
	DEFINITIONS.add(Plus.class);
	DEFINITIONS.add(Minus.class);
    }

}
