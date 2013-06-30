package com.puresol.purifinity.coding.lang.fortran.metrics;

import com.puresol.purifinity.coding.metrics.sloc.LanguageDependedSLOCMetric;
import com.puresol.purifinity.coding.metrics.sloc.SLOCType;
import com.puresol.purifinity.uhura.lexer.Token;

public class SLOCMetricImpl implements LanguageDependedSLOCMetric {

	private static final long serialVersionUID = 4638021148169259076L;

	@Override
	public SLOCType getType(Token token) {
		if ("LINE_COMMENT".equals(token.getName())
				|| "COMMENT_LINE".equals(token.getName())) {
			return SLOCType.COMMENT;
		}
		if ("WHITESPACE".equals(token.getName())
				|| "LINE_CONCATATION".equals(token.getName())
				|| "LINE_TERMINATOR".equals(token.getName())) {
			return SLOCType.BLANK;
		}
		return SLOCType.PRODUCTIVE;
	}
}
