package com.puresol.uhura.grammar.production;

public class TextProductionElement extends AbstractProductionElement {

	public TextProductionElement(String text) {
		super("", text, ProductionElementType.TEXT, Quantity.EXPECT);
	}

	public TextProductionElement(String text, Quantity quantity) {
		super("", text, ProductionElementType.TEXT, quantity);
	}

}
