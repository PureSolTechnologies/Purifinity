package com.puresoltechnologies.purifinity.coding.lang.test.metrics;

import com.puresoltechnologies.purifinity.coding.metrics.sloc.LanguageDependedSLOCMetric;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCType;
import com.puresoltechnologies.purifinity.uhura.ust.terminal.AbstractTerminal;

public class SLOCMetricImpl implements LanguageDependedSLOCMetric {

	private static final long serialVersionUID = -4093999732159565527L;

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
