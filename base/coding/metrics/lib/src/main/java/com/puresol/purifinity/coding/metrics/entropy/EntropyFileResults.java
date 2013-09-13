package com.puresol.purifinity.coding.metrics.entropy;

import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.ALL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresol.commons.utils.math.Parameter;
import com.puresol.commons.utils.math.Value;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;

public class EntropyFileResults extends AbstractEntropyResults implements
		MetricFileResults {

	private static final long serialVersionUID = 4585034044953318000L;

	private final List<EntropyResult> results = new ArrayList<EntropyResult>();

	public void add(EntropyResult result) {
		results.add(result);
		QualityLevel qualityLevel = getQualityLevel();
		if (qualityLevel == null) {
			setQualityLevel(new QualityLevel(result.getQuality()));
		} else {
			qualityLevel.add(result.getQuality());
		}
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();
		for (EntropyResult result : results) {
			Map<String, Value<?>> row = convertToRow(result);
			values.add(row);
		}

		return values;
	}

}
