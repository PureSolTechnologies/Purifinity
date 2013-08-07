package com.puresol.purifinity.uhura.ust.comments;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;
import com.puresol.purifinity.uhura.ust.facilities.AbstractToken;

public abstract class AbstractComment extends AbstractToken {

	private static final long serialVersionUID = -7080420554044222389L;

	public AbstractComment(String originalSymbol) {
		super(originalSymbol);

	}

	@Override
	public final boolean hasChildren() {
		return false;
	}

	@Override
	public final List<UniversalSyntaxTree> getChildren() {
		return new ArrayList<UniversalSyntaxTree>();
	}

}
