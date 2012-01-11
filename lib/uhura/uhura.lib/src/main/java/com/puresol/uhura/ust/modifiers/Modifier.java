package com.puresol.uhura.ust.modifiers;

import java.util.ArrayList;
import java.util.List;

import com.puresol.uhura.ust.UniversalSyntaxTree;
import com.puresol.uhura.ust.facilities.CompilerRelevantElement;

public abstract class Modifier extends CompilerRelevantElement {

	private static final long serialVersionUID = 6970210094744093023L;

	public Modifier(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public boolean hasChildren() {
		return false;
	}

	@Override
	public List<UniversalSyntaxTree> getChildren() {
		return new ArrayList<UniversalSyntaxTree>();
	}
}
