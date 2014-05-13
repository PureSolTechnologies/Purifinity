package com.puresoltechnologies.purifinity.server.core.impl.evaluation.metrics.normmaint;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

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
