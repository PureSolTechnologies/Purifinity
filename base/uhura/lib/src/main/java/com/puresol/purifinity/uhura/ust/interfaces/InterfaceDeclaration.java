package com.puresol.purifinity.uhura.ust.interfaces;

import com.puresol.purifinity.uhura.ust.AbstractUniversalSyntaxTree;

public class InterfaceDeclaration extends AbstractUniversalSyntaxTree {

	private static final long serialVersionUID = 7399817615954138581L;

	public InterfaceDeclaration(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Interface Declaration";
	}

}
