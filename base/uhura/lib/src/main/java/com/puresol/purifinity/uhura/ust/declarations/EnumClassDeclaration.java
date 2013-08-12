package com.puresol.purifinity.uhura.ust.declarations;


public class EnumClassDeclaration extends ClassDeclaration {

	private static final long serialVersionUID = 3501719223735273021L;

	public EnumClassDeclaration(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Enum Class Declaration";
	}

}
