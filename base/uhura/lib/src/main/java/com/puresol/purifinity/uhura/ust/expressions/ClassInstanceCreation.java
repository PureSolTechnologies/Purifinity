package com.puresol.purifinity.uhura.ust.expressions;

public class ClassInstanceCreation extends AbstractExpression {

	private static final long serialVersionUID = -4376983061900212317L;

	public ClassInstanceCreation(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Class Instance Creation Expression";
	}

}
