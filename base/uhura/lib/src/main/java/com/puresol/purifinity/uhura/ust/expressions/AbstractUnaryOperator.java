package com.puresol.purifinity.uhura.ust.expressions;

public abstract class AbstractUnaryOperator extends AbstractExpression {

	private static final long serialVersionUID = -6854009238011625739L;

	private final Expression operand;

	public AbstractUnaryOperator(String originalSymbol, Expression operand) {
		super(originalSymbol);
		this.operand = operand;
	}

	public final Expression getOperand() {
		return operand;
	}
}