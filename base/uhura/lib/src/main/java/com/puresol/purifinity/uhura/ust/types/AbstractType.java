package com.puresol.purifinity.uhura.ust.types;

import com.puresol.purifinity.uhura.ust.Production;

public abstract class AbstractType extends Production {

	private static final long serialVersionUID = 2846791515023904393L;

	public AbstractType(String name, String originalSymbol) {
		super(name, originalSymbol);
	}
}
