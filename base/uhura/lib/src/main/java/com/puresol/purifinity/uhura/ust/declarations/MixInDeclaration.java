package com.puresol.purifinity.uhura.ust.declarations;


public class MixInDeclaration extends AbstractTypeDeclaration {

	private static final long serialVersionUID = 3366654363107688929L;

	public MixInDeclaration(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Mix-In Declaration";
	}

}
