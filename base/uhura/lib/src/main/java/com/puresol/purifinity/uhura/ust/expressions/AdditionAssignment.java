package com.puresol.purifinity.uhura.ust.expressions;


public class AdditionAssignment extends AssignmentExpression {

    private static final long serialVersionUID = -1577433621788208449L;

    public AdditionAssignment(String originalSymbol, Expression target,
	    Expression summand) {
	super(originalSymbol, target, summand);
    }

    @Override
    public String getName() {
	return "Addition Assignment";
    }

}