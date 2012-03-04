package com.puresol.uhura.ust.literals;

import java.util.ArrayList;
import java.util.List;

import com.puresol.uhura.ust.UniversalSyntaxTree;
import com.puresol.uhura.ust.facilities.CompilerRelevantElement;

public abstract class Literal extends CompilerRelevantElement {

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
