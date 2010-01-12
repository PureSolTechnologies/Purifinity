/***************************************************************************
 *
 *   CodeRangeMetrics4Fortran.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.fortran.metrics;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.AbstractEntropyMetric;
import com.puresol.coding.analysis.AbstractHalsteadMetric;
import com.puresol.coding.analysis.AbstractMaintainabilityIndex;
import com.puresol.coding.analysis.AbstractMcCabeMetric;
import com.puresol.coding.analysis.AbstractSLOCMetric;
import com.puresol.coding.analysis.CodeRangeMetrics;

public class CodeRangeMetrics4Fortran extends CodeRangeMetrics {

    public CodeRangeMetrics4Fortran(CodeRange codeRange) {
	super(codeRange);
    }

    @Override
    protected void calculate() {
	if (AbstractMcCabeMetric.isSuitable(getCodeRange())) {
	    setMcCabeMetric(new McCabeMetric4Fortran(getCodeRange()));
	}
	if (AbstractHalsteadMetric.isSuitable(getCodeRange())) {
	    setHalsteadMetric(new HalsteadMetric4Fortran(getCodeRange()));
	}
	if (AbstractSLOCMetric.isSuitable(getCodeRange())) {
	    setSLOCMetric(new SLOCMetric4Fortran(getCodeRange()));
	}
	if (AbstractMaintainabilityIndex.isSuitable(getCodeRange())) {
	    setMaintainabilityIndex(new MaintainabilityIndex4Fortran(
		    getCodeRange(), getSLOCMetric(), getMcCabeMetric(),
		    getHalsteadMetric()));
	}
	if (AbstractEntropyMetric.isSuitable(getCodeRange())) {
	    setEntropyMetric(new EntropyMetric4Fortran(getCodeRange()));
	}
    }
}
