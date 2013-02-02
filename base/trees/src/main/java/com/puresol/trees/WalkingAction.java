package com.puresol.trees;

/**
 * This enum contains the possibilities how the process can be proceeded in each
 * single node.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum WalkingAction {

	/**
	 * This constant means to proceed node by node as normal.
	 */
	PROCEED,

	/**
	 * This constant means to abort and not to proceed.
	 */
	ABORT,
	/**
	 * This constant means to leave the current branch and to step upward and
	 * not to proceed downward.
	 */
	LEAVE_BRANCH;

}
