package com.puresol.purifinity.uhura.ust.expressions;

import com.puresol.purifinity.uhura.ust.AbstractUSTNode;

public abstract class AbstractExpression extends AbstractUSTNode implements Expression {

	private static final long serialVersionUID = 1554380039783582561L;

	public AbstractExpression(String name, String originalSymbol) {
		super(name, originalSymbol);
	}

}
