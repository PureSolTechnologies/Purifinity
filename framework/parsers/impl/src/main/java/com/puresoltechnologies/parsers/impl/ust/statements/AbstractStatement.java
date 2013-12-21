package com.puresoltechnologies.parsers.impl.ust.statements;

import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.impl.ust.AbstractProduction;

public abstract class AbstractStatement extends AbstractProduction {

	private static final long serialVersionUID = 4075534361231046558L;

	public AbstractStatement(String name, String originalSymbol) {
		super(name, originalSymbol);
	}

	public AbstractStatement(String name, String originalSymbol,
			UniversalSyntaxTree... children) {
		super(name, originalSymbol, children);
	}

}
