package com.puresoltechnologies.commons.trees;

import java.util.List;

/**
 * This is the most simple interface to a tree. The parent can be retrieved and
 * also all children. It's designed as generic to be useful in all kinds of
 * trees.
 * 
 * The interface just provides methods for an immutable tree to get a
 * thread-safe variant of trees. The tree creation should be finished as soon as
 * this interface is exposed. All other threads using this interface can access
 * in a thread-safe way.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the actual class of the tree.
 */
public interface Tree<T> {

	/**
	 * This method returns the parent node.
	 * 
	 * @return A {@link Tree} of T is returned. <code>null</code> is returned if
	 *         no parent is present and the current node is the root node.
	 */
	public T getParent();

	/**
	 * This method returns whether or not the current node contains children.
	 * 
	 * @return <code>true</code> is returned in case the node contains children.
	 *         <code>false</code> is returned otherwise.
	 */
	public boolean hasChildren();

	/**
	 * This method returns the children which are contained in the node.
	 * 
	 * @return A {@link List} of T is returned. If not child is found this list
	 *         is empty. The return value <b<must not</b> be <code>null</code>.
	 */
	public List<T> getChildren();

	/**
	 * This method returns the node identifier. Each node in a tree should have
	 * an identifying name. This is the most basic information. Implementation
	 * of this interface {@link Tree} may have other values, too.
	 * 
	 * @return A {@link String} is returned containing the name.
	 */
	public String getName();
}
