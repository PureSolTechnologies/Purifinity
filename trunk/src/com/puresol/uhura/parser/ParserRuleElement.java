package com.puresol.uhura.parser;

public class ParserRuleElement {

	private final String text;
	private final ParserRuleElementType type;
	private final Quantity quantity;

	public ParserRuleElement(String text, ParserRuleElementType type) {
		super();
		this.text = text;
		this.type = type;
		this.quantity = Quantity.EXPECT;
	}

	public ParserRuleElement(String text, ParserRuleElementType type,
			Quantity quantity) {
		super();
		this.text = text;
		this.type = type;
		this.quantity = quantity;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the type
	 */
	public ParserRuleElementType getType() {
		return type;
	}

	/**
	 * @return the quantity
	 */
	public Quantity getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return text + quantity + "(" + type + ")";
	}

}
