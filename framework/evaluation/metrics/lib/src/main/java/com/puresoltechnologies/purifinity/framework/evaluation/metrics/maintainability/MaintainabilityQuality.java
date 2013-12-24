package com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public class MaintainabilityQuality {

	public static SourceCodeQuality get(CodeRangeType codeRangeType,
			MaintainabilityIndexResult result) {
		if (result.getMI() > 85) {
			return SourceCodeQuality.HIGH;
		}
		if (result.getMI() > 65) {
			return SourceCodeQuality.MEDIUM;
		}
		return SourceCodeQuality.LOW;
	}
}
