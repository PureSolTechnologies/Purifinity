package com.puresol.coding.java;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeMetrics;
import com.puresol.coding.EntropyMetric;
import com.puresol.coding.HalsteadMetric;
import com.puresol.coding.MaintainabilityIndex;
import com.puresol.coding.McCabeMetric;
import com.puresol.coding.SLOCMetric;

public class CodeRangeMetrics4Java extends CodeRangeMetrics {

    public CodeRangeMetrics4Java(CodeRange codeRange) {
	super(codeRange);
    }

    @Override
    protected void calculate() {
	if (McCabeMetric.isSuitable(getCodeRange())) {
	    setMcCabeMetric(new McCabeMetric4Java(getCodeRange()));
	}
	if (HalsteadMetric.isSuitable(getCodeRange())) {
	    setHalsteadMetric(new HalsteadMetric4Java(getCodeRange()));
	}
	if (SLOCMetric.isSuitable(getCodeRange())) {
	    setSLOCMetric(new SLOCMetric4Java(getCodeRange()));
	}
	if (MaintainabilityIndex.isSuitable(getCodeRange())) {
	    setMaintainabilityIndex(new MaintainabilityIndex4Java(
		    getCodeRange(), getSLOCMetric(), getMcCabeMetric(),
		    getHalsteadMetric()));
	}
	if (EntropyMetric.isSuitable(getCodeRange())) {
	    setEntropyMetric(new EntropyMetric4Java(getCodeRange()));
	}
    }
}
