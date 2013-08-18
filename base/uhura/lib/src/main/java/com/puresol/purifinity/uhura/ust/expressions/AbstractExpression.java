package com.puresol.purifinity.uhura.ust.expressions;

import com.puresol.purifinity.uhura.ust.Production;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

public abstract class AbstractExpression extends Production implements Expression {

	private static final long serialVersionUID = 1554380039783582561L;

	public AbstractExpression(String name, String originalSymbol) {
		super(name, originalSymbol);
	}

	public AbstractExpression(String name, String originalSymbol,
			UniversalSyntaxTree... children) {
		super(name, originalSymbol, children);
	}

}
