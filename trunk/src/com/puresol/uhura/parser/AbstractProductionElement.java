package com.puresol.uhura.parser;

import com.puresol.uhura.grammar.production.ProductionElement;
import com.puresol.uhura.grammar.production.ProductionElementType;
import com.puresol.uhura.grammar.production.Quantity;

public abstract class AbstractProductionElement implements ProductionElement {

	private final int typeId;
	private final String text;
	private final ProductionElementType type;
	private final Quantity quantity;

	public AbstractProductionElement(int typeId, String text,
			ProductionElementType type) {
		super();
		this.typeId = typeId;
		this.text = text;
		this.type = type;
		this.quantity = Quantity.EXPECT;
	}

	public AbstractProductionElement(int typeId, String text,
			ProductionElementType type, Quantity quantity) {
		super();
		this.typeId = typeId;
		this.text = text;
		this.type = type;
		this.quantity = quantity;
	}

	/**
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.uhura.parser.ParserRuleElementInterface#getText()
	 */
	@Override
	public String getText() {
		return text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.uhura.parser.ParserRuleElementInterface#getType()
	 */
	@Override
	public ProductionElementType getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.uhura.parser.ParserRuleElementInterface#getQuantity()
	 */
	@Override
	public Quantity getQuantity() {
		return quantity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.uhura.parser.ParserRuleElementInterface#toString()
	 */
	@Override
	public String toString() {
		return text + quantity + "(" + type + ")";
	}

}
