package com.puresol.purifinity.uhura.ust.literals;

public class LogicalLiteral extends NumericalLiteral {

	private static final long serialVersionUID = -5622529938750219170L;

	private final boolean value;

	public LogicalLiteral(String originalSymbol, boolean value) {
		super("Logical Literal", originalSymbol);
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}
}
