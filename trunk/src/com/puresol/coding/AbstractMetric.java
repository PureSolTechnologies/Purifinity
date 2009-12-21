package com.puresol.coding;

abstract public class AbstractMetric implements Analysis {

    private CodeRange range = null;

    public AbstractMetric(CodeRange range) {
	this.range = range;
    }

    public CodeRange getCodeRange() {
	return range;
    }
}
