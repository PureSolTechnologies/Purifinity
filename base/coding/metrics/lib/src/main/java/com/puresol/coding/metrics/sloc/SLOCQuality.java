package com.puresol.coding.metrics.sloc;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.quality.SourceCodeQuality;

public class SLOCQuality {

	public static SourceCodeQuality get(CodeRangeType codeRangeType,
			SLOCResult sloc) {
		SourceCodeQuality levelLineCount = getQualityLevelLineCount(
				codeRangeType, sloc);
		SourceCodeQuality levelLineLength = getQualityLevelLineLength(
				codeRangeType, sloc);
		return SourceCodeQuality.getMinLevel(levelLineCount, levelLineLength);
	}

	private static SourceCodeQuality getQualityLevelLineCount(
			CodeRangeType codeRangeType, SLOCResult sloc) {
		if ((codeRangeType == CodeRangeType.FILE)
				|| (codeRangeType == CodeRangeType.CLASS)
				|| (codeRangeType == CodeRangeType.INTERFACE)
				|| (codeRangeType == CodeRangeType.ENUMERATION)
				|| (codeRangeType == CodeRangeType.ANNOTATION)
				|| (codeRangeType == CodeRangeType.MODULE)) {
			if (sloc.getPhyLOC() > 2500) {
				return SourceCodeQuality.LOW;
			}
			if (sloc.getPhyLOC() > 1000) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.HIGH;
		} else if ((codeRangeType == CodeRangeType.CONSTRUCTOR)
				|| (codeRangeType == CodeRangeType.METHOD)
				|| (codeRangeType == CodeRangeType.PROGRAM)
				|| (codeRangeType == CodeRangeType.SUBROUTINE)
				|| (codeRangeType == CodeRangeType.FUNCTION)) {
			if (sloc.getPhyLOC() > 40) {
				return SourceCodeQuality.LOW;
			}
			if (sloc.getPhyLOC() > 25) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.HIGH;
		}
		return SourceCodeQuality.UNSPECIFIED;
	}

	private static SourceCodeQuality getQualityLevelLineLength(
			CodeRangeType codeRangeType, SLOCResult sloc) {
		if (sloc.getLineStatistics().getAvg() > 70) {
			return SourceCodeQuality.LOW;
		}
		if (sloc.getLineStatistics().getAvg() > 50) {
			return SourceCodeQuality.MEDIUM;
		}
		return SourceCodeQuality.UNSPECIFIED;
	}

}
