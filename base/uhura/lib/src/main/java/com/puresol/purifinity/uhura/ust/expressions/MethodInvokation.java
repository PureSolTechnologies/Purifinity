package com.puresol.purifinity.uhura.ust.expressions;

public class MethodInvokation extends AbstractExpression {

	private static final long serialVersionUID = 3327912771024924207L;

	public MethodInvokation(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Method Invokation Expression";
	}

}
