/***************************************************************************
 *
 *   CodeRangeMetrics4Fortran.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.fortran;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeMetrics;
import com.puresol.coding.EntropyMetric;
import com.puresol.coding.HalsteadMetric;
import com.puresol.coding.MaintainabilityIndex;
import com.puresol.coding.McCabeMetric;
import com.puresol.coding.SLOCMetric;

public class CodeRangeMetrics4Fortran extends CodeRangeMetrics {

    public CodeRangeMetrics4Fortran(CodeRange codeRange) {
	super(codeRange);
    }

    @Override
    protected void calculate() {
	if (McCabeMetric.isSuitable(getCodeRange())) {
	    setMcCabeMetric(new McCabeMetric4Fortran(getCodeRange()));
	}
	if (HalsteadMetric.isSuitable(getCodeRange())) {
	    setHalsteadMetric(new HalsteadMetric4Fortran(getCodeRange()));
	}
	if (SLOCMetric.isSuitable(getCodeRange())) {
	    setSLOCMetric(new SLOCMetric4Fortran(getCodeRange()));
	}
	if (MaintainabilityIndex.isSuitable(getCodeRange())) {
	    setMaintainabilityIndex(new MaintainabilityIndex4Fortran(
		    getCodeRange(), getSLOCMetric(), getMcCabeMetric(),
		    getHalsteadMetric()));
	}
	if (EntropyMetric.isSuitable(getCodeRange())) {
	    setEntropyMetric(new EntropyMetric4Fortran(getCodeRange()));
	}
    }
}
