package com.puresol.purifinity.uhura.ust;

import java.io.Serializable;

import com.puresol.commons.trees.Tree;

/**
 * This class is an abstract implementation of a universal syntax tree node for
 * later use in specific implementations. The normal parent and children
 * handling is implemented here.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class UniversalSyntaxTree implements Tree<UniversalSyntaxTree>,
		Serializable {

	private static final long serialVersionUID = 1640932177605345931L;

	private final String originalSymbol;

	private UniversalSyntaxTree parent = null;

	public UniversalSyntaxTree(String originalSymbol) {
		super();
		this.originalSymbol = originalSymbol;
	}

	/**
	 * This methods sets the parent for the current class implementation. All
	 * class within a universal syntax tree have a parent. The top element
	 * CompilationUnit has null as parent showing the top position.
	 * 
	 * @param parent
	 */
	protected final void setParent(UniversalSyntaxTree parent) {
		if (parent != null) {
			throw new IllegalStateException("A parent was already defined!");
		}
		this.parent = parent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final UniversalSyntaxTree getParent() {
		return parent;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getOriginalLanguageSymbol() {
		return originalSymbol;
	}

	@Override
	public String toString() {
		return getName() + "(" + getOriginalLanguageSymbol() + ")";
	}
}
