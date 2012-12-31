package com.puresol.coding.metrics.entropy;

import java.io.Serializable;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.quality.api.SourceCodeQuality;
import com.puresol.uhura.source.CodeLocation;

public class EntropyFileResult implements Serializable {

    private static final long serialVersionUID = 2302225864694571092L;

    private final CodeLocation sourceCodeLocation;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final EntropyResult entropyResult;
    private final SourceCodeQuality quality;

    public EntropyFileResult(CodeLocation sourceCodeLocation,
	    CodeRangeType codeRangeType, String codeRangeName,
	    EntropyResult entropyResult, SourceCodeQuality quality) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.entropyResult = entropyResult;
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

    public EntropyResult getMaintainabilityIndexResult() {
	return entropyResult;
    }

    public SourceCodeQuality getQuality() {
	return quality;
    }

}
