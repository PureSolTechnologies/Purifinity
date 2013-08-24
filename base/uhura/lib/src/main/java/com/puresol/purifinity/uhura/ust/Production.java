package com.puresol.purifinity.uhura.ust;

import java.util.Arrays;
import java.util.Collection;

/**
 * This class is an abstract implementation of a universal syntax tree node for
 * later use in specific implementations. The normal parent and children
 * handling is implemented here.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Production extends AbstractUniversalSyntaxTreeNode {

	private static final long serialVersionUID = 1640932177605345931L;

	// XXX remove this constructor due to production always have children!
	public Production(String name, String originalSymbol) {
		super(name, originalSymbol, (String) null,
				(Collection<UniversalSyntaxTree>) null);
	}

	public Production(String name, String originalSymbol,
			Collection<UniversalSyntaxTree> children) {
		super(name, originalSymbol, null, children);
	}

	public Production(String name, String originalSymbol, String content,
			Collection<UniversalSyntaxTree> children) {
		super(name, originalSymbol, content, children);
	}

	public Production(String name, String originalSymbol,
			UniversalSyntaxTree... children) {
		super(name, originalSymbol, null, Arrays.asList(children));
	}

	public Production(String name, String originalSymbol, String content,
			UniversalSyntaxTree children) {
		this(name, originalSymbol, content, Arrays.asList(children));
	}

	@Override
	public String toString() {
		if (getContent() == null) {
			return getName() + "(" + getOriginalName() + ")";
		} else {
			return getName() + "(" + getOriginalName() + "): '" + getContent()
					+ "'";
		}
	}

}
