package com.puresol.uhura.parser;

/**
 * This
 * 
 * @author rludwig
 * 
 */
public enum Quantity {

	EXPECT(null, 1, 1), ACCEPT('?', 0, 1), EXPECT_MANY('+', 1, null), ACCEPT_MANY(
			'*', 0, null);

	private final Character symbol;
	private final Integer min;
	private final Integer max;

	private Quantity(Character symbol, Integer min, Integer max) {
		this.symbol = symbol;
		this.min = min;
		this.max = max;
	}

	/**
	 * @return the symbol
	 */
	public Character getSymbol() {
		return symbol;
	}

	/**
	 * @return the min
	 */
	public Integer getMin() {
		return min;
	}

	/**
	 * @return the max
	 */
	public Integer getMax() {
		return max;
	}

	@Override
	public String toString() {
		if (symbol == null) {
			return "";
		}
		return String.valueOf(symbol);
	}

	public static Quantity fromSymbol(Character symbol) {
		for (Quantity quantity : Quantity.class.getEnumConstants()) {
			if (quantity.getSymbol() == null) {
				if (symbol == null) {
					return quantity;
				}
			} else {
				if (quantity.getSymbol().equals(symbol)) {
					return quantity;
				}
			}
		}
		return null;
	}
}
