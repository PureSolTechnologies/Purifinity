package com.puresol.coding.metrics.mccabe;

import java.io.Serializable;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class McCabeMetricFileResult implements Serializable {

    private static final long serialVersionUID = 2302225864694571092L;

    private final String file;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final int cyclomaticComplexity;
    private final SourceCodeQuality quality;

    public McCabeMetricFileResult(String file, CodeRangeType codeRangeType,
	    String codeRangeName, int cyclomaticComplexity, SourceCodeQuality quality) {
	super();
	this.file = file;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.cyclomaticComplexity = cyclomaticComplexity;
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

    public int getCyclomaticComplexity() {
	return cyclomaticComplexity;
    }

    public SourceCodeQuality getQuality() {
	return quality;
    }

}
