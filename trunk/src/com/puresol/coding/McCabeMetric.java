package com.puresol.coding;

import java.util.Hashtable;

/**
 * This class calculates the cyclomatic number v(G) from a code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetric extends AbstractMetric {

	private int cyclomaticNumber = 1;

	public McCabeMetric(CodeRange codeRange) {
		super(codeRange);
		calculate();
	}

	private void calculate() {
		CodeRange codeRange = getCodeRange();
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

	public void print() {
		System.out.println("v(G) = " + cyclomaticNumber);
	}

	@Override
	public QualityLevel getQualityLevel() {
		CodeRange range = getCodeRange();
		if ((range.getType() == CodeRangeType.FILE)
				|| (range.getType() == CodeRangeType.CLASS)
				|| (range.getType() == CodeRangeType.ENUMERATION)) {
			if (cyclomaticNumber < 100) {
				return QualityLevel.HIGH;
			}
			if (cyclomaticNumber < 125) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.LOW;
		} else if ((range.getType() == CodeRangeType.CONSTRUCTOR)
				|| (range.getType() == CodeRangeType.METHOD)
				|| (range.getType() == CodeRangeType.FUNCTION)
				|| (range.getType() == CodeRangeType.INTERFACE)) {
			if (cyclomaticNumber < 15) {
				return QualityLevel.HIGH;
			}
			if (cyclomaticNumber < 20) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.LOW;
		}
		return QualityLevel.HIGH; // not evaluated...
	}

	@Override
	public boolean isSuitable(CodeRange codeRange) {
		return true;
	}
}
