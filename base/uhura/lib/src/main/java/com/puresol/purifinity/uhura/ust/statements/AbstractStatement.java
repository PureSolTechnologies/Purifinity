package com.puresol.purifinity.uhura.ust.statements;

import com.puresol.purifinity.uhura.ust.USTNode;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

public abstract class AbstractStatement extends USTNode {

	private static final long serialVersionUID = 4075534361231046558L;

	public AbstractStatement(String name, String originalSymbol) {
		super(name, originalSymbol);
	}

	public AbstractStatement(String name, String originalSymbol,
			UniversalSyntaxTree... children) {
		super(name, originalSymbol, children);
	}

}
