package com.puresol.coding.lang.test.metrics;

import com.puresol.coding.metrics.sloc.LanguageDependedSLOCMetric;
import com.puresol.coding.metrics.sloc.SLOCType;
import com.puresol.uhura.lexer.Token;

public class SLOCMetricImpl implements LanguageDependedSLOCMetric {

	private static final long serialVersionUID = -4093999732159565527L;

	@Override
	public SLOCType getType(Token token) {
		if (token.getName().equals("Comment")) {
			return SLOCType.COMMENT;
		}
		if (token.getName().equals("WhiteSpace")) {
			return SLOCType.BLANK;
		}
		if (!token.getName().equals("LineTerminator")) {
			return SLOCType.PRODUCTIVE;
		}
		return SLOCType.PHYSICAL;
	}

}
