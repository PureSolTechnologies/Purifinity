package com.puresol.uhura.ast;

import java.util.ArrayList;
import java.util.List;

import com.puresol.uhura.lexer.Token;

public class SyntaxTree {

	private final int typeId;
	private final Token token;
	private SyntaxTree parent;
	private final List<SyntaxTree> children = new ArrayList<SyntaxTree>();

	public SyntaxTree(int typeId, Token token) {
		this.typeId = typeId;
		this.token = token;
		this.parent = null;
	}

	public SyntaxTree(int typeId, Token token, SyntaxTree parent) {
		this.typeId = typeId;
		this.token = token;
		this.parent = parent;
	}

	/**
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
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
	}

	@Override
	public String toString() {
		return typeId + " " + token;
	}
}
