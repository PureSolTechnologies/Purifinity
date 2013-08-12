package com.puresol.purifinity.uhura.ust.declarations;

public class FieldDeclaration extends AbstractTypeDeclaration {

	private static final long serialVersionUID = -2855596387207948744L;

	public FieldDeclaration(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Field Declaration";
	}

}
