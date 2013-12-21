package com.puresoltechnologies.parsers.impl.ust.types;

import com.puresoltechnologies.parsers.impl.ust.AbstractProduction;

public abstract class AbstractType extends AbstractProduction {

	private static final long serialVersionUID = 2846791515023904393L;

	public AbstractType(String name, String originalSymbol) {
		super(name, originalSymbol);
	}
}
