package com.puresol.purifinity.uhura.ust.expressions;

public class ConditionalAssignment extends TertiaryOperatorExpression {

    private static final long serialVersionUID = 6754414167669177011L;

    public ConditionalAssignment(String originalSymbol, Expression target,
	    Expression trueAlternative, Expression falseAlternative) {
	super(originalSymbol, target, trueAlternative, falseAlternative);
    }

    @Override
    public String getName() {
	return "Conditional Assignment";
    }

}
