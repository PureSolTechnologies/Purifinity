package com.puresoltechnologies.purifinity.evaluation.domain.issues;

import com.puresoltechnologies.commons.domain.GeneralValue;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

/**
 * This class contains the description of a single issue.
 * 
 * @author Rick-Rainer Ludwig
 */
public class Issue extends GeneralValue<Integer> {

    private static final long serialVersionUID = 3991682986335349586L;

    private final Severity severity;
    private final Classification classification;
    private final int startLine;
    private final int startColumn;
    private final int lineCount;
    private final int length;
    private final String remark;

    public Issue(Severity severity, Classification classification, int startLine, int startColumn, int lineCount,
	    int length, Integer weight, IssueParameter parameter, String remark) {
	super(weight, parameter);
	this.severity = severity;
	this.classification = classification;
	this.startLine = startLine;
	this.startColumn = startColumn;
	this.lineCount = lineCount;
	this.length = length;
	this.remark = remark;
    }

    public final Severity getSeverity() {
	return severity;
    }

    public final Classification getClassification() {
	return classification;
    }

    public final int getStartLine() {
	return startLine;
    }

    public final int getStartColumn() {
	return startColumn;
    }

    public final int getLineCount() {
	return lineCount;
    }

    public final int getLength() {
	return length;
    }

    public final String getRemark() {
	return remark;
    }

}
