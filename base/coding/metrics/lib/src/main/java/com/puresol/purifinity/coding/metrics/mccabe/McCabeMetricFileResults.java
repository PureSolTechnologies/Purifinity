package com.puresol.purifinity.coding.metrics.mccabe;

import static com.puresol.purifinity.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.ALL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.utils.math.Parameter;
import com.puresol.purifinity.utils.math.Value;

public class McCabeMetricFileResults extends AbstractMcCabeMetricResults
		implements MetricFileResults {

	private final List<McCabeMetricResult> results = new ArrayList<McCabeMetricResult>();

	private static final long serialVersionUID = -5992363758018121695L;

	public List<McCabeMetricResult> getResults() {
		return results;
	}

	public void add(McCabeMetricResult result) {
		results.add(result);
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (McCabeMetricResult result : results) {
			Map<String, Value<?>> row = convertToRow(result);
			values.add(row);
		}

		return values;
	}

}
