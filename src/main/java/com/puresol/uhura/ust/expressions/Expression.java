package com.puresol.uhura.ust.expressions;

import com.puresol.uhura.ust.facilities.CompilerRelevantElement;

public abstract class Expression extends CompilerRelevantElement {

	private static final long serialVersionUID = 1554380039783582561L;

	public Expression(String originalSymbol) {
		super(originalSymbol);
	}

}
