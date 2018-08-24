package com.puresoltechnologies.purifinity.server.metrics.codedepth;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

public class CodeDepthQuality {

    public static Severity get(CodeRangeType codeRangeType, int maxDepth) {

	if ((codeRangeType == CodeRangeType.FILE) || (codeRangeType == CodeRangeType.CLASS)
		|| (codeRangeType == CodeRangeType.INTERFACE) || (codeRangeType == CodeRangeType.ENUMERATION)
		|| (codeRangeType == CodeRangeType.ANNOTATION) || (codeRangeType == CodeRangeType.MODULE)) {
	    if (maxDepth > 7) {
		return Severity.CRITICAL;
	    } else if (maxDepth > 5) {
		return Severity.MAJOR;
	    }
	    return Severity.NONE;
	} else if ((codeRangeType == CodeRangeType.CONSTRUCTOR) || (codeRangeType == CodeRangeType.METHOD)
		|| (codeRangeType == CodeRangeType.PROGRAM) || (codeRangeType == CodeRangeType.SUBROUTINE)
		|| (codeRangeType == CodeRangeType.FUNCTION)) {
	    if (maxDepth > 6) {
		return Severity.CRITICAL;
	    } else if (maxDepth > 4) {
		return Severity.MAJOR;
	    }
	    return Severity.NONE;
	} else if (codeRangeType == CodeRangeType.DIRECTORY) {
	    return Severity.NONE;
	}
	return Severity.NONE;
    }
}
