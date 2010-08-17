package com.puresol.uhura.grammar.production;

public interface Construction {

	/**
	 * @return the text
	 */
	public String getText();

	/**
	 * @return the type
	 */
	public ConstructionType getType();

	/**
	 * @return the quantity
	 */
	public Quantity getQuantity();

	public String getName();

	public boolean isTerminal();
	public boolean isNonTerminal();
	
	public String toString();

	public String toShortString();

}