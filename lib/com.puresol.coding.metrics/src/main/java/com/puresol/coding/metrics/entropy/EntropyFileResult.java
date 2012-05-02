package com.puresol.coding.metrics.entropy;

import java.io.Serializable;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class EntropyFileResult implements Serializable {

    private static final long serialVersionUID = 2302225864694571092L;

    private final String file;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final EntropyResult entropyResult;
    private final SourceCodeQuality quality;

    public EntropyFileResult(String file, CodeRangeType codeRangeType,
	    String codeRangeName, EntropyResult entropyResult,
	    SourceCodeQuality quality) {
	super();
	this.file = file;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.entropyResult = entropyResult;
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

    public EntropyResult getMaintainabilityIndexResult() {
	return entropyResult;
    }

    public SourceCodeQuality getQuality() {
	return quality;
    }

}
