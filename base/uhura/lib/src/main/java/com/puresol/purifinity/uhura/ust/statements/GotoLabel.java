package com.puresol.purifinity.uhura.ust.statements;

import com.puresol.purifinity.uhura.ust.Production;

public class GotoLabel extends Production {

	private static final long serialVersionUID = 5969840612811092250L;

	public GotoLabel(String originalSymbol) {
		super("Goto Label", originalSymbol);
	}
}
