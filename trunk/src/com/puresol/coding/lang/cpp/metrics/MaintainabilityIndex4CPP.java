/***************************************************************************
 *
 *   MaintainabilityIndex4Java.java
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
import com.puresol.coding.analysis.AbstractMaintainabilityIndex;
import com.puresol.coding.analysis.AbstractMcCabeMetric;
import com.puresol.coding.analysis.AbstractSLOCMetric;
import com.puresol.coding.analysis.CodeEvaluationSystem;
import com.puresol.coding.analysis.QualityLevel;

public class MaintainabilityIndex4CPP extends AbstractMaintainabilityIndex {

    public MaintainabilityIndex4CPP(CodeRange codeRange,
	    AbstractSLOCMetric slocMetric, AbstractMcCabeMetric mcCabeMetric,
	    AbstractHalsteadMetric halsteadMetric) {
	super(codeRange, slocMetric, mcCabeMetric, halsteadMetric);
    }

    @Override
    public QualityLevel getQualityLevel() {
	if (!CodeEvaluationSystem.isEvaluateMaintainabilityIndex()) {
	    return QualityLevel.HIGH;
	}
	CodeRange range = getCodeRange();
	if ((range.getType() == CodeRangeType.FILE)
		|| (range.getType() == CodeRangeType.CLASS)
		|| (range.getType() == CodeRangeType.ENUMERATION)) {
	    if (getMI() > 86) {
		return QualityLevel.HIGH;
	    }
	    if (getMI() > 65) {
		return QualityLevel.MEDIUM;
	    }
	    return QualityLevel.LOW;
	} else if ((range.getType() == CodeRangeType.CONSTRUCTOR)
		|| (range.getType() == CodeRangeType.METHOD)
		|| (range.getType() == CodeRangeType.FUNCTION)
		|| (range.getType() == CodeRangeType.INTERFACE)) {
	    if (getMI() > 85) {
		return QualityLevel.HIGH;
	    }
	    if (getMI() > 65) {
		return QualityLevel.MEDIUM;
	    }
	    return QualityLevel.LOW;
	}
	return QualityLevel.HIGH; // not evaluated...
    }
}
