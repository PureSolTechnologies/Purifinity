package com.puresol.html.css.parser.symbols;

import com.puresol.parser.AbstractTokenDefinition;

public class LCurlyBracket extends AbstractTokenDefinition {

	@Override
	public String getPatternString() {
		return "\\{";
	}

	@Override
	public String getLookAheadPatternString() {
		return "";
	}

}
