package com.puresol.trees;

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

	public void walk(TreeVisitor<T> walkerClient) {
		walk(syntaxTree, walkerClient);
	}

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
