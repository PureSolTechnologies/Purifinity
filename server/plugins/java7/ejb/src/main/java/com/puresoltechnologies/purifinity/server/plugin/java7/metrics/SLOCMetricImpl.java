package com.puresoltechnologies.purifinity.server.plugin.java7.metrics;

import com.puresoltechnologies.parsers.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.purifinity.server.metrics.spi.LanguageDependedSLOCMetric;
import com.puresoltechnologies.purifinity.server.metrics.spi.sloc.SLOCType;

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
