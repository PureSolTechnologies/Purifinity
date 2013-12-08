package com.puresoltechnologies.commons.trees.impl;

import java.util.Iterator;

/**
 * This is a simple tree walker for all trees implementing the tree interface.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the actual tree type.
 */
public class TreeIterator<T extends Tree<T>> implements Iterator<T> {

	private final T tree;
	private T currentNode;

	public TreeIterator(T tree) {
		super();
		this.tree = tree;
		currentNode = tree;
	}

	public T getCurrentNode() {
		return currentNode;
	}

	public void gotoStart() {
		currentNode = tree;
	}

	public void gotoEnd() {
		currentNode = tree;
		while (currentNode.hasChildren()) {
			currentNode = currentNode.getChildren().get(
					currentNode.getChildren().size() - 1);
		}
	}

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
