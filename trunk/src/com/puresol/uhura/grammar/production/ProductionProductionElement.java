package com.puresol.uhura.grammar.production;

import com.puresol.uhura.parser.AbstractProductionElement;

public class ProductionProductionElement extends AbstractProductionElement {

	public ProductionProductionElement(int typeId) {
		super(typeId, "", ProductionElementType.PRODUCTION, Quantity.EXPECT);
	}

	public ProductionProductionElement(int typeId, Quantity quantity) {
		super(typeId, "", ProductionElementType.PRODUCTION, quantity);
	}

}
