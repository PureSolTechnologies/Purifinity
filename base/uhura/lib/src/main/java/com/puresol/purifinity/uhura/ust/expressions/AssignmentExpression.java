package com.puresol.purifinity.uhura.ust.expressions;

public abstract class AssignmentExpression extends AbstractBinaryOperator {

	private static final long serialVersionUID = 4249485571689334300L;

	public AssignmentExpression(String originalSymbol, Expression operand1,
			Expression operand2) {
		super(originalSymbol, operand1, operand2);
	}

}
