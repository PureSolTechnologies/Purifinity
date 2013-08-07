package com.puresol.purifinity.uhura.ust.types;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;
import com.puresol.purifinity.uhura.ust.facilities.AbstractProduction;

public abstract class Type extends AbstractProduction {

	private static final long serialVersionUID = 2846791515023904393L;

	private final List<UniversalSyntaxTree> children = new ArrayList<UniversalSyntaxTree>();

	public Type(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public final boolean hasChildren() {
		return !children.isEmpty();
	}

	@Override
	public final List<UniversalSyntaxTree> getChildren() {
		return children;
	}

}
