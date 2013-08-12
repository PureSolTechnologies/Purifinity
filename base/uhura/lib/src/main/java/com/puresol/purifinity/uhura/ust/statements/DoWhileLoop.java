package com.puresol.purifinity.uhura.ust.statements;

public class DoWhileLoop extends AbstractTailConditionLoop {

	private static final long serialVersionUID = -711834940002537908L;

	public DoWhileLoop(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Do While Loops";
	}

}
