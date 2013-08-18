package com.puresol.purifinity.uhura.ust;

public class AbstractUSTNode implements UniversalSyntaxTree {

	private static final long serialVersionUID = -3031571790486296091L;

	private UniversalSyntaxTree parent = null;
	private final String name;
	private final String originalName;
	private final String content;

	public AbstractUSTNode(String name, String originalName, String content) {
		super();
		this.name = name;
		this.originalName = originalName;
		this.content = content;
	}

	protected final UniversalSyntaxTree getParent() {
		return parent;
	}

	protected void setParent(UniversalSyntaxTree parent) {
		this.parent = parent;
	}

	public final String getName() {
		return name;
	}

	public final String getOriginalName() {
		return originalName;
	}

	public final String getContent() {
		return content;
	}

}
