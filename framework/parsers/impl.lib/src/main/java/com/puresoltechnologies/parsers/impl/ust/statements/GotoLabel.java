package com.puresoltechnologies.parsers.impl.ust.statements;

import com.puresoltechnologies.parsers.impl.ust.AbstractProduction;

public class GotoLabel extends AbstractProduction {

	private static final long serialVersionUID = 5969840612811092250L;

	public GotoLabel(String originalSymbol) {
		super("Goto Label", originalSymbol);
	}
}
