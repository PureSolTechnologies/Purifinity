package com.puresol.purifinity.uhura.ust.literals;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;
import com.puresol.purifinity.uhura.ust.facilities.AbstractProduction;

public abstract class Literal extends AbstractProduction {

	private static final long serialVersionUID = -1731135665103554027L;

	public Literal(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public List<UniversalSyntaxTree> getChildren() {
		return new ArrayList<UniversalSyntaxTree>();
	}

	@Override
	public boolean hasChildren() {
		return false;
	}

}
