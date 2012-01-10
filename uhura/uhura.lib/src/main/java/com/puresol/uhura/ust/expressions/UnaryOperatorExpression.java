package com.puresol.uhura.ust.expressions;

import java.util.ArrayList;
import java.util.List;

import com.puresol.uhura.ust.UniversalSyntaxTree;

public abstract class UnaryOperatorExpression extends Expression {

	private static final long serialVersionUID = -6854009238011625739L;

	private final Expression operand;

	public UnaryOperatorExpression(String originalSymbol, Expression operand) {
		super(originalSymbol);
		this.operand = operand;
	}

	public final Expression getOperand() {
		return operand;
	}

	@Override
	public final List<UniversalSyntaxTree> getChildren() {
		List<UniversalSyntaxTree> children = new ArrayList<UniversalSyntaxTree>();
		children.add(operand);
		return children;
	}

	@Override
	public final boolean hasChildren() {
		return true;
	}

}
