package com.puresol.purifinity.uhura.ust;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AbstractUniversalSyntaxTreeNode implements UniversalSyntaxTree {

	private static final long serialVersionUID = -3031571790486296091L;

	private UniversalSyntaxTree parent = null;
	private final String name;
	private final String originalName;
	private final String content;

	private final List<UniversalSyntaxTree> children = new ArrayList<>();

	public AbstractUniversalSyntaxTreeNode(String name, String originalName,
			String content, Collection<UniversalSyntaxTree> children) {
		super();
		this.name = name;
		this.originalName = originalName;
		this.content = content;
		children.addAll(children);
	}

	@Override
	public final UniversalSyntaxTree getParent() {
		return parent;
	}

	protected void setParent(UniversalSyntaxTree parent) {
		this.parent = parent;
	}

	@Override
	public final String getName() {
		return name;
	}

	public final String getOriginalName() {
		return originalName;
	}

	public final String getContent() {
		return content;
	}

	@Override
	public final List<UniversalSyntaxTree> getChildren() {
		return children;
	}

	@Override
	public final boolean hasChildren() {
		return children.size() > 0;
	}

}
