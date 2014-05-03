package com.puresoltechnologies.purifinity.framework.lang.java7.metrics;

import com.puresoltechnologies.parsers.impl.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.LanguageDependedSLOCMetric;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCType;

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
