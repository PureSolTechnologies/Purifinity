package com.puresol.purifinity.uhura.ust.statements;

public class AssertStatement extends AbstractStatement {

	private static final long serialVersionUID = 1290336609348647013L;

	public AssertStatement(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Assert Statement";
	}

}
