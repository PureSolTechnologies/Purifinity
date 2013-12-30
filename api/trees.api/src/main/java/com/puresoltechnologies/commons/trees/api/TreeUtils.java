package com.puresoltechnologies.commons.trees.api;

import java.util.List;

/**
 * This method contains helpers to work with trees.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TreeUtils {

	/**
	 * This method counts all nodes within a tree
	 * 
	 * @param fileTree
	 * @return
	 */
	public static <T extends Tree<T>> int countNodes(T tree) {
		int result = 0;
		if (tree == null) {
			return result;
		}
		List<T> children = tree.getChildren();
		for (T node : children) {
			result += countNodes(node);
		}
		return result + 1; // + 1 for self!
	}

	public static <T extends Tree<T>> boolean equalsWithoutOrder(T tree1,
			T tree2) {
		if (!tree1.getName().equals(tree2)) {
			return false;
		}
		List<T> children1 = tree1.getChildren();
		List<T> children2 = tree2.getChildren();
		if (children1.size() != children2.size()) {
			return false;
		}
		for (T child1 : children1) {
			for (T child2 : children2) {
				if (child1.equals(child2)) {
					boolean equals = equalsWithoutOrder(child1, child2);
					if (!equals) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
