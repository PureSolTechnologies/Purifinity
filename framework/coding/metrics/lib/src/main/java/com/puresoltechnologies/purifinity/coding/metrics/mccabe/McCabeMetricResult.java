package com.puresoltechnologies.purifinity.coding.metrics.mccabe;

import java.io.Serializable;

import com.puresoltechnologies.parsers.api.source.CodeLocation;
<<<<<<< HEAD
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;
=======
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeQuality;
>>>>>>> 22bb20bf218d5d810e936dd668128ce7c35efbf9

public class McCabeMetricResult implements Serializable {

	private static final long serialVersionUID = 2302225864694571092L;

	private final CodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final int cyclomaticComplexity;
	private final SourceCodeQuality quality;

	public McCabeMetricResult(CodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName,
			int cyclomaticComplexity, SourceCodeQuality quality) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
		this.cyclomaticComplexity = cyclomaticComplexity;
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

	public int getCyclomaticComplexity() {
		return cyclomaticComplexity;
	}

	public SourceCodeQuality getQuality() {
		return quality;
	}

}
