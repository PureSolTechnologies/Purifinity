package com.puresol.uhura.ust.expressions;


public class SubstractionAssignment extends AssignmentExpression {

    private static final long serialVersionUID = -1577433621788208449L;

    public SubstractionAssignment(String originalSymbol, Expression target,
	    Expression subtrahent) {
	super(originalSymbol, target, subtrahent);
    }

    @Override
    public String getName() {
	return "Substraction Assignment";
    }

}
