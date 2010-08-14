package com.puresol.uhura.grammar.production;

import com.puresol.uhura.parser.AbstractProductionElement;

public class TokenProductionElement extends AbstractProductionElement {

	public TokenProductionElement(int typeId) {
		super(typeId, "", ProductionElementType.TOKEN, Quantity.EXPECT);
	}

	public TokenProductionElement(int typeId, Quantity quantity) {
		super(typeId, "", ProductionElementType.TOKEN, quantity);
	}

}
