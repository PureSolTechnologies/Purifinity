package com.puresol.coding;

import java.util.Hashtable;

/**
 * This class calculates the cyclomatic number v(G) from a code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
abstract public class McCabeMetric extends AbstractMetric {

    private int cyclomaticNumber = 1;

    public McCabeMetric(CodeRange codeRange) {
	super(codeRange);
	calculate();
    }

    private void calculate() {
	CodeRange codeRange = getCodeRange();
	Hashtable<Integer, TokenContent> tokenContents =
		codeRange.getTokenContents();
	for (int index = codeRange.getStart(); index <= codeRange
		.getStop(); index++) {
	    if (tokenContents.containsKey(index)) {
		TokenContent content = tokenContents.get(index);
		addCyclomaticNumber(content.getCyclomaticNumber());
	    }
	}

    }

    private void addCyclomaticNumber(int cyclomaticNumber) {
	this.cyclomaticNumber += cyclomaticNumber;
    }

    public int getCyclomaticNumber() {
	return cyclomaticNumber;
    }

    public void print() {
	System.out.println("v(G) = " + cyclomaticNumber);
    }

    public static boolean isSuitable(CodeRange codeRange) {
	return true;
    }
}
