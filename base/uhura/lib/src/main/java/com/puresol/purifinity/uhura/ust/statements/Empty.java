package com.puresol.purifinity.uhura.ust.statements;

public class Empty extends AbstractStatement {

	private static final long serialVersionUID = -1801242222989392854L;

	public Empty(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Empty Statement";
	}

}