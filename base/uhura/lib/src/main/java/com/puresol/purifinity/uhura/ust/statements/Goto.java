package com.puresol.purifinity.uhura.ust.statements;

public class Goto extends AbstractStatement {

	private static final long serialVersionUID = 8391205239089622685L;

	public Goto(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Goto";
	}

}
