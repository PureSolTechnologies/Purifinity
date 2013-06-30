package com.puresol.purifinity.uhura.ust.comments;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;
import com.puresol.purifinity.uhura.ust.facilities.CompilerIrrelavantElement;

public abstract class Comment extends CompilerIrrelavantElement {

	private static final long serialVersionUID = -7080420554044222389L;

	public Comment(String originalSymbol) {
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
