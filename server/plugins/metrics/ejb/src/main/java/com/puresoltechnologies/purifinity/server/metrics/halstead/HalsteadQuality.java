package com.puresoltechnologies.purifinity.server.metrics.halstead;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

public class HalsteadQuality {

    public static Severity get(CodeRangeType codeRangeType, HalsteadResult result) {
	if ((codeRangeType == CodeRangeType.FILE) || (codeRangeType == CodeRangeType.CLASS)
		|| (codeRangeType == CodeRangeType.INTERFACE) || (codeRangeType == CodeRangeType.ENUMERATION)
		|| (codeRangeType == CodeRangeType.ANNOTATION) || (codeRangeType == CodeRangeType.MODULE)) {
	    if (result.getHalsteadVolume() < 80) {
		return Severity.MINOR;
	    }
	    if (result.getHalsteadVolume() > 10000) {
		return Severity.CRITICAL;
	    }
	    if (result.getHalsteadVolume() > 8000) {
		return Severity.MAJOR;
	    }
	    return Severity.NONE;
	} else if ((codeRangeType == CodeRangeType.CONSTRUCTOR) || (codeRangeType == CodeRangeType.METHOD)
		|| (codeRangeType == CodeRangeType.PROGRAM) || (codeRangeType == CodeRangeType.SUBROUTINE)
		|| (codeRangeType == CodeRangeType.FUNCTION)) {
	    if (result.getHalsteadVolume() < 10) {
		return Severity.MINOR;
	    }
	    if (result.getHalsteadVolume() > 1250) {
		return Severity.CRITICAL;
	    }
	    if (result.getHalsteadVolume() > 1000) {
		return Severity.MAJOR;
	    }
	    return Severity.NONE;
	} else if (codeRangeType == CodeRangeType.DIRECTORY) {
	    return Severity.NONE;
	}
	return Severity.NONE;
    }
}
