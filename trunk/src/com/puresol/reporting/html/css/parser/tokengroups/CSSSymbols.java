package com.puresol.reporting.html.css.parser.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.symbols.Colon;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.TokenDefinition;

public class CSSSymbols {

    public static final CSSSymbols INSTANCE = new CSSSymbols();

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();

    static {
	DEFINITIONS.add(LCurlyBracket.class);
	DEFINITIONS.add(RCurlyBracket.class);
	DEFINITIONS.add(Colon.class);
	DEFINITIONS.add(Dot.class);
    }
}
