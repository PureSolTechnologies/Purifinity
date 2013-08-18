package com.puresol.purifinity.uhura.ust.facilities;

import com.puresol.purifinity.uhura.ust.Production;

/**
 * This tree element signals a scope for variable and element validity. This
 * scope is introduced everywhere where a new scope is starting. The elements
 * within the scope are children of the this class.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class VariableScope extends Production {

	private static final long serialVersionUID = 7165390340532196126L;

	public VariableScope(Production block) {
		super("Variable Scope", "variable scope", block);
	}
}
