package com.puresol.uhura.ust.facilities;

import com.puresol.uhura.ust.UniversalSyntaxTree;

public abstract class CompilerRelevantElement extends
		UniversalSyntaxTree {

	private static final long serialVersionUID = -2821424892907670217L;

	public CompilerRelevantElement(String originalSymbol) {
		super(originalSymbol);
	}
}
