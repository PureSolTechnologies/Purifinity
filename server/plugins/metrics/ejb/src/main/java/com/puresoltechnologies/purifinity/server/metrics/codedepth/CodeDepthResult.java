package com.puresoltechnologies.purifinity.server.metrics.codedepth;

import java.io.Serializable;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public class CodeDepthResult implements Serializable {

	private static final long serialVersionUID = -2731668647369978957L;

	private final SourceCodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final int maxDepth;
	private final SourceCodeQuality quality;

	public CodeDepthResult(SourceCodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName, int maxDepth,
			SourceCodeQuality quality) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
		this.maxDepth = maxDepth;
		this.quality = quality;
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

	public int getMaxDepth() {
		return maxDepth;
	}

	public SourceCodeQuality getQuality() {
		return quality;
	}

}
