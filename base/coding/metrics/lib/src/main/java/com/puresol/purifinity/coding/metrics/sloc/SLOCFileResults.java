package com.puresol.purifinity.coding.metrics.sloc;

import static com.puresol.purifinity.coding.metrics.sloc.SLOCEvaluatorParameter.ALL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresol.commons.utils.math.Parameter;
import com.puresol.commons.utils.math.Value;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;

public class SLOCFileResults extends AbstractSLOCResults implements
		MetricFileResults {

	private static final long serialVersionUID = -3995835459935284595L;

	private final List<SLOCResult> results = new ArrayList<SLOCResult>();

	public void add(SLOCResult result) {
		results.add(result);
	}

	public List<SLOCResult> getResults() {
		return results;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (SLOCResult result : results) {
			Map<String, Value<?>> row = convertToRow(result);
			values.add(row);
		}

		return values;
	}
}
