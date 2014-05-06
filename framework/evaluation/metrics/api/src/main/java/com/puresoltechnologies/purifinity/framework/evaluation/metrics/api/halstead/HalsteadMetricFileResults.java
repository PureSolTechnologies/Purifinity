package com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.halstead;

import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.halstead.HalsteadMetricEvaluatorParameter.ALL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.AbstractHalsteadResults;

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