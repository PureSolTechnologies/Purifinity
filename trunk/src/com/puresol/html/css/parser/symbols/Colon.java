package com.puresol.html.css.parser.symbols;

import com.puresol.parser.AbstractTokenDefinition;

public class Colon extends AbstractTokenDefinition {

	@Override
	protected void initialize() {
		setPatternString(":");
	}
}
