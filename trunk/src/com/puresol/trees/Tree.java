package com.puresol.trees;

import java.util.List;

/**
 * This is the most simple interface to a tree. The parent can be retrieved and
 * also all children. It's designed as generic to be useful in all kinds of
 * trees.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the actual class of the tree.
 */
public interface Tree<T> {

	public T getParent();

	public List<T> getChildren();

}
