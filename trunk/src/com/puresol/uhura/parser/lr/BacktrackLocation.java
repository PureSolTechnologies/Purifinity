package com.puresol.uhura.parser.lr;

import java.io.Serializable;
import java.util.Stack;

import com.puresol.uhura.ast.AST;

public class BacktrackLocation implements Serializable {

	private static final long serialVersionUID = -2641716613348317852L;

	private final Stack<Integer> stateStack;
	private final Stack<AST> treeStack;
	private final int streamPosition;
	private final int stepCounter;
	private final int lastAlternative;

	public BacktrackLocation(Stack<Integer> stateStack, Stack<AST> treeStack,
			int streamPosition, int stepCounter, int lastAlternative) {
		super();
		this.stateStack = stateStack;
		this.treeStack = treeStack;
		this.streamPosition = streamPosition;
		this.stepCounter = stepCounter;
		this.lastAlternative = lastAlternative;
	}

	/**
	 * @return the stateStack
	 */
	public Stack<Integer> getStateStack() {
		return stateStack;
	}

	/**
	 * @return the treeStack
	 */
	public Stack<AST> getTreeStack() {
		return treeStack;
	}

	/**
	 * @return the streamPosition
	 */
	public int getStreamPosition() {
		return streamPosition;
	}

	/**
	 * @return the stepCounter
	 */
	public int getStepCounter() {
		return stepCounter;
	}

	/**
	 * @return the lastAlternative
	 */
	public int getLastAlternative() {
		return lastAlternative;
	}
}
