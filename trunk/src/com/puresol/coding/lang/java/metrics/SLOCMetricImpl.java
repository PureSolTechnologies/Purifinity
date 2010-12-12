package com.puresol.coding.lang.java.metrics;

import com.puresol.coding.metrics.sloc.LanguageDependedSLOCMetric;
import com.puresol.coding.metrics.sloc.SourceLineType;
import com.puresol.uhura.lexer.Token;

public class SLOCMetricImpl implements LanguageDependedSLOCMetric {

	@Override
	public SourceLineType getType(Token token) {
		if (token.getName().equals("Comment")) {
			return SourceLineType.COMMENT;
		}
		if (token.getName().equals("WhiteSpace")) {
			return SourceLineType.BLANK;
		}
		if (!token.getName().equals("LineTerminator")) {
			return SourceLineType.PRODUCTIVE;
		}
		return SourceLineType.PHYSICAL;
	}

}
