package com.puresol.purifinity.uhura.ust.declarations;

import com.puresol.purifinity.uhura.ust.Production;

public abstract class AbstractDeclaration extends Production {

	private static final long serialVersionUID = 721736733272860703L;

	public AbstractDeclaration(String name, String originalSymbol) {
		super(name, originalSymbol);
	}

}
