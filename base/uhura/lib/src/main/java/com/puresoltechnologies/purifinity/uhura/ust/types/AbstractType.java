package com.puresoltechnologies.purifinity.uhura.ust.types;

import com.puresoltechnologies.purifinity.uhura.ust.AbstractProduction;

public abstract class AbstractType extends AbstractProduction {

	private static final long serialVersionUID = 2846791515023904393L;

	public AbstractType(String name, String originalSymbol) {
		super(name, originalSymbol);
	}
}
