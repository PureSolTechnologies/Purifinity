package com.puresol.purifinity.uhura.ust.modifiers;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;
import com.puresol.purifinity.uhura.ust.facilities.AbstractProduction;

public abstract class AbstractModifier extends AbstractProduction {

	private static final long serialVersionUID = 6970210094744093023L;

	public AbstractModifier(String originalSymbol) {
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
