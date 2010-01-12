/***************************************************************************
 *
 *   CodeRangeMetrics4Java.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.java.metrics;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeMetrics;
import com.puresol.coding.AbstractEntropyMetric;
import com.puresol.coding.AbstractHalsteadMetric;
import com.puresol.coding.AbstractMaintainabilityIndex;
import com.puresol.coding.AbstractMcCabeMetric;
import com.puresol.coding.AbstractSLOCMetric;

public class CodeRangeMetrics4Java extends CodeRangeMetrics {

    public CodeRangeMetrics4Java(CodeRange codeRange) {
	super(codeRange);
    }

    @Override
    protected void calculate() {
	if (AbstractMcCabeMetric.isSuitable(getCodeRange())) {
	    setMcCabeMetric(new McCabeMetric4Java(getCodeRange()));
	}
	if (AbstractHalsteadMetric.isSuitable(getCodeRange())) {
	    setHalsteadMetric(new HalsteadMetric4Java(getCodeRange()));
	}
	if (AbstractSLOCMetric.isSuitable(getCodeRange())) {
	    setSLOCMetric(new SLOCMetric4Java(getCodeRange()));
	}
	if (AbstractMaintainabilityIndex.isSuitable(getCodeRange())) {
	    setMaintainabilityIndex(new MaintainabilityIndex4Java(
		    getCodeRange(), getSLOCMetric(), getMcCabeMetric(),
		    getHalsteadMetric()));
	}
	if (AbstractEntropyMetric.isSuitable(getCodeRange())) {
	    setEntropyMetric(new EntropyMetric4Java(getCodeRange()));
	}
    }
}
