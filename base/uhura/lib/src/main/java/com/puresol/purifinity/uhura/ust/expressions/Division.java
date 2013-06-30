package com.puresol.purifinity.uhura.ust.expressions;


public class Division extends BinaryOperatorExpression {

    private static final long serialVersionUID = -1577433621788208449L;

    public Division(String originalSymbol, Expression divident,
	    Expression divisor) {
	super(originalSymbol, divident, divisor);
    }

    @Override
    public String getName() {
	return "Division";
    }

}
