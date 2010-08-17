package com.puresol.uhura.grammar.production;

public class TextConstruction extends AbstractConstruction {

	public TextConstruction(String text) {
		super("", text, ConstructionType.TEXT, Quantity.EXPECT);
	}

	public TextConstruction(String text, Quantity quantity) {
		super("", text, ConstructionType.TEXT, quantity);
	}

}
