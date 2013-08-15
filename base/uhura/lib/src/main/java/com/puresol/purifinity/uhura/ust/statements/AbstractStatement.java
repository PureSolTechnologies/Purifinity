package com.puresol.purifinity.uhura.ust.statements;

import com.puresol.purifinity.uhura.ust.AbstractUSTNode;

public abstract class AbstractStatement extends AbstractUSTNode {

	private static final long serialVersionUID = 4075534361231046558L;

	public AbstractStatement(String name, String originalSymbol) {
		super(name, originalSymbol);
	}

}
