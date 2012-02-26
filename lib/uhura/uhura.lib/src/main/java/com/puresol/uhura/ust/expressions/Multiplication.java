package com.puresol.uhura.ust.expressions;


public class Multiplication extends BinaryOperatorExpression {

    private static final long serialVersionUID = -1577433621788208449L;

    public Multiplication(String originalSymbol, Expression factor1,
	    Expression factor2) {
	super(originalSymbol, factor1, factor2);
    }

    @Override
    public String getName() {
	return "Multiplication";
    }

}
