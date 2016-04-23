package com.puresoltechnologies.purifinity.server.metrics.sloc;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

public class SLOCQuality {

    public static Severity get(CodeRangeType codeRangeType, SLOCMetric sloc) {
	Severity levelLineCount = getQualityLevelLineCount(codeRangeType, sloc);
	Severity levelLineLength = getQualityLevelLineLength(codeRangeType, sloc);
	return Severity.getMostSevere(levelLineCount, levelLineLength);
    }

    private static Severity getQualityLevelLineCount(CodeRangeType codeRangeType, SLOCMetric sloc) {
	if ((codeRangeType == CodeRangeType.FILE) || (codeRangeType == CodeRangeType.CLASS)
		|| (codeRangeType == CodeRangeType.INTERFACE) || (codeRangeType == CodeRangeType.ENUMERATION)
		|| (codeRangeType == CodeRangeType.ANNOTATION) || (codeRangeType == CodeRangeType.MODULE)) {
	    if (sloc.getPhyLOC() > 2500) {
		return Severity.CRITICAL;
	    }
	    if (sloc.getPhyLOC() > 1000) {
		return Severity.MAJOR;
	    }
	    return Severity.NONE;
	} else if ((codeRangeType == CodeRangeType.CONSTRUCTOR) || (codeRangeType == CodeRangeType.METHOD)
		|| (codeRangeType == CodeRangeType.PROGRAM) || (codeRangeType == CodeRangeType.SUBROUTINE)
		|| (codeRangeType == CodeRangeType.FUNCTION)) {
	    if (sloc.getPhyLOC() > 40) {
		return Severity.CRITICAL;
	    }
	    if (sloc.getPhyLOC() > 25) {
		return Severity.MAJOR;
	    }
	    return Severity.NONE;
	} else if (codeRangeType == CodeRangeType.DIRECTORY) {
	    return Severity.NONE;
	}
	return Severity.NONE;
    }

    private static Severity getQualityLevelLineLength(CodeRangeType codeRangeType, SLOCMetric sloc) {
	if (sloc.getLineStatistics().getAvg() > 70) {
	    return Severity.CRITICAL;
	}
	if (sloc.getLineStatistics().getAvg() > 50) {
	    return Severity.MAJOR;
	}
	return Severity.NONE;
    }

}
