package com.puresol.uhura.ast;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.puresol.uhura.lexer.Token;

public class SyntaxTree {

	private final String name;
	private final Token token;
	private SyntaxTree parent;
	private final List<SyntaxTree> children = new CopyOnWriteArrayList<SyntaxTree>();

	public SyntaxTree(Token token) {
		this.name = token.getName();
		this.token = token;
		this.parent = null;
	}

	public SyntaxTree(String name) {
		this.name = name;
		this.token = null;
		this.parent = null;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the text
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(SyntaxTree parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	public SyntaxTree getParent() {
		return parent;
	}

	/**
	 * @return the children
	 */
	public List<SyntaxTree> getChildren() {
		return children;
	}

	public void addChild(SyntaxTree child) {
		children.add(child);
		child.setParent(this);
	}

	public void addChildInFront(SyntaxTree child) {
		children.add(0, child);
		child.setParent(this);
	}

	@Override
	public String toString() {
		return name + " " + token;
	}
}
