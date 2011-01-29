package com.puresol.uhura.parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresol.trees.TreeException;
import com.puresol.trees.Tree;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.lexer.Token;

/**
 * This tree is an (A)bstract (S)yntax (T)ree. This class implements the Tree<T>
 * interface is therefore suitable for the TreeWalker class for easy tree
 * processing.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ParserTree implements Tree<ParserTree>, Serializable {

	private static final long serialVersionUID = -651453440127029204L;

	private final String name;
	private final Token token;
	private ParserTree parent = null;
	private final List<ParserTree> children = new ArrayList<ParserTree>();
	private final boolean node;
	private final boolean stackingAllowed;
	private ParserTreeMetaData metaData = null;

	public ParserTree(Token token) {
		super();
		this.name = token.getName();
		this.token = token;
		this.node = true;
		this.stackingAllowed = true;
	}

	public ParserTree(Production production) {
		this.name = production.getAlternativeName();
		this.token = null;
		this.node = production.isNode();
		this.stackingAllowed = production.isStackingAllowed();
	}

	public ParserTree(String name) {
		this.name = name;
		this.token = null;
		this.node = true;
		this.stackingAllowed = true;
	}

	public ParserTree(String name, Token token, boolean node,
			boolean stackingAllowed) {
		this.name = name;
		this.token = token;
		this.node = node;
		this.stackingAllowed = stackingAllowed;
	}

	/**
	 * @return the name
	 */
	@Override
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
		return name + " " + token + " (" + metaData + ")";
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(ParserTree parent) throws TreeException {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	@Override
	public ParserTree getParent() {
		return parent;
	}

	@Override
	public boolean hasChildren() {
		return !children.isEmpty();
	}

	/**
	 * @return the children
	 */
	@Override
	public List<ParserTree> getChildren() {
		return children;
	}

	public void addChild(ParserTree child) throws TreeException {
		children.add(child);
		child.setParent(this);
	}

	public void addChildren(List<ParserTree> children) throws TreeException {
		this.children.addAll(children);
		for (ParserTree child : children) {
			child.setParent(this);
		}
	}

	public void addChildInFront(ParserTree child) throws TreeException {
		children.add(0, child);
		child.setParent(this);
	}

	public void addChildrenInFront(List<ParserTree> children)
			throws TreeException {
		this.children.addAll(0, children);
		for (ParserTree child : children) {
			child.setParent(this);
		}
	}

	/**
	 * @return the children
	 * @throws TreeException
	 */
	public ParserTree getChild(String name) throws TreeException {
		ParserTree result = null;
		for (ParserTree child : children) {
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

	/**
	 * @return the children
	 * @throws TreeException
	 */
	public List<ParserTree> getChildren(String name) throws TreeException {
		List<ParserTree> result = new ArrayList<ParserTree>();
		for (ParserTree child : children) {
			if (child.getName().equals(name)) {
				result.add(child);
			}
		}
		return result;
	}

	/**
	 * @return
	 */
	public boolean hasChild(String name) {
		for (ParserTree child : children) {
			if (child.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the node
	 */
	public boolean isNode() {
		return node;
	}

	/**
	 * @return the stackingAllowed
	 */
	public boolean isStackingAllowed() {
		return stackingAllowed;
	}

	public List<ParserTree> getSubTrees(String name) {
		List<ParserTree> subTrees = new ArrayList<ParserTree>();
		getSubTrees(this, subTrees, name);
		return subTrees;
	}

	private void getSubTrees(ParserTree branch, List<ParserTree> subTrees,
			String name) {
		if (branch.getName().equals(name)) {
			subTrees.add(branch);
		}
		for (ParserTree subBranch : branch.getChildren()) {
			getSubTrees(subBranch, subTrees, name);
		}
	}

	private class TextWalkerClient implements TreeVisitor<ParserTree> {

		private final StringBuffer text = new StringBuffer();

		@Override
		public WalkingAction visit(ParserTree syntaxTree) {
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
		TreeWalker<ParserTree> treeWalker = new TreeWalker<ParserTree>(this);
		TextWalkerClient textClient = new TextWalkerClient();
		treeWalker.walk(textClient);
		return textClient.getText();
	}

	public ParserTreeMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(ParserTreeMetaData metaData) {
		this.metaData = metaData;
	}

}
