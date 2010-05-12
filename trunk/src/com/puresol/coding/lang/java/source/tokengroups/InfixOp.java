package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.symbols.Ampersand;
import com.puresol.coding.lang.java.source.symbols.BitOr;
import com.puresol.coding.lang.java.source.symbols.Caret;
import com.puresol.coding.lang.java.source.symbols.Equal;
import com.puresol.coding.lang.java.source.symbols.GreaterEqual;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.GreaterThanGreaterThan;
import com.puresol.coding.lang.java.source.symbols.GreaterThanGreaterThanGreaterThan;
import com.puresol.coding.lang.java.source.symbols.LessEqual;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.coding.lang.java.source.symbols.LessThanLessThan;
import com.puresol.coding.lang.java.source.symbols.LogicalAnd;
import com.puresol.coding.lang.java.source.symbols.LogicalOr;
import com.puresol.coding.lang.java.source.symbols.Minus;
import com.puresol.coding.lang.java.source.symbols.Percent;
import com.puresol.coding.lang.java.source.symbols.Plus;
import com.puresol.coding.lang.java.source.symbols.Slash;
import com.puresol.coding.lang.java.source.symbols.Star;
import com.puresol.coding.lang.java.source.symbols.Unequal;
import com.puresol.parser.TokenDefinition;

public class InfixOp {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();
    static {
	DEFINITIONS.add(LogicalOr.class);
	DEFINITIONS.add(LogicalAnd.class);
	DEFINITIONS.add(BitOr.class);
	DEFINITIONS.add(Caret.class);
	DEFINITIONS.add(Ampersand.class);
	DEFINITIONS.add(Equal.class);
	DEFINITIONS.add(Unequal.class);
	DEFINITIONS.add(LessThan.class);
	DEFINITIONS.add(GreaterThan.class);
	DEFINITIONS.add(LessEqual.class);
	DEFINITIONS.add(GreaterEqual.class);
	DEFINITIONS.add(LessThanLessThan.class);
	DEFINITIONS.add(GreaterThanGreaterThan.class);
	DEFINITIONS.add(GreaterThanGreaterThanGreaterThan.class);
	DEFINITIONS.add(Plus.class);
	DEFINITIONS.add(Minus.class);
	DEFINITIONS.add(Star.class);
	DEFINITIONS.add(Slash.class);
	DEFINITIONS.add(Percent.class);
    }

}
