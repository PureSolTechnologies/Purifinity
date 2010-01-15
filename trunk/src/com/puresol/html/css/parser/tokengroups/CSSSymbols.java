package com.puresol.html.css.parser.tokengroups;

import com.puresol.coding.lang.java.source.symbols.Colon;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class CSSSymbols extends AbstractTokenDefinitionGroup {

	@Override
	protected void initialize() {
		addTokenDefinition(LCurlyBracket.class);
		addTokenDefinition(RCurlyBracket.class);
		addTokenDefinition(Colon.class);
		addTokenDefinition(Dot.class);
	}
}
