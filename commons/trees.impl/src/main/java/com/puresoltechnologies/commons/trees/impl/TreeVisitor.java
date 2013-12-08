package com.puresoltechnologies.commons.trees.impl;

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

	public WalkingAction visit(T tree);

}
