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
public class USTNode implements UniversalSyntaxTree {

	private static final long serialVersionUID = 1640932177605345931L;

	private UniversalSyntaxTree parent;
	private final String name;
	private final String originalSymbol;
	private final String content;
	private final List<UniversalSyntaxTree> children = new ArrayList<>();

	public USTNode(String name, String originalSymbol) {
		super();
		this.name = name;
		this.originalSymbol = originalSymbol;
		this.content = null;
	}

	public USTNode(String name, String originalSymbol, String content) {
		super();
		this.name = name;
		this.originalSymbol = originalSymbol;
		this.content = content;
	}

	protected final void addChildren(Collection<USTNode> children) {
		this.children.addAll(children);
	}

	protected final void addChild(UniversalSyntaxTree child) {
		children.add(child);
	}

	public final List<UniversalSyntaxTree> getChildren() {
		return children;
	}

	public final UniversalSyntaxTree getParent() {
		return parent;
	}

	protected final void setParent(USTNode parent) {
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
