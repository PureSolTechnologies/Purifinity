package com.puresol.purifinity.uhura.ust.facilities;

import com.puresol.purifinity.uhura.ust.AbstractUniversalSyntaxTree;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

/**
 * This tree element signals a scope for variable and element validity. This
 * scope is introduced everywhere where a new scope is starting. The elements
 * within the scope are children of the this class.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class VariableScope extends AbstractUniversalSyntaxTree {

	private static final long serialVersionUID = 7165390340532196126L;

	public VariableScope(UniversalSyntaxTree block) {
		super("variable scope");
		addChild(block);
	}

	@Override
	public String getName() {
		return "Variable Scope";
	}
}
