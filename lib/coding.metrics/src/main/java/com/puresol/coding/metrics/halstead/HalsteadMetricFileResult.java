package com.puresol.coding.metrics.halstead;

import java.io.Serializable;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class HalsteadMetricFileResult implements Serializable {

    private static final long serialVersionUID = 2302225864694571092L;

    private final String file;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final HalsteadResult halsteadResult;
    private final SourceCodeQuality quality;

    public HalsteadMetricFileResult(String file, CodeRangeType codeRangeType,
	    String codeRangeName, HalsteadResult halsteadResult,
	    SourceCodeQuality quality) {
	super();
	this.file = file;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.halsteadResult = halsteadResult;
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

    public HalsteadResult getHalsteadResult() {
	return halsteadResult;
    }

    public SourceCodeQuality getQuality() {
	return quality;
    }

}