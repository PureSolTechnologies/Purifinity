package com.puresol.purifinity.uhura.ust.expressions;

public class Parenthesis extends AbstractUnaryOperator {

	private static final long serialVersionUID = -1577433621788208449L;

	public Parenthesis(String originalSymbol, Expression operand) {
		super(originalSymbol, operand);
	}

	@Override
	public String getName() {
		return "Parenthesis";
	}

}
