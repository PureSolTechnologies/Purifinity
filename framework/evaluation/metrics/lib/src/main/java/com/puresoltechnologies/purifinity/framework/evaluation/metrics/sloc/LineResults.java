package com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc;

import java.io.Serializable;

/**
 * This class contains the results of a SLOC count run for a single line.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
class LineResults implements Serializable {

    private static final long serialVersionUID = -7222483379600215412L;

    private int length = 0;
    private boolean comments = false;
    private boolean productiveContent = false;

    public int getLength() {
	return length;
    }

    public void addLength(int length) {
	this.length += length;
    }

    public boolean hasComments() {
	return comments;
    }

    public void setComments(boolean comments) {
	this.comments = comments;
    }

    public boolean hasProductiveContent() {
	return productiveContent;
    }

    public void setProductiveContent(boolean productiveContent) {
	this.productiveContent = productiveContent;
    }

    public boolean isBlank() {
	return (!hasProductiveContent()) && (!hasComments());
    }
}
