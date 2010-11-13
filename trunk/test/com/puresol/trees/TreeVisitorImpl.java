package com.puresol.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is a simple implementation for a tree visitor to check the
 * TreeWalker class.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TreeVisitorImpl implements TreeVisitor<TreeImpl> {

	private String nodeString = "";

	private Map<String, WalkingAction> actions = new HashMap<String, WalkingAction>();

	@Override
	public WalkingAction visit(TreeImpl syntaxTree) {
		nodeString += syntaxTree.getName();
		if (actions.containsKey(syntaxTree.getName())) {
			return actions.get(syntaxTree.getName());
		}
		return WalkingAction.PROCEED;
	}

	public void addAction(String name, WalkingAction action) {
		actions.put(name, action);
	}

	public String getNodeString() {
		return nodeString;
	}

}
