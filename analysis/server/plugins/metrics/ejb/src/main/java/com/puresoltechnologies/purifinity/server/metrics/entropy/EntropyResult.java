package com.puresoltechnologies.purifinity.server.metrics.entropy;

import java.io.Serializable;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

public class EntropyResult implements Serializable {

    private static final long serialVersionUID = 2302225864694571092L;

    private final SourceCodeLocation sourceCodeLocation;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final EntropyMetricResult entropyResult;

    public EntropyResult(SourceCodeLocation sourceCodeLocation,
	    CodeRangeType codeRangeType, String codeRangeName,
	    EntropyMetricResult entropyResult) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.entropyResult = entropyResult;
    }

    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    public CodeRangeType getCodeRangeType() {
	return codeRangeType;
    }

    public String getCodeRangeName() {
	return codeRangeName;
    }

    public EntropyMetricResult getEntropyMetricResult() {
	return entropyResult;
    }

}
