package com.puresol.purifinity.uhura.ust.facilities;

import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

public abstract class AbstractToken extends
		UniversalSyntaxTree {

	private static final long serialVersionUID = -7902733137033456572L;

	public AbstractToken(String originalSymbol) {
		super(originalSymbol);
	}

}
