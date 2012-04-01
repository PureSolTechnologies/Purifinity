package com.puresol.coding.metrics.halstead;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.quality.SourceCodeQuality;

public class HalsteadQuality {

	public static SourceCodeQuality get(CodeRangeType codeRangeType,
			HalsteadResult result) {
		if ((codeRangeType == CodeRangeType.FILE)
				|| (codeRangeType == CodeRangeType.CLASS)
				|| (codeRangeType == CodeRangeType.INTERFACE)
				|| (codeRangeType == CodeRangeType.ENUMERATION)
				|| (codeRangeType == CodeRangeType.ANNOTATION)
				|| (codeRangeType == CodeRangeType.MODULE)) {
			if (result.getHalsteadVolume() < 80) {
				return SourceCodeQuality.MEDIUM;
			}
			if (result.getHalsteadVolume() > 10000) {
				return SourceCodeQuality.LOW;
			}
			if (result.getHalsteadVolume() > 8000) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.HIGH;
		} else if ((codeRangeType == CodeRangeType.CONSTRUCTOR)
				|| (codeRangeType == CodeRangeType.METHOD)
				|| (codeRangeType == CodeRangeType.PROGRAM)
				|| (codeRangeType == CodeRangeType.SUBROUTINE)
				|| (codeRangeType == CodeRangeType.FUNCTION)) {
			if (result.getHalsteadVolume() < 10) {
				return SourceCodeQuality.MEDIUM;
			}
			if (result.getHalsteadVolume() > 1250) {
				return SourceCodeQuality.LOW;
			}
			if (result.getHalsteadVolume() > 1000) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.HIGH;
		}
		return SourceCodeQuality.UNSPECIFIED;
	}
}
