package com.puresol.uhura.ust.expressions;


public class Exponent extends BinaryOperatorExpression {

    private static final long serialVersionUID = -1577433621788208449L;

    public Exponent(String originalSymbol, Expression mantissa,
	    Expression exponent) {
	super(originalSymbol, mantissa, exponent);
    }

    @Override
    public String getName() {
	return "Exponent";
    }

}