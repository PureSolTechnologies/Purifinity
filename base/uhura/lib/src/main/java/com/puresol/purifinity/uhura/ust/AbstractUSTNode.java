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
public class AbstractUSTNode implements USTNode {

	private static final long serialVersionUID = 1640932177605345931L;

	private USTNode parent;
	private final String name;
	private final String originalSymbol;
	private final String content;
	private final List<USTNode> children = new ArrayList<>();

	public AbstractUSTNode(String name, String originalSymbol) {
		super();
		this.name = name;
		this.originalSymbol = originalSymbol;
		this.content = null;
	}

	public AbstractUSTNode(String name, String originalSymbol, String content) {
		super();
		this.name = name;
		this.originalSymbol = originalSymbol;
		this.content = content;
	}

	protected final void addChildren(Collection<AbstractUSTNode> children) {
		this.children.addAll(children);
	}

	protected final void addChild(USTNode child) {
		children.add(child);
	}

	public final List<USTNode> getChildren() {
		return children;
	}

	public final USTNode getParent() {
		return parent;
	}

	protected final void setParent(AbstractUSTNode parent) {
		if (this.parent != null) {
			throw new UniversalSyntaxTreeCreatorException(
					"Parent was already set for '" + toString() + "'!");
		}
		this.parent = parent;
	}

	public final boolean hasChildren() {
		return children.size() > 0;
	}

	public final String getName() {
		return name;
	}

	public String getOriginalName() {
		return originalSymbol;
	}

	public String getContent() {
		return content;
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
