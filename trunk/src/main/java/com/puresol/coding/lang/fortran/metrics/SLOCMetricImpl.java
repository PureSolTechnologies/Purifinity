package com.puresol.coding.lang.fortran.metrics;

import com.puresol.coding.metrics.sloc.LanguageDependedSLOCMetric;
import com.puresol.coding.metrics.sloc.SLOCType;
import com.puresol.uhura.lexer.Token;

public class SLOCMetricImpl implements LanguageDependedSLOCMetric {

	@Override
	public SLOCType getType(Token token) {
		if (token.getName().equals("LINE_COMMENT")) {
			return SLOCType.COMMENT;
		}
		if (token.getName().equals("WHITESPACE")
				|| token.getName().equals("LINE_CONCATATION")) {
			return SLOCType.BLANK;
		}
		if (!token.getName().equals("LINE_TERMINATOR")) {
			return SLOCType.PRODUCTIVE;
		}
		return SLOCType.PHYSICAL;
	}

}
