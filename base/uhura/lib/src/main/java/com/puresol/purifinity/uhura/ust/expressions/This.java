package com.puresol.purifinity.uhura.ust.expressions;

public class This extends AbstractExpression {

	private static final long serialVersionUID = -1725237580854743190L;

	public This(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "This Expression";
	}

}
