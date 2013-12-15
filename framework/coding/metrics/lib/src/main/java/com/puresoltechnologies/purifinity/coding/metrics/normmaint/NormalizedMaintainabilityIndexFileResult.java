package com.puresoltechnologies.purifinity.coding.metrics.normmaint;

import java.io.Serializable;

import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;

public class NormalizedMaintainabilityIndexFileResult implements Serializable {

	private static final long serialVersionUID = 2302225864694571092L;

	private final SourceCodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final NormalizedMaintainabilityIndexResult maintainabilityIndexResult;
	private final SourceCodeQuality quality;

	public NormalizedMaintainabilityIndexFileResult(
			SourceCodeLocation sourceCodeLocation, CodeRangeType codeRangeType,
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

	public SourceCodeLocation getSourceCodeLocation() {
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
