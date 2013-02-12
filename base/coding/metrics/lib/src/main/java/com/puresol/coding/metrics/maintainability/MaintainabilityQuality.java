package com.puresol.coding.metrics.maintainability;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.SourceCodeQuality;

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
