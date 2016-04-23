package com.puresoltechnologies.purifinity.server.metrics.normmaint;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

public class NormalizedMaintainabilityQuality {

    public static Severity get(CodeRangeType codeRangeType, NormalizedMaintainabilityIndexResult result) {
	if (result.getNMI() > 0.4) {
	    return Severity.NONE;
	}
	if (result.getNMI() > 0.3) {
	    return Severity.MAJOR;
	}
	return Severity.CRITICAL;
    }
}
