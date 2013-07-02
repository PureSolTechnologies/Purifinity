package com.puresol.commons.trees;

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
     * This mehtod returns the parent node.
     * 
     * @return
     */
    public T getParent();

    /**
     * This method is used to check whether a node has children or not.
     * 
     * @return
     */
    public boolean hasChildren();

    /**
     * This method returns all children of the node.
     * 
     * @return
     */
    public List<T> getChildren();

    /**
     * This method returns the node identifier. Each node in a tree should have
     * an identifying name.
     * 
     * @return
     */
    public String getName();
}
