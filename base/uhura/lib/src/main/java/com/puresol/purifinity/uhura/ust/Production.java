package com.puresol.purifinity.uhura.ust;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is an abstract implementation of a universal syntax tree node for
 * later use in specific implementations. The normal parent and children
 * handling is implemented here.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Production extends AbstractUSTNode {

	private static final long serialVersionUID = 1640932177605345931L;

	private final List<UniversalSyntaxTree> children = new ArrayList<>();

	public Production(String name, String originalSymbol, String content) {
		super(name, originalSymbol, content);
	}

	public Production(String name, String originalSymbol) {
		this(name, originalSymbol, (String) null);
	}

	public Production(String name, String originalSymbol,
			List<UniversalSyntaxTree> children) {
		this(name, originalSymbol);
		children.addAll(children);
	}

	public Production(String name, String originalSymbol, String content,
			List<UniversalSyntaxTree> children) {
		this(name, originalSymbol, content);
		children.addAll(children);
	}

	public Production(String name, String originalSymbol,
			UniversalSyntaxTree... children) {
		this(name, originalSymbol);
		this.children.addAll(Arrays.asList(children));
	}

	public Production(String name, String originalSymbol, String content,
			UniversalSyntaxTree children) {
		this(name, originalSymbol, content);
		this.children.addAll(Arrays.asList(children));
	}

	public final List<UniversalSyntaxTree> getChildren() {
		return children;
	}

	public final boolean hasChildren() {
		return children.size() > 0;
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
