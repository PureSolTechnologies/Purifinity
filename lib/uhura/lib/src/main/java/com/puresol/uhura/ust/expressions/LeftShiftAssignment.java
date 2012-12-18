package com.puresol.uhura.ust.expressions;


public class LeftShiftAssignment extends AssignmentExpression {

    private static final long serialVersionUID = -1577433621788208449L;

    public LeftShiftAssignment(String originalSymbol, Expression target,
	    Expression shiftWidth) {
	super(originalSymbol, target, shiftWidth);
    }

    @Override
    public String getName() {
	return "Left Shift Assignment";
    }

}
