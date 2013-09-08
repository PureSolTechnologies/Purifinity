package com.puresol.purifinity.coding.metrics.halstead;

import static com.puresol.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorParameter.ALL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresol.commons.utils.math.Parameter;
import com.puresol.commons.utils.math.Value;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;

public class HalsteadMetricFileResults extends AbstractHalsteadResults
		implements MetricFileResults {

	private static final long serialVersionUID = -5970030495863471269L;

	private final List<HalsteadMetricResult> results = new ArrayList<HalsteadMetricResult>();

	public void add(HalsteadMetricResult result) {
		results.add(result);
		QualityLevel qualityLevel = getQualityLevel();
		if (qualityLevel == null) {
			setQualityLevel(new QualityLevel(result.getQuality()));
		} else {
			qualityLevel.add(result.getQuality());
		}
	}

	public List<HalsteadMetricResult> getResults() {
		return results;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (HalsteadMetricResult result : results) {
			Map<String, Value<?>> row = convertToRow(result);
			values.add(row);
		}

		return values;
	}

}
