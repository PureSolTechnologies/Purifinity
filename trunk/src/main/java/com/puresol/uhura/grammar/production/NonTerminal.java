package com.puresol.uhura.grammar.production;

import com.puresol.uhura.grammar.Quantity;

public final class NonTerminal extends AbstractConstruction {

	private static final long serialVersionUID = 8346248512269952988L;

	public NonTerminal(String name) {
		this(name, Quantity.EXPECT);
	}

	public NonTerminal(String name, Quantity quantity) {
		super(name, quantity, false);
	}

}
