package com.puresol.coding.metrics.mccabe;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.SourceCodeQuality;

public class McCabeQuality {

	public static SourceCodeQuality get(CodeRangeType codeRangeType,
			int cyclomaticNumber) {

		if ((codeRangeType == CodeRangeType.FILE)
				|| (codeRangeType == CodeRangeType.CLASS)
				|| (codeRangeType == CodeRangeType.INTERFACE)
				|| (codeRangeType == CodeRangeType.ENUMERATION)
				|| (codeRangeType == CodeRangeType.ANNOTATION)
				|| (codeRangeType == CodeRangeType.MODULE)) {
			if (cyclomaticNumber < 100) {
				return SourceCodeQuality.HIGH;
			}
			if (cyclomaticNumber < 125) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.LOW;
		} else if ((codeRangeType == CodeRangeType.CONSTRUCTOR)
				|| (codeRangeType == CodeRangeType.METHOD)
				|| (codeRangeType == CodeRangeType.PROGRAM)
				|| (codeRangeType == CodeRangeType.SUBROUTINE)
				|| (codeRangeType == CodeRangeType.FUNCTION)) {
			if (cyclomaticNumber < 15) {
				return SourceCodeQuality.HIGH;
			}
			if (cyclomaticNumber < 20) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.LOW;
		}
		return SourceCodeQuality.UNSPECIFIED;
	}
}
