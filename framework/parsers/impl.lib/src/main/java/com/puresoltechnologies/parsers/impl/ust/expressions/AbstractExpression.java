package com.puresoltechnologies.parsers.impl.ust.expressions;

import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.impl.ust.AbstractProduction;

public abstract class AbstractExpression extends AbstractProduction implements Expression {

	private static final long serialVersionUID = 1554380039783582561L;

	public AbstractExpression(String name, String originalSymbol) {
		super(name, originalSymbol);
	}

	public AbstractExpression(String name, String originalSymbol,
			UniversalSyntaxTree... children) {
		super(name, originalSymbol, children);
	}

}
