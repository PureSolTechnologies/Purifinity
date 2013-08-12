package com.puresol.purifinity.uhura.ust.statements;

public class SwitchCase extends AbstractStatement {

	private static final long serialVersionUID = 7634495549433768210L;

	public SwitchCase(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Switch Case Branch Statement";
	}

}
