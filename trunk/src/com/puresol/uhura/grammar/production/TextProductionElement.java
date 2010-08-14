package com.puresol.uhura.grammar.production;

import com.puresol.uhura.parser.AbstractProductionElement;

public class TextProductionElement extends AbstractProductionElement {

	public TextProductionElement(String text) {
		super(-1, text, ProductionElementType.TEXT, Quantity.EXPECT);
	}

	public TextProductionElement(String text, Quantity quantity) {
		super(-1, text, ProductionElementType.TEXT, quantity);
	}

}
