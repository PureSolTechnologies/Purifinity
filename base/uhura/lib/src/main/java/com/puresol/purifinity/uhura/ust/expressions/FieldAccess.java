package com.puresol.purifinity.uhura.ust.expressions;

public class FieldAccess extends AbstractExpression {

	private static final long serialVersionUID = -5864192004843151154L;

	public FieldAccess(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Field Access Expression";
	}

}