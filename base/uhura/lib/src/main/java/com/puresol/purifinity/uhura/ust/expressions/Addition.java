package com.puresol.purifinity.uhura.ust.expressions;

public class Addition extends AbstractBinaryOperator {

	private static final long serialVersionUID = -1577433621788208449L;

	public Addition(String originalSymbol, Expression summand1,
			Expression summand2) {
		super(originalSymbol, summand1, summand2);
	}

	@Override
	public String getName() {
		return "Addition";
	}

}
