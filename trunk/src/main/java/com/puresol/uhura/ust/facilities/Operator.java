package com.puresol.uhura.ust.facilities;

/**
 * This class represents an implementation of an operator. This is needed to
 * calculate Halstead metrics and other tasks where operators and operands need
 * to be distinguished.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class Operator extends CompilerRelevantElement {

	private static final long serialVersionUID = 1637318416068127842L;

	public Operator(String originalSymbol) {
		super(originalSymbol);
	}

}
