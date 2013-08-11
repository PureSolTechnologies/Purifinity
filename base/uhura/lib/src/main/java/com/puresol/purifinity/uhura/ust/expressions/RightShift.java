package com.puresol.purifinity.uhura.ust.expressions;

public class RightShift extends AbstractBinaryOperator {

	private static final long serialVersionUID = -1577433621788208449L;

	public RightShift(String originalSymbol, Expression operand,
			Expression shiftWidth) {
		super(originalSymbol, operand, shiftWidth);
	}

	@Override
	public String getName() {
		return "Right Shift";
	}

}
