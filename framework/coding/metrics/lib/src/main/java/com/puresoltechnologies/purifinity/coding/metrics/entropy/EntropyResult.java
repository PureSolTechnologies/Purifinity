package com.puresoltechnologies.purifinity.coding.metrics.entropy;

import java.io.Serializable;

import com.puresoltechnologies.parsers.impl.source.CodeLocation;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeQuality;

public class EntropyResult implements Serializable {

	private static final long serialVersionUID = 2302225864694571092L;

	private final CodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final EntropyMetricResult entropyResult;
	private final SourceCodeQuality quality;

	public EntropyResult(CodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName,
			EntropyMetricResult entropyResult, SourceCodeQuality quality) {
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

	public EntropyMetricResult getEntropyMetricResult() {
		return entropyResult;
	}

	public SourceCodeQuality getQuality() {
		return quality;
	}

}
