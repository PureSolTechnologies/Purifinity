package com.puresol.coding;

import java.util.Hashtable;

/**
 * This class calculates the cyclomatic number v(G) from a code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetric {

	private CodeRange codeRange;
	private int cyclomaticNumber = 1;

	public McCabeMetric(CodeRange codeRange) {
		this.codeRange = codeRange;
		calculate();
	}

	private void calculate() {
		Hashtable<Integer, TokenContent> tokenContents = codeRange
				.getTokenContents();
		for (int index = codeRange.getStart(); index <= codeRange.getStop(); index++) {
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
}
