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

	@Override
	public String toString() {
		return name + " " + token;
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

	/**
	 * @return the children
	 * @throws TreeException
	 */
	public SyntaxTree getChild(String name) throws TreeException {
		SyntaxTree result = null;
		for (SyntaxTree child : children) {
			if (child.getName().equals(name)) {
				if (result != null) {
					throw new TreeException("Child '" + name
							+ "'is multiply defined!");
				}
				result = child;
			}
		}
		return result;
	}

	public List<SyntaxTree> getSubTrees(String name) {
		List<SyntaxTree> subTrees = new CopyOnWriteArrayList<SyntaxTree>();
		getSubTrees(this, subTrees, name);
		return subTrees;
	}

	private void getSubTrees(SyntaxTree branch, List<SyntaxTree> subTrees,
			String name) {
		if (branch.getName().equals(name)) {
			subTrees.add(branch);
		}
		for (SyntaxTree subBranch : branch.getChildren()) {
			getSubTrees(subBranch, subTrees, name);
		}
	}

	private class TextWalkerClient implements TreeWalkerClient {

		private final StringBuffer text = new StringBuffer();

		@Override
		public WalkingAction trigger(SyntaxTree syntaxTree) {
			Token token = syntaxTree.getToken();
			if (token != null) {
				text.append(token.getText());
			}
			return null;
		}

		public String getText() {
			return text.toString();
		}

	}

	public String getText() {
		TreeWalker treeWalker = new TreeWalker(this);
		TextWalkerClient textClient = new TextWalkerClient();
		treeWalker.walk(textClient);
		return textClient.getText();
	}
}
