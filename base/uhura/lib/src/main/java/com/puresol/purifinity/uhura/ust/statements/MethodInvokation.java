package com.puresol.purifinity.uhura.ust.statements;

public class MethodInvokation extends AbstractExpressionStatement {

	private static final long serialVersionUID = -522072523974733944L;

	public MethodInvokation(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Method Invokation";
	}

}
