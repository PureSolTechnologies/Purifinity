package com.puresoltechnologies.purifinity.server.metrics.normmaint;

import java.io.Serializable;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

public class NormalizedMaintainabilityIndexFileResult implements Serializable {

	private static final long serialVersionUID = 2302225864694571092L;

	private final SourceCodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final NormalizedMaintainabilityIndexResult maintainabilityIndexResult;
	private final Severity quality;

	public NormalizedMaintainabilityIndexFileResult(
			SourceCodeLocation sourceCodeLocation, CodeRangeType codeRangeType,
			String codeRangeName,
			NormalizedMaintainabilityIndexResult maintainabilityIndexResult,
			Severity quality) {
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

	public Severity getQuality() {
		return quality;
	}

}
