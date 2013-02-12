package com.puresol.coding.metrics.entropy;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.SourceCodeQuality;

public class EntropyQuality {

	public static SourceCodeQuality get(CodeRangeType codeRangeType,
			EntropyResult result) {

		if ((codeRangeType == CodeRangeType.FILE)
				|| (codeRangeType == CodeRangeType.CLASS)
				|| (codeRangeType == CodeRangeType.INTERFACE)
				|| (codeRangeType == CodeRangeType.ENUMERATION)
				|| (codeRangeType == CodeRangeType.ANNOTATION)
				|| (codeRangeType == CodeRangeType.MODULE)) {
			if (result.getNormalizedRedundancy() > 0.40) {
				return SourceCodeQuality.LOW;
			}
			if (result.getNormalizedRedundancy() > 0.20) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.HIGH;
		} else if ((codeRangeType == CodeRangeType.CONSTRUCTOR)
				|| (codeRangeType == CodeRangeType.METHOD)
				|| (codeRangeType == CodeRangeType.PROGRAM)
				|| (codeRangeType == CodeRangeType.SUBROUTINE)
				|| (codeRangeType == CodeRangeType.FUNCTION)) {
			if (result.getNormalizedRedundancy() > 0.40) {
				return SourceCodeQuality.LOW;
			}
			if (result.getNormalizedRedundancy() > 0.20) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.HIGH;
		}
		return SourceCodeQuality.UNSPECIFIED;
	}
}
