package com.puresol.purifinity.uhura.ust.statements;

public class WhileLoop extends AbstractHeadConditionLoop {

	private static final long serialVersionUID = -3215380137916478208L;

	public WhileLoop(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "While Loop";
	}

}
