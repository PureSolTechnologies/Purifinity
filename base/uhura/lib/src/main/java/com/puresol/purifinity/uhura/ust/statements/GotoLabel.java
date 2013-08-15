package com.puresol.purifinity.uhura.ust.statements;

import com.puresol.purifinity.uhura.ust.USTNode;

public class GotoLabel extends USTNode {

	private static final long serialVersionUID = 5969840612811092250L;

	public GotoLabel(String originalSymbol) {
		super("Goto Label", originalSymbol);
	}
}
