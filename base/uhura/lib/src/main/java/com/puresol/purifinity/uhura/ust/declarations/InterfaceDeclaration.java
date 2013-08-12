package com.puresol.purifinity.uhura.ust.declarations;



public class InterfaceDeclaration extends AbstractTypeDeclaration {

	private static final long serialVersionUID = 7399817615954138581L;

	public InterfaceDeclaration(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Interface Declaration";
	}

}
