package com.puresol.uhura.ust;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an abstract implementation of a universal syntax tree node for
 * later use in specific implementations. The normal parent and children
 * handling is implemented here.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractUniversalSyntaxTree implements
		UniversalSyntaxTree {

	private final List<UniversalSyntaxTree> children = new ArrayList<UniversalSyntaxTree>();
	private final AbstractUniversalSyntaxTree parent;
	private final String originalSymbol;

	public AbstractUniversalSyntaxTree(AbstractUniversalSyntaxTree parent,
			String originalSymbol) {
		super();
		this.parent = parent;
		this.originalSymbol = originalSymbol;
		parent.addChild(this);
	}

	private void addChild(UniversalSyntaxTree child) {
		children.add(child);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getOriginalLanguageSymbol() {
		return originalSymbol;
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
	@Override
	public final boolean hasChildren() {
		return children.size() > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<UniversalSyntaxTree> getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return getName() + "(" + getOriginalLanguageSymbol() + ")";
	}
}
