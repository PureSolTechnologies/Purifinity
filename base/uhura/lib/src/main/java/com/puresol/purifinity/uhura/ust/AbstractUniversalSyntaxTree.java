package com.puresol.purifinity.uhura.ust;

import java.util.ArrayList;
import java.util.Collection;
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

	private static final long serialVersionUID = 1640932177605345931L;

	private UniversalSyntaxTree parent;
	private final String originalSymbol;
	private final List<UniversalSyntaxTree> children = new ArrayList<>();

	public AbstractUniversalSyntaxTree(String originalSymbol) {
		super();
		this.originalSymbol = originalSymbol;
	}

	protected final void addChildren(Collection<UniversalSyntaxTree> children) {
		this.children.addAll(children);
	}

	protected final void addChild(UniversalSyntaxTree child) {
		children.add(child);
	}

	@Override
	public final List<UniversalSyntaxTree> getChildren() {
		return children;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final UniversalSyntaxTree getParent() {
		return parent;
	}

	protected final void setParent(UniversalSyntaxTree parent) {
		if (this.parent != null) {
			throw new UniversalSyntaxTreeCreatorException(
					"Parent was already set for '" + toString() + "'!");
		}
		this.parent = parent;
	}

	@Override
	public final boolean hasChildren() {
		return children.size() > 0;
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
