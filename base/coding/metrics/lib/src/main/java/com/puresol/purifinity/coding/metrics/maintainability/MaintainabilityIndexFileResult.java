package com.puresol.purifinity.coding.metrics.maintainability;

import java.io.Serializable;

import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.uhura.source.CodeLocation;

public class MaintainabilityIndexFileResult implements Serializable {

	private static final long serialVersionUID = 2302225864694571092L;

	private final CodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final MaintainabilityIndexResult maintainabilityIndexResult;
	private final SourceCodeQuality quality;

	public MaintainabilityIndexFileResult(CodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName,
			MaintainabilityIndexResult maintainabilityIndexResult,
			SourceCodeQuality quality) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
		this.maintainabilityIndexResult = maintainabilityIndexResult;
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

	public MaintainabilityIndexResult getMaintainabilityIndexResult() {
		return maintainabilityIndexResult;
	}

	public SourceCodeQuality getQuality() {
		return quality;
	}

}
