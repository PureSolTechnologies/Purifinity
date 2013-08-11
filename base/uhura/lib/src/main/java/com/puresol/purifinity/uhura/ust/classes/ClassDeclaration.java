package com.puresol.purifinity.uhura.ust.classes;

import com.puresol.purifinity.uhura.ust.AbstractUniversalSyntaxTree;

public class ClassDeclaration extends AbstractUniversalSyntaxTree {

	private static final long serialVersionUID = 4241659851575261998L;

	public ClassDeclaration(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return "Class Declaration";
	}

}
