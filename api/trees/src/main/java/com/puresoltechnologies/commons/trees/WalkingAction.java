package com.puresoltechnologies.commons.trees;

/**
 * This enum contains the possibilities how the process can be proceeded in each
 * single node. This is needed for {@link TreeVisitor} to tell the
 * {@link TreeWalker} how to proceed after a node was visited.
 * 
 * These actions are needed for different efficient solutions of tree walking
 * functions. For search walks, a walk may be finished as soon as a certain node
 * was found or for some branches a processing does not make any sense and the
 * walker needs to skip to the next branch. These actions are indicated by this
 * enum.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum WalkingAction {

	/**
	 * This constant means to proceed node by node as normal. The visitor is
	 * called for the next node in the walking path.
	 */
	PROCEED,

	/**
	 * This constant means to abort and not to proceed any more nodes. This
	 * action does not distinguish between aborts because of error or success.
	 * The visitor needs to provide this information.
	 */
	ABORT,

	/**
	 * This constant means to leave the current branch and not to step any
	 * deeper. The walker processes with the next node which is on the same
	 * level or on a higher level.
	 */
	LEAVE_BRANCH;

}
