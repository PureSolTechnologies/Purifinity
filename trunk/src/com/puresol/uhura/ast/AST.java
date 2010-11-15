package com.puresol.uhura.ast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class AST implements Tree<AST>, Serializable {

	private static final long serialVersionUID = -651453440127029204L;

	private final String name;
	private final Token token;
	private AST parent = null;
	private final List<AST> children = new ArrayList<AST>();
	private final boolean node;
	private final boolean stackingAllowed;
	private ASTMetaData metaData = null;

	public AST(Token token) {
		super();
		this.name = token.getName();
		this.token = token;
		this.node = true;
		this.stackingAllowed = true;
	}

	public AST(Production production) {
		this.name = production.getAlternativeName();
		this.token = null;
		this.node = production.isNode();
		this.stackingAllowed = production.isStackingAllowed();
	}

	public AST(String name) {
		this.name = name;
		this.token = null;
		this.node = true;
		this.stackingAllowed = true;
	}

	public AST(String name, Token token, boolean node, boolean stackingAllowed) {
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
	public void setParent(AST parent) throws ASTException {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	@Override
	public AST getParent() {
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
	public List<AST> getChildren() {
		return children;
	}

	public void addChild(AST child) throws ASTException {
		children.add(child);
		child.setParent(this);
	}

	public void addChildren(List<AST> children) {
		this.children.addAll(children);
	}

	public void addChildInFront(AST child) throws ASTException {
		children.add(0, child);
		child.setParent(this);
	}

	public void addChildrenInFront(List<AST> children) {
		this.children.addAll(0, children);
	}

	/**
	 * @return the children
	 * @throws ASTException
	 */
	public AST getChild(String name) throws ASTException {
		AST result = null;
		for (AST child : children) {
			if (child.getName().equals(name)) {
				if (result != null) {
					throw new ASTException("Child '" + name
							+ "'is multiply defined!");
				}
				result = child;
			}
		}
		return result;
	}

	/**
	 * @return the children
	 * @throws ASTException
	 */
	public List<AST> getChildren(String name) throws ASTException {
		List<AST> result = new ArrayList<AST>();
		for (AST child : children) {
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
		for (AST child : children) {
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

	public List<AST> getSubTrees(String name) {
		List<AST> subTrees = new ArrayList<AST>();
		getSubTrees(this, subTrees, name);
		return subTrees;
	}

	private void getSubTrees(AST branch, List<AST> subTrees, String name) {
		if (branch.getName().equals(name)) {
			subTrees.add(branch);
		}
		for (AST subBranch : branch.getChildren()) {
			getSubTrees(subBranch, subTrees, name);
		}
	}

	private class TextWalkerClient implements TreeVisitor<AST> {

		private final StringBuffer text = new StringBuffer();

		@Override
		public WalkingAction visit(AST syntaxTree) {
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
		TreeWalker<AST> treeWalker = new TreeWalker<AST>(this);
		TextWalkerClient textClient = new TextWalkerClient();
		treeWalker.walk(textClient);
		return textClient.getText();
	}

	public ASTMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(ASTMetaData metaData) {
		this.metaData = metaData;
	}

}
