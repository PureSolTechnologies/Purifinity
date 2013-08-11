package com.puresol.purifinity.uhura.ust.modifiers;

public class PackagePrivateModifier extends AbstractModifier {

	private static final long serialVersionUID = 729411154443449805L;

	public PackagePrivateModifier(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Package Private Modifier";
	}

}
