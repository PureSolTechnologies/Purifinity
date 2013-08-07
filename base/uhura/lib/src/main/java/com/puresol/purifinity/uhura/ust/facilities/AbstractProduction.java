package com.puresol.purifinity.uhura.ust.facilities;

import com.puresol.purifinity.uhura.ust.Production;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

public abstract class AbstractProduction extends UniversalSyntaxTree implements
		Production {

	private static final long serialVersionUID = -2821424892907670217L;

	public AbstractProduction(String originalSymbol) {
		super(originalSymbol);
	}
}
