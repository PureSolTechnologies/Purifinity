/***************************************************************************
 *
 *   CodeRangeMetrics4Java.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.cpp.metrics;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.AbstractEntropyMetric;
import com.puresol.coding.analysis.AbstractHalsteadMetric;
import com.puresol.coding.analysis.AbstractMaintainabilityIndex;
import com.puresol.coding.analysis.AbstractMcCabeMetric;
import com.puresol.coding.analysis.AbstractSLOCMetric;
import com.puresol.coding.analysis.CodeRangeMetrics;

public class CodeRangeMetrics4CPP extends CodeRangeMetrics {

    public CodeRangeMetrics4CPP(CodeRange codeRange) {
	super(codeRange);
    }

    @Override
    protected void calculate() {
	if (AbstractMcCabeMetric.isSuitable(getCodeRange())) {
	    setMcCabeMetric(new McCabeMetric4CPP(getCodeRange()));
	}
	if (AbstractHalsteadMetric.isSuitable(getCodeRange())) {
	    setHalsteadMetric(new HalsteadMetric4CPP(getCodeRange()));
	}
	if (AbstractSLOCMetric.isSuitable(getCodeRange())) {
	    setSLOCMetric(new SLOCMetric4CPP(getCodeRange()));
	}
	if (AbstractMaintainabilityIndex.isSuitable(getCodeRange())) {
	    setMaintainabilityIndex(new MaintainabilityIndex4CPP(
		    getCodeRange(), getSLOCMetric(), getMcCabeMetric(),
		    getHalsteadMetric()));
	}
	if (AbstractEntropyMetric.isSuitable(getCodeRange())) {
	    setEntropyMetric(new EntropyMetric4CPP(getCodeRange()));
	}
    }
}
