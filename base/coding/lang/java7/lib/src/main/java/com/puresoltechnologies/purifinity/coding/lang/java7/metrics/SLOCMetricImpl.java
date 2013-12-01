package com.puresoltechnologies.purifinity.coding.lang.java7.metrics;

import com.puresoltechnologies.purifinity.coding.metrics.sloc.LanguageDependedSLOCMetric;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCType;
import com.puresoltechnologies.purifinity.uhura.ust.terminal.AbstractTerminal;

public class SLOCMetricImpl implements LanguageDependedSLOCMetric {

	private static final long serialVersionUID = 6579121706560697L;

	@Override
	public SLOCType getTypeByName(String name) {
		if ("Comment".equals(name)) {
			return SLOCType.COMMENT;
		}
		if ("WhiteSpace".equals(name) || "LineTerminator".equals(name)) {
			return SLOCType.BLANK;
		}
		return SLOCType.PRODUCTIVE;
	}

	@Override
	public SLOCType getType(AbstractTerminal token) {
		return getTypeByName(token.getName());
	}
}
