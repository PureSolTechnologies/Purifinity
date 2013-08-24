package com.puresol.purifinity.uhura.ust.token;

import com.puresol.purifinity.uhura.ust.AbstractUniversalSyntaxTreeNode;

public class AbstractToken extends AbstractUniversalSyntaxTreeNode {

	private static final long serialVersionUID = -1620557770406497800L;

	public AbstractToken(String name, String originalName, String content) {
		super(name, originalName, content, null);
	}

}
