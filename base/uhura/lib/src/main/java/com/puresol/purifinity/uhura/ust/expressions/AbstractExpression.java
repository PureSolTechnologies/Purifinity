package com.puresol.purifinity.uhura.ust.expressions;

import com.puresol.purifinity.uhura.ust.USTNode;

public abstract class AbstractExpression extends USTNode implements Expression {

	private static final long serialVersionUID = 1554380039783582561L;

	public AbstractExpression(String name, String originalSymbol) {
		super(name, originalSymbol);
	}

}
