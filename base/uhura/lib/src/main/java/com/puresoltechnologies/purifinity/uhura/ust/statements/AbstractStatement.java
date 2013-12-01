package com.puresoltechnologies.purifinity.uhura.ust.statements;

import com.puresoltechnologies.purifinity.uhura.ust.AbstractProduction;
import com.puresoltechnologies.purifinity.uhura.ust.UniversalSyntaxTree;

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
