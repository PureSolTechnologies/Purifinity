package com.puresol.purifinity.uhura.ust.statements;

import com.puresol.purifinity.uhura.ust.AbstractUSTNode;

public class GotoLabel extends AbstractUSTNode {

	private static final long serialVersionUID = 5969840612811092250L;

	public GotoLabel(String originalSymbol) {
		super("Goto Label", originalSymbol);
	}
}
