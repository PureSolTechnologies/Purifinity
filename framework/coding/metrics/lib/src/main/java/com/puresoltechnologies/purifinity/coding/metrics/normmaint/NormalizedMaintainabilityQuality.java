package com.puresoltechnologies.purifinity.coding.metrics.normmaint;

import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;

public class NormalizedMaintainabilityQuality {

	public static SourceCodeQuality get(CodeRangeType codeRangeType,
			NormalizedMaintainabilityIndexResult result) {
		if (result.getNMI() > 0.4) {
			return SourceCodeQuality.HIGH;
		}
		if (result.getNMI() > 0.3) {
			return SourceCodeQuality.MEDIUM;
		}
		return SourceCodeQuality.LOW;
	}
}
