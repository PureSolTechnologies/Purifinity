package com.puresol.coding.metrics.codedepth;

import com.puresol.coding.CodeRangeType;
import com.puresol.coding.quality.SourceCodeQuality;

public class CodeDepthQuality {

	public static SourceCodeQuality get(CodeRangeType codeRangeType,
			int maxDepth) {

		if ((codeRangeType == CodeRangeType.FILE)
				|| (codeRangeType == CodeRangeType.CLASS)
				|| (codeRangeType == CodeRangeType.INTERFACE)
				|| (codeRangeType == CodeRangeType.ENUMERATION)
				|| (codeRangeType == CodeRangeType.ANNOTATION)
				|| (codeRangeType == CodeRangeType.MODULE)) {
			if (maxDepth > 7) {
				return SourceCodeQuality.LOW;
			} else if (maxDepth > 5) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.HIGH;
		} else if ((codeRangeType == CodeRangeType.CONSTRUCTOR)
				|| (codeRangeType == CodeRangeType.METHOD)
				|| (codeRangeType == CodeRangeType.PROGRAM)
				|| (codeRangeType == CodeRangeType.SUBROUTINE)
				|| (codeRangeType == CodeRangeType.FUNCTION)) {
			if (maxDepth > 6) {
				return SourceCodeQuality.LOW;
			} else if (maxDepth > 4) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.HIGH;
		}
		return SourceCodeQuality.UNSPECIFIED;
	}
}
