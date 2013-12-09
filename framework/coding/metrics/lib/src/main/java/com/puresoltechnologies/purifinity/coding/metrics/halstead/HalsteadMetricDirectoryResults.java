package com.puresoltechnologies.purifinity.coding.metrics.halstead;

import static com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.ALL;

import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;

public class HalsteadMetricDirectoryResults extends AbstractHalsteadResults
		implements MetricDirectoryResults {

	private static final long serialVersionUID = -5970030495863471269L;

	private final HalsteadMetricResult result;

	public HalsteadMetricDirectoryResults(HalsteadMetricResult result) {
		super();
		if (result == null) {
			throw new IllegalArgumentException("Result must not be null!");
		}
		this.result = result;
	}

	public HalsteadMetricResult getResult() {
		return result;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public Map<String, Value<?>> getValues() {
		return convertToRow(result);
	}
}
