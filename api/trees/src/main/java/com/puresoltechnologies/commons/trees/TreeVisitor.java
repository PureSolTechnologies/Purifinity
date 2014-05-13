package com.puresoltechnologies.commons.trees;

/**
 * This is the simple interface for a tree visitor. The walker walks the tree
 * and used this interface to tell the visitor the position.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the actual tree type.
 */
public interface TreeVisitor<T extends Tree<T>> {

	/**
	 * This method is called for every node in the tree.
	 * 
	 * @param tree
	 *            is the {@link Tree} object of the current visiting node.
	 * @return A {@link WalkingAction} is to be returned to tell the walker how
	 *         to proceed. For details of the different options, have a look to
	 *         the {@link WalkingAction} API documentation.
	 */
	public WalkingAction visit(T tree);

}
