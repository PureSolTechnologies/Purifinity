package com.puresoltechnologies.commons.trees;

import java.util.List;

/**
 * This class contains helper method to work with trees.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TreeUtils {

	/**
	 * This method counts all nodes within a tree.
	 * 
	 * @param tree
	 *            is a {@link Tree} object which nodes are to be calculated.
	 * @return An integer is returned containing the number of nodes. If tree is
	 *         <code>null</code> 0 is returned.
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

	/**
	 * This method checks to trees for equality. Equality is only checked on
	 * name basis and the order of the children is neglected.
	 * 
	 * @param tree1
	 *            is the first tree to be compared to the second.
	 * @param tree2
	 *            is the second tree to be compared to the first.
	 * @return <code>true</code> is returned if the trees are equals.
	 *         <code>false</code> is returned otherwise.
	 */
	public static <T extends Tree<T>> boolean equalsWithoutOrder(T tree1,
			T tree2) {
		if (!tree1.getName().equals(tree2.getName())) {
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

	/**
	 * Private constructor to avoid instantiation.
	 */
	private TreeUtils() {
	}
}
