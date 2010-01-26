/***************************************************************************
 *
 *   EntropyMetric4Java.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.cpp.metrics;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.AbstractEntropyMetric;
import com.puresol.coding.analysis.CodeEvaluationSystem;
import com.puresol.coding.analysis.QualityLevel;

/**
 * This class calculates entropy and redundancy for a code range. The
 * entropy is only calculated for operands due to the fact that only they
 * are chosen by the programmer. The entropy gives therefore a hint how
 * well the programmed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EntropyMetric4CPP extends AbstractEntropyMetric {
    public EntropyMetric4CPP(CodeRange codeRange) {
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
