package com.puresol.purifinity.uhura.ust.expressions;

public class Modulo extends AbstractBinaryOperator {

	private static final long serialVersionUID = -1577433621788208449L;

	public Modulo(String originalSymbol, Expression divident, Expression divisor) {
		super(originalSymbol, divident, divisor);
	}

	@Override
	public String getName() {
		return "Modulo";
	}

}
