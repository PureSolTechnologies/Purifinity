package com.puresol.uhura.ust.expressions;


public class Substraction extends BinaryOperatorExpression {

    private static final long serialVersionUID = -1577433621788208449L;

    public Substraction(String originalSymbol, Expression minutent,
	    Expression subtrahent) {
	super(originalSymbol, minutent, subtrahent);
    }

    @Override
    public String getName() {
	return "Substraction";
    }

}