package com.puresol.purifinity.uhura.ust.statements;

public class ForLoop extends AbstractHeadConditionLoop {

	private static final long serialVersionUID = 911764200002860417L;

	public ForLoop(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "For Loop";
	}

}