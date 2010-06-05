package com.puresol.reporting.html.css.parser.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.parser.TokenDefinition;
import com.puresol.reporting.html.css.parser.symbols.Colon;
import com.puresol.reporting.html.css.parser.symbols.Dot;
import com.puresol.reporting.html.css.parser.symbols.LCurlyBracket;
import com.puresol.reporting.html.css.parser.symbols.RCurlyBracket;

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
