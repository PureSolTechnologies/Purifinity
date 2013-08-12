package com.puresol.purifinity.uhura.ust.statements;

public class CoutingFor extends AbstractHeadConditionLoop {

	private static final long serialVersionUID = 1040379931239394965L;

	public CoutingFor(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Counting For Loop";
	}

}
