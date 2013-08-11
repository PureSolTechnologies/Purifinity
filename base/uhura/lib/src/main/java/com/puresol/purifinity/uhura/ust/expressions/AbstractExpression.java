package com.puresol.purifinity.uhura.ust.expressions;

import com.puresol.purifinity.uhura.ust.AbstractUniversalSyntaxTree;

public abstract class AbstractExpression extends AbstractUniversalSyntaxTree
		implements Expression {

	private static final long serialVersionUID = 1554380039783582561L;

	public AbstractExpression(String originalSymbol) {
		super(originalSymbol);
	}

}
