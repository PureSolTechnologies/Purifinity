package com.puresol.coding.metrics.sloc;

public class LineResults {

	private int length = 0;
	private boolean comments = false;
	private boolean productiveContent = false;

	public int getLength() {
		return length;
	}

	public void addLength(int length) {
		this.length += length;
	}

	public void setLength(int length) {
		this.length = length;
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
