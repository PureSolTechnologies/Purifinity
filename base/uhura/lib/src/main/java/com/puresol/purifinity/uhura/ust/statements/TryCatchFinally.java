package com.puresol.purifinity.uhura.ust.statements;

public class TryCatchFinally extends AbstractStatement {

	private static final long serialVersionUID = 1957426337882742496L;

	public TryCatchFinally(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Try-Catch-Finally Statement";
	}

}
