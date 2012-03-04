package com.puresol.uhura.ust.expressions;


public class MultiplicationAssignment extends AssignmentExpression {

    private static final long serialVersionUID = -1577433621788208449L;

    public MultiplicationAssignment(String originalSymbol, Expression target,
	    Expression factor) {
	super(originalSymbol, target, factor);
    }

    @Override
    public String getName() {
	return "Multiplication Assignment";
    }

}
