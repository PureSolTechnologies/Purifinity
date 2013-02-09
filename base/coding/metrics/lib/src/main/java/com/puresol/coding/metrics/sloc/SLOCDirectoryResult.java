package com.puresol.coding.metrics.sloc;

import java.io.Serializable;

import com.puresol.uhura.source.CodeLocation;

public class SLOCDirectoryResult implements Serializable {

    private static final long serialVersionUID = 5219081053518506427L;

    private final CodeLocation sourceCodeLocation;
    private final SLOCResult slocResult;

    public SLOCDirectoryResult(CodeLocation file, SLOCResult slocResult) {
	super();
	this.sourceCodeLocation = file;
	this.slocResult = slocResult;
    }

    public CodeLocation getCodeLocation() {
	return sourceCodeLocation;
    }

    public SLOCResult getSLOCResult() {
	return slocResult;
    }

}
