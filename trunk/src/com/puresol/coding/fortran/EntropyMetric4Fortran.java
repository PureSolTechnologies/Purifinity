package com.puresol.coding.fortran;

import com.puresol.coding.CodeEvaluationSystem;
import com.puresol.coding.CodeRange;
import com.puresol.coding.EntropyMetric;
import com.puresol.coding.QualityLevel;

/**
 * This class calculates entropy and redundancy for a code range. The
 * entropy is only calculated for operands due to the fact that only they
 * are chosen by the programmer. The entropy gives therefore a hint how
 * well the programmed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EntropyMetric4Fortran extends EntropyMetric {
    public EntropyMetric4Fortran(CodeRange codeRange) {
	super(codeRange);
    }

    @Override
    public QualityLevel getQualityLevel() {
	if (!CodeEvaluationSystem.isEvaluateEntropyMetric()) {
	    return QualityLevel.HIGH;
	}
	if (getNormRedundancy() > 0.40) {
	    return QualityLevel.LOW;
	}
	if (getNormRedundancy() > 0.20) {
	    return QualityLevel.MEDIUM;
	}
	return QualityLevel.HIGH;
    }
}
