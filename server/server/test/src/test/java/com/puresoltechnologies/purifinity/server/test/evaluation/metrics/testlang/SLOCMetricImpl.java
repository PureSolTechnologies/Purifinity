package com.puresoltechnologies.purifinity.server.test.evaluation.metrics.testlang;

import com.puresoltechnologies.parsers.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.purifinity.analysis.domain.SLOCType;
import com.puresoltechnologies.purifinity.server.metrics.spi.LanguageDependedSLOCMetric;

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
