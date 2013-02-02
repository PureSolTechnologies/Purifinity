package com.puresol.coding.metrics.sloc;

import java.io.Serializable;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.quality.api.SourceCodeQuality;
import com.puresol.uhura.source.CodeLocation;

public class SLOCFileResult implements Serializable {

    private static final long serialVersionUID = 2302225864694571092L;

    private final CodeLocation sourceCodeLocation;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final SLOCResult slocResult;
    private final SourceCodeQuality quality;

    public SLOCFileResult(CodeLocation sourceCodeLocation,
	    CodeRangeType codeRangeType, String codeRangeName,
	    SLOCResult slocResult, SourceCodeQuality quality) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.slocResult = slocResult;
	this.quality = quality;
    }

    public CodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    public CodeRangeType getCodeRangeType() {
	return codeRangeType;
    }

    public String getCodeRangeName() {
	return codeRangeName;
    }

    public SLOCResult getSLOCResult() {
	return slocResult;
    }

    public SourceCodeQuality getQuality() {
	return quality;
    }

}
