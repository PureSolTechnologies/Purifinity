package com.puresol.purifinity.uhura.ust.declarations;



public class ClassDeclaration extends AbstractTypeDeclaration {

	private static final long serialVersionUID = 4241659851575261998L;

	public ClassDeclaration(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Class Declaration";
	}

}
