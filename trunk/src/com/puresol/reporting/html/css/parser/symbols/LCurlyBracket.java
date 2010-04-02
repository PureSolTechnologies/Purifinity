package com.puresol.reporting.html.css.parser.symbols;

import com.puresol.parser.AbstractTokenDefinition;

public class LCurlyBracket extends AbstractTokenDefinition {

	@Override
	protected void initialize() {
		setPatternString("\\{");
	}
}
