package com.puresol.purifinity.uhura.ust.statements;

public class ForEachLoop extends AbstractHeadConditionLoop {

	private static final long serialVersionUID = 3421904906050261258L;

	public ForEachLoop(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "For Each Loop";
	}

}
