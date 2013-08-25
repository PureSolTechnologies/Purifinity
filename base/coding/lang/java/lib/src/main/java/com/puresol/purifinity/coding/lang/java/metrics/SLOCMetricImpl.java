package com.puresol.purifinity.coding.lang.java.metrics;

import com.puresol.purifinity.coding.metrics.sloc.LanguageDependedSLOCMetric;
import com.puresol.purifinity.coding.metrics.sloc.SLOCType;
import com.puresol.purifinity.uhura.ust.terminal.AbstractTerminal;

public class SLOCMetricImpl implements LanguageDependedSLOCMetric {

	private static final long serialVersionUID = 6579121706560697L;

	@Override
	public SLOCType getTypeByName(String name) {
		if ("Comment".equals(name)) {
			return SLOCType.COMMENT;
		}
		if ("WhiteSpace".equals(name)) {
			return SLOCType.BLANK;
		}
		if (!"LineTerminator".equals(name)) {
			return SLOCType.PRODUCTIVE;
		}
		return SLOCType.PHYSICAL;
	}

	@Override
	public SLOCType getType(AbstractTerminal token) {
		return getTypeByName(token.getName());
	}
}
