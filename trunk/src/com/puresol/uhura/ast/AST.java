package com.puresol.uhura.ast;

import java.io.Serializable;
import java.lang.reflect.Field;
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
public class AST implements Tree<AST>, Serializable, Cloneable {

	private static final long serialVersionUID = -651453440127029204L;

	private final String name;
	private final Token token;
	private AST parent = null;
	private final List<AST> children = new ArrayList<AST>();
	private boolean node = true;
	private boolean stackingAllowed = true;

	public AST(Token token) {
		this.name = token.getName();
		this.token = token;
	}

	public AST(Production production) {
		this.name = production.getAlternativeName();
		this.token = null;
		node = production.isNode();
		stackingAllowed = production.isStackingAllowed();
	}

	public AST(String name) {
		this.name = name;
		this.token = null;
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
		return name + " " + token;
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
	 * @param node
	 *            the node to set
	 */
	public void setNode(boolean node) {
		this.node = node;
	}

	/**
	 * @return the stackingAllowed
	 */
	public boolean isStackingAllowed() {
		return stackingAllowed;
	}

	/**
	 * @param stackingAllowed
	 *            the stackingAllowed to set
	 */
	public void setStackingAllowed(boolean stackingAllowed) {
		this.stackingAllowed = stackingAllowed;
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

	@Override
	public AST clone() {
		try {
			AST cloned = (AST) super.clone();

			Field name = cloned.getClass().getDeclaredField("name");
			name.setAccessible(true);
			name.set(cloned, this.name);

			Field token = cloned.getClass().getDeclaredField("token");
			token.setAccessible(true);
			token.set(cloned, this.token);

			Field children = cloned.getClass().getDeclaredField("children");
			children.setAccessible(true);
			children.set(cloned, new ArrayList<AST>());

			for (AST child : this.children) {
				cloned.children.add(child);
			}

			cloned.node = this.node;
			cloned.stackingAllowed = this.stackingAllowed;
			return cloned;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e.getMessage());
		} catch (SecurityException e) {
			throw new RuntimeException(e.getMessage());
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
