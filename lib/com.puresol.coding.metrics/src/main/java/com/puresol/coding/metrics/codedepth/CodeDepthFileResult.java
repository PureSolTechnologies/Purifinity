package com.puresol.coding.metrics.codedepth;

import java.io.Serializable;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class CodeDepthFileResult implements Serializable {

    private static final long serialVersionUID = -2731668647369978957L;

    private final String file;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final int maxDepth;
    private final SourceCodeQuality quality;

    public CodeDepthFileResult(String file, CodeRangeType codeRangeType,
	    String codeRangeName, int maxDepth, SourceCodeQuality quality) {
	super();
	this.file = file;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.maxDepth = maxDepth;
	this.quality = quality;
    }

    public String getFile() {
	return file;
    }

    public CodeRangeType getCodeRangeType() {
	return codeRangeType;
    }

    public String getCodeRangeName() {
	return codeRangeName;
    }

    public int getMaxDepth() {
	return maxDepth;
    }

    public SourceCodeQuality getQuality() {
	return quality;
    }

}
