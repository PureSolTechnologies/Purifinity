package com.puresoltechnologies.commons.trees;

import java.util.Iterator;

/**
 * This is a simple tree iterator for all trees implementing the tree interface.
 * If a {@link Tree} implementation wants to provide a iterator() method, this
 * class is used to instantiate a suitable iterator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the actual tree type.
 */
public class TreeIterator<T extends Tree<T>> implements Iterator<T> {

	private final T tree;
	private T currentNode;

	/**
	 * This constructor instantiates the object.
	 * 
	 * @param tree
	 *            is a {@link Tree} which is to be iterated over.
	 */
	public TreeIterator(T tree) {
		super();
		if (tree == null) {
			throw new IllegalArgumentException(
					"The tree object must not be null. There is nothing to iterate if it is.");
		}
		this.tree = tree;
		currentNode = tree;
	}

	/**
	 * This method returns the current node where the iterator is at the very
	 * moment.
	 * 
	 * @return A {@link Tree} of T is returned.
	 */
	public T getCurrentNode() {
		return currentNode;
	}

	/**
	 * This method resets the iterator and put the current node to the
	 * beginning.
	 */
	public void gotoStart() {
		currentNode = tree;
	}

	/**
	 * This method sets the iterator onto the last tree element.
	 */
	public void gotoEnd() {
		currentNode = tree;
		while (currentNode.hasChildren()) {
			currentNode = currentNode.getChildren().get(
					currentNode.getChildren().size() - 1);
		}
	}

	/**
	 * This method walks forward one node.
	 * 
	 * @return <code>true</code> is returned if this was successful.
	 *         <code>false</code> is returned otherwise, when the current node
	 *         is the last node reachable.
	 */
	public boolean goForward() {
		if (currentNode.hasChildren()) {
			currentNode = currentNode.getChildren().get(0);
			return true;
		} else if (currentNode.getParent() == null) {
			return false;
		}
		while (true) {
			T parent = currentNode.getParent();
			if ((parent == null) || (currentNode == tree)) {
				return false;
			}
			int index = parent.getChildren().indexOf(currentNode);
			if (parent.getChildren().size() > index + 1) {
				currentNode = parent.getChildren().get(index + 1);
				return true;
			} else {
				currentNode = parent;
			}
		}
	}

	/**
	 * This method walks backward one node.
	 * 
	 * @return <code>true</code> is returned if this was successful.
	 *         <code>false</code> is returned otherwise, when the current node
	 *         is the root node.
	 */
	public boolean goBackward() {
		T parent = currentNode.getParent();
		if ((parent == null) || (currentNode == tree)) {
			return false;
		}
		int index = parent.getChildren().indexOf(currentNode);
		if (index > 0) {
			currentNode = parent.getChildren().get(index - 1);
			while (currentNode.hasChildren()) {
				currentNode = currentNode.getChildren().get(
						currentNode.getChildren().size() - 1);
			}
			return true;
		}
		if (index == 0) {
			currentNode = parent;
			return true;
		}
		return false;
	}

	@Override
	public boolean hasNext() {
		T testNode = currentNode;
		if (testNode.hasChildren()) {
			return true;
		} else if (testNode.getParent() == null) {
			return false;
		}
		while (true) {
			T parent = testNode.getParent();
			if ((parent == null) || (testNode == tree)) {
				return false;
			}
			int index = parent.getChildren().indexOf(testNode);
			if (parent.getChildren().size() > index + 1) {
				return true;
			} else {
				testNode = parent;
			}
		}
	}

	@Override
	public T next() {
		if (!goForward()) {
			throw new IllegalStateException("There is no element left!");
		}
		return getCurrentNode();
	}

	@Override
	public void remove() {
		throw new IllegalStateException("This tree is immutable!");
	}
}
