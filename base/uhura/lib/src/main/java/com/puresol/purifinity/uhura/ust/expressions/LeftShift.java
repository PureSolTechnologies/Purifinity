package com.puresol.purifinity.uhura.ust.expressions;

public class LeftShift extends BinaryOperatorExpression {

    private static final long serialVersionUID = -1577433621788208449L;

    public LeftShift(String originalSymbol, Expression operand,
	    Expression shiftWidth) {
	super(originalSymbol, operand, shiftWidth);
    }

    @Override
    public String getName() {
	return "Left Shift";
    }

}
