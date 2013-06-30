package com.puresol.purifinity.trees;

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
}
