package com.puresol.uhura.grammar.production;

public class ProductionProductionElement extends AbstractProductionElement {

	public ProductionProductionElement(String name) {
		super(name, "", ProductionElementType.PRODUCTION, Quantity.EXPECT);
	}

	public ProductionProductionElement(String name, Quantity quantity) {
		super(name, "", ProductionElementType.PRODUCTION, quantity);
	}

}
