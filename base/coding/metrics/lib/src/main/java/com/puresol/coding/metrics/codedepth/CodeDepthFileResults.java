package com.puresol.coding.metrics.codedepth;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.coding.evaluation.api.MetricValue;

public class CodeDepthFileResults extends ArrayList<CodeDepthFileResult>
	implements MetricResults {

    private static final long serialVersionUID = 8283322540485150992L;

    @Override
    public List<MetricValue> getResults() {
	List<MetricValue> metrics = new ArrayList<MetricValue>();
	for (CodeDepthFileResult result : this) {
	    // TODO
	}
	return metrics;
    }
}
