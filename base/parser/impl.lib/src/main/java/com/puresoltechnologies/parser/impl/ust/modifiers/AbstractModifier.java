package com.puresoltechnologies.parser.impl.ust.modifiers;

import com.puresoltechnologies.parser.impl.ust.AbstractProduction;

public abstract class AbstractModifier extends AbstractProduction {

	private static final long serialVersionUID = 6970210094744093023L;

	public AbstractModifier(String name, String originalSymbol) {
		super(name, originalSymbol);
	}
}
