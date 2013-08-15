package com.puresol.purifinity.uhura.ust.statements;

import com.puresol.purifinity.uhura.ust.USTNode;

public abstract class AbstractStatement extends USTNode {

	private static final long serialVersionUID = 4075534361231046558L;

	public AbstractStatement(String name, String originalSymbol) {
		super(name, originalSymbol);
	}

}
