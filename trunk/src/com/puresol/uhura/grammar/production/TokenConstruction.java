package com.puresol.uhura.grammar.production;

public class TokenConstruction extends AbstractConstruction {

	public TokenConstruction(String name) {
		super(name, "", ConstructionType.TOKEN, Quantity.EXPECT);
	}

	public TokenConstruction(String name, Quantity quantity) {
		super(name, "", ConstructionType.TOKEN, quantity);
	}

}
