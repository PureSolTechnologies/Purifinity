package com.puresoltechnologies.purifinity.evaluation.domain.style;

import com.puresoltechnologies.commons.domain.GeneralValue;

public class StyleIssue extends GeneralValue<Integer> {

    private static final long serialVersionUID = 3991682986335349586L;

    private final int startLine;
    private final int startColumn;
    private final int lineCount;
    private final int length;

    public StyleIssue(int startLine, int startColumn, int lineCount, int length, Integer weight,
	    StyleIssueParameter parameter) {
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
