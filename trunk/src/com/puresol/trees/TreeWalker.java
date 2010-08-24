package com.puresol.trees;

/**
 * This is a simple tree walker for all trees implementing the tree interface.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the actual tree type.
 */
public class TreeWalker<T extends Tree<T>> {

	private final T syntaxTree;

	public TreeWalker(T syntaxTree) {
		super();
		this.syntaxTree = syntaxTree;
	}

	/**
	 * @return the syntaxTree
	 */
	public T getSyntaxTree() {
		return syntaxTree;
	}

	/**
	 * This class is used to start the walking process. The vistor is specified
	 * which is called everytime a new node is reached.
	 * 
	 * @param treeVisitor
	 */
	public void walk(TreeVisitor<T> treeVisitor) {
		walk(syntaxTree, treeVisitor);
	}

	/**
	 * This is the recursive part of the walk method.
	 * 
	 * @param syntaxTree
	 * @param walkerClient
	 * @return
	 */
	private WalkingAction walk(T syntaxTree, TreeVisitor<T> walkerClient) {
		WalkingAction action = walkerClient.visit((T) syntaxTree);
		if (action == WalkingAction.ABORT) {
			return WalkingAction.ABORT;
		} else if (action == WalkingAction.LEAVE_BRANCH) {
			return WalkingAction.PROCEED;
		}
		for (T child : syntaxTree.getChildren()) {
			walk(child, walkerClient);
		}
		return WalkingAction.PROCEED;
	}

}
