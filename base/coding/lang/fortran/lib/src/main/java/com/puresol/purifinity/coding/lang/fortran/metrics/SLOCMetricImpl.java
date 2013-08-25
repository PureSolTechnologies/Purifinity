package com.puresol.purifinity.coding.lang.fortran.metrics;

import com.puresol.purifinity.coding.metrics.sloc.LanguageDependedSLOCMetric;
import com.puresol.purifinity.coding.metrics.sloc.SLOCType;
import com.puresol.purifinity.uhura.ust.terminal.AbstractTerminal;

public class SLOCMetricImpl implements LanguageDependedSLOCMetric {

	private static final long serialVersionUID = 4638021148169259076L;

	@Override
	public SLOCType getTypeByName(String name) {
		if ("LINE_COMMENT".equals(name) || "COMMENT_LINE".equals(name)) {
			return SLOCType.COMMENT;
		}
		if ("WHITESPACE".equals(name) || "LINE_CONCATATION".equals(name)
				|| "LINE_TERMINATOR".equals(name)) {
			return SLOCType.BLANK;
		}
		return SLOCType.PRODUCTIVE;
	}

	@Override
	public SLOCType getType(AbstractTerminal token) {
		return getTypeByName(token.getName());
	}
}
