package com.puresol.uhura.grammar.production;

public interface ProductionElement {

	/**
	 * @return the text
	 */
	public String getText();

	/**
	 * @return the type
	 */
	public ProductionElementType getType();

	/**
	 * @return the quantity
	 */
	public Quantity getQuantity();

	public int getTypeId();

	public String toString();

}