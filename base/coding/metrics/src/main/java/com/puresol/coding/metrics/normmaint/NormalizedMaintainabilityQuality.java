package com.puresol.coding.metrics.normmaint;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.impl.quality.SourceCodeQuality;

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
