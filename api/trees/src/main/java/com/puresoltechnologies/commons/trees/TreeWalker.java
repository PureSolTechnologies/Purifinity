package com.puresoltechnologies.commons.trees;

/**
 * This is a simple tree walker for all trees implementing the tree interface.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the actual tree type.
 */
public class TreeWalker<T extends Tree<T>> {

	public static <T extends Tree<T>> void walk(TreeVisitor<T> visitor, T tree) {
		new TreeWalker<T>(tree).walk(visitor);
	}

	public static <T extends Tree<T>> void walkBackward(TreeVisitor<T> visitor,
			T tree) {
		new TreeWalker<T>(tree).walkBackward(visitor);
	}

	private final T tree;

	public TreeWalker(T tree) {
		super();
		if (tree == null) {
			throw new IllegalArgumentException(
					"Tree object must not be null. There is nothing to walk on otherwise.");
		}
		this.tree = tree;
	}

	/**
	 * @return the tree
	 */
	public T getTree() {
		return tree;
	}

	/**
	 * This method is used to start the walking process. The visitor is
	 * specified which is called every time a new node is reached.
	 * 
	 * @param treeVisitor
	 */
	public void walk(TreeVisitor<T> treeVisitor) {
		if (tree != null) {
			walk(tree, treeVisitor);
		}
	}

	/**
	 * This is the recursive part of the walk method.
	 * 
	 * @param tree
	 * @param walkerClient
	 * @return
	 */
	private WalkingAction walk(T tree, TreeVisitor<T> walkerClient) {
		WalkingAction action = walkerClient.visit(tree);
		if (action == WalkingAction.ABORT) {
			return WalkingAction.ABORT;
		} else if (action == WalkingAction.LEAVE_BRANCH) {
			return WalkingAction.PROCEED;
		}
		for (T child : tree.getChildren()) {
			if (walk(child, walkerClient) == WalkingAction.ABORT) {
				return WalkingAction.ABORT;
			}
		}
		return WalkingAction.PROCEED;
	}

	/**
	 * This method is used to start the walking process in an reverse order. The
	 * visitor is specified which is called every time a new node is reached.
	 * 
	 * @param treeVisitor
	 */
	public void walkBackward(TreeVisitor<T> treeVisitor) {
		if (tree != null) {
			walkBackward(tree, treeVisitor);
		}
	}

	private WalkingAction walkBackward(T tree, TreeVisitor<T> walkerClient) {
		for (int id = tree.getChildren().size() - 1; id >= 0; id--) {
			T child = tree.getChildren().get(id);
			WalkingAction action = walkBackward(child, walkerClient);
			if (action == WalkingAction.ABORT) {
				return WalkingAction.ABORT;
			} else if (action == WalkingAction.LEAVE_BRANCH) {
				return WalkingAction.PROCEED;
			}
		}
		return walkerClient.visit(tree);
	}
}
