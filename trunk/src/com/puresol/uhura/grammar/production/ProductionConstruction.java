package com.puresol.uhura.grammar.production;

public class ProductionConstruction extends AbstractConstruction {

	public ProductionConstruction(String name) {
		super(name, "", ConstructionType.PRODUCTION, Quantity.EXPECT);
	}

	public ProductionConstruction(String name, Quantity quantity) {
		super(name, "", ConstructionType.PRODUCTION, quantity);
	}

}
