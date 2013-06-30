package com.puresol.purifinity.coding.metrics.halstead;

import java.io.Serializable;

import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.uhura.source.CodeLocation;

public class HalsteadMetricResult implements Serializable {

	private static final long serialVersionUID = 2302225864694571092L;

	private final CodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final HalsteadResult halsteadResult;
	private final SourceCodeQuality quality;

	public HalsteadMetricResult(CodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName,
			HalsteadResult halsteadResult, SourceCodeQuality quality) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
		this.halsteadResult = halsteadResult;
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

	public HalsteadResult getHalsteadResult() {
		return halsteadResult;
	}

	public SourceCodeQuality getQuality() {
		return quality;
	}

}
