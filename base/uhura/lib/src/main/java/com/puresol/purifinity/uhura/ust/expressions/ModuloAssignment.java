package com.puresol.purifinity.uhura.ust.expressions;

public class ModuloAssignment extends AssignmentExpression {

	private static final long serialVersionUID = -1577433621788208449L;

	public ModuloAssignment(String originalSymbol, Expression target,
			Expression divisor) {
		super(originalSymbol, target, divisor);
	}

	@Override
	public String getName() {
		return "Modulo Assignment";
	}

}
