package com.puresol.purifinity.coding.metrics.normmaint;

import java.io.Serializable;

import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.uhura.source.CodeLocation;

public class NormalizedMaintainabilityIndexFileResult implements Serializable {

	private static final long serialVersionUID = 2302225864694571092L;

	private final CodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final NormalizedMaintainabilityIndexResult maintainabilityIndexResult;
	private final SourceCodeQuality quality;

	public NormalizedMaintainabilityIndexFileResult(
			CodeLocation sourceCodeLocation, CodeRangeType codeRangeType,
			String codeRangeName,
			NormalizedMaintainabilityIndexResult maintainabilityIndexResult,
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

	public NormalizedMaintainabilityIndexResult getNormalizedMaintainabilityIndexResult() {
		return maintainabilityIndexResult;
	}

	public SourceCodeQuality getQuality() {
		return quality;
	}

}
