/***************************************************************************
 *
 *   HalsteadMetric4Java.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.cpp.metrics;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.analysis.AbstractHalsteadMetric;
import com.puresol.coding.analysis.CodeEvaluationSystem;
import com.puresol.coding.analysis.QualityLevel;

public class HalsteadMetric4CPP extends AbstractHalsteadMetric {

    public HalsteadMetric4CPP(CodeRange codeRange) {
	super(codeRange);
    }

    @Override
    public QualityLevel getQualityLevel() {
	if (!CodeEvaluationSystem.isEvaluateHalsteadMetric()) {
	    return QualityLevel.HIGH;
	}
	CodeRange range = getCodeRange();
	if ((range.getType() == CodeRangeType.FILE)
		|| (range.getType() == CodeRangeType.CLASS)
		|| (range.getType() == CodeRangeType.ENUMERATION)) {
	    if (get_HV() < 80) {
		return QualityLevel.MEDIUM;
	    }
	    if (get_HV() > 10000) {
		return QualityLevel.LOW;
	    }
	    if (get_HV() > 8000) {
		return QualityLevel.MEDIUM;
	    }
	    return QualityLevel.HIGH;
	} else if ((range.getType() == CodeRangeType.CONSTRUCTOR)
		|| (range.getType() == CodeRangeType.METHOD)
		|| (range.getType() == CodeRangeType.FUNCTION)
		|| (range.getType() == CodeRangeType.INTERFACE)) {
	    if (get_HV() < 10) {
		return QualityLevel.MEDIUM;
	    }
	    if (get_HV() > 1250) {
		return QualityLevel.LOW;
	    }
	    if (get_HV() > 1000) {
		return QualityLevel.MEDIUM;
	    }
	    return QualityLevel.HIGH;
	}
	return QualityLevel.HIGH; // not evaluated...
    }
}
