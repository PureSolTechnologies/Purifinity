package com.puresol.coding.metrics.sloc;

import java.io.Serializable;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.SourceCodeQuality;
import com.puresol.uhura.source.CodeLocation;

public class SLOCResult implements Serializable {

	private static final long serialVersionUID = 2302225864694571092L;

	private final CodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final SLOCMetric slocMetric;
	private final SourceCodeQuality quality;

	public SLOCResult(CodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName,
			SLOCMetric slocResult, SourceCodeQuality quality) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
		this.slocMetric = slocResult;
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

	public SLOCMetric getSLOCMetric() {
		return slocMetric;
	}

	public SourceCodeQuality getQuality() {
		return quality;
	}

}
