package com.puresol.coding.fortran;

import com.puresol.coding.CodeEvaluationSystem;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.HalsteadMetric;
import com.puresol.coding.MaintainabilityIndex;
import com.puresol.coding.McCabeMetric;
import com.puresol.coding.QualityLevel;
import com.puresol.coding.SLOCMetric;

public class MaintainabilityIndex4Fortran extends MaintainabilityIndex {

    public MaintainabilityIndex4Fortran(CodeRange codeRange,
	    SLOCMetric slocMetric, McCabeMetric mcCabeMetric,
	    HalsteadMetric halsteadMetric) {
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
