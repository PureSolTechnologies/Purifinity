package com.puresol.coding.metrics.codedepth;

import java.io.Serializable;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.SourceCodeQuality;
import com.puresol.uhura.source.CodeLocation;

public class CodeDepthResult implements Serializable {

	private static final long serialVersionUID = -2731668647369978957L;

	private final CodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final int maxDepth;
	private final SourceCodeQuality quality;

	public CodeDepthResult(CodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName, int maxDepth,
			SourceCodeQuality quality) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
		this.maxDepth = maxDepth;
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

	public int getMaxDepth() {
		return maxDepth;
	}

	public SourceCodeQuality getQuality() {
		return quality;
	}

}
