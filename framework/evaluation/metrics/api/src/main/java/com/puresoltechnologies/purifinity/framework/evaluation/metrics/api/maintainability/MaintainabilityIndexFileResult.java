package com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability;

import java.io.Serializable;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public class MaintainabilityIndexFileResult implements Serializable {

	private static final long serialVersionUID = 2302225864694571092L;

	private final SourceCodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final MaintainabilityIndexResult maintainabilityIndexResult;
	private final SourceCodeQuality quality;

	public MaintainabilityIndexFileResult(
			SourceCodeLocation sourceCodeLocation, CodeRangeType codeRangeType,
			String codeRangeName,
			MaintainabilityIndexResult maintainabilityIndexResult,
			SourceCodeQuality quality) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
		this.maintainabilityIndexResult = maintainabilityIndexResult;
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

	public MaintainabilityIndexResult getMaintainabilityIndexResult() {
		return maintainabilityIndexResult;
	}

	public SourceCodeQuality getQuality() {
		return quality;
	}

}
