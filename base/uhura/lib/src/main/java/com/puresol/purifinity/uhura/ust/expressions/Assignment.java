package com.puresol.purifinity.uhura.ust.expressions;


public class Assignment extends AssignmentExpression {

    private static final long serialVersionUID = -1577433621788208449L;

    public Assignment(String originalSymbol, Expression target, Expression value) {
	super(originalSymbol, target, value);
    }

    @Override
    public String getName() {
	return "Assignment";
    }

}
