package com.puresoltechnologies.purifinity.server.metrics.maintainability;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

public class MaintainabilityQuality {

    public static Severity get(CodeRangeType codeRangeType, MaintainabilityIndexResult result) {
	if (result.getMI() > 85) {
	    return Severity.NONE;
	}
	if (result.getMI() > 65) {
	    return Severity.MAJOR;
	}
	return Severity.CRITICAL;
    }
}
