package com.puresol.uhura.grammar.production;

public class TokenProductionElement extends AbstractProductionElement {

	public TokenProductionElement(String name) {
		super(name, "", ProductionElementType.TOKEN, Quantity.EXPECT);
	}

	public TokenProductionElement(String name, Quantity quantity) {
		super(name, "", ProductionElementType.TOKEN, quantity);
	}

}
