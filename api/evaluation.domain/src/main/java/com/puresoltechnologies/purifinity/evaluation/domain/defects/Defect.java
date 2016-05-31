package com.puresoltechnologies.purifinity.evaluation.domain.defects;

import com.puresoltechnologies.commons.domain.GeneralValue;

public class Defect extends GeneralValue<Integer> {

    private static final long serialVersionUID = 3991682986335349586L;

    private final int startLine;
    private final int startColumn;
    private final int lineCount;
    private final int length;

    public Defect(int startLine, int startColumn, int lineCount, int length, Integer weight,
	    DefectParameter parameter) {
	super(weight, parameter);
	this.startLine = startLine;
	this.startColumn = startColumn;
	this.lineCount = lineCount;
	this.length = length;
    }

    public int getStartLine() {
	return startLine;
    }

    public int getStartColumn() {
	return startColumn;
    }

    public int getLineCount() {
	return lineCount;
    }

    public int getLength() {
	return length;
    }

}
