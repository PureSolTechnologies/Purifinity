package com.puresol.purifinity.uhura.ust.expressions;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

public abstract class TertiaryOperatorExpression extends Expression {

	private static final long serialVersionUID = -6854009238011625739L;

	private final Expression operand1;
	private final Expression operand2;
	private final Expression operand3;

	public TertiaryOperatorExpression(String originalSymbol, Expression operand1,
			Expression operand2, Expression operand3) {
		super(originalSymbol);
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operand3 = operand3;
	}

	public final Expression getOperand1() {
		return operand1;
	}

	public final Expression getOperand2() {
		return operand2;
	}

	public final Expression getOperand3() {
		return operand3;
	}

	@Override
	public final List<UniversalSyntaxTree> getChildren() {
		List<UniversalSyntaxTree> children = new ArrayList<UniversalSyntaxTree>();
		children.add(operand1);
		children.add(operand2);
		children.add(operand3);
		return children;
	}

	@Override
	public final boolean hasChildren() {
		return true;
	}

}
