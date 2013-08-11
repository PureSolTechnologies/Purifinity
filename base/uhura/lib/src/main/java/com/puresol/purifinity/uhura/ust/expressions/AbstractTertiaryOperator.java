package com.puresol.purifinity.uhura.ust.expressions;

public abstract class AbstractTertiaryOperator extends AbstractExpression {

	private static final long serialVersionUID = -6854009238011625739L;

	private final Expression operand1;
	private final Expression operand2;
	private final Expression operand3;

	public AbstractTertiaryOperator(String originalSymbol, Expression operand1,
			Expression operand2, Expression operand3) {
		super(originalSymbol);
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operand3 = operand3;
		addChild(operand1);
		addChild(operand2);
		addChild(operand3);
	}

	public final Expression getOperand1() {
		return operand1;
	}

	public final Expression getOperand2() {
		return operand2;
	}

	public final Expression getOperand3() {
		return operand3;
	}
}
