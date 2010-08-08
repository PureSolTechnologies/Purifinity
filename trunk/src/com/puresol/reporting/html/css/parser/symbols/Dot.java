package com.puresol.reporting.html.css.parser.symbols;

import com.puresol.parser.tokens.AbstractTokenDefinition;

public class Dot extends AbstractTokenDefinition {

	@Override
	protected void initialize() {
		setPatternString("\\.");
	}

}
