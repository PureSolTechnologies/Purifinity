package com.puresoltechnologies.purifinity.server.metrics.entropy;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

public class EntropyQuality {

    public static Severity get(CodeRangeType codeRangeType, EntropyMetricResult result) {

	if ((codeRangeType == CodeRangeType.FILE) || (codeRangeType == CodeRangeType.CLASS)
		|| (codeRangeType == CodeRangeType.INTERFACE) || (codeRangeType == CodeRangeType.ENUMERATION)
		|| (codeRangeType == CodeRangeType.ANNOTATION) || (codeRangeType == CodeRangeType.MODULE)) {
	    if (result.getNormalizedRedundancy() > 0.40) {
		return Severity.CRITICAL;
	    }
	    if (result.getNormalizedRedundancy() > 0.20) {
		return Severity.MAJOR;
	    }
	    return Severity.NONE;
	} else if ((codeRangeType == CodeRangeType.CONSTRUCTOR) || (codeRangeType == CodeRangeType.METHOD)
		|| (codeRangeType == CodeRangeType.PROGRAM) || (codeRangeType == CodeRangeType.SUBROUTINE)
		|| (codeRangeType == CodeRangeType.FUNCTION)) {
	    if (result.getNormalizedRedundancy() > 0.40) {
		return Severity.CRITICAL;
	    }
	    if (result.getNormalizedRedundancy() > 0.20) {
		return Severity.MAJOR;
	    }
	    return Severity.NONE;
	} else if (codeRangeType == CodeRangeType.DIRECTORY) {
	    return Severity.NONE;
	}
	return Severity.NONE;
    }
}
