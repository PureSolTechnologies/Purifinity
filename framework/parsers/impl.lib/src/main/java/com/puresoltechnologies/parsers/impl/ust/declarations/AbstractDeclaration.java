package com.puresoltechnologies.parsers.impl.ust.declarations;

import com.puresoltechnologies.parsers.impl.ust.AbstractProduction;

public abstract class AbstractDeclaration extends AbstractProduction {

	private static final long serialVersionUID = 721736733272860703L;

	public AbstractDeclaration(String name, String originalSymbol) {
		super(name, originalSymbol);
	}

}
