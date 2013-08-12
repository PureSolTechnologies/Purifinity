package com.puresol.purifinity.uhura.ust.statements;

public class IfThenElse extends AbstractStatement {

	private static final long serialVersionUID = 8951429404436197403L;

	public IfThenElse(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "If-Then-Else Branch Statement";
	}

}
