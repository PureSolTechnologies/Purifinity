package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

public class McCabeQuality {

    public static Severity get(CodeRangeType codeRangeType, int cyclomaticNumber) {

	if ((codeRangeType == CodeRangeType.FILE) || (codeRangeType == CodeRangeType.CLASS)
		|| (codeRangeType == CodeRangeType.INTERFACE) || (codeRangeType == CodeRangeType.ENUMERATION)
		|| (codeRangeType == CodeRangeType.ANNOTATION) || (codeRangeType == CodeRangeType.MODULE)) {
	    if (cyclomaticNumber < 100) {
		return Severity.NONE;
	    }
	    if (cyclomaticNumber < 125) {
		return Severity.MAJOR;
	    }
	    return Severity.CRITICAL;
	} else if ((codeRangeType == CodeRangeType.CONSTRUCTOR) || (codeRangeType == CodeRangeType.METHOD)
		|| (codeRangeType == CodeRangeType.PROGRAM) || (codeRangeType == CodeRangeType.SUBROUTINE)
		|| (codeRangeType == CodeRangeType.FUNCTION)) {
	    if (cyclomaticNumber < 15) {
		return Severity.NONE;
	    }
	    if (cyclomaticNumber < 20) {
		return Severity.MAJOR;
	    }
	    return Severity.CRITICAL;
	} else if (codeRangeType == CodeRangeType.DIRECTORY) {
	    return Severity.NONE;
	}
	return Severity.NONE;
    }
}
