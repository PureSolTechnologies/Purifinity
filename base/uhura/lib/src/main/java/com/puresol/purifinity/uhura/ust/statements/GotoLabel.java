package com.puresol.purifinity.uhura.ust.statements;

import com.puresol.purifinity.uhura.ust.AbstractUniversalSyntaxTree;

public class GotoLabel extends AbstractUniversalSyntaxTree {

	private static final long serialVersionUID = 5969840612811092250L;

	public GotoLabel(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Goto Label";
	}

}
