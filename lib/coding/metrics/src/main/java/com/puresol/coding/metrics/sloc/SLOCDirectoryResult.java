package com.puresol.coding.metrics.sloc;

import java.io.Serializable;

public class SLOCDirectoryResult implements Serializable {

    private static final long serialVersionUID = 5219081053518506427L;

    private final String file;
    private final SLOCResult slocResult;

    public SLOCDirectoryResult(String file, SLOCResult slocResult) {
	super();
	this.file = file;
	this.slocResult = slocResult;
    }

    public String getFile() {
	return file;
    }

    public SLOCResult getSLOCResult() {
	return slocResult;
    }

}
