package com.puresol.purifinity.uhura.ust.literals;

import com.puresol.purifinity.uhura.ust.AbstractUSTNode;

public abstract class Literal extends AbstractUSTNode {

	private static final long serialVersionUID = -1731135665103554027L;

	public Literal(String name, String originalSymbol) {
		super(name, originalSymbol);
	}
}
