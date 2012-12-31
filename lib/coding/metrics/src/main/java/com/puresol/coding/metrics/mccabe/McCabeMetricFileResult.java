package com.puresol.coding.metrics.mccabe;

import java.io.Serializable;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.quality.api.SourceCodeQuality;
import com.puresol.uhura.source.CodeLocation;

public class McCabeMetricFileResult implements Serializable {

    private static final long serialVersionUID = 2302225864694571092L;

    private final CodeLocation sourceCodeLocation;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final int cyclomaticComplexity;
    private final SourceCodeQuality quality;

    public McCabeMetricFileResult(CodeLocation sourceCodeLocation,
	    CodeRangeType codeRangeType, String codeRangeName,
	    int cyclomaticComplexity, SourceCodeQuality quality) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.cyclomaticComplexity = cyclomaticComplexity;
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

    public int getCyclomaticComplexity() {
	return cyclomaticComplexity;
    }

    public SourceCodeQuality getQuality() {
	return quality;
    }

}
