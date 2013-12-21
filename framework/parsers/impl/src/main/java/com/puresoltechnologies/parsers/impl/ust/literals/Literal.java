package com.puresoltechnologies.parsers.impl.ust.literals;

import com.puresoltechnologies.parsers.impl.ust.AbstractProduction;

public abstract class Literal extends AbstractProduction {

	private static final long serialVersionUID = -1731135665103554027L;

	public Literal(String name, String originalSymbol) {
		super(name, originalSymbol);
	}
}
