package com.puresol.purifinity.uhura.ust;

import java.util.ArrayList;
import java.util.List;

public class AbstractUniversalSyntaxTreeNode implements UniversalSyntaxTree {

	private static final long serialVersionUID = -3031571790486296091L;

	private UniversalSyntaxTree parent = null;
	private final String name;
	private final String originalName;
	private final UniversalSyntaxTreeMetaData metaData;
	private final String content;

	private final List<UniversalSyntaxTree> children = new ArrayList<>();

	public AbstractUniversalSyntaxTreeNode(String name, String originalName,
			String content, UniversalSyntaxTreeMetaData metaData,
			List<UniversalSyntaxTree> children) {
		super();
		this.name = name;
		this.originalName = originalName;
		this.metaData = metaData;
		this.content = content;
		children.addAll(children);
	}

	public AbstractUniversalSyntaxTreeNode(String name, String originalName,
			String content, UniversalSyntaxTreeMetaData metaData) {
		super();
		this.name = name;
		this.originalName = originalName;
		this.metaData = metaData;
		this.content = content;
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

	@Override
	public final String getOriginalName() {
		return originalName;
	}

	@Override
	public final UniversalSyntaxTreeMetaData getMetaData() {
		return metaData;
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
