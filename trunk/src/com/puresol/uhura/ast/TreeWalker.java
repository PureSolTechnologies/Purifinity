package com.puresol.uhura.ast;

public class TreeWalker {

	private final SyntaxTree syntaxTree;

	public TreeWalker(SyntaxTree syntaxTree) {
		super();
		this.syntaxTree = syntaxTree;
	}

	/**
	 * @return the syntaxTree
	 */
	public SyntaxTree getSyntaxTree() {
		return syntaxTree;
	}

	public void walk(TreeWalkerClient walkerClient) {
		walk(syntaxTree, walkerClient);
	}

	private WalkingAction walk(SyntaxTree syntaxTree,
			TreeWalkerClient walkerClient) {
		WalkingAction action = walkerClient.trigger(syntaxTree);
		if (action == WalkingAction.ABORT) {
			return WalkingAction.ABORT;
		} else if (action == WalkingAction.LEAVE_BRANCH) {
			return WalkingAction.PROCEED;
		}
		for (SyntaxTree child : syntaxTree.getChildren()) {
			walk(child, walkerClient);
		}
		return WalkingAction.PROCEED;
	}

}
